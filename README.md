# UserSpace

UserSpace is an Android project created to **learn and practice Clean Architecture, Dependency Injection, and modern Android development** by building a real feature step by step.

The main goal of this project is not just to make an application work, but to understand:

- Why each layer exists
- Where each class belongs
- How dependencies flow through the application
- How Dependency Injection solves object creation
- How data moves from the source to the UI
- How to design code that is testable and maintainable

---

## 🎯 Learning Approach

UserSpace is being developed incrementally.

Instead of creating a complete architecture upfront, each concept is introduced when a real problem appears.

The learning process is:

```text
Problem
   ↓
Identify responsibility
   ↓
Choose the appropriate layer
   ↓
Create the abstraction
   ↓
Implement it
   ↓
Connect dependencies
   ↓
Refactor
   ↓
Test
```

The goal is to understand **why** the architecture is designed this way rather than simply following a predefined folder structure.

---

# 🏗️ Project Structure

UserSpace follows a **feature-first Clean Architecture** approach.

```text
shibin.kmp.userspace
│
├── di
│
├── features
│   ├── authentication
│   │
│   └── users
│       ├── data
│       ├── domain
│       └── presentation
│
├── ui
│   └── theme
│
├── App.kt
└── MainActivity.kt
```

Each feature owns its own architecture.

For example:

```text
features/users
│
├── data
├── domain
└── presentation
```

This keeps each feature isolated and makes the project easier to scale.

---

# 👤 Users Feature

The **Users** feature is the first learning feature in UserSpace.

The initial requirement is simple:

> Display a list of users.

We use this feature to progressively learn Clean Architecture and Dependency Injection.

The current architecture is:

```text
Presentation
     ↓
Domain
     ↓
Data
```

More specifically:

```text
UserListScreen
      ↓
UserViewModel
      ↓
GetUsersUseCase
      ↓
UserRepository
      ↓
UserRepositoryImpl
      ↓
UserDataSource
```

---

# 🧠 Clean Architecture Concepts

## 1. Domain Model

The application first defines what a User means.

```text
features/users/domain/model/User.kt
```

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val city: String
)
```

The Domain model represents the application's business concept.

It should not depend on:

- Room
- Retrofit
- Firebase
- Compose
- Android framework

---

## 2. Repository

The Domain defines the repository contract:

```text
features/users/domain/repository/UserRepository.kt
```

```kotlin
interface UserRepository {

    suspend fun getUsers(): List<User>
}
```

The Domain knows **what it needs**, but not **how the data is obtained**.

---

## 3. Repository Implementation

The Data layer implements the repository:

```text
features/users/data/repository/UserRepositoryImpl.kt
```

```kotlin
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return userDataSource.getUsers()
    }
}
```

This keeps the implementation details outside the Domain layer.

---

# 🔄 Dependency Direction

The important dependency rule is:

```text
Presentation
     ↓
Domain
     ↑
Data
```

The Domain defines abstractions.

The Data layer implements those abstractions.

For example:

```text
                 Domain
                   │
                   ↓
            UserRepository
                   ↑
                   │
          UserRepositoryImpl
                   │
                   ↓
             DataSource
```

The Domain does not depend on `UserRepositoryImpl`.

---

# ⚙️ Use Case

A Use Case represents an application action.

For example:

```text
GetUsersUseCase
```

```kotlin
class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}
```

The ViewModel asks the Use Case to perform the operation instead of directly interacting with the repository.

```text
UserViewModel
      ↓
GetUsersUseCase
      ↓
UserRepository
```

---

# 💉 Dependency Injection

UserSpace uses **Hilt** to learn Dependency Injection.

The dependency graph currently looks like:

```text
UserViewModel
      ↓
GetUsersUseCase
      ↓
UserRepository
      ↓
UserRepositoryImpl
      ↓
UserDataSource
```

Hilt is responsible for constructing this dependency graph.

Without Dependency Injection, we would have to manually create:

```kotlin
val dataSource = UserDataSource()

val repository =
    UserRepositoryImpl(dataSource)

val useCase =
    GetUsersUseCase(repository)

