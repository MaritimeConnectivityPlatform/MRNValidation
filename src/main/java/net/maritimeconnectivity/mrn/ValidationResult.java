package net.maritimeconnectivity.mrn;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MRN validation result class
 */
@Getter
public class ValidationResult {
    /**
     * validation test result
     */
    protected final boolean valid;

    /**
     * MRN string to validate
     */
    protected final String mrn;

    /**
     * A constructor of ValidationResult that generates the conformance test result
     * @param mrn a mrn string to check
     * @param regex a regular expression for validation
     */
    public ValidationResult(String mrn, String regex){
        super();
        this.mrn = mrn;
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mrn);
        this.valid = m.matches();
    }
}
