package zeev.fraiman.twinklingstars;

/**
 * Represents a single star in the starfield.
 * This class holds all the properties of a star, such as its position, size, and animation characteristics.
 */
public class Star {
    // --- Fields ---

    /** The x-coordinate of the star's position on the screen. */
    float x;
    /** The y-coordinate of the star's position on the screen. */
    float y;

    /** The size (diameter) of the star. */
    float size;

    /** The current alpha (opacity) of the star, ranging from minAlpha to maxAlpha. */
    float alpha;

    /** The speed at which the star's alpha value changes, creating a twinkling effect. */
    float twinkleSpeed;

    /** The maximum alpha (opacity) value for the star, making it fully visible. */
    float maxAlpha;

    /** The minimum alpha (opacity) value for the star, making it almost transparent. */
    float minAlpha;

    /** The current rotation angle of the star in degrees. */
    float rotation;

    /** The speed at which the star rotates. */
    float rotationSpeed;

    /** The number of points the star has (e.g., for a star-shaped polygon). */
    int points;

    /** A boolean flag to determine if the star's alpha is currently increasing (rising) or decreasing. */
    boolean isRising;


    // --- Constructor ---

    /**
     * Constructs a new Star object with specified properties.
     *
     * @param x The initial x-coordinate of the star.
     * @param y The initial y-coordinate of the star.
     * @param size The size of the star.
     * @param maxAlpha The maximum alpha (opacity) for twinkling.
     * @param minAlpha The minimum alpha (opacity) for twinkling.
     * @param twinkleSpeed The speed of the twinkling effect.
     * @param rotation The initial rotation angle.
     * @param rotationSpeed The speed of rotation.
     * @param points The number of points for the star's shape.
     */
    public Star(float x, float y, float size, float maxAlpha, float minAlpha,
                float twinkleSpeed, float rotation, float rotationSpeed, int points) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.maxAlpha = maxAlpha;
        this.minAlpha = minAlpha;
        this.alpha = minAlpha; // Start with minimum alpha
        this.twinkleSpeed = twinkleSpeed;
        this.rotation = rotation;
        this.rotationSpeed = rotationSpeed;
        this.points = points;
        this.isRising = true; // Star starts by getting brighter
    }

    // --- Public Methods ---

    /**
     * Updates the star's state for each frame of the animation.
     * This method handles the twinkling and rotation of the star.
     */
    public void update() {
        // Twinkling effect: gradually change the alpha value
        if (isRising) {
            // If the star is getting brighter, increase alpha
            alpha += twinkleSpeed;
            // If alpha reaches the maximum, reverse the direction
            if (alpha >= maxAlpha) {
                alpha = maxAlpha; // Clamp to max value
                isRising = false; // Start getting dimmer
            }
        } else {
            // If the star is getting dimmer, decrease alpha
            alpha -= twinkleSpeed;
            // If alpha reaches the minimum, reverse the direction
            if (alpha <= minAlpha) {
                alpha = minAlpha; // Clamp to min value
                isRising = true; // Start getting brighter
            }
        }

        // Rotation effect: continuously update the rotation angle
        rotation += rotationSpeed;
        // Reset rotation to 0 after a full circle to avoid large numbers
        if (rotation > 360) {
            rotation = 0;
        }
    }
}
