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
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

        final PostcardPojo.Datum pojo = postcardPojos.get(position);

        if (pojo.getType().equalsIgnoreCase("WP")) {
            holder.viewBinding.name.setText(pojo.getName());
            Glide.with(context).load(pojo.getIcon()).circleCrop().into(holder.viewBinding.smallAvatar);

            String headerColor = pojo.getColor().getBar().getTop();
            holder.viewBinding.header.setBackgroundColor(Color.parseColor(headerColor));
        } else {
            holder.viewBinding.name.setText(pojo.getCoName());
            Glide.with(context).load(pojo.getCoIcon()).circleCrop().into(holder.viewBinding.smallAvatar);

            String bodyColor = pojo.getColor().getBar().getBottom();
            holder.viewBinding.header.setBackgroundColor(Color.parseColor(bodyColor));
        }

        Glide.with(context).load(pojo.getIcon()).circleCrop().into(holder.viewBinding.largeAvatar);

        holder.viewBinding.mediaLayout.setBackgroundColor(Color.parseColor(pojo.getColor().getBar().getBottom()));
        holder.viewBinding.descBody.setBackgroundColor(Color.parseColor(pojo.getColor().getBar().getBottom()));

        holder.viewBinding.job.setText(pojo.getDescription());
        holder.viewBinding.fullName.setText(pojo.getName());
        holder.viewBinding.dateJoined.setText(getDate(Long.parseLong(pojo.getTimestamp())));
        holder.viewBinding.description.setText(removeHTMLTags(pojo.getBody()));
        holder.viewBinding.starCount.setText(String.valueOf(pojo.getLikeCount()));
        holder.viewBinding.syncCount.setText(String.valueOf(pojo.getShareCount()));
        holder.viewBinding.seenCount.setText(String.valueOf(pojo.getViewCount()));

        if (pojo.getBackgroundType() == 2) {

            holder.viewBinding.videoView.setVisibility(View.VISIBLE);
            holder.viewBinding.bg.setVisibility(View.GONE);

            Uri uri = Uri.parse(pojo.getBackgroundUrl());

            holder.viewBinding.videoView.setVideoURI(uri);
            holder.viewBinding.mediaLayout.setBackgroundColor(Color.BLACK);
            holder.viewBinding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    int width = mp.getVideoWidth();
                    int height = mp.getVideoHeight();

                    Timber.e("video " + pojo.getDescription() + " " + width + " " + height);

                    if (height < 500) {
                        updateMediaLayout(height, holder.viewBinding.mediaLayout);
                    }

                }
            });
            holder.viewBinding.videoView.start();
        } else {

            holder.viewBinding.videoView.setVisibility(View.GONE);
            holder.viewBinding.bg.setVisibility(View.VISIBLE);

            final RequestBuilder<Drawable> xx = Glide.with(context).load(pojo.getBackgroundUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            xx.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                    Bitmap x = bitmapDrawable.getBitmap();

                    int width = x.getWidth();
                    int height = x.getHeight();

                    Timber.e("image " + pojo.getDescription() + " " + width + " " + height);
                    xx.into(holder.viewBinding.bg);

                }
            });

            holder.viewBinding.bg.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    holder.viewBinding.bg.getViewTreeObserver().removeOnPreDrawListener(this);
                    int width = holder.viewBinding.bg.getMeasuredWidth();
                    int height = holder.viewBinding.bg.getMeasuredHeight();

                    Timber.e("imageview " + pojo.getDescription() + " " + width + " " + height);

//                    updateMediaLayout(height, holder.viewBinding.mediaLayout);

                    return true;
                }
            });

//            Glide.with(context)
//                    .load(pojo.getBackgroundUrl())
//                    .diskCacheStrategy(DiskCacheStrategy.DATA)
//                    .into(new SimpleTarget<Drawable>() {
//                @Override
//                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                    BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
//                    Bitmap x = bitmapDrawable.getBitmap();
//
//                    int width = x.getWidth();
//                    int height = x.getHeight();
//
//                    Timber.e("image " + pojo.getDescription() + " " + width + " " + height);
////                    updateMediaLayout(x.getHeight(), holder.viewBinding.mediaLayout);
//                    Glide.with(context).load(x).into(holder.viewBinding.bg);
//                    updateMediaLayout(LinearLayout.MarginLayoutParams.WRAP_CONTENT, holder.viewBinding.mediaLayout);
//
//
//                }
//            });

        }

    }

    private String removeHTMLTags(String value) {
        return value.replaceAll("\\<.*?\\>", "");
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd MMMM yyyy", cal).toString();
        return date;
    }

    public void updateMediaLayout(int height, LinearLayout mediaLayout) {
        Timber.e("wwwoi");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.MarginLayoutParams.MATCH_PARENT, height); // or set height to any fixed value you want
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
