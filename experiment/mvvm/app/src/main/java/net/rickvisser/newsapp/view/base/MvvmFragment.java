package net.rickvisser.newsapp.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.rickvisser.newsapp.mvvm.IViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Rick on 6-5-2018.
 */

@EFragment
public abstract class MvvmFragment<T extends IViewModel> extends BaseFragment {

    protected T mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = createViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel.onAttach();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @AfterViews
    protected void onViewsInitialized() {
        startObserving();
    }

    @Override
    public void onDestroyView() {
        stopObserving();
        mViewModel.onDetach();

        super.onDestroyView();
    }

    protected abstract T createViewModel();

    protected abstract void startObserving();

    protected abstract void stopObserving();
}
