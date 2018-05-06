package net.rickvisser.newsapp.mvp;

/**
 * Created by Rick on 6-5-2018.
 */

public abstract class BasePresenter<T extends IView> implements IPresenter {
    private boolean mIsViewAttached = false;
    private T mView;

    protected BasePresenter(T pView) {
        mView = pView;
    }

    @Override
    public void onAttach() {
        mIsViewAttached = true;
    }

    @Override
    public void onDetach() {
        mIsViewAttached = false;
    }

    protected boolean isViewAttached() {
        return mIsViewAttached;
    }

    protected T getView() {
        return mView;
    }
}
