package com.huawei.ui.device.activity.autoscandevice;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cuw;
import defpackage.cvc;
import defpackage.cve;
import defpackage.cwf;
import defpackage.jfu;
import defpackage.nun;
import defpackage.oae;
import defpackage.sqo;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/* loaded from: classes6.dex */
public class GalleryAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f9066a;
    private List<String> b;
    private OnItemClickListener c;
    private ArrayList<BluetoothDevice> d;
    private List<Boolean> e = new ArrayList(16);

    public interface OnItemClickListener {
        void onClick(int i);
    }

    public GalleryAdapter(Context context, ArrayList<BluetoothDevice> arrayList) {
        this.b = new ArrayList(16);
        this.f9066a = LayoutInflater.from(context);
        this.b = jfu.e();
        this.d = arrayList;
        if (this.d == null) {
            LogUtil.h(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "GalleryAdapter", "GalleryAdapter mDataList is null");
            return;
        }
        for (int i = 0; i < this.d.size(); i++) {
            if (i == 0) {
                this.e.add(true);
            } else {
                this.e.add(false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cPq_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.f9066a.inflate(R.layout.device_auto_scan_recycler_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.e.get(i).booleanValue()) {
            viewHolder.c.setChecked(true);
        } else {
            viewHolder.c.setChecked(false);
        }
        if (i == this.d.size() - 1) {
            viewHolder.i.setVisibility(0);
            viewHolder.g.setVisibility(8);
        } else {
            viewHolder.i.setVisibility(8);
            viewHolder.g.setVisibility(0);
        }
        if (i == 0) {
            viewHolder.d.setVisibility(0);
        } else {
            viewHolder.d.setVisibility(8);
        }
        if (this.d.size() > 1) {
            viewHolder.d.setVisibility(8);
            viewHolder.g.setVisibility(0);
            viewHolder.i.setVisibility(8);
        }
        if (this.d.size() == 1) {
            viewHolder.c.setVisibility(8);
            viewHolder.f9068a.setGravity(17);
        } else {
            viewHolder.c.setVisibility(0);
            viewHolder.f9068a.setGravity(16);
        }
        try {
            if (!a(this.d.get(i).getName()) && !d(this.d.get(i).getName())) {
                viewHolder.f9068a.setText(this.d.get(i).getAddress());
                c(viewHolder, i);
            }
            viewHolder.f9068a.setText(this.d.get(i).getName());
            int c = oae.c(this.d.get(i).getName());
            if (jfu.m(c)) {
                e(viewHolder, c, this.d.get(i).getName());
            } else {
                viewHolder.e.setImageResource(e(this.d.get(i).getName()));
            }
            c(viewHolder, i);
        } catch (SecurityException e) {
            LogUtil.b("GalleryAdapter", "onBindViewHolder SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void c(ViewHolder viewHolder, final int i) {
        viewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.GalleryAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (int i2 = 0; i2 < GalleryAdapter.this.e.size(); i2++) {
                    GalleryAdapter.this.e.set(i2, false);
                }
                GalleryAdapter.this.e.set(i, true);
                GalleryAdapter.this.notifyDataSetChanged();
                if (GalleryAdapter.this.c != null) {
                    GalleryAdapter.this.c.onClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(com.huawei.ui.device.activity.autoscandevice.GalleryAdapter.ViewHolder r7, int r8, java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r0 = defpackage.jfu.j(r8)
            java.lang.Class<com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi> r1 = com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi.class
            java.lang.String r2 = "DownloadDeviceResource"
            java.lang.Object r1 = health.compact.a.Services.c(r2, r1)
            com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi r1 = (com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi) r1
            boolean r0 = r1.isResourcesAvailable(r0)
            if (r0 == 0) goto Ld7
            java.lang.Class<com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi> r0 = com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi.class
            java.lang.Object r0 = health.compact.a.Services.c(r2, r0)
            com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi r0 = (com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi) r0
            java.lang.String r1 = defpackage.jfu.j(r8)
            cvc r0 = r0.getPluginInfoByUuid(r1)
            if (r0 == 0) goto Ld7
            cvj r1 = r0.f()
            if (r1 != 0) goto L2e
            goto Ld7
        L2e:
            nuo r1 = defpackage.nuo.e()
            java.lang.String r1 = r1.d(r9)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            java.lang.String r4 = "GalleryAdapter"
            if (r3 != 0) goto L69
            java.lang.String r8 = r6.c(r1, r8, r0)
            java.lang.String r3 = "handlerIsPluginDownload productId:"
            java.lang.String r5 = "imageName:"
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1, r5, r8}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 != 0) goto L60
            java.lang.Class<com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi> r1 = com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi.class
            java.lang.Object r1 = health.compact.a.Services.c(r2, r1)
            com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi r1 = (com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi) r1
            android.graphics.Bitmap r8 = r1.loadImageByImageName(r0, r8)
            goto L6a
        L60:
            java.lang.String r8 = "handlerIsPluginDownload imageName not contains productId"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r8)
        L69:
            r8 = 0
        L6a:
            if (r8 != 0) goto Lb4
            cvj r8 = r0.f()
            java.util.ArrayList r8 = r8.aa()
            if (r8 == 0) goto Laa
            cvj r8 = r0.f()
            java.util.ArrayList r8 = r8.aa()
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L85
            goto Laa
        L85:
            java.lang.String r8 = "bitmap is null get default bitmap"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r8)
            java.lang.Class<com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi> r8 = com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi.class
            java.lang.Object r8 = health.compact.a.Services.c(r2, r8)
            com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi r8 = (com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi) r8
            cvj r1 = r0.f()
            java.util.ArrayList r1 = r1.aa()
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.Bitmap r8 = r8.loadImageByImageName(r0, r1)
            goto Lb4
        Laa:
            android.widget.ImageView r7 = r7.e
            int r8 = r6.e(r9)
            r7.setImageResource(r8)
            return
        Lb4:
            if (r8 == 0) goto Lc5
            java.lang.String r9 = "bitmap is not null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r9)
            android.widget.ImageView r7 = r7.e
            r7.setImageBitmap(r8)
            goto Ld7
        Lc5:
            android.widget.ImageView r7 = r7.e
            int r8 = r6.e(r9)
            r7.setImageResource(r8)
            java.lang.String r7 = "bitmap is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r7)
        Ld7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.device.activity.autoscandevice.GalleryAdapter.e(com.huawei.ui.device.activity.autoscandevice.GalleryAdapter$ViewHolder, int, java.lang.String):void");
    }

    private String c(String str, int i, cvc cvcVar) {
        DeviceInfo deviceInfo = new DeviceInfo();
        if (str.contains(Constants.LINK)) {
            String sb = i == 72 ? new StringBuilder(str).insert(2, Constants.LINK).toString() : str;
            deviceInfo.setDeviceModel(sb);
            String a2 = cwf.a(cvcVar, 1, deviceInfo);
            if (!TextUtils.isEmpty(a2) && a2.contains(sb)) {
                return a2;
            }
            str = str.split(Constants.LINK)[0];
        }
        deviceInfo.setHiLinkDeviceId(str);
        deviceInfo.setDeviceModel(null);
        String a3 = cwf.a(cvcVar, 1, deviceInfo);
        if (!TextUtils.isEmpty(a3) && a3.contains(str)) {
            return a3;
        }
        sqo.a("auto scan get product id image is error");
        LogUtil.h("GalleryAdapter", "handlerIsPluginDownload imageName not contains productId");
        return a3;
    }

    private int e(String str) {
        cuw c = jfu.c(oae.c(str));
        return (c == null || c.k().size() <= 0) ? R.drawable._2131430456_res_0x7f0b0c38 : c.k().get(0).intValue();
    }

    private boolean a(String str) {
        return nun.b(str, this.b);
    }

    private boolean d(String str) {
        try {
            List<cve> deviceList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList();
            ArrayList arrayList = new ArrayList(16);
            for (cve cveVar : deviceList) {
                if (cveVar.d() == 0 && cveVar.r() != null && cveVar.r().startsWith(PutDataRequest.WEAR_URI_SCHEME)) {
                    arrayList.addAll(cveVar.e());
                }
            }
            return nun.b(str, arrayList);
        } catch (ConcurrentModificationException unused) {
            LogUtil.b("GalleryAdapter", "filterToNamesByCloud, is error");
            return false;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<BluetoothDevice> arrayList = this.d;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void c(OnItemClickListener onItemClickListener) {
        this.c = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9068a;
        LinearLayout b;
        HealthCheckBox c;
        View d;
        ImageView e;
        View g;
        View i;

        public ViewHolder(View view) {
            super(view);
            this.e = (ImageView) view.findViewById(R.id.equipment_image);
            this.f9068a = (HealthTextView) view.findViewById(R.id.equipment_name);
            this.b = (LinearLayout) view.findViewById(R.id.equipment_layout);
            this.c = (HealthCheckBox) view.findViewById(R.id.equipment_cb);
            this.d = view.findViewById(R.id.left_layout);
            this.g = view.findViewById(R.id.right_layout);
            this.i = view.findViewById(R.id.right_layout_24);
        }
    }
}
