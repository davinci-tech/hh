package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.NoScrollListView;
import defpackage.pti;
import defpackage.ptt;
import java.util.List;

/* loaded from: classes6.dex */
public class OptionsListAdapter extends BaseAdapter {
    private ListView b;
    private List<pti> d;
    private Context e;

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return true;
    }

    public OptionsListAdapter(Context context, List<pti> list, ListView listView) {
        this.e = context;
        this.d = list;
        this.b = listView;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<pti> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.e).inflate(R.layout.prassure_calibrate_question_item_option, (ViewGroup) null);
            aVar = new a();
            aVar.f9872a = (HealthRadioButton) view.findViewById(R.id.hw_pressure_calibrate_check_name);
            aVar.d = (HealthTextView) view.findViewById(R.id.hw_pressure_calibrate_check_tv_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        d(i, aVar.f9872a);
        if (((!(viewGroup instanceof NoScrollListView) && !(viewGroup instanceof ListView)) || !ptt.d().h()) && i >= 0 && i < this.d.size()) {
            aVar.d.setText(this.d.get(i).e());
        }
        return view;
    }

    private void d(int i, HealthRadioButton healthRadioButton) {
        if (this.b.isItemChecked(i)) {
            healthRadioButton.setChecked(true);
        } else {
            healthRadioButton.setChecked(false);
        }
        notifyDataSetChanged();
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private HealthRadioButton f9872a;
        private HealthTextView d;

        private a() {
        }
    }
}
