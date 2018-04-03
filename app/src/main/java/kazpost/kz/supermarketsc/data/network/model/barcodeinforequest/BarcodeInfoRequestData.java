package kazpost.kz.supermarketsc.data.network.model.barcodeinforequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:BarcodeInfoRequest", strict = false)
public class BarcodeInfoRequestData {

    @Element(name = "sch:Barcode", required = false)
    String barcode;

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
