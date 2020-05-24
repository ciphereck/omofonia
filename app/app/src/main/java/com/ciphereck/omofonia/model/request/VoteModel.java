package com.ciphereck.omofonia.model.request;

public class VoteModel {
    final String authHeader;
    final String electionId;
    final String candidateId;

    public VoteModel(String authHeader, String electionId, String candidateId) {
        this.authHeader = authHeader;
        this.electionId = electionId;
        this.candidateId = candidateId;
    }
}
