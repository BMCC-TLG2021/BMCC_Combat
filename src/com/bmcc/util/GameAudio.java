package com.bmcc.util;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class GameAudio {


    public static void PlayWelcomeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/Cartoon.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }
    public static void PlayFightAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/FIGHTSOUND.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }
    public static void PlayDoorAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/DOOROPEN.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }

    public static void PlayYouWonAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/YOUWON.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }

    public static void PlayYouLostAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/YOULOST.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }
    public static void PlayYouGameOverAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/GAMEOVER.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }

    public static void PlayNextRoundAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/NEXTROUND.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }

    public static void PlayResultAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/PLAYRESULT.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }





    public static void PlayGameSavedAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/GAMESAVED.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }

    public static void PlayAttackAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/RagingStreets.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }

    public static void PlayMagicalAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);

        File file = new File("asset/BSC.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();

    }




}

