import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * An album is a collection of images. It reads images from a folder on disk,
 * and it can be used to retrieve single images.
 * 
 * The folder name to read from is passed in at construction.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Album
{
    private List<Image> images; 

    /**
     * Constructor for objects of class Album
     */
    public Album(String directoryName)
    {
        images = loadImages(directoryName);
    }

    /**
     * Return a image from this album.
     */
    public Image getImage(int imageNumber)
    {
        return images.get(imageNumber);
    }

    /**
     * Return the number of images in this album.
     */
    public int numberOfImages()
    {
        return images.size();
    }
    
    /**
     * Load the images of all image files in the given directory.
     */
    private List<Image> loadImages(String dirName)
    {
        File dir = new File(dirName);
        if(dir.isDirectory()) {
            File[] allFiles = dir.listFiles();
            List<Image> foundImages = new ArrayList<Image>();

            for(File file : allFiles) {
                //System.out.println("found: " + file);
                Image image = Image.getImage(file.getPath());
                if(image != null) {
                    foundImages.add(image);
                }
            }
            return foundImages;
        }
        else {
            System.err.println("Error: " + dirName + " must be a directory");
            return null;
        }
    }
}
