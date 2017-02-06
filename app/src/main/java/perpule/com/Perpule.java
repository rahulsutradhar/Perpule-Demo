package perpule.com;

import android.app.Application;
import android.content.Context;

/**
 * Created by developers on 03/02/2017 AD.
 */

public class Perpule extends Application {

    /**
     * Static context from everywhere in the application
     */
    private static Context context;

    /**
     * Constructor
     */
    public Perpule() {
        this.context = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Perpule.context = context;
    }
}
