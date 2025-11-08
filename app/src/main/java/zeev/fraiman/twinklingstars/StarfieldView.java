package zeev.fraiman.twinklingstars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A custom View that renders a starfield with twinkling and rotating stars.
 * This class manages the creation, animation, and drawing of all the stars on the screen.
 */
public class StarfieldView extends View {
    // --- Fields ---

    /** A list to hold all the Star objects that will be rendered. */
    private List<Star> stars = new ArrayList<>();

    /** The Paint object used for drawing all the stars. Its properties will be modified for each star. */
    private Paint paint = new Paint();

    /** A Random object for generating random properties for the stars (e.g., size, position, speed). */
    private Random random = new Random();

    /** The total number of stars to be generated in the starfield. */
    private static final int NUM_STARS = 150;

    /** A reusable Path object to define the geometric shape of a single star. This is reused for efficiency. */
    private Path starPath = new Path();

    // --- Constructors ---

    /**
     * Constructor for creating a StarfieldView programmatically.
     * @param context The Context the view is running in.
     */
    public StarfieldView(Context context) {
        super(context);
        init();
    }

    /**
     * Constructor for inflating a StarfieldView from XML layout.
     * @param context The Context the view is running in.
     * @param attrs The attributes of the XML tag that is inflating the view.
     */
    public StarfieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // --- Initialization ---

    /**
     * Initializes the view. This method is called from the constructors.
     * It sets up the Paint object and generates the initial set of stars.
     */
    private void init() {
        // Set default paint properties for the stars.
        paint.setColor(0xFFFFFFFF); // White color for the stars
        paint.setAntiAlias(true);   // Smooths out the edges of the stars
        paint.setStyle(Paint.Style.FILL); // Fill the star shape with color

        // Create the stars
        generateStars();
    }

    /**
     * Creates and populates the list of stars with random properties.
     * This method determines the position, size, and animation characteristics for each star.
     */
    private void generateStars() {
        // Get the screen dimensions to distribute stars across the entire view.
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        // Loop to create the specified number of stars.
        for (int i = 0; i < NUM_STARS; i++) {
            // Random position within the screen bounds.
            float x = random.nextFloat() * width;
            float y = random.nextFloat() * height;

            // Random properties for each star to make them look unique.
            float size = random.nextFloat() * 9 + 5; // Star size between 5 and 14
            float maxAlpha = random.nextFloat() * 0.7f + 0.3f; // Max opacity (brightness)
            float minAlpha = random.nextFloat() * 0.2f; // Min opacity (dimness)
            float twinkleSpeed = random.nextFloat() * 0.015f + 0.003f; // Speed of twinkling
            float rotation = random.nextFloat() * 360; // Initial rotation angle
            float rotationSpeed = (random.nextFloat() - 0.5f) * 0.5f; // Rotation speed (-0.25 to 0.25)
            int points = random.nextBoolean() ? 5 : 6; // Stars can have 5 or 6 points

            // Create a new Star object and add it to the list.
            stars.add(new Star(x, y, size, maxAlpha, minAlpha, twinkleSpeed,
                    rotation, rotationSpeed, points));
        }
    }

    // --- Drawing ---

    /**
     * Creates a geometric path for a star.
     *
     * @param cx The center x-coordinate of the star.
     * @param cy The center y-coordinate of the star.
     * @param outerRadius The distance from the center to the outer points of the star.
     * @param points The number of points the star has.
     * @param rotation The current rotation of the star in degrees.
     * @return A Path object representing the star's shape.
     */
    private Path createStarPath(float cx, float cy, float outerRadius, int points, float rotation) {
        // Reset the path to clear any previous geometry.
        starPath.reset();

        // The inner radius defines how deep the notches between points are.
        float innerRadius = outerRadius * 0.4f;
        // Convert the rotation from degrees to radians for trigonometric functions.
        double rotationRad = Math.toRadians(rotation);

        // Loop through each point of the star (both outer and inner vertices).
        for (int i = 0; i < points * 2; i++) {
            // Calculate the angle for the current vertex.
            double angle = (i * Math.PI / points) - Math.PI / 2 + rotationRad;
            // Alternate between outer and inner radius for the vertices.
            float radius = (i % 2 == 0) ? outerRadius : innerRadius;
            // Calculate the x and y coordinates of the vertex.
            float x = (float) (cx + Math.cos(angle) * radius);
            float y = (float) (cy + Math.sin(angle) * radius);

            // Move to the first point, then draw lines to subsequent points.
            if (i == 0) {
                starPath.moveTo(x, y);
            } else {
                starPath.lineTo(x, y);
            }
        }
        // Close the path to complete the shape.
        starPath.close();
        return starPath;
    }

    /**
     * This method is called by the Android system when it is time to draw the view.
     * It is the core of the animation.
     *
     * @param canvas The canvas on which the background and stars will be drawn.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the background color. A dark blue to simulate a night sky.
        canvas.drawColor(0xFF000033);

        // Iterate through each star in the list and draw it.
        for (Star star : stars) {
            // Set the alpha (opacity) of the paint based on the star's current alpha.
            paint.setAlpha((int) (star.alpha * 255));

            // Create the star's path with its current properties.
            Path path = createStarPath(star.x, star.y, star.size, star.points, star.rotation);
            // Draw the path on the canvas.
            canvas.drawPath(path, paint);

            // Update the star's state (twinkle and rotation) for the next frame.
            star.update();
        }

        // Invalidate the view to trigger a redraw. postInvalidateDelayed is used to create an animation loop.
        // A delay of 16 milliseconds aims for a frame rate of approximately 60 frames per second (1000ms / 16ms ≈ 60fps).
        postInvalidateDelayed(16);
    }
}
