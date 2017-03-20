package av.translator.model.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "Translations")
public class TranslationEntityDb {
    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField()
    private String value;

    @DatabaseField()
    private String languageInputValue;
    @DatabaseField()
    private String languageOutputValue;
    @DatabaseField()
    private String languageInputKey;
    @DatabaseField()
    private String languageOutputKey;

    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isFavorite;

    public TranslationEntityDb() {

    }

    public TranslationEntityDb(String value, String languageInputValue, String languageOutputValue, String languageInputKey, String languageOutputKey, boolean isFavorite) {
        this.value = value;
        this.languageInputValue = languageInputValue;
        this.languageOutputValue = languageOutputValue;
        this.languageInputKey = languageInputKey;
        this.languageOutputKey = languageOutputKey;
        this.isFavorite = isFavorite;
    }

    public TranslationEntityDb(TranslationEntity entity) {
        this.value = entity.getValue();
        this._id = entity.getId();
        this.languageInputValue = entity.getLanguageInputValue();
        this.languageOutputValue = entity.getLanguageOutputValue();
        this.languageInputKey = entity.getLanguageInputKey();
        this.languageOutputKey = entity.getLanguageOutputKey();
        this.isFavorite = entity.isFavorite();
    }

    public static List<TranslationEntity> map(List<TranslationEntityDb> allTranslations) {
        List<TranslationEntity> result = new ArrayList<>();
        for (TranslationEntityDb item : allTranslations) {
            result.add(new TranslationEntity(item._id, item.value, item.languageInputValue, item.languageOutputValue, item.languageInputKey, item.languageOutputKey, item.isFavorite));
        }
        return result;
    }
}
