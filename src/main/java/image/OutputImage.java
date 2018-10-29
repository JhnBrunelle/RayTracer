/**
 * Outputs an Image from the view of the ImagePlane
 */
package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;


public class OutputImage implements Closeable {
    private final BufferedImage image;
    private final Graphics2D graphics;

    /**
     * Instantiates a new OutputImage.
     *
     * @param w the w
     * @param h the h
     */
    public OutputImage(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
    }

    /**
     * Plots a Pixel
     *
     * @param x     the x
     * @param y     the y
     * @param color the color
     */
    public void drawPixel(int x, int y, ImageColor color) {

        graphics.setPaint( new Color(color.getR(), color.getG(), color.getB()));

        // Fill in single pixel
        graphics.fillRect(x, y, 1, 1);
    }

    /**
     * Save.
     *
     * @param file the file
     * @throws IOException the io exception
     */
    public void writeToFile(Path file) throws IOException {
        ImageIO.write(image, "PNG", file.toFile());
    }

    @Override
    public void close() throws IOException {
        graphics.dispose();
    }
}