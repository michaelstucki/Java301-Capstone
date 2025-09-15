package com.michaelstucki.java301capstone.dto;

import static com.michaelstucki.java301capstone.constants.Constants.*;
import java.time.LocalDate;

public class Card {
    private int id;
    private String front;
    private String back;
    private LocalDate creationDate;
    private LocalDate reviewedDate;
    private LocalDate dueDate;
    private int leitnerBox;
    private int numberOfReviews;
    private int numberOfPasses;

    public Card(String front, String back) {
        this.front = front;
        this.back = back;
        creationDate = LocalDate.now();
    }

    public Card(int id, String front, String back, LocalDate creationDate, LocalDate reviewedDate,
                LocalDate dueDate, int leitnerBox, int numberOfReviews, int numberOfPasses) {
        this.front = front;
        this.back = back;
        setId(id);
        setCreationDate(creationDate);
        setReviewedDate(reviewedDate);
        setDueDate(dueDate);
        setLeitnerBox(leitnerBox);
        setNumberOfReviews(numberOfReviews);
        setNumberOfPasses(numberOfPasses);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

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

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getReviewedDate() {
        return reviewedDate;
    }
    public void setReviewedDate(LocalDate reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
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
    public String toString() { return front + cardToken + back; }
}