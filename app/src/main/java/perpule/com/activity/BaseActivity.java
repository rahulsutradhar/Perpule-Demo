package perpule.com.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import perpule.com.R;
import perpule.com.fragment.AuthentictionFragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        transactToFragment();

    }

    /**
     * Transact to fragment
     */
    public void transactToFragment() {

        AuthentictionFragment authentictionFragment = new AuthentictionFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.container_main, authentictionFragment, "AUTH_FRAGMENT");
        fragmentTransaction.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            AuthentictionFragment fragment = (AuthentictionFragment) getSupportFragmentManager().findFragmentByTag("AUTH_FRAGMENT");

            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
