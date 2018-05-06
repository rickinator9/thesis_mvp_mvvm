package net.rickvisser.mvvmexample;

import android.view.View;

/**
 * Created by Rick on 5-5-2018.
 */

public interface IProjectViewModel {
    Observable<Boolean> isLoading();
    Observable<Boolean> hasFoundProject();
    Observable<String> getProjectName();
    Observable<String> getBudget();
    Observable<String> getDeadline();

    View.OnClickListener getRetrieveProjectClickListener();
}
