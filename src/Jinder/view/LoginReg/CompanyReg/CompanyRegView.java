package Jinder.view.LoginReg.CompanyReg;

import Jinder.ViewModel.LoginReg.CompanyReg.CompanyRegVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CompanyRegView
{
    @FXML
    private TextField companyNameId;
    @FXML
    private TextArea companyDescriptionId;

    private CompanyRegVM viewModel;

    public void init(CompanyRegVM vm)
    {
        this.viewModel = vm;

        companyNameId.textProperty().bindBidirectional(viewModel.onCompanyNameId());
        companyDescriptionId.textProperty().bindBidirectional(viewModel.onCompanyDescriptionId());
    }

    public void compRegBack(ActionEvent actionEvent)
    {
        viewModel.openRegistrateNeutral();
    }

    public void compRegistrate(ActionEvent actionEvent)
    {
        if (companyNameId.getText() == null || companyNameId.getText().equals("")
                || companyDescriptionId.getText() == null || companyDescriptionId.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeight(300);
            alert.setTitle("ERROR");
            alert.setHeaderText("Fill out all the fields!");
            alert.setContentText("You forgot to fill out all the fields, that are required, for finishing the registration process!");
            alert.showAndWait();
        } else
        {
            viewModel.openCompanyMain();
            viewModel.setFields();
        }
    }
}
