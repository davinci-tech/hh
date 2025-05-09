package com.huawei.health.airsharing.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.android.airsharing.api.ProjectionDevice;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceListAdapter extends BaseAdapter {
    private LayoutInflater b;
    private List<ProjectionDevice> d = new ArrayList(10);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DeviceListAdapter() {
    }

    public DeviceListAdapter(Context context) {
        if (context != null) {
            this.b = LayoutInflater.from(context);
        }
    }

    public void a(ProjectionDevice projectionDevice) {
        LogUtil.a("Distribute_DeviceListAdapter", "addDevice...");
        if (projectionDevice == null || b(projectionDevice)) {
            return;
        }
        this.d.add(projectionDevice);
        notifyDataSetChanged();
    }

    public void a() {
        List<ProjectionDevice> list = this.d;
        if (list != null) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    public void d(ProjectionDevice projectionDevice) {
        LogUtil.a("Distribute_DeviceListAdapter", "removeDevice...");
        if (projectionDevice == null) {
            return;
        }
        Iterator<ProjectionDevice> it = this.d.iterator();
        while (it.hasNext()) {
            ProjectionDevice next = it.next();
            if (next != null && TextUtils.equals(next.getDeviceName(), projectionDevice.getDeviceName()) && TextUtils.equals(next.getIndication(), projectionDevice.getIndication())) {
                it.remove();
                notifyDataSetChanged();
                return;
            }
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.d.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i >= getCount() || i < 0) {
            return null;
        }
        return this.d.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (viewGroup == null) {
            LogUtil.b("Distribute_DeviceListAdapter", "container is null");
        }
        if (view == null) {
            view = this.b.inflate(R.layout.projection_device_item_layout, (ViewGroup) null);
            eVar = new e();
            eVar.e = (HealthTextView) view.findViewById(R.id.item_miracast_device_name);
            eVar.b = view.findViewById(R.id.item_miracast_device_line);
            view.setTag(eVar);
        } else {
            if (!(view.getTag() instanceof e)) {
                return null;
            }
            eVar = (e) view.getTag();
        }
        if (i == this.d.size() - 1) {
            eVar.b.setVisibility(8);
        } else {
            eVar.b.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.d.get(i).getDeviceName())) {
            eVar.e.setText(this.d.get(i).getDeviceName());
        }
        return view;
    }

    public static class e {
        private View b;
        private HealthTextView e;
    }

    private boolean b(ProjectionDevice projectionDevice) {
        if (projectionDevice == null) {
            return false;
        }
        for (ProjectionDevice projectionDevice2 : this.d) {
            if (projectionDevice2 != null && TextUtils.equals(projectionDevice2.getDeviceName(), projectionDevice.getDeviceName()) && TextUtils.equals(projectionDevice2.getIndication(), projectionDevice.getIndication())) {
                return true;
            }
        }
        return false;
    }
}
