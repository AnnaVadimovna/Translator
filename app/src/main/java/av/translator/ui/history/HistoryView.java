package av.translator.ui.history;


import com.arellomobile.mvp.MvpView;

import java.util.List;

import av.translator.model.entity.TranslationEntity;

public interface HistoryView extends MvpView {
   void showHistory(List<TranslationEntity> items);
   void showEmptyHistory();
   void showToast(String message);
   void select(TranslationEntity item);
}
