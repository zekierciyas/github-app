# github-app
<img src="/docs/banner.jpg" align="center"/>

 An android app that lists users on Github and shows the user information's in detail page

 ## Features

1. **Offline Listing with Room Database**
   - Implements offline caching of data with Room Database.
   - Adheres to cache expiry policy for efficient data management.

2. **Remote Data from GitHub API**
   - Fetches data from the GitHub API to populate the app with remote content.

3. **Add/Remove User Favorites**
   - Allows users to add/remove items to/from favorites list stored locally.

4. **Data Listing RecyclerView & DiffUtil**
   - Utilizes DiffUtil to efficiently update RecyclerView items.

5. **Data Search**
   - Enables users to search for items within the current list.

6. **Portrait and Landscape Mode Support**
   - Provides a seamless user experience in both portrait and landscape orientations. 

 ## Tech Stack

### Architecture

#### Presentation Layer
The presentation layer is responsible for managing UI components and handling user interactions. In the MVVM (Model-View-ViewModel) architecture, activities, fragments, and custom views represent the View layer. These components observe changes in the ViewModel and update the UI accordingly. They also propagate user interactions back to the ViewModel for processing.

#### Data Layer
The data layer handles data operations, such as fetching data from remote APIs or local databases. It includes repositories, data sources, and network clients responsible for retrieving and storing data. In the MVVM architecture, the ViewModel interacts with the data layer to fetch data and update the UI. Room Database, Retrofit, and other data-related libraries are typically used in this layer.

#### Domain Layer
The domain layer contains the business logic of the application. It defines how data is processed and manipulated to fulfill the requirements of the application. This layer is independent of any specific implementation details, such as UI or data sources. Use cases, domain models, and business logic components reside in this layer. The ViewModel communicates with the domain layer to perform business operations and obtain processed data.

### Libraries and Tools
- MVVM Architecture
- View Binding
- Coroutine
- Flow for asynchronous data streams
- Channel for communication and data transfer between coroutines
- Glide for image loading and caching
- Hilt Dependency Injection
- ConstraintLayout for flexible and responsive UI design
- Room Database for local data persistence
- RecyclerView with DiffUtil for efficient list rendering and updates
- Retrofit for networking operations
- ViewModel for managing UI-related data and handling configuration changes
- Navigation Component for navigating between destinations in the app
- Material Design components for a modern and consistent UI
- Kotlin DSL for Gradle build scripts

## Screen Shots

* Portrait 

|                  Listing Screen Empty              | Listing Sreen Loaded                                | Detail Screen                                       |
| -------------------------------------------------- | --------------------------------------------------- | -------------------------------------------------- |
| ![Listing Screen Empty ](/docs/ss_1.png)           | ![Listing Sreen Loaded ](/docs/ss_2.png)            | ![Detail Screen ](/docs/ss_3.png)                   |

* Landscape 

<img src="/docs/ss_4.png" align="center"/>
<img src="/docs/ss_5.png" align="center"/>

## Conventional Commit Guidelines

In order to explain the commit history in the project more clearly, the commit guideline was designed according to European conventional commit rules. General commit types are as follows, check [here](https://ec.europa.eu/component-library/v1.15.0/eu/docs/conventions/git/) for more detailed information.

* **feat**: A new feature
* **fix**: A bug fix
* **docs**: Documentation only changes
* **style**: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
* **refactor**: A code change that neither fixes a bug nor adds a feature
* **perf**: A code change that improves performance
* **test**: Adding missing tests
* **chore**: Changes to the build process or auxiliary tools and libraries such as documentation generation
