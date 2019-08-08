import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;
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
    public static final String TRACKING = "TRACKING";
    public static final String CHECKOUT = "CHECKOUT";

    /* Attributes */
    Color bg = Color.rgb(250,248,247);

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

    Stage stage, insured;

    public JohnnyMovesGui()
    {

    }

    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        createScenes();

        JohnnyMovesController contoller = new JohnnyMovesController(this);


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
        getStartedButton.setStyle("-fx-font-weight: bold; -fx-background-color: #8fe1a2; -fx-text-fill: darkslateblue;");
        getStartedButton.setText("Get Started");
        getStartedButton.setId("get-started");

        BorderPane mainPane = new BorderPane();
        VBox mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().add(welcomeLabel);
        mainBox.getChildren().add(getStartedButton);
        mainPane.setCenter(mainBox);

        startScene = new Scene(mainPane, 500, 300);

        //  Image logo = new Image("Logo.png");
        Image header = new Image("Header.png");
        ImageView parcelPic = new  ImageView(new Image("Parcel.png"));
        ImageView magnifyingPic = new  ImageView(new Image("Magnifying.png"));
        /* Scene 1 Buttons */
        BorderPane menuPane = new BorderPane();
        GridPane centerMenu = new GridPane();
        Button sendBtn = new Button();
        Button trackBtn = new Button();
        GridPane.setHalignment(sendBtn, HPos.LEFT);
        GridPane.setHalignment(trackBtn, HPos.RIGHT);
        GridPane.setHalignment(parcelPic, HPos.LEFT);
        GridPane.setHalignment(magnifyingPic, HPos.RIGHT);
        sendBtn.setStyle("-fx-background-color: #8fe1a2; -fx-text-fill: darkslateblue;");
        trackBtn.setStyle("-fx-background-color: #8fe1a2; -fx-text-fill: darkslateblue;");
        sendBtn.setText("Send a Parcel");
        sendBtn.setId("main-send");
        trackBtn.setText("Track a Parcel");
        trackBtn.setId("main-track");

        /* Layout 1 for Main Menu */
        centerMenu.add(sendBtn, 0, 1);
        centerMenu.add(trackBtn, 1, 1);
        centerMenu.add(parcelPic, 0, 0);
        centerMenu.add(magnifyingPic, 1, 0);
        centerMenu.setAlignment(Pos.CENTER);
        GridPane.setMargin(sendBtn, new Insets(0,0,0,55));
        GridPane.setMargin(trackBtn, new Insets(0,55,0,0));
        GridPane.setMargin(parcelPic, new Insets(0,0,0,55));
        GridPane.setMargin(magnifyingPic, new Insets(0,55,0,0));

        StackPane mainTop = new StackPane();
        mainTop.getChildren().add(new ImageView(header));
        mainTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        menuPane.setTop(mainTop);
        menuPane.setCenter(centerMenu);
    //    menuPane.getChildren().add(centerMenu);

        menuScene = new Scene(menuPane, 700, 500);

        /* Scene 2 Buttons */
        Button setRecipient = new Button();
        Button setInsurance = new Button();
        Button modifyItems = new Button();
        Button checkout = new Button();
        Button backToMain = new Button();
        setRecipient.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        setInsurance.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        modifyItems.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        checkout.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        backToMain.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        setRecipient.setText("Set Recipient");
        setRecipient.setId("items-recipient");
        setInsurance.setText("Set Insurance");
        setInsurance.setId("items-insurance");
        modifyItems.setText("Add, remove, or view items");
        modifyItems.setId("items-edit");
        checkout.setText("Checkout");
        checkout.setId("items-checkout");
        backToMain.setText("Go Back to Main Menu");
        backToMain.setId("items-return");
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

        StackPane sendTop = new StackPane();
        sendTop.getChildren().add(new ImageView(header));
        sendTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        sendBorder.setTop(sendTop);
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
        /* Scene 6 Button */
        Label promptCode = new Label("Enter Tracking Number");
        TextField enterCode = new TextField();
        Button submitCode = new Button();
        Button trackToMain = new Button();
        trackToMain.setText("Go Back to Main Menu");
        trackToMain.setId("track-return");
        promptCode.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 24));
        submitCode.setText("Submit");
        submitCode.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        trackToMain.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        submitCode.setId("track-submit");
        submitCode.setMaxWidth(MAX_BTN_SIZE/3);
        enterCode.setMaxWidth(MAX_BTN_SIZE);

        /* Layout 6 for Tracking Menu */
        BorderPane trackBorder = new BorderPane();
        VBox trackDeets = new VBox(30);
        HBox trackBtns = new HBox(30);
        trackBtns.setAlignment(Pos.CENTER);
        trackBtns.getChildren().addAll(submitCode, trackToMain);
        trackDeets.getChildren().addAll(promptCode,enterCode, trackBtns);
        trackDeets.setAlignment(Pos.CENTER);
        StackPane trackTop = new StackPane();
        trackTop.getChildren().add(new ImageView(header));
        trackTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        trackBorder.setTop(trackTop);
        trackBorder.setCenter(trackDeets);

        trackingScene = new Scene(trackBorder, 700, 500, bg);

        ComboBox<String> regionComboBox = new ComboBox<>();
        regionComboBox.getItems().addAll(Parcel.REGIONS);
        GridPane.setFillWidth(regionComboBox, true);
        GridPane.setMargin(regionComboBox, new Insets(5, 10, 5, 10));

        Button cancelRecipientButton = new Button();
        cancelRecipientButton.setText("Cancel");
        cancelRecipientButton.setId("recipient-cancel");
        GridPane.setFillWidth(cancelRecipientButton, true);
        GridPane.setMargin(cancelRecipientButton, new Insets(5, 10, 5, 10));

        Button submitRecipientButton = new Button();
        submitRecipientButton.setText("Submit");
        submitRecipientButton.setId("recipient-submit");
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
        addItemButton.setId("items-add");
        addItemButton.setMaxWidth(145);
        GridPane.setFillWidth(addItemButton, true);
        GridPane.setMargin(addItemButton, new Insets(5, 20, 20, 10));

        Button removeItemButton = new Button();
        removeItemButton.setText("Remove");
        removeItemButton.setId("items-remove");
        removeItemButton.setMaxWidth(145);
        GridPane.setFillWidth(removeItemButton, true);
        GridPane.setMargin(removeItemButton, new Insets(5, 10, 20, 10));

        Button doneItemButton = new Button();
        doneItemButton.setText("Done");
        doneItemButton.setId("items-done");
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
        checkoutButton.setId("checkout-checkout");
        checkoutButton.setMaxWidth(145);
        GridPane.setFillWidth(checkoutButton, true);
        GridPane.setMargin(checkoutButton, new Insets(5, 10, 20, 20));

        Button cancelCheckoutButton = new Button();
        cancelCheckoutButton.setText("Cancel");
        cancelCheckoutButton.setId("checkout-cancel");
        cancelCheckoutButton.setMaxWidth(145);
        GridPane.setFillWidth(cancelCheckoutButton, true);
        GridPane.setMargin(cancelCheckoutButton, new Insets(5, 10, 20, 20));

        checkoutPane.getColumnConstraints().addAll(
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        checkoutPane.add(checkoutList, 0, 0, 2, 6);
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

        checkoutScene = new Scene(checkoutPane, 700, 500);
    }

    public void setScene(String scene)
    {
        Scene s = null;
        switch (scene)
        {
        case MAIN_MENU: s = menuScene; break;
        case SENDING: s = sendingScene; break;
        case TRACKING: s = trackingScene; break;
        case RECIPIENT: s = recipientScene; break;
        case ITEMS: s = itemsScene; break;
        case CHECKOUT: s = checkoutScene; break;
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
        attachHandlerToScene(trackingScene, handler);
        attachHandlerToScene(recipientScene, handler);
        attachHandlerToScene(itemsScene, handler);
        attachHandlerToScene(checkoutScene, handler);
    }

    public static void main(String[] args)
    {
        //JohnnyMovesGui gui = new JohnnyMovesGui();
        //JohnnyMovesController controller = new JohnnyMovesController(gui);
        launch(args);
    }
}
