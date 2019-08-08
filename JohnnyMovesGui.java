import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.*;

public class JohnnyMovesGui extends Application
{
  public static final double MAX_BTN_SIZE = 380.0;

  /* Attributes
  Scene 1 is the Main Menu. User can go to send menu or track menu.
  Scene 2 is send menu.
  */
  Label display = new Label();
  Scene menu, sending;

  public JohnnyMovesGui()
  {

  }

  @Override
  public void start(Stage primaryStage)
  {
  //  Image logo = new Image("Logo.png");
    Image header = new Image("Header.png");
    display.setFont(Font.font("Verdana",16));

    /* Scene 1 Buttons */
    Button sendBtn = new Button();
    Button trackBtn = new Button();
    sendBtn.setText("Send a Parcel");
    trackBtn.setText("Track a Parcel");
    sendBtn.setOnAction(e -> primaryStage.setScene(sending));
    /* Layout 1 for Main Menu */
    Pane mainMenu = new Pane();
    mainMenu.getChildren().addAll(sendBtn, trackBtn);
    sendBtn.setLayoutX(120);
    sendBtn.setLayoutY(350);
    trackBtn.setLayoutX(480);
    trackBtn.setLayoutY(350);
    menu = new Scene(mainMenu, 700, 500);

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
    setRecipient.setMaxWidth(MAX_BTN_SIZE);
    setInsurance.setMaxWidth(MAX_BTN_SIZE);
    modifyItems.setMaxWidth(MAX_BTN_SIZE);
    checkout.setMaxWidth(MAX_BTN_SIZE);
    backToMain.setMaxWidth(MAX_BTN_SIZE);
    backToMain.setOnAction(e -> primaryStage.setScene(menu));

    /* Layout 2 for Send Menu */
    BorderPane sendBorder = new BorderPane();
    VBox sendMenu = new VBox(20); // 20 px spacing
    sendMenu.setAlignment(Pos.CENTER);
    sendMenu.setPadding(new Insets(10));
    sendBorder.setCenter(sendMenu);
    sendMenu.getChildren().addAll(setRecipient, setInsurance, modifyItems, checkout, backToMain);
  //  sendMenu.getChildren().add(new ImageView(header));
    StackPane headerTop = new StackPane();
    headerTop.getChildren().add(new ImageView(header));
    headerTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
    sendBorder.setTop(headerTop);
    sendBorder.setCenter(sendMenu);

    sending = new Scene(sendBorder, 700, 500);

    /* Scene 3 Button */

    /* Layout 3 for Tracking Menu */

    /*sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                display.setText("Dummy Send");
            }
        }); */



    primaryStage.setScene(menu);
    primaryStage.setTitle("Johnny Moves");
    primaryStage.show();
    primaryStage.setResizable(false);
  }


  public static void main(String[] args)
  {
    //JohnnyMovesGui gui = new JohnnyMovesGui();
    //JohnnyMovesController controller = new JohnnyMovesController(gui);
    launch(args);
  }
}
