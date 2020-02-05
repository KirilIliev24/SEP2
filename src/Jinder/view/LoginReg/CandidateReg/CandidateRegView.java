package Jinder.view.LoginReg.CandidateReg;

import Jinder.ViewModel.LoginReg.CandidateReg.CandidateRegVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class CandidateRegView
{
    private CandidateRegVM viewModel;

    @FXML
    private TextArea educationId, experiencesId, skillsId, softSkillsId, licensesId, commentsId;


    public void init(CandidateRegVM vm)
    {
        this.viewModel = vm;

        educationId.textProperty().bindBidirectional(viewModel.onEducationId());
        experiencesId.textProperty().bindBidirectional(viewModel.onExperiencesId());
        skillsId.textProperty().bindBidirectional(viewModel.onSkillsId());
        softSkillsId.textProperty().bindBidirectional(viewModel.onSoftSkillsId());
        licensesId.textProperty().bindBidirectional(viewModel.onLicensesId());
        commentsId.textProperty().bindBidirectional(viewModel.onCommentsId());

    }

    public void candRegBack(ActionEvent actionEvent)
    {
        viewModel.openRegistrateNeutral();
    }

    public void candRegistrate(ActionEvent actionEvent)
    {
        if (educationId.getText() == null || educationId.getText().equals("")
                || experiencesId.getText() == null || experiencesId.getText().equals("")
                || skillsId.getText() == null || experiencesId.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeight(300);
            alert.setTitle("ERROR");
            alert.setHeaderText("Fill out all the fields!");
            alert.setContentText("You forgot to fill out all the fields, that are required, for finishing the registration process!");
            alert.showAndWait();
        } else
        {
            viewModel.openCandidateMain();
            viewModel.setFields();
        }
    }


}
