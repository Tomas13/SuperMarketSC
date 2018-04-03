package kazpost.kz.supermarketsc.data.network;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import kazpost.kz.supermarketsc.data.network.model.Response;
import kazpost.kz.supermarketsc.data.network.model.TechIndex;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestBody;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestData;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestEnvelope;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.Envelope;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.ParcelInfo;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelData;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelRequestBody;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelRequestEnvelope;
import retrofit2.Call;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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


    public static final String TAG = "ApiHel";


    @Override
    public Observable<Envelope> requestBarcodeInfo(BarcodeInfoRequestEnvelope envelope) {
        return networkService.requestBarcodeInfo(envelope);
    }

    @Override
    public LiveData<Envelope> requestBarcodeInfo(String barcode) {

        BarcodeInfoRequestEnvelope envelope = new BarcodeInfoRequestEnvelope();
        BarcodeInfoRequestBody body = new BarcodeInfoRequestBody();
        BarcodeInfoRequestData data = new BarcodeInfoRequestData();
        data.setBarcode(barcode);
        body.setBarcodeInfoRequestData(data);

        envelope.setBarcodeInfoRequestBody(body);

        final MutableLiveData<Envelope> envelopeMutableLiveData = new MutableLiveData<>();

        networkService.requestBarcodeInfo(envelope)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope1 -> {
                    envelopeMutableLiveData.postValue(envelope1);
                    Log.d(TAG, "requestBarcodeInfo: " + envelope1);
                }, throwable -> {
                    Log.d(TAG, "throwable: " + throwable.getMessage());
//                    state.postValue(Status.ERROR);
                });
        return envelopeMutableLiveData;
    }

    @Override
    public LiveData<kazpost.kz.supermarketsc.data.network.model.regparcelrequest.Envelope> regParcel(RegParcelRequestEnvelope envelope) {

        RegParcelRequestEnvelope envelope1 = new RegParcelRequestEnvelope();
        RegParcelRequestBody body = new RegParcelRequestBody();
        RegParcelData data = new RegParcelData();
        ParcelInfo parcelInfo = new ParcelInfo();

        parcelInfo.setBarcode("Barcode");
        parcelInfo.setMarketIndex("010000");
        parcelInfo.setRecipient("1");
        parcelInfo.setSender("1");
        parcelInfo.setShelfBarcode("1234");
        parcelInfo.setRecipientPhone("87072226642");

        data.setParcelInfo(parcelInfo);
        body.setRegParcelData(data);
        envelope1.setRegParcelRequestBody(body);

        final MutableLiveData<kazpost.kz.supermarketsc.data.network.model.regparcelrequest.Envelope>
                envelopeMutableLiveData = new MutableLiveData<>();

        networkService.regParcel(envelope1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope2 -> {
                    envelopeMutableLiveData.postValue(envelope2);
                    Log.d(TAG, "regParcel: " + envelope1);
                }, throwable -> {
                    Log.d(TAG, "throwable: " + throwable.getMessage());
//                    state.postValue(Status.ERROR);
                });

        return envelopeMutableLiveData;
    }

}
