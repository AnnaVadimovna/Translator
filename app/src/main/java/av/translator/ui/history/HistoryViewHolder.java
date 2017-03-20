package av.translator.ui.history;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import av.translator.R;

public class HistoryViewHolder {
    private SwipeRefreshLayout refresh;
    private RecyclerView list;
    private TextView hint;

    public HistoryViewHolder(View view) {
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.history_refresh);
        list = (RecyclerView) view.findViewById(R.id.history_list);
        hint = (TextView) view.findViewById(R.id.history_hint);
    }

    public SwipeRefreshLayout getRefresh() {
        return refresh;
    }

    public RecyclerView getList() {
        return list;
    }

    public TextView getHint() {
        return hint;
    }
}