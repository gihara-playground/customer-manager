package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ManageCustomerFormController {

    public Button btnNewCustomer;
    public TextField txtId;
    public TextField txtFirstName;
    public TextField txtLastName;
    public DatePicker txtDob;
    public TextField txtPicture;
    public Button btnBrowse;
    public TextField txtTelephone;
    public Button btnAdd;
    public ListView<String> lstTelephone;
    public Button btnRemove;
    public Button btnSaveCustomer;
    public TableView<?> tblCustomers;

    public void initialize() throws IOException {
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        txtTelephone.textProperty().addListener((observable, oldValue, newValue) -> {
            /*if (newValue.trim().matches("\\d{3}-\\d{7}")){
                btnAdd.setDisable(false);
            }else{
                btnAdd.setDisable(true);
            }*/
            btnAdd.setDisable(!newValue.trim().matches("\\d{3}-\\d{7}"));
        });
        lstTelephone.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRemove.setDisable(newValue==null);
        });
    }

    public void btnRemove_OnAction(ActionEvent event) {
        String selectedTelephone = lstTelephone.getSelectionModel().getSelectedItem();
        lstTelephone.getItems().remove(selectedTelephone);
        lstTelephone.getSelectionModel().clearSelection();
    }

    public void txtTelephone_OnAction(ActionEvent event) {
        btnAdd.fire();
    }

    public void btnAdd_OnAction(ActionEvent event) {
        lstTelephone.getSelectionModel().clearSelection();
        for (String telephone : lstTelephone.getItems()) {
            if (telephone.equals(txtTelephone.getText())){
                txtTelephone.selectAll();
                return;
            }
        }
        lstTelephone.getItems().add(txtTelephone.getText());
        txtTelephone.clear();
        txtTelephone.requestFocus();
    }

    public void btnBrowse_OnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image","*.jpeg", "*.jpg", "*.gif", "*.png", "*.bmp"));
        fileChooser.setTitle("Select an image");
        File file = fileChooser.showOpenDialog(btnBrowse.getScene().getWindow());
        txtPicture.setText(file!=null?file.getAbsolutePath():"");
    }

    public void btnNewCustomer_OnAction(ActionEvent event) {
        txtId.setText(generateNewId());
    }

    private String generateNewId() {
        if (tblCustomers.getItems().isEmpty()){
            return "C001";
        } else {
            /* Todo: After creating a table model */
         return "C001";
        }
    }

    public void btnSaveCustomer_OnAction(ActionEvent event) {
    }

}
