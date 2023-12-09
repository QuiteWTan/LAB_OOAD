package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.object.PanelHeader;

public class ViewPanelFan extends BorderPane {
	
	private ObservableList<PanelHeader> panelList;
    private ListView<PanelHeader> listView;
    
	public ScrollPane sp;
	public FlowPane fp;
	public VBox vb;
	public Label titleLbl;
	
	
	public ViewPanelFan(ArrayList<PanelHeader> panels, String title) {
		this.titleLbl = new Label(title);
		init();		
		this.panelList = FXCollections.observableArrayList(panels);
		this.listView = new ListView<>(this.panelList);
		this.listView.setCellFactory(new PanelCellFactory());
        
        this.titleLbl.setAlignment(Pos.CENTER);
        this.titleLbl.setTextAlignment(TextAlignment.CENTER);
		this.titleLbl.setStyle("-fx-font-weight : bold; -fx-font-size: 20px; ");
               
        this.sp.setContent(this.listView);
        this.sp.setFitToWidth(true);
        this.setCenter(this.vb);;        
	}
	
	public void init() {
		
		this.sp = new ScrollPane();
		this.vb = new VBox();
		this.fp = new FlowPane();
		
	
		this.vb.setSpacing(10);
		
		this.vb.getChildren().addAll(this.titleLbl,this.sp);
        this.vb.setVgrow(this.titleLbl, Priority.ALWAYS);
        this.vb.setAlignment(Pos.CENTER);
	}
    
    public Scene getViewScene() {
    	return new Scene(this, 1100,600);
    }
    
    public void refreshPanelList(ArrayList<PanelHeader> panels) {
    	this.panelList.clear();
    	this.panelList.addAll(panels);
    }

   
}


