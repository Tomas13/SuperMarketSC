package kazpost.kz.supermarketsc.data.network.model.barcodeinforequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "sch", reference = "http://webservices.kazpost.kz/parcelmarket/schema"),
})
@Root(name = "soapenv:Envelope", strict = false)
public class BarcodeInfoRequestEnvelope {

    @Element(name = "soapenv:Header", required = false)
    private String header = "";

    @Element(name = "soapenv:Body", required = false)
    private BarcodeInfoRequestBody barcodeInfoRequestBody;

    public BarcodeInfoRequestBody getBarcodeInfoRequestBody() {
        return barcodeInfoRequestBody;
    }

    public void setBarcodeInfoRequestBody(BarcodeInfoRequestBody barcodeInfoRequestBody) {
        this.barcodeInfoRequestBody = barcodeInfoRequestBody;
    }

}
