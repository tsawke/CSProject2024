package com.csproject;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
    private Clip clip;

    public static BackgroundMusic bgm = new BackgroundMusic("./csproject/src/main/resources/Musics/Trap Remix Guys - Krusty Krab (From SpongeBob SquarePants) [Trap Remix].wav");

    public static void DefaultPlayBGM() {
        bgm.Play();
    }

    public BackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Play() {
        if(clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void Stop() {
        if(clip != null)
            clip.stop();
    }

    public void Pause() {
        if(clip != null && clip.isRunning())
            clip.stop();
    }

    public void Resume() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }
}