package kazpost.kz.supermarketsc.data.network.model.barcodeinforequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class BarcodeInfoRequestBody {

    @Element(name = "sch:BarcodeInfoRequest", required = false)
    private BarcodeInfoRequestData barcodeInfoRequestData;

    public void setBarcodeInfoRequestData(BarcodeInfoRequestData barcodeInfoRequestData) {
        this.barcodeInfoRequestData = barcodeInfoRequestData;
    }
}
