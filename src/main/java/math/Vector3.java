/**
 * Defines a Vector3 Coordinate, meant to mimic Vector3 in C++
 * This class comes in handy, since we're working in 3D space
 */
package math;

import lombok.Value;


@Value
public class Vector3 {


    // Defines three coordinates to create the vector
    float x;
    float y;
    float z;


    /**
     * Multiply the vector by a constant.
     *
     * @param c the c
     * @return the vector 3
     */
    public Vector3 multiply(float c) {
        return new Vector3(x * c, y * c, z * c);
    }

    /**
     * Add two vectors together.
     *
     * @param vec the vec
     * @return the vector 3
     */
    public Vector3 add(Vector3 vec) {
        return new Vector3( x + vec.x, y + vec.y, z + vec.z);
    }

    /**
     * Minus vector 3.
     *
     * @param vec the vec
     * @return the vector 3
     */
    public Vector3 minus(Vector3 vec) {
        return new Vector3( x - vec.x, y - vec.y, z - vec.z);
    }

    /**
     * Reverse Polarity of the vector
     *
     * @return the vector 3 with reverse polarity
     */
    public Vector3 inverted() {
        return this.multiply(-1);
    }

    /**
     * Take the dotProduct product of two vectors
     *
     * @param vec the vec
     * @return the float
     */
    public float dotProduct(Vector3 vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }

    /**
     * Take the norm of a vector
     *
     * @return the float
     */
    public float norm() {
        return (float) Math.sqrt(this.dotProduct(this));
    }

    /**
     * Return the normalized vector.
     *
     * @return the vector 3
     */
    public Vector3 normalized() {
        return this.multiply(1 / norm());
    }

    /**
     * Linear interpolation of a vector.
     *
     * @param start the start
     * @param end   the end
     * @param t     the t
     * @return the vector 3
     */
    public static Vector3 lerp(Vector3 start, Vector3 end, float t) {
        return start.multiply(1 - t).add(end.multiply(t));
    }
}
