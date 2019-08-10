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
import javafx.scene.image.*;
import javafx.scene.Node;
import java.util.*;
import packer.*;

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
    private String parcelSize;

    // DO NOT DECLARE A TEMPORARY PARCEL FIELD HERE
    private boolean insured;

    public JohnnyMovesController(JohnnyMovesGui gui)
    {
        this.gui = gui;
        gui.addActionListener(this);
        parcels = new ArrayList<>();
        items = new ArrayList<>();
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
                Recipient r = gui.openRecipientDialog(this.recipient);
                if (r != null)
                    this.recipient = r;
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
                    gui.updateCheckoutInfo(null);
                    gui.setScene(JohnnyMovesGui.MAIN_MENU);
                }
                break;
            case "items-insurance":
                ButtonType type = gui.openInsuranceDialog(insuredValue);
                String insured  = gui.getInsurance();

                if (type.getButtonData() == ButtonData.APPLY)
                    insuredValue = insured.equals("items-insured");
                break;
            case "items-checkout":
                gui.setScene(JohnnyMovesGui.CHECKOUT);
                parcelSize = null;

                if (recipient != null && items != null)
                {
                    Parcel dummy = new Parcel(recipient, items);
                    dummy.setInsurance(insuredValue);
                    // Get List of possible box sizes and update the list
                    Container[] sizes = compute(dummy, items);

                    if (sizes.length != 0)
                    {
                        gui.updateContainerChoice(sizes);
                        // Assign the type and compute for the price
                        Container selectedBox = gui.getSelectedContainer();
                        String selsize = gui.getSelectedSize();
                        if (selsize != null)
                        {
                            dummy.setParcelType(selsize);
                        }
                        gui.updateCheckoutInfo(dummy);
                        gui.checkoutPriceLabel.setText("Php " + Double.toString(dummy.getPrice()));
                    }
                    else
                    {
                        Alert noFit = new Alert(AlertType.ERROR, "Oops! We can't fit your items in any of our containers. :(", ButtonType.OK);
                        noFit.showAndWait();
                        gui.setScene(JohnnyMovesGui.SENDING);
                    }
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
                case "checkout-copy": gui.copyTrackingToClipboard(); break;
                case "checkout-checkout":
                    String selectedSize = gui.getSelectedSize();

                  if (selectedSize == null || this.recipient == null || this.recipient.getName().length() == 0 )
                  {
                    System.out.println("No recipient exists");
                    alert = new Alert(AlertType.WARNING, "Missing Details. Fill up all fields.", ButtonType.OK);
                    result = alert.showAndWait();

                  }
                  else
                  {
                      String parcelSize;
                      switch (selectedSize)
                      {
                      case "toggle-flat1": parcelSize = "FLT0"; break;
                      case "toggle-flat2": parcelSize = "FLT1"; break;
                      case "toggle-box1": parcelSize = "BOX0"; break;
                      case "toggle-box2": parcelSize = "BOX1"; break;
                      case "toggle-box3": parcelSize = "BOX2"; break;
                      case "toggle-box4": parcelSize = "BOX3"; break;
                      default: parcelSize = null; break;
                      }
                      System.out.println(parcelSize);
                      System.out.println("Recipient exists");
                      Parcel buff = new Parcel(recipient, items);
                      buff.setInsurance(insuredValue);
                      buff.setParcelType(parcelSize);
                      buff.setTrackingCode(generateCode(buff));
                      parcels.add(buff);
                      gui.generateReceiptDialog(buff);
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
                        {
                          if (parcels.get(i).getTrackingCode().equalsIgnoreCase(code))
                          {
                            gui.displayTrackingCode.setText(code);
                            gui.displayRecipient.setText(parcels.get(i).getRecipient().getName());
                            gui.displayRegion.setText(parcels.get(i).getRecipient().getRegion());
                            gui.displayStatus.setText(parcels.get(i).getStatus(date));
                            gui.displayTrackedItems(parcels.get(i).getItems());
                            if (parcels.get(i).getStatus(date).equals("Shipping"))
                              gui.statusIcon.setImage(new Image("ImageAssets/In-Transit.png", 150, 150, true, true));
                            else
                              gui.statusIcon.setImage(new Image("ImageAssets/Delivered.png", 150, 150, true, true));
                              gui.displayDimensions.setText(parcels.get(i).getDimensions().toString().substring(1, parcels.get(i).getDimensions().toString().length()-1));
                          }
                        }
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
     * Gets a list of all the containers that would fit all the items. One of
     * these containers would end up being the size of the parcel.
     *
     * @param parcel the parcel
     * @param items all the items to be packed
     *
     * @return an array of all the containers that can fit the items
     */
    public Container[] compute(Parcel parcel, List<Item> items)
    {
      ParcelPacker packer = new ParcelPacker();
      Item[] itemsArr = items.toArray(new Item[0]);
      return packer.pack(parcel, itemsArr).toArray(new Container[0]);
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
