package net.rickvisser.newsapp.view.registration;

import android.text.TextWatcher;
import android.view.View;

import net.rickvisser.newsapp.mvvm.IObservable;
import net.rickvisser.newsapp.mvvm.IViewModel;

/**
 * Created by Rick on 6-5-2018.
 */

public interface IRegistrationViewModel extends IViewModel {
    IObservable<Boolean> isLoading();

    IObservable<Boolean> isRegistered();

    IObservable<String> getError();

    TextWatcher getUsernameTextWatcher();

    TextWatcher getEmailTextWatcher();

    TextWatcher getPasswordTextWatcher();

    View.OnClickListener getRegisterClickListener();
}
