package net.rickvisser.newsapp.view.base;

import android.support.v4.app.Fragment;

import net.rickvisser.newsapp.mvp.IView;

/**
 * Created by Rick on 6-5-2018.
 */

public abstract class BaseFragment extends Fragment implements IView {
    protected BaseActivity getBaseActivity() {
        return (BaseActivity)getContext();
    }

    protected void goToFragment(Fragment pFragment, boolean pPushBackstack) {
        getBaseActivity().goToFragment(pFragment, pPushBackstack);
    }

    @Override
    public void onLoadingStarted() {
        getBaseActivity().onLoadingStarted();
    }

    @Override
    public void onLoadingFinished() {
        getBaseActivity().onLoadingFinished();
    }
}
