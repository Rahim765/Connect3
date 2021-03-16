package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Year;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    public static final int YELLOW_CODE = 1;
    public static final int RED_CODE = -1;
    public static final int NOT_PLAYED = 2;
    private static final int NO_WINNER = 0;
    boolean isAval =true;
    int winner = -5;
    int[] gameState = {NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
            NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
            NOT_PLAYED, NOT_PLAYED, NOT_PLAYED};
    int activePlayer = RED_CODE;
    boolean aval=true;
    int[][] winnigPositions={{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    ImageView imageView;
    ImageView[] imageViews = new ImageView[9];
    String status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect3);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            status = extras.getString("Single");
        }
        imageViews[0] = findViewById(R.id.image1);
        imageViews[1] = findViewById(R.id.image2);
        imageViews[2] = findViewById(R.id.image3);
        imageViews[3] = findViewById(R.id.image4);
        imageViews[4] = findViewById(R.id.image5);
        imageViews[5] = findViewById(R.id.image6);
        imageViews[6] = findViewById(R.id.image7);
        imageViews[7] = findViewById(R.id.image8);
        imageViews[8] = findViewById(R.id.image9);
    }
    public void dropIn(View view)  {
        int tag = Integer.parseInt((String) view.getTag());
        if(winner != -5 || gameState[tag] != NOT_PLAYED){
            return;
        }
        ImageView img = (ImageView) view;
        img.setTranslationY(-2000f);

            if (activePlayer == RED_CODE) {
                img.setImageResource(R.drawable.red);
                gameState[tag] = RED_CODE;
                activePlayer = YELLOW_CODE;
                img.animate().translationY(0f).rotationBy(360f).setDuration(500);
                if(status.equals("Single")) {
                    winnerMsg();
                     if (aval == true) {
                        double bestScore = Double.NEGATIVE_INFINITY;
                        int home=0;
                        for (int i = 0; i <9 ; i++) {
                            if (gameState[i]==NOT_PLAYED)
                            {
                                gameState[i]= YELLOW_CODE;
                                double score = minimax(gameState,0,false);
                                gameState[i]=NOT_PLAYED;
                                if (score>bestScore)
                                {
                                    bestScore=score;
                                    home=i;
                                }
                            }
                        }
                        gameState[home]= YELLOW_CODE;
                        imageViews[home].setImageResource(R.drawable.yellow);
                        imageViews[home].setTranslationY(-2000f);
                        activePlayer = RED_CODE;
                        imageViews[home].animate().translationY(0f).rotationBy(360f).setDuration(500);
                        Toast.makeText(this, String.valueOf(bestScore), Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (activePlayer == YELLOW_CODE) {
                img.setImageResource(R.drawable.yellow);
                gameState[tag] = YELLOW_CODE;
                activePlayer = RED_CODE;
                img.animate().translationY(0f).rotationBy(360f).setDuration(500);
            }

        if (aval==true) {
            winnerMsg();
        }

    }
    private double minimax(int[] gameState, int depht, boolean user) {
        int result = checkWinner();
        if (result != -5 )
        {
            int score = result;
            return  score;
        }

       if (user)
        {
            double bestscore = Double.NEGATIVE_INFINITY;
            for (int j = 0; j <9 ; j++) {
                if (gameState[j] == NOT_PLAYED)
                {
                    gameState[j]=YELLOW_CODE;
                    double score = minimax(gameState,depht+1,false);
                    gameState[j]= NOT_PLAYED;
                    bestscore=Math.max(score , bestscore);
                }
            }
            return bestscore;
        }else {
            double bestscore = Double.POSITIVE_INFINITY;
            for (int j = 0; j <9 ; j++) {
                if (gameState[j] == NOT_PLAYED)
                {
                    gameState[j]=RED_CODE;
                    double score = minimax(gameState,depht+1,true);
                    gameState[j]= NOT_PLAYED;
                    bestscore=Math.min(score,bestscore);
                }
            }
            return bestscore;
        }
        }
    public void winnerMsg() {
        imageView = findViewById(R.id.winnerimage);
        winner = checkWinner();
        if(winner != -5 || filled() ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setBackground(getDrawable(R.drawable.nowinner));
                imageView.setVisibility(View.VISIBLE);
                aval=false;

            }
            if(winner == NO_WINNER){
            } else if(winner == -1){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setBackground(getDrawable(R.drawable.redwinner));
                    imageView.setVisibility(View.VISIBLE);
                    aval=false;

                }
            } else if(winner == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setBackground(getDrawable(R.drawable.yellowwin));
                    imageView.setVisibility(View.VISIBLE);
                    aval=false;
                }
            }


        }
    }
    public int checkWinner(){
        for(int[] positions : winnigPositions){
            if(     gameState[positions[0]] == gameState[positions[1]] &&
                    gameState[positions[1]] == gameState[positions[2]] &&
                    gameState[positions[0]] != NOT_PLAYED){
                if (gameState[positions[0]]==RED_CODE) {
                    return -1;
                }
                else if (gameState[positions[0]]==YELLOW_CODE)
                {
                    return 1;
                }
            }
        }
        if (filled()==true) {
            return NO_WINNER;
        }
        return -5;
    }
    public boolean filled(){
        for(int i = 0 ; i < gameState.length ; i++){
            if(gameState[i] == NOT_PLAYED){
                return false;
            }
        }
        return true;
    }
    public void reset(View v){
        // active player
        activePlayer = RED_CODE;
        // winner
        winner = -5;
        // gameState
        for(int i = 0; i < gameState.length ; i++){
            gameState[i] = NOT_PLAYED;
        }
        // play_ground
        LinearLayout pgLayout = (LinearLayout) findViewById(R.id.pg_layout);
        for(int i = 0 ; i < pgLayout.getChildCount() ; i++){
            LinearLayout row = (pgLayout.getChildAt(i) instanceof LinearLayout) ?
                    (LinearLayout) pgLayout.getChildAt(i) : null;
            if(row == null) return;
            for(int j = 0; j < row.getChildCount() ; j++){
                ImageView iv = (row.getChildAt(j) instanceof ImageView) ?
                        (ImageView) row.getChildAt(j) : null;
                if(iv == null) return;
                iv.setImageResource(0);
            }
        }
        imageView.setBackground(null);
        imageView.setVisibility(View.GONE);
        aval=true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem resetItem = menu.add("RESET");
        resetItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        resetItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                reset(null);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}