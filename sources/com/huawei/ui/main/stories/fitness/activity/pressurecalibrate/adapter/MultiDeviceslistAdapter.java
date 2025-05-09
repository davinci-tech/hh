package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cjv;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class MultiDeviceslistAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<cjv> f9871a = new ArrayList(16);
    private Context e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public MultiDeviceslistAdapter(Context context, List<cjv> list) {
        this.e = context;
        a(list);
    }

    public final void a(List<cjv> list) {
        if (list != null) {
            this.f9871a.clear();
            this.f9871a.addAll(list);
            LogUtil.a("MultiUserslistAdapter", "mList.size() = " + this.f9871a.size());
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f9871a.size() > 0) {
            return this.f9871a.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public cjv getItem(int i) {
        if (this.f9871a.size() <= 0 || i >= this.f9871a.size()) {
            return null;
        }
        return this.f9871a.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        e eVar;
        if (i > this.f9871a.size() || i < 0) {
            return view;
        }
        LogUtil.a("MultiUserslistAdapter", " into getview mList.size())" + this.f9871a.size());
        if (view == null) {
            eVar = new e();
            view2 = LayoutInflater.from(this.e).inflate(R.layout.multidevices_list_item_layout, (ViewGroup) null);
            eVar.e = (HealthTextView) view2.findViewById(R.id.multidevices_name);
            eVar.c = (HealthDivider) view2.findViewById(R.id.multidevices_divider_line);
            view2.setTag(eVar);
        } else {
            view2 = view;
            eVar = (e) view.getTag();
        }
        cjv cjvVar = this.f9871a.get(i);
        if (cjvVar.a() == 1) {
            DeviceInfo deviceInfo = (DeviceInfo) cjvVar.i();
            LogUtil.a("MultiUserslistAdapter", "getView wear device state:" + deviceInfo.getDeviceConnectState());
            LogUtil.a("MultiUserslistAdapter", "getView wear device name:" + deviceInfo.getDeviceName());
            eVar.e.setText(deviceInfo.getDeviceName());
        }
        c(cjvVar, eVar);
        if (i == this.f9871a.size() - 1) {
            eVar.c.setVisibility(8);
        } else {
            eVar.c.setVisibility(0);
        }
        return view2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0068 A[Catch: ClassCastException -> 0x00ca, TryCatch #0 {ClassCastException -> 0x00ca, blocks: (B:4:0x0008, B:7:0x0020, B:10:0x0027, B:12:0x002e, B:13:0x005e, B:15:0x0068, B:17:0x0072, B:18:0x00a9, B:21:0x0099, B:22:0x00c0, B:24:0x0038, B:25:0x0054), top: B:3:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c0 A[Catch: ClassCastException -> 0x00ca, TRY_LEAVE, TryCatch #0 {ClassCastException -> 0x00ca, blocks: (B:4:0x0008, B:7:0x0020, B:10:0x0027, B:12:0x002e, B:13:0x005e, B:15:0x0068, B:17:0x0072, B:18:0x00a9, B:21:0x0099, B:22:0x00c0, B:24:0x0038, B:25:0x0054), top: B:3:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(defpackage.cjv r8, com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter.MultiDeviceslistAdapter.e r9) {
        /*
            r7 = this;
            java.lang.String r0 = "MultiUserslistAdapter"
            int r1 = r8.a()
            if (r1 != 0) goto Ld3
            java.lang.Object r8 = r8.i()     // Catch: java.lang.ClassCastException -> Lca
            ctk r8 = (defpackage.ctk) r8     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r1 = r8.getProductId()     // Catch: java.lang.ClassCastException -> Lca
            ctk$a r8 = r8.b()     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r8 = r8.m()     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r2 = ""
            r3 = 0
            r4 = 1
            if (r8 == 0) goto L54
            boolean r5 = r8.equals(r2)     // Catch: java.lang.ClassCastException -> Lca
            if (r5 == 0) goto L27
            goto L54
        L27:
            int r2 = r8.length()     // Catch: java.lang.ClassCastException -> Lca
            r5 = 3
            if (r2 >= r5) goto L38
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r5 = "device sn length less than 3"
            r2[r3] = r5     // Catch: java.lang.ClassCastException -> Lca
            com.huawei.hwlogsmodel.LogUtil.h(r0, r2)     // Catch: java.lang.ClassCastException -> Lca
            goto L5e
        L38:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r6 = "-"
            r2.<init>(r6)     // Catch: java.lang.ClassCastException -> Lca
            int r6 = r8.length()     // Catch: java.lang.ClassCastException -> Lca
            int r6 = r6 - r5
            int r5 = r8.length()     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r8 = r8.substring(r6, r5)     // Catch: java.lang.ClassCastException -> Lca
            r2.append(r8)     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r8 = r2.toString()     // Catch: java.lang.ClassCastException -> Lca
            goto L5e
        L54:
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r5 = "device sn is null"
            r8[r3] = r5     // Catch: java.lang.ClassCastException -> Lca
            com.huawei.hwlogsmodel.LogUtil.h(r0, r8)     // Catch: java.lang.ClassCastException -> Lca
            r8 = r2
        L5e:
            com.huawei.health.ecologydevice.manager.ResourceManager r2 = com.huawei.health.ecologydevice.manager.ResourceManager.e()     // Catch: java.lang.ClassCastException -> Lca
            dcz r1 = r2.d(r1)     // Catch: java.lang.ClassCastException -> Lca
            if (r1 == 0) goto Lc0
            java.util.ArrayList r2 = r1.e()     // Catch: java.lang.ClassCastException -> Lca
            int r2 = r2.size()     // Catch: java.lang.ClassCastException -> Lca
            if (r2 > 0) goto L99
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.ClassCastException -> Lca
            r2.<init>()     // Catch: java.lang.ClassCastException -> Lca
            dcz$b r3 = r1.n()     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r3 = r3.b()     // Catch: java.lang.ClassCastException -> Lca
            r2.append(r3)     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r3 = java.lang.System.lineSeparator()     // Catch: java.lang.ClassCastException -> Lca
            r2.append(r3)     // Catch: java.lang.ClassCastException -> Lca
            dcz$b r1 = r1.n()     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r1 = r1.c()     // Catch: java.lang.ClassCastException -> Lca
            r2.append(r1)     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r1 = r2.toString()     // Catch: java.lang.ClassCastException -> Lca
            goto La9
        L99:
            java.lang.String r2 = r1.t()     // Catch: java.lang.ClassCastException -> Lca
            dcz$b r1 = r1.n()     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r1 = r1.b()     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r1 = defpackage.dcx.d(r2, r1)     // Catch: java.lang.ClassCastException -> Lca
        La9:
            com.huawei.ui.commonui.healthtextview.HealthTextView r9 = com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter.MultiDeviceslistAdapter.e.e(r9)     // Catch: java.lang.ClassCastException -> Lca
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.ClassCastException -> Lca
            r2.<init>()     // Catch: java.lang.ClassCastException -> Lca
            r2.append(r1)     // Catch: java.lang.ClassCastException -> Lca
            r2.append(r8)     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r8 = r2.toString()     // Catch: java.lang.ClassCastException -> Lca
            r9.setText(r8)     // Catch: java.lang.ClassCastException -> Lca
            goto Ld3
        Lc0:
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch: java.lang.ClassCastException -> Lca
            java.lang.String r9 = "productInfo is null"
            r8[r3] = r9     // Catch: java.lang.ClassCastException -> Lca
            com.huawei.hwlogsmodel.LogUtil.h(r0, r8)     // Catch: java.lang.ClassCastException -> Lca
            goto Ld3
        Lca:
            java.lang.String r8 = "Object to WiFiDevice ClassCastException"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
        Ld3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter.MultiDeviceslistAdapter.c(cjv, com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter.MultiDeviceslistAdapter$e):void");
    }

    static class e {
        private HealthDivider c;
        private HealthTextView e;

        e() {
        }
    }
}
