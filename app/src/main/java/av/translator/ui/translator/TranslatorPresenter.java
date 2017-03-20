package av.translator.ui.translator;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import av.translator.api.entity.TranslationResponse;
import av.translator.model.TranslationModel;
import av.translator.model.entity.TranslationEntity;
import av.translator.ui.languageSelector.LanguageViewItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class TranslatorPresenter extends MvpPresenter<TranslatorView> {
    TranslationModel translationModel;

    private String input;
    private LanguageViewItem inputLanguage = new LanguageViewItem("Английский", "en");
    private LanguageViewItem outputLanguage = new LanguageViewItem("Русский", "ru");

    public TranslatorPresenter(TranslationModel translationModel) {
        this.translationModel = translationModel;
    }

    //TODO: не выделила entity!
    //TODO:Заменить параметр en
    public void selectInputLangAction() {
        getViewState().startInputLanguageSelector();
    }

    public void selectOutputLangAction() {
        getViewState().startOutputLanguageSelector();
    }

    public void translateAction() {
        TranslationEntity translationEntity = new TranslationEntity(input, inputLanguage.getDisplay(), outputLanguage.getDisplay(), inputLanguage.getKey(), outputLanguage.getKey(), false);
        translate(translationEntity);
    }

    private String languageKey() {
        return inputLanguage.getKey() + "-" + outputLanguage.getKey();
    }

    private String languageValue() {
        return inputLanguage.getDisplay() + "->" + outputLanguage.getDisplay();
    }

    public void fillForm(String text) {
        input = text;
    }

    public void setInputLanguage(LanguageViewItem inputLanguage) {
        this.inputLanguage = inputLanguage;
        getViewState().showInputLanguageDirection(inputLanguage.getDisplay());
    }

    public void setOutputLanguage(LanguageViewItem outputLanguage) {
        this.outputLanguage = outputLanguage;
        getViewState().showOutputLanguageDirection(outputLanguage.getDisplay());
    }

    public void switchLanguages() {
        LanguageViewItem temp = inputLanguage;
        setInputLanguage(outputLanguage);
        setOutputLanguage(temp);
    }

    public void translate(TranslationEntity entity) {
        getViewState().writeToEdit(entity.getValue());
        getViewState().showInputLanguageDirection(entity.getLanguageInputValue());
        getViewState().showOutputLanguageDirection(entity.getLanguageOutputValue());
        getViewState().writeToEdit(entity.getValue());
        translationModel.translate(entity).enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.body() != null) {
                    getViewState().drawOutput(response.body().getText());
                } else getViewState().showToast("body null"); //todo: если переводов не будет
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable throwable) {
                getViewState().showToast(throwable.getMessage());

            }
        });
    }
}
