/**
 * Defines a Ray using a Vector3.
 * A Ray represents a path of light in the 3D Space, from the camera,
 * to a point on the object
 * Two Vector3s are used, one representing it's origin point and the other to
 * indicate it's direction
 */
package raytracer;

import math.Vector3;
import lombok.Value;

@Value
public class Ray {
    Vector3 origin;
    Vector3 direction;

    public Vector3 at(float t) {
        return origin.add(direction.multiply(t));
    }
}
