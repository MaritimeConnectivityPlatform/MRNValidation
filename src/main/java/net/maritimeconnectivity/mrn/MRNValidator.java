package net.maritimeconnectivity.mrn;

public class MRNValidator {
    private String mrnScheme = "^[Uu][Rr][Nn]\\:[Mm][Rr][Nn]\\:([A-Za-z0-9]([A-Za-z0-9]|\\-){0,20}[A-Za-z0-9])\\:([A-Za-z0-9][-A-Za-z0-9]{0,20}[A-Za-z0-9])\\:((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/)*)((\\?\\+((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/|\\?)*))?(\\?\\=((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/|\\?)*))?)?(#(((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/|\\?)*))?$";
    private String mcpMrnScheme = "^[Uu][Rr][Nn]\\:[Mm][Rr][Nn]\\:[Mm][Cc][Pp]\\:([Dd][Ee][Vv][Ii][Cc][Ee]|[Oo][Rr][Gg]|[Uu][Ss][Ee][Rr]|[Vv][Ee][Ss][Ss][Ee][Ll]|[Ss][Ee][Rr][Vv][Ii][Cc][Ee])\\:([A-Za-z0-9]([A-Za-z0-9]|\\-){0,20}[A-Za-z0-9])\\:((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|)*)$";

    public boolean isValid(String mrn, String regex){
        if(regex.isEmpty())
            regex = mrnScheme;
        return (new ValidationResult(mrn, regex)).getResult();
    }

    public boolean isValidMCPMRN(String mrn){
        return (new ValidationResult(mrn, mcpMrnScheme)).getResult();
    }
}
