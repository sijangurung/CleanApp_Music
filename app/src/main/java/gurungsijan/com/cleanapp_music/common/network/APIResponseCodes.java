package gurungsijan.com.cleanapp_music.common.network;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public interface APIResponseCodes {

    public static final int API_RESPONSE_SUCCESS = 200;
    public static final int API_RESPONSE_NOT_MODIFIED = 304;
    public static final int API_RESPONSE_UNAUTHORISED_BAD_REQUEST = 400;
    public static final int API_RESPONSE_UNAUTHORISED = 401;
    public static final int API_RESPONSE_UNAUTHORISED_FORBIDDEN = 403;
    public static final int API_RESPONSE_TECHNICAL_ERROR = 500;
    public static final int API_RESPONSE_UNKNOWN_HOST = 700;
    public static final int API_RESPONSE_CUSTOM_ERROR = 999;
}