package com.ciphereck.omofonia.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.model.election.Election;
import com.ciphereck.omofonia.retrofit.helper.ElectionRoutesHelper;

import java.util.List;

public class ElectionActivity extends AppCompatActivity {
    List<Election> electionList;
    ListView electionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);
        electionListView = findViewById(R.id.electionList);

        ElectionRoutesHelper
                .getAllElectionData()
                .subscribe(elections -> {
                    electionList = elections;
                    System.out.println(elections.toString());
                    ElectionListAdapter adapter = new ElectionListAdapter(electionList, this);
                    electionListView.setAdapter(adapter);
                }, err -> System.out.println(err));
    }
}
