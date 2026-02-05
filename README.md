# Crime Management System

A Java-based application for recording, managing, and tracking crime-related information. This system integrates with MySQL database to store officer and case records securely while maintaining referential integrity between tables.

## Features

- **Officer Management**
  - Add new officer records with validation
  - View all officers in formatted display
  - Update officer information
  - GUI and console interfaces available

- **Case Management**
  - Add case records with automatic officer linking
  - View cases with detailed information
  - Update case status and location
  - Automatic officer creation if needed
  - Foreign key relationships ensure data integrity

- **User Interfaces**
  - Console-based menu-driven interface
  - Java Swing GUI forms for data entry
  - Input validation and error handling
  - User-friendly dialogs and confirmations

## Technology Stack

- **Language:** Java 22
- **Database:** MySQL
- **JDBC Driver:** MySQL Connector/J 9.4.0
- **IDE:** NetBeans (recommended)
- **Build System:** Apache Ant

## Database Schema

The application uses two main tables:

### Officer Table
- `OfficerID` (INT, Primary Key, Auto Increment)
- `FirstName` (VARCHAR)
- `LastName` (VARCHAR)
- `Rank` (VARCHAR)
- `Phone` (VARCHAR)

### Case Table
- `CaseID` (INT, Primary Key, Auto Increment)
- `CaseDetails` (TEXT)
- `AttendingOfficer` (INT, Foreign Key → Officer.OfficerID)
- `Category` (VARCHAR)
- `Location` (VARCHAR)
- `ReportedOn` (DATE)
- `Status` (VARCHAR)

## Installation and Setup

### Prerequisites
- Java JDK 17 or higher
- MySQL Database (8.0 or higher recommended)
- MySQL Connector/J JDBC Driver (version 9.4.0 or higher)
- NetBeans IDE (optional, but recommended)

### MySQL JDBC Driver Setup

**IMPORTANT:** You must install the MySQL Connector JAR file before running the application.

1. **Download MySQL Connector/J:**
   - Visit: https://dev.mysql.com/downloads/connector/j/
   - Download version 9.4.0 or higher
   - Extract the downloaded ZIP file

2. **Copy to Project:**
   ```bash
   # Copy the JAR file to the lib folder
   cp mysql-connector-j-9.4.0.jar <project-path>/lib/
   ```

   Or manually:
   - Navigate to the extracted folder
   - Find `mysql-connector-j-9.4.0.jar`
   - Copy it to the `lib/` folder in your project root

3. **Verify Setup:**
   - The file should be at: `CrimeManagement/lib/mysql-connector-j-9.4.0.jar`
   - NetBeans will automatically add it to the classpath (already configured)

**Note:** If using a different version, update the filename in `nbproject/project.properties`:
```properties
file.reference.mysql-connector-j-9.4.0.jar=lib\\mysql-connector-j-X.X.X.jar
```

### Database Setup

1. Open MySQL Workbench or MySQL command line

2. Run the provided schema file:
   ```sql
   source schema.sql
   ```
   Or manually create the database and tables as specified in `schema.sql`

### Application Configuration

1. Copy the configuration template to the **project root directory**:
   ```bash
   cp config.properties.example config.properties
   ```

   **Important:** The `config.properties` file must be in the project root directory (same level as `src/` folder)

2. Edit `config.properties` with your database credentials:
   ```properties
   db.url=jdbc:mysql://localhost:3306/crime_management
   db.user=your_mysql_username
   db.password=your_mysql_password
   ```

