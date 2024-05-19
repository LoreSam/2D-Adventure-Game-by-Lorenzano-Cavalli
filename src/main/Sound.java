package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    URL musicURL[] = new URL[10];
    FloatControl fc;
    int volumeScale = 10;
    float volume;

    public Sound() {
        //TODO funzione per caricare i suoni con un indice e non con i numeri fissi
        //MUSICHE
        musicURL[0] = getClass().getResource("/sound/music/Menu Principale.wav");
        musicURL[1] = getClass().getResource("/sound/music/Tutorial.wav");
        musicURL[2] = getClass().getResource("/sound/music/Appello.wav");
        musicURL[3] = getClass().getResource("/sound/music/Mangiate Ragazzi.wav");
        musicURL[4] = getClass().getResource("/sound/music/Tempo Libero.wav");
        musicURL[5] = getClass().getResource("/sound/music/Hit The Gym.wav");
        musicURL[6] = getClass().getResource("/sound/music/Lavarsi.wav");
        musicURL[7] = getClass().getResource("/sound/music/Luci Spente.wav");

        //SOUND FX
        soundURL[0] = getClass().getResource("/sound/soundfx/cursor.wav");
        soundURL[1] = getClass().getResource("/sound/soundfx/cuttree.wav");
        soundURL[2] = getClass().getResource("/sound/soundfx/hit.wav");
        soundURL[3] = getClass().getResource("/sound/soundfx/parry (brutto).wav");
        soundURL[4] = getClass().getResource("/sound/soundfx/receivedamage.wav");
        soundURL[5] = getClass().getResource("/sound/soundfx/unlock.wav");
        soundURL[6] = getClass().getResource("/sound/soundfx/swingarm.wav");
        soundURL[7] = getClass().getResource("/sound/soundfx/gameover.wav");
        soundURL[8] = getClass().getResource("/sound/soundfx/escaped.wav");
    }

    public void setSound(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }catch (Exception e){

        }
    }

    public void setMusic(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }catch (Exception e){

        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public void checkVolume(){

        switch(volumeScale){
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -40f;
                break;
            case 2:
                volume = -35f;
                break;
            case 3:
                volume = -30f;
                break;
            case 4:
                volume = -25f;
                break;
            case 5:
                volume = -20f;
                break;
            case 6:
                volume = -15f;
                break;
            case 7:
                volume = -10f;
                break;
            case 8:
                volume = -5f;
                break;
            case 9:
                volume = 0f;
                break;
        }

        fc.setValue(volume);
    }
}
