package com.ongtonnesoup.scrum.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.pavlospt.CircleView;
import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;

import javax.inject.Inject;

public class NumberAdapter extends BaseAdapter {

    @Inject
    protected Context mContext;
    @Inject
    protected NumberModel mNumberModel;

    private int mColor;

    public NumberAdapter(int textColorId) {
        ScrummdApplication.inject(this);
        mColor = textColorId;
    }

    public int getCount() {
        return mNumberModel.getNumbers().length;
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
            viewHolder.circleView = (CircleView) convertView.findViewById(R.id.grid_item_text);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (NumberHolder) convertView.getTag();
        }

        viewHolder.circleView.setTitleText(mNumberModel.getNumbers()[position]);
        viewHolder.circleView.setFillColor(mColor);
        viewHolder.circleView.setStrokeColor(mColor);
        return convertView;
    }

    class NumberHolder {
        CircleView circleView;
    }
}
