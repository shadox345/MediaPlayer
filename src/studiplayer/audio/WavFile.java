package studiplayer.audio;

import studiplayer.basic.WavParamReader;

/**
 * Created by jakob on 30.03.15.
 */
public class WavFile extends SampledFile {

    public WavFile () {
        super();
    }

    public WavFile (String pathname) throws NotPlayableException {
        super(pathname);
        readAndSetDurationFromFile(this.getPathname());
    }

    public static long computeDuration(long numberOfFrames, float frameRate) {
        long duration = (long) (numberOfFrames / frameRate * 1000000); // duration of song in microseconds
        return duration;
    }

    public void readAndSetDurationFromFile(String pathname) throws NotPlayableException {
        try {
            WavParamReader.readParams(pathname);
        } catch (RuntimeException e) {
            throw new NotPlayableException(pathname, "Parameter can't be read from the wav file", e);
        }

        long duration = computeDuration(WavParamReader.getNumberOfFrames(), WavParamReader.getFrameRate());
        this.duration = String.valueOf(duration);
    }

    public String[] fields() {
        String album;

        if (this.album == null) album = ""; // empty string
        else album = this.album;

        String[] fields = {this.getAuthor(), this.getTitle(), album, this.getFormattedDuration()};
        return fields;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + this.getFormattedDuration();
    }
}
