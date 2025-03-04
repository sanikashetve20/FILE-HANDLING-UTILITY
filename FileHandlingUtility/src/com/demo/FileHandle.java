// FileHandle.java
// A Java program to read, write, and modify text files with clear documentation.
package com.demo;
import java.io.*;
import java.util.Scanner;

/**
 * FileHandle
 * This program demonstrates basic file operations: reading, writing, and modifying text files.
 * It restricts operations to text files and ensures they are created in the D drive.
 */
public class FileHandle {

    // Method to write data to a text file in the D drive
    public static void writeFile(String fileName, String content) {
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        String filePath = "D:" + File.separator + fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Data written to file successfully at location: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // Method to read data from a text file in the D drive
    public static void readFile(String fileName) {
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        String filePath = "D:" + File.separator + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Reading file at location: " + filePath);
            System.out.println("File Contents:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    // Method to modify data in a text file in the D drive
    public static void modifyFile(String fileName, String oldContent, String newContent) {
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        File file = new File("D:" + File.separator + fileName);
        String filePath = file.getAbsolutePath();
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line.replace(oldContent, newContent)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(contentBuilder.toString());
            System.out.println("File modified successfully at location: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("File Handling Utility");
            System.out.println("1. Write to a file");
            System.out.println("2. Read from a file");
            System.out.println("3. Modify a file");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter file name (without extension): ");
                    String writeFileName = scanner.nextLine();
                    System.out.print("Enter content to write: ");
                    String content = scanner.nextLine();
                    writeFile(writeFileName, content);
                    break;
                case 2:
                    System.out.print("Enter file name (without extension): ");
                    String readFileName = scanner.nextLine();
                    readFile(readFileName);
                    break;
                case 3:
                    System.out.print("Enter file name (without extension): ");
                    String modifyFileName = scanner.nextLine();
                    System.out.print("Enter content to replace: ");
                    String oldContent = scanner.nextLine();
                    System.out.print("Enter new content: ");
                    String newContent = scanner.nextLine();
                    modifyFile(modifyFileName, oldContent, newContent);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Move to the next line for better formatting
        }
    }
}
