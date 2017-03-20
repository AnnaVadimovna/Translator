package av.translator.api;


import av.translator.api.entity.LangsResponse;
import av.translator.api.entity.TranslationResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TranslatorApi {
    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Call<TranslationResponse> translate (@Query("lang") String lang, @Query("key") String key, @Field("text") String text);

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/getLangs")
    Call<LangsResponse> getLangs (@Field("ui") String ui, @Query("key") String key);
}