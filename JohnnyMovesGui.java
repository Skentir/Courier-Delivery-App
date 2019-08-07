import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class JohnnyMovesGui extends Application
{
  /* Attributes */
  Label display = new Label();
  /*
  Scene 1 is the Main Menu. User can go to send menu or track menu.
  Scene 2 is send menu.
  */
  Scene menu, sending;

  public JohnnyMovesGui()
  {

  }

  @Override
  public void start(Stage primaryStage)
  {
    display.setFont(Font.font("Verdana",16));

    /* Scene 1 Buttons */
    Button sendBtn = new Button();
    Button trackBtn = new Button();
    sendBtn.setText("Send a Parcel");
    trackBtn.setText("Track a Parcel");
    sendBtn.setOnAction(e -> primaryStage.setScene(sending));
    /* Layout 1 for Main Menu */
    VBox mainMenu = new VBox(3);
    mainMenu.getChildren().addAll(sendBtn, trackBtn);
    menu = new Scene(mainMenu, 700, 450);

    /* Scene 2 Buttons */
    Button setRecipient = new Button();
    Button setInsurance = new Button();
    Button modifyItems = new Button();
    Button checkout = new Button();
    Button backToMain = new Button();
    setRecipient.setText("Set Recipient");
    setInsurance.setText("Set Insurance");
    modifyItems.setText("Add, remove, or view items");
    checkout.setText("Checkout");
    backToMain.setText("Go Back to Main Menu");
    backToMain.setOnAction(e -> primaryStage.setScene(menu));

    /* Layout 2 for Send Menu */
    VBox sendMenu = new VBox(20);
    sendMenu.getChildren().addAll(setRecipient, setInsurance, modifyItems, checkout, backToMain);
    sending = new Scene(sendMenu, 700, 450);

    /* Scene 3 Button */

    /* Layout 3 for Tracking Menu */
    
    /*sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                display.setText("Dummy Send");
            }
        }); */

    //    StackPane root = new StackPane();
    //    root.getChildren().add(sendBtn);
    //    root.getChildren().add(display);

    primaryStage.setScene(menu);
    primaryStage.setTitle("Johnny Moves");
    primaryStage.show();
  }


  public static void main(String[] args)
  {
    //JohnnyMovesGui gui = new JohnnyMovesGui();
    //JohnnyMovesController controller = new JohnnyMovesController(gui);
    launch(args);
  }
}
