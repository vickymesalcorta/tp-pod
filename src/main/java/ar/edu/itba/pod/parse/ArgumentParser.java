package ar.edu.itba.pod.parse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArgumentParser {
    private static final String TO_IGNORE = "\\s";
    private static final Pattern EQUALS_PATTERN = Pattern.compile("=");

    private final Map<String, Boolean> optionalityByArgumentName;
    private final Map<String, String> valuesByArgumentName = new HashMap<>();

    private ArgumentParser(Map<String, Boolean> optionalityByArgumentName) {
        this.optionalityByArgumentName = optionalityByArgumentName;
    }

    public int getIntArgument(String name) {
        String value = getStringArgument(name);
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException exc) {
            throw new IllegalArgumentException(
                    "Invalid format, argument is not a number: " + name);
        }
    }

    public String getStringArgument(String name) {
        String value = valuesByArgumentName.get(name);
        if (value == null) {
            throw new IllegalArgumentException("Missing argument: " + name);
        }
        return valuesByArgumentName.get(name);
    }

    private void parse(String... args) {
        if (!valuesByArgumentName.isEmpty()) {
            return;
        }
        Set<String> mandatoryArguments = optionalityByArgumentName.entrySet()
                .stream().filter(data -> !data.getValue())
                .map(data -> data.getKey()).collect(Collectors.toSet());

        Arrays.stream(args).map(ArgumentParser::parseArgument).forEach(arg -> {
            Boolean optionality = optionalityByArgumentName.get(arg.name);
            if (optionality == null) {
                throw new IllegalArgumentException(
                        "Unexpected argument: " + arg.name);
            }
            mandatoryArguments.remove(arg.name);
            valuesByArgumentName.put(arg.name, arg.value);
        });

        if (!mandatoryArguments.isEmpty()) {
            throw new IllegalArgumentException(
                    "Missing arguments: " + mandatoryArguments.stream()
                            .collect(Collectors.joining(", ")));
        }
    }

    public static ArgumentParserBuilder builder() {
        return new ArgumentParserBuilder();
    }

    private static ParsedArgument parseArgument(String rawArgAndValue) {
        String[] argAndValueArray = EQUALS_PATTERN
                .split(rawArgAndValue.replace(TO_IGNORE, ""));
        return new ParsedArgument(argAndValueArray[0], argAndValueArray[1]);
    }

    public static class ArgumentParserBuilder {
        private final Map<String, Boolean> optionalityByArgumentName = new HashMap<>();

        public ArgumentParserBuilder withMandatoryArgument(String name) {
            return withArgument(name, false);
        }

        public ArgumentParserBuilder withOptionalArgument(String name) {
            return withArgument(name, true);
        }

        public ArgumentParser parse(String... args) {
            ArgumentParser argumentParser = new ArgumentParser(
                    optionalityByArgumentName);
            argumentParser.parse(args);
            return argumentParser;
        }

        private ArgumentParserBuilder withArgument(String name,
                boolean optional) {
            optionalityByArgumentName.put(name, optional);
            return this;
        }
    }

    private static class ParsedArgument {
        final String name;
        final String value;

        ParsedArgument(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
