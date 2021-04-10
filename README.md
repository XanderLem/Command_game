# Command_game
# a small little dungeon game that can be played the in command line output (so far, only tested in intelij running the main config)
## please note that this is a small project to meet the requirements of a major project for CSH and is
## not intended to ever be fully finished, released, or even played by the general public


# Running the "game"
## to start, run the main run configuration, your output should look simmilar to this
![alt text](https://github.com/XanderLem/Command_game/blob/master/Images/Command_game_read_1.PNG)
## a quick explination of what this is:
* The "P" located at row 3 coulmn 4 is the player
* The "." are blank tiles that the player can move to
* The "=" are wals that the player cannot go past
* The "D" is a doorm more about that later
## Below the view of the map ( or what I will refer to as a room) is the player information
* The room is the name of room that the player is currently in
* The name is the name of the player, it is not used for anyting yet, but might be usefull in the future
* The health is the players health, it is never used or updated yet, but will be in the future
* The score is the players score which can be updated now 
* The ">" is the cursor prompt for inputting commands
# Inputting Commands
## currently there are 7 commands, 4 of which are for movement
* "w" for moving the player up
* "s" for moving the player down
* "a" for moving the player left
* "d" for moving the player right
* "save" for saving the games current state including: the rooms state and the players state
* "quit" for quitting the game. please note that quitting with out saving will result in any progress that was not saved, being lost
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
