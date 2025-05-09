package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.watchface.api.HwWatchFaceAdapter;
import com.huawei.watchface.mvp.model.datatype.TransFileInfo;
import com.huawei.watchface.mvp.model.datatype.TransferBusiType;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IFileTransferStateCallback;
import com.huawei.watchface.utils.callback.ILoginCallback;
import com.huawei.watchface.utils.callback.IPhotoFileCallback;
import com.huawei.watchface.utils.callback.WatchFaceHealthResponseListener;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jlv extends cuj implements HwWatchFaceAdapter {
    private static final byte[] b = new byte[0];
    private static jlv c;
    private jfq d;

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void commonTransferFile(String str, String str2, int i, IFileTransferStateCallback iFileTransferStateCallback) {
    }

    private jlv() {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "WatchFaceAdapterImpl init");
        this.d = jfq.c();
    }

    public static jlv c() {
        jlv jlvVar;
        synchronized (b) {
            if (c == null) {
                c = new jlv();
            }
            jlvVar = c;
        }
        return jlvVar;
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public Map<String, Object> getHealthSettingInfo() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("developer", SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch"));
        hashMap.put("appWatchFace", SharedPreferenceManager.b(BaseApplication.getContext(), "APP_WATCHFACE", "app_watchface_change_url"));
        return hashMap;
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void shareLink(String str) {
        LogUtil.a("WatchFaceAdapterImpl", "shareLink enter.");
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("shareUrlContent");
            String string2 = jSONObject.getString("shareTitleContent");
            String string3 = jSONObject.getString("shareTextContent");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                String string4 = jSONObject.getString("imageUrl");
                Bitmap bGs_ = !TextUtils.isEmpty(string4) ? jei.bGs_(string4) : null;
                fdu fduVar = new fdu(2);
                fduVar.a(string3);
                fduVar.awp_(bGs_);
                fduVar.f(string);
                fduVar.c(string2);
                String string5 = jSONObject.getString("shareModule");
                if (TextUtils.isEmpty(string5)) {
                    string5 = AnalyticsValue.SHARE_1140001.value();
                }
                fduVar.b(string5);
                fduVar.c(false);
                ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, BaseApplication.getActivity());
                return;
            }
            ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "shareLink, param is invalid");
        } catch (JSONException e) {
            ReleaseLogUtil.c("DEVMGR_WatchFaceAdapterImpl", "shareLink, JSONException:", ExceptionUtils.d(e));
        }
    }

    @Override // defpackage.cuj, com.huawei.watchface.api.HwWatchFaceAdapter
    public String getCommonCountryCode() {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "commonCountryCode is: ", GRSManager.a(BaseApplication.getContext()).c());
        return GRSManager.a(BaseApplication.getContext()).c();
    }

    @Override // defpackage.cuj, com.huawei.watchface.api.HwWatchFaceAdapter
    public Map<String, String> getDeviceInfo() {
        HashMap hashMap = new HashMap(16);
        if (jfq.c() == null) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "getDeviceInfo sDeviceManager is null");
            return hashMap;
        }
        DeviceInfo a2 = jpt.a("WatchFaceAdapterImpl");
        String str = WatchFaceUtil.isSupportMyWatch(a2) ? "1" : "0";
        if (a2 != null && a2.getDeviceConnectState() == 2) {
            hashMap.put("device_Identify", a2.getDeviceIdentify());
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, a2.getDeviceModel());
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION, a2.getSoftVersion());
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, a2.getDeviceName());
            hashMap.put("isSupportMyWatch", str);
        }
        return hashMap;
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public List<Map<String, String>> getDisableWearInfo() {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "getDisableWearInfo enter.");
        ArrayList arrayList = new ArrayList(16);
        Iterator<cpm> it = jfv.a().iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            if (next.b() != 1 || next.e() != 2) {
                d(next.a(), arrayList);
            }
        }
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "getDisableWearInfo, size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static void d(String str, List<Map<String, String>> list) {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "buildDeviceInfoMap enter.");
        DeviceInfo b2 = jpt.b(str, "WatchFaceAdapterImpl");
        HashMap hashMap = new HashMap(16);
        if (b2 != null) {
            hashMap.put("device_Identify", b2.getDeviceIdentify());
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, b2.getDeviceModel());
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION, b2.getSoftVersion());
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, b2.getDeviceName());
            hashMap.put("last_connected_time", String.valueOf(b2.getLastConnectedTime()));
            list.add(hashMap);
            return;
        }
        ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "buildDeviceInfoMap deviceInfo is null.");
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void loginByHealthHmsLite(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            cuj.loginByHealthHmsLite(context, new com.huawei.hwbasemgr.IBaseResponseCallback() { // from class: jlv.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    iBaseResponseCallback.onResponse(i, obj);
                }
            });
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void loginByHealthHms(Context context, final ILoginCallback iLoginCallback) {
        if (iLoginCallback != null) {
            cuj.loginByHealthHms(context, new com.huawei.login.ui.login.util.ILoginCallback() { // from class: jlv.3
                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginSuccess(Object obj) {
                    iLoginCallback.onLoginSuccess(obj);
                }

                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginFailed(Object obj) {
                    iLoginCallback.onLoginFailed(obj);
                }
            });
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void registerHealthResponseListener(WatchFaceHealthResponseListener watchFaceHealthResponseListener) {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "registerHealthResponseListener");
        jlo.d(BaseApplication.getContext()).d(watchFaceHealthResponseListener);
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void unRegisterHealthResponseListener() {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "unRegisterHealthResponseListener");
        jlo.d(BaseApplication.getContext()).j();
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void sendBluetoothCommand(int i, int i2, ByteBuffer byteBuffer) {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "sendWalletTlv,serviceId:", Integer.valueOf(i), ",commandId:", Integer.valueOf(i2));
        if (byteBuffer == null) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "sendBluetoothCommand myByteBuffer is null");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "sendWalletTlv");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        this.d.b(deviceCommand);
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void commonTransferFile(TransferBusiType transferBusiType, TransFileInfo transFileInfo, ParcelFileDescriptor parcelFileDescriptor, int i, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        Object[] objArr = new Object[5];
        objArr[0] = "parcelFileDescriptor entry.";
        objArr[1] = "parcelFileDescriptor is null ? ";
        objArr[2] = Boolean.valueOf(parcelFileDescriptor == null);
        objArr[3] = "file size : ";
        objArr[4] = Integer.valueOf(i);
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", objArr);
        if (iAppTransferFileResultAIDLCallback == null && transFileInfo == null) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "commonTransferFile param error");
            return;
        }
        jqj jqjVar = new jqj();
        jqjVar.a(transFileInfo.getFileName());
        jqjVar.d(transFileInfo.getFileType());
        jqjVar.e(i);
        jqjVar.j(transFileInfo.getPackageName());
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "transFileInfo.getRequestType().getValue() : ", Integer.valueOf(transFileInfo.getRequestType().getValue()));
        TransferFileReqType fileReqTypeByValue = TransferFileReqType.getFileReqTypeByValue(transFileInfo.getRequestType().getValue());
        jqjVar.c(fileReqTypeByValue);
        Object[] objArr2 = new Object[2];
        objArr2[0] = "requestType is null ? ";
        objArr2[1] = Boolean.valueOf(fileReqTypeByValue == null);
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", objArr2);
        TransferBusinessType busiTypeByValue = TransferBusinessType.getBusiTypeByValue(transferBusiType.getValue());
        if (TransferFileReqType.DEVICE_UNREGISTRATION.equals(fileReqTypeByValue)) {
            jlp.d().d(jqjVar.a(), iAppTransferFileResultAIDLCallback);
            return;
        }
        if (TransferFileReqType.DEVICE_REGISTRATION.equals(fileReqTypeByValue)) {
            jlp.d().b(jqjVar.a(), iAppTransferFileResultAIDLCallback);
            ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "mHwDeviceConfigManager commonTransferFile registration entry");
            this.d.bGV_(busiTypeByValue, jqjVar, jlp.d().e(), parcelFileDescriptor);
        } else {
            ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "mHwDeviceConfigManager commonTransferFile 5.40.2");
            this.d.bGV_(busiTypeByValue, jqjVar, jlp.d().a(iAppTransferFileResultAIDLCallback), parcelFileDescriptor);
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void commonTransferFile(TransferBusiType transferBusiType, TransFileInfo transFileInfo, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "commonTransferFile entry");
        if (iAppTransferFileResultAIDLCallback == null && transFileInfo == null) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "commonTransferFile param error");
            return;
        }
        jqj jqjVar = new jqj();
        jqjVar.e(transFileInfo.getAttentionTypes());
        TransferFileReqType fileReqTypeByValue = TransferFileReqType.getFileReqTypeByValue(transFileInfo.getRequestType().getValue());
        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "requestType is : ", fileReqTypeByValue, "attentionTypes size is: ", Integer.valueOf(jqjVar.a().size()));
        jqjVar.c(fileReqTypeByValue);
        jqjVar.a(transFileInfo.getFileName());
        jqjVar.d(transFileInfo.getFileType());
        jqjVar.f(transFileInfo.getFilePath());
        jqjVar.j(transFileInfo.getPackageName());
        jqjVar.e(transFileInfo.getSourceId());
        TransferBusinessType busiTypeByValue = TransferBusinessType.getBusiTypeByValue(transferBusiType.getValue());
        if (TransferFileReqType.DEVICE_UNREGISTRATION.equals(fileReqTypeByValue)) {
            jlp.d().d(jqjVar.a(), iAppTransferFileResultAIDLCallback);
            return;
        }
        if (TransferFileReqType.DEVICE_REGISTRATION.equals(fileReqTypeByValue)) {
            jlp.d().b(jqjVar.a(), iAppTransferFileResultAIDLCallback);
            ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "mHwDeviceConfigManager commonTransferFile registration entry");
            this.d.d(busiTypeByValue, jqjVar, jlp.d().e());
        } else {
            ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "mHwDeviceConfigManager commonTransferFile 5.40.2");
            this.d.d(busiTypeByValue, jqjVar, jlp.d().a(iAppTransferFileResultAIDLCallback));
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void startRequestFile(String str, int i, boolean z, final IPhotoFileCallback iPhotoFileCallback) {
        if (iPhotoFileCallback != null) {
            jqj jqjVar = new jqj();
            jqjVar.a(str);
            jqjVar.d(i);
            jqjVar.a(z);
            jqjVar.c((String) null);
            this.d.c(jqjVar, new ITransferSleepAndDFXFileCallback.Stub() { // from class: jlv.4
                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onSuccess(int i2, String str2, String str3) {
                    ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "transfer successful :", Integer.valueOf(i2));
                    iPhotoFileCallback.onSuccess(i2, str2, str3);
                }

                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "TransferImagesFromDeviceFailed errorCode:", Integer.valueOf(i2));
                    iPhotoFileCallback.onFailure(i2, str2);
                }

                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onProgress(int i2, String str2) {
                    LogUtil.a("WatchFaceAdapterImpl", "onFileTransferState percentage =", Integer.valueOf(i2));
                    iPhotoFileCallback.onProgress(i2, str2);
                }
            });
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void commonStopTransfer(String str, int i, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            this.d.d(str, i, new IBaseCallback.Stub() { // from class: jlv.5
                @Override // com.huawei.hwservicesmgr.IBaseCallback
                public void onResponse(int i2, String str2) throws RemoteException {
                    ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "cancelInstallWatchFace response code:", Integer.valueOf(i2), "cancelTime:", Long.valueOf(System.currentTimeMillis()));
                    iBaseResponseCallback.onResponse(i2, str2);
                }
            });
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void installApp(String str) {
        if (CommonUtil.ag(BaseApplication.getContext())) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "packageName is null");
        } else {
            LogUtil.a("WatchFaceAdapterImpl", "packageName:", str);
            jiw.a().e(str);
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public boolean getIsTaskExecuting() {
        boolean b2 = SmartClipManager.e(BaseApplication.getContext()).b();
        LogUtil.a("WatchFaceAdapterImpl", "isTaskExecuting:", Boolean.valueOf(b2));
        return b2;
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public String getAccessToken() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        if (!TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "accessToken: ", accountInfo.substring(0, 4));
        }
        return accountInfo;
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void refreshAccessToken(final ILoginCallback iLoginCallback) {
        if (iLoginCallback == null) {
            ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "callback is null.");
        } else {
            ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "refreshAccessToken enter.");
            LoginInit.getInstance(BaseApplication.getContext()).refreshAccessToken(new com.huawei.login.ui.login.util.ILoginCallback() { // from class: jlv.1
                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginSuccess(Object obj) {
                    if (!(obj instanceof String)) {
                        ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "object is not String.");
                        iLoginCallback.onLoginFailed(obj);
                        return;
                    }
                    String str = (String) obj;
                    if (str.length() <= 4) {
                        ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "refreshAccessToken onLoginFailed, token is less than 4");
                        iLoginCallback.onLoginFailed(obj);
                    } else {
                        ReleaseLogUtil.e("DEVMGR_WatchFaceAdapterImpl", "refreshAccessToken onLoginSuccess. token:", str.substring(0, 4));
                        iLoginCallback.onLoginSuccess(obj);
                    }
                }

                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginFailed(Object obj) {
                    ReleaseLogUtil.d("DEVMGR_WatchFaceAdapterImpl", "refreshAccessToken onLoginFailed. object: ", obj);
                    iLoginCallback.onLoginFailed(obj);
                }
            });
        }
    }

    @Override // com.huawei.watchface.api.HwWatchFaceAdapter
    public void showHealthAppSettingGuide(final Context context, final String str, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        LogUtil.h("WatchFaceAdapterImpl", "showHealthAppSettingGuide");
        PermissionUtil.PermissionType valueOf = PermissionUtil.PermissionType.valueOf(str);
        CustomTextAlertDialog.Builder cyU_ = new CustomTextAlertDialog.Builder(context).b(R$string.IDS_hwh_home_other_permissions_title).e(context.getResources().getString(nsn.d(valueOf))).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: jlw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                jlv.bHY_(onClickListener2, view);
            }
        }).cyU_(R$string.IDS_hwh_motiontrack_permission_guide_go_set, new View.OnClickListener() { // from class: jlt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                jlv.bHZ_(str, context, onClickListener, view);
            }
        });
        Integer valueOf2 = Integer.valueOf(R.color._2131299338_res_0x7f090c0a);
        CustomTextAlertDialog a2 = cyU_.e(valueOf2).a(valueOf2).a();
        a2.setCancelable(false);
        nsn.cLK_(context, valueOf, a2, onClickListener, onClickListener2);
    }

    static /* synthetic */ void bHY_(View.OnClickListener onClickListener, View view) {
        LogUtil.c("WatchFaceAdapterImpl", "showPermissionSettingGuide cancel");
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void bHZ_(String str, Context context, View.OnClickListener onClickListener, View view) {
        LogUtil.c("WatchFaceAdapterImpl", "showPermissionSettingGuide cancel");
        if (PermissionUtil.g() && PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE.equals(str)) {
            nsn.y();
        } else {
            nsn.ak(context);
        }
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
