package studiplayer.audio;

import java.util.Comparator;

/**
 * Created by jakob on 31.03.15.
 */
public class AlbumComparator implements Comparator<AudioFile> {

    @Override
    public int compare(AudioFile af1, AudioFile af2) {
        if (af1 == null || af2 == null) throw new NullPointerException("One of the audio files is null");

        if (af1 instanceof TaggedFile && af2 instanceof TaggedFile) {
            String album1 = ((TaggedFile) af1).getAlbum();
            String album2 = ((TaggedFile) af2).getAlbum();

            if (album1 != null && album2 == null) return 1;
            if (album1 == null && album2 != null) return -1;
            if (album1 == null && album2 == null) return 0;

            return album1.compareTo(album2);
        }

        if (af1 instanceof TaggedFile && !(af2 instanceof TaggedFile)) return 1;
        if (!(af1 instanceof TaggedFile) && !(af2 instanceof TaggedFile)) return -1;

        return 0;
    }
}
