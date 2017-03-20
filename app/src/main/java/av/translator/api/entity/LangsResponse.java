package av.translator.api.entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class LangsResponse {
    @SerializedName("dirs")
    List<String> dirs;
    @SerializedName("langs")
    Map<String, String> langs;

    public List<String> getDirs() {
        return dirs;
    }

    public Map<String, String> getLangs() {
        return langs;
    }

}
