HiggsQuizzes
========
This project contains solutions of two quizzes from Higgs.
First one is the android application displaying Github users, which could be checked by cloning this project and build in android studio.
Second one is exchanging format of number from Roman to Chinese, which is under [app/src/java/main/package_name](https://github.com/timhuang1018/HiggsQuizzes/blob/master/app/src/main/java/com/timhuang/higgsquizzes/Quiz2.kt)

About the application
----
 - Demo how to use kotlin in android MVVM architecture
 - Separate each layer's behavior to make each testable
 - Asking data of bidirectional followed might run out of github api's free limit, consider to cache in local is one of the solutions

Library usage in project
----
 - **Retrofit:** for http request
 - **Moshi:** for parsing json
 - **Coroutine:** for asynchronous programming
 - **Glide:** for image processing
 - **Navigation Component:** for navigation