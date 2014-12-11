import studiplayer.basic.BasicPlayer;
import studiplayer.basic.TagReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Jakob on 08.12.2014.
 */
public class TaggedFile extends AudioFile {

    private String album;

    public TaggedFile() {
        super();
    }

    public TaggedFile(String pathname) {
        super(pathname);
        readAndStoreTags(this.getPathname());
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
        long duration = Long.parseLong(this.duration);
        return timeFormatter(duration);
    }

    public String getFormattedPosition() {
        long position = BasicPlayer.getPosition();
        return timeFormatter(position);
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

    public void readAndStoreTags(String pathname) {
        Map<String, Object> tags = TagReader.readTags(pathname);
        if (tags.get("title") != null) {
            this.title = tags.get("title").toString().trim();
        }
        if (tags.get("author") != null) {
            this.author = tags.get("author").toString().trim();
        }

        this.duration = tags.get("duration").toString().trim();
        this.album = tags.get("album").toString().trim();
    }

    public String getAlbum() {
        return album;
    }

    @Override
    public String toString() {
        if (album == null) {
            return super.toString() + " - " + this.getFormattedDuration();
        }
        return super.toString() + " - " + album + " - " + this.getFormattedDuration();
    }
}
