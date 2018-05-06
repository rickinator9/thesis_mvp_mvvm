package net.rickvisser.newsapp.view.login;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.rickvisser.newsapp.R;
import net.rickvisser.newsapp.view.base.BaseFragment;
import net.rickvisser.newsapp.view.base.MvpFragment;
import net.rickvisser.newsapp.view.registration.RegistrationFragment_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Rick on 6-5-2018.
 */

@EFragment(R.layout.fragment_login)
public class LoginFragment extends MvpFragment<ILoginPresenter> implements ILoginView {

    @ViewById(R.id.email)
    EditText mEmailEditText;

    @ViewById(R.id.password)
    EditText mPasswordEditText;

    @ViewById(R.id.login_button)
    Button mLoginButton;

    @ViewById(R.id.register_button)
    Button mRegisterButton;

    @Override
    public void onLoadingStarted() {
        super.onLoadingStarted();

        mEmailEditText.setEnabled(false);
        mPasswordEditText.setEnabled(false);
        mLoginButton.setEnabled(false);
        mRegisterButton.setEnabled(false);
    }

    @Override
    public void onLoadingFinished() {
        super.onLoadingFinished();

        mEmailEditText.setEnabled(true);
        mPasswordEditText.setEnabled(true);
        mLoginButton.setEnabled(true);
        mRegisterButton.setEnabled(true);
    }

    @Override
    protected ILoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(String pError) {
        Toast.makeText(getContext(), pError, Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.login_button)
    protected void onLoginClicked() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        mPresenter.login(email, password);
    }

    @Click(R.id.register_button)
    protected void onRegisterClicked() {
        goToFragment(RegistrationFragment_.builder().build(), false);
    }
}
