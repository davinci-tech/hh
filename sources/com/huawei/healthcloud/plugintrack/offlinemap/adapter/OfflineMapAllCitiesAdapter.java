package com.huawei.healthcloud.plugintrack.offlinemap.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.CityListBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gyd;
import defpackage.gym;
import defpackage.koq;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class OfflineMapAllCitiesAdapter extends BaseExpandableListAdapter {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<Integer, CityListBean> f3531a;
    private Activity b;
    private ArrayList<OfflineMapProvince> d;

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

    public OfflineMapAllCitiesAdapter(Activity activity, ArrayList<OfflineMapProvince> arrayList, HashMap<Integer, CityListBean> hashMap) {
        this.d = new ArrayList<>(10);
        this.f3531a = new HashMap<>(10);
        if (activity != null) {
            this.b = activity;
        }
        if (arrayList != null) {
            this.d = arrayList;
        }
        if (hashMap != null) {
            this.f3531a = hashMap;
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        if (i < 0 || i >= this.f3531a.size() || !koq.c(this.f3531a.get(Integer.valueOf(i))) || !koq.d(this.f3531a.get(Integer.valueOf(i)), i2) || this.f3531a.get(Integer.valueOf(i)).get(i2) == null) {
            return null;
        }
        return this.f3531a.get(Integer.valueOf(i)).get(i2).getCity();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        gym gymVar;
        OfflineMapCity offlineMapCity = null;
        if (view == null) {
            view = RelativeLayout.inflate(this.b, R.layout.track_offlinemap_child, null);
            gymVar = new gym(view);
        } else {
            gymVar = (gym) view.getTag();
        }
        if (i >= 0 && i < this.f3531a.size() && this.f3531a.get(Integer.valueOf(i)) != null && i2 >= 0 && i2 < this.f3531a.get(Integer.valueOf(i)).size()) {
            offlineMapCity = this.f3531a.get(Integer.valueOf(i)).get(i2);
        }
        gymVar.e(this.f3531a.get(Integer.valueOf(i)), i2);
        if (offlineMapCity != null) {
            int state = offlineMapCity.getState();
            int i3 = offlineMapCity.getcompleteCode();
            String city = offlineMapCity.getCity();
            String format = String.format(c(R.string._2130850260_res_0x7f0231d4), new DecimalFormat("0.0").format(offlineMapCity.getSize() / 1048576.0f));
            gymVar.d().setText(city);
            gymVar.b().setText(format);
            gymVar.a().setIdleText(c(R.string._2130839735_res_0x7f0208b7));
            LogUtil.c("OfflineMapAllCitiesAdapter", "getView() city:", city, "formatSize:", format, ",state:", Integer.valueOf(state), ",progress:", Integer.valueOf(i3));
            gymVar.a().setVisibility(8);
            gymVar.d(this.b, state, i3);
            if (this.b instanceof OfflineMapTabActivity) {
                gymVar.a().setOnClickListener(new gyd(this.b, state, city));
            }
        }
        return view;
    }

    private String c(int i) {
        return this.b.getResources().getString(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (i < 0 || i >= this.f3531a.size() || this.f3531a.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return this.f3531a.get(Integer.valueOf(i)).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        if (koq.d(this.d, i)) {
            return this.d.get(i).getProvinceName();
        }
        return null;
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public int getGroupType(int i) {
        return super.getGroupType(i);
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public int getGroupTypeCount() {
        return super.getGroupTypeCount();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.d.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        View view2;
        a aVar;
        if (view == null) {
            aVar = new a();
            view2 = RelativeLayout.inflate(this.b, R.layout.track_offlinemap_group, null);
            aVar.f3532a = (HealthTextView) view2.findViewById(R.id.group_text);
            aVar.d = (ImageView) view2.findViewById(R.id.group_image);
            aVar.b = (HealthDivider) view2.findViewById(R.id.offline_group_view);
            aVar.c = (HealthTextView) view2.findViewById(R.id.group_support);
            view2.setTag(aVar);
        } else {
            view2 = view;
            aVar = (a) view.getTag();
        }
        if (koq.d(this.d, i)) {
            aVar.f3532a.setText(this.d.get(i).getProvinceName());
            if (i == this.d.size() - 1) {
                aVar.b.setVisibility(8);
            } else {
                aVar.b.setVisibility(0);
            }
        }
        if ("台湾省".equals(this.d.get(i).getProvinceName())) {
            aVar.c.setVisibility(0);
            aVar.d.setVisibility(8);
        } else {
            aVar.c.setVisibility(8);
            aVar.d.setVisibility(0);
            if (z) {
                aVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
            } else {
                aVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
            }
        }
        return view2;
    }

    public void b(HashMap<Integer, CityListBean> hashMap, ArrayList<OfflineMapProvince> arrayList) {
        this.f3531a = hashMap;
        this.d = arrayList;
        notifyDataSetChanged();
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f3532a;
        private HealthDivider b;
        private HealthTextView c;
        private ImageView d;

        private a() {
        }
    }
}
