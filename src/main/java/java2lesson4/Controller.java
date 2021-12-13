package java2lesson4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class Controller {
    @FXML
    TextArea mainTextArea;
    @FXML
    TextField enterTextField;



    public void btnOneClickAction(ActionEvent actionEvent) {
                mainTextArea.setText(enterTextField.getText());
                enterTextField.setText("");
    }

}
