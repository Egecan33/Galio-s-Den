import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LearningAI {

    private Random random;
    private Map<String, Integer> choiceFrequencies;
    private String filename;

    public LearningAI(String filename) {
        random = new Random();
        choiceFrequencies = new HashMap<String, Integer>();
        choiceFrequencies.put("1", 0);
        choiceFrequencies.put("2", 0);
        choiceFrequencies.put("3", 0);
        this.filename = filename;
        loadFromFile();
    }

    public int makeChoice() {
        // Choose a random option with a probability based on the frequency of past
        // choices
        int totalFrequencies = choiceFrequencies.get("1") + choiceFrequencies.get("2") + choiceFrequencies.get("3");
        int randomNumber = random.nextInt(totalFrequencies) + 1;
        int choice = 0;

        if (randomNumber <= choiceFrequencies.get("1")) {
            choice = 1;
        } else if (randomNumber <= choiceFrequencies.get("1") + choiceFrequencies.get("2")) {
            choice = 2;
        } else {
            choice = 3;
        }

        System.out.println("AI chooses option " + choice);

        return choice;
    }

    public void learnFromChoice(int choice) {
        // Increment the frequency of the chosen option
        String choiceString = Integer.toString(choice);
        int frequency = choiceFrequencies.get(choiceString);
        choiceFrequencies.put(choiceString, frequency + 1);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String choice : choiceFrequencies.keySet()) {
                int frequency = choiceFrequencies.get(choice);
                writer.write(choice + "," + frequency);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving AI data to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String choice = parts[0];
                int frequency = Integer.parseInt(parts[1]);
                choiceFrequencies.put(choice, frequency);
            }
        } catch (IOException e) {
            System.out.println("Error loading AI data from file: " + e.getMessage());
        }
    }
}
