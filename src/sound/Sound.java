package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound
{
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound()
    {
        soundURL[0] = getClass().getResource("/sound/music/map1_music.wav");
        soundURL[1] = getClass().getResource("/sound/music/map2_music.wav");
        soundURL[2] = getClass().getResource("/sound/music/map3_music.wav");
        soundURL[3] = getClass().getResource("/sound/music/map4_music.wav");
        soundURL[4] = getClass().getResource("/sound/music/map5_music.wav");
        soundURL[5] = getClass().getResource("/sound/music/title_screen_music.wav");
        soundURL[6] = getClass().getResource("/sound/sfx/battle_theme.wav");
        soundURL[7] = getClass().getResource("/sound/sfx/bumping_wall.wav");
        soundURL[8] = getClass().getResource("/sound/sfx/buy_sell_merchant.wav");
        soundURL[9] = getClass().getResource("/sound/sfx/click_menu.wav");
        soundURL[10] = getClass().getResource("/sound/sfx/dog_bark.wav"); // belum
        soundURL[11] = getClass().getResource("/sound/sfx/heal.wav"); // belum
        soundURL[12] = getClass().getResource("/sound/sfx/lvl_up.wav");
        soundURL[13] = getClass().getResource("/sound/sfx/meow_cat.wav"); // belum
        soundURL[14] = getClass().getResource("/sound/sfx/pick_up_item.wav");
        soundURL[15] = getClass().getResource("/sound/sfx/run_away.wav");
        soundURL[16] = getClass().getResource("/sound/sfx/teleport.wav");
        soundURL[17] = getClass().getResource("/sound/sfx/unlock_door.wav"); // belum
        soundURL[18] = getClass().getResource("/sound/sfx/up_down_menu.wav");
        soundURL[19] = getClass().getResource("/sound/sfx/win_battle.wav");
    }

    public void setFile(int i)
    {
        try {
            // Format to open a sound in java
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        clip.stop();
    }
}
