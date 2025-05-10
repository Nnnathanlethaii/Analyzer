package com.example.seoanalyzer;

import java.util.ArrayList;
import java.util.List;

public class ScoreReport {
    private int score = 0;
    private final List<String> details = new ArrayList<>();

    public void addPoints(int pts, String reason){
        score += pts;
        details.add(String.format("-%d %s", pts, reason));
    }

    public void subtractPoints(int pts, String reason){
        score -= pts;
        details.add(String.format("-%d %s", pts, reason));
    }

    public int getScore() {
        return score;
    }

    public List<String> getDetails() {
        return details;
    }
}
