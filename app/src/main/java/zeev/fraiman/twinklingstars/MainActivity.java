package zeev.fraiman.twinklingstars;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The main activity of the application.
 * This activity serves as the entry point and main screen for the user interface.
 * It is responsible for creating and displaying the main layout of the app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up:
     * create views, bind data to lists, etc. This method also provides
     * you with a Bundle containing the activity's previously frozen state,
     * if there was one.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in onSaveInstanceState(Bundle).
     *     Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call the superclass's implementation of onCreate
        super.onCreate(savedInstanceState);

        // Set the user interface layout for this activity.
        // The layout file is defined in the project's "res/layout" directory.
        // R.layout.activity_main refers to the "activity_main.xml" file.
        setContentView(R.layout.activity_main);
    }
}
