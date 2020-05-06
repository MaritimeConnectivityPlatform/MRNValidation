package net.maritimeconnectivity.mrn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationResult {
    private boolean result;

    private String mrn;

    public String getMrn() {
        return mrn;
    }

    public boolean getResult() {
        return result;
    }

    public ValidationResult(String mrn, String regex){
        super();
        this.mrn = mrn;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mrn);
        this.result = m.matches();
    }
}
