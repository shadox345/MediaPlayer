package studiplayer.audio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by jakob on 30.03.15.
 */
public class PlayList extends LinkedList<AudioFile> {

    private int current;
    private boolean randomOrder;

    public PlayList() {
        current = 0;
        randomOrder = false;
    }

    public PlayList(String m3uPathname) {
        this();
        loadFromM3U(m3uPathname);
    }

    public AudioFile getCurrentAudioFile() {
        if (!this.isEmpty() && current < this.size() && this.get(current) != null) {
            return this.get(current);
        } else return null;
    }

    public void changeCurrent() {
        current = (current + 1) % this.size();
        if (current == 0 && randomOrder) Collections.shuffle(this);
    }

    public void saveAsM3U (String pathname) {
        FileWriter writer = null;
        String separator = System.getProperty("line.separator");

        try {
            writer = new FileWriter(pathname);
            writer.write("# created on: " + new Date() + separator);

            for (AudioFile audioFile : this) {
                writer.write(audioFile.getPathname() + separator);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file " + pathname + ": " + e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    public void loadFromM3U (String pathname) {
        Scanner scanner = null;
        AudioFile audioFile = null;
        String line;
        this.clear();

        try {
            scanner = new Scanner(new File(pathname));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                try {
                    audioFile = AudioFileFactory.getInstance(line);
                } catch (NotPlayableException e) {
                    e.printStackTrace();
                }

                if (audioFile != null) this.add(audioFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (scanner != null) scanner.close();
        }
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isRandomOrder() {
        return randomOrder;
    }

    public void setRandomOrder(boolean randomOrder) {
        this.randomOrder = randomOrder;
        if (randomOrder) Collections.shuffle(this);
    }
}
