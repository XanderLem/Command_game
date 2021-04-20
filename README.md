# Command_game
# a small little dungeon game that can be played in the command line output (so far, only tested in intelij running the main config)
## please note that this is a small project to meet the requirements of a major project for CSH and is
## not intended to ever be fully finished, released, or even played by the public, bugs and glitches are to be expected

###Update 4/20/21:
####Noticed that only having two square rooms with nothing in them was boring. I am not good with level design, so I started to implement the origional Doom's E1M1 Hanger level
also started to experiment with color-coding the characters to make the visuals easier to understand

# Running the "game"
## to start, run the main run configuration, your output should look similar to this
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_1.PNG)
## a quick explanation of what this is:
* The "P" located at row 3 column 4 is the player
* The "." are blank tiles that the player can move to
* The "=" are walls that the player cannot go past
* The "D" is a door, more about that later
## Below the view of the map ( or what I will refer to as a room) is the player information
* The room is the name of room that the player is currently in
* The name is the name of the player, it is not used for anything yet, but might be useful in the future
* The health is the players' health, it is never used or updated yet, but will be in the future
* The score is the players score which can be updated now 
* The ">" is the cursor prompt for inputting commands
# Inputting Commands
## currently, there are 7 commands, 4 of which are for movement, please note that the game only accepts one command at a time
* "w" for moving the player up
* "s" for moving the player down
* "a" for moving the player left
* "d" for moving the player right
* "save" for saving the games current state including: the rooms state, and the players state
* "quit" for quitting the game. please note that quitting without saving will result in any progress that was not saved, being lost
* "score number" for increasing the score by "number" IE: "score 5" adds 5 to the players score
# Here is an example of typing "d" and its result
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_2.PNG)
## result
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_3.PNG)
# What are doors?
## doors are the way that the player can move between rooms
## to use a door, simply move next to it, and move to the tile where are 
## here is the input  of moving to the door
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_4.PNG)
## and the result
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_5.PNG)
# What is the "$"?
## the "$" is the symbol that is used to represent a point
## to gain a point, simply walk to it, and you will gain a point
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_6.PNG)
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_7.PNG)
# Extra info/ behind the game
## typing the save command and then quitting after the command was entered will save the game
## the game saves rooms, and the player state in text files
## here is what the saved state of the first room that the player started in looks like
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_9.PNG)
## the first line, is the dimensions of the room (row,col)
## lines 2-5 are simple, they are representing part of the room with walls and empty tiles
## please note that each symbol needs to be separated by a space on either side 
## line 6 is the same as lines 2-5 but with one major difference: it has a door
# How do doors work and how to make them?
## doors are made by typing the destination room filename followed by ".door", the first letter of the name would be in the place where a "." or a "=" would go
## in this example, the destination room is room3 and its file name is room3.txt, so the door for room3 is "room3.door"
## please not that a door MUST have a corresponding door in the other room, as seen in room3's text file
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_8.PNG)
* the "$" is the point symbol, and the "P" is the player symbol
## to quickly go over how doors work, and this is an oversimplification:
* when the player moves to a tile with a door in it the door finds the destination room
* and then corresponding door that has the origin room name ("room3.door" looks in room3, for a door with "room2.text" as its destination)
* and then tells that room to change the players rooms and move the player to a tile next to that door
* ( in reality, the door tells the manager object to change the rooms, and the manager does the rest) 
## How the player info is saved
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_10.PNG)
* The first line is the name of the file of the room where the player was, when the game was saved
* The next line is the name of the player
* The third line is the player's health
* The last line in the player's score
## Misc
* rooms can be any size and shape (as long as that shape is a rectangle, or a square), as long as the dimensions match the numbers on the first line
* will add any other info when I think of some
* might implement a settings feature for changing the colors of the objects in a room
