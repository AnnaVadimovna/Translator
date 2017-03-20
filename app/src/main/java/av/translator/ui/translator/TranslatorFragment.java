package av.translator.ui.translator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import av.translator.R;
import av.translator.TranslatorApplication;
import av.translator.model.entity.TranslationEntity;
import av.translator.ui.languageSelector.LanguageSelectorActivity;
import av.translator.ui.languageSelector.LanguageViewItem;

import static android.app.Activity.RESULT_OK;

public class TranslatorFragment extends MvpAppCompatFragment implements TranslatorView {

    @InjectPresenter
    TranslatorPresenter presenter;
    TranslatorViewHolder viewHolder;

    @ProvidePresenter
    TranslatorPresenter providePresenter() {
        return new TranslatorPresenter(TranslatorApplication.model);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("translate")) {
            TranslationEntity entity = arguments.getParcelable("translate");
            if (entity != null) {
                presenter.translate(entity);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewHolder = new TranslatorViewHolder(view);

        viewHolder.getTranslateButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fillForm(viewHolder.getTranslatorInput().getText().toString());
                presenter.translateAction();
            }
        });

        viewHolder.getLangFromButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectInputLangAction();
            }

        });

        viewHolder.getLangToButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectOutputLangAction();
            }

        });

        viewHolder.getLangSwitchButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switchLanguages();
            }

        });

        viewHolder.getClearButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });

        viewHolder.getYandex().setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewHolder.getTranslateButton().setOnClickListener(null);
    }

    @Override
    public void showInputLanguageDirection(String message) {
        viewHolder.getLangFromButton().setText(message);
    }

    @Override
    public void showOutputLanguageDirection(String message) {
        viewHolder.getLangToButton().setText(message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void drawOutput(List<String> message) {
        viewHolder.getTranslatorOutput().setText(message.get(0));
    }

    @Override
    public void startInputLanguageSelector() {
        Intent intent = new Intent(getContext(), LanguageSelectorActivity.class);
        startActivityForResult(intent, 700);
    }

    @Override
    public void startOutputLanguageSelector() {
        Intent intent = new Intent(getContext(), LanguageSelectorActivity.class);
        startActivityForResult(intent, 750);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 700 && resultCode == RESULT_OK) {
            LanguageViewItem item = data.getParcelableExtra("result");
            presenter.setInputLanguage(item);
        }
        if (requestCode == 750 && resultCode == RESULT_OK) {
            LanguageViewItem item = data.getParcelableExtra("result");

            presenter.setOutputLanguage(item);
        }
    }

    @Override
    public void clearText() {
        viewHolder.getTranslatorInput().setText("");
    }

    @Override
    public void writeToEdit(String value) {
        viewHolder.getTranslatorInput().setText(value);
    }
}