package net.rickvisser.mvvmexample;

import java.util.Date;

/**
 * Created by Rick on 4-5-2018.
 */

public class Project {
    private Observable<String> mName;

    private Observable<Integer> mBudget;

    private Observable<Date> mDeadline;

    public Project(String pName, int pBudget, Date pDeadline) {
        mName = new Observable<>(pName);
        mBudget = new Observable<>(pBudget);
        mDeadline = new Observable<>(pDeadline);
    }

    public Observable<String> getName() {
        return mName;
    }

    public Observable<Integer> getBudget() {
        return mBudget;
    }

    public Observable<Date> getDeadline() {
        return mDeadline;
    }
}
