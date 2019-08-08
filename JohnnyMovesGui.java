import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JohnnyMovesGui extends Application
{
    public static final double MAX_BTN_SIZE = 380.0;
    public static final String MAIN_MENU = "MENU";
    public static final String SENDING = "SENDING";
    public static final String RECIPIENT = "RECIPIENT";

    /* Attributes */
    Label display = new Label();
    /*
    Scene 1 is the Main Menu. User can go to send menu or track menu.
    Scene 2 is send menu.
    */
    private Scene
        startScene,
        menuScene,
        sendingScene,
        recipientScene,
        itemsScene,
        checkoutScene,
        trackingScene,
        statusScene,
        optionsScene,
        timeScene;

    Stage stage;

    public JohnnyMovesGui()
    {

    }

    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        display.setFont(Font.font("Verdana",16));
        createScenes();

        JohnnyMovesController contoller = new JohnnyMovesController(this);

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

        primaryStage.setScene(startScene);
        primaryStage.setTitle("Johnny Moves");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void createScenes()
    {
        /* -------------------------- */
        /* INITIALIZE SCENE 0 - START */
        /* -------------------------- */

        Label welcomeLabel = new Label();
        welcomeLabel.setText("Welcome to Johnny Moves!");
        Button getStartedButton = new Button();
        getStartedButton.setText("Get Started");

        BorderPane borderPane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(welcomeLabel);
        vbox.getChildren().add(getStartedButton);
        borderPane.setCenter(vbox);

        startScene = new Scene(borderPane, 500, 300);

        //  Image logo = new Image("Logo.png");
        Image header = new Image("Header.png");
        display.setFont(Font.font("Verdana",16));

        /* Scene 1 Buttons */
        Button sendBtn = new Button();
        Button trackBtn = new Button();
        sendBtn.setText("Send a Parcel");
        trackBtn.setText("Track a Parcel");
        /* Layout 1 for Main Menu */
        Pane mainMenu = new Pane();
        mainMenu.getChildren().addAll(sendBtn, trackBtn);
        sendBtn.setLayoutX(120);
        sendBtn.setLayoutY(350);
        trackBtn.setLayoutX(480);
        trackBtn.setLayoutY(350);
        menuScene = new Scene(mainMenu, 700, 500);

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

        sendingScene = new Scene(sendBorder, 700, 500);

        /* ------------------------------ */
        /* INITIALIZE SCENE 3 - RECIPIENT */
        /* ------------------------------ */
        GridPane recipientPane = new GridPane();

        recipientPane.setAlignment(Pos.CENTER);

        Label recipientNameLabel = new Label();
        recipientNameLabel.setText("Name");
        Label recipientRegionLabel = new Label();
        recipientRegionLabel.setText("Region");

        TextField recipientField = new TextField();
        GridPane.setFillWidth(recipientField, true);
        GridPane.setMargin(recipientField, new Insets(5, 10, 5, 10));

        ComboBox<String> regionComboBox = new ComboBox<>();
        regionComboBox.getItems().addAll(Parcel.REGIONS);
        GridPane.setFillWidth(regionComboBox, true);
        GridPane.setMargin(regionComboBox, new Insets(5, 10, 5, 10));

        Button cancelRecipientButton = new Button();
        cancelRecipientButton.setText("Cancel");
        GridPane.setFillWidth(cancelRecipientButton, true);
        GridPane.setMargin(cancelRecipientButton, new Insets(5, 10, 5, 10));

        Button submitRecipientButton = new Button();
        submitRecipientButton.setText("Submit");
        GridPane.setFillWidth(submitRecipientButton, true);
        GridPane.setMargin(submitRecipientButton, new Insets(5, 10, 5, 10));

        recipientPane.add(recipientNameLabel, 0, 0);
        recipientPane.add(recipientRegionLabel, 0, 1);
        recipientPane.add(recipientField, 1, 0, 2, 1);
        recipientPane.add(regionComboBox, 1, 1, 2, 1);
        recipientPane.add(submitRecipientButton, 1, 2);
        recipientPane.add(cancelRecipientButton, 2, 2);

        recipientScene = new Scene(recipientPane, 350, 200);
    }

    public void setScene(String scene)
    {
        Scene s = null;
        switch (scene)
        {
        case MAIN_MENU: s = menuScene; break;
        case SENDING: s = sendingScene; break;
        case RECIPIENT: s = recipientScene; break;
        }

        if (s != null)
        {
            stage.setScene(s);
        }
    }

    public void close()
    {

    }

    private void attachHandlerToPane(Pane pane, EventHandler<ActionEvent> handler)
    {
        for (Node node : pane.getChildren())
        {
            if (node instanceof Button)
            {
                Button button = (Button)node;
                button.setOnAction(handler);
            }
            else if (node instanceof Pane)
            {
                attachHandlerToPane((Pane)node, handler);
            }
        }
    }

    private void attachHandlerToScene(Scene scene, EventHandler<ActionEvent> handler)
    {
        Parent root = scene.getRoot();
        if (root instanceof Pane)
        {
            attachHandlerToPane((Pane)root, handler);
        }
    }

    public void addActionListener(EventHandler<ActionEvent> handler)
    {
        attachHandlerToScene(startScene, handler);
        attachHandlerToScene(menuScene, handler);
        attachHandlerToScene(sendingScene, handler);
        attachHandlerToScene(recipientScene, handler);
    }

    public static void main(String[] args)
    {
        //JohnnyMovesGui gui = new JohnnyMovesGui();
        //JohnnyMovesController controller = new JohnnyMovesController(gui);
        launch(args);
    }
}
