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
import java.util.List;

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
    TextField recipientField = new TextField();
    ComboBox<String> regionComboBox = new ComboBox<>();
    ToggleGroup insureGroup = new ToggleGroup();
    RadioButton selectedRadioBtn;
    ListView<Item> itemsList = new ListView<>();
    Label itemNameLabel = new Label();
    Label dimensionsLabel = new Label();
    Label weightLabel = new Label();
    Label typeLabel = new Label();

    ListView<Item> checkoutList = new ListView<>();
    Label checkoutRecipientLabel = new Label();
    Label checkoutRegionLabel = new Label();
    Label checkoutInsuredLabel = new Label();
    Label checkoutItemCountLabel = new Label();
    Label checkoutPriceLabel = new Label();

    ListView<Item> displayParcelItems = new ListView<>();
    Label displayTrackingCode = new Label();
    Label displayRecipient= new Label();
    Label displayRegion = new Label();
    Label displayStatus = new Label();

    RadioButton insuredButton = new RadioButton("Yes, insure");
    RadioButton notInsuredButton = new RadioButton("No, don't insure");

    GridPane addItemRoot;
    ComboBox<String> itemTypes = new ComboBox<>();
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

    Dialog<Recipient> recipientDialog;
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
        ImageView logo = new ImageView(new Image("ImageAssets/Logo.png"));
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

        Image header = new Image("ImageAssets/Header.png");
        ImageView parcelPic = new  ImageView(new Image("ImageAssets/Parcel.png"));
        ImageView magnifyingPic = new  ImageView(new Image("ImageAssets/Magnifying.png"));
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
        /* Scene 6 Button */
        Label promptCode = new Label("Enter Tracking Number");
    // enter code
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

        /* ------------------------------ */
        /* INITIALIZE SCENE 3 - RECIPIENT */
        /* ------------------------------ */
        recipientDialog = new Dialog<>();
        recipientDialog.setTitle("Enter recipient");
        GridPane recipientPane = new GridPane();

        recipientPane.setAlignment(Pos.CENTER);

        Label recipientNameLabel = new Label();
        recipientNameLabel.setText("Name");
        Label recipientRegionLabel = new Label();
        recipientRegionLabel.setText("Region");

        GridPane.setFillWidth(recipientField, true);
        GridPane.setMargin(recipientField, new Insets(5, 10, 5, 10));

        regionComboBox.getItems().addAll(Parcel.REGIONS);
        GridPane.setFillWidth(regionComboBox, true);
        GridPane.setMargin(regionComboBox, new Insets(5, 10, 5, 10));

        recipientDialog.setResultConverter(btype ->
        {
            switch (btype.getButtonData())
            {
            case OK_DONE:
                return new Recipient(recipientField.getText(), regionComboBox.getValue());
            default:
                return null;
            }
        });

        recipientPane.add(recipientNameLabel, 0, 0);
        recipientPane.add(recipientRegionLabel, 0, 1);
        recipientPane.add(recipientField, 1, 0, 2, 1);
        recipientPane.add(regionComboBox, 1, 1, 2, 1);

        recipientDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        recipientDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        recipientDialog.getDialogPane().setContent(recipientPane);

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
        itemsList.setId("items-list");
        GridPane.setFillWidth(itemsList, true);
        GridPane.setMargin(itemsList, new Insets(20, 20, 5, 10));

        Label itemNameLabel = new Label();
        GridPane.setMargin(itemNameLabel, new Insets(20, 0, 0, 0));
        itemNameLabel.setText("The quick brown fox jumps over the lazy dog.");
        Label dimensionsNameLabel = new Label();
        GridPane.setMargin(dimensionsNameLabel, new Insets(10, 10, 0, 5));
        GridPane.setMargin(dimensionsLabel, new Insets(10, 0, 0, 5));
        dimensionsNameLabel.setText("Dimensions: ");
        Label weightNameLabel = new Label();
        GridPane.setMargin(weightNameLabel, new Insets(10, 10, 0, 5));
        GridPane.setMargin(weightLabel, new Insets(10, 0, 0, 5));
        weightNameLabel.setText("Weight: ");
        Label typeNameLabel = new Label();
        GridPane.setMargin(typeNameLabel, new Insets(10, 10, 0, 5));
        GridPane.setMargin(typeLabel, new Insets(10, 0, 0, 5));
        typeNameLabel.setText("Type: ");

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
        itemsPane.add(itemNameLabel, 2, 0, 2, 1);
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
        FlowPane containerChoice = new FlowPane();
        containerChoice.setPadding(new Insets(5,0,5,0));
        containerChoice.setVgap(4);
        containerChoice.setHgap(4);
        containerChoice.setPrefWrapLength(255);
        ImageView containerAssets[] = new ImageView[6];
        for (int i=0; i<6; i++)
        {
          containerAssets[i] = new ImageView(new Image("ImageAssets/Container"+(i+1)+".png", 80, 80, true, true));
          containerChoice.getChildren().add(containerAssets[i]);
        }

        checkoutList = new ListView<>();
        GridPane.setHalignment(checkoutList, HPos.CENTER);
        GridPane.setFillWidth(checkoutList, true);
        GridPane.setMargin(checkoutList, new Insets(20, 20, 20, 10));

        Label checkoutRecipientNameLabel = new Label("Recipient:");
        GridPane.setMargin(checkoutRecipientNameLabel, new Insets(10, 0, 0, 5));
        GridPane.setMargin(checkoutRecipientLabel, new Insets(10, 0, 0, 5));
        Label checkoutRegionNameLabel = new Label("Region:");
        GridPane.setMargin(checkoutRegionNameLabel, new Insets(10, 0, 0, 5));
        GridPane.setMargin(checkoutRegionLabel, new Insets(10, 0, 0, 5));
        Label checkoutInsuredNameLabel = new Label("Insured:");
        GridPane.setMargin(checkoutInsuredNameLabel, new Insets(10, 0, 0, 5));
        GridPane.setMargin(checkoutInsuredLabel, new Insets(10, 0, 0, 5));
        Label checkoutItemCountNameLabel = new Label("# of items:");
        GridPane.setMargin(checkoutItemCountNameLabel, new Insets(10, 0, 0, 5));
        GridPane.setMargin(checkoutItemCountLabel, new Insets(10, 0, 0, 5));
        Label checkoutPriceNameLabel = new Label("Price:");
        GridPane.setMargin(checkoutPriceNameLabel, new Insets(10, 0, 0, 5));
        GridPane.setMargin(checkoutPriceLabel, new Insets(10, 0, 0, 5));

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
        checkoutPane.add(containerChoice, 0, 0,3,2);
        checkoutPane.add(checkoutRecipientNameLabel, 2, 1);
        checkoutPane.add(checkoutRegionNameLabel, 2, 2);
        checkoutPane.add(checkoutInsuredNameLabel, 2, 3);
        checkoutPane.add(checkoutItemCountNameLabel, 2, 4);
        checkoutPane.add(checkoutPriceNameLabel, 2, 5);
        checkoutPane.add(checkoutRecipientLabel, 3, 1);
        checkoutPane.add(checkoutRegionLabel, 3, 2);
        checkoutPane.add(checkoutInsuredLabel, 3, 3);
        checkoutPane.add(checkoutItemCountLabel, 3, 4);
        checkoutPane.add(checkoutPriceLabel, 3, 5);
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

        adjustTimeDialog.setResultConverter(btype ->
        {
            switch (btype.getButtonData())
            {
            case OK_DONE:
                return daySpinner.getValue() * 86400 + hourSpinner.getValue() * 3600 + minuteSpinner.getValue() * 60 + secondsSpinner.getValue();
            default:
                return null;
            }
        });

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

        insuredButton.setId("items-insured");
        GridPane.setMargin(insuredButton, new Insets(10, 0, 0, 0));
        notInsuredButton.setId("items-notinsured");
        GridPane.setMargin(notInsuredButton, new Insets(10, 0, 0, 0));

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

        addItemPane.getColumnConstraints().addAll(
            new ColumnConstraints(60.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(240.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        addItemDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        addItemDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        addItemDialog.setResultConverter(btype ->
        {
            String type = itemTypes.getValue();
            itemTypes.setValue(null);
            closeItemPane();
            switch (btype.getButtonData())
            {
            case OK_DONE:
                switch (type)
                {
                case "REGULAR PRODUCT":
                    double regProdLength = Double.parseDouble(productLengthField.getText());
                    double regProdWidth = Double.parseDouble(productWidthField.getText());
                    double regProdHeight = Double.parseDouble(productHeightField.getText());
                    double regProdWeight = Double.parseDouble(productWeightField.getText());
                    productLengthField.clear();
                    productWidthField.clear();
                    productHeightField.clear();
                    productWeightField.clear();
                    return new RegularProduct(null, regProdLength, regProdWidth, regProdHeight, regProdWeight);
                case "IRREGULAR PRODUCT":
                    double irregProdLength = Double.parseDouble(productLengthField.getText());
                    double irregProdWidth = Double.parseDouble(productWidthField.getText());
                    double irregProdHeight = Double.parseDouble(productHeightField.getText());
                    double irregProdWeight = Double.parseDouble(productWeightField.getText());
                    productLengthField.clear();
                    productWidthField.clear();
                    productHeightField.clear();
                    productWeightField.clear();
                    return new IrregularProduct(null, irregProdLength, irregProdWidth, irregProdHeight, irregProdWeight);
                case "DOCUMENT":
                    double docLength = Double.parseDouble(documentLengthField.getText());
                    double docWidth = Double.parseDouble(documentWidthField.getText());
                    int docPages = Integer.parseInt(documentPagesField.getText());
                    documentLengthField.clear();
                    documentWidthField.clear();
                    documentPagesField.clear();
                    return new Document(null, docLength, docWidth, docPages);
                default: return null; // TODO: custom exception
                }
            case CANCEL_CLOSE:
            default:
                return null;
            }
        });

        Label itemTypeLabel = new Label("Type");
        itemTypes.setId("items-type");
        itemTypes.getItems().addAll("REGULAR PRODUCT", "IRREGULAR PRODUCT", "DOCUMENT");
        productGroup = new GridPane();
        Label productWidthLabel = new Label("Width");
        Label productHeightLabel = new Label("Height");
        Label productLengthLabel = new Label("Length");
        Label productWeightLabel = new Label("Weight");
        productGroup.add(productWidthLabel, 0, 0);
        GridPane.setMargin(productWidthLabel, new Insets(10, 0, 0, 0));
        productGroup.add(productWidthField, 1, 0);
        GridPane.setMargin(productWidthField, new Insets(10, 0, 0, 5));
        productGroup.add(productHeightLabel, 0, 1);
        GridPane.setMargin(productHeightLabel, new Insets(10, 0, 0, 0));
        productGroup.add(productHeightField, 1, 1);
        GridPane.setMargin(productHeightField, new Insets(10, 0, 0, 5));
        productGroup.add(productLengthLabel, 0, 2);
        GridPane.setMargin(productLengthLabel, new Insets(10, 0, 0, 0));
        productGroup.add(productLengthField, 1, 2);
        GridPane.setMargin(productLengthField, new Insets(10, 0, 0, 5));
        productGroup.add(productWeightLabel, 0, 3);
        GridPane.setMargin(productWeightLabel, new Insets(10, 0, 0, 0));
        productGroup.add(productWeightField, 1, 3);
        GridPane.setMargin(productWeightField, new Insets(10, 0, 0, 5));

        documentGroup = new GridPane();
        Label documentWidthLabel = new Label("Width");
        Label documentLengthLabel = new Label("Length");
        Label documentPagesLabel = new Label("Pages");
        documentGroup.add(documentWidthLabel, 0, 0);
        GridPane.setMargin(documentWidthLabel, new Insets(10, 0, 0, 0));
        documentGroup.add(documentWidthField, 1, 0);
        GridPane.setMargin(documentWidthField, new Insets(10, 0, 0, 5));
        documentGroup.add(documentLengthLabel, 0, 1);
        GridPane.setMargin(documentLengthLabel, new Insets(10, 0, 0, 0));
        documentGroup.add(documentLengthField, 1, 1);
        GridPane.setMargin(documentLengthField, new Insets(10, 0, 0, 5));
        documentGroup.add(documentPagesLabel, 0, 2);
        GridPane.setMargin(documentPagesLabel, new Insets(10, 0, 0, 0));
        documentGroup.add(documentPagesField, 1, 2);
        GridPane.setMargin(documentPagesField, new Insets(10, 0, 0, 5));

        productGroup.getColumnConstraints().addAll(
            new ColumnConstraints(60.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(240.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );

        documentGroup.getColumnConstraints().addAll(
            new ColumnConstraints(60.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(240.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );


        addItemPane.add(itemTypeLabel, 0, 0);
        addItemPane.add(itemTypes, 1, 0);
        GridPane.setFillWidth(itemTypes, true);

        addItemRoot = addItemPane;

        addItemDialog.getDialogPane().setContent(addItemPane);

        insuranceDialog.getDialogPane().setContent(insurancePane);

        /* --------------------------------- */
        /* INITIALIZE SCENE 7 - DISPLAY CODE */
        /* --------------------------------- */
        BorderPane displayCodeMenu = new BorderPane();
        GridPane displayInfo = new GridPane();
        StackPane displayCodeTop = new StackPane();
        displayCodeTop.getChildren().add(new ImageView(header));
        displayCodeTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        displayCodeMenu.setTop(displayCodeTop);
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


        displayInfo.getColumnConstraints().addAll(
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE),
            new ColumnConstraints(175.0, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE)
        );
        displayCodeMenu.setCenter(displayInfo);


        displayParcelScene = new Scene(displayCodeMenu, 700, 500);
    }

    public void updateItems(List<Item> items)
    {
        if (items == null)
            itemsList.getItems().clear();
        else
            itemsList.getItems().setAll(items);
    }

    public void updateCheckoutInfo(Recipient recipient, boolean insured, List<Item> items)
    {
        if (items == null)
        {
            checkoutList.getItems().clear();
            checkoutItemCountLabel.setText("");
        }
        else
        {
            checkoutList.getItems().setAll(items);
            checkoutItemCountLabel.setText(Integer.toString(items.size()));
        }

        if (recipient == null)
        {
            checkoutRecipientLabel.setText("");
            checkoutRegionLabel.setText("");
            checkoutInsuredLabel.setText("");
        }
        else
        {
            checkoutRecipientLabel.setText(recipient.getName());
            checkoutRegionLabel.setText(recipient.getRegion());
            checkoutInsuredLabel.setText(insured ? "Yes" : "No");
        }
    }

    public Item getSelectedItem()
    {
        return itemsList.getSelectionModel().getSelectedItem();
    }

    public void updateItemDetails(Item item)
    {
        if (item != null)
        {
            dimensionsLabel.setText(String.format("%.2f\" x %.2f\" x %.2f\"", item.getWidth(), item.getHeight(), item.getLength()));
            weightLabel.setText(String.format("%.2f kg", item.getWeight()));
            typeLabel.setText(item.getItemType());
        }
        else
        {
            dimensionsLabel.setText("");
            weightLabel.setText("");
            typeLabel.setText("");
        }
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
        case DISPLAY_CODE: s = displayParcelScene; break;
        }

        if (s != null)
        {
            stage.setScene(s);
        }
    }

    public void close()
    {
        stage.close();
    }

    public String getCodeInput()
    {
      System.out.println("I got this " + enterCode.getText());
      return enterCode.getText();
    }

    public String getRecipient()
    {
      System.out.println("Reciever is " + recipientField.getText());
      return recipientField.getText();
    }

    public String getRegion()
    {
      System.out.println("Region is " + regionComboBox.getValue());
      return regionComboBox.getValue();
    }

    public String getInsurance()
    {
        selectedRadioBtn = (RadioButton) insureGroup.getSelectedToggle();
        if (selectedRadioBtn != null)
        {
            System.out.println("Insurance choice is " + selectedRadioBtn.getText());
            return selectedRadioBtn.getId();
        }
        return null;
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
        attachHandlerToScene(itemsScene, handler);
        attachHandlerToScene(checkoutScene, handler);
        attachHandlerToPane(addItemRoot, handler);
    }

    public void addMouseListener(EventHandler<MouseEvent> handler)
    {
        itemsList.setOnMouseClicked(handler);
    }

    public void openProductPane()
    {
        addItemRoot.getChildren().removeAll(documentGroup, productGroup);
        addItemRoot.add(productGroup, 0, 1, 2, 1);
        addItemDialog.setHeight(320.0);
    }

    public void openDocumentPane()
    {
        addItemRoot.getChildren().remove(productGroup);
        addItemRoot.add(documentGroup, 0, 1, 2, 1);
        addItemDialog.setHeight(270.0);
    }

    public void closeItemPane()
    {
        addItemRoot.getChildren().removeAll(documentGroup, productGroup);
        addItemDialog.setHeight(70.0);
    }

    public void updateAddItemPane()
    {
        String selected = itemTypes.getValue();
        if (selected != null)
        {
            if (selected.contains("PRODUCT"))
                openProductPane();
            else if (selected.contains("DOCUMENT"))
                openDocumentPane();
            else
                closeItemPane();
        }
    }

    public Recipient openRecipientDialog()
    {
        Optional<Recipient> result = recipientDialog.showAndWait();
        return result.orElse(null);
    }

    public Recipient openRecipientDialog(Recipient recipient)
    {
        if (recipient != null)
        {
            recipientField.setText(recipient.getName());
            regionComboBox.setValue(recipient.getRegion());
        }
        else
        {
            recipientField.setText("");
            regionComboBox.setValue("");
        }
        return openRecipientDialog();
    }

    public ButtonType openInsuranceDialog()
    {
        Optional<ButtonType> result = insuranceDialog.showAndWait();
        return result.orElse(ButtonType.CANCEL);
    }

    public ButtonType openInsuranceDialog(boolean value)
    {
        if (value)
            insureGroup.selectToggle(insuredButton);
        else
            insureGroup.selectToggle(notInsuredButton);
        return openInsuranceDialog();
    }

    public Item openAddItemDialog()
    {
        Optional<Item> result = addItemDialog.showAndWait();
        return result.orElse(null);
    }

    public Integer openTimeDialog()
    {
        Optional<Integer> result = adjustTimeDialog.showAndWait();
        return result.orElse(null);
    }

    public static void main(String[] args)
    {
        //JohnnyMovesGui gui = new JohnnyMovesGui();
        //JohnnyMovesController controller = new JohnnyMovesController(gui);
        launch(args);
    }
}
