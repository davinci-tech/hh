package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.callback.DownloadCallback;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hmf.services.internal.ApplicationContext;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class cwv {
    public void c(String str, String str2, boolean z, final DownloadCallback downloadCallback) {
        ProductMapInfo d = ProductMap.d(str2);
        if (d == null) {
            LogUtil.h("DownloadResourceUtils", "productMapInfo is null");
            downloadCallback.onFailure(1001);
            return;
        }
        String h = d.h();
        if (TextUtils.isEmpty(h)) {
            LogUtil.h("DownloadResourceUtils", "productId is isEmpty");
            downloadCallback.onFailure(1001);
            return;
        }
        DeviceDownloadSourceInfo b = b(h);
        if (z && b == null) {
            LogUtil.h("DownloadResourceUtils", "set index_all downloadResourceInfo fail");
            downloadCallback.onFailure(1003);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(h);
        dkd dkdVar = new dkd(BaseApplication.getContext(), str, 2, arrayList, new dkc() { // from class: cwv.2
            @Override // defpackage.dkb
            public void onDownload(int i) {
            }

            @Override // defpackage.dkb
            public void onSuccess() {
                LogUtil.a("DownloadResourceUtils", "downloadSingleDeviceResource onSuccess");
                downloadCallback.onSuccess();
            }

            @Override // defpackage.dkb
            public void onFailure(int i) {
                LogUtil.h("DownloadResourceUtils", "downloadSingleDeviceResource onFailure");
                downloadCallback.onFailure(i);
            }
        });
        if (b != null) {
            dkdVar.a(b);
        }
        dkdVar.b();
    }

    private DeviceDownloadSourceInfo b(String str) {
        LogUtil.a("DownloadResourceUtils", "isSetIndexAllDownloadResource");
        List<msx> c = mst.a().c(str);
        if (koq.c(c)) {
            msx msxVar = c.get(0);
            if (msxVar.d() != null && msxVar.d().size() > 0) {
                return c.get(0).d().get(0).d();
            }
            return c.get(0).j();
        }
        LogUtil.h("DownloadResourceUtils", "devicePluginInfoBeans is isEmpty");
        return null;
    }

    public void d(final DownloadCallback downloadCallback) {
        if (jbw.c(ApplicationContext.getContext())) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: cwv.4
                @Override // java.lang.Runnable
                public void run() {
                    if (jbw.d(ApplicationContext.getContext(), 2)) {
                        downloadCallback.onSuccess();
                        LogUtil.a("DownloadResourceUtils", "isDownloadMapSuccess is true");
                    } else {
                        LogUtil.h("DownloadResourceUtils", "isDownloadMapSuccess is false");
                        downloadCallback.onFailure(1000);
                    }
                }
            });
        } else if (ProductMapParseUtil.b(ApplicationContext.getContext())) {
            LogUtil.a("DownloadResourceUtils", "loadProductMapConfig is true");
            downloadCallback.onSuccess();
        } else {
            LogUtil.h("DownloadResourceUtils", "loadProductMapConfig is false");
            downloadCallback.onFailure(1001);
        }
    }

    public void c(final DownloadCallback downloadCallback) {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            downloadCallback.onFailure(1004);
            return;
        }
        DownloadManagerApi downloadManagerApi = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
        downloadManagerApi.addDownloadIndexAllCallBack(new DownloadResultCallBack() { // from class: cwv.1
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
            public void setDownloadStatus(int i, int i2) {
                if (i == 0) {
                    return;
                }
                LogUtil.a("DownloadResourceUtils", "setDownloadStatus status: ", Integer.valueOf(i));
                if (i == 1) {
                    mst.a().b();
                    downloadCallback.onSuccess();
                } else {
                    downloadCallback.onFailure(1002);
                    LogUtil.h("DownloadResourceUtils", "startDownloadResource  DOWNLOAD_TIME_OUT");
                }
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
            }
        });
        ReleaseLogUtil.e("DownloadResourceUtils", "downLoad index_all");
        downloadManagerApi.downloadIndexAll();
    }
}
