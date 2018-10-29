/**
 * The Main RayTracer Class, where the magic happens!
 * @TODO - Add more explanations
 */
package raytracer;

import math.Vector3;
import models.Material;
import scene.*;
import lombok.Value;

import java.util.Optional;

/**
 * The type Ray tracer.
 */
@Value
public class RayTracer {
    private static final int BOUNCES = 3;
    private static final int SAMPLEDIRECTIONS = 2;
    private static final int SAMPLESPERPIXEL = SAMPLEDIRECTIONS * SAMPLEDIRECTIONS;


    Scene scene;
    int w;
    int h;

    /**
     * The RayTraced value at the pixel on the object.
     *
     * @param x the x
     * @param y the y
     * @return the color
     */
    public Color rayTracedPixel(int x, int y) {
        float xt = ((float) x) / w;
        float yt = ((float) h - y - 1) / h;

        float dx = 1f / (w * SAMPLEDIRECTIONS);
        float dy = 1f / (h * SAMPLEDIRECTIONS);

        Color color = Color.BLACK;
        for (int xi = 0; xi < SAMPLEDIRECTIONS; xi++) {
            for (int yi = 0; yi < SAMPLEDIRECTIONS; yi++) {
                color = color.add(
                        rayTracedImagePlane(
                                xt + dx * xi,
                                yt + dy * yi
                        )
                );
            }
        }

        return color.divide(SAMPLESPERPIXEL).clamped();
    }

    /**
     * the RayTraced color seen from the image plane
     * @param xt
     * @param yt
     * @return
     */
    private Color rayTracedImagePlane(float xt, float yt) {
        Vector3 top = Vector3.lerp(
                scene.getImagePlane().getTopLeft(),
                scene.getImagePlane().getTopRight(),
                xt
        );

        Vector3 bottom = Vector3.lerp(
                scene.getImagePlane().getBottomLeft(),
                scene.getImagePlane().getBottomRight(),
                xt
        );

        Vector3 point = Vector3.lerp(bottom, top, yt);
        Ray ray = new Ray(
                point,
                point.minus(scene.getCamera())
        );

        return colorFromHit(BOUNCES, ray).clamped();
    }

    /**
     * Returns the color from the Hit of An Object
     * @param numBounces
     * @param ray
     * @return
     */
    private Color colorFromHit(int numBounces, Ray ray) {
        return scene
                .getObjects()
                .stream()
                .map(obj -> obj
                        .earliestIntersection(ray)
                        .map(t -> new RayCastHit(
                                obj,
                                t,
                                obj.normalAt(ray.at(t))
                        )))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min((h0, h1) -> (int) Math.signum(h0.getT() - h1.getT()))
                .map(hit -> {
                    Vector3 point = ray.at(hit.getT());
                    Vector3 view = ray.getDirection().inverted().normalized();

                    Color color = phong(
                            scene,
                            hit.getObject(),
                            point,
                            hit.getNormal(),
                            view
                    );

                    if (numBounces > 0) {
                        Vector3 reflection = hit.getNormal()
                                .multiply(view.dotProduct(hit.getNormal()))
                                .multiply(2)
                                .minus(view);

                        Color reflectedColor = colorFromHit(
                                numBounces - 1,
                                new Ray(point, reflection)
                        );

                        color = color.add(
                                reflectedColor.multiply(hit.getObject()
                                                           .getMaterial()
                                                           .getReflected()
                                )
                        );
                    }

                    return color;
                })
                .orElse(Color.BLACK);
    }

    /**
     * This is an implementation of Phong Shading, designed
     * to simulate illumination from a surface
     *
     * @param scene The Scene to simulate
     * @param object The Object where phong is being applied
     * @param point Point of application
     * @param normal the normal of that point
     * @param view the view
     * @return
     */
    private Color phong(Scene scene, SceneObject object, Vector3 point, Vector3 normal, Vector3 view) {


        Material mat = object.getMaterial();

        Color lightContributions = scene
                .getLights()
                .stream()
                .filter(light ->
                        light.getPosition().minus(point).dotProduct(normal) > 0)
                .filter(light ->
                        !isPointInShadowFromLight(scene, object, point, light))
                .map(light -> {
                    Vector3 l = light.getPosition().minus(point).normalized();
                    Vector3 r = normal.multiply(l.dotProduct(normal) * 2).minus(l);

                    Color diffuse = light
                            .getDiffuse()
                            .multiply(mat.getDiffuseColor(normal))
                            .multiply(l.dotProduct(normal));

                    Color specular = light
                            .getSpecular()
                            .multiply(mat.getSpecular())
                            .multiply((float) Math.pow(
                                    r.dotProduct(view), mat.getAlpha()));

                    return diffuse.add(specular);
                })
                .reduce(Color.BLACK, Color::add);

        Color ambient = mat.getAmbient().multiply(scene.getAmbientLight());

        return ambient.add(lightContributions);
    }

    private boolean isPointInShadowFromLight(
            Scene scene,
            SceneObject objectToExclude,
            Vector3 point,
            Light light) {
        Vector3 direction = light.getPosition().minus(point);
        Ray shadowRay = new Ray(point, direction);

        return scene
                .getObjects()
                .stream()
                .filter(obj -> obj != objectToExclude)
                .map(obj -> obj
                        .earliestIntersection(shadowRay)
                        .map(t -> new RayCastHit(
                                obj,
                                t,
                                null)
                        ))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .anyMatch(hit -> hit.getT() <= 1);
    }
}
