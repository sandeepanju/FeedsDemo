# Goal

The purpose of these exercises is for the interviewer to get a sense of how you write code and english. This shouldn’t take you more than a few hours to get done.

## Writing exercise

The point of this exercise is to benchmark your writing skills.

Write an approximately 500 word article about something you're passionate about that’s technology related. It doesn't really matter what the topic is. For instance, you can write about a [language feature](https://www.mikeash.com/pyblog/friday-qa-2015-06-19-the-best-of-whats-new-in-swift.html) you like or dislike, something in the [news](http://david-smith.org/blog/2014/11/18/initial-impressions-for-watchkit/), [something](http://daringfireball.net/2014/11/native_apps_are_part_of_the_web) that you have an opinion about, etc.

File a pull request on this repo with your article in a file called `Article.md`.



## Code exercise

The point of this exercise is to benchmark your ability to write code. Although this is a toy problem, please make sure your solution is of production quality. You should be comfortable checking your solution into your primary code base. Use proper names, check for errors, etc. Your code should be representative of how you code on the job. The process you follow should also be representative of a process you would normally follow (i.e. the way you break up commits, write commit messages, etc.)


### Constraints

- You must submit an Android Studio project and a signed APK (a self signed APK is fine).
- Your code must be written using the Android SDK in Kotlin, targeting API level 29 or later (`minSdkVersion` must be no higher than 21).
- You must use the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/).
- You may use any libraries of your choice.
- You must provide instructions on how to build and run your code.
- The app must work in both portrait and landscape orientations.


### Submission

Your submission should be a pull request to the master branch of this repository from a feature branch. Do not fork the repository.


### Problem

A JSON over HTTP API is deployed at `http://surya-interview.appspot.com`. The goal is to make requests to it and render the data obtained from it in an Android application.

Your app should start with a view that asks the user to enter an email address. You are then to make an HTTP `POST` request to `http://surya-interview.appspot.com/list`. The post body must be a JSON object that looks like this:

```javascript
{
    "emailId": "<email address you got from user>"
}
```

The response will contain a JSON object that looks like this:

```javascript
{
    "items": [
        {
            "emailId": "john@doe.com",
            "imageUrl": "http//something.com/foo.jpg",
            "firstName": "John",
            "lastName": "Doe"
        },
        {
            "emailId": "jane@doe.com",
            "imageUrl": "http//something.com/bar.jpg",
            "firstName": "Jane",
            "lastName": "Doe"
        }
    ]
}
```

You are to cache this data locally on the device (that is, if you've gotten the data once, the next time your app is opened, it should use the locally stored data to render the view and then update data from the server in the background) and render each of these items in a row in a list view.

We recommend avoiding Dagger, but feel free to use it if you must. If you’re in the habit of writing tests, please do so. Again, the idea is to have this be representative of how you code professionally.


# Contact

If there's anything you're uncertain about and need clarification, please file an issue on this repo, and assign it to @gps.
