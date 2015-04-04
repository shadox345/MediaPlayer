package studiplayer.audio;

import java.util.Comparator;

/**
 * Created by jakob on 31.03.15.
 */
public class AuthorComparator implements Comparator<AudioFile> {

    public int compare(AudioFile af1, AudioFile af2) {
        if (af1 == null || af2 == null) throw new NullPointerException("One of the audio files is null");

        String author1 = af1.getAuthor();
        String author2 = af2.getAuthor();

        if (author1 != null && author2 != null) {
            return author1.compareTo(author2);
        }

        if (author1 != null && author2 == null) return 1;
        if (author1 == null && author2 != null) return -1;

        return 0;
    }
}
