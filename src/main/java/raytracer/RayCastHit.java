/**
 * Defines a Ray hitting the object
 */
package raytracer;

import scene.SceneObject;
import math.Vector3;
import lombok.Value;

@Value
class RayCastHit {
    SceneObject object;     // Object to hit
    float t;
    Vector3 normal;         // Normal to the hit
}
