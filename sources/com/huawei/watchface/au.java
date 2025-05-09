package com.huawei.watchface;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.manager.WatchFaceKaleidoscopeManager;
import com.huawei.watchface.manager.WatchFacePhotoAlbumManager;
import com.huawei.watchface.manager.WatchFaceWearManager;
import com.huawei.watchface.mvp.model.datatype.DownloadQueryBean;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.TryoutBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.info.VipPayInfoCoupons;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.LanguageUtils;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.DelectUserDesignationWatchFaceCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.callback.PluginOperationAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes7.dex */
public class au implements PluginOperationAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static volatile au f10909a;
    private Context b;
    private Map<Integer, Integer> c;
    private Map<String, Integer> d;

    public static au a(Context context) {
        if (f10909a == null) {
            synchronized (au.class) {
                if (f10909a == null) {
                    f10909a = new au(context);
                }
            }
        }
        return f10909a;
    }

    private au(Context context) {
        if (context != null) {
            this.b = context.getApplicationContext();
        } else {
            this.b = Environment.getApplicationContext();
        }
        this.c = new HashMap();
        this.d = new HashMap();
        a();
    }

    private void a() {
        this.c.put(1, 258);
        this.c.put(2, 257);
        this.c.put(3, 259);
        this.d.put("km", 1);
        this.d.put("s", 0);
        this.d.put("cal", 2);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void setWatchFaceCallback(OperateWatchFaceCallback operateWatchFaceCallback, boolean z) {
        a(operateWatchFaceCallback, z);
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).setWatchFaceCallback(operateWatchFaceCallback);
        WatchFaceKaleidoscopeManager.getInstance(Environment.getApplicationContext()).setWatchFaceCallback(operateWatchFaceCallback);
        WatchFaceWearManager.getInstance(Environment.getApplicationContext()).setWatchFaceCallback(operateWatchFaceCallback);
    }

    private void a(OperateWatchFaceCallback operateWatchFaceCallback, boolean z) {
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).setWatchFaceCallback(operateWatchFaceCallback, z);
        s.a(Environment.getApplicationContext()).a(operateWatchFaceCallback);
        l.a().a(operateWatchFaceCallback);
        t.a().a(operateWatchFaceCallback);
        k.a().a(operateWatchFaceCallback);
        HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).setRegisterH5Callback(operateWatchFaceCallback);
        az.a().a(operateWatchFaceCallback);
        n.a(Environment.getApplicationContext()).a(operateWatchFaceCallback);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void updateWatchFaceCallback(OperateWatchFaceCallback operateWatchFaceCallback, boolean z) {
        a(operateWatchFaceCallback, z);
        if (z) {
            return;
        }
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).setWatchFaceCallback(operateWatchFaceCallback);
        WatchFaceKaleidoscopeManager.getInstance(Environment.getApplicationContext()).setWatchFaceCallback(operateWatchFaceCallback);
        WatchFaceWearManager.getInstance(Environment.getApplicationContext()).setWatchFaceCallback(operateWatchFaceCallback);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String downloadAndInstallWatchFace(DownloadQueryBean downloadQueryBean) {
        t.a().g(downloadQueryBean.getHitopId(), downloadQueryBean.getVersion());
        return k.a().a(downloadQueryBean);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceInfo() {
        return HwWatchFaceManager.getInstance(Environment.getApplicationContext()).getWatchFaceInfo();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getLocale() {
        return LanguageUtils.a(true);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getIsoCode() {
        return HwWatchFaceApi.getInstance(this.b).getCommonCountryCode();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getScreenSize() {
        return HwWatchFaceBtManager.getInstance(this.b).getWatchFaceScreen(true, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getBuildNumber() {
        return HwWatchFaceApi.getInstance(this.b).getSoftVersion();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchThemeVersion() {
        return HwWatchFaceBtManager.getInstance(this.b).getWatchFaceMaxVersion(false, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchOtherThemeVersion() {
        return HwWatchFaceManager.getInstance(this.b).getWatchOtherThemeVersion();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getPhoneWatchType() {
        return HwWatchFaceApi.getInstance(this.b).getDeviceModel();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getCurrentWatchFaceId() {
        return HwWatchFaceManager.getInstance(this.b).getCurrentWatchFaceId();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getPreWatchFaceIdStore() {
        return HwWatchFaceManager.getInstance(this.b).getPreWatchFaceIdStore();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceIdStore() {
        return HwWatchFaceManager.getInstance(this.b).getWatchFaceIdStore();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void setDefaultWatchFace(String str, String str2, String str3) {
        HwWatchFaceManager.getInstance(this.b).setDefaultWatchFace(str, str2, str3);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void cancelInstallWatchFace(String str, String str2) {
        HwWatchFaceManager.getInstance(this.b).cancelInstallWatchFaceByH5(str, str2);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void deleteWatchFace(String str, String str2) {
        HwWatchFaceManager.getInstance(this.b).deleteWatchFace(str, str2);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void setWatchFaceDeleteId(String str, String str2) {
        HwWatchFaceManager.getInstance(this.b).setWatchFaceDeleteId(str, str2);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceUrlBase() {
        return WatchFaceHttpUtil.l();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public int getWatchFaceInstallState() {
        return HwWatchFaceManager.getInstance(Environment.getApplicationContext()).getWatchFaceInstallState();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void getWatchFaceNames(String str) {
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).getWatchFaceNames(str);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void choosePicToClip() {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).k();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void choosePicToH5Clip() {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).l();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void chooseWearToClip() {
        WatchFaceWearManager.getInstance(Environment.getApplicationContext()).f();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void transferWatchFaceFile(Uri uri, WebViewActivity webViewActivity) {
        o.a(Environment.getApplicationContext()).a(uri, webViewActivity);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void openSystemFileManager(WebViewActivity webViewActivity) {
        o.a(Environment.getApplicationContext()).b(webViewActivity);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void showInstallProgress(int i, WebViewActivity webViewActivity) {
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).showInstallProgress(i, webViewActivity);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void obtainWidgetColor(String str, String str2, String str3, String str4, String str5) {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).a(str, str2, str3, str4, str5);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void getWatchFaceThemeAlbumInfo(String str, String str2, String str3, String str4) {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).getWatchFaceInfo(str, str2, str3, str4);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void syncWatchFaceThemeAlbumInfo(String str) {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).a(str);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void getWatchFaceThemeWearInfo(String str, String str2, String str3, String str4) {
        HwLog.i("Opera_PluginOperationAdapterImpl", "getWatchFaceThemeWearInfo");
        WatchFaceWearManager.getInstance(Environment.getApplicationContext()).getWatchFaceInfo(str, str2, str3, str4);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void saveWatchFaceThemeAlbumInfo(String str) {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).saveWatchFaceInfo(str);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void applyWatchFaceThemeAlbumInfo(String str) {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).f(str);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void saveWatchFaceThemeWearInfo(String str) {
        WatchFaceWearManager.getInstance(Environment.getApplicationContext()).saveWatchFaceInfo(str);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getAlbumPackageName() {
        return WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).j();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getVideoAlbumPackageName() {
        return HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getVideoPackageName();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void getWatchFacePayInfo(InstallWatchFaceBean installWatchFaceBean) {
        s.a(Environment.getApplicationContext()).a(installWatchFaceBean);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void payForVip(BaseReq baseReq, bf bfVar) {
        s.a(Environment.getApplicationContext()).a(baseReq, bfVar);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void payForVipNew(VipPayInfoCoupons vipPayInfoCoupons, bf bfVar, bg bgVar) {
        s.a(Environment.getApplicationContext()).a(vipPayInfoCoupons, bfVar, bgVar);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void uploadPayResult(String str, String str2) {
        s.a(Environment.getApplicationContext()).a(str, str2);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void uploadSubscriptionPayResult(PayResultInfo payResultInfo) {
        s.a(Environment.getApplicationContext()).b(payResultInfo);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void uploadPayEvent(String str) {
        s.a(Environment.getApplicationContext()).d(str);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getTokenAndDeviceType() {
        return n.a(Environment.getApplicationContext()).g();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceSortState() {
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceSupportInfo();
        return (watchFaceSupportInfo == null || !watchFaceSupportInfo.isWatchFaceSort()) ? "0" : "1";
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void setWatchFaceSortList(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("Opera_PluginOperationAdapterImpl", "str is null");
            return;
        }
        if (!HwWatchFaceManager.getInstance(Environment.getApplicationContext()).isBtConnect()) {
            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).notifyH5DeviceIsBreak();
            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).dismissStartSaveDialog();
            return;
        }
        HwLog.i("Opera_PluginOperationAdapterImpl", "setWatchFaceSortList str is " + str);
        String[] split = str.split(",");
        ArrayList<WatchResourcesInfo> arrayList = new ArrayList<>(split.length);
        for (String str2 : split) {
            WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
            watchResourcesInfo.setWatchInfoId(str2);
            watchResourcesInfo.setVipFreeWatchFace(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getVipFreeFromCache(str2));
            arrayList.add(watchResourcesInfo);
        }
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).startSaveDialog();
        HwLog.i("Opera_PluginOperationAdapterImpl", "setWatchFaceSortList watchResourcesInfos is " + arrayList.toString());
        HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).sortWatchList(arrayList, new IBaseResponseCallback() { // from class: com.huawei.watchface.au$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                au.a(i, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(int i, Object obj) {
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).dismissStartSaveDialog();
        if (i == 101) {
            HwLog.i("Opera_PluginOperationAdapterImpl", "send watchface is success");
        } else {
            HwLog.i("Opera_PluginOperationAdapterImpl", "send watchface is failure");
            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).showToast();
        }
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceSortList() {
        ArrayList<WatchResourcesInfo> allWatchInfo = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getAllWatchInfo();
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<WatchResourcesInfo> it = allWatchInfo.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().getWatchInfoId());
            stringBuffer.append(",");
        }
        return stringBuffer.toString();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceData() {
        return dp.d(Environment.getApplicationContext());
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void deleteDesignationUserWatchFace(String str, String str2, DelectUserDesignationWatchFaceCallback delectUserDesignationWatchFaceCallback) {
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).deleteDesignationWatchFace(str, str2, delectUserDesignationWatchFaceCallback);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void deleteDesignationWatchFace(String str, String str2) {
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).h5deleteWatchFace(str, str2);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceSignBean() {
        return s.a(Environment.getApplicationContext()).g();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void setAlbumWatchFaceGoBack() {
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).i();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void setEnteringWatchFaceAlbum(boolean z) {
        dp.a("EnteringWatchFaceAlbum", z, "socialsharedpreference");
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public boolean isEnterWatchFaceAlbum() {
        return dp.a("EnteringWatchFaceAlbum", "socialsharedpreference", false);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void setEnterWatchFaceMarket(boolean z) {
        dp.a("EnteringWatchFaceMarket", z, "socialsharedpreference");
        if (z) {
            WatchFaceKaleidoscopeManager.getInstance(Environment.getApplicationContext()).f();
        }
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public boolean isBluetoothConnected() {
        return HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceInfo() != null;
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFaceDownloadData() {
        return p.a(Environment.getApplicationContext()).k();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public int getWatchFaceDownloadItemNum() {
        return p.a(Environment.getApplicationContext()).l();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String sendContinueDownloadWatchFace(int i, DownloadQueryBean downloadQueryBean) {
        return p.a(Environment.getApplicationContext()).a(i, downloadQueryBean);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void releaseWatchFaceLoginHelper() {
        ap.a();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void installTryoutWatchFace(TryoutBean tryoutBean) {
        t.a().a(tryoutBean);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void cancelTryOutWatchFace(String str, String str2) {
        t.a().a(str, str2);
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getTryOutWatchFacePackageName() {
        return t.a().e();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getKaleidoscopePackageName() {
        return WatchFaceKaleidoscopeManager.getInstance(Environment.getApplicationContext()).d();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWearPackageName() {
        return HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWearPackageName();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public void reselectionWearTransmit() {
        WatchFaceWearManager.getInstance(Environment.getApplicationContext()).g();
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getWatchFileType() {
        return "hwt";
    }

    @Override // com.huawei.watchface.utils.callback.PluginOperationAdapter
    public String getFirmware() {
        return "6.0.1";
    }
}
