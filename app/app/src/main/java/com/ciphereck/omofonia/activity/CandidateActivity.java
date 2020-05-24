package com.ciphereck.omofonia.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.managers.UserManager;
import com.ciphereck.omofonia.model.User;
import com.ciphereck.omofonia.model.election.Election;
import com.ciphereck.omofonia.model.request.VoteModel;
import com.ciphereck.omofonia.retrofit.helper.ElectionRoutesHelper;

public class CandidateActivity extends AppCompatActivity {
    Election election;
    ListView candidateListView;
    Button vote;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        user = UserManager.getInstance();

        candidateListView = findViewById(R.id.candidateList);
        vote = findViewById(R.id.vote);

        election = (Election) getIntent().getSerializableExtra("ELECTION_INFO");

        CandidateAdapter adapter = new CandidateAdapter(election.getCandidateList(), this);
        candidateListView.setAdapter(adapter);

        vote.setOnClickListener(v -> {
            String position = adapter.getSelectedCandidate().toString();
            if(position.equals(election.getCandidateList().size())) return;
            String electionId = election.getId();
            String authHeader = user.getToken();
            ElectionRoutesHelper
                    .vote(new VoteModel(authHeader, electionId, position))
                    .subscribe(jsonElement -> {
                        System.out.println(jsonElement);
                        Toast toast = Toast.makeText(this, "voted", Toast.LENGTH_LONG);
//                        toast.setText("voted");
                        toast.show();
                        super.onBackPressed();
                    }, err -> System.out.println(err));
        });
    }
}
