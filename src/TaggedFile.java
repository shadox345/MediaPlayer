import studiplayer.basic.BasicPlayer;

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
}
