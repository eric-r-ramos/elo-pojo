# ELO-POJO #

## Overview ##
Application concept used to calc a rating using the Elo Rating Algorithm.
The development was made using simple java classes.


## Structure ##

There are 4 core classes in order to process the ranking:

* Entities classes: `Match.java`, `Player.java`
* The Elo Rating Algorithm: `EloRating.java`
* A master class `Championship.java` used to store and orchestrate all inputs

The report is made using a mix of design patters like builder, command, dependency injection, etc. 

Basically there is the abstract class `Report.java` that contain the method abstract method `print()`. 
The `Report` demands a injection of `Championship` class (the context) 

Every report implemented should implements `Report` and should be added in `reportBuilder()`method at `EloPojo` class

The main method is under `EloPojo.java`. It validate the arguments, start a new Championship and build the report 

### Dependencies ###
All dependencies can be checked in pom.xlm

* slf4j-log4j12-1.7.5.jar 

* slf4j-api-1.7.5.jar 

* log4j-1.2.17.jar 

* hamcrest-core-1.3.jar 

* lombok-1.16.16.jar 

* junit-4.12.jar 



## Usage ##

Use java EloPojo <file_players> <file_matches> <[rank] | [player <player_name> | [next_matches] | [matches] >

where:

    file_players: 	plain file where each line has an ID number and a name for said ID
    
    file_matches: 	plain file where each line contains the ID of the two players of a match and the first one is the winner of said match. with id matches
  
    report_type: 	use the string:
	
        rank: for list players ordered for their rating
		    
        player <player_name>: to show player data 
		    
        next_matches: for list of suggested next matches
		    
        matches for: list of matches

### Files ####
The files should be on this format:
* <player_file>: each line has the id and a name for the player with a space used to separate the fields. Ex.: `<id> <name>` 

* <matches_file>: each line represents a match that happened between two players. Only the id is used. 
The first id representes the winner. Ex.: `<id> <id>`
