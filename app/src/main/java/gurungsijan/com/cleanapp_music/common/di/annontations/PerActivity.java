package gurungsijan.com.cleanapp_music.common.di.annontations;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}