/**
 * Defines a new light to add to the scene, and takes in the diffuse and specular
 * of the light intensity, as well as the position in 3D space
 */
package scene;

import lombok.Value;
import math.Vector3;

@Value
public class Light {
    Vector3 position;
    Color specular;
    Color diffuse;
}
