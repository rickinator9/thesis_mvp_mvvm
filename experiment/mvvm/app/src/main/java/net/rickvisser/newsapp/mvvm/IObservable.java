package net.rickvisser.newsapp.mvvm;

import java.util.ArrayList;

/**
 * Created by Rick on 6-5-2018.
 */

public interface IObservable<T> {
    T get();
    void set(T pValue);

    void startObserving(IObserver<T> pObserver);
    void stopObserving(IObserver<T> pObserver);

    interface IObserver<T> {
        void onValueChanged(T pOldValue, T pNewValue);
    }
}
