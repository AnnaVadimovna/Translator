package av.translator;

import android.app.Application;

import av.translator.api.TranslatorApi;
import av.translator.model.DatabaseHelper;
import av.translator.model.TranslationModel;
import av.translator.model.TranslationModelImplementation;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslatorApplication extends Application {
    public static TranslatorApi translatorApi;
    public static DatabaseHelper db;
    public static TranslationModel model;
    public static String translatorApiKey = "trnsl.1.1.20170412T203603Z.07bf83e593b5aebd.5c3b0dfed3a1061fa9da0a32713f7ab47c93896e";

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        translatorApi = retrofit.create(TranslatorApi.class);
        db = new DatabaseHelper(this);

        model = new TranslationModelImplementation(translatorApi, db.getDao(), translatorApiKey);
    }
}