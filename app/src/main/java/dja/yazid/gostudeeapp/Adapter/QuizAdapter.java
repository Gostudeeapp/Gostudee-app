package dja.yazid.gostudeeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dja.yazid.gostudeeapp.Activity.Activity.QuizActivity;
import dja.yazid.gostudeeapp.POJO.Quiz;
import dja.yazid.gostudeeapp.R;

/**
 * Created by yazid on 18/04/2018.
 */

public class QuizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<Quiz> quizItems;

    public class SimpleQuiz extends RecyclerView.ViewHolder{
        TextView subject, description;
        RelativeLayout relativeLayout, bgSubject;

        public SimpleQuiz(View itemView) {
            super(itemView);
            this.subject = itemView.findViewById(R.id.subject);
            this.description = itemView.findViewById(R.id.description);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);
            this.bgSubject = itemView.findViewById(R.id.bgSubject);
        }
    }

    public QuizAdapter(Context context, ArrayList<Quiz> quizItems){
        this.context = context;
        this.quizItems = quizItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_main, parent, false);

        return new QuizAdapter.SimpleQuiz(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Quiz quizzItem = quizItems.get(position);

        ((QuizAdapter.SimpleQuiz)holder).subject.setText(quizzItem.getSubject());
        ((QuizAdapter.SimpleQuiz)holder).description.setText(quizzItem.getDescription());

        ((QuizAdapter.SimpleQuiz)holder).relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizActivity.class);

                intent.putExtra("id", quizItems.get(position).getId());
                intent.putExtra("subject", quizItems.get(position).getSubject());
                intent.putExtra("description", quizItems.get(position).getDescription());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizItems.size();
    }
}
