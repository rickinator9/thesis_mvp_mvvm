package net.rickvisser.newsapp.view.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.rickvisser.newsapp.R;
import net.rickvisser.newsapp.mvvm.IObservable;
import net.rickvisser.newsapp.view.base.MvvmFragment;
import net.rickvisser.newsapp.view.login.LoginFragment_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Rick on 6-5-2018.
 */

@EFragment(R.layout.fragment_register)
public class RegistrationFragment extends MvvmFragment<IRegistrationViewModel> {

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

    private LoadingObserver mLoadingObserver;
    private RegisteredObserver mRegisteredObserver;
    private ErrorObserver mErrorObserver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingObserver = new LoadingObserver();
        mRegisteredObserver = new RegisteredObserver();
        mErrorObserver = new ErrorObserver();
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

    @Click(R.id.login_button)
    protected void onLoginClicked() {
        goToFragment(LoginFragment_.builder().build(), false);
    }

    @Override
    protected void startObserving() {
        mViewModel.isLoading().startObserving(mLoadingObserver);
        mViewModel.isRegistered().startObserving(mRegisteredObserver);
        mViewModel.getError().startObserving(mErrorObserver);

        mUserNameEditText.addTextChangedListener(mViewModel.getUsernameTextWatcher());
        mEmailEditText.addTextChangedListener(mViewModel.getEmailTextWatcher());
        mPasswordEditText.addTextChangedListener(mViewModel.getPasswordTextWatcher());
        mRegisterButton.setOnClickListener(mViewModel.getRegisterClickListener());
    }

    @Override
    protected void stopObserving() {
        mViewModel.isLoading().stopObserving(mLoadingObserver);
        mViewModel.isRegistered().stopObserving(mRegisteredObserver);
        mViewModel.getError().stopObserving(mErrorObserver);

        mUserNameEditText.removeTextChangedListener(mViewModel.getUsernameTextWatcher());
        mEmailEditText.removeTextChangedListener(mViewModel.getEmailTextWatcher());
        mPasswordEditText.removeTextChangedListener(mViewModel.getPasswordTextWatcher());
        mRegisterButton.setOnClickListener(null);
    }

    @Override
    protected IRegistrationViewModel createViewModel() {
        return new RegistrationViewModel();
    }

    private class LoadingObserver implements IObservable.IObserver<Boolean> {
        @Override
        public void onValueChanged(Boolean pOldValue, Boolean pNewValue) {
            if(pNewValue) onLoadingStarted();
            else onLoadingFinished();
        }
    }

    private class RegisteredObserver implements IObservable.IObserver<Boolean> {
        @Override
        public void onValueChanged(Boolean pOldValue, Boolean pNewValue) {
            if(pNewValue) goToFragment(LoginFragment_.builder().build(), false);
        }
    }

    private class ErrorObserver implements IObservable.IObserver<String> {
        @Override
        public void onValueChanged(String pOldValue, String pNewValue) {
            if(pNewValue != null && pNewValue.length() > 0) Toast.makeText(getContext(), pNewValue, Toast.LENGTH_SHORT).show();
        }
    }
}