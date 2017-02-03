package perpule.com.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.digits.sdk.android.Digits;

import perpule.com.R;
import perpule.com.databinding.AuthenticationFragmentBinding;
import perpule.com.viewmodel.AuthenticationFragmentViewModel;

/**
 * Created by developers on 03/02/2017 AD.
 */

public class AuthentictionFragment extends BaseFragment {

    /**
     * ViewModel
     */
    private AuthenticationFragmentViewModel authenticationFragmentViewModel;

    /**
     * Constructor
     */
    public AuthentictionFragment() {
        //empty body
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //clear the seesion for digit login
        Digits.clearActiveSession();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authentication, container, false);

        AuthenticationFragmentBinding binding = DataBindingUtil.bind(view);
        authenticationFragmentViewModel = new AuthenticationFragmentViewModel(this);
        setAuthenticationFragmentViewModel(authenticationFragmentViewModel);
        binding.setViewModel(authenticationFragmentViewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        authenticationFragmentViewModel.bindLayout();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authenticationFragmentViewModel.setActivityResutlToCallback(requestCode, resultCode, data);
    }

    public AuthenticationFragmentViewModel getAuthenticationFragmentViewModel() {
        return authenticationFragmentViewModel;
    }

    public void setAuthenticationFragmentViewModel(AuthenticationFragmentViewModel authenticationFragmentViewModel) {
        this.authenticationFragmentViewModel = authenticationFragmentViewModel;
    }
}
