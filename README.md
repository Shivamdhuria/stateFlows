# State Flows

<p align="start">
An Android Application written purely in Kotlin exploring various usages of Flows and Coroutines in a Real World App.</p>

# Medium Articles 
Follow me on [Medium](https://medium.com/@shivamdhuria) for latest articles.

  | Medium Article  | Github Branch|
| ------ | ------ |
|Form Vaidation using Mutable State Flows|pending|
|Search Listeners for Latest Query using Debounce|pending|

# Cases

## Form Validation
The Screen contains three input fields, the **Submit** button is only enabled once all the 3 fields are properly filled.

 - First Name must contain only alphabets.
 - Password must be atleast 8 characters long.
 - User Id must contain an underscore( _ )

Once all these fields are properly filled only then the **Submit** button is enabled. The Submit button disables again if any of the fields are edited and the conditions are not met.

## Smart Listeners for Shooting requests
For this we use an example of a search query. You don't want to shoot a request whenever the text is edited but wait untill a couple of seconds before shooting a netwiork request.

This way you reduce the number of requests and only return result for the latest text query.
For Eg: User may type "Co"..."Coff"..."Coffee". You'd only want to shoot a request when the user has typed the whole word instead of sending a request for every input.

## Tech Stack
<img src="/previews/transition.gif" align="right" width="20%"/>

- [Kotlin](https://kotlinlang.org/)  
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)  
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
  
  
## Roadmap (Features to be added )
- Exponential Backoff for Network Requests
  
  
### Find this repository useful? :heart:
Support it by joining starring this repository. :star: <br>
And follow me on [Medium](https://medium.com/@shivamdhuria) and [Github](https://github.com/Shivamdhuria?tab=repositories)
  
  
### Issues and Contribution
Find any other use case scenario for Flows? Hit a PR!

### Project Maintainers
This project is founded and actively maintained by [Shivam Dhuria](https://github.com/Shivamdhuria).

### Credits
Got the idea from [Kaushik Gopal](https://github.com/kaushikgopal/RxJava-Android-Samples#16-simple-timeout-example-using-timeout)
  
### Libraries
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Gson](https://github.com/google/gson) - Gson is a Java library that can be used to convert Java Objects into their JSON representation.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - For storing Database.

