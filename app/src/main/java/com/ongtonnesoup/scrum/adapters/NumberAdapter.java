package com.ongtonnesoup.scrum.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.github.pavlospt.CircleView;
import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.managers.NumberModelDecorator;
import com.ongtonnesoup.scrum.managers.NumberModelManager;

import javax.inject.Inject;

public class NumberAdapter extends BaseAdapter {

    @Inject
    protected Context mContext;
    @Inject
    protected NumberModelManager mNumberModelManager;
    @Inject
    protected NumberModelDecorator mNumberModelDecorator;

    private int mColor;

    public NumberAdapter(int textColorId) {
        ScrummdApplication.inject(this);
        mColor = textColorId;
    }

    public int getCount() {
        return mNumberModelManager.getCurrentModel().getNumbers().length;
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
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (NumberHolder) convertView.getTag();
        }

        String estimate = mNumberModelDecorator.getNumber(position);
        int resourceId = mNumberModelDecorator.getResourceIdentifier(position);

        if (estimate != null) {
            viewHolder.circleView.setTitleText(mNumberModelManager.getCurrentModel().getNumbers()[position]);
            viewHolder.imageView.setVisibility(View.GONE);
        } else {
            viewHolder.circleView.setTitleText("");
            viewHolder.imageView.setImageResource(resourceId);
            viewHolder.imageView.setVisibility(View.VISIBLE);
        }
        viewHolder.circleView.setFillColor(mColor);
        viewHolder.circleView.setStrokeColor(mColor);
        return convertView;
    }

    class NumberHolder {
        CircleView circleView;
        ImageView imageView;
    }
}
