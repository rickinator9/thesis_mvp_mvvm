package net.rickvisser.newsapp.view.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import net.rickvisser.newsapp.mvp.BasePresenter;

/**
 * Created by Rick on 6-5-2018.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {
    private FirebaseAuth mFirebaseAuth;

    public LoginPresenter(ILoginView pView) {
        super(pView);

        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(final String pEmail, String pPassword) {
        if(isViewAttached()) {
            getView().onLoadingStarted();

            mFirebaseAuth.signInWithEmailAndPassword(pEmail, pPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> pTask) {
                    if(isViewAttached()) {
                        getView().onLoadingFinished();

                        if(pTask.isSuccessful()) {
                            getView().onLoginSuccess();
                        } else {
                            getView().onLoginFailure(pTask.getException().getMessage());
                        }
                    }
                }
            });
        }
    }
}
