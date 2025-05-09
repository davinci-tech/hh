package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.dqj;
import defpackage.eao;
import defpackage.koq;
import defpackage.nrf;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes3.dex */
public class SectionExpandableAdapter extends BaseExpandableListAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<List<eao>> f2564a;
    private Context d;
    private int e;
    private String f;
    private LayoutInflater g;
    private int i;
    private final String c = Marker.ANY_NON_NULL_MARKER;
    private final int b = 3;

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
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    class d {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2567a;
        HealthImageView b;
        HealthImageView c;
        HealthImageView d;
        HealthImageView e;
        HealthTextView h;

        private d() {
        }
    }

    class a {

        /* renamed from: a, reason: collision with root package name */
        HealthDivider f2566a;
        HealthImageView b;
        HealthTextView c;
        HealthTextView e;

        private a() {
        }
    }

    public SectionExpandableAdapter(Context context, List<List<eao>> list, String str, int i, int i2) {
        this.d = context;
        this.f2564a = list;
        this.f = str;
        this.e = i;
        this.i = i2;
    }

    public void a(List<List<eao>> list, String str, int i, int i2) {
        LogUtil.a("SectionExpandableAdapter", "setData", list.toString());
        this.f2564a = list;
        this.g = (LayoutInflater) this.d.getSystemService("layout_inflater");
        this.f = str;
        this.e = i;
        this.i = i2;
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public eao getChild(int i, int i2) {
        return this.f2564a.get(i).get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        LogUtil.a("SectionExpandableAdapter", "getChildView now is ", getChild(i, i2).toString());
        if (view == null) {
            view = this.g.inflate(R.layout.section_expandable_item_layout, (ViewGroup) null);
        }
        final a aVar = new a();
        aVar.b = (HealthImageView) view.findViewById(R.id.item_expand_icon);
        nrf.c(getChild(i, i2).c(), aVar.b, (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362362_res_0x7f0a023a));
        aVar.e = (HealthTextView) view.findViewById(R.id.item_expand_name);
        aVar.e.setText(getChild(i, i2).d());
        aVar.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionExpandableAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                dqj.a(aVar.e.getText().toString());
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        aVar.c = (HealthTextView) view.findViewById(R.id.item_expand_detail);
        aVar.c.setText(getChild(i, i2).b());
        aVar.f2566a = (HealthDivider) view.findViewById(R.id.section_expand_line);
        if (i2 == getChildrenCount(0) - 1) {
            aVar.f2566a.setVisibility(8);
        } else {
            aVar.f2566a.setVisibility(0);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (koq.d(this.f2564a, i)) {
            LogUtil.a("SectionExpandableAdapter", "mData.get(groupPosition).size() is ", Integer.valueOf(this.f2564a.get(i).size()));
        } else {
            LogUtil.a("SectionExpandableAdapter", "mData.get(groupPosition).size() is null");
        }
        return this.f2564a.get(i).size();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<eao> getGroup(int i) {
        return this.f2564a.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        LogUtil.a("SectionExpandableAdapter", "mData.size() is ", Integer.valueOf(this.f2564a.size()));
        return this.f2564a.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        LogUtil.a("SectionExpandableAdapter", "getGroupView groupPosition is", Integer.valueOf(i));
        if (view == null) {
            view = this.g.inflate(R.layout.section_expandable_goup_layout, (ViewGroup) null);
        }
        d dVar = new d();
        dVar.h = (HealthTextView) view.findViewById(R.id.item_expand_group_name);
        dVar.h.setText(this.f);
        dVar.f2567a = (HealthTextView) view.findViewById(R.id.item_expand_group_count);
        StringBuffer stringBuffer = new StringBuffer(Marker.ANY_NON_NULL_MARKER);
        if (this.f2564a.get(i).size() > 3) {
            stringBuffer.append(this.f2564a.get(i).size() - 3);
            dVar.f2567a.setText(stringBuffer.toString());
        } else {
            dVar.f2567a.setVisibility(8);
        }
        dVar.d = (HealthImageView) view.findViewById(R.id.item_expand_group_icon_1);
        dVar.b = (HealthImageView) view.findViewById(R.id.item_expand_group_icon_2);
        dVar.c = (HealthImageView) view.findViewById(R.id.item_expand_group_icon_3);
        if (this.f2564a.get(i).size() >= 1) {
            dVar.c.setVisibility(0);
            nrf.c(getChild(i, 0).c(), dVar.c, 0);
        }
        if (this.f2564a.get(i).size() >= 2) {
            dVar.b.setVisibility(0);
            nrf.c(getChild(i, 1).c(), dVar.b, 0);
        }
        if (this.f2564a.get(i).size() >= 3) {
            dVar.d.setVisibility(0);
            nrf.c(getChild(i, 2).c(), dVar.d, 0);
        }
        dVar.e = (HealthImageView) view.findViewById(R.id.item_expand_group_arrow);
        if (z) {
            dVar.e.setBackgroundResource(this.i);
            dVar.d.setVisibility(8);
            dVar.b.setVisibility(8);
            dVar.c.setVisibility(8);
            dVar.f2567a.setVisibility(8);
        } else {
            dVar.e.setBackgroundResource(this.e);
            dVar.d.setVisibility(0);
            dVar.b.setVisibility(0);
            dVar.c.setVisibility(0);
            dVar.f2567a.setVisibility(0);
        }
        return view;
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.ExpandableListAdapter
    public void onGroupExpanded(int i) {
        super.onGroupExpanded(i);
        dqj.d();
    }
}
