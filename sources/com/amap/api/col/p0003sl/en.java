package com.amap.api.col.p0003sl;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapActivity;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public final class en extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<OfflineMapCity> f1017a = new ArrayList();
    private OfflineMapManager b;
    private Activity c;

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    public en(OfflineMapManager offlineMapManager, OfflineMapActivity offlineMapActivity) {
        this.b = offlineMapManager;
        this.c = offlineMapActivity;
    }

    public final void a(List<OfflineMapCity> list) {
        this.f1017a = list;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f1017a.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.f1017a.get(i);
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        final a aVar;
        int state;
        try {
            final OfflineMapCity offlineMapCity = this.f1017a.get(i);
            if (view == null) {
                aVar = new a();
                view = eu.a(this.c, R.plurals._2130903042_res_0x7f030002);
                aVar.f1019a = (TextView) view.findViewById(R.layout.abc_cascading_menu_item_layout);
                aVar.b = (TextView) view.findViewById(R.layout.abc_list_menu_item_icon);
                aVar.c = (TextView) view.findViewById(R.layout.abc_expanded_menu_layout);
                aVar.d = (ImageView) view.findViewById(R.layout.abc_list_menu_item_checkbox);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.3sl.en.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    aVar.d.setVisibility(8);
                    aVar.c.setVisibility(0);
                    aVar.c.setText("下载中");
                    try {
                        en.this.b.downloadByCityName(offlineMapCity.getCity());
                        ViewClickInstrumentation.clickOnView(view2);
                    } catch (AMapException e) {
                        e.printStackTrace();
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                }
            });
            aVar.c.setVisibility(0);
            aVar.f1019a.setText(offlineMapCity.getCity());
            TextView textView = aVar.b;
            textView.setText(String.valueOf(((int) (((offlineMapCity.getSize() / 1024.0d) / 1024.0d) * 100.0d)) / 100.0d) + " M");
            state = offlineMapCity.getState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (state != -1) {
            if (state == 0 || state == 1) {
                aVar.d.setVisibility(8);
                aVar.c.setText("下载中");
            } else if (state == 2) {
                aVar.d.setVisibility(8);
                aVar.c.setText("等待下载");
            } else if (state == 3) {
                aVar.d.setVisibility(8);
                aVar.c.setText("暂停中");
            } else if (state == 4) {
                aVar.d.setVisibility(8);
                aVar.c.setText("已下载");
            } else if (state != 6) {
                switch (state) {
                }
            } else {
                aVar.d.setVisibility(0);
                aVar.c.setVisibility(8);
            }
            return view;
        }
        aVar.d.setVisibility(8);
        aVar.c.setText("下载失败");
        return view;
    }

    public final class a {

        /* renamed from: a, reason: collision with root package name */
        public TextView f1019a;
        public TextView b;
        public TextView c;
        public ImageView d;

        public a() {
        }
    }
}
