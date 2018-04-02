package kazpost.kz.supermarketsc;

import android.app.Application;

import com.facebook.stetho.Stetho;

import kazpost.kz.supermarketsc.di.component.ApplicationComponent;
import kazpost.kz.supermarketsc.di.component.DaggerApplicationComponent;
import kazpost.kz.supermarketsc.di.module.ApplicationModule;

/**
 * Created by root on 3/26/18.
 */

public class App extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);


        Stetho.initializeWithDefaults(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
