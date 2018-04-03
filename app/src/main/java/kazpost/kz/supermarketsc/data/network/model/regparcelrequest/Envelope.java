package kazpost.kz.supermarketsc.data.network.model.regparcelrequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
public class Envelope {

    @Element(name = "Header", required = false)
    String header;

    @Element(name = "Body", required = false)
    Body body;

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String value) {
        this.header = value;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body value) {
        this.body = value;
    }


    public static class ResponseInfo {

        @Element(name = "ResponseCode", required = false)
        String responseCode;

        @Element(name = "ResponseText", required = false)
        String responseText;

        @Element(name = "ResponseGenTime", required = false)
        String responseGenTime;

        public String getResponseCode() {
            return this.responseCode;
        }

        public void setResponseCode(String value) {
            this.responseCode = value;
        }

        public String getResponseText() {
            return this.responseText;
        }

        public void setResponseText(String value) {
            this.responseText = value;
        }

        public String getResponseGenTime() {
            return this.responseGenTime;
        }

        public void setResponseGenTime(String value) {
            this.responseGenTime = value;
        }

    }

    public static class RegParcelResponse {

        @Element(name = "ResponseInfo", required = false)
        ResponseInfo responseInfo;

        public ResponseInfo getResponseInfo() {
            return responseInfo;
        }
    }

    public static class Body {

        @Element(name = "RegParcelResponse", required = false)
        RegParcelResponse regParcelResponse;

        public RegParcelResponse getRegParcelResponse() {
            return regParcelResponse;
        }

        public void setRegParcelResponse(RegParcelResponse regParcelResponse) {
            this.regParcelResponse = regParcelResponse;
        }
    }

}