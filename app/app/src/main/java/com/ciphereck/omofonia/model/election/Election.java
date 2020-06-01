package com.ciphereck.omofonia.model.election;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Election implements Serializable {
    @SerializedName("name")
    String electionName;
    @SerializedName("candidate")
    List<Candidate> candidateList;
    @SerializedName("id")
    String id;
    @SerializedName("status")
    String status;
    @SerializedName("totalVotesCasted")
    String totalVotesCasted;

    public Election(String ID) {
        electionName = "Default Name";
        candidateList = new ArrayList<>();
        id = ID;
        status = "0";
        totalVotesCasted = "0";
    }

    public Election(List<Candidate> candidateList, String id, String status, String totalVotesCasted, String electionName) {
        this.candidateList = candidateList;
        this.id = id;
        this.status = status;
        this.totalVotesCasted = totalVotesCasted;
        this.electionName = electionName;
    }

    public String getElectionName() {
        return electionName;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getTotalVotesCasted() {
        return totalVotesCasted;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalVotesCasted(String totalVotesCasted) {
        this.totalVotesCasted = totalVotesCasted;
    }

    public void addCandidate(Candidate candidate) {
        candidateList.add(candidate);
    }
}
