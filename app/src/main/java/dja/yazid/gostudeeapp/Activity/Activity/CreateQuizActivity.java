package dja.yazid.gostudeeapp.Activity.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import dja.yazid.gostudeeapp.POJO.Quiz;
import dja.yazid.gostudeeapp.POJO.QuizItem;
import dja.yazid.gostudeeapp.R;

/**
 * Created by yazid on 18/04/2018.
 */

public class CreateQuizActivity extends AppCompatActivity {
    FirebaseFirestore db;
    Quiz quiz;
    QuizItem quizItem;
    Button buttonValidate;
    EditText editTextSubject;
    EditText editTextDescription;
    EditText editTextQuestion;
    EditText editTextAnswer1;
    EditText editTextAnswer2;
    EditText editTextAnswer3;
    EditText editTextAnswer4;
    EditText editTextRealAnswer;
    boolean quizcreated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_quiz);

        db = FirebaseFirestore.getInstance();
        buttonValidate = findViewById(R.id.buttonValidate);
        editTextSubject = findViewById(R.id.subject);
        editTextQuestion = findViewById(R.id.question);
        editTextDescription = findViewById(R.id.description);
        editTextAnswer1 = findViewById(R.id.answer1);
        editTextAnswer2 = findViewById(R.id.answer2);
        editTextAnswer3 = findViewById(R.id.answer3);
        editTextAnswer4 = findViewById(R.id.answer4);
        editTextRealAnswer = findViewById(R.id.realAnswer);
        quizcreated = false;
        final int i[] = new int[1];
        i[0] = 1;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        changeStatusBarColor("#0073AF");

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!quizcreated){
                    String subject = editTextSubject.getText().toString();
                    quiz = new Quiz(subject, subject, editTextDescription.getText().toString());
                    db.collection("Quiz").document(quiz.getId()).set(quiz).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    });
                }
                quizItem = new QuizItem(editTextQuestion.getText().toString(),
                                        editTextAnswer1.getText().toString(),
                                        editTextAnswer2.getText().toString(),
                                        editTextAnswer3.getText().toString(),
                                        editTextAnswer4.getText().toString(),
                                        editTextRealAnswer.getText().toString());
                db.collection("Question").document(quiz.getId()).collection("Question").document("question " + i[0]).set(quizItem);
                i[0]++;
                Toast.makeText(getBaseContext(), "Succès",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeStatusBarColor(String color){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(color));
    }
}

