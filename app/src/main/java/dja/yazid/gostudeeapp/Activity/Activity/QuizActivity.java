package dja.yazid.gostudeeapp.Activity.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collections;

import dja.yazid.gostudeeapp.POJO.QuizItem;
import dja.yazid.gostudeeapp.R;

/**
 * Created by yazid on 18/04/2018.
 */

public class QuizActivity extends AppCompatActivity {
    FirebaseFirestore db; // référence à la racine de la base de données
    TextView subject, description, questionText;
    ArrayList<QuizItem> quizItems; // les quiz récupérer de la base de données firestore
    Button buttonA, buttonB, buttonC, buttonD;
    QuizItem currentQuestion; // stock la quesiton courante
    int actualQuestion; // numéro de la question courante
    int numberOfGoodAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);
        quizItems = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        subject = findViewById(R.id.subject);
        description = findViewById(R.id.description);
        questionText = findViewById(R.id.question);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        actualQuestion = 0;
        numberOfGoodAnswer = 0;

        enableButton();

        final Intent intent = getIntent();
        subject.setText(intent.getStringExtra("subject"));
        description.setText(intent.getStringExtra("description"));


        db.collection("Question").document(intent.getStringExtra("id")).collection("Question").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) { // récupérer les questions dans firestore
                int i =0;
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        quizItems.add(document.toObject(QuizItem.class)); // remplie la liste quizItems des différentes questions de firestore
                    }

                    Collections.shuffle(quizItems); // rend aléatoire les questions
                    currentQuestion = quizItems.get(actualQuestion);
                    updateValueOfButton();

                } else {

                }
            }
        });


        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //compare the option with the ans if yes then make button color green
                if (currentQuestion.getAnswerA().equals(currentQuestion.getGoodAnswer())) {
                    buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorGreen));
                    numberOfGoodAnswer++;
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        correctDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        startActivity(intent1);
                    }
                } else {
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        incorrectDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        intent1.putExtra("numberOfQuestion", quizItems.size());
                        startActivity(intent1);
                    }
                }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //compare the option with the ans if yes then make button color green
                if (currentQuestion.getAnswerB().equals(currentQuestion.getGoodAnswer())) {
                    buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorGreen));
                    numberOfGoodAnswer++;
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        correctDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        startActivity(intent1);
                    }
                } else {
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        incorrectDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        intent1.putExtra("numberOfQuestion", quizItems.size());
                        startActivity(intent1);
                    }
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //compare the option with the ans if yes then make button color green
                if (currentQuestion.getAnswerC().equals(currentQuestion.getGoodAnswer())) {
                    buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorGreen));
                    numberOfGoodAnswer++;
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        correctDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        startActivity(intent1);
                    }
                } else {
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        incorrectDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        intent1.putExtra("numberOfQuestion", quizItems.size());
                        startActivity(intent1);
                    }
                }
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //compare the option with the ans if yes then make button color green
                if (currentQuestion.getAnswerD().equals(currentQuestion.getGoodAnswer())) {
                    buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorGreen));
                    numberOfGoodAnswer++;
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        correctDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        startActivity(intent1);
                    }
                } else {
                    if(actualQuestion < quizItems.size() - 1){ // on vérifie que l'utilisateur n'a pas dépasser le nombre de question
                        disableButton();
                        incorrectDialog();
                    } else {
                        Intent intent1 = new Intent(getBaseContext(), EndOfQuizActivity.class);

                        intent1.putExtra("goodAnswer", numberOfGoodAnswer);
                        intent1.putExtra("numberOfQuestion", quizItems.size());
                        startActivity(intent1);
                    }
                }
            }
        });

    }

    //This dialog is show to the user after he ans correct
    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(QuizActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = dialogCorrect.findViewById(R.id.correctText);
        Button buttonNext = dialogCorrect.findViewById(R.id.dialogNext);


        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                actualQuestion++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = quizItems.get(actualQuestion);
                //Now this method will set the new que and 4 options
                updateValueOfButton();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }

    //This dialog is show to the user after he ans correct
    public void incorrectDialog() {
        final Dialog dialogCorrect = new Dialog(QuizActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_incorrect);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = dialogCorrect.findViewById(R.id.correctText);
        TextView correctAnswer = dialogCorrect.findViewById(R.id.correctAnswer);
        Button buttonNext = dialogCorrect.findViewById(R.id.dialogNext);

        correctAnswer.setText(currentQuestion.getGoodAnswer());

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                actualQuestion++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = quizItems.get(actualQuestion);
                //Now this method will set the new que and 4 options
                updateValueOfButton();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }

    private void changeStatusBarColor(String color){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(color));
    }

    public void updateValueOfButton() {

        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());

        if(currentQuestion.getAnswerA().equals("")){
            buttonA.setVisibility(View.GONE);
        } else {
            buttonA.setVisibility(View.VISIBLE);
            buttonA.setText(currentQuestion.getAnswerA());
        }
        if(currentQuestion.getAnswerB().equals("")){
            buttonB.setVisibility(View.GONE);
        } else {
            buttonB.setVisibility(View.VISIBLE);
            buttonB.setText(currentQuestion.getAnswerB());
        }
        if(currentQuestion.getAnswerC().equals("")){
            buttonC.setVisibility(View.GONE);
        } else {
            buttonC.setVisibility(View.VISIBLE);
            buttonC.setText(currentQuestion.getAnswerC());
        }
        if(currentQuestion.getAnswerD().equals("")){
            buttonD.setVisibility(View.GONE);
        } else {
            buttonD.setVisibility(View.VISIBLE);
            buttonD.setText(currentQuestion.getAnswerD());
        }




    }

    public void resetColor() {
        buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
        buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
        buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
        buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
    }

    //This method will disable all the option button
    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    //This method will all enable the option buttons
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }
}

