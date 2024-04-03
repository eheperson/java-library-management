# Library Management System in JAVA
- This Java project simulates a library management system, enabling the tracking of library books, members, and book issues. 
- It demonstrates CRUD operations through a simple console application and uses in-memory data storage for simplicity.

> This project was created for the *IYTE SEDS504 – Software Construction* lecture during the *Spring 2024* semester.

## Build & Run

```bash
cd librarymanagement # Adjust this to your project's root directory
mvn clean compile
mvn clean package
java -jar target/librarymanagement-1.0-SNAPSHOT.jar
```

## Project Structure

The project is structured as follows:

```
├── librarymanagement
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/localhost/librarymanagement/
│   │   │   │   ├── main/
│   │   │   │   │   └── Main.java
│   │   │   │   ├── management/
│   │   │   │   │   └── Management.java
│   │   │   │   ├── models/
│   │   │   │   │   ├── Book.java
│   │   │   │   │   ├── Issue.java
│   │   │   │   │   ├── Library.java
│   │   │   │   │   └── Member.java
│   │   │   │   ├── query/
│   │   │   │   │   └── LibraryQuery.java
│   │   │   │   └── utils/
│   │   │   │       └── FileIO.java
│   │   │   └── resources/
│   │   │       ├── L1_Books.csv
│   │   │       ├── L1_Issues.csv
│   │   │       ├── Members.csv
│   │   │       └── ...
│   │   └── test  
│   └── pom.xml
├── .gitignore
├── SEDS504_Spring2024_HW1_DUE_4_APRIL.pdf
└── readme.md
```

## Prerequisites

- Java JDK 11 or later
- Maven (Optional - for command-line execution)
- Eclipse IDE (Optional - for running within Eclipse)

## Preparation

- The application uses CSV files located in `src/main/resources` to load initial data for books, members, and their issues within the library. 
- Ensure these files are present and correctly formatted before running the application.

## Troubleshooting

### CSV File Issues:
- Ensure CSV files are present in `src/main/resources` and properly formatted according to the application's expectations.

---

## Maven Commands:

### Project initialized with this command

```bash
mvn archetype:generate -DgroupId=com.localhost.librarymanagement -DartifactId=librarymanagement -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
