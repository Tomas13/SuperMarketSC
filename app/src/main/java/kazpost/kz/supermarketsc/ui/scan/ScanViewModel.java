package kazpost.kz.supermarketsc.ui.scan;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

import kazpost.kz.supermarketsc.AppExecutors;
import kazpost.kz.supermarketsc.data.SupermarketRepository;


/**
 * Created by root on 3/26/18.
 */

public class ScanViewModel extends ViewModel {

    private SupermarketRepository supermarketRepository;


    @Inject
    public ScanViewModel(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }


    public static final String TAG = ScanViewModel.class.getSimpleName();

    private MutableLiveData<String> mData = new MutableLiveData<>();

    private MutableLiveData<Status> state = new MutableLiveData<>();

    public MutableLiveData<Status> getState() {
        return state;
    }


    private AppExecutors mExecutors;

    private MutableLiveData<String> row = new MutableLiveData<>();
    private MutableLiveData<String> cell = new MutableLiveData<>();
    private MutableLiveData<String> barcode = new MutableLiveData<>();

    public void netwo() {
        mExecutors = AppExecutors.getInstance();
        mData.postValue(null);

        state.postValue(Status.LOADING);

        mExecutors.networkIO().execute(() -> {
            try {


                Log.d(TAG, "netwo: " + mData.getValue());
                Thread.sleep(3000);
                mData.postValue("Data");

                supermarketRepository.savePostIndex("post index");
                state.postValue(Status.ERROR);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

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
