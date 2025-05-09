package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cnw;
import defpackage.cpp;
import defpackage.ctv;
import defpackage.nmn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class WifiDeviceShareMemberInfoAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f2257a;
    private List<cnw.d> b;
    private String e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WifiDeviceShareMemberInfoAdapter(Context context, String str, List<cnw.d> list) {
        new ArrayList(16);
        this.f2257a = context;
        this.b = list;
        this.e = str;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i < 0 || i >= this.b.size()) {
            return null;
        }
        return this.b.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        b bVar;
        if (i < 0 || i > this.b.size()) {
            return view;
        }
        if (view == null) {
            bVar = new b();
            view2 = LayoutInflater.from(this.f2257a).inflate(R.layout.item_wifi_device_share_layout, (ViewGroup) null);
            bVar.e = (ImageView) view2.findViewById(R.id.share_member_header_img);
            bVar.b = (HealthTextView) view2.findViewById(R.id.share_member_header_title_tv);
            bVar.c = (HealthTextView) view2.findViewById(R.id.share_member_sub_title_tv);
            bVar.d = (ImageView) view2.findViewById(R.id.arrow_img);
            view2.setTag(bVar);
        } else if (view.getTag() instanceof b) {
            view2 = view;
            bVar = (b) view.getTag();
        } else {
            view2 = view;
            bVar = null;
        }
        if (bVar != null) {
            cnw.d dVar = this.b.get(i);
            ctv.Mh_(null, bVar.e, nmn.cBh_(this.f2257a.getResources(), new nmn.c(null, dVar.d(), true)));
            if (!TextUtils.isEmpty(dVar.a())) {
                bVar.b.setText(dVar.a());
            } else {
                bVar.b.setText(dVar.b());
            }
            DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.e);
            stringBuffer.append("_");
            stringBuffer.append(dVar.d());
            String c = deviceCloudSharePreferencesManager.c(stringBuffer.toString());
            if (!TextUtils.isEmpty(c)) {
                bVar.b.setText(c);
            }
            bVar.c.setText(dVar.b());
            if (dVar.e() == 1) {
                bVar.c.setText(R.string.IDS_device_wifi_share_account_administrator);
            }
            bVar.d.setVisibility(0);
        }
        return view2;
    }

    static class b {
        private HealthTextView b;
        private HealthTextView c;
        private ImageView d;
        private ImageView e;

        b() {
        }
    }
}
