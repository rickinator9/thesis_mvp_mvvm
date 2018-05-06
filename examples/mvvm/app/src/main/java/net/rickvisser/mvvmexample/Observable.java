package net.rickvisser.mvvmexample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick on 5-5-2018.
 */

public class Observable<T> {

    private T mValue;

    private List<IObserver<T>> mObservers;

    public Observable(T pValue) {
        mObservers = new ArrayList<>();

        set(pValue);
    }

    public T get() {
        return mValue;
    }

    public void set(T pValue) {
        if(pValue.equals(mValue)) return; // Values are the same, so no need to change anything.

        // Notify all observers of the change in values.
        for (IObserver<T> observer : mObservers) {
            observer.onValueChanged(mValue, pValue);
        }

        mValue = pValue;
    }

    public void startObserving(IObserver<T> pObserver) {
        mObservers.add(pObserver);

        pObserver.onValueChanged(null, mValue);
    }

    public void stopObserving(IObserver<T> pObserver) {
        mObservers.remove(pObserver);
    }

    public interface IObserver<T> {
        void onValueChanged(T pOldValue, T pNewValue);
    }
}
