package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class cwt {
    private static volatile cwt b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private DeviceDownloadSourceInfo f11513a;
    private List<?> d = new ArrayList(16);
    private DownloadDeviceInfoCallBack e;
    private String f;
    private boolean j;

    private cwt() {
    }

    public static cwt a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new cwt();
                }
            }
        }
        return b;
    }

    public DeviceDownloadSourceInfo b() {
        return this.f11513a;
    }

    public void c(boolean z) {
        this.j = z;
    }

    public void b(String str, String str2, DownloadDeviceInfoCallBack downloadDeviceInfoCallBack) {
        this.f = str2;
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setHiLinkDeviceId(str2);
        e(str, Collections.singletonList(deviceInfo), downloadDeviceInfoCallBack);
    }

    public void e(final String str, List<?> list, DownloadDeviceInfoCallBack downloadDeviceInfoCallBack) {
        LogUtil.a("DownloadResourceFileHelper", "handleUpdateProductMap");
        if (downloadDeviceInfoCallBack == null) {
            LogUtil.a("DownloadResourceFileHelper", "handleUpdateProductMap callBack is null");
            return;
        }
        this.e = downloadDeviceInfoCallBack;
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            LogUtil.h("DownloadResourceFileHelper", "handleUpdateProductMap: deviceInfoList or kindName is empty");
            e(104);
            return;
        }
        this.d = list;
        LogUtil.a("DownloadResourceFileHelper", "handleUpdateProductMap: list.size = ", Integer.valueOf(list.size()));
        if (jbw.c(BaseApplication.getContext())) {
            if (d()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: cws
                    @Override // java.lang.Runnable
                    public final void run() {
                        cwt.this.e(str);
                    }
                });
                return;
            } else {
                LogUtil.h("DownloadResourceFileHelper", "not have net, not download Map");
                e(103);
                return;
            }
        }
        ProductMapParseUtil.b(BaseApplication.getContext());
        d(str);
    }

    /* synthetic */ void e(String str) {
        if (jbw.d(BaseApplication.getContext(), 2)) {
            d(str);
        } else {
            LogUtil.a("DownloadResourceFileHelper", "handleUpdateProductMap download map fail");
            e(102);
        }
    }

    public void d(Context context, String str) {
        SharedPreferenceManager.e(context, "RESOURCE_UPDATA_TIME_CONFIG", str, Long.toString(System.currentTimeMillis()), new StorageParams());
    }

    public boolean c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("DownloadResourceFileHelper", "isNeedUpdataResourceTimeConfig context or devicePid is null");
            return false;
        }
        boolean z = System.currentTimeMillis() - CommonUtil.g(SharedPreferenceManager.b(context, "RESOURCE_UPDATA_TIME_CONFIG", str)) > 86400000;
        LogUtil.a("DownloadResourceFileHelper", "isNeedUpdataResourceTimeConfig isNeedUpdate = ", Boolean.valueOf(z));
        return z;
    }

    private void d(String str) {
        LogUtil.a("DownloadResourceFileHelper", "handleDownloadResource kindName = ", str);
        ArrayList arrayList = new ArrayList(this.d.size());
        for (Object obj : this.d) {
            if (obj instanceof DeviceInfo) {
                DeviceInfo deviceInfo = (DeviceInfo) obj;
                ProductMapInfo d = ProductMap.d(deviceInfo.getHiLinkDeviceId());
                if (d == null) {
                    LogUtil.h("DownloadResourceFileHelper", "handleDownloadResource sportProductInfo is null");
                    e(102);
                    return;
                }
                String h = d.h();
                LogUtil.a("DownloadResourceFileHelper", "handleDownloadResource productId is:", h);
                if (TextUtils.isEmpty(h)) {
                    LogUtil.h("DownloadResourceFileHelper", "handleDownloadResource sportProductInfo is null");
                    e(102);
                    return;
                } else {
                    deviceInfo.setProductId(h);
                    arrayList.add(h);
                }
            } else {
                LogUtil.a("DownloadResourceFileHelper", "handleDownloadResource object not match DeviceInfo");
                e(104);
                return;
            }
        }
        if (arrayList.size() == 0) {
            LogUtil.a("DownloadResourceFileHelper", "productIdList.size() is 0");
            e(101);
        }
        c(str, arrayList);
    }

    private void c(String str, List<String> list) {
        dkd dkdVar = new dkd(BaseApplication.getContext(), str, 2, list, new dkc() { // from class: cwt.2
            @Override // defpackage.dkb
            public void onDownload(int i) {
            }

            @Override // defpackage.dkb
            public void onSuccess() {
                LogUtil.a("DownloadResourceFileHelper", "HadDownloadSingleDevice onSuccess");
                cwt.this.e(100);
            }

            @Override // defpackage.dkb
            public void onFailure(int i) {
                LogUtil.a("DownloadResourceFileHelper", "HadDownloadSingleDevice onFailure");
                cwt.this.e(i);
            }
        });
        if (this.j && !e()) {
            LogUtil.h("DownloadResourceFileHelper", "set index_all downloadResourceInfo fail");
            e(101);
        } else {
            DeviceDownloadSourceInfo deviceDownloadSourceInfo = this.f11513a;
            if (deviceDownloadSourceInfo != null) {
                dkdVar.a(deviceDownloadSourceInfo);
            }
            dkdVar.b();
        }
    }

    private boolean e() {
        LogUtil.a("DownloadResourceFileHelper", "isSetIndexAllDownloadResource");
        ProductMapInfo d = ProductMap.d(this.f);
        if (d == null) {
            LogUtil.h("DownloadResourceFileHelper", "productMapInfo is null");
            return false;
        }
        String h = d.h();
        if (TextUtils.isEmpty(h)) {
            LogUtil.h("DownloadResourceFileHelper", "productId is isEmpty");
            return false;
        }
        List<msx> c2 = mst.a().c(h);
        if (koq.c(c2)) {
            msx msxVar = c2.get(0);
            if (msxVar.d() != null && msxVar.d().size() > 0) {
                this.f11513a = c2.get(0).d().get(0).d();
                return true;
            }
            this.f11513a = c2.get(0).j();
            return true;
        }
        LogUtil.h("DownloadResourceFileHelper", "devicePluginInfoBeans is isEmpty");
        return false;
    }

    private boolean d() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            return true;
        }
        LogUtil.h("DownloadResourceFileHelper", "not have net, not download");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LogUtil.a("DownloadResourceFileHelper", "callBackDownloadResult resultCode = ", Integer.valueOf(i));
        DownloadDeviceInfoCallBack downloadDeviceInfoCallBack = this.e;
        if (downloadDeviceInfoCallBack != null) {
            if (i == 100) {
                downloadDeviceInfoCallBack.onSuccess();
            } else if (i == 103) {
                downloadDeviceInfoCallBack.onNetworkError();
            } else {
                downloadDeviceInfoCallBack.onFailure(i);
            }
        }
    }
}
