package kazpost.kz.supermarketsc.data.network.model.barcodeinforequest;

public interface BarcodeInfoRequestCallback {

        void onTasksLoaded(Envelope envelope);

        void onDataNotAvailable(String errorMsg);

        void onDataLoading();
    }