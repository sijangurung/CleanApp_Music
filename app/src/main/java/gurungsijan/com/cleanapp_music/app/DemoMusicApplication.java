package gurungsijan.com.cleanapp_music.app;

import android.app.Application;

import gurungsijan.com.cleanapp_music.BuildConfig;
import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.common.di.components.AppComponent;
import gurungsijan.com.cleanapp_music.common.di.components.DaggerAppComponent;
import gurungsijan.com.cleanapp_music.common.di.modules.DataModule;
import gurungsijan.com.cleanapp_music.common.di.modules.ExecutorModule;
import gurungsijan.com.cleanapp_music.common.di.modules.InteractorModule;
import gurungsijan.com.cleanapp_music.common.di.modules.MainModule;
import gurungsijan.com.cleanapp_music.common.di.modules.NetworkModule;
import gurungsijan.com.cleanapp_music.common.di.modules.PresenterModule;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Sijan Gurung on 09/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class DemoMusicApplication extends Application {

    private static DemoMusicApplication INSTANCE;
    protected AppComponent component;

    public static DemoMusicApplication getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initCustomFont();
        initDaggerComponent();
        initTimberLogging();
    }

    protected void initCustomFont() {
        CalligraphyConfig config = new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();

        CalligraphyConfig.initDefault(config);
    }

    public AppComponent getAppComponent() {
        return component;
    }

    protected void initDaggerComponent() {
        component = DaggerAppComponent.builder()
                .mainModule(new MainModule(this))
                .executorModule(new ExecutorModule())
                .interactorModule(new InteractorModule())
                .presenterModule(new PresenterModule())
                .networkModule(new NetworkModule())
                .dataModule(new DataModule())
                .build();
        component.inject(this);
    }

    protected void initTimberLogging() {
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element)+ ':'+ element.getLineNumber();
                }
            });
        }
        else {
            Timber.plant(new ReleaseTree());
        }
    }
}
