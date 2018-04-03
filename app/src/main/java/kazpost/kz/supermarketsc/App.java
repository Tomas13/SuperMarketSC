package kazpost.kz.supermarketsc;

import android.app.Application;

import com.facebook.stetho.Stetho;

import kazpost.kz.supermarketsc.di.DaggerDiComponent;
import kazpost.kz.supermarketsc.di.DiComponent;
import kazpost.kz.supermarketsc.di.DiModule;

/**
 * Created by root on 3/26/18.
 */

public class App extends Application {
    private DiComponent mDiComponent;

    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        mDiComponent = DaggerDiComponent.builder()
                .diModule(new DiModule(this)).build();

        mDiComponent.inject(this);

        Stetho.initializeWithDefaults(this);
    }

    public DiComponent getmDiComponent() {
        return mDiComponent;
    }
}
