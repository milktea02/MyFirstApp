package com.example.jolene.myfirstapp;

import android.databinding.DataBindingUtil;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.ProgressDialog;

import com.example.jolene.myfirstapp.databinding.ActivityMainBinding;

import static java.lang.Math.round;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int valueTwo;

    private int playerTwoPoints;
    private int playerOnepoints;

    private static final char ADDITION = '+';
    private static final char SUBSTRACTION = '-';
    private static final char HALF = '2';

    private char CURRENT_ACTION;
    private int CURRENT_PLAYER = 1;

    private SoundPool soundPool;
    private int hurtID;
    private int gainID;
    boolean plays = false, loaded = false;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        playerOnepoints = Integer.parseInt(binding.playerOnePoints.getText().toString());
        playerTwoPoints = Integer.parseInt(binding.playerTwoPoints.getText().toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();
        }
        else {

            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        hurtID = soundPool.load(MainActivity.this, R.raw.timgormlyeightbithurt, 1);
        gainID = soundPool.load(MainActivity.this, R.raw.hydranosbeep, 1);

        // Symbols

        binding.buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = ADDITION;
                computeCalculation();
                binding.editText.setText(null);
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = SUBSTRACTION;
                computeCalculation();
                binding.editText.setText(null);
            }
        });

        binding.buttonHalf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = HALF;
                computeCalculation();
                binding.editText.setText(null);
            }
        });

        // Numbers

        binding.buttonZero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonDoubleZero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "00");
            }
        });

        binding.buttonTripleZero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "000");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        /**
         * Clears editText one char at a time
         *
         * If there is only 1 char then it resets everything in editText
         */
        binding.buttonClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(binding.editText.getText().length() > 0 ){
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length()-1));
                } else {
                    //valueOne = 0;
                    valueTwo = 0;
                    binding.editText.setText("");
                    //binding.playerOnePoints.setText("");
                }
            }
        });

        binding.buttonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText("");
                binding.playerOnePoints.setText("8000");
                binding.playerTwoPoints.setText("8000");
                playerOnepoints = 8000;
                playerTwoPoints = 8000;
                valueTwo = 0;
            }
        });
    }


    /** Need to refactor this badly */

    private void computeCalculation() {
        if(!binding.editText.getText().toString().equals("")) {
            valueTwo = Integer.parseInt(binding.editText.getText().toString());
            binding.editText.setText(null);

            if (CURRENT_ACTION == ADDITION) {
                playSound(gainID);
                if (CURRENT_PLAYER == 1) {
                    playerOnepoints += valueTwo;
                    binding.playerOnePoints.setText(Integer.toString(playerOnepoints));
                } else {
                    playerTwoPoints += valueTwo;
                    binding.playerTwoPoints.setText(Integer.toString(playerTwoPoints));
                }
            } else if (CURRENT_ACTION == SUBSTRACTION) {
                playSound(hurtID);
                if (CURRENT_PLAYER == 1) {
                    playerOnepoints -= valueTwo;
                    binding.playerOnePoints.setText(Integer.toString(playerOnepoints));
                } else {
                    playerTwoPoints -= valueTwo;
                    binding.playerTwoPoints.setText(Integer.toString(playerTwoPoints));
                }
            }
        } else if (CURRENT_ACTION == HALF) {
            if (CURRENT_PLAYER == 1) {
                playerOnepoints = round((playerOnepoints/2));
                binding.playerOnePoints.setText(Integer.toString(playerOnepoints));
            } else {
                playerTwoPoints = round((playerTwoPoints/2));
                binding.playerTwoPoints.setText(Integer.toString(playerTwoPoints));
            }
        }
    }

    public void setPlayer(View v) {
        this.CURRENT_PLAYER = Integer.parseInt(v.getTag().toString());
        Log.d("Current Player", Integer.toString(CURRENT_PLAYER));
    }

    private void playSound(int soundID) {
        soundPool.play(soundID, 1, 1, 1, 0, 1f);
        Log.d("playBeep()", "Played the Beep with sound ID: " + soundID);
    }

}
