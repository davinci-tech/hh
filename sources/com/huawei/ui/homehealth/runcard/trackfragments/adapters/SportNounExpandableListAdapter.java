package com.huawei.ui.homehealth.runcard.trackfragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.orn;
import defpackage.oro;
import java.util.List;

/* loaded from: classes9.dex */
public class SportNounExpandableListAdapter extends BaseExpandableListAdapter {
    private List<orn> b;
    private Context d;

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public SportNounExpandableListAdapter(Context context) {
        this.d = context == null ? BaseApplication.getContext() : context;
    }

    public void a(List<orn> list) {
        LogUtil.a("SportNounExpandableListAdapter", "resetGroupData mGroupData");
        List<orn> list2 = this.b;
        if (list2 != null) {
            list2.clear();
        }
        this.b = list;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<orn> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (koq.b(this.b, i)) {
            return 0;
        }
        return this.b.get(i).e();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        if (koq.b(this.b, i)) {
            return null;
        }
        return this.b.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        if (koq.b(this.b, i)) {
            return null;
        }
        return this.b.get(i).e(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            b bVar2 = new b();
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.layout_sport_noun_father_item, (ViewGroup) null);
            bVar2.b = (HealthTextView) inflate.findViewById(R.id.hw_show_health_data_sport_noun_father_date);
            bVar2.f9578a = (ImageView) inflate.findViewById(R.id.hw_show_health_data_sport_noun_father_arrow);
            bVar2.e = (HealthDivider) inflate.findViewById(R.id.hw_show_health_data_sport_noun_father_line);
            inflate.setTag(bVar2);
            bVar = bVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof b)) {
                LogUtil.a("SportNounExpandableListAdapter", "getGroupView object is not instanceof FatherViewHolder");
                return view;
            }
            bVar = (b) tag;
        }
        if (koq.b(this.b, i)) {
            LogUtil.h("SportNounExpandableListAdapter", "groupPosition is out of bounds");
            return view;
        }
        if (z) {
            bVar.f9578a.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_sel);
        } else {
            bVar.f9578a.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_nor);
        }
        if (this.b.get(i).d() != null) {
            bVar.b.setText(this.b.get(i).d());
        }
        if (i == getGroupCount() - 1) {
            bVar.e.setVisibility(8);
        } else {
            bVar.e.setVisibility(0);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            eVar = new e();
            view = LayoutInflater.from(this.d).inflate(R.layout.layout_sport_noun_child_item, (ViewGroup) null);
            eVar.e = (HealthTextView) view.findViewById(R.id.hw_show_sport_noun_child_text_title);
            eVar.d = (HealthTextView) view.findViewById(R.id.hw_show_sport_noun_child_text_content);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        if (koq.b(this.b, i)) {
            LogUtil.a("SportNounExpandableListAdapter", "groupPosition is out of bounds");
            return view;
        }
        oro e2 = this.b.get(i).e(i2);
        if (e2 == null) {
            LogUtil.h("SportNounExpandableListAdapter", "child is null'");
            return view;
        }
        c(eVar, e2);
        return view;
    }

    private void c(e eVar, oro oroVar) {
        if (eVar == null || oroVar == null) {
            LogUtil.h("SportNounExpandableListAdapter", "viewholder or data is null");
        } else {
            eVar.e.setText(oroVar.b());
            eVar.d.setText(oroVar.e().replace("\\n", "\n"));
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f9578a;
        private HealthTextView b;
        private HealthDivider e;
    }

    public static class e {
        private HealthTextView d;
        private HealthTextView e;
    }
}
