package uk.ac.ebi.uniprot.uuw.suggester.model;

/**
 * Created 18/07/18
 *
 * @author Edd
 */
public class Suggestion {
    private final String suggestion;

    Suggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
