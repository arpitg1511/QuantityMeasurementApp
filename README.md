# Quantity Measurement App

A comprehensive Java application for measuring and converting quantities across different units and measurement categories.

## Project Overview

This project implements a flexible quantity measurement system that supports:
- **Length Measurements** (Feet, Inch, Yard, Cm)
- **Weight Measurements** (Kg, Gram, Tonne)
- **Volume Measurements** (Litre, Millilitre, Gallon)
- **Temperature Measurements** (Celsius, Fahrenheit)

The application provides functionality for:
- ✅ Equality comparison between different units
- ✅ Unit conversions
- ✅ Arithmetic operations (Addition, Subtraction, Division)
- ✅ Generic quantity class with multi-category support
- ✅ N-Tier architecture implementation
- ✅ Database integration with JDBC
- ✅ REST API with Spring Boot
- ✅ Spring Security with OAuth 2.0 authentication
---

## Repository Structure & Branches

### Main Branches

#### 🔴 **main**
- **Purpose**: Production-ready stable release
- **Description**: The main branch contains the final, tested, and production-ready version of the application
- **Status**: Latest stable release

#### 🔵 **dev**
- **Purpose**: Development integration branch
- **Description**: Integration branch where feature branches are merged after testing
- **Usage**: Base branch for creating new feature branches

---

### Feature Branches (Use Cases)

#### 📋 **feature/UC1-FeetEquality**
- **Purpose**: Implement equality check for Feet measurements
- **Features**:
  - Compare two Feet quantities
  - Verify equality regardless of measurement method
  - Foundation for length measurements

#### 📋 **feature/UC2-InchEquality**
- **Purpose**: Implement equality check for Inch measurements
- **Features**:
  - Compare two Inch quantities
  - Extend equality functionality to Inch units
  - Support for different inch representation methods

#### 📋 **feature/UC3-GenericLength**
- **Purpose**: Create generic length measurement class
- **Features**:
  - Support multiple length units (Feet, Inch, Yard, Cm)
  - Generic equality comparison for all length units
  - Foundation for polymorphic measurements

#### 📋 **feature/UC4-UnitSupport**
- **Purpose**: Extend unit support across different measurement types
- **Features**:
  - Add enum for unit definitions
  - Support for multiple unit types
  - Standardized unit representation

#### 📋 **feature/UC5-UnitConversion**
- **Purpose**: Implement unit conversion functionality
- **Features**:
  - Convert between different units (Feet ↔ Inch, etc.)
  - Accurate conversion ratios
  - Support for all length units

#### 📋 **feature/UC6-UnitAddition**
- **Purpose**: Implement addition operation for quantities
- **Features**:
  - Add quantities of the same type
  - Automatic unit conversion in operations
  - Return result in specified unit

#### 📋 **feature/UC7-TargetUnitAddition**
- **Purpose**: Support target unit specification in addition
- **Features**:
  - Specify result unit for addition operations
  - Flexible result unit selection
  - Enhanced arithmetic operations

#### 📋 **feature/UC8-RefactoringUnitEnumToStandalone**
- **Purpose**: Refactor unit handling to standalone implementation
- **Features**:
  - Extract unit enum to separate class
  - Improve code organization
  - Better separation of concerns

#### 📋 **feature/UC9-WeightMeasurement**
- **Purpose**: Implement weight measurement system
- **Features**:
  - Support for Kilogram, Gram, Tonne units
  - Weight equality comparison
  - Weight unit conversion
  - Parallel implementation to length measurements

#### 📋 **feature/UC10-GenericQuantityClassWithUnitInterfaceForMultiCategorySupport**
- **Purpose**: Create generic Quantity class with Unit interface
- **Features**:
  - Generic `Quantity<T>` class supporting any measurement type
  - `Unit` interface for extensibility
  - Support for multiple measurement categories
  - Reduced code duplication across measurement types

#### 📋 **feature/UC11-VolumeMeasurementEqualityConversionAddition**
- **Purpose**: Implement volume measurement system
- **Features**:
  - Support for Litre, Millilitre, Gallon units
  - Volume equality comparison
  - Volume unit conversion
  - Volume addition operations
  - Complete volume measurement system

#### 📋 **feature/UC12-SubtarctDivideOperationQuantityMeasurement**
- **Purpose**: Implement subtraction and division operations
- **Features**:
  - Subtract quantities of the same type
  - Divide quantities with unit conversion
  - Support for all measurement categories
  - Extended arithmetic capabilities

#### 📋 **feature/UC13-EnforceDRY**
- **Purpose**: Refactor to enforce DRY (Don't Repeat Yourself) principle
- **Features**:
  - Remove code duplication
  - Implement shared utility methods
  - Consolidate common logic
  - Improve maintainability

#### 📋 **feature/UC14-temperatureMeasurement**
- **Purpose**: Implement temperature measurement system
- **Features**:
  - Support for Celsius and Fahrenheit
  - Temperature equality comparison
  - Temperature conversion with proper formulas
  - Handle non-linear temperature conversions

#### 📋 **feature/UC15-N-Tier-Architecture-Refactoring**
- **Purpose**: Refactor application to N-Tier architecture
- **Features**:
  - **Presentation Layer**: User interface components
  - **Business Logic Layer**: Core measurement logic
  - **Data Access Layer**: Data persistence operations
  - **Database Layer**: Actual data storage
  - Improved separation of concerns
  - Better testability and maintainability

#### 📋 **feature/UC16-Database-Integration-with-JDBC**
- **Purpose**: Integrate database with JDBC
- **Features**:
  - JDBC connection management
  - Store measurement data in database
  - Retrieve historical measurement data
  - Support for database operations (CRUD)
  - Connection pooling and optimization

#### 📋 **feature/UC17-SpringBootBackend**
- **Purpose**: Migrate backend to Spring Boot framework
- **Features**:
  - REST API endpoints for measurements
  - Spring Data JPA for data access
  - Dependency injection with Spring
  - Spring Boot auto-configuration
  - API documentation
  - Enhanced scalability and maintainability

#### 📋 **feature/UC18-Spring-Security-With-OAuth**
- **Purpose**: Implement Spring Security with OAuth 2.0 authentication
- **Features**:
  - OAuth 2.0 authentication provider integration
  - Spring Security configuration and setup
  - User authentication and authorization
  - JWT token-based authentication
  - Role-based access control (RBAC)
  - Secure API endpoints protection
  - Third-party OAuth provider support (Google, GitHub, etc.)
  - Session management and token refresh
  - User credentials encryption and storage
  
---

## Technology Stack

- **Language**: Java
- **Build Tool**: Maven/Gradle
- **Database**: JDBC compatible (MySQL/PostgreSQL)
- **Framework**: Spring Boot (UC17+)
- **Testing**: JUnit
- **Security**: Spring Security with OAuth 2.0


---

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven 3.6.0 or higher
- H2 (development), MySQL/PostgreSQL (production)

### Installation

```bash
git clone https://github.com/arpitg1511/QuantityMeasurementApp.git
cd QuantityMeasurementApp
