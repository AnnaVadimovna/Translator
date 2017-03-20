package av.translator.model;

import android.util.Log;

import java.sql.SQLException;
import java.util.List;

import av.translator.api.TranslatorApi;
import av.translator.api.entity.LangsResponse;
import av.translator.api.entity.TranslationResponse;
import av.translator.model.entity.TranslationEntity;
import av.translator.model.entity.TranslationEntityDb;
import retrofit2.Call;

public class TranslationModelImplementation implements TranslationModel {
    TranslatorApi translatorApi;
    DatabaseHelper.TranslationDAO dao;
    String apiKey;

    public TranslationModelImplementation(TranslatorApi translatorApi, DatabaseHelper.TranslationDAO dao, String apiKey) {
        this.translatorApi = translatorApi;
        this.apiKey = apiKey;
        this.dao = dao;
    }

    @Override
    public Call<TranslationResponse> translate(TranslationEntity entity) {
        dao.insertNew(entity);
        String languageKey = entity.getLanguageInputKey() + "-" + entity.getLanguageOutputKey();
        return translatorApi.translate(languageKey, apiKey, entity.getValue());
    }

    @Override
    public Call<LangsResponse> getLangs(String ui) {
        return translatorApi.getLangs(ui, apiKey);
    }

    @Override
    public List<TranslationEntity> getHistory() throws SQLException {
        return TranslationEntityDb.map(dao.getAllTranslations());
    }

    @Override
    public List<TranslationEntity> getFavorites() throws SQLException {
        return TranslationEntityDb.map(dao.getAllTranslations());
    }

    @Override
    public void updateItem(TranslationEntity entity) throws SQLException {
        TranslationEntityDb data = new TranslationEntityDb(entity);
        int t = dao.update(data);
        Log.i("DB", "update: " + t);
    }
}
