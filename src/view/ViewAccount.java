package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.object.User;

public class ViewAccount extends BorderPane {
	
	private ObservableList<User> userList;
    private TableView<User> tableView;
    private Boolean isAdmin;
    
	public ScrollPane sp;
	public Button deleteBtn;
	public FlowPane fp;
	public VBox vb;
	public Label titleLbl;
	
	
	public ViewAccount(ArrayList<User> users, String title, Boolean isAdmin) {
		this.isAdmin = isAdmin;
		this.titleLbl = new Label(title);
		init();
		this.userList = FXCollections.observableArrayList(users);
		
        this.tableView = new TableView<>();
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<User, Integer> idColumn = new TableColumn<>("User ID");
        TableColumn<User, String> nameColumn = new TableColumn<>("Username");
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        TableColumn<User, String> roleColumn = new TableColumn<>("Role");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("userId") );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username") );
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email") );
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role") );
        
        idColumn.setPrefWidth(80);
        nameColumn.prefWidthProperty().bind(this.tableView.widthProperty().divide(4));
        emailColumn.prefWidthProperty().bind(this.tableView.widthProperty().divide(4));
        roleColumn.prefWidthProperty().bind(this.tableView.widthProperty().divide(4));

        nameColumn.setStyle(
                "-fx-text-fill: blue;" +      
                "-fx-font-weight: bold;"      
        );
        
        this.titleLbl.setAlignment(Pos.CENTER);
        this.titleLbl.setTextAlignment(TextAlignment.CENTER);
		this.titleLbl.setStyle("-fx-font-weight : bold; -fx-font-size: 20px; ");
       
        this.tableView.setPadding(new Insets(10));
        this.tableView.getColumns().addAll(idColumn, nameColumn, emailColumn, roleColumn);
  
        this.tableView.setItems(this.userList);
        
        this.sp.setContent(this.tableView);
        this.sp.setFitToWidth(true);
        this.setCenter(this.vb);;        
	}
	
	public void init() {
		
		this.sp = new ScrollPane();
		this.vb = new VBox();
		this.deleteBtn = new Button("Delete");
		this.fp = new FlowPane();
		
		Button selectAllBtn = new Button("Select All");
		
		selectAllBtn.setOnAction(e -> {
			this.tableView.getSelectionModel().selectAll();
		});
		
		selectAllBtn.setStyle("-fx-background-color: dimgrey; -fx-text-fill: white; -fx-padding : 5px 40px 5px 40px;");
		this.deleteBtn.setStyle("-fx-background-color: dimgrey; -fx-text-fill: white; -fx-padding : 5px 40px 5px 40px;");
		this.fp.getChildren().addAll(selectAllBtn,this.deleteBtn);			
	
		this.fp.setAlignment(Pos.CENTER_RIGHT);
		this.fp.setHgap(20);
		this.fp.setPadding(new Insets(10));
		
		this.vb.setSpacing(10);
		
		if (this.isAdmin) {
			this.vb.getChildren().addAll(this.titleLbl,this.sp,this.fp);
		}
		else {
			this.vb.getChildren().addAll(this.titleLbl,this.sp);
		}
		
        this.vb.setVgrow(this.titleLbl, Priority.ALWAYS);
        this.vb.setAlignment(Pos.CENTER);
	}

		
    public void setButtonClickHandler(Runnable handler) {
        this.deleteBtn.setOnAction(event -> handler.run());
    }
    
    public Scene getViewScene() {
    	return new Scene(this, 800,600);
    }
    
    public void refreshUserList(ArrayList<User> users) {
    	this.userList.clear();
    	this.userList.addAll(users);
    }
    
    public ObservableList<User> getSelectedUsers(){
    	ObservableList<User> selectedUsers = this.tableView.getSelectionModel().getSelectedItems();
    	return selectedUsers;
    }
   
}
