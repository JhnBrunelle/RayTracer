
package models;

import image.ImageColor;
import lombok.RequiredArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The type Texture image.
 */
@RequiredArgsConstructor
public class TextureImage {
    private final BufferedImage image;

    /**
     * From resource texture image.
     *
     * @param resource the resource
     * @return the texture image
     * @throws IOException the io exception
     */
    public static TextureImage fromResource(String resource) throws IOException {
        BufferedImage image = ImageIO.read(TextureImage.class.getResourceAsStream(resource));

        return new TextureImage(image);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * Read pixel image color.
     *
     * @param x the x
     * @param y the y
     * @return the image color
     */
    public ImageColor readPixel(int x, int y) {
        int color = image.getRGB(x, y);
        int r = (color & 0x00ff0000) >> 16;
        int g = (color & 0x0000ff00) >> 8;
        int b =  color & 0x000000ff;

        return new ImageColor(r, g, b);
    }
}
