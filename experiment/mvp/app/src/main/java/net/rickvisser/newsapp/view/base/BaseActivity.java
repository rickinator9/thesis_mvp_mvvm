package net.rickvisser.newsapp.view.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.rickvisser.newsapp.R;

/**
 * Created by Rick on 6-5-2018.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public void goToFragment(Fragment pFragment, boolean pPushBack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, pFragment);
        if(pPushBack) transaction.addToBackStack(null);

        transaction.commit();
    }

    public abstract void onLoadingStarted();
    public abstract void onLoadingFinished();
}
