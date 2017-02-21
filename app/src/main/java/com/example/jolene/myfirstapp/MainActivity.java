package com.example.jolene.myfirstapp;

import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import com.example.jolene.myfirstapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int valueOne = 0;
    private int valueTwo;

    private int playerTwoPoints;
    private int playerOnepoints;

    private static final char ADDITION = '+';
    private static final char SUBSTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;
    private int CURRENT_PLAYER = 1;

    private SoundPool soundPool;
    private int soundID;
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

        // AudioManager Settings
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        // Hard Buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // the Counter will help us recognize the stream id of the sound played now
        counter = 0;

        // Load the sounds
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        soundID = soundPool.load(this, R.raw.timgormly8bithurt, 1);




        // Symbols

        binding.buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = ADDITION;
                computeCalculation();
                //binding.playerOnePoints.setText(decimalFormat.format(valueOne) + "+");
                binding.editText.setText(null);
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = SUBSTRACTION;
                computeCalculation();
                //binding.playerOnePoints.setText(decimalFormat.format(valueOne) + "-");
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
                    valueOne = 0;
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
                valueOne = 0;
                valueTwo = 0;
            }
        });
    }


    /** Need to refactor this badly */

    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            valueTwo = Integer.parseInt(binding.editText.getText().toString());
            binding.editText.setText(null);
            playBeep();

            if (CURRENT_ACTION == ADDITION) {
                if (CURRENT_PLAYER == 1) {
                    playerOnepoints += valueTwo;
                    binding.playerOnePoints.setText(Integer.toString(playerOnepoints));
                } else {
                    playerTwoPoints += valueTwo;
                    binding.playerTwoPoints.setText(Integer.toString(playerTwoPoints));
                }
            } else if (CURRENT_ACTION == SUBSTRACTION) {
                if (CURRENT_PLAYER == 1) {
                    playerOnepoints -= valueTwo;
                    binding.playerOnePoints.setText(Integer.toString(playerOnepoints));
                } else {
                    playerTwoPoints -= valueTwo;
                    binding.playerTwoPoints.setText(Integer.toString(playerTwoPoints));
                }
            } else {
                try {
                    valueOne = Integer.parseInt(binding.editText.getText().toString());
                } catch (Exception e) {

                }
            }

        }
    }

    public void setPlayerOne(View v) {
        this.CURRENT_PLAYER = 1;
        Log.d("Current Player", Integer.toString(CURRENT_PLAYER));
    }

    public void setPlayerTwo(View v) {
        this.CURRENT_PLAYER = 2;
        Log.d("Current Player", Integer.toString(CURRENT_PLAYER));
    }

    public void playSound(View v) {
        // Is the sound loaded does it already play?
        if (loaded && !plays) {
            soundPool.play(soundID, volume, volume, 1, 0, 1f);
            counter = counter++;
            Toast.makeText(this, "Played sound", Toast.LENGTH_SHORT).show();
            plays = true;
        }
    }

    private void playBeep() {
        soundPool.play(soundID, volume, volume, 1, 0, 1f);
        soundPool.stop(soundID);
        Log.d("playBeep()", "Played the Beep");
    }


}
