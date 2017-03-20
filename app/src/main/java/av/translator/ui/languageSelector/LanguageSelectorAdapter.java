package av.translator.ui.languageSelector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import av.translator.R;

public class LanguageSelectorAdapter extends RecyclerView.Adapter<LanguageSelectorAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<LanguageViewItem> items = new ArrayList<>();

    SelectionListener listener;

    public void setSelections(SelectionListener listener) {
        this.listener = listener;
    }

    public LanguageSelectorAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.language_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LanguageViewItem languageViewItem = items.get(position);
        holder.bind(languageViewItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectionListener localListener = listener;
                if (localListener != null) {
                    localListener.onSelect(languageViewItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<LanguageViewItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name = (TextView) itemView.findViewById(R.id.language_text);

        ViewHolder(View itemView) {
            super(itemView);
        }

        void bind(LanguageViewItem languageViewItem) {
            name.setText(languageViewItem.getDisplay());
        }
    }

    interface SelectionListener {
        void onSelect(LanguageViewItem languageViewItem);
    }
}