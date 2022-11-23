# Card Game

## Introduction

This game is a pair-programming project that uses a multi-threading interface to run a card game with an amount of players n, until one of the players has a hand of the same four cards.

### How to use the project

CardGame contains the main function where all the threads are started and where the functionality begins, with player also starting threads for every player entered by the user.

Deck and Card classes contain arraylists with the cards used for the game. The game state is used to check if the game has finsished.

### Testing

The test are refered to the classes used one test class per class used with Junit tests and assertions used for testing.

- **CardGameTest**
- **CardTest**
- **DeckTest**
- **GameStateTest**
- **PlayerTest**  

### Running

To run the game run **jar file**, enter number of **players** and location of the **pack file** to use; sample packfile given.

### File Management

- ECM2414-CARD-GAME
  - ECM2414
    - Card.java
    - CardGame.java
    - Deck.java
    - GameState.java
    - InvalidPackException.java
    - Player.java
    - CardGameTest.java
    - CardTest.java
    - DeckTest.java
    - GameStateTest.java
    - PlayerTest.java
  - .gitattributes
  - five.txt
  - four.txt
  - README.md

## Details

- Project Created by **Luis Hidalgo** and **George Hynes**
- Github repostery: **<https://github.com/lhidalgogt2003/ECM2414-card-game>**
- pack file: **four.txt**, **five.txt**
