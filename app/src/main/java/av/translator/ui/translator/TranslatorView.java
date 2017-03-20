package av.translator.ui.translator;


import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.Map;

public interface TranslatorView extends MvpView {
    void showInputLanguageDirection(String message);
    void showOutputLanguageDirection(String message);

    public void showToast(String message);
    public void drawOutput(List<String> message);
    public void startOutputLanguageSelector();
    public void startInputLanguageSelector();
    public void clearText();

    void writeToEdit(String value);
}
