package Jinder.view.Company.CompanySeek;

import Jinder.ViewModel.Company.CompanySeek.CompanySeekJobRevVM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class CompanySeekJobRevView
{
    private CompanySeekJobRevVM viewModel;

    @FXML
    private JFXButton reviewButton;

    @FXML
    private TextArea jobReviewId;

    @FXML
    private JFXComboBox<String> choiceBox;

    public void init(CompanySeekJobRevVM vm)
    {
        this.viewModel = vm;
        choiceBox.setItems(viewModel.getItems());
        reviewButton.textProperty().bindBidirectional(viewModel.getButtonText());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
            {
                viewModel.displayJob((Integer) number2);
            }
        });

        jobReviewId.textProperty().bindBidirectional(viewModel.onJobReviewId());
    }

    public void openCompanySeek(ActionEvent actionEvent)
    {
        viewModel.openCompanySeek();
    }

    public void compRevBackToMain(ActionEvent actionEvent)
    {
        viewModel.openCompanyMain();
    }

}
