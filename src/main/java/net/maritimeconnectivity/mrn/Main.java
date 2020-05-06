package net.maritimeconnectivity.mrn;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


@Slf4j
public class Main {
    private static final String HELP = "help";
    private static final String MRN = "mrn";
    private static final String MCP = "mcp";
    private static final String REGEX = "regex";

    private Options setupOptions() {
        // Create Options object
        Options options = new Options();
        // Help output
        options.addOption("h", HELP, false, "Show this help message");
        options.addOption("mcp",MCP, false, "Check the MCP MRN compliance");
        options.addOption("mrn",MRN, true, "MRN to validate");
        options.addOption("reg",REGEX, true, "Regular expression of the MRN namespace for validation");

        return options;
    }

    private void validate(CommandLine cmd) {
        if (!cmd.hasOption(MRN)) {
            log.error("The validation requires the parameters: " + String.join(", ", MRN));
            return;
        }

        try {
            MRNValidator validator = new MRNValidator();
            String regex = cmd.getOptionValue(REGEX) == null ? "" : cmd.getOptionValue(REGEX);
            String mrn = cmd.getOptionValue(MRN);

            if(cmd.hasOption(MCP)){
                if(validator.isValidMCPMRN(mrn))
                    log.info("PASS: " + mrn +" is valid as an MCP MRN.");
                else
                    log.info("FAIL: " + mrn +" is not valid as an MCP MRN.");
            }
            else{
                if(validator.isValid(mrn, regex))
                    log.info("PASS: " + mrn +" is valid.");
                else
                    log.info("FAIL: " + mrn +" is not valid.");
            }
        } catch (Exception e) {
            log.error("Could not validate the MRN : " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Main main = new Main();

        CommandLineParser parser = new DefaultParser();
        Options options = main.setupOptions();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            log.error("Parsing failed. Reason: " + e.getMessage());
            return;
        }

        if (cmd.hasOption(MRN)) {
            main.validate(cmd);
        } else {
            // Automatically generate the help statement
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "mcp-mrn", options );
        }
    }
}
