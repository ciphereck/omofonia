package com.ciphereck.omofonia.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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
    List<Election> electionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        dropdown = findViewById(R.id.spinner1);

        ElectionRoutesHelper
                .getAllElectionData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(electionList1 -> {
                    electionList = electionList1;
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
    }

}
