package net.rickvisser.mvpexample;

/**
 * Created by Rick on 5-5-2018.
 */

public interface IProjectView {

    void setIsLoading(boolean pIsLoading);

    void setHasFoundProject(boolean pHasFoundProject);

    void setProjectName(String pProjectName);

    void setBudget(String pProjectBudget);

    void setDeadline(String pDeadline);
}
