# compose-ui-event-handling-demo
This project is a clean and minimal example of managing **UI State** and **UI Events** in Jetpack Compose using `StateFlow`, `Channel`, and `MVVM`.
It demonstrates:
- 🔄 Reactive UI with StateFlow
- 🧼 One-time effects (navigation, snackbars) using Channel
- 🧠 Event-driven UI with sealed classes

### 📸 Demo
![UI State & Event Demo](https://github.com/user-attachments/assets/05847b20-5141-4698-a252-7124d0e21167)

# Tech Stack 
- ViewModel 
- StateFlow
- Channel
- Koin (DI)
- Navigation Component
- Jetpack Compose

# Install
### Add the dependencies 
Add the following to your Module-level `build.gradle.kts` file :
```kotlin
dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.core)
}
```

### Add the Version Catalog 
Add the following to your `libs.versions.toml` file :
```toml
[versions]
navigationCompose = "2.8.9"
koinAndroid = "3.5.0"
koinAndroidxCompose = "3.5.0"
koinCore = "3.5.0"

[libraries]
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigationCompose" }
koin-android = { module = "io.insert-koin:koin-android", version.ref = "koinAndroid" }
koin-androidx-compose = { module = "io.insert-koin:koin-androidx-compose", version.ref = "koinAndroidxCompose" }
koin-core = { module = "io.insert-koin:koin-core", version.ref = "koinCore" }
```
