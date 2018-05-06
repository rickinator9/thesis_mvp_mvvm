package net.rickvisser.newsapp.view.registration;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import net.rickvisser.newsapp.model.User;
import net.rickvisser.newsapp.mvp.BasePresenter;

/**
 * Created by Rick on 6-5-2018.
 */

public class RegistrationPresenter extends BasePresenter<IRegistrationView> implements IRegistrationPresenter {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;

    public RegistrationPresenter(IRegistrationView pView) {
        super(pView);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void register(final String pName, final String pEmail, String pPassword) {
        getView().onLoadingStarted();

        mFirebaseAuth.createUserWithEmailAndPassword(pEmail, pPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> pTask) {
                if(isViewAttached()) {
                    getView().onLoadingFinished();

                    if (pTask.isSuccessful()) {
                        String userId = pTask.getResult().getUser().getUid();
                        User user = new User(pName, pEmail);
                        mFirebaseDatabase.getReference("users").child(userId).setValue(user);

                        getView().onRegistrationSuccess();
                    } else {
                        getView().onRegistrationError(pTask.getException().getMessage());
                    }
                }
            }
        });
    }
}