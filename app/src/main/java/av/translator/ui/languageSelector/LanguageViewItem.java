package av.translator.ui.languageSelector;

import android.os.Parcel;
import android.os.Parcelable;

public class LanguageViewItem implements Parcelable {
    private String display;
    private String key;

    public LanguageViewItem(String display, String key) {
        this.display = display;
        this.key = key;
    }

    public String getDisplay() {
        return display;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.display);
        dest.writeString(this.key);
    }

    protected LanguageViewItem(Parcel in) {
        this.display = in.readString();
        this.key = in.readString();
    }

    public static final Creator<LanguageViewItem> CREATOR = new Creator<LanguageViewItem>() {
        @Override
        public LanguageViewItem createFromParcel(Parcel source) {
            return new LanguageViewItem(source);
        }

        @Override
        public LanguageViewItem[] newArray(int size) {
            return new LanguageViewItem[size];
        }
    };
}