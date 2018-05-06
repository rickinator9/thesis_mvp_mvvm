package net.rickvisser.mvpexample;

/**
 * Created by Rick on 5-5-2018.
 */

public interface IProjectPresenter {
    void onAttach();

    void onDetach();

    void retrieveProject();
}
