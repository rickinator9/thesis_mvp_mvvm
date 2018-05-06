package net.rickvisser.newsapp.view.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.rickvisser.newsapp.R;
import net.rickvisser.newsapp.mvvm.IObservable;
import net.rickvisser.newsapp.view.base.MvvmFragment;
import net.rickvisser.newsapp.view.registration.RegistrationFragment_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Rick on 6-5-2018.
 */

@EFragment(R.layout.fragment_login)
public class LoginFragment extends MvvmFragment<ILoginViewModel> {

    @ViewById(R.id.email)
    EditText mEmailEditText;

    @ViewById(R.id.password)
    EditText mPasswordEditText;

    @ViewById(R.id.login_button)
    Button mLoginButton;

    @ViewById(R.id.register_button)
    Button mRegisterButton;

    private LoadingObserver mLoadingObserver;
    private LoggedInObserver mLoggedInObserver;
    private ErrorObserver mErrorObserver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingObserver = new LoadingObserver();
        mLoggedInObserver = new LoggedInObserver();
        mErrorObserver = new ErrorObserver();
    }

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
    protected ILoginViewModel createViewModel() {
        return new LoginViewModel();
    }

    @Override
    protected void startObserving() {
        mViewModel.isLoading().startObserving(mLoadingObserver);
        mViewModel.isLoggedIn().startObserving(mLoggedInObserver);
        mViewModel.getError().startObserving(mErrorObserver);

        mEmailEditText.addTextChangedListener(mViewModel.getEmailTextWatcher());
        mPasswordEditText.addTextChangedListener(mViewModel.getPasswordTextWatcher());
        mLoginButton.setOnClickListener(mViewModel.getLoginClickListener());
    }

    @Override
    protected void stopObserving() {
        mViewModel.isLoading().stopObserving(mLoadingObserver);
        mViewModel.isLoggedIn().stopObserving(mLoggedInObserver);
        mViewModel.getError().stopObserving(mErrorObserver);

        mEmailEditText.removeTextChangedListener(mViewModel.getEmailTextWatcher());
        mPasswordEditText.removeTextChangedListener(mViewModel.getPasswordTextWatcher());
        mLoginButton.setOnClickListener(null);
    }

    @Click(R.id.register_button)
    protected void onRegisterClicked() {
        goToFragment(RegistrationFragment_.builder().build(), false);
    }

    private class LoadingObserver implements IObservable.IObserver<Boolean> {
        @Override
        public void onValueChanged(Boolean pOldValue, Boolean pNewValue) {
            if(pNewValue) onLoadingStarted();
            else onLoadingFinished();
        }
    }

    private class LoggedInObserver implements IObservable.IObserver<Boolean> {
        @Override
        public void onValueChanged(Boolean pOldValue, Boolean pNewValue) {
            if(pNewValue) Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
        }
    }

    private class ErrorObserver implements IObservable.IObserver<String> {
        @Override
        public void onValueChanged(String pOldValue, String pNewValue) {
            if(pNewValue != null && pNewValue.length() > 0) Toast.makeText(getContext(), pNewValue, Toast.LENGTH_SHORT).show();
        }
    }
}
