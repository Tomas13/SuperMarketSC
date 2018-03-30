package kazpost.kz.supermarketsc.data.network;


import java.util.List;
import java.util.Map;

import kazpost.kz.supermarketsc.data.network.model.Response;
import kazpost.kz.supermarketsc.data.network.model.TechIndex;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by root on 4/18/17.
 */

public interface NetworkService {


    @GET("https://bot.post.kz/api/supermarkets")
    Observable<List<TechIndex>> getTechIndexList();


    @GET("http://pls-test.post.kz/api/mobile/save-supermarket-cell")
    Observable<Response> sendData(
            @QueryMap Map<String, String> params);

    @GET("http://pls-test.post.kz/api/mobile/save-supermarket-cell")
    Call<Response> sendCallData(
            @QueryMap Map<String, String> params);


}
