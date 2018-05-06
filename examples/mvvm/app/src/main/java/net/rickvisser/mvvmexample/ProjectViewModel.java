package net.rickvisser.mvvmexample;

import android.os.AsyncTask;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observer;

/**
 * Created by Rick on 5-5-2018.
 */

public class ProjectViewModel implements IProjectViewModel {
    private Project mProject;

    private Observable<Boolean> mIsLoading;
    private Observable<Boolean> mHasFoundProject;
    private Observable<String> mProjectName;
    private Observable<String> mBudget;
    private Observable<String> mDeadline;

    private Observable.IObserver<String> mProjectNameObserver;
    private Observable.IObserver<Integer> mBudgetObserver;
    private Observable.IObserver<Date> mDeadlineObserver;

    public ProjectViewModel() {
        mIsLoading = new Observable<>(false);
        mHasFoundProject = new Observable<>(false);
        mProjectName = new Observable<>("");
        mBudget = new Observable<>("");
        mDeadline = new Observable<>("");

        mProjectNameObserver = new ProjectNameObserver();
        mBudgetObserver = new BudgetObserver();
        mDeadlineObserver = new DeadlineObserver();
    }

    @Override
    public Observable<Boolean> isLoading() {
        return mIsLoading;
    }

    @Override
    public Observable<Boolean> hasFoundProject() {
        return mHasFoundProject;
    }

    @Override
    public Observable<String> getProjectName() {
        return mProjectName;
    }

    @Override
    public Observable<String> getBudget() {
        return mBudget;
    }

    @Override
    public Observable<String> getDeadline() {
        return mDeadline;
    }

    @Override
    public View.OnClickListener getRetrieveProjectClickListener() {
        return new RetrieveProjectClickListener();
    }

    private void startObserving(Project pProject) {
        pProject.getName().startObserving(mProjectNameObserver);
        pProject.getBudget().startObserving(mBudgetObserver);
        pProject.getDeadline().startObserving(mDeadlineObserver);
    }

    private void stopObserving(Project pProject) {
        pProject.getName().stopObserving(mProjectNameObserver);
        pProject.getBudget().stopObserving(mBudgetObserver);
        pProject.getDeadline().stopObserving(mDeadlineObserver);
    }

    private class ProjectNameObserver implements Observable.IObserver<String> {
        @Override
        public void onValueChanged(String pOldValue, String pNewValue) {
            mProjectName.set(pNewValue);
        }
    }

    private class BudgetObserver implements Observable.IObserver<Integer> {
        @Override
        public void onValueChanged(Integer pOldValue, Integer pNewValue) {
            mBudget.set("€ " + pNewValue);
        }
    }

    private class DeadlineObserver implements Observable.IObserver<Date> {
        @Override
        public void onValueChanged(Date pOldValue, Date pNewValue) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            mDeadline.set(format.format(pNewValue));
        }
    }

    private class RetrieveProjectClickListener implements View.OnClickListener {
        @Override
        public void onClick(View pView) {
            new RetrieveProjectTask().execute();
        }
    }

    private class RetrieveProjectTask extends AsyncTask<Void, Void, Project> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Before the async operation begins, tell the view something is getting loaded.
            mIsLoading.set(true);
        }

        @Override
        protected Project doInBackground(Void... voids) {
            // Wait 1 second to simulate a long operation.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Create a project.
            String projectName = "Create an Android app";
            int budget = 10000;
            Date today = new Date();

            return new Project(projectName, budget, today);
        }

        @Override
        protected void onPostExecute(Project project) {
            super.onPostExecute(project);

            // Tell the view the loading is done.
            mIsLoading.set(false);

            if(project != null) { // A project was found.
                mHasFoundProject.set(true);

                if(mProject != null) stopObserving(mProject);

                startObserving(project);
                mProject = project;
            } else mHasFoundProject.set(false);
        }
    }
}
