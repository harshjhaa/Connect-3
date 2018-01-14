package com.jhaharsh.android.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static int x=0,tokenPos; // x=1 yellow x=0 red
    static int[] pos = {2,2,2,2,2,2,2,2,2};
    int[][] winLoc = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameOver = false;

    class Tic{

        void test(String str){
            for (int[] winPos : winLoc) {
                if (pos[winPos[0]] == pos[winPos[1]] &&
                        pos[winPos[1]] == pos[winPos[2]] &&
                        pos[winPos[0]] != 2) {
                    //Toast.makeText(MainActivity.this,str + " Won !",Toast.LENGTH_SHORT).show();
                    TextView winMessage = (TextView) findViewById(R.id.winMessage);
                    winMessage.setText(str + " PLAYER WON !");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                    layout.setVisibility(layout.VISIBLE);
                    gameOver = true;
                }
                //Checking for the draw condition
                else {
                    boolean gmOvr =  true;
                    for (int countState : pos){
                        if(countState == 2) gmOvr = false;
                    }
                    if(gmOvr == true){
                        TextView winMessage = (TextView) findViewById(R.id.winMessage);
                        winMessage.setText("GAME IS DRAW !");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                        layout.setVisibility(layout.VISIBLE);
                        gameOver = true;
                    }
                }
            }
        }

        void dropYellow(View view){

                ImageView token = (ImageView) view;
                token.setTranslationY(-2000f);
                token.setImageResource(R.drawable.yellow);
                token.animate().translationYBy(2000f).rotation(180f).setDuration(200);
                tokenPos = Integer.parseInt(token.getTag().toString());
                pos[tokenPos]=0;
                test("YELLOW");
        }
        void dropRed(View view){
                ImageView token = (ImageView) view;
                token.setTranslationY(2000f);
                token.setImageResource(R.drawable.red);
                token.animate().translationYBy(-2000f).rotation(180f).setDuration(200);
                tokenPos = Integer.parseInt(token.getTag().toString());
                pos[tokenPos]=1;
                test("RED");

        }
    }Tic o = new Tic();

    public void dropIn(View view){

        if (gameOver == false){

            ImageView token = (ImageView) view;
            tokenPos = Integer.parseInt(token.getTag().toString());
            if(pos[tokenPos]==2){
                if(x == 0){
                    o.dropYellow(view);
                    x=1;
                }
                else{
                    o.dropRed(view);
                    x=0;
                }
            }
        }
    }

    public void playAgain(View view){

        gameOver = false;

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        layout.setVisibility(layout.INVISIBLE);
        //Setting game state to default
        x=0; // x=1 yellow and x=0 red
        for(int i=0;i<pos.length;i++){
            pos[i]=2;
        }
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        for(int i=0; i<grid.getChildCount(); i++){

            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
