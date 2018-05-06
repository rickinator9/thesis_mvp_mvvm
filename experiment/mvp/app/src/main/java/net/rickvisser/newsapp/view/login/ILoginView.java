package net.rickvisser.newsapp.view.login;

import net.rickvisser.newsapp.mvp.IView;

/**
 * Created by Rick on 6-5-2018.
 */

public interface ILoginView extends IView {

    void onLoginSuccess();

    void onLoginFailure(String pError);
}
