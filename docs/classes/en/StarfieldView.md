# Class: StarfieldView

## 1. General Information
*   **Class Name:** `StarfieldView`
*   **Type:** Normal Class (Custom View)
*   **Purpose:** This class is the "canvas" and the "engine" of the app. It is responsible for drawing the entire night sky and managing the animation of all 150 stars. It handles the math for creating star shapes and ensures they move smoothly.
*   **Interaction:** 
    *   It is placed inside the `activity_main.xml` layout.
    *   It creates and manages a list of `Star` objects.
    *   It interacts with the Android `Canvas` system to paint graphics on the screen.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `stars` | `List<Star>` | A collection (list) that holds all 150 star objects. | `generateStars()`, `onDraw()` |
| `paint` | `Paint` | The "brush" used to draw the stars. Defines color and smoothness. | `init()`, `onDraw()` |
| `random` | `Random` | A tool to generate random numbers for positions and sizes. | `generateStars()` |
| `NUM_STARS` | `int` (static) | A constant set to 150, defining how many stars to create. | `generateStars()` |
| `starPath` | `Path` | A reusable geometric shape object used to define the star's lines. | `createStarPath()` |

## 3. Class Methods

### Method Name: `StarfieldView` (Constructors)
*   **Type:** `public`
*   **Parameters:** `Context context`, `AttributeSet attrs` (optional).
*   **What does it do:** These are standard constructors required by Android. They call `init()` to prepare the view.
*   **When called:** When the app starts and the layout is loaded.

### Method Name: `init`
*   **Type:** `private`
*   **Return Value:** `void`
*   **What does it do:** 
    1.  Sets the brush (`paint`) color to white.
    2.  Enables "anti-alias" (which makes the edges of the stars look smooth instead of pixelated).
    3.  Calls `generateStars()` to create the star data.
*   **When called:** Once, when the view is first created.

### Method Name: `generateStars`
*   **Type:** `private`
*   **What does it do:** 
    1.  Calculates the screen width and height.
    2.  Runs a loop 150 times.
    3.  Each time, it picks random values for position, size, speed, and rotation.
    4.  Creates a new `Star` object and adds it to the `stars` list.
*   **When called:** During initialization.

### Method Name: `createStarPath`
*   **Type:** `private`
*   **Return Value:** `Path` (The geometric shape of the star).
*   **Parameters:** `cx`, `cy` (center), `outerRadius` (size), `points` (5 or 6), `rotation`.
*   **What does it do:** This is the math-heavy part. It calculates the X and Y coordinates for each point of a star (outer points and inner "valleys") and connects them with lines to form a polygon.
*   **When called:** Every time a star needs to be drawn.

### Method Name: `onDraw`
*   **Type:** `protected`
*   **Parameters:** `Canvas canvas` (The screen to draw on).
*   **What does it do:** 
    1.  Fills the background with dark blue.
    2.  Loops through all 150 stars.
    3.  For each star, it sets the brush transparency (`alpha`).
    4.  Calculates the star's current shape via `createStarPath`.
    5.  Draws it on the `canvas`.
    6.  Tells the star to `update()` its state.
    7.  **Animation Loop:** Calls `postInvalidateDelayed(16)`, which tells the system: "Wait 16 milliseconds and then run `onDraw` again."
*   **When called:** Triggered by the system whenever the view needs to be redrawn (continously during animation).
*   **Important:** 16 milliseconds is used because 1000ms / 60 frames = ~16.6ms. This creates a smooth 60 FPS animation.

## 4. Lifecycle
*Not applicable (View, not Activity).*

## 5. Interface Interaction (UI)
*   This class *is* the UI component. It is defined in `activity_main.xml` and fills the entire screen background.

## 6. Interaction with other components
*   **Model Management:** It "owns" the `Star` objects and manages their lifecycle.

## 7. General Logic
1.  **Preparation:** Create 150 stars with random properties.
2.  **Drawing:** Paint the background blue, then paint each star based on its current position and brightness.
3.  **Iteration:** Change the stars' properties slightly and ask the system to redraw immediately.

## 8. Simplified Explanation
**Explanation in simple words:**
Think of `StarfieldView` as a movie projector and a film strip. 
- The `generateStars` method is like filming the scenes. 
- The `onDraw` method is the projector lamp. It shows the current frame, then quickly moves the "actors" (the stars) a tiny bit, and shows the next frame. 
It does this so fast (60 times a second) that your eyes see smooth movement instead of individual pictures.
