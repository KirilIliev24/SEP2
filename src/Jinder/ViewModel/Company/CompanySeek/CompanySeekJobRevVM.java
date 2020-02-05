package Jinder.ViewModel.Company.CompanySeek;

import Jinder.model.CompanyModel;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import Jinder.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class CompanySeekJobRevVM
{
    private ViewHandler viewHandler;
    private CompanyModel employerModel;
    private StringProperty onJobReviewId;
    private StringProperty reviewButton;
    private ObservableList<String> jobs;

    public CompanySeekJobRevVM(CompanyModel employerModel, ViewHandler vh)
    {
        this.employerModel = employerModel;
        this.viewHandler = vh;
        jobs = FXCollections.observableArrayList();
        onJobReviewId = new SimpleStringProperty();
        reviewButton = new SimpleStringProperty();
    }

    public ObservableList<String> getItems()
    {
        jobs.clear();
        ArrayList<Job> jobs = employerModel.getJobsMadeByUser();
        for (int i = 0; i < jobs.size(); i++)
        {
            this.jobs.add(jobs.get(i).getJobName());
        }
        return this.jobs;
    }

    public void openCompanySeek()
    {
        User user = employerModel.getUserToReview();
        if (user != null)
        {
            System.out.println(user);
            viewHandler.openCompanySeek();
        } else
        {
            System.out.println("Noone has applied yet");
        }
    }

    public void openCompanyMain()
    {
        viewHandler.openCompanyMain();
    }

    public StringProperty onJobReviewId()
    {
        return onJobReviewId;
    }

    public void displayJob(Integer number2)
    {
        if (number2 == -1)
        {
            onJobReviewId.setValue(null);
        } else
        {
            onJobReviewId.setValue(employerModel.getJobsByIndex(number2).toString());
            reviewButton.setValue("Review Applicants (" + employerModel.getApplicantAmount() + " Applied)");
        }
    }

    public StringProperty getButtonText()
    {
        reviewButton.setValue("Review Applicants");
        return reviewButton;
    }
}
