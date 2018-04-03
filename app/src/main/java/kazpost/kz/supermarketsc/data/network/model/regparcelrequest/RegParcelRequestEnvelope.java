package kazpost.kz.supermarketsc.data.network.model.regparcelrequest;

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
public class RegParcelRequestEnvelope {

    @Element(name = "soapenv:Header", required = false)
    private String header = "";

    @Element(name = "soapenv:Body", required = false)
    private RegParcelRequestBody regParcelRequestBody;

    public RegParcelRequestBody getRegParcelRequestBody() {
        return regParcelRequestBody;
    }

    public void setRegParcelRequestBody(RegParcelRequestBody regParcelRequestBody) {
        this.regParcelRequestBody = regParcelRequestBody;
    }

}
