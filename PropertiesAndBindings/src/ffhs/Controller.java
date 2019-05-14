package ffhs;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class Controller {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Rectangle rectangle;

    public void initialize() {

        rectangle.widthProperty().bind(borderPane.widthProperty().divide(2));
        rectangle.heightProperty().bind(borderPane.heightProperty().divide(2));

        rectangle.opacityProperty().bind(borderPane.widthProperty().add(borderPane.heightProperty()).divide(1000));

    }
}
