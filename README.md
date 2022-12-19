# Warning App

This project is a Warning App, which uses https://nina.api.proxy.bund.dev/api31/ to retrieve data from api.

In the app there is a list of different warning channels, when item is clicked, user is navigated to a warning screen, where user can see and scroll through all of the available warning from the selected warning channel. There is a color coding, to see the severity of the warning:
* white color - Minor, 
* yellow color - Moderate, 
* pink color - Severe, 
* red color - Extreme.

The project uses MVVM pattern, Jetpack Compose, coroutines, Dagger Hilt, Retrofit, OkHttp.
