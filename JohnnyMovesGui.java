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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public class JohnnyMovesGui extends Application
{
    public static final double MAX_BTN_SIZE = 380.0;
    public static final String MAIN_MENU = "MENU";
    public static final String SENDING = "SENDING";
    public static final String RECIPIENT = "RECIPIENT";
    public static final String ITEMS = "ITEMS";
    public static final String TRACKING = "TRACKING";
    public static final String DISPLAY_CODE = "DISPLAY_CODE";
    public static final String CHECKOUT = "CHECKOUT";

    public static final String DIALOG_INSURANCE = "INSURANCE";
    public static final String DIALOG_ADD_ITEM = "ADD_ITEM";
    public static final String DIALOG_ADJUST_TIME = "ADJUST_TIME";

    /* Attributes */
    Color bg = Color.rgb(250,248,247);
    TextField enterCode = new TextField();

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

    ListView<Item> displayParcelItems = new ListView<>();
    Label displayTrackingCode = new Label();
    Label displayRecipient= new Label();
    Label displayRegion = new Label();
    Label displayStatus = new Label();

    ToggleGroup insureGroup = new ToggleGroup();
    RadioButton insuredButton = new RadioButton("Yes, insure");
    RadioButton notInsuredButton = new RadioButton("No, don't insure");

    Pane addItemRoot;
    GridPane productGroup;
    TextField productWidthField = new TextField();
    TextField productHeightField = new TextField();
    TextField productLengthField = new TextField();
    TextField productWeightField = new TextField();
    GridPane documentGroup;
    TextField documentWidthField = new TextField();
    TextField documentLengthField = new TextField();
    TextField documentPagesField = new TextField();

    Stage stage;
    Spinner<Integer> daySpinner = new Spinner<Integer>();
    Spinner<Integer> hourSpinner = new Spinner<Integer>();
    Spinner<Integer> minuteSpinner = new Spinner<Integer>();
    Spinner<Integer> secondsSpinner = new Spinner<Integer>();

    Dialog<Item> addItemDialog;
    Dialog<ButtonType> insuranceDialog;
    Dialog<Integer> adjustTimeDialog;

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
        displayParcelScene,
        statusScene,
        optionsScene,
        timeScene;

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
        ImageView logo = new ImageView(new Image("Logo.png"));
        Button getStartedButton = new Button();
        getStartedButton.setStyle("-fx-font-weight: bold; -fx-background-color: #8fe1a2; -fx-text-fill: darkslateblue;");
        getStartedButton.setText("Get Started");
        getStartedButton.setId("get-started");

        BorderPane mainPane = new BorderPane();
        VBox mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(logo, getStartedButton);
        mainPane.setStyle("-fx-background-color: #662d91;-fx-font-size: 2em;");
        mainBox.setMargin(getStartedButton, new Insets(15, 0, 0, 0));
        mainPane.setCenter(mainBox);

        startScene = new Scene(mainPane, 700, 500);

        Image header = new Image("Header.png");
        ImageView parcelPic = new  ImageView(new Image("Parcel.png"));
        ImageView magnifyingPic = new  ImageView(new Image("Magnifying.png"));
        /* Scene 1 Buttons */
        BorderPane menuPane = new BorderPane();
        GridPane centerMenu = new GridPane();
        Button sendBtn = new Button();
        Button trackBtn = new Button();
        GridPane.setHalignment(sendBtn, HPos.CENTER);
        GridPane.setHalignment(trackBtn, HPos.CENTER);
        GridPane.setHalignment(parcelPic, HPos.LEFT);
        GridPane.setHalignment(magnifyingPic, HPos.RIGHT);
        sendBtn.setStyle("-fx-background-color: #8fe1a2; -fx-text-fill: darkslateblue;");
        trackBtn.setStyle("-fx-background-color: #8fe1a2; -fx-text-fill: darkslateblue;");
        sendBtn.setText("Send a Parcel");
        sendBtn.setId("main-send");
        trackBtn.setText("Track a Parcel");
        trackBtn.setId("main-track");

        centerMenu.getColumnConstraints().addAll(
            new ColumnConstraints(180.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(40.0),
            new ColumnConstraints(180.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        /* Layout 1 for Main Menu */
        centerMenu.add(sendBtn, 0, 1);
        centerMenu.add(trackBtn, 2, 1);
        centerMenu.add(parcelPic, 0, 0);
        centerMenu.add(magnifyingPic, 2, 0);
        centerMenu.setAlignment(Pos.CENTER);
        GridPane.setMargin(sendBtn, new Insets(10,0,0,55));
        GridPane.setMargin(trackBtn, new Insets(10,55,0,0));
        GridPane.setFillWidth(parcelPic, true);
        GridPane.setFillWidth(magnifyingPic, true);
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
        enterCode = new TextField();
        Button submitCode = new Button();
        Button trackToMain = new Button();
        trackToMain.setText("Go Back to Main Menu");
        trackToMain.setId("track-main");
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
        BorderPane itemMenu = new BorderPane();
        GridPane itemsPane = new GridPane();
        StackPane itemTop = new StackPane();
        itemTop.getChildren().add(new ImageView(header));
        itemTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        itemMenu.setTop(itemTop);

        itemsPane.setAlignment(Pos.CENTER);

        itemsList = new ListView<>();
        GridPane.setFillWidth(itemsList, true);
        GridPane.setMargin(itemsList, new Insets(20, 20, 5, 10));

        Label itemNameLabel = new Label();
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
        addItemButton.setStyle("-fx-background-color:#8fe1a2; -fx-text-fill: darkslateblue;");
        addItemButton.setMaxWidth(145);
        GridPane.setFillWidth(addItemButton, true);
        GridPane.setMargin(addItemButton, new Insets(5, 20, 20, 10));

        Button removeItemButton = new Button();
        removeItemButton.setStyle("-fx-background-color:#8fe1a2; -fx-text-fill: darkslateblue;");
        removeItemButton.setText("Remove");
        removeItemButton.setId("items-remove");
        removeItemButton.setMaxWidth(145);
        GridPane.setFillWidth(removeItemButton, true);
        GridPane.setMargin(removeItemButton, new Insets(5, 10, 20, 10));

        Button doneItemButton = new Button();
        doneItemButton.setText("Done");
        doneItemButton.setId("items-done");
        doneItemButton.setMaxWidth(145);
        doneItemButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: #8fe1a2;");
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

        itemMenu.setCenter(itemsPane);

        itemsScene = new Scene(itemMenu, 700, 500);

        /* ----------------------------- */
        /* INITIALIZE SCENE 5 - CHECKOUT */
        /* ----------------------------- */
        BorderPane checkoutMenu = new BorderPane();
        GridPane checkoutPane = new GridPane();
        StackPane checkTop = new StackPane();
        checkTop.getChildren().add(new ImageView(header));
        checkTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        checkoutMenu.setTop(checkTop);

        checkoutPane.setAlignment(Pos.CENTER);

        checkoutList = new ListView<>();
        GridPane.setHalignment(checkoutList, HPos.CENTER);
        GridPane.setFillWidth(checkoutList, true);
        GridPane.setMargin(checkoutList, new Insets(20, 20, 20, 10));

        Label checkoutRecipientNameLabel = new Label();
        checkoutRecipientNameLabel.setText("Recipient:");
        GridPane.setMargin(checkoutRecipientLabel, new Insets(20, 0, 5, 5));
        Label checkoutRegionNameLabel = new Label();
        checkoutRegionNameLabel.setText("Region:");
        GridPane.setMargin(checkoutRegionNameLabel, new Insets(0, 0, 5, 5));
        Label checkoutItemCountNameLabel = new Label();
        checkoutItemCountNameLabel.setText("# of items:");
        GridPane.setMargin(checkoutItemCountNameLabel, new Insets(0, 0, 5, 5));
        Label checkoutPriceNameLabel = new Label();
        checkoutPriceNameLabel.setText("Price:");
        GridPane.setMargin(checkoutPriceNameLabel, new Insets(0, 0, 5, 5));

        Button checkoutButton = new Button();
        checkoutButton.setText("Checkout");
        checkoutButton.setId("checkout-checkout");
        checkoutButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: #8fe1a2;");
        checkoutButton.setMaxWidth(145);
        GridPane.setValignment(checkoutButton, VPos.BOTTOM);
        GridPane.setFillWidth(checkoutButton, true);
        GridPane.setMargin(checkoutButton, new Insets(0, 10, 20, 20));

        Button cancelCheckoutButton = new Button();
        cancelCheckoutButton.setText("Cancel");
        cancelCheckoutButton.setId("checkout-cancel");
        cancelCheckoutButton.setStyle("-fx-background-color:#8fe1a2; -fx-text-fill: darkslateblue;");
        cancelCheckoutButton.setMaxWidth(145);
        GridPane.setValignment(cancelCheckoutButton, VPos.BOTTOM);
        GridPane.setFillWidth(cancelCheckoutButton, true);
        GridPane.setMargin(cancelCheckoutButton, new Insets(0, 10, 20, 20));

        checkoutPane.getColumnConstraints().addAll(
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        checkoutPane.add(checkoutList, 0, 0, 2, 8);
        //itemsPane.add(itemNameLabel, 2, 0);
        checkoutPane.add(checkoutRecipientNameLabel, 2, 1);
        checkoutPane.add(checkoutRegionNameLabel, 2, 2);
        checkoutPane.add(checkoutItemCountNameLabel, 2, 3);
        checkoutPane.add(checkoutPriceNameLabel, 2, 4);
        checkoutPane.add(checkoutRecipientLabel, 3, 1);
        checkoutPane.add(checkoutRegionLabel, 3, 2);
        checkoutPane.add(checkoutItemCountLabel, 3, 3);
        checkoutPane.add(checkoutPriceLabel, 3, 4);
        checkoutPane.add(checkoutButton, 2, 7);
        checkoutPane.add(cancelCheckoutButton, 3, 7);

        checkoutMenu.setCenter(checkoutPane);
        checkoutScene = new Scene(checkoutMenu, 700, 500);

        /* --------------------------------- */
        /* INITIALIZE DIALOG - ADJUST TIME   */
        /* --------------------------------- */
        adjustTimeDialog = new Dialog<>();
        adjustTimeDialog.setTitle("Time Machine");
        BorderPane timeBorder = new BorderPane();
        Label timeLabel = new Label();
        timeLabel.setText("Speed-up time?");
        timeLabel.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 16));
        timeLabel.setAlignment(Pos.CENTER);
        GridPane timePane = new GridPane();
        timePane.setAlignment(Pos.CENTER);
        ButtonType timeBtn = new ButtonType("Submit", ButtonData.APPLY);

        Integer initialValue = 0;
        SpinnerValueFactory<Integer> dayFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, initialValue);
        daySpinner.setValueFactory(dayFactory);

        SpinnerValueFactory<Integer> hourFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, initialValue);
        hourSpinner.setValueFactory(hourFactory);

        SpinnerValueFactory<Integer> minuteFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, initialValue);
        minuteSpinner.setValueFactory(minuteFactory);

        SpinnerValueFactory<Integer> secondsFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, initialValue);
        secondsSpinner.setValueFactory(secondsFactory);

        /* Layout for Time Adjustment */
        daySpinner.setPrefWidth(100);
        timePane.add(daySpinner, 0, 1);
        GridPane.setMargin(daySpinner, new Insets(5, 20, 5, 0));
        timePane.add(new Label("Days"),0, 2);
        hourSpinner.setPrefWidth(100);
        timePane.add(hourSpinner, 1,1);
        GridPane.setMargin(hourSpinner, new Insets(5, 20, 5, 0));
        timePane.add(new Label("Hours"),1, 2);
        minuteSpinner.setPrefWidth(100);
        timePane.add(minuteSpinner, 2,1);
        GridPane.setMargin(minuteSpinner, new Insets(5, 20, 5, 0));
        timePane.add(new Label("Minutes"),2, 2);
        secondsSpinner.setPrefWidth(100);
        timePane.add(secondsSpinner, 3,1);
        GridPane.setMargin(secondsSpinner, new Insets(5, 20, 5, 0));
        timePane.add(new Label("Seconds"),3, 2);
        timeBorder.setTop(timeLabel);
        timeBorder.setCenter(timePane);
        adjustTimeDialog.getDialogPane().getButtonTypes().add(timeBtn);
        adjustTimeDialog.getDialogPane().setContent(timeBorder);


        /* --------------------------------- */
        /* INITIALIZE DIALOG - SET INSURANCE */
        /* --------------------------------- */
        insuranceDialog = new Dialog<>();
        insuranceDialog.setTitle("Insure parcel?");
        GridPane insurancePane = new GridPane();
        insurancePane.setAlignment(Pos.CENTER);

        ButtonType submitButtonType = new ButtonType("Submit", ButtonData.APPLY);

        insuranceDialog.getDialogPane().getButtonTypes().add(submitButtonType);
        insuranceDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Label insuranceLabel = new Label();
        insuranceLabel.setText("Do you want the parcel insured?");

        insuredButton.setToggleGroup(insureGroup);
        notInsuredButton.setToggleGroup(insureGroup);

        insurancePane.add(insuranceLabel, 0, 0, 2, 1);
        insurancePane.add(insuredButton, 0, 1, 2, 1);
        insurancePane.add(notInsuredButton, 0, 2, 2, 1);

        insuranceDialog.getDialogPane().setContent(insurancePane);

        /* ---------------------------- */
        /* INITIALIZE DIALOG - ADD ITEM */
        /* ---------------------------- */
        addItemDialog = new Dialog<>();
        addItemDialog.setTitle("Add item");
        GridPane addItemPane = new GridPane();
        addItemPane.setAlignment(Pos.CENTER);

        addItemDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        addItemDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Label itemTypeLabel = new Label("Type");
        ComboBox<String> itemTypes = new ComboBox<>();
        itemTypes.setId("items-type");
        itemTypes.getItems().addAll("REGULAR PRODUCT", "IRREGULAR PRODUCT", "DOCUMENT");
        productGroup = new GridPane();
        Label productWidthLabel = new Label("Width");
        Label productHeightLabel = new Label("Height");
        Label productLengthLabel = new Label("Length");
        Label productWeightLabel = new Label("Weight");
        productGroup.add(productWidthLabel, 0, 0);
        productGroup.add(productWidthField, 1, 0);
        productGroup.add(productHeightLabel, 0, 1);
        productGroup.add(productHeightField, 1, 1);
        productGroup.add(productLengthLabel, 0, 2);
        productGroup.add(productLengthField, 1, 2);
        productGroup.add(productWeightLabel, 0, 3);
        productGroup.add(productWeightField, 1, 3);

        documentGroup = new GridPane();
        Label documentWidthLabel = new Label("Width");
        Label documentLengthLabel = new Label("Length");
        Label documentPagesLabel = new Label("Pages");
        documentGroup.add(documentWidthLabel, 0, 0);
        documentGroup.add(documentWidthField, 1, 0);
        documentGroup.add(documentLengthLabel, 0, 1);
        documentGroup.add(documentLengthField, 1, 1);
        documentGroup.add(documentPagesLabel, 0, 2);
        documentGroup.add(documentPagesField, 1, 2);

