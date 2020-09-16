package zero.to.hero.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

import timber.log.Timber;
import zero.to.hero.R;
import zero.to.hero.databinding.RvPostcardIndividualBinding;
import zero.to.hero.pojo.PostcardPojo;

public class PostcardAdapter extends RecyclerView.Adapter<PostcardAdapter.ViewHolder> {

    private Context context;
    private List<PostcardPojo.Datum> postcardPojos;

    public PostcardAdapter(Context context, List<PostcardPojo.Datum> postcardPojos) {
        this.context = context;
        this.postcardPojos = postcardPojos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_postcard_individual, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        PostcardPojo.Datum pojo = postcardPojos.get(position);
        String fileExtension = pojo.getBackgroundUrl().substring(pojo.getBackgroundUrl().lastIndexOf(".") + 1);

        if (pojo.getType().equalsIgnoreCase("WP")) {
            Glide.with(context).load(pojo.getIcon()).circleCrop().into(holder.viewBinding.smallAvatar);
        } else {
            Glide.with(context).load(pojo.getCoIcon()).circleCrop().into(holder.viewBinding.smallAvatar);
            Glide.with(context).load(pojo.getIcon()).circleCrop().into(holder.viewBinding.largeAvatar);
        }

        holder.viewBinding.name.setText(pojo.getName());
        holder.viewBinding.fullName.setText(pojo.getName());
        holder.viewBinding.job.setText(pojo.getCoName());
        holder.viewBinding.dateJoined.setText(pojo.getUpdatedAt().getDate());
        holder.viewBinding.description.setText(pojo.getBody());
        holder.viewBinding.starCount.setText(String.valueOf(pojo.getLikeCount()));
        holder.viewBinding.syncCount.setText(String.valueOf(pojo.getShareCount()));
        holder.viewBinding.seenCount.setText(String.valueOf(pojo.getViewCount()));

        try {
            String headerColor = pojo.getColor().getBar().getTop();
            holder.viewBinding.header.setBackgroundColor(Color.parseColor(headerColor));
        } catch (Exception ignored) {
        }

        try {
            String bodyColor = pojo.getColor().getBar().getBottom();
            holder.viewBinding.descBody.setBackgroundColor(Color.parseColor(bodyColor));
        } catch (Exception ignored) {
        }

        if (pojo.getBackgroundType() == 2) {

            holder.viewBinding.videoView.setVisibility(View.VISIBLE);
            holder.viewBinding.bg.setVisibility(View.GONE);

            Uri uri = Uri.parse(pojo.getBackgroundUrl());

            holder.viewBinding.videoView.setVideoURI(uri);
            holder.viewBinding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    int width = mp.getVideoWidth();
                    int height = mp.getVideoHeight();

//                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height); // or set height to any fixed value you want
//                    holder.viewBinding.mediaLayout.setLayoutParams(lp);
                }
            });
            holder.viewBinding.videoView.start();
        } else {

            holder.viewBinding.videoView.setVisibility(View.GONE);
            holder.viewBinding.bg.setVisibility(View.VISIBLE);
            Glide.with(context).load(pojo.getBackgroundUrl()).into(holder.viewBinding.bg);

        }

    }

    public void updateMediaLayout(int width, int height, LinearLayout mediaLayout) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height); // or set height to any fixed value you want
        mediaLayout.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return postcardPojos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RvPostcardIndividualBinding viewBinding;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding = DataBindingUtil.bind(itemView);
        }
    }
}
