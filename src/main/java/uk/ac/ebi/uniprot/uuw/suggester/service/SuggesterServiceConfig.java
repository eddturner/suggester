package uk.ac.ebi.uniprot.uuw.suggester.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * Created 18/07/18
 *
 * @author Edd
 */
@Configuration
@Import(SolrConfigProperties.class)
public class SuggesterServiceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuggesterServiceConfig.class);
    private static final String ID = "id";
    private static final String COMMA = ",";

    @Bean
    public SolrConfigProperties solrConfigProperties() {
        return new SolrConfigProperties();
    }

    @Bean
    public SolrClient createSolr(SolrConfigProperties solrConfigProperties) {
        String solrUrl = solrConfigProperties.getUrl();
        if (solrConfigProperties.isUseCloudClient()) {
            String[] split = solrUrl.split(COMMA);
            LOGGER.debug("Using CloudSolrClient (i.e., pointing to a Zookeeper) with url: {}", split);
            CloudSolrClient build = new CloudSolrClient.Builder().withZkHost(Arrays.asList(split)).build();
            build.setDefaultCollection(solrConfigProperties.getCollectionName());
            String idFieldName = solrConfigProperties.getIdFieldName();
            if (!idFieldName.equals(ID)) {
                build.setIdField(idFieldName);
            }
            return build;
        } else {
            LOGGER.debug("Using ConcurrentUpdateSolrClient with url: {}", solrUrl);
            return new ConcurrentUpdateSolrClient.Builder(solrUrl)
                    .withQueueSize(solrConfigProperties.getQueueSize())
                    .withThreadCount(solrConfigProperties.getThreadCount()).build();
        }
    }

    @Bean
    public SuggesterService suggesterService(SolrClient solrClient) {
        return new SuggesterService(solrClient);
    }
}
