## Mapsbox demo app

This app serves as a demo for [Mapbox SDK](https://docs.mapbox.com/android/maps/guides/). It shows how to use Mapbox SDK to display a map and add markers. It also displays weather information for placed markers. 

## How to run

In order to run the app you will need to obtain:
- [Mapbox access token](https://docs.mapbox.com/android/maps/guides/install/#configure-credentials)
- [OpenWeather API key](https://openweathermap.org/appid)

One you have those, you need to store them in your local `gradle.properties` file as:

mapbox_public_api_key=YOUR_PUBLIC_MAPBOX_KEY_HERE
mapbox_secret_api_key=YOUR_SECRET_MAPBOX_KEY_HERE
open_weather_api_key=YOUR_OPEN_WEATHER_API_KEY_HERE

## APIs used

- [Nominatim](https://nominatim.org/release-docs/latest/api/Reverse/)
- [OpenWeather](https://openweathermap.org/appid)

## Libraries used

- Jetpack Compose
- Hilt
- Room
- Retrofit
- Gson
- Coroutines
- Flow
- DataStore
