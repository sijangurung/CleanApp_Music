package gurungsijan.com.cleanapp_music.common.network;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class NetworkClient {

    private NetworkConfig config;

    public NetworkClient(NetworkConfig config) {
        this.config = config;
    }

    public <T> T getApi(Class<T> apiRest) {
        return config.getRetrofit().create(apiRest);
    }

    public <T> T execute(Call<T> call) throws Exception {
        Timber.i("Executing the call !");
        Response<T> response;

        try {
            response = call.execute();
        } catch (Exception e) {
            //Deal with the error here.. parsing it to normal string....
            throw new Exception(e);
        }

        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception();
        }
    }
}
