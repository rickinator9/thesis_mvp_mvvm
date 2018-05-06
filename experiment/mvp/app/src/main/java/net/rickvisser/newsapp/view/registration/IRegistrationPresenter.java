package net.rickvisser.newsapp.view.registration;

import net.rickvisser.newsapp.mvp.IPresenter;

/**
 * Created by Rick on 6-5-2018.
 */

public interface IRegistrationPresenter extends IPresenter {

    void register(String pUserName, String pEmail, String pPassword);
}
