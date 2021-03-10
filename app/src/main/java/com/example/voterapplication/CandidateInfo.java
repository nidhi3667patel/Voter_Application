package com.example.voterapplication;

import java.io.Serializable;

public class CandidateInfo implements Serializable {
    public CandidateInfo(int candidate_id, String candidate_name, int votes) {
        this.candidate_id = candidate_id;
        this.candidate_name = candidate_name;
        this.votes = votes;
    }

    private int candidate_id;
    private String candidate_name;
    private int votes;

    public int getId() {
        return candidate_id;
    }

    public void setId(int candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getName() {
        return candidate_name;
    }

    public void setName(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return candidate_name;
    }
}

