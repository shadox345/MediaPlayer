import static org.junit.Assert.*;

import org.junit.Test;
import java.io.File;

public class UTestAudioFile {
    @Test
    public void test_parsePathname_03() throws Exception {
        AudioFile af = new AudioFile();
        af.parsePathname("/my-tmp/file.mp3");
        char sepchar = File.separatorChar;

        assertEquals("Pathname stored incorrectly",
                    sepchar + "my-tmp" + sepchar + "file.mp3",
                    af.getPathname());
        
        assertEquals("Returned filename is incorrect", "file.mp3",
                	af.getFilename());
    }
    
    @Test
    public void test_parseFilename_38() throws Exception {
    	AudioFile af = new AudioFile();
    	
    	af.parsePathname("/tmp/test/  A.U.T.O.R  -  T.I.T.E.L  .EXTENSION");
    	af.parseFilename(af.getFilename());
    	
    	assertEquals("Filename stored incorrectly",
    			"  A.U.T.O.R  -  T.I.T.E.L  .EXTENSION",
    			af.getFilename());
    	assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
    	assertEquals("Title stored incorrectly", "T.I.T.E.L", af.getTitle());
    }
}
