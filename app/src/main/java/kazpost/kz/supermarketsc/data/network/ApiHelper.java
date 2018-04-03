package kazpost.kz.supermarketsc.data.network;


import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import kazpost.kz.supermarketsc.data.network.model.Response;
import kazpost.kz.supermarketsc.data.network.model.TechIndex;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestEnvelope;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.Envelope;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelRequestEnvelope;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

public interface ApiHelper {

    Observable<List<TechIndex>> getTechIndexList();

    Observable<Response> sendData(Map<String, String> stringMap);

    Call<Response> sendCallData(Map<String, String> stringMap);


    Observable<Envelope> requestBarcodeInfo(BarcodeInfoRequestEnvelope envelope);


    LiveData<Envelope> requestBarcodeInfo(String barcode);


    LiveData<kazpost.kz.supermarketsc.data.network.model.regparcelrequest.Envelope> regParcel(RegParcelRequestEnvelope envelope);

    }
