package av.translator.ui.languageSelector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import av.translator.R;
import av.translator.TranslatorApplication;

public class LanguageSelectorActivity extends MvpAppCompatActivity implements LanguageSelectorView {

    @InjectPresenter
    LanguageSelectorPresenter presenter;
    LanguageSelectorViewHolder viewHolder;

    LanguageSelectorAdapter adapter;

    @ProvidePresenter
    LanguageSelectorPresenter providePresenter() {
        return new LanguageSelectorPresenter(TranslatorApplication.model);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_langselector);
        viewHolder = new LanguageSelectorViewHolder(this);
        viewHolder.getList().setLayoutManager(new LinearLayoutManager(this));
        adapter = new LanguageSelectorAdapter(this);
        viewHolder.getList().setAdapter(adapter);
        adapter.setSelections(new LanguageSelectorAdapter.SelectionListener() {
            @Override
            public void onSelect(LanguageViewItem languageViewItem) {
                presenter.select(languageViewItem);
            }
        });
    }

    @Override
    public void drawContent(List<LanguageViewItem> items) {
        adapter.updateItems(items);
    }

    @Override
    public void drawError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish(LanguageViewItem item) {
        Intent result = new Intent();
        result.putExtra("result", item);
        setResult(RESULT_OK, result);
        finish();
    }
}
