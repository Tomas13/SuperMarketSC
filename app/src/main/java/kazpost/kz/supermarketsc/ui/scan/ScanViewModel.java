package kazpost.kz.supermarketsc.ui.scan;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

import kazpost.kz.supermarketsc.App;
import kazpost.kz.supermarketsc.data.SupermarketRepository;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.BarcodeInfoRequestCallback;
import kazpost.kz.supermarketsc.data.network.model.barcodeinforequest.Envelope;
import kazpost.kz.supermarketsc.data.network.model.regparcelrequest.RegParcelRequestCallback;
import kazpost.kz.supermarketsc.utils.ToastMessage;
import timber.log.Timber;

/**
 * Created by root on 3/26/18.
 */

public class ScanViewModel extends ViewModel {

    public static final String TAG = ScanViewModel.class.getSimpleName();

    @Inject
    SupermarketRepository supermarketRepository;

    public ScanViewModel() {
        App.getApp().getmDiComponent().inject(this);
    }

    private final ToastMessage mToast = new ToastMessage();
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public ToastMessage getmToast() {
        return mToast;
    }

    private void showToast(String message) {
        mToast.postValue(message);
    }

    /*    private MutableLiveData<String> row = new MutableLiveData<>();
    private MutableLiveData<String> cell = new MutableLiveData<>();
    private MutableLiveData<String> barcode = new MutableLiveData<>();*/


    public void netwo(String barcode, String shelfBarcode) {


        /*        envelopeMutableLiveData = */
        supermarketRepository.requestBarcodeInfo(barcode, new BarcodeInfoRequestCallback() {
            @Override
            public void onTasksLoaded(Envelope envelope) {
                showProgress.postValue(false);

                String marketIndex = supermarketRepository.getPostIndex();

                regParcelRequest(barcode, shelfBarcode,
                        envelope.getBody().getSavePaymentSrvResponse().getSndr(),
                        envelope.getBody().getSavePaymentSrvResponse().getRcpn(),
                        envelope.getBody().getSavePaymentSrvResponse().getRcpnPhone(),
                        marketIndex);
            }

            @Override
            public void onDataNotAvailable(String errorMsg) {
                showProgress.postValue(false);
                showToast(errorMsg);
            }

            @Override
            public void onDataLoading() {
                showProgress.postValue(true);
            }
        });

    }

    private void regParcelRequest(String barcode, String shelfBarcode, String sender,
                                  String recipient, String recipientPhone, String marketIndex) {


        supermarketRepository.regParcel(barcode, shelfBarcode, sender, recipient, recipientPhone, marketIndex,
                new RegParcelRequestCallback() {
                    @Override
                    public void onTasksLoaded(kazpost.kz.supermarketsc.data.network.model.regparcelrequest.Envelope envelope) {
                        showProgress.postValue(false);
                        Timber.i("onTaskLoaded: %s", showProgress.getValue());

                        showToast(envelope.getBody().getRegParcelResponse().getResponseInfo().getResponseText());
                    }

                    @Override
                    public void onDataNotAvailable(String errorMsg) {
                        showProgress.postValue(false);
                    }

                    @Override
                    public void onDataLoading() {
                        showProgress.postValue(true);
                    }
                });
    }

    public MutableLiveData<Boolean> getProgressState() {
        return showProgress;
    }

    public void saveMarketIndex(String marketIndex) {
        supermarketRepository.savePostIndex(marketIndex);

        showToast("Индекс супермаркета успешно сохранен");
    }

    public boolean isMarketIndexExist() {
        return !supermarketRepository.getPostIndex().equals("Не выбрано");
    }

    public String getMarketIndex() {
        return supermarketRepository.getPostIndex();
    }


/*    public void setRow(String value) {
        if (value.length() >= 4 && value.length() <= 5) {
            row.postValue(value.substring(0, value.length() - 3));
            cell.postValue(value.substring(value.length() - 3));
        } else {
            row.postValue(null);
            cell.postValue(null);
        }
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
    }*/
}
