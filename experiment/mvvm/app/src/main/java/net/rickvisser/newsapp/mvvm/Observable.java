package net.rickvisser.newsapp.mvvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick on 6-5-2018.
 */

public class Observable<T> implements IObservable<T> {
    private T mValue;

    private List<IObserver<T>> mObservers;

    public Observable(T pValue) {
        mObservers = new ArrayList<>();

        set(pValue);
    }

    @Override
    public T get() {
        return mValue;
    }

    @Override
    public void set(T pValue) {
        if(pValue.equals(mValue)) return; // Values are the same, so no need to change anything.

        // Notify all observers of the change in values.
        for (IObserver<T> observer : mObservers) {
            observer.onValueChanged(mValue, pValue);
        }

        mValue = pValue;
    }

    @Override
    public void startObserving(IObserver<T> pObserver) {
        mObservers.add(pObserver);

        pObserver.onValueChanged(null, mValue);
    }

    @Override
    public void stopObserving(IObserver<T> pObserver) {
        mObservers.remove(pObserver);
    }
}
