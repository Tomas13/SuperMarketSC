package kazpost.kz.supermarketsc.data.network.model.regparcelrequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:ParcelInfo", strict = false)
public class ParcelInfo {

    @Element(name = "sch:Barcode", required = false)
    String aBarcode;

    @Element(name = "sch:ShelfBarcode", required = false)
    String bShelfBarcode;

    @Element(name = "sch:Sender", required = false)
    String cSender;

    @Element(name = "sch:Recipient", required = false)
    String dRecipient;

    @Element(name = "sch:RecipientPhone", required = false)
    String eRecipientPhone;

    @Element(name = "sch:MarketIndex", required = false)
    String fMarketIndex;

    public void setaBarcode(String aBarcode) {
        this.aBarcode = aBarcode;
    }

    public void setbShelfBarcode(String bShelfBarcode) {
        this.bShelfBarcode = bShelfBarcode;
    }

    public void setcSender(String cSender) {
        this.cSender = cSender;
    }

    public void setdRecipient(String dRecipient) {
        this.dRecipient = dRecipient;
    }

    public void seteRecipientPhone(String eRecipientPhone) {
        this.eRecipientPhone = eRecipientPhone;
    }

    public void setfMarketIndex(String fMarketIndex) {
        this.fMarketIndex = fMarketIndex;
    }
}
