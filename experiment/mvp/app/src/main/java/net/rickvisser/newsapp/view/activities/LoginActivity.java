package net.rickvisser.newsapp.view.activities;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import net.rickvisser.newsapp.R;
import net.rickvisser.newsapp.view.base.BaseActivity;
import net.rickvisser.newsapp.view.login.LoginFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_fragment_container)
public class LoginActivity extends BaseActivity {

    @ViewById(R.id.fragment_container) protected FrameLayout mFragmentContainer;
    @ViewById(R.id.progress_bar) protected ProgressBar mProgressBar;

    @AfterViews
    protected void onViewsInitialized() {
        goToFragment(LoginFragment_.builder().build(), false);
    }

    @Override
    public void onLoadingStarted() {
        mProgressBar.setVisibility(View.VISIBLE);

        mFragmentContainer.setAlpha(0.5f);
        mFragmentContainer.setEnabled(false);
    }

    @Override
    public void onLoadingFinished() {
        mProgressBar.setVisibility(View.GONE);

        mFragmentContainer.setAlpha(1.0f);
        mFragmentContainer.setEnabled(true);
    }
}
