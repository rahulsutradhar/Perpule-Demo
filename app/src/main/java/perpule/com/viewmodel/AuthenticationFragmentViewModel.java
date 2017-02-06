package perpule.com.viewmodel;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;

import perpule.com.Perpule;
import perpule.com.R;
import perpule.com.fragment.AuthentictionFragment;
import perpule.com.global.Constant;


/**
 * Created by developers on 03/02/2017 AD.
 */

public class AuthenticationFragmentViewModel extends BaseViewModel {

    /**
     * Fragment
     */
    private AuthentictionFragment authentictionFragment;

    /**
     * Constructor
     */
    public AuthenticationFragmentViewModel(AuthentictionFragment authentictionFragment) {
        this.authentictionFragment = authentictionFragment;

    }

    /**
     * Invite Friends
     */
    public void onClickInvite() {

        onInviteClicked();
    }

    public void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(authentictionFragment.getActivity().getResources().getString(R.string.invitation_title))
                .setMessage(authentictionFragment.getActivity().getResources().getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(authentictionFragment.getActivity().getResources().getString(R.string.invitation_deep_link)))
                .setCustomImage(Uri.parse(authentictionFragment.getActivity().getResources().getString(R.string.invitation_custom_image)))
                .setCallToActionText(authentictionFragment.getActivity().getResources().getString(R.string.invitation_cta))
                .build();

        if (intent.resolveActivity(authentictionFragment.getActivity().getPackageManager()) != null) {
            authentictionFragment.startActivityForResult(intent, Constant.REQUEST_INVITE);
        } else {
            Toast.makeText(Perpule.getContext(), "Sorry No application found to share.", Toast.LENGTH_LONG).show();
        }

    }

}
