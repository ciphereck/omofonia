package com.ciphereck.omofonia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.model.election.Election;
import com.ciphereck.omofonia.retrofit.helper.ElectionRoutesHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class AdminPanelActivity extends AppCompatActivity {
    Spinner dropdown;
    Spinner candidateDropDown;
    List<Election> electionList = new ArrayList<>();

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
                ((EditText)findViewById(R.id.editText)).setText(electionList.get(position).getElectionName());
                ((EditText)findViewById(R.id.editText2)).setText(electionList.get(position).getStatus());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
