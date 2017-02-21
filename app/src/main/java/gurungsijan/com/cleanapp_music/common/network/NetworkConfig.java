package gurungsijan.com.cleanapp_music.common.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gurungsijan.com.cleanapp_music.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class NetworkConfig {
    private Retrofit retrofit;

    public NetworkConfig(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static class Builder {
        private static final String BASE_URL = BuildConfig.API_URL;
        private Interceptor interceptor;
        private boolean debug = false;
        private String baseURL = BASE_URL;

        private Retrofit retrofit;

        public Builder() {
        }

        public Builder debug() {
            this.debug = false;
            return this;
        }

        public Builder baseURL(String baseURL) {
            this.baseURL = baseURL;
            return this;
        }

        public NetworkConfig build() {
            if (retrofit == null) {
                retrofit = createRetrofit(baseURL);
            }

            return new NetworkConfig(retrofit);
        }

        private Retrofit createRetrofit(String baseURL) {
            Timber.v("Making new retrofit instance with BaseUrl: "+baseURL);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new TagsTypeAdapterFactory()) // This is the important line ;)
                    .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                    .create();

            //The variable from Build can be used to show the logs or not!!! :)
            Interceptor inteceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(inteceptor)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

            return new Retrofit.Builder().baseUrl(baseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }
}