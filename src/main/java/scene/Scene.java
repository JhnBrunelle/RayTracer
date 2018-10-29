package scene;

import lombok.Value;
import math.Vector3;

import java.util.List;

@Value
public class Scene {
    Vector3 camera;
    ImagePlane ImagePlane;
    Color ambientLight;
    List<Light> lights;
    List<SceneObject> objects;
}
