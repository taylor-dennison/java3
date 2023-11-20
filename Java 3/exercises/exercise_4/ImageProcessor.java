import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageProcessor {
   static int processPixel(int p, String type) {
      // add your code here
      // unpack values from the pixel
      int alpha = (p >> 24) & 0xFF;
      int red = (p >> 16) & 0xFF;
      int green = (p >> 8) & 0xFF;
      int blue = p & 0xFF;

      // process pixel based on type

      if (type.equals("negative")) {
         red = 255 - red;
         green = 255 - green;
         blue = 255 - blue;
      }
      
      if (type.equals("grayscale")) {
         int average = (red + green +blue) / 3;
         red = average;
         green = average;
         blue = average;
      }
       if (type.equals("sepia")) {
         Double newRed = (0.393 * red) + (0.769 * green) + (0.189 * blue);
         Double newGreen = (0.349 * red) + (0.686 * green) + (0.168 * blue);
         Double newBlue = (0.272 * red) + (0.534 * green) + (0.131 * blue);
        
         if (newRed > 255) {
            newRed = 255.0;
         }
         if (newGreen > 255) {
            newGreen = 255.0;
         }
         if (newBlue > 255) {
            newBlue = 255.0;
         }
         red = newRed.intValue();
         green = newGreen.intValue();
         blue = newBlue.intValue();
      }

      // pack values back into the pixel
      p = 0;
      p = (alpha << 24) | p;
      p = (red << 16 ) | p;
      p = (green << 8) | p;
      p = blue | p;
    

      return p;
   }

   static void processImage(String inFilename, String outFilename, String type) {
      BufferedImage img = null;
      File f = null;

      // load image
      try {
         f = new File(inFilename);
         img = ImageIO.read(f);
      } catch (IOException e) {
         System.out.println(e);
      }

      // get image width and height
      int width = img.getWidth();
      int height = img.getHeight();

      // process all pixels in the image
      for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++) {
            int p = img.getRGB(x, y);
            p = processPixel(p, type);
            img.setRGB(x, y, p);
         }
      }

      // write image to file
      try {
         f = new File(outFilename);
         ImageIO.write(img, "jpg", f);
      } catch (IOException e) {
         System.out.println(e);
      }
   }

   public static void main(String args[]) {
      processImage("Taj.jpg", "Taj_negative.jpg", "negative");
      processImage("Taj.jpg", "Taj_sepia.jpg", "sepia");
      processImage("Taj.jpg", "Taj_grayscale.jpg", "grayscale");
   }
}
