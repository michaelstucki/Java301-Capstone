# FlashcardApp

FlashcardApp is a spaced repetition application that enables a user to create an account and within the account to create and manage decks of flashcards and to drill them. Multiple users can use the app on the same computer, each user having their own account.

## Installation (MacOS Only)

Double-click the .dmg to install.

## Usage

Click installed app from Launchpad. From the landing screen, user can create an account, login, or recover their password.
Once logged in, user can delete their account, change their password, and view, create, and rename decks.
User can open a deck to view, add, delete, and edit cards in the deck. Finally, user can drill cards in a deck.

## Spaced Repetition

During a drill session, user is presented with the front side of a card. When the card is clicked, its back side is shown. User decides whether they got it correct and then presses either *Pass* or *Fail* accordingly. Failed cards are shown again in the session while passed cards are not. A passed card advances to the next Leitner box and its due date is set based on the box in which it resides. A failed card returns to the first box.

The Leitner boxes are 0-indexed (i.e., the first box has an index of 0, the second an index of 1, etc.) and the cards in a box have due dates set according to a $2^n$ progression, where *n* represents the Leitner box index. So, cards in box 0 are reviewed $2^0$ (1) day after arriving in box 0; cards in box 1 are reviewed $2^1$ (2) days after arriving in box 1, and so on.



