package net.rickvisser.newsapp.view.registration;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.rickvisser.newsapp.R;
import net.rickvisser.newsapp.view.base.MvpFragment;
import net.rickvisser.newsapp.view.login.LoginFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Rick on 6-5-2018.
 */

@EFragment(R.layout.fragment_register)
public class RegistrationFragment extends MvpFragment<IRegistrationPresenter> implements IRegistrationView {

    @ViewById(R.id.username)
    EditText mUserNameEditText;

    @ViewById(R.id.email)
    EditText mEmailEditText;

    @ViewById(R.id.password)
    EditText mPasswordEditText;

    @ViewById(R.id.register_button)
    Button mRegisterButton;

    @ViewById(R.id.login_button)
    Button mLoginButton;

    @Override
    public void onRegistrationSuccess() {
        goToFragment(LoginFragment_.builder().build(), false);
    }

    @Override
    public void onRegistrationError(String pError) {
        Toast.makeText(getContext(), pError, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected IRegistrationPresenter createPresenter() {
        return new RegistrationPresenter(this);
    }

    @Override
    public void onLoadingStarted() {
        super.onLoadingStarted();

        mUserNameEditText.setEnabled(false);
        mEmailEditText.setEnabled(false);
        mPasswordEditText.setEnabled(false);
        mLoginButton.setEnabled(false);
        mRegisterButton.setEnabled(false);
    }

    @Override
    public void onLoadingFinished() {
        super.onLoadingFinished();

        mUserNameEditText.setEnabled(true);
        mEmailEditText.setEnabled(true);
        mPasswordEditText.setEnabled(true);
        mLoginButton.setEnabled(true);
        mRegisterButton.setEnabled(true);
    }

    @Click(R.id.register_button)
    protected void onRegisterClicked() {
        String username = mUserNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        mPresenter.register(username, email, password);
    }

    @Click(R.id.login_button)
    protected void onLoginClicked() {
        goToFragment(LoginFragment_.builder().build(), false);
    }
}
