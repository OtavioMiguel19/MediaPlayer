package com.vidroid.com.br.apps.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    SeekBar seekBarVolume;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.hakuna_matata);
        inicializarSeekBar();
    }
    private void inicializarSeekBar(){
        seekBarVolume = findViewById(R.id.seekBarVolume);

        //Configurar audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Recupera volumes atual e máximo
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Configurar volumes na seekbar
        seekBarVolume.setMax(volumeMaximo);
        seekBarVolume.setProgress(volumeAtual);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        progress,
                        0
                );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }



    public void executarSom(View view){
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }
    public void pausarSom(View view){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    public void stopSom(View view){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}
