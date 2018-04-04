package kazpost.kz.supermarketsc.data.network;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import kazpost.kz.supermarketsc.data.network.model.Response;
import kazpost.kz.supermarketsc.data.network.model.TechIndex;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestBody;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestCallback;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestData;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestEnvelope;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.Envelope;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.ParcelInfo;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelData;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelRequestBody;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelRequestCallback;
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
    public LiveData<Envelope> requestBarcodeInfo(String barcode, BarcodeInfoRequestCallback callback) {

        BarcodeInfoRequestEnvelope envelope = new BarcodeInfoRequestEnvelope();
        BarcodeInfoRequestBody body = new BarcodeInfoRequestBody();
        BarcodeInfoRequestData data = new BarcodeInfoRequestData();
        data.setBarcode(barcode);
        body.setBarcodeInfoRequestData(data);

        envelope.setBarcodeInfoRequestBody(body);

        callback.onDataLoading();

        final MutableLiveData<Envelope> envelopeMutableLiveData = new MutableLiveData<>();

        networkService.requestBarcodeInfo(envelope)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(envelope1 -> {

                    envelopeMutableLiveData.postValue(envelope1);
                    callback.onTasksLoaded(envelope1);

                }, throwable -> callback.onDataNotAvailable(throwable.getMessage()));

        return envelopeMutableLiveData;
    }

    @Override
    public LiveData<kazpost.kz.supermarketsc.data.network.model.regparcelrequest.Envelope> regParcel(
            String barcode, String shelfBarcode, String sender,
            String recipient, String recipientPhone, String marketIndex, RegParcelRequestCallback callback) {

        RegParcelRequestEnvelope envelope1 = new RegParcelRequestEnvelope();
        RegParcelRequestBody body = new RegParcelRequestBody();
        RegParcelData data = new RegParcelData();
        ParcelInfo parcelInfo = new ParcelInfo();

        parcelInfo.setaBarcode(barcode);
        parcelInfo.setbShelfBarcode(shelfBarcode);
        parcelInfo.setcSender(sender);
        parcelInfo.setdRecipient(recipient);
        parcelInfo.seteRecipientPhone(recipientPhone);
        parcelInfo.setfMarketIndex(marketIndex);

        data.setParcelInfo(parcelInfo);
        body.setRegParcelData(data);
        envelope1.setRegParcelRequestBody(body);


        callback.onDataLoading();


        final MutableLiveData<kazpost.kz.supermarketsc.data.network.model.regparcelrequest.Envelope>
                envelopeMutableLiveData = new MutableLiveData<>();
        networkService.regParcel(envelope1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(envelope2 -> {
                    envelopeMutableLiveData.postValue(envelope2);

                    callback.onTasksLoaded(envelope2);

                }, throwable -> callback.onDataNotAvailable(throwable.getMessage()));


        return envelopeMutableLiveData;
    }


}
