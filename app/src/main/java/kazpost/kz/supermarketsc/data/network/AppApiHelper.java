package kazpost.kz.supermarketsc.data.network;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import kazpost.kz.supermarketsc.data.network.model.Response;
import kazpost.kz.supermarketsc.data.network.model.TechIndex;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    NetworkService networkService;


    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<List<TechIndex>> getTechIndexList() {
        return networkService.getTechIndexList();
    }

    @Override
    public Observable<Response> sendData(Map<String, String> stringMap) {
        return networkService.sendData(stringMap);
    }

    @Override
    public Call<Response> sendCallData(Map<String, String> stringMap) {
        return networkService.sendCallData(stringMap);
    }

}
