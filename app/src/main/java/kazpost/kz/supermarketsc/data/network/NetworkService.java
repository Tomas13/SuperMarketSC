package kazpost.kz.supermarketsc.data.network;


import java.util.List;
import java.util.Map;

import kazpost.kz.supermarketsc.data.network.model.Response;
import kazpost.kz.supermarketsc.data.network.model.TechIndex;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestEnvelope;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.Envelope;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelRequestEnvelope;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by root on 4/18/17.
 */

public interface NetworkService {

    @POST("parcelmarket/endpoint")
    @Headers("Content-Type: text/xml")
    Observable<Envelope> requestBarcodeInfo(@Body BarcodeInfoRequestEnvelope envelope);


    @POST("parcelmarket/endpoint")
    @Headers("Content-Type: text/xml")
    Observable<kazpost.kz.supermarketsc.data.network.model.regparcelrequest.Envelope> regParcel(@Body RegParcelRequestEnvelope envelope);


    @GET("https://bot.post.kz/api/supermarkets")
    Observable<List<TechIndex>> getTechIndexList();


    @GET("http://pls-test.post.kz/api/mobile/save-supermarket-cell")
    Observable<Response> sendData(
            @QueryMap Map<String, String> params);

    @GET("http://pls-test.post.kz/api/mobile/save-supermarket-cell")
    Call<Response> sendCallData(
            @QueryMap Map<String, String> params);


}
