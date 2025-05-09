package com.huawei.healthcloud.plugintrack.bolt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.gsy;
import defpackage.jed;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class BoltDeviceInfoAdapter extends RecyclerView.Adapter<d> {
    private OnItemClickListener b;
    private List<gsy.b> c;
    private Context d;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    BoltDeviceInfoAdapter(List<gsy.b> list, Context context) {
        this.c = list;
        this.d = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aTw_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bolt_device_info, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, final int i) {
        if (koq.b(this.c, i)) {
            LogUtil.h("Track_BoltDeviceInfoAdapter", "onBindViewHolder() outOfBounds position: ", Integer.valueOf(i));
            return;
        }
        gsy.b bVar = this.c.get(i);
        if (bVar == null) {
            LogUtil.h("Track_BoltDeviceInfoAdapter", "onBindViewHolder() deviceInfo is null");
            return;
        }
        if (bVar.e() == null) {
            LogUtil.h("Track_BoltDeviceInfoAdapter", "onBindViewHolder() deviceInfo.getDeviceInfo() is null");
            return;
        }
        b(dVar, bVar);
        b(dVar);
        dVar.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltDeviceInfoAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BoltDeviceInfoAdapter.this.b != null) {
                    BoltDeviceInfoAdapter.this.b.onItemClick(i);
                } else {
                    LogUtil.h("Track_BoltDeviceInfoAdapter", "mOnItemClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        BoltCustomDialog.a().b(true);
    }

    private void b(d dVar, gsy.b bVar) {
        dVar.b.setImageResource(R.drawable._2131429808_res_0x7f0b09b0);
        dVar.b.setVisibility(0);
        dVar.c.setVisibility(8);
        LogUtil.a("Track_BoltDeviceInfoAdapter", "initBoltDeviceStatus() deviceInfo: ", bVar);
        int deviceConnectState = bVar.e().getDeviceConnectState();
        if (bVar.d() && deviceConnectState == 2) {
            dVar.b.setImageResource(R.drawable._2131429792_res_0x7f0b09a0);
            dVar.b.setVisibility(0);
            dVar.c.setText(this.d.getResources().getString(R.string._2130840025_res_0x7f0209d9));
            dVar.c.setVisibility(0);
        }
        if (!bVar.d()) {
            dVar.b.setImageResource(R.drawable._2131429808_res_0x7f0b09b0);
            dVar.b.setVisibility(0);
            dVar.c.setText("");
        }
        if (bVar.c()) {
            dVar.b.setVisibility(8);
            dVar.g.setVisibility(0);
        }
        if (deviceConnectState == 1) {
            dVar.b.setImageResource(R.drawable._2131429808_res_0x7f0b09b0);
            dVar.b.setVisibility(8);
            dVar.g.setVisibility(0);
            dVar.c.setVisibility(0);
            dVar.c.setText(this.d.getResources().getString(R.string.IDS_hw_health_wear_connect_device_connect_text));
        }
        if (deviceConnectState == 3) {
            dVar.b.setImageResource(R.drawable._2131429808_res_0x7f0b09b0);
            dVar.b.setVisibility(0);
            dVar.c.setVisibility(8);
        }
        if (deviceConnectState == 2 && bVar.a() >= 0) {
            dVar.f3517a.setText(jed.b(bVar.a(), 2, 0));
            dVar.e.setImageDrawable(nsn.cLd_(bVar.a()));
            dVar.f3517a.setVisibility(0);
            dVar.e.setVisibility(0);
        } else {
            dVar.f3517a.setVisibility(8);
            dVar.e.setVisibility(8);
        }
        dVar.d.setText(bVar.e().getDeviceName());
        e(deviceConnectState);
    }

    private void e(int i) {
        if (BoltCustomDialog.a().d() || i != 4) {
            return;
        }
        Context context = this.d;
        nrh.d(context, context.getString(R.string._2130840111_res_0x7f020a2f));
    }

    private void b(d dVar) {
        if (LanguageUtil.bc(this.d)) {
            dVar.e.setScaleX(-1.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<gsy.b> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f3517a;
        ImageView b;
        HealthTextView c;
        HealthTextView d;
        ImageView e;
        HealthProgressBar g;
        LinearLayout j;

        d(View view) {
            super(view);
            this.j = (LinearLayout) view.findViewById(R.id.item_bolt_root);
            this.e = (ImageView) view.findViewById(R.id.item_bolt_battery_image);
            this.f3517a = (HealthTextView) view.findViewById(R.id.item_bolt_battery_value);
            this.d = (HealthTextView) view.findViewById(R.id.item_device_name);
            this.b = (ImageView) view.findViewById(R.id.item_check_image);
            this.c = (HealthTextView) view.findViewById(R.id.item_bolt_in_use);
            this.g = (HealthProgressBar) view.findViewById(R.id.hw_device_before_one_loading_img);
        }
    }

    public void b(OnItemClickListener onItemClickListener) {
        this.b = onItemClickListener;
    }
}
