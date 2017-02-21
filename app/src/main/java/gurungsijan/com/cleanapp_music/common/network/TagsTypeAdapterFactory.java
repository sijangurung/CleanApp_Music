package gurungsijan.com.cleanapp_music.common.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class TagsTypeAdapterFactory implements TypeAdapterFactory {
    public final String TAG = "AdapterFactory";

    @Override
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> tTypeToken) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, tTypeToken);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            @Override
            public T read(JsonReader jsonReader) throws IOException {
                JsonElement jsonElement = elementAdapter.read(jsonReader);
                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                }
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                }
                return delegate.fromJsonTree(jsonElement);
            }

            @Override
            public void write(JsonWriter jsonWriter, T t) throws IOException {
                delegate.write(jsonWriter, t);
            }
        }.nullSafe();
    }
}

