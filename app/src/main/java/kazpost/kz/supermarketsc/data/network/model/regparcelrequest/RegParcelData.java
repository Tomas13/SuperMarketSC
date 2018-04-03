package kazpost.kz.supermarketsc.data.network.model.regparcelrequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sch:RegParcelRequest", strict = false)
public class RegParcelData {

    @Element(name = "sch:ParcelInfo", required = false)
    private ParcelInfo parcelInfo;

    public void setParcelInfo(ParcelInfo parcelInfo) {
        this.parcelInfo = parcelInfo;
    }
}
