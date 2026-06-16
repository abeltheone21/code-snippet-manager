# Code Snippet Manager

A modern desktop application for storing, organizing, searching, and managing code snippets efficiently.

![Java](https://img.shields.io/badge/Java-11+-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-12+-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Version](https://img.shields.io/badge/Version-1.0-success)

## Overview

Code Snippet Manager is a professional desktop application built for developers who want a centralized place to store and organize reusable code snippets.

Instead of keeping snippets scattered across notes, files, browser bookmarks, or chat applications, Code Snippet Manager provides an IDE-like experience with syntax highlighting, powerful search capabilities, favorites management, and a modern dark-themed interface.

---

## Features

### Snippet Management

* Create, Read, Update, and Delete (CRUD) snippets
* Store code, descriptions, and language information
* Persistent storage using PostgreSQL

### Organization

* Favorite important snippets
* View recently added snippets
* Categorize snippets by programming language

### Search & Filtering

* Search by title
* Search by programming language
* Search by description
* Instant filtering of stored snippets

### Code Editor

* Syntax highlighting
* Line numbering
* Code folding support
* IDE-style editing experience

### Supported Languages

* Java
* Python
* JavaScript
* C++
* C#
* HTML/CSS
* SQL
* all 

### User Interface

* Modern dark theme
* Responsive layout
* Clean card-based design
* Developer-friendly workflow

---

## Technology Stack

| Technology      | Purpose               |
| --------------- | --------------------- |
| Java 11+        | Core application      |
| Java Swing      | GUI framework         |
| FlatLaf         | Modern UI theme       |
| RSyntaxTextArea | Syntax highlighting   |
| PostgreSQL      | Database              |
| JDBC            | Database connectivity |
| Git             | Version control       |

---

## System Requirements

| Component        | Requirement                     |
| ---------------- | ------------------------------- |
| Operating System | Windows 10/11, macOS 11+, Linux |
| Java Runtime     | Java 11+ (Java 21 recommended)  |
| Database         | PostgreSQL 12+                  |
| RAM              | 256 MB minimum                  |
| Storage          | 100 MB                          |
| Display          | 1024×768 or higher              |

---

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/abeltheone21/code-snippet-manager.git
cd code-snippet-manager
```

### 2. Create the Database

Connect to PostgreSQL and run:

```sql
CREATE DATABASE snippet_management;

\c snippet_management;

CREATE TABLE snippets (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    language VARCHAR(50) NOT NULL,
    code TEXT,
    description TEXT,
    favorite INTEGER DEFAULT 0
);
```

### 3. Configure Database Connection

Open:

```java
src/dataBaseConnection/connectionEstablish.java
```

Update the database credentials:

```java
String url = "jdbc:postgresql://localhost:5432/snippet_management";
String username = "postgres";
String password = "YOUR_PASSWORD";
```

### 4. Run the Application

#### Windows

```bash
START_SNIPATE.bat
```

#### macOS / Linux

```bash
java -jar out/artifacts/codesnippetmanager_jar/codesnippetmanager.jar
```

---

## Usage

### Create a Snippet

1. Click **New Snippet**
2. Enter a title
3. Select a programming language
4. Add an optional description
5. Write or paste your code
6. Click **Save**

### Edit a Snippet

1. Select a snippet card
2. Click **Edit Snippet**
3. Modify the desired fields
4. Save changes

### Delete a Snippet

1. Locate the snippet card
2. Click the trash icon
3. Confirm deletion

### Manage Favorites

* Mark snippets as favorites
* Quickly access them through the Favorites section
* Remove favorites with a single click

### Search Snippets

Search by:

* Title
* Language
* Description

---

## Database Schema

```sql
CREATE TABLE snippets (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    language VARCHAR(50) NOT NULL,
    code TEXT,
    description TEXT,
    favorite INTEGER DEFAULT 0
);
```

---

## Project Structure

```text
code-snippet-manager/
│
├── src/
│   ├── ui/
│   │   ├── mainframe.java
│   │   ├── detailpanel.java
│   │   ├── codepanel.java
│   │   └── snippetcard.java
│   │
│   ├── model/
│   │   └── Snippet.java
│   │
│   ├── databaseOperations/
│   │   └── dataQR.java
│   │
│   ├── dataBaseConnection/
│   │   └── connectionEstablish.java
│   │
│   └── resources/
│       └── app-icon.png
│
├── lib/
├── out/
├── README.md
├── LICENSE
└── START_SNIPATE.bat
```

---

## Troubleshooting

### Application Does Not Start

```bash
java -version
```

Verify that Java is installed and available in your system path.

### Database Connection Errors

Ensure PostgreSQL is running and that:

* The database exists
* The username is correct
* The password is correct
* PostgreSQL accepts local connections

### JAR File Does Not Execute

Run manually:

```bash
java -jar codesnippetmanager.jar
```

---

## Future Improvements

* User authentication
* Tagging system
* Import/Export snippets
* Cloud synchronization
* Multiple themes
* Snippet sharing
* AI-powered code search

---

## Contributing

Contributions are welcome.

1. Fork the repository
2. Create a feature branch

```bash
git checkout -b feature/amazing-feature
```

3. Commit your changes

```bash
git commit -m "Add amazing feature"
```

4. Push to GitHub

```bash
git push origin feature/amazing-feature
---

---

## Author

**Abel gebrehiwot**

GitHub: https://github.com/abeltheone21

Repository: https://github.com/abeltheone21/code-snippet-manager

---

## Support

If you encounter a bug or have a feature request, please open an issue in the GitHub repository.

---

## Show Your Support

If you found this project useful, consider giving it a ⭐ on GitHub.
