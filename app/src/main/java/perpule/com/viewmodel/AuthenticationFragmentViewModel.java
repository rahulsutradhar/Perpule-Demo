package perpule.com.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import io.fabric.sdk.android.Fabric;
import perpule.com.Perpule;
import perpule.com.R;
import perpule.com.fragment.AuthentictionFragment;
import perpule.com.model.FacebookUser;

/**
 * Created by developers on 03/02/2017 AD.
 */

public class AuthenticationFragmentViewModel extends BaseViewModel {

    /**
     * Fragment
     */
    private AuthentictionFragment authentictionFragment;

    /**
     * Facebook CallbackManager
     */
    private CallbackManager mCallbackManager;
    private List<String> permissionNeeds = Arrays.asList("public_profile", "email");

    /**
     * Fabric Digit
     */
    private DigitsAuthButton digitsAuthButton;


    /**
     * Constructor
     */
    public AuthenticationFragmentViewModel(AuthentictionFragment authentictionFragment) {
        this.authentictionFragment = authentictionFragment;

    }

    public void bindLayout() {
        digitsAuthButton = (DigitsAuthButton) authentictionFragment.getActivity().findViewById(R.id.auth_button);

        digitCallback();

    }

    /**
     * Facebook Login
     */
    public void onClickFacebookLogin() {

        doTheFacebookLogin();
    }


    public void doTheFacebookLogin() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(authentictionFragment.getActivity(), permissionNeeds);
        LoginManager.getInstance().registerCallback(mCallbackManager, mCallback);

    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {


            //facebook Access Token
            if (loginResult.getAccessToken() != null) {
                Set<String> deniedPermissions = loginResult.getRecentlyDeniedPermissions();

                if (deniedPermissions.contains("email") || deniedPermissions.contains("public_profile")) {
                    LoginManager.getInstance().logInWithReadPermissions(authentictionFragment.getActivity(), permissionNeeds);

                } else if (deniedPermissions.isEmpty() == true) {

                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                                    //parse Json object
                                    parseGraphObject(jsonObject.toString());
                                }
                            });
                    Bundle parameters = new Bundle();
                    //parameters.putString("fields", "id,first_name,last_name,email,picture");
                    parameters.putString("fields", "id,first_name,last_name,email");
                    request.setParameters(parameters);
                    request.executeAsync();
                }
            }


        }

        @Override
        public void onCancel() {

            Toast.makeText(authentictionFragment.getActivity(), "You have cancelled.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(FacebookException e) {
            Toast.makeText(authentictionFragment.getActivity(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
        }

    };

    /**
     * Parse the graph Json object send by facebook
     */
    public void parseGraphObject(String jsonString) {

        try {

            Gson gson = new GsonBuilder().create();
            FacebookUser facebookUser = gson.fromJson(jsonString, FacebookUser.class);

            if (facebookUser != null) {
                //transact to next fragment and display the details
                Toast.makeText(authentictionFragment.getActivity(), "Successfuly Logged in as " + facebookUser.getName() + " ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(authentictionFragment.getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(authentictionFragment.getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Reference passing of the call back methord override in Activity via fragment
     */
    public void setActivityResutlToCallback(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * Set digit callback methord
     */
    public void digitCallback() {

        digitsAuthButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(Perpule.getContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(DigitsException error) {
                Log.d("Digits", "Sign in with Digits failure", error);
                Toast.makeText(Perpule.getContext(), "Authentication failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}
