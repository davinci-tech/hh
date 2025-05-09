package com.huawei.healthcloud.plugintrack.offlinemap.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gyd;
import defpackage.gym;
import defpackage.koq;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class OfflineMapSomeCitiesAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Activity f3533a;
    private List<OfflineMapCity> b;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public OfflineMapSomeCitiesAdapter(Activity activity, List<OfflineMapCity> list) {
        this.b = new ArrayList(10);
        if (activity != null) {
            this.f3533a = activity;
        }
        if (list != null) {
            this.b = list;
        }
    }

    public void e(List<OfflineMapCity> list) {
        this.b = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<OfflineMapCity> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.d(this.b, i)) {
            return this.b.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        gym gymVar;
        if (view == null) {
            view = RelativeLayout.inflate(this.f3533a, R.layout.track_offlinemap_child_some, null);
            gymVar = new gym(view);
        } else {
            gymVar = (gym) view.getTag();
        }
        gymVar.b(this.b, i);
        if (koq.d(this.b, i)) {
            OfflineMapCity offlineMapCity = this.b.get(i);
            int state = offlineMapCity.getState();
            int i2 = offlineMapCity.getcompleteCode();
            String city = offlineMapCity.getCity();
            String format = String.format(e(R.string._2130850260_res_0x7f0231d4), new DecimalFormat("0.0").format(offlineMapCity.getSize() / 1048576.0f));
            gymVar.d().setText(city);
            gymVar.b().setText(format);
            gymVar.a().setIdleText(e(R.string._2130839735_res_0x7f0208b7));
            LogUtil.c("OfflineMapSomeCitiesAdapter", "getView() city:", city, "formatSize:", format, ",state:", Integer.valueOf(state), ",progress:", Integer.valueOf(i2));
            gymVar.a().setVisibility(8);
            gymVar.d(this.f3533a, state, i2);
            if (this.f3533a instanceof OfflineMapTabActivity) {
                gymVar.a().setOnClickListener(new gyd(this.f3533a, state, city));
            }
        }
        return view;
    }

    private String e(int i) {
        return this.f3533a.getResources().getString(i);
    }
}
