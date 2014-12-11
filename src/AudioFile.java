import java.io.File;

public abstract class AudioFile {

    private String pathname;
    private String filename;
    protected String author;
    protected String title;
    // protected String album;
    protected String duration;

    public AudioFile() {

    }
    
    public AudioFile(String pathname) {
    	this.pathname = pathname;
    	parsePathname(pathname);
    	parseFilename(filename);

        File testFile = new File(this.getPathname());
        if (!testFile.canRead()) {
            throw new RuntimeException("Can't read file:" + this.getPathname());
        }
    }

    // media control methods
    public abstract void play();
    public abstract void togglePause();
    public abstract void stop();
    // methods for showing time
    public abstract String getFormattedDuration();
    public abstract String getFormattedPosition();

    public void parsePathname(String pathname) {
        String os = System.getProperty("os.name").toLowerCase();

        if(pathname.isEmpty()) {
            this.pathname = "";
            filename = "";
            return;
        }
        if(pathname.trim().isEmpty()) {
            filename = pathname;
            this.pathname = pathname;
            return;
        }

        if(os.contains("windows")) {
            parsePathnameWindows(pathname);
        } else {
            parsePathnameUnix(pathname);
        }
    }

    public void parsePathnameWindows(String pathname) {
        String sep = File.separator;
        String unixSep = "/";

        while(pathname.contains(unixSep)) {
            pathname = pathname.replace(unixSep, sep);
        }

        while(pathname.contains(sep + sep)) {
            pathname = pathname.replace(sep + sep, sep);
        }

        if(!pathname.contains(sep)) {
            filename = pathname;
            this.pathname = pathname;
            return;
        }

        this.pathname = pathname;
        filename = pathname.substring(pathname.lastIndexOf(sep) + 1);
    }

    public void parsePathnameUnix(String pathname) {
        String sep = File.separator;
        String winSep = "\\";

        while(pathname.contains(winSep)) {
            pathname = pathname.replace(winSep, sep);
        }

        while(pathname.contains(sep + sep)) {
            pathname = pathname.replace(sep + sep, sep);
        }

        if(!pathname.contains(sep)) {
            filename = pathname;
            this.pathname = pathname;
            return;
        }
        
        if(!pathname.startsWith(sep)) {
        	pathname = sep + pathname;
        }

        this.pathname = pathname;
        filename = pathname.substring(pathname.lastIndexOf(sep) + 1);
    }
    
    public void parseFilename(String filename) {
    	String sep = " - ";
    	
    	// catch abnormal filenames
    	if(filename.equals("-")) {
    		author = "";
    		title = "-";
    		return;
    	}
    	if(filename.equals(" - ")) {
    		author = "";
    		title = "";
    		return;
    	}
    	if(!filename.contains(sep)) {
    		author = "";
    		if(filename.trim().isEmpty()){
    			title = "";
    			return;
    		}
    		
    		title = filename.substring(0, filename.lastIndexOf("."));
    		return;
    	}
    	if(!filename.contains(".")) {
    		author = filename.substring(0, filename.indexOf(sep)).trim();
    		title = filename.substring(filename.indexOf(sep)+2).trim();
    		return;
    	}
    	
    	// parsing normal filenames
    	author = filename.substring(0, filename.indexOf(sep)).trim();
    	title = filename.substring(filename.indexOf(sep)+2, filename.lastIndexOf(".")).trim();
    }

    public String getPathname() {
        return pathname;
    }

    public String getFilename() {
        return filename;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public String toString() {
    	if(author.isEmpty()) {
    		return title;
    	}
    	return author + " - " + title;
    }

}