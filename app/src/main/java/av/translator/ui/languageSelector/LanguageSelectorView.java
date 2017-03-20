package av.translator.ui.languageSelector;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.Map;

public interface LanguageSelectorView extends MvpView {
    void drawContent(List<LanguageViewItem> items);

    void drawError(String message);

    void finish(LanguageViewItem item);
}