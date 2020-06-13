package com.example.lionortigergane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity
    {

        enum Player
        {

            ONE,TWO,NO
        }

        Player currentPlayer=Player.ONE;


        Player[] playerChoices=new Player[9];

        int [][] winnercolumnsrows={  { 0,1,2 } ,{3,4,5} ,{6,7,8},{0,3,6},{1,4,7},
                                      {2,5,8},{0,4,8},{2,4,6} };

             private boolean gameOver=false;
             private Button btnReset;
             private GridLayout gridLayout;


        @Override
    protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReset=findViewById(R.id.ResetButton);
         gridLayout=findViewById(R.id.myGridLayout);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                resetTheGame();
            }
        });



         for(int index=0;index<playerChoices.length;index++)
         {

             playerChoices[index]=Player.NO;
         }

     }



        public void imageviewisTapped(View imageView) {
            ImageView tappedImageView = (ImageView) imageView;
            int tiTag = Integer.parseInt(tappedImageView.getTag().toString());


            if (playerChoices[tiTag] == Player.NO && gameOver==false)
            {

                tappedImageView.setTranslationX(-2000);


                playerChoices[tiTag] = currentPlayer;

                if (currentPlayer == Player.ONE) {
                    tappedImageView.setImageResource(R.drawable.tiger);

                    currentPlayer = Player.TWO;
                } else if (currentPlayer == Player.TWO) {

                    tappedImageView.setImageResource(R.drawable.lion);

                    currentPlayer = Player.ONE;

                }


                tappedImageView.animate().translationXBy(2000).alpha(1)
                        .rotation(3600).setDuration(2000);


                Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();


                for (int[] winnerColumns : winnercolumnsrows) {

                    if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                            playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                            && playerChoices[winnerColumns[0]]!= Player.NO)
                    {

                        btnReset.setVisibility(View.VISIBLE);

                        gameOver=true;


                        String winnerofGame = "";


                        if (currentPlayer == Player.ONE) {

                            winnerofGame = "Player 2";


                        } else if (currentPlayer == Player.TWO) {

                            winnerofGame = "Player 1";

                        }

                        else
                            {
                           Toast.makeText(this,"No Winner, Game is resetting..",Toast.LENGTH_SHORT).show();

                           resetTheGame();

                        }


                        Toast.makeText(this, winnerofGame + "is the winner", Toast.LENGTH_LONG).show();


                    }


                }


            }


        }


             private void resetTheGame()
             {

              for(int index=0;index<gridLayout.getChildCount();index++)
              {

                  ImageView imageView= (ImageView) gridLayout.getChildAt(index);

                  imageView.setImageDrawable(null);
                  imageView.setAlpha(0f);
              }

                  currentPlayer=Player.ONE;

                /*playerChoices[0]=Player.NO;
                 playerChoices[1]=Player.NO;
                 playerChoices[2]=Player.NO;
                 playerChoices[3]=Player.NO;
                 playerChoices[4]=Player.NO;
                 playerChoices[5]=Player.NO;
                 playerChoices[6]=Player.NO;
                 playerChoices[7]=Player.NO;
                 playerChoices[8]=Player.NO;*/


                 for(int index=0;index<playerChoices.length;index++)
                 {

                    playerChoices[index]=Player.NO;
                 }

                 gameOver=false;

               btnReset.setVisibility(View.INVISIBLE);
             }

    }