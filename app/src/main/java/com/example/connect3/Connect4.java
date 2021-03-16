package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.SecureRandomSpi;
import java.util.Random;

public class Connect4 extends AppCompatActivity {
    public static final int YELLOW_CODE = 1;
    public static final int RED_CODE = -1;
    public static final int NOT_PLAYED = 2;
    private static final int NO_WINNER = 0;
    boolean isAval =true;
    int winner = -5;
    int[] gameState = {NOT_PLAYED, NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
                       NOT_PLAYED, NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
                       NOT_PLAYED, NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
                       NOT_PLAYED, NOT_PLAYED, NOT_PLAYED , NOT_PLAYED};
    int activePlayer = RED_CODE;
    boolean aval=true;
    int[][] winnigPositions={{0,1,2,3}, {4,5,6,7}, {8,9,10,11},
            {12,13,14,15}, {0,4,8,12}, {1,5,9,13},
            {2,6,10,14}, {3,7,11,15},{0,5,10,15},{3,6,9,12}};
    ImageView imageView;
    ImageView[] imageViews = new ImageView[16];
    String status="";
    int y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect4);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            status = extras.getString("Single");
        }
        imageViews[0] = findViewById(R.id.imageView10);
        imageViews[1] = findViewById(R.id.imageView11);
        imageViews[2] = findViewById(R.id.imageView12);
        imageViews[3] = findViewById(R.id.imageView13);
        imageViews[4] = findViewById(R.id.imageView14);
        imageViews[5] = findViewById(R.id.imageView15);
        imageViews[6] = findViewById(R.id.imageView16);
        imageViews[7] = findViewById(R.id.imageView17);
        imageViews[8] = findViewById(R.id.imageView18);
        imageViews[9] = findViewById(R.id.imageView19);
        imageViews[10] = findViewById(R.id.imageView20);
        imageViews[11] = findViewById(R.id.imageView21);
        imageViews[12] = findViewById(R.id.imageView22);
        imageViews[13] = findViewById(R.id.imageView23);
        imageViews[14] = findViewById(R.id.imageView24);
        imageViews[15] = findViewById(R.id.imageView25);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
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
                if (y<=2) {
                    if (y == 2) {
                        for(int[] positions : winnigPositions){
                            int p=0;
                            for (int i = 0; i <positions.length ; i++) {
                                if(gameState[positions[i]]==RED_CODE)
                                {
                                    p++;
                                }
                            }
                            if (p>=2)
                            {
                                y++;
                                if (gameState[positions[0]]==NOT_PLAYED)
                                {
                                    gameState[positions[0]] = YELLOW_CODE;
                                    imageViews[positions[0]].setImageResource(R.drawable.yellow);
                                    imageViews[positions[0]].setTranslationY(-2000f);
                                    activePlayer = RED_CODE;
                                    imageViews[positions[0]].animate().translationY(0f).rotationBy(360f).setDuration(500);

                                }
                                else if (gameState[positions[1]]==NOT_PLAYED)
                                {
                                    gameState[positions[1]] = YELLOW_CODE;
                                    imageViews[positions[1]].setImageResource(R.drawable.yellow);
                                    imageViews[positions[1]].setTranslationY(-2000f);
                                    activePlayer = RED_CODE;
                                    imageViews[positions[1]].animate().translationY(0f).rotationBy(360f).setDuration(500);

                                }
                                else if (gameState[positions[2]]==NOT_PLAYED)
                                {
                                    gameState[positions[2]] = YELLOW_CODE;
                                    imageViews[positions[2]].setImageResource(R.drawable.yellow);
                                    imageViews[positions[2]].setTranslationY(-2000f);
                                    activePlayer = RED_CODE;
                                    imageViews[positions[2]].animate().translationY(0f).rotationBy(360f).setDuration(500);
                                }
                                else if (gameState[positions[3]]==NOT_PLAYED)
                                {
                                    gameState[positions[3]] = YELLOW_CODE;
                                    imageViews[positions[3]].setImageResource(R.drawable.yellow);
                                    imageViews[positions[3]].setTranslationY(-2000f);
                                    activePlayer = RED_CODE;
                                    imageViews[positions[3]].animate().translationY(0f).rotationBy(360f).setDuration(500);
                                }
                            }
                        }
                        if (y==2)
                        {
                            Random random = new Random();
                            while (true) {
                                int u = random.nextInt(16);
                                if (gameState[u] == NOT_PLAYED) {
                                    gameState[u] = YELLOW_CODE;
                                    imageViews[u].setImageResource(R.drawable.yellow);
                                    imageViews[u].setTranslationY(-2000f);
                                    activePlayer = RED_CODE;
                                    imageViews[u].animate().translationY(0f).rotationBy(360f).setDuration(500);
                                    y++;
                                    break;
                                }
                            }
                        }

                    } else {
                        Random random = new Random();
                        while (true) {
                            int u = random.nextInt(16);
                            if (gameState[u] == NOT_PLAYED) {
                                gameState[u] = YELLOW_CODE;
                                imageViews[u].setImageResource(R.drawable.yellow);
                                imageViews[u].setTranslationY(-2000f);
                                activePlayer = RED_CODE;
                                imageViews[u].animate().translationY(0f).rotationBy(360f).setDuration(500);
                                y++;
                                break;
                            }
                        }
                    }
                }
                else {
                    winnerMsg();
                    if (aval == true) {
                        double bestScore = Double.NEGATIVE_INFINITY;
                        int home = 0;
                        for (int i = 0; i < 16; i++) {
                            if (gameState[i] == NOT_PLAYED) {
                                gameState[i] = YELLOW_CODE;
                                double score = minimax(gameState, 0, false);
                                gameState[i] = NOT_PLAYED;
                                if (score > bestScore) {
                                    bestScore = score;
                                    home = i;
                                }
                            }
                        }
                        gameState[home] = YELLOW_CODE;
                        imageViews[home].setImageResource(R.drawable.yellow);
                        imageViews[home].setTranslationY(-2000f);
                        activePlayer = RED_CODE;
                        imageViews[home].animate().translationY(0f).rotationBy(360f).setDuration(500);
                        Toast.makeText(this, String.valueOf(bestScore), Toast.LENGTH_SHORT).show();
                    }
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
            for (int j = 0; j <16; j++) {
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
            for (int j = 0; j <16; j++) {
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
        imageView = findViewById(R.id.winnerimage2);
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
                    gameState[positions[2]] == gameState[positions[3]] &&
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
        y=0;
        // active player
        activePlayer = RED_CODE;
        // winner
        winner = -5;
        // gameState
        for(int i = 0; i < gameState.length ; i++){
            gameState[i] = NOT_PLAYED;
        }
        // play_ground
        LinearLayout pgLayout = (LinearLayout) findViewById(R.id.pg_layout2);
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