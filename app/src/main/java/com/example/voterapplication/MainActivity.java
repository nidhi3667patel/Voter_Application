package com.example.voterapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<CandidateInfo> candidateArray;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button go_vote_btn;
        TextView candidate1 = findViewById(R.id.candidate1);
        TextView candidate2 = findViewById(R.id.candidate2);
        TextView candidate3 = findViewById(R.id.candidate3);
        go_vote_btn = findViewById(R.id.btn_go_vote);

        candidateArray = new ArrayList<CandidateInfo>();
        Intent intent = getIntent();

        ArrayList<CandidateInfo> candidates = (ArrayList<CandidateInfo>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidateArray.add(new CandidateInfo(1,"Tim Jonas",0));
            candidateArray.add(new CandidateInfo(2,"Calli Brown",0));
            candidateArray.add(new CandidateInfo(3,"Priyanka",0));
        }
        else{
            candidateArray = candidates;
        }

        candidate1.setText(candidateArray.get(0).getName()+"      : " + candidateArray.get(0).getVotes());
        candidate2.setText(candidateArray.get(1).getName()+"    : " + candidateArray.get(1).getVotes());
        candidate3.setText(candidateArray.get(2).getName()+"       : " + candidateArray.get(2).getVotes());

        go_vote_btn.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, VotingActivity.class);
            intent1.putExtra("candidates", candidateArray);
            startActivity(intent1);
        });


    }
}