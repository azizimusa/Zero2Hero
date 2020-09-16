package zero.to.hero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import zero.to.hero.adapter.PostcardAdapter;
import zero.to.hero.databinding.ActivityMainBinding;
import zero.to.hero.pojo.PostcardPojo;

/**
 * Created by azizimusa at 9/15/20 5:01 PM
 */

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding viewBinding;
    private PostcardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewBinding.setVm(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView tt = getSupportActionBar().getCustomView().findViewById(R.id.title);
        tt.setText("MyPostcards");

        Call<PostcardPojo> call = Zero2HeroApp.getInstance().getRestApi().getPostcards(Utils.getToken());
        call.enqueue(new Callback<PostcardPojo>() {
            @Override
            public void onResponse(Call<PostcardPojo> call, Response<PostcardPojo> response) {

                Timber.e(new Gson().toJson(response.body()));
                initRV(response.body().getData());

            }

            @Override
            public void onFailure(Call<PostcardPojo> call, Throwable t) {

                Timber.e(t.getMessage());

            }
        });

    }

    private void initRV(List<PostcardPojo.Datum> response) {
        adapter = new PostcardAdapter(this, response);
        viewBinding.rv.setAdapter(adapter);
        viewBinding.rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Span 1", Toast.LENGTH_LONG).show();
                viewBinding.rv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                return true;
            case R.id.item2:
                Toast.makeText(this, "Span 2", Toast.LENGTH_LONG).show();
                viewBinding.rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}