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
            switch (button.getText())
            {
                case "Go Back to Main Menu": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "Get Started": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "Send a Parcel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "Track a Parcel": gui.setScene(JohnnyMovesGui.TRACKING); break;
                case "Set Recipient": gui.setScene(JohnnyMovesGui.RECIPIENT); break;
                case "Add, remove, or view items": gui.setScene(JohnnyMovesGui.ITEMS); break;
                case "Checkout": gui.setScene(JohnnyMovesGui.CHECKOUT); break;
                case "Cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "Done": gui.setScene(JohnnyMovesGui.SENDING); break;
            }
        }
    }
}
