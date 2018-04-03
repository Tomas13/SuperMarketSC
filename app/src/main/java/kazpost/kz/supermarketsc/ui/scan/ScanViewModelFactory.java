package kazpost.kz.supermarketsc.ui.scan;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;


public class ScanViewModelFactory implements ViewModelProvider.Factory {

    ScanViewModel scanViewModel;

    @Inject
    public ScanViewModelFactory(ScanViewModel scanViewModel) {
        this.scanViewModel = scanViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ScanViewModel.class)) {
            return (T) scanViewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
        //noinspection unchecked
//        return (T) new ScanViewModel(mRepository);
    }
}
