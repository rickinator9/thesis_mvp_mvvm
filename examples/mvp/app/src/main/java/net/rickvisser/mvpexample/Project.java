package net.rickvisser.mvpexample;

import java.util.Date;

/**
 * Created by Rick on 4-5-2018.
 */

public class Project {
    private String mName;

    private int mBudget;

    private Date mDeadline;

    public Project(String pName, int pBudget, Date pDeadline) {
        this.mName = pName;
        this.mBudget = pBudget;
        this.mDeadline = pDeadline;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        this.mName = pName;
    }

    public int getBudget() {
        return mBudget;
    }

    public void setBudget(int pBudget) {
        this.mBudget = pBudget;
    }

    public Date getDeadline() {
        return mDeadline;
    }

    public void setDeadline(Date pDeadline) {
        this.mDeadline = pDeadline;
    }
}
