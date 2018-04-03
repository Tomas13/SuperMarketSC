package kazpost.kz.supermarketsc.data.network.model.regparcelrequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:ParcelInfo", strict = false)
public class ParcelInfo {

    @Element(name = "sch:Barcode", required = false)
    String barcode;

    @Element(name = "sch:ShelfBarcode", required = false)
    String ShelfBarcode;

    @Element(name = "sch:Sender", required = false)
    String Sender;

    @Element(name = "sch:Recipient", required = false)
    String Recipient;

    @Element(name = "sch:RecipientPhone", required = false)
    String RecipientPhone;

    @Element(name = "sch:MarketIndex", required = false)
    String MarketIndex;

    public void setShelfBarcode(String shelfBarcode) {
        ShelfBarcode = shelfBarcode;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public void setRecipientPhone(String recipientPhone) {
        RecipientPhone = recipientPhone;
    }

    public void setMarketIndex(String marketIndex) {
        MarketIndex = marketIndex;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
