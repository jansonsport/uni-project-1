/**
 * The ImageViewer class implements the logic of the image viewer. 
 * It stores the instructions for each button of the GUI as well as stores data such as the maximum
 * width of images in the album as well as the total images viewed by the user.
 * 
 * @Michael Kölling, David J. Barnes and Joachim Siallagan
 * @2024.10.26
 */

 public class ImageViewer
{    
    private ImageViewerGUI gui;     // the Graphical User Interface
    private Album album;            // album that will be displayed in slideshow
    private int indexInAlbum;       // arbitrary variable that shows the index of current image displayed
    private int imagesViewed;       // total images viewed
    private int totalWidth;         // sum of image widths in the album
    private String status;          // representation of height and width of each image
    /**
     * Create an ImageViewer and display its GUI on screen.
     * Displays first image in collection and its respective height and width
     * initialising all counters
     * borders are set to maximum width and height in respect to third challenge task
     */
    public ImageViewer()
    {
        album = new Album("images");
        if(album.numberOfImages() == 0){
            return;                                         //imageviewer does not open with blank album
        }
        gui = new ImageViewerGUI(this);
        int indexInAlbum = 0;                               // arbitrary variable 
        int imagesViewed = 0;                               // total images viewed, initialised to 0
        int totalWidth = 0;                                 // total width of images, intialised to 0
        int maxWidth = getMaxWidthOrHeight(false);          //false gives width
        int maxHeight = getMaxWidthOrHeight(true);          //true gives height
        gui.setImageSize(maxWidth, maxHeight);              //initialising the image viewer's borders
        showEverything();                                   //display and all necessary variables
    }
    /**
     * displays next image in the collection when the "Next" button is pressed in GUI
     */
    public void nextImage()
    {
        if( album.numberOfImages() > 0){
            indexInAlbum = (indexInAlbum +1) % album.numberOfImages();
        }
        showEverything();
    }

    /**
     * displays previous image in the collection when the "Previous" button is pressed in GUI
     */
    public void previousImage()
    {
        if( album.numberOfImages() > 0){
        if(indexInAlbum == 0){
            indexInAlbum = indexInAlbum + (album.numberOfImages() -1);
        }
        else{
            indexInAlbum--;
        }
        }
        showEverything();
    }

    /**
     * applying the fisheye effect on the current image displayed
     */
    public void fishEye() 
    {
        Image picture = album.getImage(indexInAlbum);
        picture.fishEye();
        gui.showImage(picture);
    }

    /**
     * insctruction to display image as well as the relevant data
     * displays: image, name, image height and image width
     */
    private void showEverything()
    {
        Image picture = album.getImage(indexInAlbum);       //get image from album
        gui.showImage(picture);                             //display image
        gui.showName(picture.getName());                    //display name of current image 
        totalWidth+= picture.getWidth();                    //update totalWidth
        imagesViewed++;                                     //update images viewed
        String dim = picture.getHeight() + " x " + 
                     picture.getWidth() + " pixels";        //dimensions of images   
        gui.showStatus(dim);                                //display dimensions in status bar
    }

    /**
     * first challenge task
     * retrieve the current number of images viewed at a particular instance
     */
    public int getNumberOfImagesViewed()
    {
        return imagesViewed;
    }

    /**
     * second challenge task
     * retrieve the current average width of images viewed at a particular instance
     */
    public int averageImageWidth()
    {
        int avgWidth = 0;
        if(imagesViewed > 0){
            Image picture = album.getImage(indexInAlbum);
            avgWidth = totalWidth / imagesViewed;
        }
        return avgWidth;
    }

    /**
     * part of third challenge task
     * retrieves the width of the image with the greatest width within the album
     * this is to set the border dimensions so that the image viewer does not need to resize
     * as you traverse between images
     * input is either if it is true: that you want height
     * or false: that you dont want height and want width instead
     */
    public int getMaxWidthOrHeight(boolean wantHeight)
    {
        int index = 0;                              //index of image initialised to 0 to start loop
        int maxValue = 0;                           //value of either maximum width or maximum height
        int value;                                  //value of either width or height of an image of
                                                    //a particular index
        while(index < album.numberOfImages() - 1){ 
            Image picture = album.getImage(index);
            if (wantHeight){
                value = picture.getHeight();
            }
            else{
                value = picture.getWidth();
            }
            if(value > maxValue){
                maxValue = value;
            }
            index++;
        }
        return maxValue;
    }
}