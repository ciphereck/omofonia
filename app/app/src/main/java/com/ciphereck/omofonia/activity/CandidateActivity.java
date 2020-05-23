package com.ciphereck.omofonia.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.model.election.Election;

public class CandidateActivity extends AppCompatActivity {
    Election election;
    ListView candidateListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        candidateListView = findViewById(R.id.candidateList);

        election = (Election) getIntent().getSerializableExtra("ELECTION_INFO");

        CandidateAdapter adapter = new CandidateAdapter(election.getCandidateList(), this);
        candidateListView.setAdapter(adapter);
    }
}
