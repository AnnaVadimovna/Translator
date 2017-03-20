package av.translator.ui.languageSelector;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.Map;

import av.translator.R;

public class LanguageSelectorViewHolder {
    private RecyclerView list;
    private SwipeRefreshLayout refresh;

    public LanguageSelectorViewHolder(LanguageSelectorActivity languageSelectorActivity) {
        list = (RecyclerView) languageSelectorActivity.findViewById(R.id.langs_list_id);
    }

    public void setList(RecyclerView list) {
        this.list = list;
    }

    public void setRefresh(SwipeRefreshLayout refresh) {
        this.refresh = refresh;
    }

    public RecyclerView getList() {
        return list;
    }

    public SwipeRefreshLayout getRefresh() {
        return refresh;
    }
}
