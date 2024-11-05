# Fumo Gacha Simulator

This is a simple Java program that simulates a gacha system for collecting Fumo plushies. 

## Features

* **Four rarity levels:** N (Normal), R (Real), SR (Super Rare), SSR (Super Special Rare)
* **Guaranteed SR pull every 10 pulls** 
* **Guaranteed SSR pull every 60 pulls**
* **Pity system** that increases the chances of getting rarer items the more you pull

## How to Play

1. **Compile and run the Main.java file.**
2. **Enter an amount of money between $1 and $10 to add to your balance.** Each dollar allows for one pull.
3. **Choose whether to pull from the gacha pool.**
4. **The program will display the item you pulled and its rarity.**
5. **The game continues until you choose to stop adding money.**

## Gacha Pool

The gacha pool includes the following Fumos:

* **Fumbo (N)**
* **Mass-produced Bootleg Fumo (R)**
* **Decent Custom Fumo (SR)**
* **A Real Genuine Fumo (SSR)**

## Timeline 


*   **March 15th 2024:** Initial development of the Fumo Gacha program begins. 
*   **April 5th 2024:** Core gacha mechanics, including rarity levels, pity system, and guaranteed pulls, are implemented. 
*   **April 20th 2024:** User interface using `JOptionPane` is added, allowing for user interaction.
*   **May 1st 2024:** Testing and debugging completed for ver 1.0.0.
*   **September 12th 2024:** In progress implimentation of a SQL database for images for the gacha. Stored in txt doc.
*   **Nov 5th 2024:** Transfering code to this GitHub.

## Code Overview

The program uses several classes to implement the gacha system:

* **`Gacha` class:** Represents a single gacha item with a name and rarity. [1, 2]
* **`GachaSystem` class:** Manages the gacha pool, pulls, pity system, and balance. [3-13]
* **`Main` class:** Contains the main method and handles user interaction. [14-18]

The code utilizes the `ArrayList`, `Random`, and `JOptionPane` classes for data storage, random number generation, and GUI input/output, respectively. [1-3, 14]

## Example Gameplay

Enter $1 to $10 to add to your balance (or 0 to stop): 10 Total pulls during this session: 10 Your money has turned into the amount of pulls you can pull for. You have 10 pulls left. Would you like to pull from the gacha pool? Yes You pulled: Fumbo (Rarity: Normal!) You pulled: Mass produced Bootleg Fumo (Rarity: Real!) You pulled: Mass produced Bootleg Fumo (Rarity: Real!) You pulled: Fumbo (Rarity: Normal!) You pulled: A Real Genuine Fumo (Rarity: Super Special Rare!) You pulled: Mass produced Bootleg Fumo (Rarity: Real!) You pulled: Fumbo (Rarity: Normal!) You pulled: Decent Custom Fumo (Rarity: Super Rare!) You pulled: Fumbo (Rarity: Normal!) You pulled: Fumbo (Rarity: Normal!) Congratulations! You are guaranteed to get a SR in the next pull.

## Additional Note

The program currently is not functional with the SQLManager class. Please run without that class till full implmentation.

## Bugs
It is known and still is a issue with the constantly of the pulls shown to the user. Possibly the amount of money put in doesn't give a 1 for 1 in terms of pulls.
Current bug testing is underway for this project for this issue.

## Disclaimer

This is a simple gacha simulation for fun. It does not involve real money or prizes. 

