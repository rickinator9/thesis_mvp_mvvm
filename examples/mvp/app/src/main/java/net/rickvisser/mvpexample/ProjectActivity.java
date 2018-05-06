package net.rickvisser.mvpexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectActivity extends AppCompatActivity implements IProjectView {

    private IProjectPresenter mPresenter;

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

        mPresenter = new ProjectPresenter(this);

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
        mRetrieveProjectButton.setOnClickListener(new RetrieveProjectButtonClickListener());
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.onAttach();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        mPresenter.onAttach();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mPresenter.onDetach();
    }

    @Override
    public void setIsLoading(boolean pIsLoading) {
        mProgressBar.setVisibility(pIsLoading ? View.VISIBLE : View.GONE);
        mContent.setAlpha(pIsLoading ? 0.5f : 1.0f);
        mRetrieveProjectButton.setEnabled(!pIsLoading);
    }

    @Override
    public void setHasFoundProject(boolean pHasFoundProject) {
        mProjectView.setVisibility(pHasFoundProject ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setProjectName(String pProjectName) {
        mProjectNameTextView.setText(pProjectName);
    }

    @Override
    public void setBudget(String pProjectBudget) {
        mBudgetTextView.setText(pProjectBudget);
    }

    @Override
    public void setDeadline(String pDeadline) {
        mDeadlineNameTextView.setText(pDeadline);
    }

    private class RetrieveProjectButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mPresenter.retrieveProject();
        }
    }
}
