package kazpost.kz.supermarketsc.ui.scan;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

import kazpost.kz.supermarketsc.App;
import kazpost.kz.supermarketsc.data.SupermarketRepository;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.Envelope;
import kazpost.kz.supermarketsc.utils.AppExecutors;

/**
 * Created by root on 3/26/18.
 */

public class ScanViewModel extends ViewModel {

    @Inject
    SupermarketRepository supermarketRepository;

    public ScanViewModel() {
        App.getApp().getmDiComponent().inject(this);
    }

    public static final String TAG = ScanViewModel.class.getSimpleName();

    private MutableLiveData<String> mData = new MutableLiveData<>();

    private MutableLiveData<Status> state = new MutableLiveData<>();

    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public MutableLiveData<Status> getState() {
        return state;
    }

    MutableLiveData<Envelope> envelopeMutableLiveData = new MutableLiveData<>();

    private AppExecutors mExecutors;

    private MutableLiveData<String> row = new MutableLiveData<>();
    private MutableLiveData<String> cell = new MutableLiveData<>();
    private MutableLiveData<String> barcode = new MutableLiveData<>();

    LiveData<Envelope> envelopeMutableLiveData1 = new MutableLiveData<>();

    public void netwo(String s) {
        envelopeMutableLiveData1 = null;

        showProgress.postValue(true);


        Log.d(TAG, "netwo: " + showProgress.getValue());

        envelopeMutableLiveData1 = supermarketRepository.requestBarcodeInfo(barcode.getValue());


//        showProgress.postValue(false);


        Log.d(TAG, "netwo: " + showProgress.getValue());
        Log.d(TAG, "netwo: " + envelopeMutableLiveData1);

    }

    public MutableLiveData<Boolean> getProgressState(){
        return showProgress;
    }


//    public void netwo(String s) {
//        mExecutors = AppExecutors.getInstance();
//
//        mData.postValue(null);
//        envelopeMutableLiveData.postValue(null);
//
//        state.postValue(Status.LOADING);
//
//        RegParcelRequestEnvelope envelope = new RegParcelRequestEnvelope();
//        RegParcelRequestBody body = new RegParcelRequestBody();
//        ParcelInfo data = new ParcelInfo();
//        data.setBarcode(barcode.getValue());
//        body.setRegParcelRequestData(data);
//
//        envelope.setRegParcelRequestBody(body);
//
//        supermarketRepository.requestBarcodeInfo(envelope)
//
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(envelope1 -> {
//                    state.postValue(Status.ERROR);
//
//                    envelopeMutableLiveData.postValue(envelope1);
//
//                    Log.d(TAG, "requestBarcodeInfo: " + envelope1);
//                }, throwable -> {
//                    Log.d(TAG, "throwable: " + throwable.getMessage());
//                    state.postValue(Status.ERROR);
//
//                });
//
//
////        mExecutors.networkIO().execute(() -> {
////            try {
////
////                Thread.sleep(6000);
////
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        });
//    }


    public void setRow(String value) {
        if (value.length() >= 4 && value.length() <= 5) {
            row.postValue(value.substring(0, value.length() - 3));
            cell.postValue(value.substring(value.length() - 3));
        } else {
            row.postValue(null);
            cell.postValue(null);
        }
    }

    public MutableLiveData<String> getmData() {
        return mData;
    }

    public MutableLiveData<String> getRow() {
        return row;
    }

    public MutableLiveData<String> getCell() {
        return cell;
    }

    public MutableLiveData<String> getBarcode() {
        return barcode;
    }

    public void setBarcode(String value) {
        if (value.length() == 13) {
            barcode.postValue(value);
        } else {
            barcode.postValue(null);
        }
    }
}
