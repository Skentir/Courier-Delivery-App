import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.text.SimpleDateFormat;
import java.util.*;

public class JohnnyMovesController implements EventHandler<ActionEvent>
{
    private JohnnyMovesGui gui;
    private ArrayList<Parcel> parcels;

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

            Alert alert;
            Optional<ButtonType> result;
            switch (button.getId())
            {
            case "items-return": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
            case "get-started": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
            case "main-send": gui.setScene(JohnnyMovesGui.SENDING); break;
            case "main-track": gui.setScene(JohnnyMovesGui.TRACKING); break;
            case "items-recipient": gui.setScene(JohnnyMovesGui.RECIPIENT); break;
            case "items-edit": gui.setScene(JohnnyMovesGui.ITEMS); break;
            case "items-type": break;
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
                case "track-main": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "track-submit":
                  String code = gui.getCodeInput();
                  if (code.length() >= 15)
                  {
                    System.out.println("Entered a something");
                    if (isValidCode(code) && code != null) //TODO: Fix this part
                    {
                      gui.openTimeDialog();
                      int daysOffset = gui.getSpinnerDays();
                      int hoursOffset = gui.getSpinnerHours();
                      int minsOffset = gui.getSpinnerMins();
                      int secsOffset = gui.getSpinnerSecs();
                      Date now = new Date();
                      new Date(now.getTime() + daysOffset * 86400L * 1000L);
                      //TODO: Update the formula
                      Parcel p = parcels.get(parcels.size()-1);
                      p.setTrackingCode(generateCode(p));
                    }
                    else
                    {
                      alert = new Alert(AlertType.WARNING, "Invalid Input. Try Again.");
                      alert.setTitle("Error");
                      alert.setContentText("A correct tracking code contains 15 characters.\nSample Code: FLT0821VIS0301");
                      alert.showAndWait();
                      break;
                    }
                  }
                  else
                  {
                    System.out.println("Entered nothing :( ");
                    alert = new Alert(AlertType.WARNING, "Please enter a code. Try Again.");
                    alert.setTitle("Error");
                    alert.setContentText("A correct tracking code contains 15 characters.\nSample Code: FLT0821VIS0301");
                    alert.showAndWait();
                    break;
                  }
                case "track-return":
                  gui.setScene(JohnnyMovesGui.DISPLAY_CODE);
                  break;
            }
        }
    }

    /**
     * Checks if the tracking code passed is valid.
     *
     * @return true or false
     */
    public boolean isValidCode(String code)
    {
      for (int i = 0; i < parcels.size(); i++)
      {
        Parcel p = parcels.get(i);
        if (p.getTrackingCode().equalsIgnoreCase(code))
          return true;
      }
      return false;
    }


      /**
       * Generates a tracking code for the parcel.
       *
       * @param parcel the parcel whose tracking code is to be generated.
       *
       * @return the tracking code for the parcel.
       */
      public String generateCode(Parcel parcel)
      {
        String code, dest, itemNum, pType, seq, date;
        dest = parcel.getRegionCode(); /* Gets location code: MNL, VIS, MIN, or LUZ */
        itemNum = String.format("%02d", parcel.getItems().size()); /* Gets number of items */
        pType = parcel.getParcelType(); /* Get parcel Type: FLT or BOX */
        date = dateCode(parcel); /* Set date in string format of MMDD */
        seq = String.format("%03d", seqCode(parcel)); /* Number for the day */
        code = pType + date + dest + itemNum + seq;
        return code;
      }
        /**
         * Returns an incremental sequence code for the day.
         *
         * @param parcel the parcel
         *
         * @return the sequence code starting from 1
         */
        private int seqCode(Parcel parcel)
        {
          Date target = parcel.getShipDate();
          //Collections.sort(parcels, new ParcelComparator());
          int seq = 0, i = 0;
          boolean found;
          /* Find the index where the first occurance of the Month and Day of the parcel sent */

          do
          {
            seq++;
            found = true;
            for (int j = 0; j < parcels.size(); j++)
            {
              String trackingCode = parcels.get(j).getTrackingCode();
              String dateCode1 = trackingCode.substring(3, 7);
              String dateCode2 = dateCode(parcel);
              int seqCode1 = Integer.parseInt(trackingCode.substring(12, 15));
              if (dateCode1.equals(dateCode2) && seq == seqCode1) {
                found = false;
                break;
              }
            }

          } while (!found);

          return seq;
        }

        /**
         * Returns a date code of the form MMDD, representing the shipping date of
         * the parcel.
         *
         * @param parcel the parcel
         *
         * @return a date code
         */
        private String dateCode(Parcel parcel)
        {
          Date shipDate = parcel.getShipDate();
          SimpleDateFormat fDate = new SimpleDateFormat("MMdd");
          return fDate.format(shipDate);
        }

}
