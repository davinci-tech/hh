package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.wifi.lib.db.dbtable.DeviceListManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.DeviceDetailInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAllDeviceRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class ogr {
    private static final List<String> b = new ArrayList<String>() { // from class: ogr.2
        {
            add("007B");
            add("M00D");
            add("M00F");
        }
    };
    private CommonDialog21 d;
    private String e = "";

    private void c(String str) {
        if (BaseApplication.getActivity() == null) {
            LogUtil.h("SubUserDeviceHandler", "getActivity() is null please check the sActivityMonitor");
            return;
        }
        if (BaseApplication.getActivity().isFinishing() || BaseApplication.getActivity().isDestroyed()) {
            LogUtil.h("SubUserDeviceHandler", "showLoadingDialog error: activity is finishing");
            return;
        }
        CommonDialog21 commonDialog21 = this.d;
        if (commonDialog21 != null && commonDialog21.isShowing()) {
            LogUtil.h("SubUserDeviceHandler", "mLoadingDialog.isShowing can not show again");
            return;
        }
        if (this.d == null) {
            new CommonDialog21(BaseApplication.getActivity(), R.style.app_update_dialogActivity);
            this.d = CommonDialog21.a(BaseApplication.getActivity());
        }
        this.d.setCancelable(false);
        this.d.e(str);
        this.d.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CommonDialog21 commonDialog21 = this.d;
        boolean z = commonDialog21 != null && commonDialog21.isShowing();
        if (BaseApplication.getActivity() == null) {
            LogUtil.h("SubUserDeviceHandler", "getActivity() is null please check the sActivityMonitor");
        } else {
            if (BaseApplication.getActivity().isFinishing() || !z) {
                return;
            }
            this.d.dismiss();
            this.d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        LogUtil.a("SubUserDeviceHandler", "gotoUserInfo");
        if (context == null) {
            LogUtil.h("SubUserDeviceHandler", "gotoUserInfo context is null");
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.putExtra("device", "wifi");
        intent.putExtra("productId", str);
        intent.putExtra("auth_device_id", str);
        intent.setClassName("com.huawei.health", "com.huawei.ui.main.stories.me.activity.BindUserInfoActivity");
        if (!TextUtils.isEmpty(this.e)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.e);
            contentValues.put("productId", str);
            intent.putExtra("commonDeviceInfo", contentValues);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("SubUserDeviceHandler", "gotoUserInfo ActivityNotFoundException");
        }
    }

    public void c(final Context context, final String str) {
        if (context == null) {
            LogUtil.h("SubUserDeviceHandler", "getShareDeviceList context is null");
            return;
        }
        c(context.getString(R.string._2130841415_res_0x7f020f47));
        if (!((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(str)) {
            LogUtil.a("SubUserDeviceHandler", "getShareDeviceList: no product resource");
            a(context, str, 3);
        } else {
            jbs.a(context).d(new ICloudOperationResult<WifiDeviceGetAllDeviceRsp>() { // from class: ogr.4
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void operationResult(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, String str2, boolean z) {
                    if (z && wifiDeviceGetAllDeviceRsp != null && ogr.this.b(wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList())) {
                        List e = ogr.this.e(wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList());
                        if (ogr.this.b((List<DeviceDetailInfo>) e)) {
                            ogr.this.d(e, 2);
                            ogr.this.a(context, str);
                        } else {
                            LogUtil.h("SubUserDeviceHandler", "getShareDeviceList authDevices is null");
                        }
                    } else {
                        LogUtil.h("SubUserDeviceHandler", "getShareDeviceList fail");
                    }
                    ogr.this.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<DeviceDetailInfo> list, int i) {
        if (!b(list)) {
            LogUtil.h("SubUserDeviceHandler", "saveDevice devices is null");
            return;
        }
        for (DeviceDetailInfo deviceDetailInfo : list) {
            if (deviceDetailInfo != null && deviceDetailInfo.getDevInfo() != null && ctm.b(deviceDetailInfo.getDevInfo().getProdId())) {
                String sn = deviceDetailInfo.getDevInfo() != null ? deviceDetailInfo.getDevInfo().getSn() : "";
                if (!TextUtils.isEmpty(sn)) {
                    MeasurableDevice c = ceo.d().c(sn, false);
                    ctk ctkVar = new ctk();
                    ctkVar.a(deviceDetailInfo);
                    ctkVar.setAutoDevice(false);
                    ctkVar.b().d(i);
                    e(ctkVar, c == null);
                }
            }
        }
    }

    private boolean e(ctk ctkVar, boolean z) {
        dcz e = e(ctkVar.b().f());
        if (e != null) {
            ctkVar.setProductId(e.t());
            ctkVar.setKind(HealthDevice.HealthDeviceKind.HDK_WEIGHT);
            ctkVar.setAutoDevice(false);
            if (z) {
                if (TextUtils.isEmpty(this.e)) {
                    this.e = ctkVar.getUniqueId();
                }
                return e(ctkVar, e);
            }
            return a(ctkVar, e);
        }
        LogUtil.h("SubUserDeviceHandler", "saveDevice productInfo is null");
        return false;
    }

    private boolean b(String str, int i) {
        if (!ctv.d(cpp.a())) {
            LogUtil.b("SubUserDeviceHandler", "updateResourceFileWithRetry fail: no network");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SubUserDeviceHandler", "updateResourceFile: product id is null");
            return false;
        }
        if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(str)) {
            LogUtil.a("SubUserDeviceHandler", "updateResourceFile: isPluginAvaiable is true");
            return false;
        }
        if (i > 0) {
            return true;
        }
        LogUtil.b("SubUserDeviceHandler", "updateResourceFileWithRetry fail");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final String str, final int i) {
        LogUtil.a("SubUserDeviceHandler", "updateResourceFileWithRetry ", Integer.valueOf(i));
        if (!b(str, i)) {
            LogUtil.h("SubUserDeviceHandler", "can not updateResourceFile");
            e();
        } else {
            cpe.c(str, new PullListener() { // from class: ogr.1
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    if (msoVar == null || msoVar.i() == 0) {
                        return;
                    }
                    LogUtil.a("SubUserDeviceHandler", "updateResourceFile onPullingChange ", Integer.valueOf(msoVar.i()));
                    if (msoVar.i() == 1) {
                        ogr.this.c(context, str);
                    } else {
                        ogr.this.a(context, str, i - 1);
                    }
                }
            });
        }
    }

    private boolean e(ctk ctkVar, dcz dczVar) {
        return cjx.e().b(ctkVar.getProductId(), dczVar.s(), ctkVar, new IDeviceEventHandler() { // from class: ogr.3
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
                LogUtil.a("SubUserDeviceHandler", "saveDevice onDeviceFound");
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
                LogUtil.a("SubUserDeviceHandler", "saveDevice failed code ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
                LogUtil.a("SubUserDeviceHandler", "saveDevice code ", Integer.valueOf(i));
            }
        });
    }

    private boolean a(ctk ctkVar, dcz dczVar) {
        return cjx.e().a(ctkVar.getProductId(), dczVar.s(), ctkVar, new IDeviceEventHandler() { // from class: ogr.5
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
                LogUtil.h("SubUserDeviceHandler", "updataDevice onScanFailed code ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
                LogUtil.a("SubUserDeviceHandler", "updataDevice code ", Integer.valueOf(i));
            }
        });
    }

    private dcz e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("SubUserDeviceHandler", "getProductInfo prodId is null");
            return null;
        }
        LogUtil.a("SubUserDeviceHandler", "getProductInfo prodId is ", str);
        dkz e = DeviceListManager.c(cpp.a()).e(str);
        dcz b2 = e != null ? b(e.d) : null;
        if (b2 != null) {
            return b2;
        }
        LogUtil.h("SubUserDeviceHandler", "getProductInfo The product obtained from Table is empty");
        String t = cpa.t(str);
        if (TextUtils.isEmpty(t)) {
            LogUtil.h("SubUserDeviceHandler", "getProductInfo productId is null");
            return b2;
        }
        return b(t);
    }

    private dcz b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("SubUserDeviceHandler", "getProduct productId is null");
            return null;
        }
        return ResourceManager.e().d(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<DeviceDetailInfo> e(List<DeviceDetailInfo> list) {
        if (koq.b(list)) {
            LogUtil.h("SubUserDeviceHandler", "filterDetailInfoList originDevices is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (DeviceDetailInfo deviceDetailInfo : list) {
            if (d(deviceDetailInfo)) {
                arrayList.add(deviceDetailInfo);
            }
        }
        return arrayList;
    }

    private boolean d(DeviceDetailInfo deviceDetailInfo) {
        if (deviceDetailInfo == null || deviceDetailInfo.getDevInfo() == null) {
            LogUtil.h("SubUserDeviceHandler", "isDeviceInfoValid device is null or device.getDevInfo() is null");
            return false;
        }
        if (TextUtils.isEmpty(deviceDetailInfo.getDevId())) {
            LogUtil.h("SubUserDeviceHandler", "isDeviceInfoValid device.getDevId() is null");
            return false;
        }
        String prodId = deviceDetailInfo.getDevInfo().getProdId();
        if (TextUtils.isEmpty(prodId)) {
            LogUtil.h("SubUserDeviceHandler", "isDeviceInfoValid device.getDevInfo().getProdId() is null");
            return false;
        }
        if (!b.contains(prodId) || !koq.b(deviceDetailInfo.getServices())) {
            return true;
        }
        LogUtil.h("SubUserDeviceHandler", "isDeviceInfoValid device.getServices() is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(List<DeviceDetailInfo> list) {
        if (koq.c(list)) {
            return true;
        }
        LogUtil.h("SubUserDeviceHandler", "hasDevice devices is null");
        return false;
    }
}
