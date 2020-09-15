package zero.to.hero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import zero.to.hero.pojo.PostcardPojo;

/**
 * Created by azizimusa at 9/15/20 5:01 PM
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<PostcardPojo> call = Zero2HeroApp.getInstance().getRestApi().getPostcards(Utils.getToken());
        call.enqueue(new Callback<PostcardPojo>() {
            @Override
            public void onResponse(Call<PostcardPojo> call, Response<PostcardPojo> response) {

                Timber.e(new Gson().toJson(response.body()));

            }

            @Override
            public void onFailure(Call<PostcardPojo> call, Throwable t) {

                Timber.e(t.getMessage());

            }
        });

    }
}