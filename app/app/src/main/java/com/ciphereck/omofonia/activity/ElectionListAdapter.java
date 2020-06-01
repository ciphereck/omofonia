package com.ciphereck.omofonia.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.model.election.Election;

import java.util.List;

public class ElectionListAdapter extends ArrayAdapter<Election> {
    private List<Election> electionList;
    Context context;

    public ElectionListAdapter(List<Election> electionList, Context context) {
        super(context, R.layout.election_element);
        this.context = context;
        this.electionList = electionList;
    }

    @Override
    public int getCount() {
        return electionList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Election election = electionList.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.election_element, null, true);

        TextView electionId = view.findViewById(R.id.electionId);
        TextView electionName = view.findViewById(R.id.electionName);

        electionId.setText(election.getId());
        electionName.setText(election.getElectionName());

        view.setOnClickListener(v -> {
            if(election.getCandidateList() == null) return;
            Intent intent = new Intent(context, CandidateActivity.class);
            intent.putExtra("ELECTION_INFO", electionList.get(position));
            context.startActivity(intent);
        });

        return view;
    }
}