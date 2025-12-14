# Unicode-Compatible Username Matching

## Overview

This program implements a Unicode-compatible username authentication system that handles diacritics (accent marks) gracefully. It solves the common problem where users with international names containing special characters (like Renée or José) may struggle to type their usernames on US-layout keyboards without diacritics.

## Problem Statement

European users often need to use US-layout keyboards that lack diacritic marks. For example:

- A user named **Renée** might type **Renee**
- A user named **José** might type **Jose**
- A user named **Søren** might type **Soren**

The system should intelligently match these inputs to the correct registered usernames.

## Features

- ✅ Reads usernames from a file (`names.txt`)
- ✅ Stores names in an ArrayList data structure
- ✅ Implements custom linear search algorithm (no built-in `contains()` method)
- ✅ Uses NFD normalization to decompose Unicode characters
- ✅ Employs Java's Collator class for locale-sensitive string comparison
- ✅ Handles special Nordic characters (ø, Ø) that aren't standard diacritics
- ✅ Case-insensitive matching

## How It Works

1. **NFD Normalization**: Decomposes accented characters into base letters + combining marks
   - Example: `é` → `e` + combining acute accent

2. **Combining Mark Removal**: Strips the diacritical marks using regex pattern `\p{M}`

3. **Special Character Handling**: Replaces Nordic letters like ø → o

4. **Collator Comparison**: Uses PRIMARY strength for final comparison, ignoring case and remaining diacritics

## Compilation & Usage

### Compile

```bash
javac -d bin src/assignment3/Main.java
```

### Run

```bash
java -cp bin assignment3.Main
```

### Example Session

```
Enter username: Renee
Matched with Renée
```

```
Enter username: Joe
Username not found
```

```
Enter username: Soren
Matched with Søren
```

## Test Cases

| Input      | Expected Output        | Status |
|------------|------------------------|--------|
| `Renee`    | Matched with Renée     | ✅     |
| `Jose`     | Matched with José      | ✅     |
| `Hakon`    | Matched with Håkon     | ✅     |
| `Soren`    | Matched with Søren     | ✅     |
| `Francois` | Matched with François  | ✅     |
| `Joe`      | Username not found     | ✅     |

## File Structure

```
Assignment3/
├── src/
│   └── assignment3/
│       └── Main.java          # Main program
├── bin/
│   └── assignment3/
│       └── Main.class         # Compiled bytecode
├── names.txt                  # Sample usernames with diacritics
└── README.md                  # This file
```

## Sample Data (names.txt)

```
José
Håkon
Renée
Zoé
Søren
François
Björn
María
```

## Technical Implementation

### Required Imports

```java
import java.text.Collator;      // For locale-sensitive string comparison
import java.text.Normalizer;    // For NFD Unicode normalization
import java.util.Locale;        // For locale settings
```

### Key Methods

- **`linearSearch()`**: Implements manual linear search with diacritic-insensitive matching
- **`normalizeString()`**: Applies NFD normalization and removes diacritics

### CLO Alignment

- **CLO A2**: Proper implementation of linear search algorithm ✓
- **CLO B1**: Appropriate use of Java classes (Collator, Normalizer) for String operations ✓
- **CLO B3**: Clean code design with proper comments and conventions ✓

## Assignment Requirements Met

✅ Downloads and reads usernames from file  
✅ Stores names in ArrayList  
✅ Gets user input  
✅ Implements linear search manually (no `contains()`)  
✅ Uses NFD normalization  
✅ Compares normalized versions  
✅ Proper exception handling  
✅ Clean code with appropriate comments  
✅ Follows Java naming conventions  

## Notes

- The program uses PRIMARY strength in Collator, which ignores diacritics and case differences
- Special handling is included for ø (Norwegian/Danish letter) which is not a standard diacritic
- All file operations use proper try-catch-finally blocks for resource management

## License

Educational project for COSC 125 course.
