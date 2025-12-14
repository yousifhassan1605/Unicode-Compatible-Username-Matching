package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.Collator;
import java.text.Normalizer;
import java.util.Locale;

// COSC 125 - Assignment 3 by Youssef Hassan Mahfoudhi (F2400002)
public class Main {
	
	// Main method to run the username matching program
	public static void main(String[] args) {
		// ArrayList to store usernames from file
		List<String> names = new ArrayList<>();
		
		// Read usernames from file
		File file = new File("names.txt");
		Scanner fileScanner = null;
		
		try {
			fileScanner = new Scanner(file);
			
			// Read each line and add to list
			while (fileScanner.hasNextLine()) {
				String name = fileScanner.nextLine().trim();
				if (!name.isEmpty()) {
					names.add(name);
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: names.txt file not found!");
			e.printStackTrace();
			return;
		} finally {
			// Close scanner to free resources
			if (fileScanner != null) {
				fileScanner.close();
			}
		}
		
		// Get username from user
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("Enter username: ");
		String userInput = inputScanner.nextLine().trim();
		
		// Perform linear search
		String matchedName = linearSearch(names, userInput);
		
		// Display result
		if (matchedName != null) {
			System.out.println("Matched with " + matchedName);
		} else {
			System.out.println("Username not found");
		}
		
		inputScanner.close();
	}
	
	// Linear search using NFD normalization and Collator for diacritic-insensitive matching
	public static String linearSearch(List<String> namesList, String searchTerm) {
		// Collator with primary strength ignores diacritics and case
		Collator collator = Collator.getInstance(Locale.getDefault());
		collator.setStrength(Collator.PRIMARY);
		
		// Normalize search term to NFD and replace special characters
		String normalizedSearchTerm = normalizeString(searchTerm);
		
		// Linear search through the list
		for (int i = 0; i < namesList.size(); i++) {
			String currentName = namesList.get(i);
			
			// Normalize current name to NFD and replace special characters
			String normalizedCurrentName = normalizeString(currentName);
			
			// Compare using Collator (returns 0 if equal)
			if (collator.compare(normalizedSearchTerm, normalizedCurrentName) == 0) {
				return currentName;
			}
		}
		
		return null; // No match found
	}
	
	// Normalize string using NFD and replace special Nordic characters
	private static String normalizeString(String text) {
		// First apply NFD normalization for standard diacritics
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		// Remove combining marks
		normalized = normalized.replaceAll("\\p{M}", "");
		// Replace ø which isn't handled by NFD
		normalized = normalized.replace("ø", "o").replace("Ø", "O");
		return normalized;
	}
}