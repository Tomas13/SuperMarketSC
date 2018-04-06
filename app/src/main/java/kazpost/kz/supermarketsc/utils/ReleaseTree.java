package kazpost.kz.supermarketsc.utils;

import android.util.Log;

import timber.log.Timber;

public class ReleaseTree extends Timber.Tree {

    @Override
    protected boolean isLoggable(String tag, int priority) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO){
            return false;
        }

        //only log WARN, ERROR and WTF
        return true;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

        if(isLoggable(priority)){

        }
    }
}
