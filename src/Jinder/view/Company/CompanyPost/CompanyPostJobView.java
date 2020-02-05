package Jinder.view.Company.CompanyPost;

import Jinder.ViewModel.Company.CompanyPost.CompanyPostJobVM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CompanyPostJobView
{
    private CompanyPostJobVM viewModel;

    ObservableList<String> categoriesList = FXCollections.observableArrayList
            ("Engineering & Technology", "Sales, Service & Support", "Marketing & Communication",
                    "Design", "Business Strategy", "Finance", "Legal", "People", "Facilities");

    @FXML
    private TextField jobNameId;
    @FXML
    private TextArea descriptionId;
    @FXML
    private TextArea requirementsId;
    @FXML
    private ChoiceBox categories;

    @FXML
    public void initialize()
    {
        categories.setValue("Engineering & Technology");
        categories.setItems(categoriesList);
    }

    public void init(CompanyPostJobVM vm)
    {
        this.viewModel = vm;

        jobNameId.textProperty().bindBidirectional(viewModel.onJobNameId());
        descriptionId.textProperty().bindBidirectional(viewModel.onDescriptionId());
        requirementsId.textProperty().bindBidirectional(viewModel.onRequirementsId());
    }

    public void onPostJob(ActionEvent actionEvent)
    {
        if (jobNameId.getText() == null && descriptionId.getText() == null && requirementsId.getText() == null)
        {
            categories.setValue("Engineering & Technology");
        }
        viewModel.postNewJob((String) categories.getSelectionModel().getSelectedItem());
    }
}
