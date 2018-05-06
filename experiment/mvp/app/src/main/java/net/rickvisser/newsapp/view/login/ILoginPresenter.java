package net.rickvisser.newsapp.view.login;

import net.rickvisser.newsapp.mvp.IPresenter;

/**
 * Created by Rick on 6-5-2018.
 */

public interface ILoginPresenter extends IPresenter {

    void login(String pEmail, String pPassword);
}
