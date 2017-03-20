package av.translator.ui.languageSelector;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import av.translator.api.entity.LangsResponse;
import av.translator.model.TranslationModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class LanguageSelectorPresenter extends MvpPresenter<LanguageSelectorView> {
    TranslationModel translationModel;

    public void refresh() {
        Callback<LangsResponse> callback = new Callback<LangsResponse>() {
            @Override
            public void onResponse(Call<LangsResponse> call, Response<LangsResponse> response) {
                if(response.isSuccessful()){
                    getViewState().drawContent(proceede(response.body()));
                }else{
                    getViewState().drawError(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<LangsResponse> call, Throwable throwable) {
                getViewState().drawError(throwable.getMessage());
            }
        };
        translationModel.getLangs("ru").enqueue(callback);
    }

    public void select(LanguageViewItem item) {
        getViewState().finish(item);
    }

    public LanguageSelectorPresenter(TranslationModel translationModel) {
        this.translationModel = translationModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        refresh();
    }

    private List<LanguageViewItem> proceede(LangsResponse response){
        ArrayList<LanguageViewItem> languageSelectorViews = new ArrayList<>();

        for (Map.Entry<String, String> item: response.getLangs().entrySet()) {
            LanguageViewItem languageViewItem = new LanguageViewItem(item.getValue(), item.getKey());
            languageSelectorViews.add(languageViewItem);
        }

        return languageSelectorViews;
    }
}
