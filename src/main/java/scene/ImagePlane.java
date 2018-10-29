/**
 * This creates a flat 2D plane which is the viewport of our image.
 * Essentially, it's what will be outputted
 */
package scene;


import lombok.Value;
import math.Vector3;

@Value
public class ImagePlane {

    // Define the 4 corners of the image, as vectors
    Vector3 topLeft;
    Vector3 topRight;
    Vector3 bottomLeft;
    Vector3 bottomRight;
}
