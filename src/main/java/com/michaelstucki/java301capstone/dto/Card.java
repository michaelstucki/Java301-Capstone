package com.michaelstucki.java301capstone.dto;

import static com.michaelstucki.java301capstone.constants.Constants.cardToken;

public class Card {
    private int cardId;
    private String front;
    private String back;
    private String creationDate;
    private String reviewedDate;
    private String dueDate;
    private int leitnerBox;
    private int numberOfReviews;
    private int numberOfPasses;

    public Card(int cardId, String front, String back, String creationDate, String reviewedDate,
                String dueDate, int leitnerBox, int numberOfReviews, int numberOfPasses) {
        this.front = front;
        this.back = back;
        setId(cardId);
        setCreationDate(creationDate);
        setReviewedDate(reviewedDate);
        setDueDate(dueDate);
        setLeitnerBox(leitnerBox);
        setNumberOfReviews(numberOfReviews);
        setNumberOfPasses(numberOfPasses);
    }

    public int getId() {
        return cardId;
    }
    public void setId(int cardId) { this.cardId = cardId; }

    public String getFront() {
        return front;
    }
    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }
    public void setBack(String back) {
        this.back = back;
    }

    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getReviewedDate() {
        return reviewedDate;
    }
    public void setReviewedDate(String reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getLeitnerBox() {
        return leitnerBox;
    }
    public void setLeitnerBox(int leitnerBox) {
        this.leitnerBox = leitnerBox;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }
    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public int getNumberOfPasses() {
        return numberOfPasses;
    }
    public void setNumberOfPasses(int numberOfPasses) {
        this.numberOfPasses = numberOfPasses;
    }

    @Override
    public String toString() { return cardId + cardToken + front + cardToken + back; }
}