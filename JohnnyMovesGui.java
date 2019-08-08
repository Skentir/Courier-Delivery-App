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
    public static final String ITEMS = "ITEMS";

    /* Attributes */
    Label display = new Label();

    ListView<Item> itemsList = new ListView<>();
    Label itemNameLabel = new Label();
    Label dimensionsLabel = new Label();
    Label weightLabel = new Label();
    Label typeLabel = new Label();

    ListView<Item> checkoutList = new ListView<>();
    Label checkoutRecipientLabel = new Label();
    Label checkoutRegionLabel = new Label();
    Label checkoutItemCountLabel = new Label();
    Label checkoutPriceLabel = new Label();

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

        /* -------------------------- */
        /* INITIALIZE SCENE 4 - ITEMS */
        /* -------------------------- */
        GridPane itemsPane = new GridPane();

        itemsPane.setAlignment(Pos.CENTER);

        itemsList = new ListView<>();
        GridPane.setFillWidth(itemsList, true);
        GridPane.setMargin(itemsList, new Insets(20, 20, 5, 10));

        itemNameLabel = new Label();
        GridPane.setMargin(itemNameLabel, new Insets(20, 10, 5, 20));
        Label dimensionsNameLabel = new Label();
        dimensionsNameLabel.setText("Dimensions: ");
        Label weightNameLabel = new Label();
        weightNameLabel.setText("Weight: ");
        Label typeNameLabel = new Label();
        typeNameLabel.setText("Type: ");

        dimensionsLabel = new Label();
        weightLabel = new Label();
        typeLabel = new Label();

        Button addItemButton = new Button();
        addItemButton.setText("Add");
        addItemButton.setMaxWidth(145);
        GridPane.setFillWidth(addItemButton, true);
        GridPane.setMargin(addItemButton, new Insets(5, 20, 20, 10));

        Button removeItemButton = new Button();
        removeItemButton.setText("Remove");
        removeItemButton.setMaxWidth(145);
        GridPane.setFillWidth(removeItemButton, true);
        GridPane.setMargin(removeItemButton, new Insets(5, 10, 20, 10));

        Button doneItemButton = new Button();
        doneItemButton.setText("Done");
        doneItemButton.setMaxWidth(145);
        GridPane.setFillWidth(doneItemButton, true);
        GridPane.setMargin(doneItemButton, new Insets(5, 10, 20, 20));

        itemsPane.getColumnConstraints().addAll(
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        itemsPane.add(itemsList, 0, 0, 2, 6);
        itemsPane.add(addItemButton, 0, 6);
        itemsPane.add(removeItemButton, 1, 6);
        itemsPane.add(itemNameLabel, 2, 0);
        itemsPane.add(dimensionsNameLabel, 2, 1);
        itemsPane.add(weightNameLabel, 2, 2);
        itemsPane.add(typeNameLabel, 2, 3);
        itemsPane.add(dimensionsLabel, 3, 1);
        itemsPane.add(weightLabel, 3, 2);
        itemsPane.add(typeLabel, 3, 3);
        itemsPane.add(doneItemButton, 3, 6);

        itemsScene = new Scene(itemsPane, 700, 500);

        /* ----------------------------- */
        /* INITIALIZE SCENE 5 - CHECKOUT */
        /* ----------------------------- */
        GridPane checkoutPane = new GridPane();

        checkoutPane.setAlignment(Pos.CENTER);

        checkoutList = new ListView<>();
        GridPane.setFillWidth(checkoutList, true);
        GridPane.setMargin(checkoutList, new Insets(20, 20, 5, 10));

        Label checkoutRecipientNameLabel = new Label();
        checkoutRecipientNameLabel.setText("Recipient:");
        Label checkoutRegionNameLabel = new Label();
        checkoutRegionNameLabel.setText("Region:");
        Label checkoutItemCountNameLabel = new Label();
        checkoutItemCountNameLabel.setText("# of items:");
        Label checkoutPriceNameLabel = new Label();
        checkoutPriceNameLabel.setText("Price:");

        Button checkoutButton = new Button();
        checkoutButton.setText("Checkout");
        checkoutButton.setMaxWidth(145);
        GridPane.setFillWidth(checkoutButton, true);
        GridPane.setMargin(checkoutButton, new Insets(5, 10, 20, 20));

        Button cancelCheckoutButton = new Button();
        cancelCheckoutButton.setText("Cancel");
        cancelCheckoutButton.setMaxWidth(145);
        GridPane.setFillWidth(cancelCheckoutButton, true);
        GridPane.setMargin(cancelCheckoutButton, new Insets(5, 10, 20, 20));

        itemsPane.getColumnConstraints().addAll(
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        checkoutPane.add(itemsList, 0, 0, 2, 6);
        //itemsPane.add(itemNameLabel, 2, 0);
        checkoutPane.add(checkoutRecipientNameLabel, 2, 1);
        checkoutPane.add(checkoutRegionNameLabel, 2, 2);
        checkoutPane.add(checkoutItemCountNameLabel, 2, 3);
        checkoutPane.add(checkoutPriceNameLabel, 2, 4);
        checkoutPane.add(checkoutRecipientLabel, 3, 1);
        checkoutPane.add(checkoutRegionLabel, 3, 2);
        checkoutPane.add(checkoutItemCountLabel, 3, 3);
        checkoutPane.add(checkoutPriceLabel, 3, 4);
        checkoutPane.add(checkoutButton, 2, 6);
        checkoutPane.add(cancelCheckoutButton, 3, 6);

        itemsScene = new Scene(checkoutPane, 700, 500);
    }

    public void setScene(String scene)
    {
        Scene s = null;
        switch (scene)
        {
        case MAIN_MENU: s = menuScene; break;
        case SENDING: s = sendingScene; break;
        case RECIPIENT: s = recipientScene; break;
        case ITEMS: s = itemsScene; break;
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
        attachHandlerToScene(itemsScene, handler);
    }

    public static void main(String[] args)
    {
        //JohnnyMovesGui gui = new JohnnyMovesGui();
        //JohnnyMovesController controller = new JohnnyMovesController(gui);
        launch(args);
    }
}
