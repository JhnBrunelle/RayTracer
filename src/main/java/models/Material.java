/**
 * Defines a new Material from a texture,
 * Textures being a flat png file,
 * Also needs to define the ambient, diffuse, specular and reflected color
 * from the material
 */
package models;

import lombok.Value;
import math.Vector3;
import scene.Color;


@Value
public class Material {

    Color ambient;      // Surrounding Light
    Color diffuse;      // Direct hit on the material
    Color specular;     // White highlight reflection
    Color reflected;    // Reflected light off of the material

    int alpha;          // The Alpha value of the material
    Texture texture;    // Texture

    /**
     * Returns the diffuse color at the normal of the material
     *
     * @param normal the normal
     * @return the diffuse color
     */
    public Color getDiffuseColor(Vector3 normal) {

        if (texture == null) {
            return diffuse;
        }

        // Calculate point from normal
        float u = (float) (0.5f + Math.atan2(normal.getZ(), normal.getX()) / (2 * Math.PI));
        float v = (float) (0.5f - Math.asin(normal.getY()) / Math.PI);

        // Return the color at the point
        return texture.colorAtPixel(u, v);
    }
}
