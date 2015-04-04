package studiplayer.audio;

import java.util.Comparator;

/**
 * Created by jakob on 31.03.15.
 */
public class DurationComparator implements Comparator<AudioFile> {

    @Override
    public int compare(AudioFile af1, AudioFile af2) {
        if (af1 == null || af2 == null) throw new NullPointerException("One of the audio files is null");



        return 0;
    }
}
