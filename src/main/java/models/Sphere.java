/**
 *
 * Creates a Sphere Model
 *
 */

package models;

import math.Vector3;
import scene.SceneObject;
import raytracer.Ray;
import lombok.Value;

import java.util.Optional;

@Value
public class Sphere implements SceneObject {
    Vector3 center;
    float radius;
    Material material;

    @Override
    public Optional<Float> earliestIntersection(Ray ray) {
        Vector3 cPrime = ray.getOrigin().minus(center);
        float a = ray.getDirection().dotProduct(ray.getDirection());
        float b = 2 * cPrime.dotProduct(ray.getDirection());
        float c = cPrime.dotProduct(cPrime) - radius * radius;

        float discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return Optional.empty();
        }

        float sqrt = (float) Math.sqrt(discriminant);
        float min = Math.min(
                (-b + sqrt) / (2 * a),
                (-b - sqrt) / (2 * a)
        );

        return min > 0 ?
                Optional.of(min) :
                Optional.empty();
    }

    @Override
    public Vector3 normalAt(Vector3 point) {
        return point.minus(center).normalized();
    }
}
