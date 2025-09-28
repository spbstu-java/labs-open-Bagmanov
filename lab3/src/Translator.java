import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Translator {

    private static final String WORD_SPLIT_REGEX = "(?:(?<![a-zA-Z])'|'(?![a-zA-Z])|[^a-zA-Z'])+";

    private final Map<String, String> dictionary = new HashMap<>();

    public void loadDictionaryFromFile(String filePath) throws InvalidFileFormatException, FileReadException {
        try {
            for (final var line : Files.readAllLines(Paths.get(filePath))) {
                final var parts = line.split("\\|");
                if (parts.length != 2)
                    throw new InvalidFileFormatException(
                            "Invalid format in dictionary file: " + line + ". Expected 'word | translation'");

                dictionary.put(parts[0].trim().toLowerCase(), parts[1].trim());
            }
        } catch (IOException e) {
            throw new FileReadException("Unable to read the file: " + e.getMessage());
        }
    }

    private String findLongestMatchingPhrase(String[] words, int start) {
        final var phraseBuilder = new StringBuilder();
        String longestMatchingPhrase = null;

        for (var i = start; i < words.length; i++) {
            phraseBuilder.append(words[i].toLowerCase()).append(" ");
            final var currentPhrase = phraseBuilder.toString().trim();
            if (dictionary.containsKey(currentPhrase))
                longestMatchingPhrase = currentPhrase;
            else
                break;
        }

        return longestMatchingPhrase;
    }

    public String translate(String inputText) {
        final var words = inputText.split(WORD_SPLIT_REGEX);
        final var translationResult = new StringBuilder();

        int i = 0;
        while (i < words.length) {
            final var longestMatchingPhrase = findLongestMatchingPhrase(words, i);
            if (longestMatchingPhrase != null) {
                translationResult.append(dictionary.get(longestMatchingPhrase.toLowerCase())).append(" ");
                i += longestMatchingPhrase.split(" ").length;
            } else {
                translationResult.append(words[i]).append(" ");
                i++;
            }
        }

        return translationResult.toString().trim();
    }

    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        try (scanner) {
            System.out.print("Enter the path to the dictionary file: ");
            final var dictionaryPath = scanner.nextLine();
            final var translator = new Translator();
            translator.loadDictionaryFromFile(dictionaryPath);
            System.out.println("Dictionary loaded successfully!");

            while (true) {
                System.out.print("Enter text to translate (or type 'exit' to quit): ");
                final var inputText = scanner.nextLine();

                if (inputText.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                final var translation = translator.translate(inputText);
                System.out.println("Translated text: " + translation);
            }
        } catch (InvalidFileFormatException | FileReadException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}