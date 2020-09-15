package zero.to.hero.restApi;

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
}
