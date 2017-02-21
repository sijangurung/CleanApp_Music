package gurungsijan.com.cleanapp_music.common.utils;

/**
 * Created by Sijan Gurung on 12/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class Converter {

    public static String convertSeconds(int durationInSecs){
        int minutes = durationInSecs / 60;
        int remainingSeconds = durationInSecs - (minutes * 60);
        return String.format("%02d:%02d", minutes, remainingSeconds);

    }
}
