/**
 * This class defines a color Object, consisting of Red, Green and Blue
 * values
 */

package scene;

import lombok.Value;

/**
 * The type Color.
 */
@Value
public class Color {
    /**
     * The constant MAX.
     */
    public static final float MAX = 1;

    /**
     * The constant BLACK.
     */
// Define Black
    public static final Color BLACK = new Color(0, 0, 0);

    /**
     * REd
     */
// Color Values
    float r;
    /**
     * Green
     */
    float g;
    /**
     * Blue
     */
    float b;


    /**
     * Multipliy the color by a constant
     *
     * @param c the num
     * @return the color
     */
    public Color multiply(float c) {
        return new Color(
                r * c,
                g * c,
                b * c
        );
    }

    /**
     * Multiply color.
     *
     * @param other the other
     * @return the color
     */
    public Color multiply(Color other) {
        return new Color(
                r * other.r,
                g * other.g,
                b * other.b
        );
    }

    /**
     * Divide color by a constant
     *
     * @param num the num
     * @return the color
     */
    public Color divide(float num) {
        return new Color(
                r / num,
                g / num,
                b / num
        );
    }

    /**
     * Add colors together
     *
     * @param other the other
     * @return the color
     */
    public Color add(Color other) {
        return new Color(
                r + other.r,
                g + other.g,
                b + other.b
        );
    }

    /**
     * Force colors to be within MAX colors
     *
     * @return the clmaped color
     */
    public Color clamped() {
        return new Color(
                r < 0 ? 0 : r > MAX ? MAX : r,
                g < 0 ? 0 : g > MAX ? MAX : g,
                b < 0 ? 0 : b > MAX ? MAX : b
        );
    }
}
