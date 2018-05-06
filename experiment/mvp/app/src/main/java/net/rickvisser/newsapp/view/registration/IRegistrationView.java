package net.rickvisser.newsapp.view.registration;

import net.rickvisser.newsapp.mvp.IView;

/**
 * Created by Rick on 6-5-2018.
 */

public interface IRegistrationView extends IView {

    void onRegistrationSuccess();

    void onRegistrationError(String pError);
}
