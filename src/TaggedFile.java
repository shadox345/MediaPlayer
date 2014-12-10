import studiplayer.basic.BasicPlayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jakob on 08.12.2014.
 */
public class TaggedFile extends AudioFile {

    public TaggedFile() {
        super();
    }

    public TaggedFile(String pathname) {
        super(pathname);
    }

    public void play() {
        BasicPlayer.play(this.getPathname());
    }

    public void togglePause() {
        BasicPlayer.togglePause();
    }

    public void stop() {
        BasicPlayer.stop();
    }

    public String getFormattedDuration() {
        return "";
    }

    public String getFormattedPosition() {
        return "";
    }

    public static String timeFormatter(long microtime) {
        if (microtime < 0) {
            throw new RuntimeException("Negative time value provided");
        } else if (microtime >= (100 * 60 * 1000 * 1000)) { // more than 100 minutes
            throw new RuntimeException("Time value exceeds allowed format");
        }

        DateFormat formatter = new SimpleDateFormat("mm:ss");
        String time = formatter.format(microtime/1000); // convert to sec*10^-3
        return time;
    }
}
