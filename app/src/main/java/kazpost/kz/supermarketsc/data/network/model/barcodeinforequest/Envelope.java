package kazpost.kz.supermarketsc.data.network.model.barcodeinforequest;

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

    public static class BarcodeInfoResponse {

        @Element(name = "ResponseInfo", required = false)
        ResponseInfo responseInfo;

        @Element(name = "Sndr", required = false)
        String sndr;

        @Element(name = "Rcpn", required = false)
        String rcpn;

        @Element(name = "SndrPhone", required = false)
        String sndrPhone;

        @Element(name = "SndrIIN", required = false)
        String sndrIIN;

        public String getSndr() {
            return sndr;
        }

        public String getRcpn() {
            return rcpn;
        }

        public String getSndrPhone() {
            return sndrPhone;
        }

        public String getSndrIIN() {
            return sndrIIN;
        }

        public ResponseInfo getResponseInfo() {
            return responseInfo;
        }
    }

    public static class Body {

        @Element(name = "BarcodeInfoResponse", required = false)
        BarcodeInfoResponse savePaymentSrvResponse;

        public void setSavePaymentSrvResponse(BarcodeInfoResponse savePaymentSrvResponse) {
            this.savePaymentSrvResponse = savePaymentSrvResponse;
        }

        public BarcodeInfoResponse getSavePaymentSrvResponse() {
            return savePaymentSrvResponse;
        }
    }

}