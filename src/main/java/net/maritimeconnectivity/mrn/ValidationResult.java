package net.maritimeconnectivity.mrn;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class ValidationResult {
    private final boolean valid;

    private final String mrn;

    public ValidationResult(String mrn, String regex){
        super();
        this.mrn = mrn;
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mrn);
        this.valid = m.matches();
    }
}
