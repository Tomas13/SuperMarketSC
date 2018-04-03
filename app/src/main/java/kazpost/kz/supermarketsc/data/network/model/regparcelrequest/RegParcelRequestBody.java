package kazpost.kz.supermarketsc.data.network.model.regparcelrequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by root on 4/17/17.
 */

@Root(name = "soapenv:Body", strict = false)
public class RegParcelRequestBody {

    @Element(name = "sch:RegParcelRequest", required = false)
    private RegParcelData regParcelData;

    public void setRegParcelData(RegParcelData regParcelData) {
        this.regParcelData = regParcelData;
    }
}