<<<<<<< HEAD
        addItemPane.add(itemTypeLabel, 0, 0);
        addItemPane.add(itemTypes, 1, 0);

        addItemRoot = addItemPane;

        addItemDialog.getDialogPane().setContent(addItemPane);
=======
        insuranceDialog.getDialogPane().setContent(insurancePane);

        /* --------------------------------- */
        /* INITIALIZE SCENE 7 - DISPLAY CODE */
        /* --------------------------------- */
        BorderPane displayCodeMenu = new BorderPane();
        GridPane displayInfo = new GridPane();
        StackPane displayCodeTop = new StackPane();
        displayCodeTop.getChildren().add(new ImageView(header));
        displayCodeTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        displayCodeMenu.setTop(checkTop);
        displayCodeMenu.setId("track-return");

        displayParcelItems = new ListView<>();
        GridPane.setHalignment(displayInfo, HPos.CENTER);
        GridPane.setFillWidth(displayInfo, true);
        GridPane.setMargin(displayInfo, new Insets(20, 20, 20, 10));

        Label displayLabel = new Label();
        displayLabel.setText("Parcel Details");
        GridPane.setMargin(displayLabel, new Insets(20, 0, 5, 5));
        Label displayTrackingCode = new Label();
        GridPane.setMargin(displayTrackingCode, new Insets(0, 0, 5, 5));
        Label displayRecipient = new Label();
        GridPane.setMargin(displayRecipient, new Insets(0, 0, 5, 5));
        Label displayRegion = new Label();
        GridPane.setMargin(displayRegion, new Insets(0, 0, 5, 5));
        Label displayStatus = new Label();
        GridPane.setMargin(displayStatus, new Insets(0, 0, 5, 5));

        displayInfo.add(displayParcelItems, 0, 0, 2, 8);
        displayInfo.add(displayTrackingCode, 2, 1);
        displayInfo.add(displayLabel, 2, 2);
        displayInfo.add(displayRecipient, 2, 3);
        displayInfo.add(displayRegion, 2, 4);
        displayInfo.add(displayStatus, 3, 1);
        /*
        checkoutPane.add(checkoutRegionLabel, 3, 2);
        checkoutPane.add(checkoutItemCountLabel, 3, 3);
        checkoutPane.add(checkoutPriceLabel, 3, 4);
        checkoutPane.add(checkoutButton, 2, 7);
        checkoutPane.add(cancelCheckoutButton, 3, 7);*/

        displayInfo.getColumnConstraints().addAll(
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        /* For Reference lang */
        displayCodeMenu.setCenter(displayInfo);
        /*
        System.out.print("Tracking Code: ");
        System.out.println (parcel.getTrackingCode());
        System.out.printf("Recipient: %s\n", parcel.getRecipient());
        System.out.printf("Region: %s\n", parcel.getRegion());
        System.out.printf("Status: %s\n", parcel.getStatus(getDate()));
        System.out.print("Items shipped:\n");
        parcel.displayItems(); */

        displayParcelScene = new Scene(displayCodeMenu, 700, 500);
>>>>>>> origin/kirsten
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

    public int getSpinnerDays()
    {
      return daySpinner.getValue();
    }

    public int getSpinnerHours()
    {
      return hourSpinner.getValue();
    }

    public int getSpinnerMins()
    {
      return minuteSpinner.getValue();
    }

    public int getSpinnerSecs()
    {
      return secondsSpinner.getValue();
    }

    public String getCodeInput()
    {
      System.out.println("I got this "+ enterCode.getText());
      return enterCode.getText();
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
            else if (node instanceof ComboBox)
            {
                ComboBox<?> cb = (ComboBox<?>)node;
                cb.setOnAction(handler);
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

    public ButtonType openInsuranceDialog()
    {
        Optional<ButtonType> result = insuranceDialog.showAndWait();
        return result.orElse(ButtonType.CANCEL);
    }

    public Item openAddItemDialog()
    {
        Optional<Item> result = addItemDialog.showAndWait();
        return result.orElse(null);
    }

    public Integer openTimeDialog()
    {
      Optional<Integer> result = adjustTimeDialog.showAndWait();
      return result.orElse(null); // Return current Date/Time
    }

    public static void main(String[] args)
    {
        //JohnnyMovesGui gui = new JohnnyMovesGui();
        //JohnnyMovesController controller = new JohnnyMovesController(gui);
        launch(args);
    }
}
