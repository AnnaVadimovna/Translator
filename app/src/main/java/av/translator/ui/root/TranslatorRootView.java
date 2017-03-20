package av.translator.ui.root;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import av.translator.R;
import av.translator.model.entity.TranslationEntity;
import av.translator.ui.history.HistoryFragment;
import av.translator.ui.translator.TranslatorFragment;

public class TranslatorRootView {
    TranslationEntity entity = null;
    private Toolbar toolbar;
    private FrameLayout frame;
    private BottomNavigationView bottomMenu;

    public TranslatorRootView(final View view, final FragmentManager fragmentManager) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        frame = (FrameLayout) view.findViewById(R.id.translator_frame);
        bottomMenu = (BottomNavigationView) view.findViewById(R.id.bottom_menu);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int toastMessage;
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_translate:
                        toastMessage = R.string.translator;
                        fragment = new TranslatorFragment();
                        TranslationEntity localItem = entity;
                        if (localItem != null) {
                            Bundle args = new Bundle();
                            args.putParcelable("translate", localItem);
                            fragment.setArguments(args);
                            entity = null;
                        }
                        break;
                    case R.id.action_history:
                        fragment = HistoryFragment.create(false);
                        toastMessage = R.string.histoty;
                        break;
                    case R.id.action_favorites:
                        fragment = HistoryFragment.create(true);
                        toastMessage = R.string.favorites;
                        break;
                    default:
                        toastMessage = 0;
                }
                if (toastMessage != 0) {
                    fragmentManager.beginTransaction()
                            .replace(frame.getId(), fragment)
                            .commitNow();
                    toolbar.setTitle(toastMessage);
                    //Toast.makeText(view.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    public BottomNavigationView getBottomMenu() {
        return bottomMenu;
    }

    public void destroy() {
        bottomMenu.setOnNavigationItemSelectedListener(null);
    }

    public void goToTranslator(TranslationEntity item) {
        this.entity = item;
        getBottomMenu().setSelectedItemId(R.id.action_translate);
    }
}
