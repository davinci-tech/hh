package com.huawei.ui.main.stories.nps.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes9.dex */
public class ScoreLayoutAdapter extends BaseAdapter {
    private int mChoiceNum;
    private LayoutInflater mInflater;
    private String[] mScoreArray = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ScoreLayoutAdapter(Context context, int i) {
        this.mChoiceNum = i;
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService instanceof LayoutInflater) {
            this.mInflater = (LayoutInflater) systemService;
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        String[] strArr = this.mScoreArray;
        if (strArr == null) {
            return 0;
        }
        return strArr.length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i < 0) {
            return "";
        }
        String[] strArr = this.mScoreArray;
        return i >= strArr.length ? "" : strArr[i];
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = this.mInflater;
        if (layoutInflater == null) {
            return view;
        }
        if (view == null) {
            view = layoutInflater.inflate(R.layout.adapter_item_score, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.mScore = (HealthTextView) view.findViewById(R.id.settings_score_text);
            viewHolder.mScoreImg = (ImageView) view.findViewById(R.id.settings_score_imgview);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i < this.mScoreArray.length) {
            viewHolder.mScore.setText(this.mScoreArray[i]);
        }
        if (this.mChoiceNum == (this.mScoreArray.length - i) - 1) {
            viewHolder.mScoreImg.setImageResource(R.drawable._2131427695_res_0x7f0b016f);
        } else {
            viewHolder.mScoreImg.setImageResource(R.drawable._2131427694_res_0x7f0b016e);
        }
        return view;
    }

    static class ViewHolder {
        HealthTextView mScore;
        ImageView mScoreImg;

        private ViewHolder() {
        }
    }
}
