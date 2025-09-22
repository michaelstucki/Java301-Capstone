# FlashcardApp

FlashcardApp is a spaced repetition-enabled application that enables a user to create an account and within the account to create and manage decks of flashcards and to drill them. Multiple users can use the app on the same computer.

## Installation (MacOS Only)

Double-click the .dmg to install.

## Usage

Double-click installed app from Launchpad. From landing screen, user can create an account (or login and change or recover their password).
Once logged in, user can delete their account or change their password, and view their decks, create and rename decks, and add cards to their decks.
User can also view cards in their decks, and add, delete, and edit cards. Finally, user can drill cards in a deck.

## Spaced Repetition

While drilling cards in a deck, user is presented with the front side of a card. When they click the card, it toggles to show its back side. User then presses either *Pass* or *Fail*. Failed cards are shown again. Passed cards are not shown again. A passed card advances to the next Leitner box and its due date is set based on the box in which it resides. A failed card returns to the first box.

The Leitner boxes are 0-indexed, with the next review due date set according to a $2^n$ progression, where *n* represents the Leitner box index.



