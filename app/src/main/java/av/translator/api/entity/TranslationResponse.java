package av.translator.api.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TranslationResponse {
    @SerializedName("code")
    long code;
    @SerializedName("lang")
    String lang;
    @SerializedName("text")
    List<String> text;

    public long getCode() {
        return code;
    }

    public String getLang() {
        return lang;
    }

    public List<String> getText() {
        return text;
    }
}
