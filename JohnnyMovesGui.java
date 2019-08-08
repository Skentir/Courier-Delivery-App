import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    public static final String MAIN_MENU = "MENU";
    public static final String SENDING = "SENDING";

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

        /* Scene 1 Buttons */
        Button sendBtn = new Button();
        Button trackBtn = new Button();
        sendBtn.setText("Send a Parcel");
        trackBtn.setText("Track a Parcel");
        //sendBtn.setOnAction(e -> primaryStage.setScene(sending));

        Image image = null;
        ImageView imageView = null;
        try
        {
            image = new Image(new FileInputStream("logo.png"));
            imageView = new ImageView(image);
        }
        catch (FileNotFoundException e)
        {

        }

        /* Layout 1 for Main Menu */
        VBox mainMenu = new VBox(3);
        mainMenu.getChildren().addAll(sendBtn, trackBtn);
        if (imageView != null)
            mainMenu.getChildren().add(imageView);
        menuScene = new Scene(mainMenu, 700, 450);

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
        //backToMain.setOnAction(e -> primaryStage.setScene(menu));

        /* Layout 2 for Send Menu */
        VBox sendMenu = new VBox(20);
        sendMenu.getChildren().addAll(setRecipient, setInsurance, modifyItems, checkout, backToMain);
        sendingScene = new Scene(sendMenu, 700, 450);

    }

    public void setScene(String scene)
    {
        Scene s = null;
        switch (scene)
        {
        case MAIN_MENU: s = menuScene; break;
        case SENDING: s = sendingScene; break;
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
    }

    public static void main(String[] args)
    {
        //JohnnyMovesGui gui = new JohnnyMovesGui();
        //JohnnyMovesController controller = new JohnnyMovesController(gui);
        launch(args);
    }
}