val viewModel =
    UserViewModel(useCase)
```

With Hilt, these dependencies are provided automatically.

---

## Hilt Concepts Being Practiced

UserSpace is being used to understand:

```text
@Inject
@HiltViewModel
@Module
@Binds
@Provides
@InstallIn
@Singleton
```

The concepts are introduced gradually instead of being added all at once.

### `@Inject`

Tells Hilt how a class can be constructed.

```kotlin
class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
)
```

### `@Binds`

Connects an interface to its implementation.

```kotlin
@Binds
abstract fun bindUserRepository(
    implementation: UserRepositoryImpl
): UserRepository
```

Conceptually:

```text
UserRepository
      ↓
UserRepositoryImpl
```

### `@InstallIn`

Defines which Hilt component contains the module's bindings.

```kotlin
@InstallIn(SingletonComponent::class)
```

---

# 📱 Presentation Layer

The Presentation layer contains UI-related logic.

Current structure:

```text
features/users/presentation/users
│
├── UserListScreen
├── UserViewModel
└── UserListUiState
```

The ViewModel exposes UI state using `StateFlow`.

```text
UserViewModel
      ↓
StateFlow
      ↓
Compose UI
```

---

# 🔄 UI State

The Users screen represents its state using:

```kotlin
sealed interface UserListUiState {

    data object Loading : UserListUiState

    data class Success(
        val users: List<User>
    ) : UserListUiState

    data class Error(
        val message: String
    ) : UserListUiState
}
```

This gives the UI explicit states:

```text
Loading
Success
Error
```

---

# 📚 Learning Roadmap

The project will evolve gradually.

### Completed / Currently Learning

- [x] Feature-first project structure
- [x] Domain model
- [x] Repository abstraction
- [x] Repository implementation
- [x] Use Case
- [x] ViewModel
- [x] UI State
- [x] StateFlow
- [x] Hilt dependency injection
- [x] `@Inject`
- [x] `@Binds`
- [x] `@InstallIn`
- [x] DataSource

### Next

- [ ] Data Model vs Domain Model
- [ ] DTO and mapping
- [ ] Room database
- [ ] Local DataSource
- [ ] Retrofit
- [ ] Remote DataSource
- [ ] Local + Remote repository
- [ ] Error handling
- [ ] Create User
- [ ] Update User
- [ ] Delete User
- [ ] Search Users
- [ ] Authentication
- [ ] Unit testing
- [ ] ViewModel testing
- [ ] Repository testing
- [ ] Advanced Hilt
- [ ] Qualifiers
- [ ] Scopes

---

# 🧪 Learning Through Practice

The project intentionally starts with simple implementations.

For example:

```text
Hardcoded DataSource
        ↓
Room
        ↓
Retrofit
        ↓
Local + Remote
```

Each stage introduces a new architectural problem.

This allows the architecture to be understood through **problem solving**, rather than simply copying a predefined template.

---

# 📖 Learning Guide

A detailed learning guide is maintained separately:

```text
docs/
└── UserSpace-Clean-Architecture-DI-Learning-Guide.pdf
```

The guide explains the concepts, dependency graph, reasoning behind each layer, and the progression of the project.

---

# 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- Clean Architecture
- MVVM
- Hilt
- Kotlin Coroutines
- Flow / StateFlow
- Room
- Retrofit
- Gson
- JUnit

Technologies are introduced progressively as part of the learning process.

---

# 🎯 Project Goal

The goal of UserSpace is to develop a practical understanding of:

- Clean Architecture
- SOLID principles
- Dependency Inversion
- Dependency Injection
- Repository Pattern
- Use Case Pattern
- State Management
- Data Mapping
- Local Persistence
- Networking
- Testable Architecture

The most important objective is:

> **Understand why the architecture works, not just how to implement it.**

---

## 👨‍💻 Learning Philosophy

UserSpace is a learning project.

The architecture will evolve as new requirements are introduced.

Instead of asking:

> "What folders should I create?"

The project asks:

> "What problem am I solving, what responsibility does it require, and where should that responsibility live?"

---

**UserSpace — Learn the architecture by building it.**
