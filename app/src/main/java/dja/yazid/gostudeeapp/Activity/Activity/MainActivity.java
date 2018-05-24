package dja.yazid.gostudeeapp.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import dja.yazid.gostudeeapp.Adapter.QuizAdapter;
import dja.yazid.gostudeeapp.POJO.Quiz;
import dja.yazid.gostudeeapp.POJO.QuizItem;
import dja.yazid.gostudeeapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener { // entrer tous les questionnaires et ensuite supprimer les var inutiles

    RecyclerView recyclerView;
    Toolbar toolbar;
    FirebaseFirestore db;
    Quiz quiz;
    ArrayList<Quiz> quizArrayList;
    QuizItem quizItem;
    ArrayList<String> quizitemsAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        int resId = R.anim.layout_animation_fall_down;
        final LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        toolbar = findViewById(R.id.toolbar);
        db = FirebaseFirestore.getInstance();
        quizArrayList = new ArrayList<>();
        quizitemsAnswer = new ArrayList<>();
        quiz = new Quiz("BETABTETATTBEA", "Mathématiques", "Faites des mathématiques");

        quizitemsAnswer.add("11111");
        quizitemsAnswer.add("trhrth1111");
        quizitemsAnswer.add("1rtjhyj1");
        quizitemsAnswer.add("1jyrjryy11");

        quizItem = new QuizItem("Qui a inventé le passe bas", "Moi", "Toi", "Eux", "Nous", "Moi");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db.collection("Quiz").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        quizArrayList.add(document.toObject(Quiz.class));
                    }
                } else {

                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext()); // Recycler view
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(new QuizAdapter(getBaseContext(), quizArrayList));
                recyclerView.setLayoutAnimation(animation);
            }
        });

        Button buttonAbout = findViewById(R.id.buttonAbout); // bouton du menu latéral gauche, (test)

        buttonAbout.setOnClickListener(new View.OnClickListener() { // bouton à propos dans le menu (navigation drawer)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCreateAQuiz = findViewById(R.id.buttonAddQuiz);

        buttonCreateAQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CreateQuizActivity.class);
                startActivity(intent);
            }
        });

        /*db.collection("Quiz").document(quiz.getId()).set(quiz).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });


        db.collection("Quiz").document(quiz.getId()).set(quiz);
        for(int i = 1; i < 10; i++){
            db.collection("Question").document(quiz.getId()).collection("Question").document("question " + i).set(quizItem);
        }*/



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favoris) {
            // Handle the camera action
        } else if (id == R.id.nav_apropos) {

            Intent intentApropos = new Intent(getBaseContext(), AboutActivity.class);
            startActivity(intentApropos);

        } else if (id == R.id.nav_aide) {

        } else if (id == R.id.nav_parametres) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
