package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.wifi.lib.db.dbtable.DeviceListManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceDetailInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceAddAuthorizeForSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAllDeviceRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeSchemeActivity;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes6.dex */
public class oop extends QrCodeBaseHandler {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f15826a = new ArrayList<String>() { // from class: oop.3
        {
            add("007B");
            add("M00D");
            add("M00F");
        }
    };
    private boolean b;
    private CommonDialog21 d;
    private String e;

    public oop(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface, Bundle bundle) {
        super(activity, commBaseCallbackInterface, bundle);
        this.b = false;
        initHandlerThread("WifiQrCodeHandler");
    }

    private String a(String str, String str2) {
        String str3 = null;
        try {
            LogUtil.c("WifiQrCodeHandler", "getQrContent qrResult = ", str2);
            String str4 = str2.split("&")[1];
            if (str4.length() >= 2) {
                str3 = str4.substring(2);
            } else {
                LogUtil.h("WifiQrCodeHandler", "getQrContent qrContent sub string fail");
            }
        } catch (IndexOutOfBoundsException | PatternSyntaxException e) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "getQrContent exception = ", e.getMessage());
            sendCallBack(-4, str, str3);
        }
        return str3;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        if (this.mExtraValues != null && (this.mExtraValues.get("sschemeQrCode") instanceof Boolean) && ((Boolean) this.mExtraValues.get("sschemeQrCode")).booleanValue()) {
            str = str + "schemeQrCode";
            this.b = true;
            this.mMainThreadHandler.sendEmptyMessage(10015);
        }
        LogUtil.a("WifiQrCodeHandler", "parser mIsScheme = ", Boolean.valueOf(this.b));
        if (!(obj instanceof String)) {
            sendCallBack(-4, str, null);
            LogUtil.b("WifiQrCodeHandler", "parser data is not a string type");
            return null;
        }
        String a2 = a(str, (String) obj);
        LogUtil.c("WifiQrCodeHandler", "parser newAction = ", str, "qrContent = ", a2);
        if ("s".equals(str) || "sschemeQrCode".equals(str)) {
            ooo oooVar = new ooo(str);
            int parser = oooVar.parser(a2);
            if (parser == 0) {
                return oooVar;
            }
            sendCallBack(parser, str, null);
        } else {
            LogUtil.h("R_QrCode_WifiQrCodeHandler", "parser newAction is error");
            sendCallBack(-4, str, null);
        }
        return null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (qrCodeDataBase != null) {
            if (qrCodeDataBase instanceof ooo) {
                ooo oooVar = (ooo) qrCodeDataBase;
                if (!oooVar.c()) {
                    return a(oooVar);
                }
                sendCallBack(-3, qrCodeDataBase.getAction(), null);
                return false;
            }
            sendCallBack(-4, qrCodeDataBase.getAction(), null);
            LogUtil.b("R_QrCode_WifiQrCodeHandler", " qrcodeData type is error,");
            return false;
        }
        LogUtil.b("R_QrCode_WifiQrCodeHandler", " qrcodeData is null");
        return false;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        this.mMainThreadHandler.sendEmptyMessage(10011);
        this.mHandler.sendEmptyMessage(1005);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                d();
                break;
            case 1002:
                f();
                break;
            case 1003:
                d(message.arg1);
                break;
            case 1004:
                der_(message);
                break;
            case 1005:
                b();
                break;
            default:
                LogUtil.h("R_QrCode_WifiQrCodeHandler", "type not match ");
                break;
        }
    }

    private void den_(final Activity activity) {
        if (activity instanceof QrCodeSchemeActivity) {
            activity.runOnUiThread(new Runnable() { // from class: oor
                @Override // java.lang.Runnable
                public final void run() {
                    oop.this.dez_(activity);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deo_, reason: merged with bridge method [inline-methods] */
    public void dez_(Activity activity) {
        LogUtil.c("WifiQrCodeHandler", "initScaleView");
        final QrCodeSchemeActivity qrCodeSchemeActivity = (QrCodeSchemeActivity) activity;
        qrCodeSchemeActivity.setContentView(R.layout.activity_qrcode_scanning);
        ((CustomTitleBar) qrCodeSchemeActivity.findViewById(R.id.qrcode_scanning_title_bar)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: ooz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oop.dep_(QrCodeSchemeActivity.this, view);
            }
        });
        ((HealthButton) activity.findViewById(R.id.qrcode_scanning_scheme_bind_btn)).setOnClickListener(new View.OnClickListener() { // from class: oov
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oop.deq_(QrCodeSchemeActivity.this, view);
            }
        });
    }

    static /* synthetic */ void dep_(QrCodeSchemeActivity qrCodeSchemeActivity, View view) {
        LogUtil.c("WifiQrCodeHandler", "setLeftButtonOnClickListener finish");
        qrCodeSchemeActivity.finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void deq_(QrCodeSchemeActivity qrCodeSchemeActivity, View view) {
        qrCodeSchemeActivity.e();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void der_(Message message) {
        ctk ctkVar;
        boolean z;
        if (message.obj != null && (message.obj instanceof ctk)) {
            ctkVar = (ctk) message.obj;
            z = b(ctkVar);
        } else {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "saveWiFiDevice msg obj is error ");
            ctkVar = null;
            z = false;
        }
        LogUtil.a("R_QrCode_WifiQrCodeHandler", "saveDevice isSave ", Boolean.valueOf(z));
        if (z && ctkVar != null) {
            sendCallBack(0, this.mQrCodeDataBase.getAction(), ctkVar);
        } else {
            sendCallBack(-1, this.mQrCodeDataBase.getAction(), null);
        }
    }

    private boolean e(List<DeviceDetailInfo> list) {
        return koq.c(list);
    }

    private void d(List<DeviceDetailInfo> list, int i) {
        for (DeviceDetailInfo deviceDetailInfo : list) {
            if (ctm.b(deviceDetailInfo.getDevInfo().getProdId())) {
                String sn = deviceDetailInfo.getDevInfo() != null ? deviceDetailInfo.getDevInfo().getSn() : "";
                if (!TextUtils.isEmpty(sn) && ceo.d().c(sn, false) == null) {
                    ctk ctkVar = new ctk();
                    ctkVar.a(deviceDetailInfo);
                    ctkVar.setAutoDevice(false);
                    ctkVar.b().d(i);
                    b(ctkVar);
                }
            }
        }
    }

    private boolean b(ctk ctkVar) {
        dcz a2 = a(ctkVar.b().f());
        if (a2 != null) {
            boolean z = ceo.d().c(ctkVar.getSerialNumber(), false) != null;
            ctkVar.setProductId(a2.t());
            ctkVar.setKind(HealthDevice.HealthDeviceKind.HDK_WEIGHT);
            ctkVar.setAutoDevice(false);
            if (z) {
                return d(ctkVar, a2);
            }
            return b(ctkVar, a2);
        }
        LogUtil.h("R_QrCode_WifiQrCodeHandler", "saveDevice productInfo is null");
        return false;
    }

    private boolean b(ctk ctkVar, dcz dczVar) {
        return cjx.e().b(ctkVar.getProductId(), dczVar.s(), ctkVar, new IDeviceEventHandler() { // from class: oop.4
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
                LogUtil.a("WifiQrCodeHandler", "saveDevice code ", Integer.valueOf(i));
            }
        });
    }

    private boolean d(ctk ctkVar, dcz dczVar) {
        return cjx.e().a(ctkVar.getProductId(), dczVar.s(), ctkVar, new IDeviceEventHandler() { // from class: oop.2
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
                LogUtil.a("WifiQrCodeHandler", "updataDevice code ", Integer.valueOf(i));
            }
        });
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void sendCallBack(int i, String str, Object obj) {
        d(i, obj);
        super.sendCallBack(i, str, obj);
    }

    private boolean d(List<DeviceDetailInfo> list) {
        ooo oooVar;
        if (!koq.b(list) && (oooVar = (ooo) this.mQrCodeDataBase) != null) {
            Iterator<DeviceDetailInfo> it = list.iterator();
            while (it.hasNext()) {
                if (oooVar.d().equals(it.next().getDevId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<DeviceDetailInfo> b(List<DeviceDetailInfo> list) {
        if (koq.b(list)) {
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

    private void a(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp) {
        wifiDeviceGetAllDeviceRsp.setDeviceDetailInfoList(b(wifiDeviceGetAllDeviceRsp.getDeviceDetailInfoList()));
        wifiDeviceGetAllDeviceRsp.setAuthorizedDeviceDetailInfoList(b(wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList()));
    }

    private boolean d(DeviceDetailInfo deviceDetailInfo) {
        if (deviceDetailInfo == null || deviceDetailInfo.getDevInfo() == null || TextUtils.isEmpty(deviceDetailInfo.getDevId())) {
            return false;
        }
        String prodId = deviceDetailInfo.getDevInfo().getProdId();
        if (TextUtils.isEmpty(prodId)) {
            return false;
        }
        return (f15826a.contains(prodId) && koq.b(deviceDetailInfo.getServices())) ? false : true;
    }

    private void f() {
        LogUtil.a("WifiQrCodeHandler", "verifyDevice in");
        if (!ctv.d(cpp.a())) {
            sendCallBack(-5, this.mQrCodeDataBase.getAction(), null);
        } else {
            jbs.a(cpp.a()).d(new ICloudOperationResult() { // from class: oow
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    oop.this.e((WifiDeviceGetAllDeviceRsp) obj, str, z);
                }
            });
            ResourceManager.e().c();
        }
    }

    /* synthetic */ void e(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, String str, boolean z) {
        int i;
        String str2;
        if (z) {
            LogUtil.a("WifiQrCodeHandler", "verifyDevice reg device success :", wifiDeviceGetAllDeviceRsp.toString());
            List<DeviceDetailInfo> b = b(wifiDeviceGetAllDeviceRsp.getDeviceDetailInfoList());
            List<DeviceDetailInfo> b2 = b(wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList());
            if (e(b)) {
                d(b, 1);
            }
            if (e(b2)) {
                d(b2, 2);
            }
            if (d(b2) || d(b)) {
                LogUtil.h("R_QrCode_WifiQrCodeHandler", "verifyDevice() authorize same device");
                sendCallBack(-13, this.mQrCodeDataBase.getAction(), null);
                return;
            } else {
                c(b);
                this.mMainThreadHandler.sendEmptyMessage(10001);
                return;
            }
        }
        if (wifiDeviceGetAllDeviceRsp != null) {
            i = wifiDeviceGetAllDeviceRsp.getResultCode().intValue();
            str2 = wifiDeviceGetAllDeviceRsp.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        if (i == 1003) {
            sendCallBack(-17, this.mQrCodeDataBase.getAction(), null);
        } else {
            sendCallBack(-12, this.mQrCodeDataBase.getAction(), null);
        }
        LogUtil.h("WifiQrCodeHandler", "verifyDevice() errCode = ", Integer.valueOf(i), ",resultDesc:", str2);
    }

    private void c(List<DeviceDetailInfo> list) {
        String c = c();
        if (list == null || c == null) {
            return;
        }
        for (DeviceDetailInfo deviceDetailInfo : list) {
            if (cpa.t(deviceDetailInfo.getDevInfo().getProdId()).equals(c) && deviceDetailInfo.getServices() == null) {
                LogUtil.a("WifiQrCodeHandler", "start to delete bluetooth device, deviceID = ", deviceDetailInfo.getDevId());
                csf.i(deviceDetailInfo.getDevId());
            }
        }
    }

    private void d(final int i) {
        LogUtil.a("WifiQrCodeHandler", "queryDevice in");
        if (deg_() == null) {
            LogUtil.h("R_QrCode_WifiQrCodeHandler", "queryDevice() activity is null ");
            sendCallBack(-1, this.mQrCodeDataBase.getAction(), null);
        } else {
            jbs.a(cpp.a()).d(new ICloudOperationResult() { // from class: ooq
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    oop.this.b(i, (WifiDeviceGetAllDeviceRsp) obj, str, z);
                }
            });
        }
    }

    /* synthetic */ void b(int i, WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, String str, boolean z) {
        c(wifiDeviceGetAllDeviceRsp, z, i);
    }

    private Activity deg_() {
        if (this.mActivity != null) {
            return this.mActivity.get();
        }
        return null;
    }

    private ctk c(List<DeviceDetailInfo> list, String str) {
        for (DeviceDetailInfo deviceDetailInfo : list) {
            if (str.equals(deviceDetailInfo.getDevId())) {
                ctk ctkVar = new ctk();
                ctkVar.a(deviceDetailInfo);
                ctkVar.b().d(2);
                return ctkVar;
            }
        }
        LogUtil.b("R_QrCode_WifiQrCodeHandler", "getAuthDeviceInfo() get device is null");
        return null;
    }

    private void c(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, boolean z, int i) {
        int i2;
        String str;
        LogUtil.a("WifiQrCodeHandler", "processDeviceFromCloud in");
        if (!z) {
            if (wifiDeviceGetAllDeviceRsp != null) {
                i2 = wifiDeviceGetAllDeviceRsp.getResultCode().intValue();
                str = wifiDeviceGetAllDeviceRsp.getResultDesc();
            } else {
                i2 = Constants.CODE_UNKNOWN_ERROR;
                str = "unknown error";
            }
            c(i);
            LogUtil.a("WifiQrCodeHandler", "processDeviceFromCloud() errCode = ", Integer.valueOf(i2), ",resultDesc:", str);
            return;
        }
        if (wifiDeviceGetAllDeviceRsp == null) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "processDeviceFromCloud reg device success but rsp is null");
            c(i);
            return;
        }
        a(wifiDeviceGetAllDeviceRsp);
        if (wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList() == null || wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList().size() == 0) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "processDeviceFromCloud reg device success but rsp.getDeviceDetailInfo is null");
            c(i);
            return;
        }
        if (this.mHandler != null) {
            ctk c = c(wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList(), ((ooo) this.mQrCodeDataBase).d());
            if (c != null) {
                Message obtain = Message.obtain();
                obtain.obj = c;
                obtain.what = 1004;
                this.mHandler.sendMessage(obtain);
                return;
            }
            c(i);
            return;
        }
        LogUtil.b("R_QrCode_WifiQrCodeHandler", "processDeviceFromCloud() mHandler is null");
    }

    private boolean a(ctk ctkVar, ctk ctkVar2) {
        if (ctkVar == null || ctkVar2 == null) {
            LogUtil.h("R_QrCode_WifiQrCodeHandler", "checkDeviceProdId device or authDevice is null");
            return false;
        }
        if (!ctkVar.b().f().equals(ctkVar2.b().f())) {
            return false;
        }
        LogUtil.a("R_QrCode_WifiQrCodeHandler", "checkDeviceProdId has same device");
        return true;
    }

    private void c(int i) {
        LogUtil.a("WifiQrCodeHandler", "queryNum is:", Integer.valueOf(i));
        if (i < 3) {
            Message obtain = Message.obtain();
            obtain.arg1 = i + 1;
            obtain.what = 1003;
            this.mHandler.sendMessage(obtain);
            return;
        }
        sendCallBack(-16, this.mQrCodeDataBase.getAction(), null);
    }

    private void d() {
        String str;
        LogUtil.a("WifiQrCodeHandler", "deviceAuth in");
        Activity deg_ = deg_();
        if (deg_ == null) {
            LogUtil.h("R_QrCode_WifiQrCodeHandler", "deviceAuth() activity is null ");
            sendCallBack(-1, this.mQrCodeDataBase.getAction(), null);
            return;
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            str = userInfo.getName();
            LogUtil.a("R_QrCode_WifiQrCodeHandler", "deviceAuth HWUserProfileMgr in");
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            str = new UpApi(deg_).getLegalAccountName();
            LogUtil.a("R_QrCode_WifiQrCodeHandler", "deviceAuth UpApi in");
        }
        if (TextUtils.isEmpty(str)) {
            str = LoginInit.getInstance(deg_).getAccountInfo(1000);
            LogUtil.a("R_QrCode_WifiQrCodeHandler", "deviceAuth SharedPreferenceUtil in");
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiQrCodeHandler", "SharedPreferenceUtil.getInstance(activity).getAccountName is null");
            str = LoginInit.getInstance(deg_).getAccountInfo(1002);
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("R_QrCode_WifiQrCodeHandler", "get subNickName is null");
            sendCallBack(-15, this.mQrCodeDataBase.getAction(), null);
        } else {
            dec_(str, deg_);
        }
    }

    private void dec_(String str, Activity activity) {
        int length = str.length();
        if (length > 20) {
            str = str.substring(0, 10) + str.substring(length - 10, length);
        }
        ooo oooVar = (ooo) this.mQrCodeDataBase;
        if (c(oooVar.d())) {
            LogUtil.h("WifiQrCodeHandler", "shareToSubUser is wifi subUser");
            e(R.string.IDS_device_hygride_auth_user, activity);
            return;
        }
        LogUtil.h("WifiQrCodeHandler", "shareToSubUser is not wifi subUser");
        WifiDeviceAddAuthorizeForSubUserReq wifiDeviceAddAuthorizeForSubUserReq = new WifiDeviceAddAuthorizeForSubUserReq();
        wifiDeviceAddAuthorizeForSubUserReq.setDevId(oooVar.d());
        wifiDeviceAddAuthorizeForSubUserReq.setNickName(str);
        wifiDeviceAddAuthorizeForSubUserReq.setMainHuid(oooVar.e());
        wifiDeviceAddAuthorizeForSubUserReq.setVerifyCode(oooVar.b());
        jbs.a(activity).d(wifiDeviceAddAuthorizeForSubUserReq, new ICloudOperationResult() { // from class: oon
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                oop.this.b((CloudCommonReponse) obj, str2, z);
            }
        });
    }

    /* synthetic */ void b(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (z) {
            LogUtil.a("R_QrCode_WifiQrCodeHandler", "add deviceAuth Success.");
            c(0);
        } else {
            a(cloudCommonReponse);
        }
    }

    private boolean c(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        LogUtil.h("WifiQrCodeHandler", "checkCurrentUserIsSubUser currentUserHuid： ", accountInfo);
        crw a2 = ctq.a(str);
        ArrayList<crx> arrayList = new ArrayList<>();
        if (a2 != null) {
            arrayList = a2.b();
        } else {
            LogUtil.h("WifiQrCodeHandler", "deviceUserInfo is null.");
        }
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            Iterator<crx> it = arrayList.iterator();
            while (it.hasNext()) {
                crx next = it.next();
                LogUtil.h("WifiQrCodeHandler", "checkCurrentUserIsSubUser subUser.getNickName() = ", next.d());
                arrayList2.add(next.e());
            }
            if (arrayList2.contains(accountInfo)) {
                LogUtil.h("WifiQrCodeHandler", "checkCurrentUserIsSubUser have auth user > ", Integer.valueOf(R.string.IDS_device_hygride_auth_user));
                return true;
            }
            LogUtil.h("WifiQrCodeHandler", "checkCurrentUserIsSubUser current user is not subUser");
            return false;
        }
        LogUtil.h("WifiQrCodeHandler", "checkCurrentUserIsSubUser current device have none subUser");
        return false;
    }

    private void a(CloudCommonReponse cloudCommonReponse) {
        if (cloudCommonReponse == null) {
            sendCallBack(-15, this.mQrCodeDataBase.getAction(), null);
            return;
        }
        int intValue = cloudCommonReponse.getResultCode().intValue();
        String resultDesc = cloudCommonReponse.getResultDesc();
        if (intValue == 112000020) {
            sendCallBack(-13, this.mQrCodeDataBase.getAction(), null);
        } else if (intValue == 112000060) {
            sendCallBack(-14, this.mQrCodeDataBase.getAction(), null);
        } else if (intValue == 112000070) {
            sendCallBack(-17, this.mQrCodeDataBase.getAction(), null);
        } else if (intValue == 112000010) {
            sendCallBack(-18, this.mQrCodeDataBase.getAction(), null);
        } else {
            sendCallBack(-15, this.mQrCodeDataBase.getAction(), null);
        }
        LogUtil.b("WifiQrCodeHandler", "deviceAuth error: ", Integer.valueOf(intValue), ", resultDesc: ", resultDesc);
    }

    private boolean a(ooo oooVar) {
        this.mQrCodeDataBase = oooVar;
        return true;
    }

    private dcz d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "getProduct productId is null");
            return null;
        }
        return ResourceManager.e().d(str);
    }

    private dcz a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "getProductInfo prodId is null");
            return null;
        }
        LogUtil.a("WifiQrCodeHandler", "getProductInfo prodId is ", str);
        dkz e = DeviceListManager.c(cpp.a()).e(str);
        dcz d = e != null ? d(e.d) : null;
        if (d != null) {
            return d;
        }
        LogUtil.h("R_QrCode_WifiQrCodeHandler", "getProductInfo The product obtained from Table is empty");
        String t = cpa.t(str);
        if (TextUtils.isEmpty(t)) {
            LogUtil.h("R_QrCode_WifiQrCodeHandler", "getProductInfo productId is null");
            return d;
        }
        return d(t);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
        LogUtil.a("WifiQrCodeHandler", "updateResourceFile: isPluginAvaiable is true and msg.what = ", Integer.valueOf(message.what));
        int i = message.what;
        if (i == 1016) {
            e(R.string.IDS_device_wifi_my_qrcode_error_qrcode, activity);
            a();
            def_(activity);
            return;
        }
        if (i != 10015) {
            switch (i) {
                case 10001:
                    dem_(activity);
                    break;
                case 10002:
                    det_(activity);
                    dej_(activity, message.obj);
                    break;
                case 10003:
                    del_(activity);
                    break;
                case 10004:
                    e(R.string._2130841884_res_0x7f02111c, activity);
                    a();
                    def_(activity);
                    break;
                case 10005:
                    e(R.string.IDS_device_wifi_my_qrcode_overdue, activity);
                    a();
                    def_(activity);
                    break;
                case 10006:
                    ded_(message, activity);
                    break;
                case 10007:
                    e(R.string._2130842410_res_0x7f02132a, activity);
                    a();
                    def_(activity);
                    break;
                case 10008:
                    deu_(activity, activity.getString(R.string.IDS_device_wifi_my_qrcode_add_member_duplicate_msg));
                    break;
                default:
                    dek_(message, activity);
                    break;
            }
            return;
        }
        LogUtil.a("WifiQrCodeHandler", "mainHandleMessage MSG_INIT_SCALE_VIEW");
        den_(activity);
    }

    private void del_(Activity activity) {
        if (this.b) {
            e(R.string.IDS_device_wifi_scheme_link_error, activity);
        } else {
            e(R.string.IDS_device_wifi_my_qrcode_error_qrcode, activity);
        }
        a();
        def_(activity);
    }

    private void dem_(Activity activity) {
        if (this.e != null && EzPluginManager.a().g(this.e) && cpa.ad(this.e) && !crj.c(this.e, "HDK_WEIGHT")) {
            LogUtil.a("WifiQrCodeHandler", "updateResourceFile: isPluginAvaiable is true and mProductId = ", CommonUtil.l(this.e));
            crj.Lu_(BaseApplication.getContext(), activity);
        } else {
            deh_(activity);
        }
    }

    private void ded_(Message message, Activity activity) {
        if (message.obj != null && (message.obj instanceof ctk)) {
            dev_(activity, (ctk) message.obj);
        } else {
            LogUtil.h("R_QrCode_WifiQrCodeHandler", "main Handler device is null or class error");
        }
    }

    private void dek_(Message message, Activity activity) {
        switch (message.what) {
            case 10009:
                e(R.string.IDS_device_wifi_my_qrcode_error_qrcode, activity);
                a();
                def_(activity);
                break;
            case 10010:
                a();
                def_(activity);
                break;
            case 10011:
                if (this.b) {
                    dew_(R.string.IDS_device_wifi_scheme_link_scanning_loading, activity);
                    break;
                } else {
                    dew_(R.string.IDS_device_wifi_my_qrcode_add_member_loading, activity);
                    break;
                }
            case 10012:
                dew_(R.string.IDS_device_wifi_my_qrcode_auth_loadding_message, activity);
                break;
            case 10013:
                if (this.b) {
                    e(R.string.IDS_device_wifi_scheme_link_expired, activity);
                } else {
                    e(R.string.IDS_device_wifi_my_qrcode_overdue, activity);
                }
                a();
                def_(activity);
                break;
            case PrebakedEffectId.RT_CALENDAR_DATE /* 10014 */:
                deu_(activity, activity.getString(R.string.IDS_device_wifi_my_qrcode_add_member_exceeding_limit));
                break;
            default:
                LogUtil.a("WifiQrCodeHandler", "mainHandleMessage what is error:", Integer.valueOf(message.what));
                break;
        }
    }

    private void dee_(Activity activity) {
        a();
        activity.finish();
    }

    private void d(int i, Object obj) {
        LogUtil.a("R_QrCode_WifiQrCodeHandler", "handleErrorEvent errorCode:", Integer.valueOf(i));
        Message obtain = Message.obtain();
        switch (i) {
            case com.tencent.connect.common.Constants.ERROR_PROXY_LOGIN_AND_QQ_VERSION_LOWER /* -18 */:
                obtain.what = PrebakedEffectId.RT_CALENDAR_DATE;
                break;
            case -17:
                obtain.what = 10013;
                break;
            case com.tencent.connect.common.Constants.ERROR_IMAGE_TOO_LARGE /* -16 */:
            case -15:
            case -12:
                obtain.what = 10007;
                break;
            case -14:
                obtain.what = 10009;
                break;
            case -13:
                obtain.what = 10008;
                break;
            case -11:
                obtain.obj = obj;
                obtain.what = 10006;
                break;
            case -10:
            case -9:
            case -8:
            case -7:
            default:
                LogUtil.a("WifiQrCodeHandler", "handleErrorEvent other errorCode ,", Integer.valueOf(i));
                return;
            case -6:
                obtain.what = 10005;
                break;
            case -5:
                obtain.what = 10004;
                break;
            case -4:
            case -3:
            case -2:
                obtain.what = 10003;
                break;
            case -1:
                obtain.what = 10010;
                break;
            case 0:
                obtain.what = 10002;
                obtain.obj = obj;
                break;
        }
        this.mMainThreadHandler.sendMessage(obtain);
    }

    private void e(int i, Context context) {
        if (context != null) {
            nrh.b(context.getApplicationContext(), i);
        }
    }

    private void dew_(int i, Activity activity) {
        String string = activity.getString(i);
        if (this.d == null) {
            new CommonDialog21(activity, R.style.app_update_dialogActivity);
            this.d = CommonDialog21.a(activity);
        }
        this.d.setCancelable(false);
        this.d.e(string);
        this.d.a();
    }

    private void a() {
        CommonDialog21 commonDialog21 = this.d;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.d.dismiss();
        this.d = null;
    }

    private void def_(Activity activity) {
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    private void deu_(final Activity activity, String str) {
        a();
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(str).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: oou
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oop.this.deA_(activity, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCanceledOnTouchOutside(false);
        e.show();
    }

    /* synthetic */ void deA_(Activity activity, View view) {
        def_(activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dev_(final Activity activity, final ctk ctkVar) {
        a();
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        String string = activity.getString(R.string.IDS_device_wifi_my_qrcode_add_member_failed_title);
        builder.b(string).e(activity.getString(R.string.IDS_device_wifi_my_qrcode_add_member_has_other_scales_msg)).cyU_(R.string._2130837830_res_0x7f020146, new View.OnClickListener() { // from class: ooy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oop.this.deB_(activity, ctkVar, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: oox
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oop.this.deC_(activity, view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCanceledOnTouchOutside(false);
        a2.show();
    }

    /* synthetic */ void deB_(Activity activity, ctk ctkVar, View view) {
        dei_(activity, ctkVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void deC_(Activity activity, View view) {
        def_(activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dei_(Activity activity, ctk ctkVar) {
        String str;
        LogUtil.a("WifiQrCodeHandler", "gotoDeviceControl in");
        dee_(activity);
        ArrayList<ctk> a2 = cjx.e().a();
        if (a2 == null || a2.size() == 0) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "gotoDeviceControl local devices is null");
            return;
        }
        Iterator<ctk> it = a2.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            ctk next = it.next();
            if (a(next, ctkVar)) {
                str = next.getProductId();
                break;
            }
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "gotoDeviceControl productId is null");
        } else {
            dey_(activity, str);
            LogUtil.a("R_QrCode_WifiQrCodeHandler", "gotoDeviceControl out");
        }
    }

    private void dey_(Activity activity, String str) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setAction("SWITCH_PLUGINDEVICE");
        bundle.putString("arg1", "DeviceInfoList");
        bundle.putString("productId", str);
        intent.setPackage(com.huawei.haf.application.BaseApplication.d());
        intent.setClassName(com.huawei.haf.application.BaseApplication.d(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtras(bundle);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WifiQrCodeHandler", "startWifiFragment startActivity ActivityNotFoundException.");
        }
    }

    private void det_(Activity activity) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(activity, AnalyticsValue.HEALTH_PLUGIN_DEVICE_WIFI_DEVICE_MULTIACCOUNT_ADD_SUCCESS_2060034.value(), hashMap, 0);
    }

    private void dej_(Activity activity, Object obj) {
        LogUtil.a("WifiQrCodeHandler", "gotoUserActivity in");
        a();
        if (obj != null && (obj instanceof ctk)) {
            ctk ctkVar = (ctk) obj;
            Intent intent = new Intent();
            intent.putExtra("device", "wifi");
            intent.putExtra("auth_device_id", ctkVar.d());
            intent.putExtra("productId", ctkVar.getProductId());
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", ctkVar.getProductId());
            MeasurableDevice c = ceo.d().c(ctkVar.getSerialNumber(), false);
            if (c != null) {
                contentValues.put("uniqueId", c.getUniqueId());
            } else {
                contentValues.put("uniqueId", ctkVar.getUniqueId());
            }
            intent.putExtra("commonDeviceInfo", contentValues);
            intent.setClassName(com.huawei.haf.application.BaseApplication.d(), "com.huawei.ui.main.stories.me.activity.BindUserInfoActivity");
            try {
                activity.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("WifiQrCodeHandler", "gotoUserActivity startActivity ActivityNotFoundException.");
            }
            activity.finish();
            return;
        }
        LogUtil.b("R_QrCode_WifiQrCodeHandler", "gotoUserActivity object is not wifi device or object is null");
    }

    private void des_(int i, View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_privacy_auth_item_two);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.hw_privacy_auth_cancel_by_setting);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.hw_privacy_auth_layout_healthdata);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.hw_privacy_auth_layout_userinfo);
        if (i == 1) {
            healthTextView.setVisibility(8);
            healthTextView2.setVisibility(8);
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(8);
            return;
        }
        if (i == 2) {
            healthTextView.setVisibility(0);
            healthTextView2.setVisibility(0);
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(8);
            return;
        }
        if (i == 3) {
            healthTextView.setVisibility(0);
            healthTextView2.setVisibility(0);
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(0);
            return;
        }
        if (i == 4) {
            healthTextView.setVisibility(0);
            healthTextView2.setVisibility(0);
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(0);
            return;
        }
        LogUtil.a("WifiQrCodeHandler", "setDialogView status is error", Integer.valueOf(i));
    }

    private void dex_(int i, final Activity activity) {
        final rvo e = rvo.e(activity);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(activity);
        View inflate = View.inflate(activity, R.layout.dialog_show_privacy_auth, null);
        des_(i, inflate);
        final boolean z = i == 1;
        builder.d(R.string.IDS_device_wifi_privacy_auth).czg_(inflate).czc_(R.string._2130837555_res_0x7f020033, new View.OnClickListener() { // from class: opb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oop.this.deE_(activity, view);
            }
        }).cze_(R.string._2130839489_res_0x7f0207c1, new View.OnClickListener() { // from class: oos
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oop.this.deD_(z, e, view);
            }
        });
        CustomViewDialog e2 = builder.e();
        e2.setCanceledOnTouchOutside(false);
        e2.show();
    }

    /* synthetic */ void deE_(Activity activity, View view) {
        if (!this.b) {
            def_(activity);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void deD_(boolean z, rvo rvoVar, View view) {
        if (!z) {
            rvoVar.e(2, true);
            rvoVar.e(300, true);
            rvoVar.e(7, true);
            rvoVar.e(301, true);
        }
        this.mHandler.sendEmptyMessage(1001);
        this.mMainThreadHandler.sendEmptyMessage(10012);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void deh_(Activity activity) {
        LogUtil.a("R_QrCode_WifiQrCodeHandler", "getPrivacyAuthSwitchStatus in");
        a();
        rvo e = rvo.e(activity);
        String a2 = e.a(2);
        String a3 = e.a(7);
        if ("true".equals(a2) && "true".equals(a3)) {
            dex_(1, activity);
            return;
        }
        if ("true".equals(a2) && !"true".equals(a3)) {
            dex_(2, activity);
            return;
        }
        if (!"true".equals(a2) && "true".equals(a3)) {
            dex_(3, activity);
        } else if (!"true".equals(a2) && !"true".equals(a3)) {
            dex_(4, activity);
        } else {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "switch status is error");
        }
    }

    private void b() {
        b(3);
    }

    private void b(final int i) {
        LogUtil.a("R_QrCode_WifiQrCodeHandler", "updateResourceFileWithRetry ", Integer.valueOf(i));
        if (!ctv.d(cpp.a())) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "updateResourceFileWithRetry fail: no network");
            sendCallBack(-5, this.mQrCodeDataBase.getAction(), null);
            return;
        }
        String c = c();
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("R_QrCode_WifiQrCodeHandler", "updateResourceFile: product id is null");
            this.mHandler.sendEmptyMessage(1002);
            return;
        }
        this.e = c;
        if (EzPluginManager.a().g(c)) {
            LogUtil.a("R_QrCode_WifiQrCodeHandler", "updateResourceFile: isPluginAvaiable is true");
            if (!e(((ooo) this.mQrCodeDataBase).a())) {
                this.mMainThreadHandler.sendEmptyMessage(1016);
                return;
            } else {
                this.mHandler.sendEmptyMessage(1002);
                return;
            }
        }
        if (i <= 0) {
            LogUtil.b("R_QrCode_WifiQrCodeHandler", "updateResourceFileWithRetry fail");
            sendCallBack(-1, this.mQrCodeDataBase.getAction(), null);
        } else {
            cpe.c(c, new PullListener() { // from class: oot
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    oop.this.b(i, msqVar, msoVar);
                }
            });
        }
    }

    /* synthetic */ void b(int i, msq msqVar, mso msoVar) {
        if (msoVar == null || msoVar.i() == 0) {
            return;
        }
        LogUtil.a("WifiQrCodeHandler", "updateResourceFile onPullingChange ", Integer.valueOf(msoVar.i()));
        b(i - 1);
    }

    private String c() {
        return cpa.t(((ooo) this.mQrCodeDataBase).a());
    }

    private boolean e(String str) {
        dcz a2 = a(str);
        if (a2 == null) {
            LogUtil.a("WifiQrCodeHandler", "isDeviceResourceCanUsing productInfo is null");
            return false;
        }
        int d = CommonUtil.d(BaseApplication.getContext());
        LogUtil.a("WifiQrCodeHandler", "isDeviceResourceCanUsing currentVersion =", Integer.valueOf(d));
        String a3 = a2.a();
        try {
        } catch (NumberFormatException unused) {
            LogUtil.b("WifiQrCodeHandler", "isDeviceResourceCanUsing NumberFormatException");
        }
        if (TextUtils.isEmpty(a3)) {
            return true;
        }
        return ((long) d) >= Long.parseLong(a3);
    }
}
