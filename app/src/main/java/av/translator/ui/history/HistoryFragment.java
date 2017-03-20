package av.translator.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import av.translator.ui.root.TranslatorActivity;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView {

    @InjectPresenter
    HistoryPresenter presenter;
    HistoryViewHolder viewHolder;
    TranslationItemAdapter adapter;
    boolean isFavoriteList;

    public static HistoryFragment create(boolean isFavoriteList) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putBoolean("isFavoriteList", isFavoriteList);
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    HistoryPresenter providePresenter() {
        return new HistoryPresenter(TranslatorApplication.model);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null && args.containsKey("isFavoriteList")) {
            isFavoriteList = args.getBoolean("isFavoriteList");
            presenter.setFavoriteList(isFavoriteList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewHolder = new HistoryViewHolder(view);
        adapter = new TranslationItemAdapter(getContext());
        viewHolder.getList().setLayoutManager(new LinearLayoutManager(getContext()));
        viewHolder.getList().setAdapter(adapter);
        viewHolder.getRefresh().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        adapter.setSelections(new TranslationItemAdapter.SelectionListener() {
            @Override
            public void onSelect(TranslationEntity entity) {
                presenter.click(entity);
            }

            @Override
            public void onLongSelect(TranslationEntity entity) {
                presenter.longClick(entity);
            }
        });
    }

    @Override
    public void showHistory(List<TranslationEntity> items) {
        viewHolder.getRefresh().setVisibility(View.VISIBLE);
        viewHolder.getHint().setVisibility(View.GONE);
        adapter.update(items);
        viewHolder.getRefresh().setRefreshing(false);
    }

    @Override
    public void showEmptyHistory() {
        viewHolder.getRefresh().setRefreshing(false);
        viewHolder.getRefresh().setVisibility(View.GONE);
        viewHolder.getHint().setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void select(TranslationEntity item) {
        TranslatorActivity activity = (TranslatorActivity) getActivity();
        activity.goToTranslator(item);
    }
}