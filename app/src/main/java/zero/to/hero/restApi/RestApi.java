package zero.to.hero.restApi;

import android.media.Image;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import zero.to.hero.pojo.PostcardPojo;

/**
 * Created by azizimusa at 9/15/20 5:02 PM
 */

public interface RestApi {

    String AUTH = "Authorization";
    String BASE_URL = "https://dev.codezero.app/api/";

    @GET("v2/posts")
    Call<PostcardPojo> getPostcards(@Header(AUTH) String token);

    class Factory {
        public static RestApi create(){

            //    this is for enabling debug through chrome browser
            //    type chrome://inspect at chrome to start

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(RestApi.class);
        }

    }

//    id	Int64	PostCard ID
//    card_id	Int64	Card ID
//    user_id	Int64	User ID
//    timestamp	Int64	Date in miliseconds
//    type	String	Card Type. <"WP"|"BP">
//    name	String	Name in the brandcard
//    icon	String	Card icon url
//    description	String	Designation, caption etc in the brandcard
//    color	String	RGB in HEX
//    email	String	Optional
//    background_type	Int	1 = Image, 2 = Video
//    background_url	String
//    background_width	Int	"Since not all media able to get the width, server might returned this as NULL.
//    I suggest client side detects the width or just assume vertical :D"
//    background_height	Int	"Since not all media able to get the height, server might returned this as NULL.
//    I suggest client side detects the width or just assume vertical :D"
//    co_name	String	Company name    (shown if type is BP)
//    co_icon	String	Company icon url  (shown if type is BP)
//    liked	Boolean
//    like_count	Int
//    viewed	Boolean
//    view_count	Int
//    shared	Boolean
//    share_count	Int
//    comments	[COMMENT]
//    body	String	Post body
}
