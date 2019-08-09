import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.Node;
import java.util.*;

public class JohnnyMovesController implements EventHandler<ActionEvent>
{
    private JohnnyMovesGui gui;

    public JohnnyMovesController(JohnnyMovesGui gui)
    {
        this.gui = gui;
        gui.addActionListener(this);
    }

    @Override
    public void handle(ActionEvent event)
    {
        EventTarget target = event.getTarget();
        if (target instanceof Node)
        {
            Node node = (Node)target;

            Alert alert;
            Optional<ButtonType> result;
            switch (node.getId())
            {
            case "items-return": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
            case "get-started": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
            case "main-send": gui.setScene(JohnnyMovesGui.SENDING); break;
            case "main-track": gui.setScene(JohnnyMovesGui.TRACKING); break;
            case "items-recipient":
                Recipient recipient = gui.openRecipientDialog();
                break;
            case "items-edit": gui.setScene(JohnnyMovesGui.ITEMS); break;
            case "items-type":
                gui.updateAddItemPane();
                break;
            case "items-insurance":
                ButtonType type = gui.openInsuranceDialog();
                break;
            case "items-checkout": gui.setScene(JohnnyMovesGui.CHECKOUT); break;
            case "items-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
            case "items-add":
                Item item = gui.openAddItemDialog();
                break;
            case "items-remove":
                alert = new Alert(AlertType.WARNING, "Removing an item cannot be undone. Proceed?", ButtonType.YES, ButtonType.NO);
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.YES)
                {
                    // TODO: remove the item
                }
                break;
                case "items-done": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "recipient-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "recipient-submit": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "checkout-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "checkout-checkout": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "track-submit":
                   gui.openTimeDialog();
                   int daysOffset = gui.getSpinnerDays();
                   int hoursOffset = gui.getSpinnerHours();
                   int minsOffset = gui.getSpinnerMins();
                   int secsOffset = gui.getSpinnerSecs();
                   Date now = new Date();
                   new Date(now.getTime() + daysOffset * 86400L * 1000L);
                case "track-return": gui.setScene(JohnnyMovesGui.MAIN_MENU);

                break;
            }
        }
    }
}
