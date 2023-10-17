# Game Rules
Capitalism is a game simulation of a four-player version of a trick-playing card game.

## Equipment:
Capitalism is played using a standard playing deck of 52 cards with no jokers. The cards need to be initially shuffled.

## Game Play:
At the start of every round, all cards are evenly distributed to each player. For the first trick, a player is selected at random to start. Players then take turns in a clockwise manner, adding cards on top of the trick, playing cards that have the same value or higher than the previous card played. If a card is played that has the same value as the previous card in the trick, the next player is skipped, and they do not get to add a card to the trick. The cards are ranked, with "ace" being the highest card and "three" being the lowest card. "Two" cards are bombs, which means they clear the trick immediately. A trick is cleared when a bomb is played or when no player chooses to play a higher card. The player who played the last card in the trick starts the next trick with a card of their choice. A round ends when the last player runs out of cards. The first player out of cards is the "President" and earns three points to their total score. The second player out is the "Vice President" and earns two points towards their score. The third player out is the "Middle Man" and earns one point towards their score. The last player out is the "Bum" and earns zero points towards their score. Cards are collected, randomized, and distributed equally for the next round. A new round starts with the president giving their two lowest cards to the bum and the bum giving their two highest cards to the president. After this is done, the president plays a card of their choice and starts the first trick. Play continues as usual.

## Strategies:
There are four distinct strategies that the simulation players can employ.

### Lowest-Card First
The player will play the lowest card possible at every opportunity on the trick, while saving their bomb cards until the very end. The player will compare the cards in their hand to the card last played in the trick, then play their lowest card that is greater than or equal to the last card played in the trick.

### Highest-Card First
The player will play the highest card possible at every opportunity on the trick, playing their bomb card first. The player will compare the cards in their hand to the card last played in the trick, then play their highest card that is greater than or equal to the last card played in the trick.

### Bomb First, then Lowest-Card First
The player will play the bomb cards in their hand first, then the rest of their cards from lowest possible to highest possible. After playing all the bomb cards in their hand, the player will compare the cards in their hand to the cards last played in the trick, then play their lowest card that is greater than or equal to the last card played in the trick.

### Maximize Skipping
The player will pass on their turn and not play a card unless they can match the last card played on the trick, skipping the next player in the queue. The player will compare the last card played in the trick to the cards in their hand and only play a card if the value of the card matches the value of the last card played in the trick.

## Objective:

A Player wins the game when they have accumulated 10 points total.