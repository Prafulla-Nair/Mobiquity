
#### Authored by Prafulla Thottasseri
#### A scalable app which displays a list of categories. On selection of a category it shows the products in the category. On selecting a particular product it's details are shown.

* Mobiquity app is written entirely in Kotlin, using Android KTX. Android KTX is a set of Kotlin extensions that optimizes Jetpack architecture components and Android platform APIs for more concise and idiomatic Kotlin code.
* The app is built using MVVM architecture with dependency injection and follows TDD approach.
* The app uses a single Activity with multiple Fragments. Transitions between fragments use the Navigation component and transition animation actions.
* The screens use fragment layouts, created using ConstraintLayout and Data Binding
* JSON data from the REST API is fetched using Retrofit2 and Rxjava2 and surfaced to the UI through ViewModel via LiveData
* GSON library is used for JSON parsing
* Glide image loading library is used to load images in the app
* AppCompat is used to preserve key app functionality on older versions of Android
* Testing is performed by both local JUnit tests using Mockito and Espresso Android UI tests
* ktlint is used for kotlin code formatting

#### The app has the following packages:
1. **model**: It contains the Data, CategoriesApi and CategoriesService
2. **view**: It contains Activity class and adapter, fragment, util packages needed for the UI implementation.
3. **viewmodel**: It contains the SharedViewModel class that surfaces data to the UI via LiveData





