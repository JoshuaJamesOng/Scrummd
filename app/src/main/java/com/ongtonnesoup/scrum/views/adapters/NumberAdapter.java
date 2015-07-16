package com.ongtonnesoup.scrum.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.NumberModel;

import javax.inject.Inject;

public class NumberAdapter extends BaseAdapter {

    @Inject
    Context mContext;
    private final String[] mNumbers;
    private int mColor;

    public NumberAdapter(NumberModel numberModel, int textColorId) {
        ScrummdApplication.inject(this);
        mNumbers = NumberModel.getValues();
        mColor = textColorId;
    }

    public int getCount() {
        return mNumbers.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        NumberHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item, null);

            viewHolder = new NumberHolder();
            viewHolder.textView = (CircleView) convertView.findViewById(R.id.grid_item_text);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (NumberHolder) convertView.getTag();
        }

        viewHolder.textView.setTitleText(mNumbers[position]);
        viewHolder.textView.setFillColor(mColor);
        viewHolder.textView.setStrokeColor(mColor);
        return convertView;
    }

    class NumberHolder {
        CircleView textView;
    }
}
