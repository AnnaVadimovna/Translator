package av.translator.ui.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import av.translator.model.TranslationModel;
import av.translator.model.entity.TranslationEntity;
import av.translator.ui.languageSelector.LanguageViewItem;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {
    TranslationModel translationModel;
    private boolean isFavoriteList;
    private String input;
    private LanguageViewItem inputLanguage;
    private LanguageViewItem outputLanguage;

    public HistoryPresenter(TranslationModel translationModel) {
        this.translationModel = translationModel;
    }

    public void setFavoriteList(boolean favoriteList) {
        isFavoriteList = favoriteList;
    }

    public void refresh() {
        try {
            List<TranslationEntity> history = translationModel.getHistory();
            if (isFavoriteList) {
                List<TranslationEntity> onlyFav = new ArrayList<>();
                for (TranslationEntity item : history) {
                    if (item.isFavorite()) {
                        onlyFav.add(item);
                    }
                }
                history = onlyFav;
            }
            if (history.size() != 0) {
                getViewState().showHistory(history);
            } else {
                getViewState().showEmptyHistory();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            getViewState().showEmptyHistory();
            getViewState().showToast("SQL exception");
        }
    }

    public void click(TranslationEntity entity) {
        getViewState().select(entity);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        refresh();
    }

    public void longClick(TranslationEntity entity) {
        entity.setFavorite(!entity.isFavorite());
        try {
            translationModel.updateItem(entity);
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            getViewState().showToast("SQL exception");
        }
    }
}
