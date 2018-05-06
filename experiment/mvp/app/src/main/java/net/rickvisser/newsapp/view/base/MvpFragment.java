package net.rickvisser.newsapp.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.rickvisser.newsapp.mvp.IPresenter;
import net.rickvisser.newsapp.mvp.IView;

/**
 * Created by Rick on 6-5-2018.
 */

public abstract class MvpFragment<T extends IPresenter> extends BaseFragment implements IView {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter.onAttach();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();

        super.onDestroyView();
    }

    protected abstract T createPresenter();
}
