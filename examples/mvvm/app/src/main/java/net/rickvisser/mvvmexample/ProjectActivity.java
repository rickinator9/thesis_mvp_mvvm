package net.rickvisser.mvvmexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProjectActivity extends AppCompatActivity {

    private IProjectViewModel mViewModel;
    private Observable.IObserver<Boolean> mLoadingObserver;
    private Observable.IObserver<Boolean> mHasFoundProjectObserver;
    private Observable.IObserver<String> mProjectNameObserver;
    private Observable.IObserver<String> mBudgetObserver;
    private Observable.IObserver<String> mDeadlineObserver;

    private View mProgressBar;

    private View mContent;

    private View mProjectView;

    private Button mRetrieveProjectButton;
    private TextView mProjectNameTextView;
    private TextView mBudgetTextView;
    private TextView mDeadlineNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ProjectViewModel();
        mLoadingObserver = new LoadingObserver();
        mHasFoundProjectObserver = new HasFoundProjectObserver();
        mProjectNameObserver = new ProjectNameObserver();
        mBudgetObserver = new BudgetObserver();
        mDeadlineObserver = new DeadlineObserver();

        // Set the layout.
        setContentView(R.layout.activity_project);

        // Find all of the layout elements.
        mProgressBar = findViewById(R.id.progress_bar);
        mContent = findViewById(R.id.content);
        mProjectView = findViewById(R.id.project_view);
        mRetrieveProjectButton = findViewById(R.id.retrieve_project);
        mProjectNameTextView = findViewById(R.id.project_name);
        mBudgetTextView = findViewById(R.id.project_budget);
        mDeadlineNameTextView = findViewById(R.id.project_deadline);

        // Set an event listener on the button.
        mRetrieveProjectButton.setOnClickListener(mViewModel.getRetrieveProjectClickListener());
    }

    @Override
    protected void onStart() {
        super.onStart();

        startObserving();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        startObserving();
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopObserving();
    }

    private void startObserving() {
        mViewModel.isLoading().startObserving(mLoadingObserver);
        mViewModel.hasFoundProject().startObserving(mHasFoundProjectObserver);
        mViewModel.getProjectName().startObserving(mProjectNameObserver);
        mViewModel.getBudget().startObserving(mBudgetObserver);
        mViewModel.getDeadline().startObserving(mDeadlineObserver);
    }

    private void stopObserving() {
        mViewModel.isLoading().stopObserving(mLoadingObserver);
        mViewModel.hasFoundProject().stopObserving(mHasFoundProjectObserver);
        mViewModel.getProjectName().stopObserving(mProjectNameObserver);
        mViewModel.getBudget().stopObserving(mBudgetObserver);
        mViewModel.getDeadline().stopObserving(mDeadlineObserver);
    }

    private class LoadingObserver implements Observable.IObserver<Boolean> {
        @Override
        public void onValueChanged(Boolean pOldValue, Boolean pNewValue) {
            mProgressBar.setVisibility(pNewValue ? View.VISIBLE : View.GONE);
            mContent.setAlpha(pNewValue ? 0.5f : 1.0f);
            mRetrieveProjectButton.setEnabled(!pNewValue);
        }
    }

    private class HasFoundProjectObserver implements Observable.IObserver<Boolean> {
        @Override
        public void onValueChanged(Boolean pOldValue, Boolean pNewValue) {
            mProjectView.setVisibility(pNewValue ? View.VISIBLE : View.GONE);
        }
    }

    private class ProjectNameObserver implements Observable.IObserver<String> {
        @Override
        public void onValueChanged(String pOldValue, String pNewValue) {
            mProjectNameTextView.setText(pNewValue);
        }
    }

    private class BudgetObserver implements Observable.IObserver<String> {
        @Override
        public void onValueChanged(String pOldValue, String pNewValue) {
            mBudgetTextView.setText(pNewValue);
        }
    }

    private class DeadlineObserver implements Observable.IObserver<String> {
        @Override
        public void onValueChanged(String pOldValue, String pNewValue) {
            mDeadlineNameTextView.setText(pNewValue);
        }
    }
}
