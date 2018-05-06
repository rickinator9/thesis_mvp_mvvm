package net.rickvisser.newsapp.view.login;

import android.text.TextWatcher;
import android.view.View;

import net.rickvisser.newsapp.mvvm.IObservable;
import net.rickvisser.newsapp.mvvm.IViewModel;

/**
 * Created by Rick on 6-5-2018.
 */

public interface ILoginViewModel extends IViewModel {
    IObservable<Boolean> isLoading();

    IObservable<Boolean> isLoggedIn();

    IObservable<String> getError();

    TextWatcher getEmailTextWatcher();

    TextWatcher getPasswordTextWatcher();

    View.OnClickListener getLoginClickListener();
}
