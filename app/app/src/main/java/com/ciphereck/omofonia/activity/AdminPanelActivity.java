package com.ciphereck.omofonia.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.model.election.Candidate;
import com.ciphereck.omofonia.model.election.Election;
import com.ciphereck.omofonia.model.election.Party;
import com.ciphereck.omofonia.retrofit.helper.ElectionRoutesHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AdminPanelActivity extends AppCompatActivity {
    Spinner dropdown;
    Spinner candidateDropDown;
    Button createElection;
    List<Election> electionList = new ArrayList<>();
    Button updateElection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        dropdown = findViewById(R.id.spinner1);
        candidateDropDown = findViewById(R.id.spinner);

        ElectionRoutesHelper
                .getAllElectionData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(electionList1 -> {
                    for(int i=0; i<electionList1.size(); i++) {
                        if(electionList1.get(i)!=null)
                            electionList.add(electionList1.get(i));
                    }
                    updateSpinner();
                }, err -> System.out.println(err));

        createElection = findViewById(R.id.button6);
        createElection.setOnClickListener(v -> {
            electionList.add(new Election("" +electionList.size()));
            updateSpinner();
        });

        updateElection = findViewById(R.id.button8);
        updateElection.setOnClickListener(v -> {
            ElectionRoutesHelper
                    .updateElection(electionList)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(electionResponseModel -> {
                        System.out.println(electionResponseModel);
                    }, err -> System.out.println(err));
        });
    }

    public void updateSpinner() {
        List<String> electionId = new ArrayList<>();
        for(int i=0; i<electionList.size(); i++) {
            if(electionList.get(i) != null) {
                electionId.add(electionList.get(i).getElectionName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, electionId);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((Button)findViewById(R.id.button7)).setOnClickListener(v -> {
                    electionList.get(position).addCandidate(new Candidate("", "Dummy Name", new Party("Dummy Party", "URL", "URL"), "URL"));
                    updateSpinner();
                });
                updateCandidateSpinner(position);
                ((EditText)findViewById(R.id.editText)).setText(electionList.get(position).getElectionName());
                ((EditText)findViewById(R.id.editText2)).setText(electionList.get(position).getStatus());
                ((EditText)findViewById(R.id.editText2)).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        electionList.get(position).setStatus(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                ((EditText)findViewById(R.id.editText)).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        electionList.get(position).setElectionName(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void updateCandidateSpinner(int pos)
    {
        List<String> candidateId = new ArrayList<>();
        for(int i=0; i<electionList.get(pos).getCandidateList().size(); i++) {
            if(electionList.get(pos).getCandidateList().get(i) != null) {
                candidateId.add(electionList.get(pos).getCandidateList().get(i).getName());
            }
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, candidateId);
        candidateDropDown.setAdapter(adapter2);

        candidateDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((EditText)findViewById(R.id.editText3)).setText(electionList.get(pos).getCandidateList().get(position).getName());
                ((EditText)findViewById(R.id.editText9)).setText(electionList.get(pos).getCandidateList().get(position).getPicUrl());
                ((EditText)findViewById(R.id.editText10)).setText(electionList.get(pos).getCandidateList().get(position).getParty().getName());
                ((EditText)findViewById(R.id.editText11)).setText(electionList.get(pos).getCandidateList().get(position).getParty().getPartySign());
                addListener(pos, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addListener(int pos, int position) {
        ((EditText)findViewById(R.id.editText3)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                electionList.get(pos).getCandidateList().get(position).setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ((EditText)findViewById(R.id.editText9)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                electionList.get(pos).getCandidateList().get(position).setPicUrl(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ((EditText)findViewById(R.id.editText10)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                electionList.get(pos).getCandidateList().get(position).getParty().setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ((EditText)findViewById(R.id.editText11)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                electionList.get(pos).getCandidateList().get(position).getParty().setPartyUrl(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
