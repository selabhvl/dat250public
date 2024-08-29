# Domain-Description: Poll-App

Your task is to develop a _smart voting app_!
The app allows users to create _pools_.
In order to create a poll, one first has to register as a new user by providing a username, password, and e-mail address.
Each poll is basically one question with a given set of voting options.
When a poll is registered it automatically gets assigned a unique identifier. 
The creator of a poll may chose to make the pull public or private.
If the poll is private only users that are logged in are able to see the poll given that the creator of the poll had send them an invite.
If the poll is public it is available under a public URL, which opens the possibility for anonymous votes.
Public polls therefore open up for the possibility that one (possibly anonymous) user can vote several times.
For private polls, the creator can chose to limit the number of allowed votes per user to one.
Each poll has published timestamp and a deadline timestamp, which can be chose by the poll creator.
A vote is only valid if it falls into the time span between published and deadline.


## Extension: Quiz-App

In this extension, the Poll-App shall be extended to become a Quiz-App. 
A quiz is a special type of a private poll, where the creator has to select one or more of the voting options as _correct_.
The invited participants get points when selecting the correct options. 
Depending on how "quick" the particpants select the correct option, they get more points, i.e. the time that goes between 
publishing the question and registration of the answer.
Since single-question polls are a bit boring, a quiz may comprise a sequence of follow-up questions.
The quiz participants accumulate points throughout the quiz. 
At the end, the app should automatically present a leaderboard that presents the participants in descending order based on their points.

**Bonus:** Feel free to additionally create new types of questions, e.g. where the user has to write a "free text answer",
where the user has to sort elements in the correct order, etc.
