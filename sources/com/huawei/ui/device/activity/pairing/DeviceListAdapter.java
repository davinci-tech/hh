package com.huawei.ui.device.activity.pairing;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CompileParameterUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class DeviceListAdapter extends BaseAdapter {
    private LayoutInflater d;
    private List<String> c = new ArrayList(12);

    /* renamed from: a, reason: collision with root package name */
    private List<c> f9178a = new ArrayList(12);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DeviceListAdapter() {
    }

    public DeviceListAdapter(Context context) {
        if (context != null) {
            this.d = LayoutInflater.from(context);
        }
    }

    public void b(List<BluetoothDeviceNode> list) {
        if (list == null) {
            return;
        }
        this.f9178a.clear();
        try {
            Iterator<BluetoothDeviceNode> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    BluetoothDeviceNode next = it.next();
                    c cVar = new c();
                    BluetoothDevice btDevice = next.getBtDevice();
                    if (TextUtils.isEmpty(next.getNodeId())) {
                        if (btDevice.getName() == null) {
                            cVar.d(next.getRecordName());
                        } else {
                            cVar.d(btDevice.getName());
                        }
                        cVar.a(btDevice.getAddress());
                    } else {
                        cVar.d(next.getDisplayName());
                        cVar.a(next.getNodeId());
                    }
                    cVar.a(true);
                    this.f9178a.add(cVar);
                } else {
                    c cVar2 = new c();
                    cVar2.d(BaseApplication.getContext().getString(R$string.IDS_device_mgr_not_found_device));
                    cVar2.a("");
                    cVar2.a(false);
                    this.f9178a.add(cVar2);
                    notifyDataSetChanged();
                    return;
                }
            }
        } catch (SecurityException e) {
            LogUtil.b("DeviceListAdapter", "setList SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void a(List<BluetoothDeviceNode> list, int i) {
        if (list == null) {
            return;
        }
        if (i > this.f9178a.size()) {
            b(list);
            return;
        }
        if (list.size() != this.f9178a.size()) {
            b(list);
            return;
        }
        BluetoothDeviceNode bluetoothDeviceNode = list.get(i);
        BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
        c cVar = new c();
        if (TextUtils.isEmpty(bluetoothDeviceNode.getNodeId())) {
            cVar.d(btDevice.getName());
            cVar.a(btDevice.getAddress());
        } else {
            cVar.d(bluetoothDeviceNode.getDisplayName());
            cVar.a(bluetoothDeviceNode.getNodeId());
        }
        cVar.a(true);
        this.f9178a.add(i, cVar);
        List<c> list2 = this.f9178a;
        if (list2.get(list2.size() - 1).e()) {
            c cVar2 = new c();
            cVar2.d(BaseApplication.getContext().getString(R$string.IDS_device_mgr_not_found_device));
            cVar2.a("");
            cVar2.a(false);
            this.f9178a.add(cVar2);
        }
        LogUtil.a("DeviceListAdapter", "devices.size() : ", Integer.valueOf(list.size()), "devices.size() : mList ", Integer.valueOf(this.f9178a.size()));
        notifyDataSetChanged();
    }

    public void a(List<String> list) {
        if (list == null) {
            return;
        }
        this.c = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f9178a.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public c getItem(int i) {
        if (i < 0 || i >= this.f9178a.size()) {
            return null;
        }
        return this.f9178a.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        d dVar;
        if (viewGroup == null) {
            LogUtil.h(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DeviceListAdapter", "container is null");
        }
        if (view == null) {
            view = this.d.inflate(R.layout.device_item_layout, (ViewGroup) null);
            dVar = new d();
            dVar.d = (HealthTextView) view.findViewById(R.id.item_device_name);
            dVar.c = view.findViewById(R.id.item_device_line);
            view.setTag(dVar);
        } else if (view.getTag() instanceof d) {
            dVar = (d) view.getTag();
        } else {
            LogUtil.b("DeviceListAdapter", "holder is not ViewHolder");
            return view;
        }
        if (i == this.f9178a.size() - 1) {
            dVar.c.setVisibility(8);
        } else {
            dVar.c.setVisibility(0);
        }
        if (TextUtils.isEmpty(this.f9178a.get(i).d())) {
            dVar.d.setText(this.f9178a.get(i).c());
        } else if (b(this.f9178a.get(i).d())) {
            dVar.d.setText(this.f9178a.get(i).d());
        } else if (!this.f9178a.get(i).e()) {
            dVar.d.setText(this.f9178a.get(i).d());
        } else {
            dVar.d.setText(this.f9178a.get(i).c());
        }
        if (i == this.f9178a.size() - 1) {
            dVar.d.setTextColor(BaseApplication.getContext().getResources().getColor(R$color.common_ui_text_color));
        } else {
            dVar.d.setTextColor(BaseApplication.getContext().getResources().getColor(R$color.colorPrimary));
        }
        return view;
    }

    public static class d {
        private View c;
        public HealthTextView d;
    }

    private boolean b(String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            if (this.c.size() > 0 && "Blacktip".equalsIgnoreCase(this.c.get(0))) {
                return false;
            }
            LogUtil.h(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DeviceListAdapter", "isShowDeviceName deviceName is null and return true.");
            return true;
        }
        if (CompileParameterUtil.a("IS_SUPPORT_NEW_ADD_MODE", false)) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DeviceListAdapter", "isShowDeviceName use new add device mode.");
        } else {
            z = a(str);
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DeviceListAdapter", "isShowDeviceName isShowName is ", Boolean.valueOf(z));
        return z;
    }

    private boolean a(String str) {
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        boolean z = false;
        for (String str2 : this.c) {
            if (TextUtils.isEmpty(str2)) {
                z = true;
            } else if (upperCase.contains(str2.toUpperCase(Locale.ENGLISH))) {
                return true;
            }
        }
        return z;
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f9179a;
        private boolean c;
        private String d;

        private c() {
            this.c = true;
        }

        public String d() {
            return this.d;
        }

        public void d(String str) {
            if (str == null) {
                return;
            }
            this.d = str;
        }

        public String c() {
            return this.f9179a;
        }

        public void a(String str) {
            if (str == null) {
                return;
            }
            this.f9179a = str;
        }

        public boolean e() {
            return this.c;
        }

        public void a(boolean z) {
            this.c = z;
        }
    }
}
