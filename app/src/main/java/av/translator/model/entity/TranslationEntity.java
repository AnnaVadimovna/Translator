package av.translator.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TranslationEntity implements Parcelable {
    public static final Creator<TranslationEntity> CREATOR = new Creator<TranslationEntity>() {
        @Override
        public TranslationEntity createFromParcel(Parcel source) {
            return new TranslationEntity(source);
        }

        @Override
        public TranslationEntity[] newArray(int size) {
            return new TranslationEntity[size];
        }
    };
    private int id;
    private String value;
    private String languageInputValue;
    private String languageOutputValue;
    private String languageInputKey;
    private String languageOutputKey;
    private boolean isFavorite;

    public TranslationEntity(int id, String value, String languageInputValue, String languageOutputValue, String languageInputKey, String languageOutputKey, boolean isFavorite) {
        this.value = value;
        this.id = id;
        this.languageInputValue = languageInputValue;
        this.languageOutputValue = languageOutputValue;
        this.languageInputKey = languageInputKey;
        this.languageOutputKey = languageOutputKey;
        this.isFavorite = isFavorite;
    }

    public TranslationEntity(String value, String languageInputValue, String languageOutputValue, String languageInputKey, String languageOutputKey, boolean isFavorite) {
        this(0, value, languageInputValue, languageOutputValue, languageInputKey, languageOutputKey, isFavorite);
    }

    protected TranslationEntity(Parcel in) {
        this.value = in.readString();
        this.languageInputValue = in.readString();
        this.languageOutputValue = in.readString();
        this.languageInputKey = in.readString();
        this.languageOutputKey = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLanguageInputValue() {
        return languageInputValue;
    }

    public void setLanguageInputValue(String languageInputValue) {
        this.languageInputValue = languageInputValue;
    }

    public String getLanguageOutputValue() {
        return languageOutputValue;
    }

    public void setLanguageOutputValue(String languageOutputValue) {
        this.languageOutputValue = languageOutputValue;
    }

    public String getLanguageInputKey() {
        return languageInputKey;
    }

    public void setLanguageInputKey(String languageInputKey) {
        this.languageInputKey = languageInputKey;
    }

    public String getLanguageOutputKey() {
        return languageOutputKey;
    }

    public void setLanguageOutputKey(String languageOutputKey) {
        this.languageOutputKey = languageOutputKey;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
        dest.writeString(this.languageInputValue);
        dest.writeString(this.languageOutputValue);
        dest.writeString(this.languageInputKey);
        dest.writeString(this.languageOutputKey);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
