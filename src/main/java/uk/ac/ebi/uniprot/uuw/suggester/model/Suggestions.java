package uk.ac.ebi.uniprot.uuw.suggester.model;

import org.apache.solr.client.solrj.response.SuggesterResponse;
import uk.ac.ebi.uniprot.uuw.suggester.SuggestionDictionary;

import java.util.List;

/**
 * Created 18/07/18
 *
 * @author Edd
 */
public class Suggestions {
    private final String query;
    private final String dictionary;
    private final List<String> suggestions;

    private Suggestions(String dictionary, String query, List<String> suggestions) {
        this.dictionary = dictionary;
        this.query = query;
        this.suggestions = suggestions;
    }

    public static Suggestions createSuggestions(SuggestionDictionary dictionary, String query, SuggesterResponse response) {
        return new Suggestions(dictionary.name(), query, response.getSuggestedTerms().get(dictionary.getId()));
    }

    public String getDictionary() {
        return dictionary;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public String getQuery() {
        return query;
    }
}
