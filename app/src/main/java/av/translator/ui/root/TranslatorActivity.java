package av.translator.ui.root;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import av.translator.R;
import av.translator.model.entity.TranslationEntity;
import av.translator.ui.translator.TranslatorFragment;
import av.translator.ui.translator.TranslatorPresenter;

public class TranslatorActivity extends MvpAppCompatActivity {
    TranslatorRootView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.translator_frame, new TranslatorFragment())
                    .commitNow();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        view = new TranslatorRootView(findViewById(R.id.translator_activity_root), getSupportFragmentManager());
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.destroy();
    }

    public void goToTranslator(TranslationEntity item) {
       view.goToTranslator( item);
    }
}
