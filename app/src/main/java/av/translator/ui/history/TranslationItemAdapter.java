package av.translator.ui.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import av.translator.R;
import av.translator.model.entity.TranslationEntity;

public class TranslationItemAdapter extends RecyclerView.Adapter<TranslationItemAdapter.ViewHolder> {

    List<TranslationEntity> list = new ArrayList<>();
    TranslationItemAdapter.SelectionListener listener;
    private LayoutInflater inflater;

    public TranslationItemAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setSelections(TranslationItemAdapter.SelectionListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.translator_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TranslationEntity translationEntity = list.get(position);
        holder.bind(translationEntity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationItemAdapter.SelectionListener localListener = listener;
                if (localListener != null) {
                    localListener.onSelect(translationEntity);
                }
            }
        });
        holder.isFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TranslationItemAdapter.SelectionListener localListener = listener;
                if (localListener != null) {
                    localListener.onLongSelect(translationEntity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<TranslationEntity> items) {
        this.list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    interface SelectionListener {
        void onSelect(TranslationEntity entity);

        void onLongSelect(TranslationEntity entity);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView language;
        ImageButton isFavorite;

        public ViewHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.translation_text);
            language = (TextView) itemView.findViewById(R.id.language);
            isFavorite = (ImageButton) itemView.findViewById(R.id.is_favorite);
        }

        public void bind(TranslationEntity translationEntity) {
            text.setText(translationEntity.getValue());
            language.setText(translationEntity.getLanguageInputValue() + " -> " + translationEntity.getLanguageOutputValue());
            if (translationEntity.isFavorite()) {
                isFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
            } else {
                isFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        }
    }
}