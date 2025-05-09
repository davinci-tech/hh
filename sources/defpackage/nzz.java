package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.reflect.TypeToken;
import com.huawei.datatype.DeviceCommand;
import com.huawei.devicepair.api.BasePairManagerApi;
import com.huawei.devicepair.model.StartPairOption;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@ApiDefine(uri = BasePairManagerApi.class)
@Singleton
/* loaded from: classes6.dex */
public class nzz implements BasePairManagerApi {
    private static final Set<String> c = Collections.unmodifiableSet(new HashSet<String>() { // from class: nzz.5
        {
            add("HUAWEI WATCH GT 4-");
        }
    });

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public boolean isSupportOobe(String str) {
        return false;
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void syncAccount(String str, int i) {
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void connectDevice(final Context context, final String str, boolean z, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        if (!z) {
            if (Build.VERSION.SDK_INT < 29 || !CommonUtil.br()) {
                PermissionUtil.b(context, PermissionUtil.PermissionType.PHONE_STATE, new CustomPermissionAction(context) { // from class: nzz.2
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        nzz.this.d(str, str2, iBaseResponseCallback);
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onDenied(String str3) {
                        super.onDenied(str3);
                        LogUtil.a("BasePairManagerApiImpl", "requestPermissions Permission onDenied");
                        iBaseResponseCallback.d(0, false);
                        nzz.this.c(iBaseResponseCallback, context);
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                        LogUtil.a("BasePairManagerApiImpl", "requestPermissions Permission onForeverDenied");
                        iBaseResponseCallback.d(0, false);
                        nzz.this.c(iBaseResponseCallback, context);
                    }
                });
                return;
            } else {
                d(str, str2, iBaseResponseCallback);
                return;
            }
        }
        d(str, str2, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        oae.c(BaseApplication.getContext());
        ReleaseLogUtil.e("BasePairManagerApiImpl", "enter startAddDevice():");
        String d = nzy.d(str2);
        int c2 = jfu.c(d);
        LogUtil.a("BasePairManagerApiImpl", "connectDevice, deviceProductType: ", Integer.valueOf(c2));
        DeviceParameter deviceParameter = new DeviceParameter();
        deviceParameter.setBluetoothType(nzy.a(c2));
        deviceParameter.setProductType(c2);
        deviceParameter.setDeviceNameInfo(d);
        deviceParameter.setIsSupportHeartRate(false);
        deviceParameter.setProductPin("");
        deviceParameter.setIsBand(jfu.h(c2));
        deviceParameter.setNameFilter(nzy.b(c2));
        deviceParameter.setMac(str);
        List<DeviceInfo> c3 = oae.c(BaseApplication.getContext()).c();
        if (obb.e(c2) || c3 == null || c3.isEmpty()) {
            LogUtil.a("BasePairManagerApiImpl", "connectedDeviceInfo is null");
            d(iBaseResponseCallback, deviceParameter, "");
        } else if (c3.size() == 1) {
            b(c2, deviceParameter, c3, iBaseResponseCallback);
        } else {
            a(c2, deviceParameter, c3, iBaseResponseCallback);
        }
    }

    private void b(int i, DeviceParameter deviceParameter, List<DeviceInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo deviceInfo = list.get(0);
        boolean c2 = cvt.c(i);
        boolean c3 = cvt.c(deviceInfo.getProductType());
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (!c3) {
            if (c2) {
                LogUtil.a("BasePairManagerApiImpl", "startAddDevice has one connected other device and want another aw70 device");
                d(iBaseResponseCallback, deviceParameter, "");
                return;
            } else {
                LogUtil.a("BasePairManagerApiImpl", "startAddDevice has one connected other device and want another other device");
                d(iBaseResponseCallback, deviceParameter, deviceIdentify);
                return;
            }
        }
        if (c3 && deviceInfo.getAutoDetectSwitchStatus() == 1) {
            if (c2) {
                LogUtil.a("BasePairManagerApiImpl", "startAddDevice has one connected aw70 device in run mode and want another aw70 device");
                d(iBaseResponseCallback, deviceParameter, deviceIdentify);
                return;
            } else {
                LogUtil.a("BasePairManagerApiImpl", "startAddDevice has one connected aw70 device in run mode and want another other device");
                d(iBaseResponseCallback, deviceParameter, "");
                return;
            }
        }
        if (c3 && deviceInfo.getAutoDetectSwitchStatus() == 0) {
            LogUtil.a("BasePairManagerApiImpl", "startAddDevice has one connected aw70 device and want another other device/aw70 device");
            d(iBaseResponseCallback, deviceParameter, deviceIdentify);
        } else {
            LogUtil.a("BasePairManagerApiImpl", "startAddDevice other condition");
        }
    }

    private void a(int i, DeviceParameter deviceParameter, List<DeviceInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        if (cvt.c(i)) {
            for (DeviceInfo deviceInfo : list) {
                if (cvt.c(deviceInfo.getProductType())) {
                    LogUtil.a("BasePairManagerApiImpl", "startAddDevice has one connected aw70 device and want another aw70 device");
                    d(iBaseResponseCallback, deviceParameter, deviceInfo.getDeviceIdentify());
                    return;
                }
            }
            return;
        }
        for (DeviceInfo deviceInfo2 : list) {
            if (!cvt.a(deviceInfo2.getProductType(), deviceInfo2.getAutoDetectSwitchStatus())) {
                LogUtil.a("BasePairManagerApiImpl", "startAddDevice has one connected device is other device");
                d(iBaseResponseCallback, deviceParameter, deviceInfo2.getDeviceIdentify());
                return;
            }
        }
    }

    private void d(final IBaseResponseCallback iBaseResponseCallback, DeviceParameter deviceParameter, String str) {
        oae.c(BaseApplication.getContext()).d(deviceParameter, str, new IAddDeviceStateAIDLCallback.Stub() { // from class: nzz.1
            @Override // com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback
            public void onAddDeviceState(int i) throws RemoteException {
                LogUtil.h("BasePairManagerApiImpl", "BT open error, state: ", Integer.valueOf(i));
                if (i == 1 || i == 2) {
                    iBaseResponseCallback.d(0, false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final IBaseResponseCallback iBaseResponseCallback, final Context context) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(context.getString(R.string._2130842089_res_0x7f0211e9)).e(context.getString(R.string._2130837673_res_0x7f0200a9)).cyV_(context.getString(R.string._2130841425_res_0x7f020f51).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: nzz.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nsn.ak(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(context.getString(R.string._2130839505_res_0x7f0207d1).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: nzz.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("BasePairManagerApiImpl", "handlePermission negative.");
                iBaseResponseCallback.d(0, false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCanceledOnTouchOutside(false);
        a2.show();
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void disconnectDevice(String str, String str2, boolean z) {
        if (nzy.c(str2)) {
            String d = nzy.d(str2);
            obi.a().a(jfu.c(d), d);
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        oae.c(BaseApplication.getContext()).e(arrayList, z);
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public List<bgd> listDevice(Context context) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "BasePairManagerApiImpl");
        ArrayList arrayList = new ArrayList(16);
        if (!deviceList.isEmpty()) {
            for (DeviceInfo deviceInfo : deviceList) {
                bgd bgdVar = new bgd();
                bgdVar.c("5");
                bgdVar.a("5");
                bgg bggVar = new bgg();
                bggVar.a(String.valueOf(deviceInfo.getDeviceBluetoothType()));
                bggVar.c(deviceInfo.getDeviceName());
                bggVar.d(deviceInfo.getDeviceIdentify());
                if (jfu.h(deviceInfo.getProductType())) {
                    bggVar.i("wear_band");
                } else {
                    bggVar.i("wear_watch");
                }
                bggVar.e(String.valueOf(deviceInfo.getProductType()));
                bgc bgcVar = new bgc();
                bgcVar.e(deviceInfo.getDeviceIdentify());
                bgcVar.c(deviceInfo.getBluetoothVersion());
                bgcVar.a(deviceInfo.getDeviceModel());
                bgcVar.b(deviceInfo.getHiLinkDeviceId());
                bgcVar.d(deviceInfo.getSecurityDeviceId());
                bgcVar.g(deviceInfo.getSoftVersion());
                bgcVar.f(deviceInfo.getEmuiVersion());
                bgcVar.h(String.valueOf(deviceInfo.getProductType()));
                bggVar.c(bgcVar);
                c(deviceInfo, bggVar, context);
                bgdVar.b(bggVar);
                arrayList.add(bgdVar);
            }
        }
        return arrayList;
    }

    private void c(DeviceInfo deviceInfo, bgg bggVar, Context context) {
        Drawable drawable;
        Bitmap loadImageByImageName;
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.c(deviceInfo.getProductType()).ad());
        String a2 = cwf.a(pluginInfoByUuid, 2, deviceInfo);
        if (new File(msj.a().d(pluginInfoByUuid.e()) + File.separator + pluginInfoByUuid.e() + File.separator + "img" + File.separator + a2 + ".png").exists() && (loadImageByImageName = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, a2)) != null) {
            LogUtil.h("BasePairManagerApiImpl", "deviceBitmapTemp is not null");
            bggVar.b(nzy.cTv_(loadImageByImageName));
        } else {
            if (juu.j(deviceInfo.getProductType())) {
                drawable = context.getResources().getDrawable(R.mipmap._2131820664_res_0x7f110078);
            } else {
                drawable = context.getResources().getDrawable(R.mipmap._2131820674_res_0x7f110082);
            }
            bggVar.b(nzy.cTv_(nrf.cHF_(drawable)));
        }
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public String getOobePath(String str) {
        cuw c2 = jfu.c(jfu.c(str));
        if (c2 != null) {
            String pluginIndexOobe = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginIndexOobe(c2.ad());
            try {
                File filesDir = BaseApplication.getContext().getFilesDir();
                if (filesDir != null) {
                    return filesDir.getCanonicalPath() + File.separator + "plugins" + File.separator + pluginIndexOobe + File.separator + pluginIndexOobe + File.separator + "declaration";
                }
            } catch (IOException unused) {
                LogUtil.b("BasePairManagerApiImpl", "getSourcePath catch IOException");
            }
        }
        return "";
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void sendDeclarationInfoList(String str, String str2) {
        List<bge> list = (List) HiJsonUtil.b(str2, new TypeToken<List<bge>>() { // from class: nzz.6
        }.getType());
        if (CollectionUtils.d(list)) {
            LogUtil.h("BasePairManagerApiImpl", "sendDeclarationInfoList, infos is empty");
            return;
        }
        String sb = e(list).toString();
        LogUtil.a("BasePairManagerApiImpl", "getDeviceCommand commanHex:" + sb);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setmIdentify(str);
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(48);
        deviceCommand.setDataContent(cvx.a(sb));
        deviceCommand.setDataLen(cvx.a(sb).length);
        jfq.c().b(deviceCommand);
        LogUtil.a("BasePairManagerApiImpl", "sendUserSettingCommand" + deviceCommand.toString());
    }

    private StringBuilder e(List<bge> list) {
        StringBuilder sb = new StringBuilder(16);
        if (CollectionUtils.d(list)) {
            sb.append(cvx.e(129) + cvx.e(0));
            return sb;
        }
        for (bge bgeVar : list) {
            if (bgeVar != null && !TextUtils.isEmpty(bgeVar.b())) {
                String c2 = cvx.c(bgeVar.b());
                String str = cvx.e(3) + cvx.e(c2.length() / 2) + c2;
                String e = cvx.e(bgeVar.e());
                String str2 = cvx.e(4) + cvx.e(e.length() / 2) + e;
                String c3 = cvx.c(bgeVar.a());
                String str3 = cvx.e(5) + cvx.e(c3.length() / 2) + c3;
                String c4 = cvx.c(bgeVar.d());
                String str4 = str + str2 + str3 + (cvx.e(6) + cvx.e(c4.length() / 2) + c4);
                sb.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + cvx.e(str4.length() / 2) + str4);
            }
        }
        String str5 = cvx.e(129) + cvx.d(sb.length() / 2);
        if (sb.length() > 0) {
            sb.insert(0, str5);
        }
        return sb;
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public boolean isSupportSyncAccount(String str) {
        DeviceCapability e = cvs.e(str);
        if (e == null) {
            return false;
        }
        if (e.isSupportSyncAccount() && !Utils.i()) {
            LogUtil.a("BasePairManagerApiImpl", "empty account");
            return false;
        }
        return e.isSupportSyncAccount();
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public boolean isSupportHwSynergy(String str) {
        return jfu.o(jpt.e(str, "BasePairManagerApiImpl").getProductType()) && cvz.c();
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void hwSynergy(String str, int i) {
        cvz.c(Boolean.valueOf(i == 1));
        jrg.a(i == 1);
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public boolean isSupportKeepAlive() {
        return lcu.e();
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void sendOobeStatus(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BasePairManagerApiImpl", "sendOobeStatus: ", Boolean.valueOf(jfq.c().d("oobeCreated", deviceInfo, 0, null)));
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void startPair(Context context, StartPairOption startPairOption) {
        AppRouter.zi_(Uri.parse(startPairOption.creatDeepLink())).b(268435456).c(a(context));
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public boolean isSupportH5Pair(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("BasePairManagerApiImpl", "isSupportH5Pair bluetoothName isEmpty");
            return false;
        }
        Iterator<String> it = c.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = str.toUpperCase(Locale.ROOT).contains(it.next());
        }
        ReleaseLogUtil.d("BasePairManagerApiImpl", "isSupportH5Pair is ", Boolean.valueOf(z));
        return z && !CommonUtil.bv();
    }

    public static Context a(Context context) {
        if (context instanceof Activity) {
            return context;
        }
        Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        ReleaseLogUtil.d("BasePairManagerApiImpl", "getActivity topActivity ", wa_);
        return wa_ == null ? com.huawei.haf.application.BaseApplication.e() : wa_;
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void onSuccessPair(Context context, String str) {
        SharedPreferenceManager.c("privacy_center", "account_device", String.valueOf(System.currentTimeMillis()));
        DeviceInfo e = jpt.e(str, "BasePairManagerApiImpl");
        if (e != null) {
            e(context, e, str);
            d(e);
            e(str);
        }
        c(str);
        jpp.i(e);
        jpp.e();
        gmn.b(context);
    }

    private void e(Context context, DeviceInfo deviceInfo, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("productId", str);
        hashMap.put("deviceId", Integer.valueOf(jfu.c(deviceInfo.getProductType()).o()));
        LogUtil.a("BasePairManagerApiImpl", "PluginAchieve setEvent productID:", iyl.d().e(str));
        mcv.d(context).c(context, String.valueOf(1500), hashMap);
    }

    private void d(DeviceInfo deviceInfo) {
        GuideInteractors guideInteractors = new GuideInteractors(BaseApplication.getContext());
        guideInteractors.d(true);
        if (obb.e(deviceInfo.getProductType())) {
            return;
        }
        guideInteractors.e(true);
        guideInteractors.b(true);
        guideInteractors.a(true);
    }

    private void e(String str) {
        LogUtil.a("BasePairManagerApiImpl", "enter setDeviceActiveTime");
        if (SharedPreferenceManager.d(BaseApplication.getContext(), String.valueOf(2000000)).contains(bgv.e(str))) {
            LogUtil.a("BasePairManagerApiImpl", "setDeviceActiveTime device already active.");
            return;
        }
        StorageParams storageParams = new StorageParams();
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(2000000), bgv.e(str), String.valueOf(currentTimeMillis), storageParams);
    }

    private void c(String str) {
        if (obb.d(jpo.c(str))) {
            AppBundle.e().preDownloadPlugins(BaseApplication.getContext(), Collections.singletonList("PluginWearAbility"), true, true);
        }
        if (obb.c()) {
            AppBundle.e().preDownloadPlugins(BaseApplication.getContext(), Collections.singletonList("PluginHiAiEngine"), true, true);
        }
        LogUtil.a("BasePairManagerApiImpl", "background downloading");
    }

    @Override // com.huawei.devicepair.api.BasePairManagerApi
    public void removeDevice(String str) {
        obb.a(str);
    }
}
