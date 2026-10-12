CS 2114 - Project 2 - Group 67

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
