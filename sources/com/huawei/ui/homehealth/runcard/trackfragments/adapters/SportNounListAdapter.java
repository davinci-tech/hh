package com.huawei.ui.homehealth.runcard.trackfragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.oro;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class SportNounListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<oro> f9579a;
    private Context c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SportNounListAdapter(Context context, List<oro> list) {
        this.f9579a = new ArrayList(10);
        this.c = context;
        if (list != null) {
            this.f9579a = list;
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<oro> list = this.f9579a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<oro> list = this.f9579a;
        if (list == null || koq.b(list, i)) {
            return null;
        }
        return this.f9579a.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            e eVar2 = new e();
            View inflate = LayoutInflater.from(this.c).inflate(R.layout.layout_sport_noun_item, (ViewGroup) null);
            eVar2.c = (HealthTextView) inflate.findViewById(R.id.hw_show_sport_noun_text_title);
            eVar2.d = (HealthTextView) inflate.findViewById(R.id.hw_show_sport_noun_text_content);
            inflate.setTag(eVar2);
            eVar = eVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof e)) {
                LogUtil.a("SportNounExpandableListAdapter", "getView object is not instanceof ItemViewHolder");
                return view;
            }
            eVar = (e) tag;
        }
        List<oro> list = this.f9579a;
        if (list == null || koq.b(list, i)) {
            LogUtil.a("SportNounExpandableListAdapter", "position is out of bounds or mList is null");
            return view;
        }
        oro oroVar = this.f9579a.get(i);
        if (oroVar == null) {
            LogUtil.h("SportNounExpandableListAdapter", "child is null'");
            return view;
        }
        b(eVar, oroVar);
        return view;
    }

    private void b(e eVar, oro oroVar) {
        if (eVar == null || oroVar == null) {
            LogUtil.h("SportNounExpandableListAdapter", "viewholder or data is null");
        } else {
            eVar.c.setText(oroVar.b());
            eVar.d.setText(oroVar.e().replace("\\n", "\n"));
        }
    }

    public static class e {
        private HealthTextView c;
        private HealthTextView d;
    }
}
