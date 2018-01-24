package com.example.khalessi.gretas_vokabeltrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalessi.gretas_vokabeltrainer.exercise.IExercise;
import com.example.khalessi.gretas_vokabeltrainer.exercise.LevelOneExercise;

public class LevelOneActivity extends AppCompatActivity {

    private IExercise loe = new LevelOneExercise();
    private String[] words;
    private String[] nativeLang;
    private int solutionIdx;

    // views
    Button[] buttons = new Button[4];
    private TextView tv_LevelOneMutterSprache;
    private Button btn_LevelOne_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        tv_LevelOneMutterSprache = (TextView) findViewById(R.id.tv_LevelOneMutterSprache);

        buttons[0] = (Button) findViewById(R.id.btn_LevelOne_word0);
        buttons[1] = (Button) findViewById(R.id.btn_LevelOne_word1);
        buttons[2] = (Button) findViewById(R.id.btn_LevelOne_word2);
        buttons[3] = (Button) findViewById(R.id.btn_LevelOne_word3);

        btn_LevelOne_next = (Button) findViewById(R.id.btn_LevelOne_next);

        erzeugeUebung();
        setTexts();

        registerCallback();
    }

    private void setTexts() {
        tv_LevelOneMutterSprache.setText(nativeLang[solutionIdx]);
        buttons[0].setText(words[0]);
        buttons[1].setText(words[1]);
        buttons[2].setText(words[2]);
        buttons[3].setText(words[3]);

        buttons[solutionIdx].setText(buttons[solutionIdx].getText() + " <--");
    }

    private void registerCallback() {
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResults(loe.checkResult(0));
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResults(loe.checkResult(1));
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResults(loe.checkResult(2));
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResults(loe.checkResult(3));
            }
        });

        btn_LevelOne_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // nächste übung generieren
                erzeugeUebung();
                setTexts();
            }
        });
    }

    private void erzeugeUebung() {
        loe.generateExercise();
        words = loe.getWords();
        nativeLang = loe.getNativeLang();
        solutionIdx = loe.getSolutionIdx();
    }


    private void checkResults(boolean val) {
        if (val) {
            Toast.makeText(getApplicationContext(), "richtig", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "falsch", Toast.LENGTH_SHORT).show();
        }
    }

}
