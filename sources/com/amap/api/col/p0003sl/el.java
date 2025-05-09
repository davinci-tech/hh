package com.amap.api.col.p0003sl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public final class el extends BaseExpandableListAdapter implements ExpandableListView.OnGroupCollapseListener, ExpandableListView.OnGroupExpandListener {
    private boolean[] b;
    private Context c;
    private eq d;
    private es f;
    private OfflineMapManager g;
    private List<OfflineMapProvince> e = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    List<OfflineMapProvince> f1012a = new ArrayList();

    @Override // android.widget.ExpandableListAdapter
    public final long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public final long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public final boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public final boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public el(Context context, es esVar, OfflineMapManager offlineMapManager, List<OfflineMapProvince> list) {
        this.c = context;
        this.f = esVar;
        this.g = offlineMapManager;
        if (list != null && list.size() > 0) {
            this.e.clear();
            this.e.addAll(list);
            for (OfflineMapProvince offlineMapProvince : this.e) {
                if (offlineMapProvince != null && offlineMapProvince.getDownloadedCityList().size() > 0) {
                    this.f1012a.add(offlineMapProvince);
                }
            }
        }
        this.b = new boolean[this.f1012a.size()];
    }

    public final void a() {
        for (OfflineMapProvince offlineMapProvince : this.e) {
            if (offlineMapProvince.getDownloadedCityList().size() > 0 && !this.f1012a.contains(offlineMapProvince)) {
                this.f1012a.add(offlineMapProvince);
            }
        }
        this.b = new boolean[this.f1012a.size()];
        notifyDataSetChanged();
    }

    public final void b() {
        try {
            for (int size = this.f1012a.size(); size > 0; size--) {
                OfflineMapProvince offlineMapProvince = this.f1012a.get(size - 1);
                if (offlineMapProvince.getDownloadedCityList().size() == 0) {
                    this.f1012a.remove(offlineMapProvince);
                }
            }
            this.b = new boolean[this.f1012a.size()];
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public final int getGroupCount() {
        return this.f1012a.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public final int getChildrenCount(int i) {
        return this.f1012a.get(i).getDownloadedCityList().size();
    }

    @Override // android.widget.ExpandableListAdapter
    public final Object getGroup(int i) {
        return this.f1012a.get(i).getProvinceName();
    }

    @Override // android.widget.ExpandableListAdapter
    public final Object getChild(int i, int i2) {
        return this.f1012a.get(i).getDownloadedCityList().get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public final View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = (RelativeLayout) eu.a(this.c, R.plurals._2130903043_res_0x7f030003);
        }
        TextView textView = (TextView) view.findViewById(R.layout.abc_list_menu_item_radio);
        ImageView imageView = (ImageView) view.findViewById(R.layout.abc_popup_menu_header_item_layout);
        textView.setText(this.f1012a.get(i).getProvinceName());
        if (this.b[i]) {
            imageView.setImageDrawable(eu.a().getDrawable(R.string._2130837509_res_0x7f020005));
        } else {
            imageView.setImageDrawable(eu.a().getDrawable(R.string._2130837510_res_0x7f020006));
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public final View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view != null) {
            aVar = (a) view.getTag();
        } else {
            aVar = new a();
            eq eqVar = new eq(this.c, this.g);
            this.d = eqVar;
            eqVar.a(2);
            view = this.d.a();
            aVar.f1014a = this.d;
            view.setTag(aVar);
        }
        OfflineMapProvince offlineMapProvince = this.f1012a.get(i);
        if (i2 < offlineMapProvince.getDownloadedCityList().size()) {
            final OfflineMapCity offlineMapCity = offlineMapProvince.getDownloadedCityList().get(i2);
            aVar.f1014a.a(offlineMapCity);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.3sl.el.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    el.this.f.a(offlineMapCity);
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
        return view;
    }

    public final class a {

        /* renamed from: a, reason: collision with root package name */
        public eq f1014a;

        public a() {
        }
    }

    @Override // android.widget.ExpandableListView.OnGroupCollapseListener
    public final void onGroupCollapse(int i) {
        this.b[i] = false;
    }

    @Override // android.widget.ExpandableListView.OnGroupExpandListener
    public final void onGroupExpand(int i) {
        this.b[i] = true;
    }
}
