# UserSpace

An Android project built to **learn and practice Clean Architecture** using modern Android development practices with Kotlin.

The goal of this project is to understand how to structure a scalable Android application by separating **UI, business logic, and data access** into independent layers.

## 🚀 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Clean Architecture**
- **MVVM**
- **Hilt** – Dependency Injection
- **Kotlin Coroutines**
- **Flow / StateFlow**
- **Retrofit** – API communication
- **Gson** – JSON serialization
- **Android Architecture Components**

## 🏗️ Architecture

UserSpace follows the **Clean Architecture** approach with three main layers:

```text
Presentation
     ↓
   Domain
     ↑
    Data
```

### Presentation Layer

Responsible for UI and user interaction.

```text
presentation/
├── ui/
├── viewmodel/
└── state/
```

Contains:

- Jetpack Compose screens
- ViewModels
- UI state
- User interactions

### Domain Layer

Contains the application's **business logic**.

```text
domain/
├── model/
├── repository/
└── usecase/
```

Contains:

- Business models
- Repository interfaces
- Use cases

The Domain layer does not depend on Android framework or external data sources.

### Data Layer

Responsible for retrieving and managing data.

```text
data/
├── remote/
├── local/
├── repository/
└── model/
```

Contains:

- API services
- Data sources
- DTOs
- Repository implementations
- Data mapping

## 📁 Project Structure

```text
UserSpace/
│
├── data/
│   ├── local/
│   ├── remote/
│   ├── repository/
│   └── model/
│
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
│
├── presentation/
│   ├── ui/
│   ├── viewmodel/
│   └── state/
│
└── di/
```

## 🔄 Data Flow

A typical flow in UserSpace looks like:

```text
User Interaction
       ↓
   Compose UI
       ↓
    ViewModel
       ↓
    Use Case
       ↓
Repository Interface
       ↓
Repository Implementation
       ↓
   Data Source
       ↓
      API
```

For example:

```text
User
 ↓
UserScreen
 ↓
UserViewModel
 ↓
GetUsersUseCase
 ↓
UserRepository
 ↓
UserRepositoryImpl
 ↓
LocationApiService
 ↓
REST API
```

## 🎯 Learning Goals

This project is primarily for practicing:

- Clean Architecture
- MVVM
- SOLID principles
- Separation of concerns
- Dependency inversion
- Repository pattern
- Use Case pattern
- Dependency Injection
- Coroutines
- Flow
- StateFlow
- Error handling
- UI state management
- API integration
- DTO → Domain model mapping
- Testable architecture

## 🧩 Key Clean Architecture Principles

### Dependency Rule

Dependencies should point **towards the Domain layer**.

```text
Presentation ──────→ Domain
Data ──────────────→ Domain
```

The Domain layer should remain independent from:

- Android UI
- Retrofit
- Room
- Firebase
- Compose
- Other external frameworks

### Repository Pattern

The Domain layer defines the repository contract:

```kotlin
interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
}
```

The Data layer provides the implementation:

```kotlin
class UserRepositoryImpl(
    private val apiService: UserApiService
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        // Fetch and map data
    }
}
```

### Use Case

Business operations are represented as individual use cases:

```kotlin
class GetUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return repository.getUsers()
    }
}
```

## 🧪 Testing

The architecture is designed to make individual components easier to test.

Potential areas for testing:

- Use Cases
- ViewModels
- Repository implementations
- Data mapping
- UI state
- API error handling

## 📚 Learning Approach

UserSpace is being developed incrementally.

Each feature is used as an opportunity to understand **why** a particular architectural decision is made rather than simply following a predefined template.

The focus is on understanding:

> **What belongs where, why it belongs there, and how the layers communicate.**

## 🛠️ Future Improvements

- [ ] Add Room database
- [ ] Add offline-first support
- [ ] Improve error handling
- [ ] Add unit tests
- [ ] Add UI tests
- [ ] Add Paging
- [ ] Add authentication
- [ ] Improve network layer
- [ ] Add reusable UI components
- [ ] Explore modularization
- [ ] Explore Kotlin Multiplatform concepts

## 👨‍💻 Purpose

This repository is a **learning and practice project** focused on building a strong understanding of Clean Architecture and modern Android development.

---

**UserSpace — Learn. Build. Refactor. Repeat.**
