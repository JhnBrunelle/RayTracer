/**
 * Defines a texture to be used to map the object
 */
package models;

import image.ImageColor;
import lombok.AccessLevel;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import scene.Color;

import java.io.IOException;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Texture {
    TextureImage image;


    /**
     * Creates a new texture from a file
     *
     * @param resource the resource
     * @return the texture
     * @throws IOException the io exception
     */
    public static Texture fileTexture(String file) throws IOException {
        return new Texture(TextureImage.fromResource(file));
    }

    /**
     * Returns the color at a pixel in the original texture,
     * hence the use of TextureImage
     *
     * @param u the u
     * @param v the v
     * @return the color
     */
    Color colorAtPixel(float u, float v) {

        // Read the pixel
        ImageColor color = image.readPixel( (int) Math.floor(u * image.getWidth()),
                                            (int) Math.floor(v * image.getHeight())
        );


        // Return a normalized color
        return new Color(
                (float) color.getR() / ImageColor.MAX,
                (float) color.getG() / ImageColor.MAX,
                (float) color.getB() / ImageColor.MAX
        );
    }
}
