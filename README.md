# Android Mobile Challenge

## The Challenge
The challenge was to design a memory game that retrieved images from an external JSON endpoint and loaded them into a playable format. 10 matches to win in the easy mode (2 cards per match) and 6 matches to win in the hard mode (3 cards per match).

See [here](https://docs.google.com/document/d/1M2VsBSZr8696HU6mO3MWveSB7p3Do9lOkMrjT5nKiEg/edit) for more details!

## About The App
+ Architecture: I followed the MVVM (Model-View-ViewModel) pattern, which is recommended by Android for its clean structure and decoupling of logic/processing and UI layers. However, this is not a strict MVVM app, as this project's scale doesn't demand it.
+ Language: Kotlin was used! I have more experience in Java but wanted to develop my proficiency in Kotlin and learn how to leverage its unique features (null safety, lambdas, data classes, etc.). As a bonus, I know that top tech companies such as Shopify rely on Kotlin and wanted to demonstrate this skill.
+ Image Loading Libaries: The most commonly used libraries are Picasso and Glide. Since I don't resize the images, the way that they handle cache (real size vs. imageView size) wasn't relevant. I ended up going with Glide since it was smaller, could support GIFs, had more recent support, and is recommended by Android.
+ JSON Serialization: Moshi was used since it adapts better to Kotlin than Gson and is faster.
+ Have any other questions? If you want more information on choosing libraries, the challenges I encountered, or how I designed the app, let me know! I'm available through [email](mailto:e2wai@uwaterloo.ca) and happy to help.

## Some Screenshots
+ No matches found yet:

![No matches found yet image](https://github.com/e-wai/MemoryMatchingGame/blob/master/no_matches_yet.PNG)

+ Some matches found:

![Some matches found image](https://github.com/e-wai/MemoryMatchingGame/blob/master/couple_matches_found.PNG)

+ Congratulations!:

![Congratulatory message image](https://github.com/e-wai/MemoryMatchingGame/blob/master/celebration_screen.PNG)

## Third Party Libraries used
+ Retrofit for API calls
+ Moshi for deserializing JSON from API call
+ Glide for image loading from URLs
+ Mockito and Robolectric for testing

I also used AndroidX components such as ViewModel, LiveData, and DataBinding to use updated practices for MVVM and to experiment with the latest Android libraries/technologies.

GIF from tenor.com, card images from Shopify.