package gurungsijan.com.cleanapp_music.common.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.app.DemoMusicApplication;
import gurungsijan.com.cleanapp_music.common.di.components.AppComponent;
import gurungsijan.com.cleanapp_music.common.di.modules.ActivityModule;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Sijan Gurung on 09/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected FrameLayout container = null;
    protected Toolbar toolbar = null;
    protected Context context = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        container = (FrameLayout) findViewById(R.id.container);
        final ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation shake = AnimationUtils.loadAnimation(BaseActivity .this, R.anim.shake);
                view.startAnimation(shake);
                Toast.makeText(context, "Navigation Drawing not implemented!", Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     *Toolbar inflating
     *
     * @param toolbarRes the layout resource to be inflated in the toolbar_container
     * @return View the view reference to get the views in the inflated toolbar....
     */
    public View loadToolbarRes(@LayoutRes int toolbarRes){
        FrameLayout toolbarContainer = (FrameLayout) toolbar.findViewById(R.id.toolbar_container);
        return  getLayoutInflater().inflate(toolbarRes, toolbarContainer);
    }

    protected AppComponent getApplicationComponent(){
        return ((DemoMusicApplication) getApplication()).getAppComponent();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
