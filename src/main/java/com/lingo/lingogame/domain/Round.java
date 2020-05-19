package com.lingo.lingogame.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int index;

    private String guess;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "round", cascade = CascadeType.PERSIST)
    private List<Feedback> feedbackList;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    public Round() {
    }

    public Round(String guess, String correct) {
        this.guess = guess;
        this.feedbackList = this.calculateFeedback(guess, correct);
    }

    private List<Feedback> calculateFeedback(String guess, String correct) {
        List<Feedback> result = new ArrayList<>();

        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char correctChar = correct.charAt(i);

            if (guessChar == correctChar) {
                result.add(new Feedback(i, guessChar, FeedbackType.CORRECT, this));
                correct = correct.replaceFirst(String.valueOf(guessChar), "!");
            } else if (correct.indexOf(guessChar) > -1) {
                result.add(new Feedback(i, guessChar, FeedbackType.WRONG_PLACE, this));
                correct = correct.replaceFirst(String.valueOf(guessChar), "!");
            } else {
                result.add(new Feedback(i, guessChar, FeedbackType.NOT_IN_WORD, this));
            }
        }

        return result;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public int getIndex() {
        return index;
    }

    public String getGuess() {
        return guess;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}