package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    // 0:yellow , 1: red  , 2: empty
    int[] gamestate = { 2,2,2,2,2,2,2,2,2};
    int[][] winning = {{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};
    int activeplayer = 0;
    boolean gameactive = true ;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winner : winning) {
                if (gamestate[winner[0]] == gamestate[winner[1]] && gamestate[winner[1]] == gamestate[winner[2]] && gamestate[winner[0]] != 2) {
                    String win = "";
                    gameactive = false;
                    if (activeplayer == 1) {
                        win = "Yellow";
                    } else {
                        win = "Red";
                    }
                    Toast.makeText(this, win + " has won!", Toast.LENGTH_SHORT).show();
                    Button playagainbutton = (Button) findViewById(R.id.playagain);
                    TextView winnertextview = (TextView) findViewById(R.id.winnertext);
                    winnertextview.setText(win+" has won!");
                    winnertextview.setVisibility(View.VISIBLE);
                    playagainbutton.setVisibility(View.VISIBLE);

                }
            }
        }
    }
    public void play(View view)
    {
        Button playagainbutton = (Button) findViewById(R.id.playagain);
        TextView winnertextview = (TextView) findViewById(R.id.winnertext);
        winnertextview.setVisibility(View.INVISIBLE);
        playagainbutton.setVisibility(View.INVISIBLE);
        GridLayout gridd = (GridLayout) findViewById(R.id.grid);
        for(int i=0; i<gridd.getChildCount(); i++) {
            ImageView counter = (ImageView)gridd.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i] = 2;
        }
        activeplayer = 0;
        gameactive = true ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
