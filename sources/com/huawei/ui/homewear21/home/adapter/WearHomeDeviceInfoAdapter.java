package com.huawei.ui.homewear21.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;
import defpackage.pbn;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeDeviceInfoAdapter extends RecyclerView.Adapter<DeviceInfoViewHolder> {
    private Context b;
    private List<pbn> d;

    public WearHomeDeviceInfoAdapter(Context context, List<pbn> list) {
        this.b = context;
        this.d = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dll_, reason: merged with bridge method [inline-methods] */
    public DeviceInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DeviceInfoViewHolder(LayoutInflater.from(this.b).inflate(R.layout.acticity_device_info_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(DeviceInfoViewHolder deviceInfoViewHolder, int i) {
        if (i >= this.d.size()) {
            return;
        }
        if (i == this.d.size() - 1) {
            deviceInfoViewHolder.f9658a.setVisibility(8);
        } else {
            deviceInfoViewHolder.f9658a.setVisibility(0);
        }
        pbn pbnVar = this.d.get(i);
        deviceInfoViewHolder.d.setText(pbnVar.e());
        deviceInfoViewHolder.b.setText(pbnVar.a());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.d.size() > 0) {
            return this.d.size();
        }
        return 0;
    }

    public static class DeviceInfoViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f9658a;
        private HealthTextView b;
        private HealthTextView d;

        DeviceInfoViewHolder(View view) {
            super(view);
            this.f9658a = (HealthDivider) nsy.cMd_(view, R.id.device_info_divider);
            this.d = (HealthTextView) nsy.cMd_(view, R.id.device_info_basic_key);
            this.b = (HealthTextView) nsy.cMd_(view, R.id.device_info_basic_value);
        }
    }
}
