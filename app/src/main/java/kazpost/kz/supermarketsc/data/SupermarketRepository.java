package kazpost.kz.supermarketsc.data;

import android.content.Context;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import kazpost.kz.supermarketsc.data.network.ApiHelper;
import kazpost.kz.supermarketsc.data.network.model.Response;
import kazpost.kz.supermarketsc.data.network.model.TechIndex;
import kazpost.kz.supermarketsc.data.prefs.PreferencesHelper;
import kazpost.kz.supermarketsc.di.ActivityContext;
import kazpost.kz.supermarketsc.di.ApplicationContext;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by root on 3/27/18.
 */

@Singleton
public class SupermarketRepository implements ApiHelper, PreferencesHelper, Repo {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public SupermarketRepository(@ApplicationContext Context context, PreferencesHelper mPreferencesHelper, ApiHelper mApiHelper) {
        this.mContext = context;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mApiHelper = mApiHelper;
    }


    @Override
    public Observable<List<TechIndex>> getTechIndexList() {
        return mApiHelper.getTechIndexList();
    }

    @Override
    public Observable<Response> sendData(Map<String, String> stringMap) {
        return mApiHelper.sendData(stringMap);
    }

    @Override
    public Call<Response> sendCallData(Map<String, String> stringMap) {
        return mApiHelper.sendCallData(stringMap);
    }

    @Override
    public void savePostIndex(String postIndex) {
        mPreferencesHelper.savePostIndex(postIndex);
    }

    @Override
    public void saveSpinnerPosition(int position) {
        mPreferencesHelper.saveSpinnerPosition(position);
    }

    @Override
    public int getSpinnerPosition() {
        return mPreferencesHelper.getSpinnerPosition();
    }

    @Override
    public String getPostIndex() {
        return mPreferencesHelper.getPostIndex();
    }
}
