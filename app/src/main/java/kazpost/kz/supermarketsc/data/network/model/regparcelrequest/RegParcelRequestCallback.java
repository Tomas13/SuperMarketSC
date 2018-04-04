package kazpost.kz.supermarketsc.data.network.model.regparcelrequest;

public interface RegParcelRequestCallback {

        void onTasksLoaded(Envelope envelope);

        void onDataNotAvailable(String errorMsg);

        void onDataLoading();
    }