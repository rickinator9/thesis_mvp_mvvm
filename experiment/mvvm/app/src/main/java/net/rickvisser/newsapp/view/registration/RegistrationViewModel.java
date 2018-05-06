package net.rickvisser.newsapp.view.registration;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import net.rickvisser.newsapp.model.User;
import net.rickvisser.newsapp.mvvm.IObservable;
import net.rickvisser.newsapp.mvvm.Observable;

/**
 * Created by Rick on 6-5-2018.
 */

public class RegistrationViewModel implements IRegistrationViewModel {
    private IObservable<Boolean> mIsLoading;
    private IObservable<Boolean> mIsRegistered;
    private IObservable<String> mError;

    private UsernameTextWatcher mUsernameTextWatcher;
    private EmailTextWatcher mEmailTextWatcher;
    private PasswordTextWatcher mPasswordTextWatcher;
    private RegisterClickListener mRegisterClickListener;

    private String mUsername;
    private String mEmail;
    private String mPassword;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;

    public RegistrationViewModel() {
        mIsLoading = new Observable<>(false);
        mIsRegistered = new Observable<>(false);
        mError = new Observable<>("");

        mUsernameTextWatcher = new UsernameTextWatcher();
        mEmailTextWatcher = new EmailTextWatcher();
        mPasswordTextWatcher = new PasswordTextWatcher();
        mRegisterClickListener = new RegisterClickListener();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public IObservable<Boolean> isLoading() {
        return mIsLoading;
    }

    @Override
    public IObservable<Boolean> isRegistered() {
        return mIsRegistered;
    }

    @Override
    public IObservable<String> getError() {
        return mError;
    }

    @Override
    public TextWatcher getUsernameTextWatcher() {
        return mUsernameTextWatcher;
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
    public View.OnClickListener getRegisterClickListener() {
        return mRegisterClickListener;
    }

    private class UsernameTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

        }

        @Override
        public void onTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

        }

        @Override
        public void afterTextChanged(Editable pEditable) {
            mUsername = pEditable.toString();
        }
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

    private class RegisterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View pView) {
            mIsLoading.set(true);

            mFirebaseAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> pTask) {
                    mIsLoading.set(false);

                    if (pTask.isSuccessful()) {
                        String userId = pTask.getResult().getUser().getUid();
                        User user = new User(mUsername, mEmail);

                        mFirebaseDatabase.getReference("users").child(userId).setValue(user);

                        mIsRegistered.set(true);
                    } else {
                        mIsRegistered.set(false);
                        mError.set(pTask.getException().getMessage());
                    }
                }
            });
        }
    }
}