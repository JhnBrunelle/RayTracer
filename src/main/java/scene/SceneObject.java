package scene;

import math.Vector3;
import models.Material;
import raytracer.Ray;

import java.util.Optional;

public interface SceneObject {
    Material getMaterial();
    Optional<Float> earliestIntersection(Ray ray);
    Vector3 normalAt(Vector3 point);
}
