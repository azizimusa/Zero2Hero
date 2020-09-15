package zero.to.hero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

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

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView tt = getSupportActionBar().getCustomView().findViewById(R.id.title);
        tt.setText("MyPostcards");

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.setting) {
            Timber.e("setting opened");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}