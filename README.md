[![Build Status](https://travis-ci.org/MaritimeConnectivityPlatform/MRNValidation.svg?branch=master)](https://travis-ci.org/MaritimeConnectivityPlatform/MRNValidation)
# MRNValidation
A Java library for MRN validation 
(registered in the Maven repository: https://mvnrepository.com/artifact/net.maritimeconnectivity.mrn/mrn-validation )

## What is MRN?
[**Maritime Resource Name (MRN)**](https://www.iala-aism.org/technical/data-modelling/mrn/)is a naming scheme under [Uniform Resource Name (URN)](https://en.wikipedia.org/wiki/Uniform_Resource_Name) that can uniquely identify any maritime resource on a global scale, hosted by [IALA](https://www.iala-aism.org/).
This implementation is based on the version 1 of MRN proposed by Kasper Nielsen. (https://www.iana.org/assignments/urn-formal/mrn)

## How to use
- Checking MRN compliance
```
  java -jar target/mrn-validation-0.1.0-SNAPSHOT-jar-with-dependencies.jar --mrn "urn:mrn:mcp:user:granpa:thomas"
```
Expected result of the command above:
```
  [main] INFO net.maritimeconnectivity.mrn.Main - PASS: urn:mrn:mcp:user:granpa:thomas is valid.
```

- Checking MRN compliance with custom regex
```
  java -jar target/mrn-validation-0.1.0-SNAPSHOT-jar-with-dependencies.jar --mrn "urn:mrn:mcp:user:granpa:thomas" --regex "^urn:mrn:mcp:(device|org|user|vessel|service|mms):([a-z0-9]([a-z0-9]|-){0,20}[a-z0-9]):((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)((([-._a-z0-9]|~)|%[0-9a-f][0-9a-f]|([!$&'()*+,;=])|:|@)|/)*)$"
```
Expected result of the command above:
```
  [main] INFO net.maritimeconnectivity.mrn.Main - PASS: urn:mrn:mcp:user:granpa:thomas is valid.
```

- Checking MCP MRN compliance
```
  java -jar target/mrn-validation-0.1.0-SNAPSHOT-jar-with-dependencies.jar --mrn "urn:mrn:mcp:user:granpa:thomas" --mcp
```
Expected result of the command above:
```
  [main] INFO net.maritimeconnectivity.mrn.Main - PASS: urn:mrn:mcp:user:granpa:thomas is valid as an MCP MRN.
```

## Build
In the root folder of the repository,
```
  mvn clean install
```

## Prerequisite
The MRN validation of this implementation is based on the [regular expression (REGEX)](https://en.wikipedia.org/wiki/Regular_expression), which can be obtained from a MRN syntax through several steps.
In the example with [the official MRN syntax](https://www.iana.org/assignments/urn-formal/mrn),

      <MRN>   ::= "urn" ":" "mrn" ":" <OID> ":" <OSS>
                  [ rq-components ]
                  [ "#" f-component ]
      <OID>   ::= (alphanum) 0*20(alphanum / "-") (alphanum) ; Organization ID
      <OSS>   ::= <OSNID> ":" <OSNS> ; Organization-specific string
      <OSNID> ::= (alphanum) 0*32(alphanum / "-") (alphanum) ; Organization-specific namespace ID
      <OSNS>  ::= pchar *(pchar / "/") ; Organization-specific namespace string
                  
This could be converted to the pure [Augmented Backus–Naur form (ABNF)](https://en.wikipedia.org/wiki/Augmented_Backus%E2%80%93Naur_form) as below:

    mrn = "urn" ":" "mrn" ":" oid ":" oss [rq-components] [ "#" f-component ]
    oid = (alphanum) 0*20((alphanum) / "-") (alphanum) ; Organization ID
    oss = osnid ":" osns ; Organization-specific string
    osnid = (alphanum) 0*20((alphanum) / "-") (alphanum) ; Organization-specific namespace ID
    osns = pchar *(pchar / "/") ; Organization-specific namespace string
    rq-components = [ "?+" r-component ][ "?=" q-component ] ; rfc8141
    r-component = pchar *( pchar / "/" / "?" ) ; rfc8141
    q-component = pchar *( pchar / "/" / "?" ) ; rfc8141
    f-component   = fragment ; rfc8141
    fragment = *( pchar / "/" / "?" ) ; ; rfc3986
    alphanum = ALPHA / DIGIT ; rfc3986
    pchar = unreserved / pct-encoded / sub-delims / ":" / "@" ; rfc3986
    unreserved = ALPHA / DIGIT / "-" / "." / "_" / "~" ; rfc3986
    pct-encoded = "%" HEXDIG HEXDIG ; rfc3986
    sub-delims = "!" / "$" / "&" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "=" ; rfc3986

You can practice the generation of valid ABNF and corresponding REGEX by online tools, for example: https://abnf.msweet.org/.

The corresponding raw REGEX of the MRN syntax is:

    mrn: ^[Uu][Rr][Nn]\:[Mm][Rr][Nn]\:([A-Za-z0-9]([A-Za-z0-9]|\-){0,20}[A-Za-z0-9])\:([A-Za-z0-9][-A-Za-z0-9]{0,20}[A-Za-z0-9])\:((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/)*)((\?\+((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/|\?)*))?(\?\=((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/|\?)*))?)?(#(((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/|\?)*))?$

You can also practice with REGEX and the MRN complying with it by online tools, for example: https://regex101.com/.

## Motivation
MRN validation has been motivated for MRNs in [Maritime Connectivity Platform (MCP)](https://maritimeconnectivity.net/).
