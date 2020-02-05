package Jinder.ViewModel.Candidate.CandidateSeek;

import Jinder.model.CandidateModel;
import Jinder.sharedFiles.Job;
import Jinder.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CandidateSeekVM
{
    private ViewHandler viewHandler;
    private CandidateModel candidateModel;
    private StringProperty onJobDescriptionId;

    public CandidateSeekVM(CandidateModel candidateModel, ViewHandler vh)
    {
        this.candidateModel = candidateModel;
        this.viewHandler = vh;

        onJobDescriptionId = new SimpleStringProperty();
    }

    public void showNewPoster(String category)
    {
        Job job = candidateModel.getJobInfo(category);
        if (job != null)
            onJobDescriptionId.setValue(job.toString());
        else onJobDescriptionId.setValue("\n\n                          No jobs for this category found yet.\n" +
                "                                      Try again later!");
    }

    public void reactedToShownPoster(String response)
    {
        candidateModel.reactToJob(response);
    }

    public void openCandidateMain()
    {
        viewHandler.openCandidateMain();
    }

    public StringProperty onJobDescriptionId()
    {
        return onJobDescriptionId;
    }
}
