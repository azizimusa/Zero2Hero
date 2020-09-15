package zero.to.hero;

import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.appspector.sdk.AppSpector;

import java.io.IOException;
import java.security.GeneralSecurityException;

import okhttp3.OkHttpClient;
import timber.log.Timber;
import zero.to.hero.restApi.RestApi;

/**
 * Created by azizimusa at 9/15/20 5:12 PM
 */

public class Zero2HeroApp extends Application {

    private static Zero2HeroApp instance;

    public static synchronized Zero2HeroApp getInstance() {
        return instance;
    }

    private RestApi restApi;

    SharedPreferences sharedPreferences = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        ActiveAndroid.initialize(this);

        AppSpector
                .build(this)
                .addHttpMonitor()
                .addScreenshotMonitor()
                .addSQLMonitor()
                .addEnvironmentMonitor()
                .run("android_NzM3Zjk3ZDUtOWM5MS00MmY1LWEzMzQtZDM3ZWU5OGZjNjlk");
    }

    public RestApi getRestApi() {
        if (restApi == null) {
            restApi = RestApi.Factory.create();
        }

        return restApi;
    }

    public SharedPreferences getSharedPrefs() {
        if (sharedPreferences == null) {
            String masterKeyAlias = null;
            try {
                masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                sharedPreferences = EncryptedSharedPreferences.create(
                        "secretZero2HeroPrefs",
                        masterKeyAlias,
                        instance,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sharedPreferences;
    }
}
