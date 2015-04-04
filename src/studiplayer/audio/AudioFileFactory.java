package studiplayer.audio;

/**
 * Created by jakob on 30.03.15.
 */
public class AudioFileFactory {

    public static AudioFile getInstance(String pathname) throws NotPlayableException {
        String name = pathname.toLowerCase();

        if (name.endsWith(".mp3") || name.endsWith(".ogg")) return new TaggedFile(pathname);
        else if (name.endsWith(".wav")) return new WavFile(pathname);

        else throw new NotPlayableException(pathname, "Unknown suffix for AudioFile");
    }
}
