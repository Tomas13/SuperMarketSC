package kazpost.kz.supermarketsc.ui.scan;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import kazpost.kz.supermarketsc.data.SupermarketRepository;

public class ScanViewModelFactory implements ViewModelProvider.Factory {

    private ScanViewModel scanViewModel;

    @Inject
    public ScanViewModelFactory(ScanViewModel scanViewModel) {

        this.scanViewModel = scanViewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ScanViewModel.class)) {
            return (T) scanViewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
        //noinspection unchecked
//        return (T) new ScanViewModel(mRepository);
    }
}
