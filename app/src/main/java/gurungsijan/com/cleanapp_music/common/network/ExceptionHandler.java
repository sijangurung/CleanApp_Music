package gurungsijan.com.cleanapp_music.common.network;

import android.content.res.Resources;

import gurungsijan.com.cleanapp_music.R;

import java.net.UnknownHostException;

import timber.log.Timber;

import static gurungsijan.com.cleanapp_music.common.network.APIResponseCodes.API_RESPONSE_CUSTOM_ERROR;
import static gurungsijan.com.cleanapp_music.common.network.APIResponseCodes.API_RESPONSE_TECHNICAL_ERROR;
import static gurungsijan.com.cleanapp_music.common.network.APIResponseCodes.API_RESPONSE_UNAUTHORISED_FORBIDDEN;
import static gurungsijan.com.cleanapp_music.common.network.APIResponseCodes.API_RESPONSE_UNKNOWN_HOST;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class ExceptionHandler {

    public static int getErrorCode(Throwable throwable) {

        Timber.e("Processing Exception "+throwable);

        int errorcode;

        if (throwable.getCause() instanceof UnknownHostException) {
            errorcode = API_RESPONSE_UNKNOWN_HOST;
        } else {
            errorcode = API_RESPONSE_CUSTOM_ERROR;
        }
        return errorcode;
    }

    public static String getErrorString(int errorResponseCode, Resources resource) {

        String defaultErrorString;

        switch (errorResponseCode) {
            case API_RESPONSE_TECHNICAL_ERROR:
                defaultErrorString = resource.getString(R.string.technical_error_body);
                break;
            case API_RESPONSE_UNAUTHORISED_FORBIDDEN:
                defaultErrorString = resource.getString(R.string.forbidden_error_body);
                break;
            case API_RESPONSE_UNKNOWN_HOST:
                defaultErrorString = resource.getString(R.string.unknown_host_error_body);
                break;
            default:
                defaultErrorString = resource.getString(R.string.technical_error_body);
        }

        return defaultErrorString;
    }
}
