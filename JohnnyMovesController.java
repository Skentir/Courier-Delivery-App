import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;

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
        if (target instanceof Button)
        {
            Button button = (Button)target;
            switch (button.getId())
            {
                case "items-return": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "get-started": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "main-send": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "main-track": gui.setScene(JohnnyMovesGui.TRACKING); break;
                case "items-recipient": gui.setScene(JohnnyMovesGui.RECIPIENT); break;
                case "items-edit": gui.setScene(JohnnyMovesGui.ITEMS); break;
                case "items-checkout": gui.setScene(JohnnyMovesGui.CHECKOUT); break;
                case "items-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "items-add": break;
                case "items-remove": break;
                case "items-done": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "recipient-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "recipient-submit": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "checkout-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "checkout-checkout": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "track-submit": break;
                case "track-return": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
            }
        }
    }
}
