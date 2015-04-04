package studiplayer.audio;

import java.util.Comparator;

/**
 * Created by jakob on 31.03.15.
 */
public class TitleComparator implements Comparator<AudioFile> {

    @Override
    public int compare(AudioFile af1, AudioFile af2) {
        if (af1 == null || af2 == null) throw new NullPointerException("One of the audio files is null");

        String title1 = af1.getTitle();
        String title2 = af2.getTitle();

        if (title1 != null && title2 != null) {
            return title1.compareTo(title2);
        }

        if (title1 != null && title2 == null) return 1;
        if (title1 == null && title2 != null) return -1;

        return 0;
    }
}
