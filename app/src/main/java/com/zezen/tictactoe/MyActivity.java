package com.zezen.tictactoe;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class MyActivity extends ActionBarActivity {

    Button pion0,pion1,pion2,pion3,pion4,pion5,pion6,pion7,pion8;
    Button[] pions = new Button[9];
    TextView gameStatus;
    Button replayButton;
    ImageView morpionImageView;

    boolean gameIsOver = false;
    int currentTurn = 0;
    String[] joueurs = {"X", "0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        initialisation();



    }

    private void initialisation() {
        pion0 = (Button) findViewById(R.id.pion1Button);
        pion1 = (Button) findViewById(R.id.pion2Button);
        pion2 = (Button) findViewById(R.id.pion3Button);
        pion3 = (Button) findViewById(R.id.pion4Button);
        pion4 = (Button) findViewById(R.id.pion5Button);
        pion5 = (Button) findViewById(R.id.pion6Button);
        pion6 = (Button) findViewById(R.id.pion7Button);
        pion7 = (Button) findViewById(R.id.pion8Button);
        pion8 = (Button) findViewById(R.id.pion9Button);

        gameStatus = (TextView) findViewById(R.id.gameStatus);

        replayButton = (Button) findViewById(R.id.tryAgain);

        morpionImageView = (ImageView) findViewById(R.id.morpion);

        pions[0] = pion0;
        pions[1] = pion1;
        pions[2] = pion2;
        pions[3] = pion3;
        pions[4] = pion4;
        pions[5] = pion5;
        pions[6] = pion6;
        pions[7] = pion7;
        pions[8] = pion8;

        bindEventsListenerOnButtons();
    }


    private void bindEventsListenerOnButtons() {
        for(Button pion : pions){
            pion.setOnClickListener(pionListener);
        }

        replayButton.setOnClickListener(replayListener);
    };



    private OnClickListener pionListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            //Trouver le bouton cliqué
            Button button = (Button) findViewById(view.getId());

            //jeu finit ?
            if(gameIsOver){
                //alors "rien"
                return;
            }

            //emplacement invalide ?
            if(!isValid(button)){
                //shake animation
                //emplacement invalide!
                gameStatus.setText("Déplacement Invalide!");
            }


            //sinon
            // mettre symbole utilisateur courant
            else{
                setSymbol(button, joueurs[currentTurn]);

                //utilisateur gagné
                gameIsOver = winnerExists();

                if(gameIsOver){
                    //confirmation
                    gameStatus.setText("Le joueur " + joueurs[currentTurn] + " a gagné!");
                    return;
                }
                //tableau plein
                if(boardIsFull()){
                    //match nul
                    //notifier jeu terminé
                    gameStatus.setText("MATCH NUL!");
                    gameIsOver = true;
                    return;
                }

                //alors on informe le prochain joueur
                currentTurn = currentTurn == 0 ? 1 : 0;
                gameStatus.setText("C\'est le tour de " + joueurs[currentTurn]);
            }

        }


    };

    private boolean winnerExists() {

        //ligne 1
        if(pions[0].getText().toString() == joueurs[currentTurn]
                && pions[1].getText().toString() == joueurs[currentTurn]
                && pions[2].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(0, 1, 2);
            return true;
        }

        //ligne 2
        if(pions[3].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[5].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(3,4,5);
            return true;
        }

        //ligne 3
        if(pions[6].getText().toString() == joueurs[currentTurn]
                && pions[7].getText().toString() == joueurs[currentTurn]
                && pions[8].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(6,7,8);
            return true;
        }

        //colone 1
        if(pions[0].getText().toString() == joueurs[currentTurn]
                && pions[3].getText().toString() == joueurs[currentTurn]
                && pions[6].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(0,3,6);
            return true;
        }

        //colone 2
        if(pions[1].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[7].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(1,4,7);
            return true;
        }

        //colone 3
        if(pions[2].getText().toString() == joueurs[currentTurn]
                && pions[5].getText().toString() == joueurs[currentTurn]
                && pions[8].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(2,5,8);
            return true;
        }

        //diag 1
        if(pions[0].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[8].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(0,4,8);
            return true;
        }

        //diag 2
        if(pions[2].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[6].getText().toString() == joueurs[currentTurn]){
            changeColorsOfButtons(2,4,6);
            return true;
        }
        return false;
    }

    private void changeColorsOfButtons(int i, int i1, int i2) {
        pions[i].setTextColor(Color.argb(255, 8, 13, 246));//bleu
        pions[i1].setTextColor(Color.argb(255, 42,188,18));//vert
        pions[i2].setTextColor(Color.argb(255, 230,79,97));//bleu
    }

    private boolean isValid(Button button) {
        return button.getText().toString().length()==0;
    }

    private boolean boardIsFull() {
        for(Button pion : pions){
            if(isValid(pion)){
                return false;
            }
        }
        return true;
    }

    private void setSymbol(Button button, String symbol) {
        button.setText(symbol);
    }



    private OnClickListener replayListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            for (Button pion : pions){
                pion.setText("");
                pion.setTextColor(Color.BLACK);
            }

            gameStatus.setText(R.string.gameStatus);
            currentTurn = 0;
            gameIsOver = false;
        }
    };

}
