package com.example.voterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class VotingActivity extends AppCompatActivity {
    private Spinner candidate_spinner;
    ToggleButton toggle_btn;
    Button btn_vote;
    EditText voter_name, voter_id;
    private ArrayList<CandidateInfo> candidateArray;
    ArrayList<VoterDetails> votersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        votersArray = new ArrayList<VoterDetails>();
        candidate_spinner = findViewById(R.id.spinner_candidates);
        toggle_btn = findViewById(R.id.toggleButton);
        btn_vote = findViewById(R.id.btn_vote);
        voter_name = findViewById(R.id.input_name);
        voter_id = findViewById(R.id.input_ID);


        Intent intent = getIntent();
        ArrayList<CandidateInfo> candidates = (ArrayList<CandidateInfo>) intent.getSerializableExtra("candidates");
        candidateArray = candidates;
        ArrayAdapter<CandidateInfo> adapter = new ArrayAdapter<CandidateInfo>(this,
                android.R.layout.simple_spinner_item, candidateArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        candidate_spinner.setAdapter(adapter);

        btn_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(voter_name.getText().toString().isEmpty()){
                    Toast.makeText(VotingActivity.this, "Please fill the name field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(voter_id.getText().toString().isEmpty()){
                    Toast.makeText(VotingActivity.this, "Please fill the Id field", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (VoterDetails V : votersArray) {
                    if(V.getId() == Integer.parseInt(voter_id.getText().toString())){
                        Toast.makeText(VotingActivity.this, "Opps!! ID already present", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!toggle_btn.isChecked()){
                    Toast.makeText(VotingActivity.this, "Please accept the terms and condition first", Toast.LENGTH_SHORT).show();
                    return;
                }

                votersArray.add(new VoterDetails(Integer.parseInt(voter_id.getText().toString()), voter_name.getText().toString()));
                int selectedCandidateIndex = candidate_spinner.getSelectedItemPosition();
                CandidateInfo selectedCandidate = candidateArray.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(VotingActivity.this, "Your vote has been casted !!", Toast.LENGTH_SHORT).show();
            }
        });

        toggle_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    toggle_btn.setTextOn("Refuse");

                } else {

                    toggle_btn.setTextOff("Accept");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(VotingActivity.this, MainActivity.class);
        intent.putExtra("candidates", candidateArray);
        startActivity(intent);
    }
}