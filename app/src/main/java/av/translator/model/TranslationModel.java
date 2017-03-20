package av.translator.model;

import java.sql.SQLException;
import java.util.List;

import av.translator.api.entity.LangsResponse;
import av.translator.api.entity.TranslationResponse;
import av.translator.model.entity.TranslationEntity;
import retrofit2.Call;

public interface TranslationModel {
    public Call<TranslationResponse> translate(TranslationEntity translationEntity);

    public Call<LangsResponse> getLangs(String ui);

    public List<TranslationEntity> getHistory() throws SQLException;

    public List<TranslationEntity> getFavorites() throws SQLException;

    void updateItem(TranslationEntity entity) throws SQLException;
}
