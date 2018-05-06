package net.rickvisser.mvpexample;

import android.os.AsyncTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rick on 5-5-2018.
 */

public class ProjectPresenter implements IProjectPresenter {

    private IProjectView mView;

    private boolean mIsViewAttached = false;

    public ProjectPresenter(IProjectView pView) {
        mView = pView;
    }

    @Override
    public void onAttach() {
        mIsViewAttached = true;
    }

    @Override
    public void onDetach() {
        mIsViewAttached = false;
    }

    @Override
    public void retrieveProject() {
        new RetrieveProjectTask().execute();
    }

    private class RetrieveProjectTask extends AsyncTask<Void, Void, Project> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Before the async operation begins, tell the view something is getting loaded.
            if(mIsViewAttached) mView.setIsLoading(true);
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

            if(mIsViewAttached) {
                // Tell the view the loading is done.
                mView.setIsLoading(false);

                if(project != null) { // A project was found.
                    mView.setHasFoundProject(true);

                    // Set the project name.
                    mView.setProjectName(project.getName());

                    // Set the project budget.
                    mView.setBudget("€ " + project.getBudget());

                    // Set the project deadline.
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    mView.setDeadline(format.format(project.getDeadline()));
                } else {
                    mView.setHasFoundProject(false);
                }
            }
        }
    }
}
