package net.rickvisser.newsapp.view.login;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import net.rickvisser.newsapp.mvvm.IObservable;
import net.rickvisser.newsapp.mvvm.Observable;

/**
 * Created by Rick on 6-5-2018.
 */

public class LoginViewModel implements ILoginViewModel {
    private IObservable<Boolean> mIsLoading;
    private IObservable<Boolean> mIsLoggedIn;
    private IObservable<String> mError;
    private TextWatcher mEmailTextWatcher;
    private TextWatcher mPasswordTextWatcher;
    private View.OnClickListener mLoginClickListener;

    private String mEmail;
    private String mPassword;

    private FirebaseAuth mFirebaseAuth;

    public LoginViewModel() {
        mIsLoading = new Observable<>(false);
        mIsLoggedIn = new Observable<>(false);
        mError = new Observable<>("");

        mEmailTextWatcher = new EmailTextWatcher();
        mPasswordTextWatcher = new PasswordTextWatcher();
        mLoginClickListener = new LoginClickListener();

        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public IObservable<Boolean> isLoading() {
        return mIsLoading;
    }

    @Override
    public IObservable<Boolean> isLoggedIn() {
        return mIsLoggedIn;
    }

    @Override
    public IObservable<String> getError() {
        return mError;
    }

    @Override
    public TextWatcher getEmailTextWatcher() {
        return mEmailTextWatcher;
    }

    @Override
    public TextWatcher getPasswordTextWatcher() {
        return mPasswordTextWatcher;
    }

    @Override
    public View.OnClickListener getLoginClickListener() {
        return mLoginClickListener;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    private class EmailTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

        }

        @Override
        public void onTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

        }

        @Override
        public void afterTextChanged(Editable pEditable) {
            mEmail = pEditable.toString();
        }
    }

    private class PasswordTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

        }

        @Override
        public void onTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

        }

        @Override
        public void afterTextChanged(Editable pEditable) {
            mPassword = pEditable.toString();
        }
    }

    private class LoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View pView) {
            mIsLoading.set(true);

            mFirebaseAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> pTask) {
                    mIsLoading.set(false);
                    if(pTask.isSuccessful()) {
                        mIsLoggedIn.set(true);
                    } else {
                        mIsLoggedIn.set(false);
                        mError.set(pTask.getException().getMessage());
                    }
                }
            });
        }
    }
}
