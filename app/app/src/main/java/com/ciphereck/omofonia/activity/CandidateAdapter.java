package com.ciphereck.omofonia.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.model.election.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateAdapter extends ArrayAdapter<Candidate> {
    private List<Candidate> candidateList;
    Context context;
    private Integer selectedCandidate = -1;
    List<View> views;

    public CandidateAdapter(List<Candidate> candidateList, Context context) {
        super(context, R.layout.candidate);
        this.context = context;
        this.candidateList = candidateList;
        views = new ArrayList<>();
        selectedCandidate = candidateList.size();
    }

    @Override
    public int getCount() {
        return candidateList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Candidate candidate = candidateList.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.candidate, null, true);

        TextView candidateName = view.findViewById(R.id.candidateName);
        TextView partyName = view.findViewById(R.id.partyName);

        candidateName.setText(candidate.getName());
        partyName.setText(candidate.getParty().getName());

        view.setOnClickListener(v -> {
            selectedCandidate = position;
            System.out.println(position);
        });

        views.add(view);

        return view;
    }

    public Integer getSelectedCandidate() {
        return selectedCandidate;
    }
}
