CS 2114 - Project 2 - Group 67

# Reflection

Added MediaType interface that is implemented by Song and Movie; Changed what the linked chain holds from Song objects to MediaType objects Song and Movie so that the linked chain held multiple classes

ShuffleBoxRunner now uses a linked chain, two scanners - one for file and one for user input, a file and BufferedWriter so that it can more effectively read and write files and handle user input too

Main method features several helper methods that were not included in specification doc: readHistory, writeHistory, prompt, displayRuntime, displayPlaylist, getItemByName, forceClear, and cursorFix

all MediaTypes have both an overwritten toString and formattedToString method now, with toString being for files and formattedTostring being for printing



Time complexity updated from spec

shuffle() time complexity should be O(N^2) instead of O(N)

readHistory O(N)

writeHistory O(N)

prompt(boolean) O(1)

prompt(int) O(1) unless case 6: O(N)

displayRuntime O(N)

displayPlaylist O(N)

getItemByName O(1)

forceClear O(1)

cursorFix O(1)

# Next Steps

The main next steps in the program are finishing test cases, ensuring the MVP is fully finished, and implementing any possible stretch goals. Test cases primarily need to be finished within ShuffleBoxRunner to ensure that each method is working properly. The MVP should be fully complete; however, based on test results some changes may need to be made to fix any errors. Finally the stretch goals we could still attempt to add are a no repeat shuffle that would play all unique media before playing any repeated media. We will be dropping the weighted shuffle stretch goal due to being overly complex while also being significantly repeated with the project having two different shuffle methods already.

# Attribution


|Task|Name|Description|
|----|----|-----------|
|MediaType|Tyler|Wrote MediaType class and all methods|
|Song|Tyler|Wrote Song class and implemented all methods|
|Movie|Tyler|Wrote Movie class and methods|
|Node|Tyler|Wrote generic Node class and all methods|
|Playlist|Tyler|Wrote generic linked chain class and methods, gemini used for shuffle method|
|ShuffleBoxRunner|Tyler|Wrote class and methods, gemini used for forceClear method|
|SongTest|Tyler|Wrote unit tests for Song|
|MovieTest|Ethan|Wrote unit tests for Movie|
|NodeTest|Tyler|Wrote unit tests for Node|
|PlaylistTest|Tyler|Wrote unit tests for Playlist|
|ShuffleBoxRunnerTest|Tue|Wrote unit tests for ShuffleBoxRunner|

# ShuffleBox

ShuffleBox is a console-based Java application designed to manage, play, shuffle, and track your music playlist using a type-safe generic linked chain data structure. The app pulls random songs to play and can keep track of which artists you've been listening to the most.

# How to compile and run



# Class Design:

ShuffleBoxRunner - the main execution class. It prompts the user via a text-based console menu, handles File I/O for saving/loading history, manages user inputs for songs, and calculates the frequency of specific artists.

Playlist - collection class containing all song nodes in a linked chain. contains linked chain operations and shuffle method.

Node<T> - generic node that stores data of any reference type and pointer to next node.

Song - Represents an individual song object to be added to linked chain. Contains the title, artist, and runtime length in seconds, as well as usual getter/setter methods.


Scoping document: https://docs.google.com/document/d/1BeiDmwNcfFkO5ODyWj60uXFrrqvxOizMH2Rcr6rEW_c/edit?usp=sharing

specification document: https://docs.google.com/document/d/1TaegGIvULI59V0a3JnqMK7TGCHGAAeYVdnexLCKcNiU/edit?usp=sharing
