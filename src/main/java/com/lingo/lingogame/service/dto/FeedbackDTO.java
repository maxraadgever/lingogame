package com.lingo.lingogame.service.dto;

import com.lingo.lingogame.domain.Feedback;
import com.lingo.lingogame.domain.FeedbackType;

public class FeedbackDTO {

    private int index;
    private char character;
    private FeedbackType feedbackType;

    public FeedbackDTO(Feedback feedback) {
        this.index = feedback.getIndex();
        this.character = feedback.getCharacter();
        this.feedbackType = feedback.getFeedbackType();
    }

    public int getIndex() {
        return index;
    }

    public char getCharacter() {
        return character;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }
}
