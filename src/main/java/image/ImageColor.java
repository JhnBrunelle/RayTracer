/**
 * Defines a Color from the Image, to later be converted to
 * a normalized color in space
 */
package image;

import lombok.Value;

@Value
public class ImageColor {
    public static final int MAX = 0xff;

    int r;
    int g;
    int b;
}