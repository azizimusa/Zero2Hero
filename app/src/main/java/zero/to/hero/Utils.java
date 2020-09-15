package zero.to.hero;

/**
 * Created by azizimusa at 9/15/20 5:19 PM
 */

public class Utils {

    public static void setToken(String value) {
        Zero2HeroApp.getInstance().getSharedPrefs().edit().putString(MyConstants.TOKEN, value).apply();
    }

    public static String getToken() {
        return "Bearer " + Zero2HeroApp.getInstance().getSharedPrefs().getString(MyConstants.TOKEN, MyConstants.TEMP_TOKEN);
    }

}
