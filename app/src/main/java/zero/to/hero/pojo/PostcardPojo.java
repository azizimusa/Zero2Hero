package zero.to.hero.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by azizimusa at 9/15/20 5:01 PM
 */

public class PostcardPojo {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("card_id")
        @Expose
        private Integer cardId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("body")
        @Expose
        private String body;
        @SerializedName("color")
        @Expose
        private Color color;
        @SerializedName("background_type")
        @Expose
        private Integer backgroundType;
        @SerializedName("background_url")
        @Expose
        private String backgroundUrl;
        @SerializedName("co_name")
        @Expose
        private String coName;
        @SerializedName("co_icon")
        @Expose
        private String coIcon;
        @SerializedName("liked")
        @Expose
        private Boolean liked;
        @SerializedName("like_count")
        @Expose
        private Integer likeCount;
        @SerializedName("viewed")
        @Expose
        private Boolean viewed;
        @SerializedName("view_count")
        @Expose
        private Integer viewCount;
        @SerializedName("shared")
        @Expose
        private Boolean shared;
        @SerializedName("share_count")
        @Expose
        private Integer shareCount;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("updated_at")
        @Expose
        private UpdatedAt updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getCardId() {
            return cardId;
        }

        public void setCardId(Integer cardId) {
            this.cardId = cardId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Integer getBackgroundType() {
            return backgroundType;
        }

        public void setBackgroundType(Integer backgroundType) {
            this.backgroundType = backgroundType;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getCoName() {
            return coName;
        }

        public void setCoName(String coName) {
            this.coName = coName;
        }

        public String getCoIcon() {
            return coIcon;
        }

        public void setCoIcon(String coIcon) {
            this.coIcon = coIcon;
        }

        public Boolean getLiked() {
            return liked;
        }

        public void setLiked(Boolean liked) {
            this.liked = liked;
        }

        public Integer getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Integer likeCount) {
            this.likeCount = likeCount;
        }

        public Boolean getViewed() {
            return viewed;
        }

        public void setViewed(Boolean viewed) {
            this.viewed = viewed;
        }

        public Integer getViewCount() {
            return viewCount;
        }

        public void setViewCount(Integer viewCount) {
            this.viewCount = viewCount;
        }

        public Boolean getShared() {
            return shared;
        }

        public void setShared(Boolean shared) {
            this.shared = shared;
        }

        public Integer getShareCount() {
            return shareCount;
        }

        public void setShareCount(Integer shareCount) {
            this.shareCount = shareCount;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public UpdatedAt getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(UpdatedAt updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    public class Bar {

        @SerializedName("bottom")
        @Expose
        private String bottom;
        @SerializedName("top")
        @Expose
        private String top;

        public String getBottom() {
            return bottom;
        }

        public void setBottom(String bottom) {
            this.bottom = bottom;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

    }

    public class Color {

        @SerializedName("bar")
        @Expose
        private Bar bar;
        @SerializedName("font")
        @Expose
        private Font font;

        public Bar getBar() {
            return bar;
        }

        public void setBar(Bar bar) {
            this.bar = bar;
        }

        public Font getFont() {
            return font;
        }

        public void setFont(Font font) {
            this.font = font;
        }

    }

    public class Font {

        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("text")
        @Expose
        private String text;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public class UpdatedAt {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("timezone_type")
        @Expose
        private Integer timezoneType;
        @SerializedName("timezone")
        @Expose
        private String timezone;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getTimezoneType() {
            return timezoneType;
        }

        public void setTimezoneType(Integer timezoneType) {
            this.timezoneType = timezoneType;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

    }

}
