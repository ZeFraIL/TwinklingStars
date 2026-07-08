# Class: MainActivity

## 1. General Information
*   **Class Name:** `MainActivity`
*   **Type:** Activity
*   **Purpose:** This is the "Host" or "Manager" of the application. It is the first screen that opens when a user clicks the app icon. Its job is to set up the window where everything else happens.
*   **Interaction:** 
    *   It loads the `activity_main.xml` file.
    *   It acts as the parent container for the `StarfieldView`.

## 2. Variables (Class Fields)
*   *None.* This specific activity is very simple and doesn't store its own data variables.

## 3. Class Methods

### Method Name: `onCreate`
*   **Type:** `protected`
*   **Return Value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `savedInstanceState` | `Bundle` | Stores data if the app was restarted (e.g., after rotating the phone). |
*   **What does it do:** 
    1.  Calls the parent version of `onCreate` (mandatory).
    2.  Sets the visual layout using `setContentView(R.layout.activity_main)`. This tells Android: "Show the XML file I designed on the screen."
*   **When called:** Exactly once, when the app is launched.
*   **What is important:** If you forget to call `setContentView`, the user will just see a blank white screen.

## 4. Lifecycle (Activity ONLY)
*   **`onCreate()`:** Called when the activity is first created. This is where we setup the layout.
*   **`onStart()` / `onResume()`:** Not explicitly written in the code (it uses default Android behavior), but these happen after `onCreate` to make the screen visible and interactive.
*   **`onPause()` / `onStop()` / `onDestroy()`:** Handled by the system to save battery when the user leaves the app.

## 5. Interface Interaction (UI)
*   **Layout:** Uses `R.layout.activity_main`.
*   **Components:** This activity hosts the `StarfieldView` (custom component) and a `TextView` (the "Twinkling Stars" title) defined in the XML.

## 6. Interaction with other components
*   **Layout Inflation:** It reads the XML and creates the Java objects for the UI.

## 7. General Logic
1.  User clicks the app.
2.  Android system calls `MainActivity`.
3.  `MainActivity` says: "Show the `activity_main` layout."
4.  Inside that layout, `StarfieldView` starts its animation.

## 8. Simplified Explanation
**Explanation in simple words:**
`MainActivity` is like the front door and the foundation of a house. When you enter the "house" (the app), the `MainActivity` opens the door and makes sure the walls and furniture (the UI) are in the right place. It doesn't do the "work" (like the stars), but it provides the space where the stars can exist.
