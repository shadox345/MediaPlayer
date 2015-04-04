package studiplayer.audio;

import studiplayer.basic.BasicPlayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by jakob on 30.03.15.
 */
public abstract class SampledFile extends AudioFile {

    protected String album;

    public SampledFile () {
        super();
    }

    public SampledFile (String pathname) throws NotPlayableException {
        super(pathname);
    }

    public void play() throws NotPlayableException {
        try {
            BasicPlayer.play(this.getPathname());
        } catch (RuntimeException e) {
            throw new NotPlayableException(this.getPathname(), "Error in play", e);
        }
    }

    public void togglePause() {
        BasicPlayer.togglePause();
    }

    public void stop() {
        BasicPlayer.stop();
    }

    public String getFormattedDuration() {
        long duration = Long.parseLong(this.duration);
        return timeFormatter(duration);
    }

    public String getFormattedPosition() {
        long position = BasicPlayer.getPosition();
        return timeFormatter(position);
    }

    public static String timeFormatter(long microtime) {
        long limit = 100L * 60 * 1000 * 1000;
        if (microtime < 0) {
            throw new RuntimeException("Negative time value provided");
        } else if (microtime >= limit) { // more than 100 minutes
            throw new RuntimeException("Time value exceeds allowed format");
        }

        DateFormat formatter = new SimpleDateFormat("mm:ss");
        String time = formatter.format(microtime/1000); // convert to sec*10^-3
        return time;
    }

    public String toString() {
        return super.toString();
    }

    public String getAlbum() {
        return album;
    }
}
