package net.maritimeconnectivity.mrn;

/**
 * MRN validation class that returns the conformance of given MRN string with given regular expression (checks with the general MRN scheme when it comes without regular expression)
 */
public class MRNValidator {
    /**
     * regex for generic MRN scheme
     */
    protected static final String MRN_SCHEME = "^urn:mrn:([a-z0-9]([a-z0-9]|-){0,20}[a-z0-9]):([a-z0-9][-a-z0-9]{0,20}[a-z0-9]):((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)|/)*)((\\?\\+((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)|/|\\?)*))?(\\?=((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)|/|\\?)*))?)?(#(((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)|/|\\?)*))?$";
    /**
     * regex for MCP MRN scheme
     */
    protected static final String MCP_MRN_SCHEME = "^urn:mrn:mcp:(device|org|user|vessel|service|mms):([a-z0-9]([a-z0-9]|-){0,20}[a-z0-9]):((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)|/)*)$";

    /**
     * Test the MRN scheme conformance of given mrn and regular expression, and return the result
     * Return general MRN scheme conformance result if it does not contain regex parameter
     * @param mrn a MRN string to check
     * @param regex a regular expression for validation (optional)
     * @return boolean conformance test result
     */
    public boolean isValid(String mrn, String regex){
        if(regex.isEmpty())
            regex = MRN_SCHEME;
        return (new ValidationResult(mrn, regex)).isValid();
    }

    /**
     * Test the MCP MRN scheme conformance of given mrn, and return the result
     * @param mrn a MRN string to check
     * @return boolean conformance test result
     */
    public boolean isValidMCPMRN(String mrn){
        return (new ValidationResult(mrn, MCP_MRN_SCHEME)).isValid();
    }
}
