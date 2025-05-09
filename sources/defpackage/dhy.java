package defpackage;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.VersionVerifyUtilApi;
import com.huawei.health.device.callback.VersionNoSupportCallback;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.dhy;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dhy {

    /* renamed from: a, reason: collision with root package name */
    private Activity f11670a;
    private CustomProgressDialog.Builder b;
    private CustomTextAlertDialog c;
    private CustomProgressDialog d;
    private dkc e;
    private boolean f;
    private String g;
    private DownloadResultCallBack h;
    private Handler i;
    private dkd j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private ArrayList<String> r;

    /* renamed from: dhy$3, reason: invalid class name */
    class AnonymousClass3 extends dkc {
        AnonymousClass3() {
        }

        @Override // defpackage.dkb
        public void onSuccess() {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "onSuccess");
            VersionVerifyUtilApi versionVerifyUtilApi = (VersionVerifyUtilApi) Services.c("PluginDevice", VersionVerifyUtilApi.class);
            if (!versionVerifyUtilApi.isPublishVersion(dhy.this.l, dhy.this.n)) {
                versionVerifyUtilApi.noSupportDevice(dhy.this.f11670a, dhy.this.f11670a, new VersionNoSupportCallback() { // from class: die
                    @Override // com.huawei.health.device.callback.VersionNoSupportCallback
                    public final void onDialogClose() {
                        dhy.AnonymousClass3.this.c();
                    }
                });
                return;
            }
            if (dhy.this.f) {
                dks.Ww_(dhy.this.f11670a, dhy.this.l);
            } else {
                dhy.this.g();
            }
            dhy.this.a("");
        }

        /* synthetic */ void c() {
            dhy.this.a("");
        }

        @Override // defpackage.dkb
        public void onDownload(final int i) {
            dhy.this.i.post(new Runnable() { // from class: dia
                @Override // java.lang.Runnable
                public final void run() {
                    dhy.AnonymousClass3.this.c(i);
                }
            });
        }

        /* synthetic */ void c(int i) {
            if (i == 0) {
                LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "onDownload");
                dhy.this.f();
            }
            dhy.this.d(i);
        }

        @Override // defpackage.dkb
        public void onFailure(int i) {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "onFailure");
            if (dhy.this.f11670a == null) {
                LogUtil.h("PluginDevice_ThirdDeviceQrCodeHelper", "activity is null");
            } else {
                dhy dhyVar = dhy.this;
                dhyVar.a(dhyVar.f11670a.getString(R.string.IDS_downlod_device_error));
            }
        }
    }

    public dhy(Activity activity, String str, String str2, String str3) {
        this(activity, str, str2, str3, null);
    }

    public dhy(Activity activity, String str, String str2, String str3, String str4) {
        this.i = new Handler(Looper.getMainLooper());
        this.h = new DownloadResultCallBack() { // from class: dhy.4
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
            public void setDownloadStatus(int i, int i2) {
                if (i == 0) {
                    return;
                }
                LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "setDownloadStatus status: ", Integer.valueOf(i));
                if (i == 1) {
                    mst.a().b();
                    dhy.this.j();
                } else {
                    dhy dhyVar = dhy.this;
                    dhyVar.a(dhyVar.f11670a.getString(R.string.IDS_downlod_device_error));
                }
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
            }
        };
        this.e = new AnonymousClass3();
        this.f11670a = activity;
        this.o = str;
        this.k = str2;
        this.m = str3;
        this.g = str4;
    }

    public void d(String str, boolean z) {
        this.f = z;
        d(str);
    }

    public void d(final String str) {
        LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "initDownloadData");
        if (this.f11670a == null) {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "startDownloadResource activity is null");
            return;
        }
        if (TextUtils.isEmpty(this.m) || TextUtils.isEmpty(str)) {
            LogUtil.h("PluginDevice_ThirdDeviceQrCodeHelper", "smartProductId or kindName is null");
            c();
            return;
        }
        if (!CommonUtil.aa(this.f11670a)) {
            a(this.f11670a.getString(R.string._2130844160_res_0x7f021a00));
            return;
        }
        if (Utils.o()) {
            this.i.post(new Runnable() { // from class: dhw
                @Override // java.lang.Runnable
                public final void run() {
                    dhy.this.h();
                }
            });
            return;
        }
        this.n = str;
        if (jbw.c(this.f11670a)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dhz
                @Override // java.lang.Runnable
                public final void run() {
                    dhy.this.b(str);
                }
            });
        } else {
            ProductMapParseUtil.b(BaseApplication.getContext());
            g(str);
        }
    }

    /* synthetic */ void b(String str) {
        if (jbw.d(BaseApplication.getContext(), 2)) {
            g(str);
        } else {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "download map fail");
            a(this.f11670a.getString(R.string.IDS_downlod_device_error));
        }
    }

    private void g(String str) {
        LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "initDownloadData");
        ProductMapInfo d = ProductMap.d(this.m);
        if (d == null) {
            LogUtil.h("PluginDevice_ThirdDeviceQrCodeHelper", "startDownloadResource sportProductInfo is null");
            a(this.f11670a.getString(R.string.IDS_downlod_device_error));
            return;
        }
        this.l = d.h();
        this.r = new ArrayList<>(16);
        if (TextUtils.isEmpty(this.l)) {
            LogUtil.h("PluginDevice_ThirdDeviceQrCodeHelper", "startDownloadResource sportProductInfo is null");
            a(this.f11670a.getString(R.string.IDS_downlod_device_error));
        } else {
            this.r.add(this.l);
            this.j = new dkd(this.f11670a, str, 1, this.r, this.e);
            a();
        }
    }

    private void a() {
        DownloadManagerApi downloadManagerApi = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            LogUtil.h("PluginDevice_ThirdDeviceQrCodeHelper", "downloadResource net is error");
            a(this.f11670a.getString(R.string._2130844160_res_0x7f021a00));
        } else {
            downloadManagerApi.addDownloadIndexAllCallBack(this.h);
            ReleaseLogUtil.e("EcologyDevice_ThirdDeviceQrCodeHelper", "downLoad index_all");
            downloadManagerApi.downloadIndexAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        List<msx> c = mst.a().c(this.r.get(0));
        if (koq.c(c)) {
            DeviceDownloadSourceInfo j = c.get(0).j();
            if (j != null && j.getSite() == 1) {
                LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "deviceDownloadSource site = 1");
                this.j.a(j);
            }
            this.j.b();
            return;
        }
        a(this.f11670a.getString(R.string.IDS_downlod_device_error));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str) {
        this.i.post(new Runnable() { // from class: dhv
            @Override // java.lang.Runnable
            public final void run() {
                dhy.this.c(str);
            }
        });
    }

    /* synthetic */ void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            e(str);
        }
        b();
        c();
        d();
    }

    private void e(String str) {
        Activity activity = this.f11670a;
        if (activity == null) {
            LogUtil.h("PluginDevice_ThirdDeviceQrCodeHelper", "activity is null");
        } else {
            nrh.c(activity, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "startDownLoadProgress");
        CustomProgressDialog customProgressDialog = this.d;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "startDownLoadProgress exists");
            return;
        }
        this.d = new CustomProgressDialog(this.f11670a);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.f11670a);
        this.b = builder;
        builder.d(this.f11670a.getString(R.string._2130841851_res_0x7f0210fb)).cyH_(new View.OnClickListener() { // from class: dhy.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "startDownLoadProgress onclick cancel");
                dhy.this.i();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e = this.b.e();
        this.d = e;
        e.setCanceledOnTouchOutside(false);
        if (this.f11670a.isFinishing()) {
            return;
        }
        this.d.show();
        this.b.d(0);
        this.b.e(UnitUtil.e(0.0d, 2, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        CustomProgressDialog customProgressDialog = this.d;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || i <= 0) {
            return;
        }
        this.b.d(i);
        this.b.e(UnitUtil.e(i, 2, 0));
    }

    private void b() {
        LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "enter closeProgress");
        CustomProgressDialog customProgressDialog = this.d;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.f11670a.isFinishing()) {
            return;
        }
        this.d.cancel();
        LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "enter closeProgress cancel");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "enter handleCancel");
        a(this.f11670a.getString(R.string.IDS_downlod_device_error));
        if (TextUtils.isEmpty(this.l) || koq.b(this.r)) {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "productId or mUuidList is empty");
            return;
        }
        Iterator<String> it = this.r.iterator();
        while (it.hasNext()) {
            msq e = msn.c().e(it.next());
            if (e != null) {
                EzPluginManager.a().a(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (TextUtils.isEmpty(this.l)) {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "productId is null");
            return;
        }
        dcz d = ResourceManager.e().d(this.l);
        if (d == null) {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "productInfo is null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.l);
        contentValues.put("uniqueId", this.k);
        contentValues.put("name", d.n().b());
        contentValues.put("deviceType", d.l().name());
        new BleJsInteractionCompact().startH5Pro(this.f11670a, "com.huawei.health.device." + this.l, contentValues, e());
    }

    private String e() {
        if ("O06A".equals(this.m)) {
            return "#/?mac=" + this.k + "&healthProductId=" + this.l;
        }
        if (TextUtils.isEmpty(this.g)) {
            return "#/?sn=" + this.o;
        }
        return "#/?sn=" + this.o + "&ex" + this.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        CustomTextAlertDialog customTextAlertDialog = this.c;
        if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
            LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "showIsOverseaTipsDialog Already show!");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.f11670a).d(R.string._2130849801_res_0x7f023009).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: dhy.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (dhy.this.c != null) {
                    dhy.this.c.dismiss();
                }
                dhy.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.c = a2;
        a2.setCancelable(false);
        if (this.c.isShowing()) {
            return;
        }
        this.c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Activity activity = this.f11670a;
        if (activity == null || "MainActivity".equals(activity.getClass().getSimpleName())) {
            return;
        }
        this.f11670a.finish();
    }

    private void d() {
        LogUtil.a("PluginDevice_ThirdDeviceQrCodeHelper", "downloadClean");
        CustomProgressDialog customProgressDialog = this.d;
        if (customProgressDialog != null) {
            if (customProgressDialog.isShowing()) {
                this.d.dismiss();
            }
            this.d = null;
        }
        CustomProgressDialog.Builder builder = this.b;
        if (builder != null) {
            builder.cyH_(null);
            this.b = null;
        }
        CustomTextAlertDialog customTextAlertDialog = this.c;
        if (customTextAlertDialog != null) {
            if (customTextAlertDialog.isShowing()) {
                this.c.dismiss();
            }
            this.c = null;
        }
        dkd dkdVar = this.j;
        if (dkdVar != null) {
            dkdVar.e();
        }
        this.e = null;
        this.i = null;
        this.f11670a = null;
        this.k = null;
        this.o = null;
        this.m = null;
    }
}
