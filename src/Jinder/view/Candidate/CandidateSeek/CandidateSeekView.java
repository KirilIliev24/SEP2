package Jinder.view.Candidate.CandidateSeek;

import Jinder.ViewModel.Candidate.CandidateSeek.CandidateSeekVM;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class CandidateSeekView
{
    ObservableList<String> categoriesList = FXCollections.observableArrayList
            ("Engineering & Technology", "Sales, Service & Support", "Marketing & Communication",
                    "Design", "Business Strategy", "Finance", "Legal", "People", "Facilities");

    private CandidateSeekVM viewModel;

    @FXML
    private JFXComboBox categories;

    @FXML
    private TextArea jobDescriptionId;


    public void init(CandidateSeekVM vm)
    {
        this.viewModel = vm;

        jobDescriptionId.textProperty().bindBidirectional(viewModel.onJobDescriptionId());
        categories.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
            {
                viewModel.showNewPoster((String) categories.getItems().get((Integer) number2));
            }
        });
    }

    @FXML
    public void initialize()
    {
        categories.setItems(categoriesList);
    }

    public void candSeekBackToMain(ActionEvent actionEvent)
    {
        viewModel.openCandidateMain();
    }

    public void onAccept(ActionEvent actionEvent)
    {
        viewModel.reactedToShownPoster("Accepted");
        viewModel.showNewPoster((String) categories.getSelectionModel().getSelectedItem());
    }

    public void onDecline(ActionEvent actionEvent)
    {
        viewModel.reactedToShownPoster("Declined");
        viewModel.showNewPoster((String) categories.getSelectionModel().getSelectedItem());
    }
}
