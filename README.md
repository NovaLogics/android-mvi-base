<h1 align="center" >
Clean Feature MVI Architecture <br> 
♨ [ ᴀɴᴅʀᴏɪᴅ  ᴘʀᴏᴊᴇᴄᴛ ] ♨
</h1>

⁃ ᴄᴏɴᴛᴇɴᴛꜱ ⁃  
- [Introduction](#introduction)
- [What is MVI?](#what-is-mvi)
- [MVVM vs MVI](#mvvm-vs-mvi)
- [About Clean-Feature-MVI](#about-clean-feature-mvi)
- [Android Architecture Project Structure](#android-architecture-project-structure)
- [Benefits](#benefits-of-clean-feature-mvi)
- [Data Flow Overview](#data-flow-overview)
- [Best Use Cases](#best-use-cases)
- [Summary](#summary)

---

### Introduction
🚀 Welcome to **Clean-Feature-MVI**,  a feature-based, MVI (Model-View-Intent) Clean Architecture designed for modern Android development. This architecture focuses on unidirectional data flow, ensuring predictable state management and clear separation of concerns.

---

### What is MVI?
- 📚 **MVI** stands for **Model-View-Intent**.
- It emphasizes **unidirectional data flow**, data moves in one direction, making state changes easy to track.
- Components:
    - **Model**: Represents the state of the view.
    - **View**: Renders the UI and reflects the current state.
    - **Intent**: Describes user actions or events triggering state changes.

---

### MVVM vs MVI

| ⚔️ Aspect      | MVVM                                  | MVI                                          |
|----------------|---------------------------------------|----------------------------------------------|
| Data Flow      | Bidirectional ( two-way)              | Unidirectional    ( one-way)                 |
| State Control  | Can become complex over time          | Explicit and predictable                     |
| Event Handling | Observers can lead to race conditions | Handled through immutable state changes      |
| Testability    | Decent                                | Highly testable due to state-driven approach |
| Complexity     | Moderate                              | Slightly higher, but clearer long-term       |

---

### About Clean-Feature-MVI
- 🌟 Built on **MVI principles** combined with **clean architecture**.
- Organized in **feature-based modules**.
- Uses **ViewModel** for state management.
- Integrates **Room DB** and **Retrofit** for local and remote data handling.
- Includes dependency injection using **Hilt**.

---

### Android Architecture Project Structure


```plaintext
📁 app

├── 📁 di
│      ├── 📄 AppModule.kt            // Dependency injection for app-level modules
│      └── 📄 NavigationModule.kt     // Provides navigation-related dependencies
│
├── 📁 navigation
│      ├── 📄 AppNavigation.kt        // Handles app-wide navigation logic
│      ├── 📄 NavGraph.kt             // Defines the navigation graph
│      └── 📄 Screens.kt              // Contains screen route definitions
│
├── 📄 MainActivity.kt                // Main entry point of the app (UI layer)
└── 📄 MainApplication.kt             // Application class for app initialization

📁 core

├── 📁 arch
│   ├── 📁 state
│   │   ├── 📄 ViewIntent.kt          // Represents user intentions (actions)
│   │   ├── 📄 ViewUiState.kt        // Represents the UI state
│   │   └── 📄 ViewEffect.kt         // Represents one-time UI events
│   └── UiState.kt                   // Base UI state interface
│
├── 📁 data
│   ├── 📁 local
│   │   ├── 📁 database
│   │   │     └── 📄 AppDatabase.kt    // Room database setup
│   │   └── 📁 datastore
│   │         ├── 📄 DataStoreKeys.kt         // Keys for datastore preferences
│   │         ├── 📄 DataStoreRepository.kt   // Interface for data storage
│   │         └── 📄 DataStoreRepositoryImpl.kt // Implementation of data storage
│   └── 📁 remote
│        ├── 📄 ApiConfig.kt           // Configures API client
│        ├── 📄 ApiService.kt          // Defines API service calls
│        └── 📄 Response.kt            // Handles API responses
│
├── 📁 di
│   ├── 📁 module
│   │   ├── 📄 DatabaseModule.kt       // Provides database dependencies
│   │   ├── 📄 DataStoreModule.kt      // Provides datastore dependencies
│   │   └── 📄 NetworkModule.kt        // Provides network dependencies
│   └── 📁 qualifier
│        └── 📄 Qualifier.kt           // Defines custom qualifiers for DI
│
├── 📁 presentation
│   ├── 📁 base
│   │   └── 📄 BaseViewModel.kt        // Base ViewModel for feature screens
│   ├── 📁 components
│   │   ├── 📄 CustomProgressIndicator.kt // Loading indicator component
│   │   └── 📄 ErrorMessageCard.kt         // Error message component
│   └── 📁 theme
│         ├── 📄 Color.kt              // App color definitions
│         ├── 📄 Shape.kt              // Shape definitions
│         ├── 📄 Theme.kt              // App theme setup
│         └── 📄 Type.kt               // Typography definitions
│
└── 📁 util
      ├── 📄 Common.kt                // Common utility functions
      ├── 📄 Constants.kt             // App constants
      ├── 📄 Extensions.kt            // Kotlin extension functions
      └── 📄 TrustManager.kt          // Handles SSL configurations

📁 feature

├── 📁 home
│   ├── 📁 data
│   │   ├── 📁 di
│   │   │   └── 📄 DataModule.kt       // Provides dependencies for home feature
│   │   ├── 📁 local
│   │   │   ├── 📁 converter
│   │   │   │   └── 📄 ListConverter.kt // Converts lists for Room database
│   │   │   ├── 📁 dao
│   │   │   │   └── 📄 ItemDao.kt        // Data Access Object for home feature
│   │   │   └── 📁 entity
│   │   │       └── 📄 ItemEntity.kt     // Entity class for Room database
│   │   ├── 📁 mapper
│   │   │   └── 📄 ItemMapper.kt         // Maps data between layers
│   │   ├── 📁 model
│   │   │   └── 📄 ItemDto.kt            // Data Transfer Object (DTO)
│   │   ├── 📁 remote
│   │   │   └── 📄 ItemApiService.kt     // API calls for home feature
│   │   └── 📁 repository
│   │       └── 📄 ItemRepositoryImpl.kt // Implements data logic for home
│   │
│   ├── 📁 domain
│   │   ├── 📁 di
│   │   │   └── 📄 DomainModule.kt       // Provides domain-level dependencies
│   │   ├── 📁 model
│   │   │   └── 📄 Item.kt               // Domain model for home feature
│   │   ├── 📁 repository
│   │   │   └── 📄 ItemRepository.kt     // Abstract data repository
│   │   └── 📁 usecase
│   │       ├── 📄 CountItemsUseCase.kt      // Business logic for counting items
│   │       ├── 📄 GetItemsFromApiUseCase.kt // Gets items from API
│   │       ├── 📄 GetItemsFromDbUseCase.kt  // Gets items from DB
│   │       └── 📄 InsertItemsToDbUseCase.kt // Inserts items to DB
│   │
│   └── 📁 presentation
│         ├── 📁 components
│         │   └── 📄 ItemComponent.kt      // UI component for displaying items
│         ├── 📁 state
│         │   ├── 📄 HomeEffect.kt         // Home screen UI effects
│         │   ├── 📄 HomeIntent.kt         // Home screen user intentions
│         │   └── 📄 HomeUiState.kt        // Home screen UI state
│         ├── 📄 HomeScreen.kt             // Home screen UI
│         └── 📄 HomeViewModel.kt          // ViewModel for home screen
│
└── 📁 details...                        // Other feature modules...

```

📚 **App Layer:** 
- The entry point of the application, contains the main activity, application class, and navigation setup.

📚 **Core Layer:** 
- Provides shared utilities, constants, and common components used across layers.

📚 **Feature Layer:** 
- Organizes code by features, keeping each feature isolated and modular.

📚 **Data Layer:**
- Manages data sources (local and remote). Includes repositories, API services, and database access.

📚 **Domain Layer:**
- Contains business logic, use cases, and interfaces for repositories.

📚 **Presentation Layer:**
- Handles UI components and user interactions. Includes ViewModels and UI states.

---

### Benefits of Clean-Feature-MVI
- ✅ **Predictable state management**: Unidirectional data flow simplifies debugging.
- ✅ **Scalable and modular**: Easy to add new features without affecting existing code.
- ✅ **Test-friendly**: State and side effects are isolated, making unit testing straightforward.
- ✅ **Separation of concerns**: Clear division between data, domain, and presentation layers.
- ✅ **Reusability**: Modular design supports reusability across features.

---

### Data Flow Overview

```plaintext
Screen (UI) +<---->+ ViewModel +<---->+ UseCase +<---->+ Repository +<---->+ API/Room DB
```

- 🔄 **Screen**: Displays data and captures user actions
- 🔄 **ViewModel**: Processes intents, updates state, and invokes use cases
- 🔄 **UseCase**: Contains business logic
- 🔄 **Repository**: Fetches data from API or DB
- 🔄 **Room DB**: Local data storage
- 🔄 **API**: Remote data source

#
### 🔄 Screen → Fetch API/DB Flow (Unidirectional)
```plaintext
Screen (UI) → ViewModel → UseCase → Repository → API/Room DB
```
#### Screen → ViewModel:

  - The Screen sends an Intent (e.g., "Fetch Data") to the ViewModel

#### ViewModel → UseCase:

 - The ViewModel calls the appropriate UseCase to handle the request

#### UseCase → Repository:

 - The UseCase requests data from the Repository

#### Repository → API/Room DB:

 - The Repository fetches data from the API (remote) or Room DB (local)

<br/>

### 🔄 API/DB → Screen Flow (Unidirectional)
```plaintext
API/Room DB → Repository → UseCase → ViewModel → Screen (UI)
```

#### API/Room DB → Repository:

- The API or Room DB provides the requested data to the Repository

#### Repository → UseCase:

- The Repository returns the data to the UseCase

#### UseCase → ViewModel:

- The UseCase processes the data (if needed) and sends it to the ViewModel

#### ViewModel → Screen:

- The ViewModel emits a new State containing the data, which is observed by the Screen to update the UI

---

### Best Use Cases
🎯 Clean-Feature-MVI is ideal for:
- **Scalable apps** with complex data flows.
- **Real-time apps** requiring precise state management.
- **Modular projects** with feature separation.
- **Test-driven development (TDD)** projects.

---

### Summary
🏁 **Clean-Feature-MVI** is a powerful, modern architecture combining MVI and clean principles, perfect for building scalable, testable, and maintainable Android applications. With its clear structure and unidirectional data flow, it empowers developers to tackle complexity with confidence.

---

