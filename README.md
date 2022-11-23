# Card Game

## Introduction

This game is a pair-programming project that uses a multi-threading interface to run a card game with a number of players _n_, and a number of decks (also _n_). 
The decks and players form a ring topology. 
At the start of the game, each player is distributed four cards in a round-robin fashion, from the top of the pack.
After the hands have been dealt, the decks will then be filled from the remaining cards in the pack, again in a round-robin fashion.
### How to use the project

CardGame contains the main function where all the threads are started and where the functionality begins.

Deck and Card represent the obvious. The GameState object is used by players to check if the game has finished and, if so, who has won.

### Testing

The tests are designed for use with JUnit 5.7.0.

- **CardGameTest**
- **CardTest**
- **DeckTest**
- **GameStateTest**
- **PlayerTest**  

Run tests:
```
java -jar junit-platform-console-standalone-1.9.1.jar -cp ECM2414 --select-package tests
```

### Running

To run the game, run the **jar file**, enter number of **players** and location of the **pack file** to use - sample pack file provided.

### File Management

- ECM2414-CARD-GAME
  - ECM2414
    - Card.java
    - CardGame.java
    - Deck.java
    - GameState.java
    - InvalidPackException.java
    - Player.java
  - tests
    - CardGameTest.java
    - CardTest.java
    - DeckTest.java
    - GameStateTest.java
    - PlayerTest.java
  - five.txt
  - four.txt
  - README.md

## Details

- Project Created by **Luis Hidalgo** and **George Hynes**
- GitHub repository: **<https://github.com/lhidalgogt2003/ECM2414-card-game>**
- pack files: **four.txt**, **five.txt**
