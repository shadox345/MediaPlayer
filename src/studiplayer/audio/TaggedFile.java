package studiplayer.audio;

import studiplayer.basic.TagReader;
import java.util.Map;

/**
 * Created by Jakob on 08.12.2014.
 */
public class TaggedFile extends SampledFile {

    protected String album;

    public TaggedFile() {
        super();
    }

    public TaggedFile(String pathname) throws NotPlayableException {
        super(pathname);
        readAndStoreTags(this.getPathname());
    }

    public void readAndStoreTags(String pathname) throws NotPlayableException {
        Map<String, Object> tags;
        try {
            tags = TagReader.readTags(pathname);
        } catch (RuntimeException e) {
            throw new NotPlayableException(pathname, "Tags can't be read", e);
        }

        if (tags.get("title") != null) {
            this.title = tags.get("title").toString().trim();
        }
        if (tags.get("author") != null) {
            this.author = tags.get("author").toString().trim();
        }
        if (tags.get("album") != null) {
            this.album = tags.get("album").toString().trim();
        }

        this.duration = tags.get("duration").toString().trim();
    }

    public String[] fields() {
        String album;

        if (this.album == null) album = "";
        else album = this.album;

        String[] fields = {this.getAuthor(), this.getTitle(), album, this.getFormattedDuration()};
        return fields;
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
