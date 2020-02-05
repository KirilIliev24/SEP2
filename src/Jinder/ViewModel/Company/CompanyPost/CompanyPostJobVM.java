package Jinder.ViewModel.Company.CompanyPost;

import Jinder.model.CompanyModel;
import Jinder.view.ViewHandler;
import javafx.beans.property.*;
import javafx.scene.control.Alert;

public class CompanyPostJobVM
{
    private ViewHandler viewHandler;
    private CompanyModel companyModel;
    private StringProperty onJobNameId;
    private StringProperty onDescriptionId;
    private StringProperty onRequirementsId;

    public CompanyPostJobVM(CompanyModel companyModel, ViewHandler vh)
    {
        this.companyModel = companyModel;
        this.viewHandler = vh;

        onJobNameId = new SimpleStringProperty();
        onDescriptionId = new SimpleStringProperty();
        onRequirementsId = new SimpleStringProperty();
    }

    public StringProperty onJobNameId()
    {
        return onJobNameId;
    }

    public StringProperty onDescriptionId()
    {
        return onDescriptionId;
    }

    public StringProperty onRequirementsId()
    {
        return onRequirementsId;
    }


    public void postNewJob(String category)
    {
        if (onJobNameId.getValue() == null || onJobNameId.getValue().equals("")
                || onDescriptionId.getValue() == null || onDescriptionId.getValue().equals("")
                || onRequirementsId.getValue() == null || onRequirementsId.getValue().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Fill out all the fields!");
            alert.setContentText("You haven't filled out all the fields, that is required, for the posting!");
            alert.showAndWait();
        } else
        {
            companyModel.postNewJob(onJobNameId.getValue(), onRequirementsId.getValue(), onDescriptionId.getValue(), category);
            clearFields();

        }
    }

    public void clearFields()
    {
        onJobNameId.setValue(null);
        onDescriptionId.setValue(null);
        onRequirementsId.setValue(null);
    }


}
