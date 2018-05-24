package dja.yazid.gostudeeapp.Activity.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import dja.yazid.gostudeeapp.R;

/**
 * Created by yazid on 20/04/2018.
 */

public class EndOfQuizActivity extends AppCompatActivity {
    TextView goodAnswer, succes;
    Button revenirEnArriere;
    int numberOfGoodAnswer;
    int numberOfQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_of_quiz);

        Intent intent = getIntent();
        goodAnswer = findViewById(R.id.goodAnswer);
        succes = findViewById(R.id.succes);
        revenirEnArriere = findViewById(R.id.revenirEnArriere);

        numberOfGoodAnswer = intent.getIntExtra("goodAnswer", 0);
        numberOfQuestion = intent.getIntExtra("numberOfQuestion", 0);



        goodAnswer.setText("" + intent.getIntExtra("goodAnswer", 0));
        succes.setText("Bonne réponse");

        revenirEnArriere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent1);
    }


    private void changeStatusBarColor(String color){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(color));
    }
}