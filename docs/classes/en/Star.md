# Class: Star

## 1. General Information
*   **Class Name:** `Star`
*   **Type:** Normal Class (Data Model)
*   **Purpose:** This class represents a single star in our animated sky. It acts as a "blueprint" or "container" that holds all the information about one star, such as where it is, how big it is, how bright it is, and how fast it rotates.
*   **Interaction:** It is used by the `StarfieldView` class. The `StarfieldView` creates a large list of these `Star` objects and tells each one to update its state before drawing it.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `x` | `float` | The horizontal position of the star on the screen. | `Constructor`, `StarfieldView.createStarPath` |
| `y` | `float` | The vertical position of the star on the screen. | `Constructor`, `StarfieldView.createStarPath` |
| `size` | `float` | The radius/diameter of the star. | `Constructor`, `StarfieldView.createStarPath` |
| `alpha` | `float` | The current transparency (0.0 = invisible, 1.0 = fully visible). | `Constructor`, `update()`, `StarfieldView.onDraw` |
| `twinkleSpeed` | `float` | How quickly the star changes its brightness. | `Constructor`, `update()` |
| `maxAlpha` | `float` | The maximum brightness the star can reach. | `Constructor`, `update()` |
| `minAlpha` | `float` | The minimum brightness (how dim it gets). | `Constructor`, `update()` |
| `rotation` | `float` | The current angle of the star in degrees. | `Constructor`, `update()`, `StarfieldView.createStarPath` |
| `rotationSpeed` | `float` | How fast the star spins. | `Constructor`, `update()` |
| `points` | `int` | How many points/rays the star has (e.g., 5 or 6). | `Constructor`, `StarfieldView.createStarPath` |
| `isRising` | `boolean` | Tracks if the star is currently getting brighter or dimmer. | `Constructor`, `update()` |

## 3. Class Methods

### Method Name: `Star` (Constructor)
*   **Type:** `public`
*   **Return Value:** None (it's a constructor, it creates the object).
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `x`, `y` | `float` | Position coordinates. |
    | `size` | `float` | Size of the star. |
    | `maxAlpha`, `minAlpha`| `float` | Brightness limits. |
    | `twinkleSpeed` | `float` | Speed of brightness change. |
    | `rotation` | `float` | Initial angle. |
    | `rotationSpeed` | `float` | Speed of spinning. |
    | `points` | `int` | Number of star points. |
*   **What does it do:** It initializes a new star with specific traits. It sets the starting brightness to the minimum and tells the star to start "rising" (getting brighter).
*   **When called:** When `StarfieldView` creates the stars at the start of the app.
*   **What is important:** The constructor allows us to have many stars that all look and behave differently.

### Method Name: `update`
*   **Type:** `public`
*   **Return Value:** `void` (returns nothing).
*   **Parameters:** None.
*   **What does it do:** 
    1.  **Twinkling:** If `isRising` is true, it adds `twinkleSpeed` to `alpha`. If it hits `maxAlpha`, it starts decreasing.
    2.  **Spinning:** It adds `rotationSpeed` to the current `rotation`.
    3.  **Reset:** If the rotation goes over 360 degrees, it resets to 0 to keep the numbers small.
*   **When called:** Automatically by `StarfieldView` inside the animation loop (approx. 60 times per second).
*   **What is important:** This method contains the "math" of the animation. It doesn't draw anything; it just changes the numbers so that the *next* time it is drawn, it looks different.

## 4. Lifecycle
*Not applicable (Normal Class).*

## 5. Interface Interaction (UI)
*   This class does not interact with the UI directly. It only holds the data that `StarfieldView` uses to draw on the screen.

## 6. Interaction with other components
*   **Data Holder:** It passes its properties (like `alpha`, `x`, `y`) to `StarfieldView` when requested.

## 7. General Logic
The `Star` class is like an actor in a play. It knows its position, how bright its costume is, and how to "twinkle." Every time the director (`StarfieldView`) says "Action!" (calls `update`), the actor changes their brightness slightly or spins a bit.

## 8. Simplified Explanation
**Explanation in simple words:**
Imagine a single Christmas light. This class is like the memory of that light. It remembers: "I am at the top left corner," "I am currently half-bright," and "I am spinning slowly." 
Every second, the light checks its rules: "Am I supposed to get brighter? Yes. Okay, I'll turn up my brightness a tiny bit." That is exactly what the `update()` method does.
The class doesn't "know" how to draw itself on a screen; it just keeps track of its own state.
