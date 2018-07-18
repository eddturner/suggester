package uk.ac.ebi.uniprot.uuw.suggester.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.NamedList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.ac.ebi.uniprot.uuw.suggester.SuggestionDictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created 18/07/18
 *
 * @author Edd
 */
@RunWith(MockitoJUnitRunner.class)
public class SuggesterServiceTest {
    @Mock
    private SolrClient solrClient;
    private SuggesterService suggesterService;

    @Before
    public void setUp() {
        this.suggesterService = new SuggesterService(solrClient);
    }

    @Test
    public void createsSuggestionsWhenSolrFindsThemSuccessfully() throws IOException, SolrServerException {
        // TODO: 18/07/18
        Map<String, NamedList<Object>> responseMap = new HashMap<>();
        QueryResponse queryResponse = new QueryResponse();
//        queryResponse.setResponse();
        when(solrClient.query(any())).thenReturn(queryResponse);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = SuggestionRetrievalException.class)
    public void suggestionThatCausesSolrExceptionCausesSuggestionRetrievalException() throws IOException, SolrServerException {
        when(solrClient.query(any())).thenThrow(SolrServerException.class);
        suggesterService.getSuggestions(SuggestionDictionary.taxonomy, "some text");
    }
}