BlackjackV2
===========

Redo of Blackjack program in one week, GO!

-Edit: Extended to two weeks :c

I will remake the Blackjack game I coded about a year ago using the design patterns I have picked up in an intro 
to software engineering class. Model View Controller will be the chief design pattern.

=======================

Tuesday May 13
-Prototyped a design of UI

Thursday May 15
-UML'd and modeled version 1 
-Went through an activity diagram of version 1's dealFirst procedure

Friday May 16
-Finalized prototype design of UI
-Insurance shall have its own area on the screen, instead of a dialog box
-So far, the View shall have the actionlisteners for all the components, but each will call a function that the controller provides. That function would call logic functions in model.

Tuesday May 21
-Worked out implementation of Seats (playing spots where initial bets are made)
-Added more classes to go along with the above.

Thursday May 22
-Most done with implementation of Seat, SeatManager, Hand, and Card. Each Seat should have a
reference to the Bet class as well.
-A continual reworking of my understanding of MVC as well as its implementation for the project. I'll have the View reference Model to update its label displays, but any user input that comes in through mouseclicking events will go through the Controller first. As it stands, the Controller basically delegates these tasks to the Model due to the fact that the project may be a little too simple to really need a Controller. The Model shall directly tell the View to update whenever changes happen in the Model.  
-Also continually making little changes and alterations to the interface abstractions of the Model and Controller as well as access scopes of various functions in various classes. I may need to create an interface for the View.

Friday May 24
-Dealfirst in progress
-toStrings for each game object (card, hand, seat) to be used in label displays

Tuesday May 27
-Fixed how current hands are determined as well as refactoring and moving around the data between classes. Just trying to keep the Single Responsibility Principle upheld within the individual object classes (Seat, Hand, Card) by delegating most of the "behavioral" functions and methods that facilitate the game inside SeatManager. 
-Outlined the general idea for all the game actions during phase 2 of the game (when deciding on hitting/staying/splitting/etc). 

Todo tasks:
-Blackjack logic: Should I use a different class to operate on the logic relating to whether or not the player busts, whether or not dealer busts, and comparing between dealer/player? As it stands, in Blackjack version 1, there was only a method call for each. Three conditionals for the first, a little more for the second, and not much for the third. Otherwise, I would have write these methods in the SeatManager class (bloating it up even more), or in the GameModel (which might give it too much responsibility. Hell, check out my CardSet class from V1, which essentially acted as the model for that version).

-finish dealfirst 
-Money implementation
.
.
.
.
-MUSIC?