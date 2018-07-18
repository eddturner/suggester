package uk.ac.ebi.uniprot.uuw.suggester.model;

import org.apache.solr.client.solrj.response.SuggesterResponse;
import uk.ac.ebi.uniprot.uuw.suggester.SuggestionDictionary;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created 18/07/18
 *
 * @author Edd
 */
public class Suggestions {
    private final String query;
    private final String dictionary;
    private final List<Suggestion> suggestions;

    private Suggestions(String dictionary, String query, List<Suggestion> suggestions) {
        this.dictionary = dictionary;
        this.query = query;
        this.suggestions = suggestions;
    }

    public static Suggestions createSuggestions(SuggestionDictionary dictionary, String query, SuggesterResponse response) {
        List<Suggestion> suggestionStrings = response.getSuggestedTerms().get(dictionary.getId())
                .stream()
                .map(Suggestion::new)
                .collect(Collectors.toList());
        return new Suggestions(dictionary.getId(), query, suggestionStrings);
    }

    public String getDictionary() {
        return dictionary;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public String getQuery() {
        return query;
    }
}
