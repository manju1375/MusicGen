# MusicWiki Mobile Android application

#### **Local machine codebase setup flow**

     1. Clone the MusicWiki Android using:
        - **Command:**  git clone https://yourusername@stash.lbidts.com/scm/~yourusername/https://github.com/manju1375/MusicGen.git
     2. Import it in Android studio

Your environment is now set up and ready for development.

#### Architecture
The MusicWiki Application adheres to a Mode-View-ViewModel Architecture. Every View, Fragment, or Activity which makes display changes based on Business rules should implement an interface and have an associated ViewModel which is fully unit testable. For more information on the approach described above please read the article [here](https://developer.android.com/jetpack/guide)
) for more information.
![alt text][mvvm]

[mvvm]: https://developer.android.com/jetpack/guide

#### Architecture Documentation
----------

RxJava
http://reactivex.io/RxJava/javadoc/

Retrofit 2.0
http://square.github.io/retrofit/

Mocker https://github.com/teegarcs/Mocker


#### Helpful Articles:



http://android-developers.blogspot.com/

https://google.github.io/styleguide/javaguide.html

https://developer.android.com/index.html?hl=ru

https://developer.android.com/training/index.html?hl=ru

http://fragmentedpodcast.com/

http://androidweekly.net/

####  **Commit flow**
     1. Commits should only be made into your branch, never develop/master.
     2. After you commit, set up a pull request to pull your code into develop/your feature.
     3. Repeat for each kit you modify.
     4. If you make a change in the application that is dependent on a change you made in an associated kit, you MUST create the kit pull request first and then place a link to that respective pull request in your application change pull request. This is to ensure the reviewer modifies them in the correct order and the build does not break.

