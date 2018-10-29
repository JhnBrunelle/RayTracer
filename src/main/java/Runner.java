/**
 * This Class is simply a runner for the RayTracer
 * and sets up a scene with two spheres to rendered
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import image.OutputImage;
import image.ImageColor;
import math.Vector3;
import models.Material;
import models.Sphere;
import models.Texture;
import scene.*;

/**
 * The type Runner.
 */
public class Runner {

    // The Size of the image
    private static final int W = 1920;
    private static final int H = 1080;

    // Textures to use for the spheres
    private static final Texture MoonTexture = readTexture("/planet1.jpg");
    private static final Texture BallTexture = readTexture("/planet2.jpg");

    // Create a new Scene
    private static final Scene SCENE = new Scene(
            new Vector3(0, 0, 2),
            new ImagePlane(
                    new Vector3(-1.92f,  1.08f, -0.5f),
                    new Vector3( 1.92f,  1.08f, -0.5f),
                    new Vector3(-1.92f, -1.08f, -0.5f),
                    new Vector3( 1.92f, -1.08f, -0.5f)
            ),
            new Color(0.3f, 0.5f, 0.5f),

            // Define Two lights
            Arrays.asList(
                    new Light(
                            new Vector3(-5, 1, 0.5f),
                            new Color(0.4f, 0.4f, 0.4f),
                            new Color(0.8f, 0.8f, 0.8f)
                    ),
                    new Light(
                            new Vector3(5, -1, 0.5f),
                            new Color(0.7f, 0.7f, 0.7f),
                            new Color(0.8f, 0.8f, 0.8f)
                    )
            ),

            // Define two spheres
            Arrays.asList(
                    new Sphere(
                            new Vector3(0, 0, -1.2f),
                            0.4f,
                            new Material(
                                    new Color(0.2f, 0.1f, 0.1f),
                                    new Color(0.4f, 0.1f, 0.1f),
                                    new Color(0.7f, 0.7f, 0.7f),
                                    new Color(0.9f, 0.5f, 0.5f),
                                    100,
                                    MoonTexture
                            )
                    ),
                    new Sphere(
                            new Vector3(1, 0f, -0.8f),
                            0.2f,
                            new Material(
                                    new Color(0.1f, 0.1f, 0.2f),
                                    new Color(0.5f, 0.5f, 0.9f),
                                    new Color(0.7f, 0.7f, 0.7f),
                                    new Color(0.2f, 0.3f, 0.5f),
                                    50,
                                    BallTexture
                            )
                    )
            )
    );

    @SuppressWarnings("SameParameterValue")
    private static Texture readTexture(String filename) {
        try {
            return Texture.fileTexture(filename);
        } catch (IOException e) {
            System.out.println("Couldnt find " + filename);
            throw new RuntimeException(e);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String [] args) throws IOException {
        raytracer.RayTracer tracer =  new raytracer.RayTracer(SCENE, W, H);

        // Write the image
        try (OutputImage outputImage = new OutputImage(W, H)) {
            for (int x = 0; x < W; x++)
                for (int y = 0; y < H; y++) {
                    Color color = tracer.rayTracedPixel(x, y);
                    outputImage.drawPixel(x, y, colorToImageColor(color));
                }

            outputImage.writeToFile(Paths.get(args[0]));
        }
    }

    private static ImageColor colorToImageColor(Color color) {
        return new ImageColor(
                (int) (color.getR() * ImageColor.MAX),
                (int) (color.getG() * ImageColor.MAX),
                (int) (color.getB() * ImageColor.MAX)
        );
    }
}
