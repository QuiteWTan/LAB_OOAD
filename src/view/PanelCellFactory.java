package view;

import controller.FanController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.*;
import model.object.PanelHeader;

public class PanelCellFactory implements Callback<ListView<PanelHeader>, ListCell<PanelHeader>> {

    @Override
    public ListCell<PanelHeader> call(ListView<PanelHeader> param) {
        return new ListCell<PanelHeader>() {
            @Override
            protected void updateItem(PanelHeader item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("");                    
                } else {
                    Button printButton = new Button("Show Detail");
                    printButton.setStyle("-fx-background-color: dimgrey; -fx-text-fill: white; -fx-padding : 5px 40px 5px 40px;");

                    // Set an action for the button
                    printButton.setOnAction(event -> {
//                    	FanController.showDetails(item.getPanelId(), Integer.parseInt(item.getUserId()));
                    });

                    // Set the cell content to display the information and the button
                    String labelText = "Title : " + item.getPanelTitle() + " | " +
                            "Start Time: " + item.getStartTime() + " | " +
                            "End Time: " + item.getEndTime();
                    
                    HBox hbox = new HBox(new Label(labelText), printButton);
                    hbox.setSpacing(30);
                    hbox.setAlignment(Pos.CENTER);
                    
                    setGraphic(hbox);
                    if (item.getIsFinished()) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else {
                        setStyle("");
                    }
                }
            }
        };
    }
}
