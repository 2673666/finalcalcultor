package com.example.finalcalcultor;

public class Course {
    private String name;
    private double score;
    private double credit;

    public Course(String name, double score, double credit) {
        this.name = name;
        this.score = score;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public double getCredit() {
        return credit;
    }
}