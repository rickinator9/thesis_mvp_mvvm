package net.rickvisser.newsapp.view.base;

import android.support.v4.app.Fragment;

/**
 * Created by Rick on 6-5-2018.
 */

public abstract class BaseFragment extends Fragment {
    protected BaseActivity getBaseActivity() {
        return (BaseActivity)getContext();
    }

    protected void goToFragment(Fragment pFragment, boolean pPushBackstack) {
        getBaseActivity().goToFragment(pFragment, pPushBackstack);
    }

    public void onLoadingStarted() {
        getBaseActivity().onLoadingStarted();
    }

    public void onLoadingFinished() {
        getBaseActivity().onLoadingFinished();
    }
}
