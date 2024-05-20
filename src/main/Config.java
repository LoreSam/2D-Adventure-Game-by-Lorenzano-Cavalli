package main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

        //FULL SCREEN
        if(gp.fullScreenOn){
            bw.write("On");
        }

        if(!gp.fullScreenOn)
            bw.write("Off");
        bw.newLine();

        //VOLUME MUSICA
        bw.write(String.valueOf(gp.music.volumeScale));
        bw.newLine();

        //VOLUME SUONI
        bw.write(String.valueOf(gp.se.volumeScale));
        bw.newLine();

        //LINGUA
        bw.write(String.valueOf(gp.language));
        bw.newLine();

        bw.close();
    }

    public void loadConfig() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("config.txt"));

        String s = br.readLine();

        //FULL SCREEEN
        if(s.equals("On")){
            gp.fullScreenOn = true;
        }

        if(s.equals("Off")){
            gp.fullScreenOn = false;
        }

        //VOLUME MUSICA
        s = br.readLine();
        gp.music.volumeScale = Integer.parseInt(s);

        //VOLUME SUONI
        s = br.readLine();
        gp.se.volumeScale = Integer.parseInt(s);

        //LINGUA
        s = br.readLine();
        gp.language = Integer.parseInt(s);

        br.close();
    }
}
