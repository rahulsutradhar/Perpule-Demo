package perpule.com;

import android.app.Application;
import android.content.Context;

import com.digits.sdk.android.Digits;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

/**
 * Created by developers on 03/02/2017 AD.
 */

public class Perpule extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "50oQ2WJRVzIfu81KerSO8oUVq";
    private static final String TWITTER_SECRET = "iV3RztPNj2iG45sVt6lFbD2diuFdMhc4DLgU4X6H43Q2mrnkH0";


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
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());

        //initialize facebook sdk
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Perpule.context = context;
    }
}
