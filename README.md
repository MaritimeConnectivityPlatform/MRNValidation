[![Build Status](https://travis-ci.org/MaritimeConnectivityPlatform/MRNValidation.svg?branch=master)](https://travis-ci.org/MaritimeConnectivityPlatform/MRNValidation)
# MRNValidation
JAVA library for MRN validation

## Build
```
  mvn clean install
```
  
## Run
- Checking MCP MRN compliance
```
  java -jar target/mrn-validation-0.1.0-SNAPSHOT-jar-with-dependencies.jar --mrn "urn:mrn:mcp:user:granpa:thomas" --mcp
```
- Expected result of the command above:
```
  [main] INFO net.maritimeconnectivity.mrn.Main - PASS: urn:mrn:mcp:user:granpa:thomas is valid as an MCP MRN.
```
