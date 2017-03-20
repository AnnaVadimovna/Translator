package av.translator.ui.translator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import av.translator.R;

public class TranslatorViewHolder {
    private Button langToButton;
    private Button langSwitchButton;
    private Button getLangFromButton;
    private EditText translatorInput;
    private Button translateButton;
    private TextView translatorOutput;
    private ImageButton clearButton;
    private TextView yandex;

    public TranslatorViewHolder(View view) {
        langToButton = (Button) view.findViewById(R.id.lang_to);
        langSwitchButton = (Button) view.findViewById(R.id.lang_switch);
        getLangFromButton = (Button) view.findViewById(R.id.lang_from);
        translatorInput = (EditText) view.findViewById(R.id.translator_form);
        translateButton = (Button) view.findViewById(R.id.translate_button);
        translatorOutput = (TextView) view.findViewById(R.id.translation_output);
        clearButton = (ImageButton) view.findViewById(R.id.clear_image);
        yandex = (TextView) view.findViewById(R.id.yandex);
    }

    public Button getLangToButton() {
        return langToButton;
    }

    public Button getLangSwitchButton() {
        return langSwitchButton;
    }

    public Button getLangFromButton() {
        return getLangFromButton;
    }

    public EditText getTranslatorInput() {
        return translatorInput;
    }

    public Button getTranslateButton() {
        return translateButton;
    }

    public TextView getTranslatorOutput() {
        return translatorOutput;
    }

    public Button getGetLangFromButton() {
        return getLangFromButton;
    }

    public ImageButton getClearButton() {
        return clearButton;
    }

    public TextView getYandex() {
        return yandex;
    }
}
