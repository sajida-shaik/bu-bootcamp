package Module2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GradeAnalyzer {

    public static void main(String[] args) {
        // Step 1: read scores from file
        String filename = "Module2/scores.txt";
        String outputFile = "Module2/report.txt";

        ArrayList<Integer> readScores = readScores(filename);

        if(readScores == null || readScores.size() == 0) {
            System.out.println("No scores are available in the file");
            System.exit(0);
        }
        int highestScore = Integer.MIN_VALUE;
        int lowestScore = Integer.MAX_VALUE;

        int countA;
        int countB;
        int countC;
        int countD;
        int countF;
        countA = countB = countC = countD = countF = 0;
    
        for(int score : readScores) {
            highestScore = (highestScore < score) ? score : highestScore;    
            lowestScore = (lowestScore > score) ? score : lowestScore;

            if (score >= 90) {
                countA ++;
            } else if (score >= 80 && score < 90) {
                countB ++;
            } else if (score >= 70 && score < 80) {
                countC ++;
            } else if (score >= 60 && score < 70) {
                countD ++;
            } else if (score <= 60) {
                countF ++;
            }
        }
        double averageScore = calculateAverage(readScores);
        writeReport(readScores, averageScore, highestScore, lowestScore, outputFile,
            countA, countB, countC, countD, countF);
        
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        
        ArrayList<Integer> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int number;

            while((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    System.out.println("Skipping an empty line");
                    continue;
                }

                try {
                    number = Integer.parseInt(line.trim());
                    scores.add(number);

                } catch(NumberFormatException e) {
                    System.out.println("Skipping a non integer value : " + line);
                }
            }
        } catch(IOException e) {
            System.out.println("Could not read file : " + e.getMessage());
        }

        return scores;
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        
        int totalScore = 0;

        if (scores.isEmpty()) return 0.0;
        
        for(int n : scores) {
            totalScore += n;
        }

        return totalScore/(scores.size());
    } 
 
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile,
                                int countA, int countB, int countC,
                            int countD, int countF) {
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("=== Grade Analysis Report ===");
            writer.newLine();
            System.out.println("\n\n=== Grade Analysis Report ===");
            writer.write(String.format("Average score:  %.2f%n", avg));
            System.out.println(String.format("Average score:  %.2f%n", avg));
            writer.write(String.format("Highest score:  %d%n", high));
            System.out.println(String.format("Highest score:  %d%n", high));
            writer.write(String.format("Lowest score:  %d%n", low));
            System.out.println(String.format("Lowest score:  %d%n", low));
            writer.write("Grade distribution:");
            writer.newLine();
            System.out.println("Grade distribution:");
            writer.write(String.format("  A (90-100):   %d%n", countA));
            System.out.println(String.format("  A (90-100):   %d", countA));
            writer.write(String.format("  B (80-89):    %d%n", countB));
            System.out.println(String.format("  B (80-89):    %d", countB));
            writer.write(String.format("  C (70-79):    %d%n", countC));
            System.out.println(String.format("  C (70-79):    %d", countC));
            writer.write(String.format("  D (60-69):    %d%n", countD));
            System.out.println(String.format("  D (60-69):    %d", countD));
            writer.write(String.format("  F (below 60):  %d%n", countF));
            System.out.println(String.format("  F (below 60):  %d", countF));

        } catch(IOException e) {
            System.out.println("Could not write file: " + e.getMessage());
        }
    }
}
