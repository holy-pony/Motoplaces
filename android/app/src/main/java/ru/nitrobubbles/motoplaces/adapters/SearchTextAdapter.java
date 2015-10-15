package ru.nitrobubbles.motoplaces.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Predicate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nitrobubbles.motoplaces.R;
import ru.nitrobubbles.motoplaces.model.Motoplace;
import ru.nitrobubbles.motoplaces.support.GeoSupport;
import ru.nitrobubbles.motoplaces.support.SharedPreferencesManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by konstantinaksenov on 15.10.15.
 */
public class SearchTextAdapter extends ArrayAdapter<Motoplace> {
    private final Activity context;
    private ArrayList<Motoplace> items;
    private ArrayList<Motoplace> itemsAll;
    private ArrayList<Motoplace> suggestions;

    public SearchTextAdapter(Activity context, ArrayList<Motoplace> motoplaces
    ) {
        super(context, R.layout.search_item, motoplaces);
        this.context = context;
        this.items = motoplaces;
        this.itemsAll = (ArrayList<Motoplace>) items.clone();
        this.suggestions = new ArrayList<>();
    }

    static class ViewHolder {
        @Bind(R.id.title)
        public TextView title;
        @Bind(R.id.subtitle)
        public TextView subtext;
        @Bind(R.id.distance)
        public TextView distance;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View rowView = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.search_item,
                null, true);

        Motoplace motoplace = getItem(position);
        holder = new ViewHolder(rowView);
        holder.title.setText(motoplace.getTitle());
        if(TextUtils.isEmpty(motoplace.getAddress()))
             holder.subtext.setText(motoplace.getAddress());
        else {
            GeoSupport.loadAddress(motoplace.getPosition())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        holder.subtext.setText(s);
                    });
        }
        rowView.setTag(holder);
        float distance =  motoplace.distanceTo();
        distance = distance / 1000;
        holder.distance.setText(String.format("%.1f км", distance));
        return rowView;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Motoplace)(resultValue)).getTitle();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                suggestions.addAll(
                        Stream.of(itemsAll)
                        .filter(value -> value.getTitle().toLowerCase().contains(constraint))
                        .sorted((lhs, rhs) -> Float.compare(lhs.distanceTo(), rhs.distanceTo()))
                        .collect(Collectors.toList()));
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Motoplace> filteredList = (ArrayList<Motoplace>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Motoplace c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}
