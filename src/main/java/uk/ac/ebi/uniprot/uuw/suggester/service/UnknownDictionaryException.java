package uk.ac.ebi.uniprot.uuw.suggester.service;

/**
 * Created 18/07/18
 *
 * @author Edd
 */
public class UnknownDictionaryException extends RuntimeException {
    public UnknownDictionaryException(String message) {
        super(message);
    }
}
