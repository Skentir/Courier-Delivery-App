import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import java.text.SimpleDateFormat;
import javafx.scene.Node;
import java.util.*;

public class JohnnyMovesController implements EventHandler<ActionEvent>
{
    private JohnnyMovesGui gui;
    private ArrayList<Parcel> parcels;
    private ArrayList<Item> items;
    private Recipient recipient;
    private boolean insuredValue;
    private String parcelType = "FLT";
    private String generatedCode;
    private String status;

    // DO NOT DECLARE A TEMPORARY PARCEL FIELD HERE
    private boolean insured;

    public JohnnyMovesController(JohnnyMovesGui gui)
    {
        this.gui = gui;
        gui.addActionListener(this);
        gui.addMouseListener(event ->
        {
            Item item = gui.getSelectedItem();
            gui.updateItemDetails(item);
            event.consume();
        });
        parcels = new ArrayList<>();

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
            // purely navigational buttons
            case "get-started": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
            case "main-send": gui.setScene(JohnnyMovesGui.SENDING); break;
            case "main-track": gui.setScene(JohnnyMovesGui.TRACKING); break;

            // send packing
            case "items-recipient":
                recipient = gui.openRecipientDialog();
                if (recipient != null)
                    this.recipient = recipient;
                break;
            case "items-edit": gui.setScene(JohnnyMovesGui.ITEMS); break;
            case "items-type":
                gui.updateAddItemPane();
                break;
            case "items-return":
                Alert closeItems = new Alert(AlertType.WARNING, "Items added will not be saved.", ButtonType.YES, ButtonType.NO);
                boolean close = true;
                if (items.size() > 0)
                {
                    result = closeItems.showAndWait();
                    close = result.isPresent() && result.get() == ButtonType.YES;
                }

                if (close)
                {
                    items.clear();
                    gui.updateItemDetails(null);
                    gui.updateItems(null);
                    gui.setScene(JohnnyMovesGui.MAIN_MENU);
                }
                break;
            case "items-insurance":
                ButtonType type = gui.openInsuranceDialog();
                String insured  = gui.getInsurance();

                if (insured.equals("Yes, insure"))
                    insuredValue = true;
                else
                    insuredValue = false;
                break;
            case "items-checkout":
                gui.setScene(JohnnyMovesGui.CHECKOUT);

                if (recipient == null || recipient.getName().length() == 0)
                  gui.checkoutRecipientLabel.setText("Empty");
                else
                  gui.checkoutRecipientLabel.setText(recipient.getName());
                if (recipient == null)
                  gui.checkoutRegionLabel.setText("Nowhere");
                else
                  gui.checkoutRegionLabel.setText(recipient.getRegion());

                //TODO Let user pick a parcel types

                if (recipient != null && items != null)
                {
                  Parcel dummy = new Parcel(recipient, items);
                  dummy.setParcelType(parcelType);
                  dummy.setInsurance(insuredValue);
                  gui.checkoutItemCountLabel.setText(Integer.toString(items.size()));
                  gui.checkoutPriceLabel.setText("Php " + Double.toString(dummy.getBasePrice()));
                }
                else
                {
                  gui.checkoutItemCountLabel.setText("Empty");
                  gui.checkoutPriceLabel.setText("Php " + 0.00);
                }
                break;
            case "items-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
            case "items-add":
                Item item = gui.openAddItemDialog();
                if (item != null)
                {
                    items.add(item);
                    gui.updateItems(items);
                }
                break;
            case "items-remove":
                alert = new Alert(AlertType.WARNING, "Removing an item cannot be undone. Proceed?", ButtonType.YES, ButtonType.NO);
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.YES)
                {
                    items.remove(gui.getSelectedItem());
                    gui.updateItems(items);
                    gui.updateItemDetails(gui.getSelectedItem());
                }
                break;
                case "items-done": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "recipient-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "recipient-submit": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "checkout-cancel": gui.setScene(JohnnyMovesGui.SENDING); break;
                case "checkout-checkout":
                  if (recipient == null || recipient.getName().length() != 0 )
                  {
                    System.out.println("No recipient exists");
                    alert = new Alert(AlertType.WARNING, "Missing Details. Fill up all fields.", ButtonType.OK);
                    result = alert.showAndWait();
                  }
                  else
                  {
                      System.out.println("Recipient exists");
                      Parcel buff = new Parcel(recipient, items);
                      buff.setInsurance(insuredValue);
                      buff.setParcelType(parcelType);
                      parcels.add(buff);
                      Parcel p = parcels.get(parcels.size()-1);
                      p.setTrackingCode(generateCode(p));
                      // TODO Display a dialog box containing code and lets user copy it
                      p.getBasePrice();
                      gui.setScene(JohnnyMovesGui.MAIN_MENU);
                  }
                 break;
                case "track-main": gui.setScene(JohnnyMovesGui.MAIN_MENU); break;
                case "track-submit":
                  String code = gui.getCodeInput();
                  Date date;
                  if (code.length() >= 15)
                  {
                    System.out.println("Entered a something");
                    if (isValidCode(code))
                    {
                        Integer tmpTime = gui.openTimeDialog();
                        if (tmpTime != null)
                        {
                            int time = tmpTime;
                            date = new Date(new Date().getTime() + time * 1000L);
                        }
                        else
                        {
                          int time = tmpTime;
                          date = new Date(new Date().getTime() + time * 1000L);
                        }

                        for (int i=0; i < parcels.size(); i++)
                          if (parcels.get(i).getTrackingCode().equalsIgnoreCase(code))
                            status = parcels.get(i).getStatus(date);
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
