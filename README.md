# Jira Comments Exporter

Extract comments for specified Jira tickets using the Jira API and store the results in a CSV file. Authenticate your session using the `JSESSIONID` cookie for SSO-based Jira instances.

---

## Features
- Fetch comments for Jira tickets.
- Write ticket comments to a CSV file.
- Include the ticket ID as the first column in the CSV file.
- Automatically handles CSV file creation and appending data.

---

## Prerequisites

1. **Java**:
    - Ensure you have Java 11 or higher installed.

2. **Maven**:
    - Make sure Maven is installed for building the project.

3. **Jira SSO Cookie**:
    - Log in to Jira via SSO and retrieve the `JSESSIONID` cookie value from your browser.

---

## Setup

### Clone the Repository
```bash
git clone <repository-url>
cd jira-comments-exporter
```

### Configure Application Properties
1. Open `src/main/resources/application.properties`.
2. Set the required properties:

```properties
jira.jsessionid=your-session-id
jira.api.url=https://jira.marksandspencer.app/rest/api/latest/issue
```

Replace `your-session-id` with your `JSESSIONID` cookie value and adjust the Jira API URL as needed.

---

## Building the Project

Use Maven to build the project:
```bash
mvn clean install
```

---

## Running the Application

Run the application with:
```bash
mvn spring-boot:run
```

The application will:
1. Fetch comments for the specified ticket IDs.
2. Write the comments to `comments.csv` in the project directory.

---

## File Details

### `comments.csv`
- The CSV file contains the following columns:
    - `Ticket ID`
    - `Comment ID`
    - `Author Name`
    - `Author Email`
    - `Comment`
    - `Created Date`
    - `Updated Date`

---

## Code Overview

### Key Classes

1. **`JiraService`**:
    - Fetches comments from the Jira API.
    - Handles API requests and parses JSON responses.

2. **`CsvWriter`**:
    - Writes the fetched comments to a CSV file.
    - Appends data to the file and adds a header if the file is new.

3. **`ApplicationRunner`**:
    - Executes the main logic: iterates over ticket IDs, fetches comments, and writes them to the CSV file.

---
## License
This project is licensed under the MIT License. See `LICENSE` for details.

---

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---