3. **Security Note:** Never commit `config.properties` to version control (it's already in `.gitignore`)

**Note:** The application will automatically search for `config.properties` in multiple locations:
- Project root directory (recommended)
- Parent directories
- Classpath

If the file is not found, you'll see a clear error message with the searched locations.

### Running the Application

#### Option 1: NetBeans IDE
1. Open the project in NetBeans
2. Clean and Build the project
3. Run `CrimeManagement.java` for console interface
4. Or run `OfficerFormGUI.java` / `CaseFormGUI.java` for GUI interface

#### Option 2: Command Line
```bash
# Compile
javac -cp ".:mysql-connector-j-9.4.0.jar" src/crimemanagement/*.java

# Run console interface
java -cp ".:mysql-connector-j-9.4.0.jar:src" crimemanagement.CrimeManagement
```

## Usage

### Console Interface

When you run the application, you'll see a menu with options:

```
Crime Management System
1. Add Officer
2. View Officers
3. Update Officer
4. Add Case
5. View Cases
6. Update Case
7. Exit
```

Simply enter the number corresponding to your desired action and follow the prompts.

### GUI Interface

The GUI forms provide visual interfaces for:
- Adding new records via form fields
- Viewing records in table format
- Updating records by ID
- Deleting records with confirmation dialogs
- Officer selection via dropdown menus for cases

## Key Features

### Automatic Officer Linking
When adding a new case, you can enter an officer's name:
- If the officer exists, the system automatically links them
- If the officer doesn't exist, a new officer record is created
- Maintains referential integrity between tables

### Data Validation
- Required field validation
- Input trimming and formatting
- SQL injection prevention via PreparedStatements
- User-friendly error messages

### Database Connection Management
- Secure credential storage via configuration file
- Clear error messages for connection issues
- Proper resource handling

## Project Structure

```
CrimeManagement/
├── src/crimemanagement/
│   ├── CrimeManagement.java      # Console main application
│   ├── DBConnection.java          # Database connection manager
│   ├── Officer.java               # Officer entity model
│   ├── OfficerAccess.java         # Officer CRUD operations
│   ├── OfficerFormGUI.java        # Officer GUI form
│   ├── CaseRecords.java           # Case entity model
│   ├── CaseAccess.java            # Case CRUD operations
│   └── CaseFormGUI.java           # Case GUI form
├── lib/                           # External libraries
│   └── mysql-connector-j-9.4.0.jar  # MySQL JDBC driver (download required)
├── config.properties.example      # Configuration template
├── config.properties              # Your credentials (not committed)
├── schema.sql                     # Database schema
├── .gitignore                     # Git ignore rules
└── README.md                      # This file
```

## Development Notes

- **Referential Integrity:** Foreign key constraints ensure data consistency
- **PreparedStatements:** All SQL queries use PreparedStatements to prevent SQL injection
- **Exception Handling:** Comprehensive try-catch blocks with informative error messages
- **Layered Architecture:** Clear separation between presentation, business logic, and data access layers

## Security Considerations

- Database credentials are stored in `config.properties` (never committed to Git)
- All user inputs are validated and sanitized
- PreparedStatements prevent SQL injection attacks
- Connection errors are caught and handled gracefully

## Troubleshooting

### "No suitable driver found for jdbc:mysql" Error

This is the **most common error** and means the MySQL JDBC driver is not in the classpath.

**Solution:**
1. Download MySQL Connector/J from https://dev.mysql.com/downloads/connector/j/
2. Copy `mysql-connector-j-9.4.0.jar` to the `lib/` folder in your project root
3. Verify the file exists: `CrimeManagement/lib/mysql-connector-j-9.4.0.jar`
4. In NetBeans: Clean and Build the project (right-click project → Clean and Build)
5. Run the application again

The error message will show:
```
ERROR: MySQL JDBC driver not found!
Please ensure mysql-connector-j-9.4.0.jar is in the lib/ folder
```

### "Could not find config.properties" Error

If you see this error, the application cannot locate your configuration file:

1. Verify `config.properties` exists in the project root directory
2. Check the error message to see which locations were searched
3. When running from NetBeans, ensure the working directory is set to the project root

### Database Connection Fails

If you see "Connection failed" errors:

1. Verify MySQL server is running:
   ```bash
   # Windows
   net start MySQL80

   # Or check MySQL Workbench
   ```

2. Check your credentials in `config.properties`
3. Verify the database name matches: `crime_management`
4. Test connection with MySQL Workbench using the same credentials

### Running from Command Line

If running from command line, make sure you're in the project root directory:
```bash
cd C:\Users\mmath\OneDrive\Desktop\HSYD201\CrimeManagement
java -cp "build;lib/*" crimemanagement.CrimeManagement
```

### MySQL Connector Not Found

If you get "ClassNotFoundException" for MySQL driver:
1. Verify MySQL Connector/J JAR is in the `lib/` folder
2. In NetBeans: Right-click project → Properties → Libraries → Add JAR/Folder

## Contributing

This project was created as an educational exercise. If you'd like to contribute improvements:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is available for educational purposes.

## Author

Created by mmath

## Acknowledgments

- Built as a learning project for database connectivity and CRUD operations
- Demonstrates practical application of Java JDBC and MySQL integration
