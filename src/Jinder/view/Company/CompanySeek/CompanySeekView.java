package Jinder.view.Company.CompanySeek;

import Jinder.ViewModel.Company.CompanySeek.CompanySeekVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class CompanySeekView
{
    private CompanySeekVM viewModel;

    @FXML
    private TextArea candCVId;

    public void init(CompanySeekVM vm)
    {
        this.viewModel = vm;
        candCVId.textProperty().bindBidirectional(viewModel.onCandCVId());
        viewModel.showUserInfo();

    }

    public void candSeekBackToMain(ActionEvent actionEvent)
    {
        viewModel.openCompanyMain();
    }

    public void onAccept(ActionEvent actionEvent)
    {
        viewModel.response("Accepted");
        viewModel.showUserInfo();

    }

    public void onDecline(ActionEvent actionEvent)
    {
        viewModel.response("Declined");
        viewModel.showUserInfo();

    }

    public void backToRev(ActionEvent actionEvent)
    {
        viewModel.openCompanyRev();
    }
}
