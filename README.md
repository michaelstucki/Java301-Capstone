# FlashcardApp

FlashcardApp is a spaced repetition-enabled application that enables a user to create an account and within the account to create and manage decks of flashcards and to drill them. Multiple users can use the app on the same computer, each having their own account.

## Installation (MacOS Only)

Double-click the .dmg to install.

## Usage

Double-click installed app from Launchpad. From the landing screen, user can create an account (or login or recover their password).
Once logged in, user can delete their account or change their password, view their decks, create and rename decks.
User can also open a deck to view cards in that deck and add, delete, and edit cards in that deck. Finally, user can drill cards in a deck.

## Spaced Repetition

While drilling cards in a deck, user is presented with the front side of a card. When the card is clicked, its back side is shown. User decides whether they got it correct and then presses either *Pass* or *Fail* accordingly. Failed cards are shown again. Passed cards are not shown again. A passed card advances to the next Leitner box and its due date is set based on the box in which it resides. A failed card returns to the first box.

The Leitner boxes are 0-indexed, the cards in a box have due dates set according to a $2^n$ progression, where *n* represents the Leitner box index.



