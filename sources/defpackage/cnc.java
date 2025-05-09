package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceExitAuthorizeSubUserReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.cnc;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class cnc {

    /* renamed from: a, reason: collision with root package name */
    private coy f792a;
    private WeakReference<Activity> b;
    private cmv c;
    private WeakReference<CommonDialog21> d;
    private WeakReference<HagridDeviceManagerFragment> e;
    private String h;
    private String j;

    private cnc() {
        this.f792a = new coy();
        this.d = new WeakReference<>(null);
    }

    public void IY_(String str, String str2, Activity activity, cmv cmvVar, HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        this.j = str;
        this.h = str2;
        this.b = new WeakReference<>(activity);
        this.c = cmvVar;
        this.e = new WeakReference<>(hagridDeviceManagerFragment);
    }

    public static cnc b() {
        return e.e;
    }

    public void d(String str, cld cldVar, HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        if (nsn.o()) {
            LogUtil.h("RestoreFactoryManager", "clickRequestDeviceShareItem click too fast.");
            return;
        }
        this.j = str;
        if (!(ceo.d().d(str, true) instanceof cxh)) {
            hagridDeviceManagerFragment.showSelectBindDeviceDialog();
            return;
        }
        if (!d(cldVar)) {
            LogUtil.h("RestoreFactoryManager", "bluetooth is not connect");
            return;
        }
        Context context = BaseApplication.getContext();
        if (!ctv.d(context) && cpa.s(this.h)) {
            nrh.b(context, R.string.IDS_device_wifi_not_network);
        } else {
            IV_(this.b.get());
        }
    }

    private void IV_(final Activity activity) {
        if (activity == null) {
            LogUtil.h("RestoreFactoryManager", "showDataDialog activity is null");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string.IDS_device_bluetooth_open).d(R.string.IDS_device_wifi_factory_data_reset_msg).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: cni
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cnc.this.IZ_(activity, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cnh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cnc.IU_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    /* synthetic */ void IZ_(Activity activity, View view) {
        LogUtil.a("RestoreFactoryManager", "factory data reset");
        MeasurableDevice d = ceo.d().d(this.j, false);
        boolean z = d instanceof cxh;
        if (!z && !(d instanceof ctk)) {
            LogUtil.h("RestoreFactoryManager", "device type is not ble or wifi");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        IW_(activity, activity.getString(R.string._2130840618_res_0x7f020c2a));
        if (z) {
            LogUtil.h("RestoreFactoryManager", "RESTORE_FACTORY_EVENT_SEND_RESET_COMMAND");
            Jd_("send_reset_cmd", new Bundle());
        } else if (d instanceof ctk) {
            Bundle bundle = new Bundle();
            bundle.putString("deviceId", ((ctk) d).d());
            LogUtil.h("RestoreFactoryManager", "RESTORE_FACTORY_EVENT_UNBIND_CLOUD_DEVICE");
            Jd_("unbind_cloud_device", bundle);
            a(d);
        } else {
            LogUtil.h("RestoreFactoryManager", "unknow device type");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void IU_(View view) {
        LogUtil.a("RestoreFactoryManager", "restoreFactory on click negative");
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean d(cld cldVar) {
        Context context = BaseApplication.getContext();
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            if (e(cldVar)) {
                return true;
            }
            nrh.b(context, R.string.IDS_plugin_device_weight_device_not_connect);
            return false;
        }
        if (cldVar != null) {
            cldVar.b(1);
        } else {
            LogUtil.h("RestoreFactoryManager", "bluetooth is off, but mWeightInteractor is null");
            nrh.b(context, R.string._2130843214_res_0x7f02164e);
        }
        return false;
    }

    private boolean e(cld cldVar) {
        if (cldVar != null) {
            return cldVar.b();
        }
        return false;
    }

    private void IW_(Activity activity, String str) {
        if (this.d.get() == null) {
            new CommonDialog21(activity, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(activity);
            a2.e(str);
            a2.a();
            a2.setCancelable(false);
            this.d = new WeakReference<>(a2);
            return;
        }
        LogUtil.a("R_Weight_RestoreFactoryManager", "showLoadingDialog: mLoadingDialog is not null");
    }

    public void Jd_(String str, Bundle bundle) {
        cmv cmvVar;
        char c;
        if (TextUtils.isEmpty(str) || (cmvVar = this.c) == null) {
            LogUtil.h("RestoreFactoryManager", "processRestoreFactory illegal argments, eventType ", str);
            return;
        }
        cmvVar.removeMessages(13);
        Message obtain = Message.obtain();
        obtain.what = 13;
        obtain.arg1 = 1;
        this.c.sendMessageDelayed(obtain, 6000L);
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1810862359) {
            if (str.equals("unbind_cloud_device")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -834878221) {
            if (hashCode == 669290067 && str.equals("send_reset_cmd")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("unbind_local_device")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            if (bundle == null || TextUtils.isEmpty(bundle.getString("deviceId"))) {
                LogUtil.h("RestoreFactoryManager", "illegal argument, deviceId must provided");
                return;
            } else {
                c(bundle.getString("deviceId"), true);
                return;
            }
        }
        if (c == 1) {
            this.c.removeMessages(13);
            c();
            LogUtil.a("RestoreFactoryManager", "restory factory succeed");
        } else if (c == 2) {
            EventBus.d(new EventBus.b("device_reset"));
        } else {
            LogUtil.h("RestoreFactoryManager", "processRestoreFactory unknow event_type");
        }
    }

    private void c(String str, final boolean z) {
        this.f792a.d(str, new ICloudOperationResult() { // from class: cnj
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z2) {
                cnc.this.d(z, (CloudCommonReponse) obj, str2, z2);
            }
        });
    }

    /* synthetic */ void d(boolean z, CloudCommonReponse cloudCommonReponse, String str, boolean z2) {
        int i;
        String str2;
        LogUtil.a("RestoreFactoryManager", "onClickUnBind :", Boolean.valueOf(z2));
        HagridDeviceManagerFragment hagridDeviceManagerFragment = this.e.get();
        if (hagridDeviceManagerFragment == null) {
            LogUtil.h("RestoreFactoryManager", "unBindCloudDevice, fragment is null");
            return;
        }
        if (hagridDeviceManagerFragment.isDestory()) {
            LogUtil.h("RestoreFactoryManager", "unBindCloudDevice HagridDeviceManagerFragment is Destory");
            Activity activity = this.b.get();
            if (activity == null) {
                activity = BaseApplication.getActivity();
                LogUtil.a("RestoreFactoryManager", "unBindCloudDevice, ", activity);
            }
            if (activity != null) {
                activity.finish();
                return;
            } else {
                LogUtil.h("RestoreFactoryManager", "unBindCloudDevice HagridDeviceManagerFragment mainActivity is null");
                return;
            }
        }
        e();
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = -1;
            str2 = "unknown error";
        }
        if (!z2 && i != 112000000) {
            a();
            j();
        } else if (z) {
            Jd_("send_reset_cmd", new Bundle());
        } else {
            c();
        }
        LogUtil.h("RestoreFactoryManager", " unBindCloudDevice error:", Integer.valueOf(i), ",resultDesc:", str2);
    }

    private void e() {
        cmv cmvVar = this.c;
        if (cmvVar != null) {
            cmvVar.removeMessages(9);
        }
    }

    private void a() {
        CommonDialog21 commonDialog21 = this.d.get();
        if (commonDialog21 != null) {
            commonDialog21.cancel();
        }
    }

    public void c() {
        LogUtil.a("RestoreFactoryManager", "unBindLocalDevice starting:");
        a();
        HagridDeviceManagerFragment hagridDeviceManagerFragment = this.e.get();
        if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory()) {
            LogUtil.h("RestoreFactoryManager", "unBindLocalDevice, fragment is destroyed");
            return;
        }
        MeasurableDevice d = ceo.d().d(this.j, false);
        LogUtil.a("RestoreFactoryManager", "unBindLocalDevice starting device：", d);
        this.f792a.JX_(hagridDeviceManagerFragment.getContentValues(), hagridDeviceManagerFragment.getWiFiDevice(), hagridDeviceManagerFragment.getProductInfo());
        csb.a().e(this.h);
        if (d != null && !(d instanceof ctk)) {
            if (hagridDeviceManagerFragment.getWeightInteractor() != null) {
                hagridDeviceManagerFragment.getWeightInteractor().e(false);
                hagridDeviceManagerFragment.getWeightInteractor().onDestroy();
            }
            d.disconnect();
        }
        Context context = BaseApplication.getContext();
        coz.d("RestoreFactoryManager", "", context, this.j);
        coz.a("RestoreFactoryManager", "", context, this.j);
        SharedPreferenceManager.e(context, String.valueOf(10000), "isNfcConnect" + this.j, (String) null, (StorageParams) null);
        SharedPreferenceManager.e(context, String.valueOf(10000), "hagridConfigureTheNetwork" + this.j, (String) null, (StorageParams) null);
        SharedPreferenceManager.e(context, String.valueOf(10000), "hagridConfigureAutoUpgrade" + this.j, (String) null, (StorageParams) null);
        LogUtil.a("RestoreFactoryManager", "unBindLocalDevice isSuccess:");
        Activity activity = this.b.get();
        if (activity == null) {
            activity = BaseApplication.getActivity();
            LogUtil.a("RestoreFactoryManager", "unBindLocalDevice, ", activity);
        }
        if (activity != null) {
            activity.finish();
        }
    }

    private void j() {
        HandlerExecutor.a(new Runnable() { // from class: cnm
            @Override // java.lang.Runnable
            public final void run() {
                cnc.this.d();
            }
        });
    }

    public void d() {
        nrh.c(BaseApplication.getContext(), R.string._2130841551_res_0x7f020fcf);
    }

    public void a(HealthDevice healthDevice) {
        LogUtil.h("R_Weight_RestoreFactoryManager", "unbindConfirmed device");
        if (healthDevice != null) {
            if (healthDevice instanceof ctk) {
                ctk ctkVar = (ctk) healthDevice;
                if (!TextUtils.isEmpty(ctkVar.d())) {
                    LogUtil.h("RestoreFactoryManager", "unbindConfirmed WiFiDevice and onClickUnBind");
                    b(ctkVar);
                    return;
                }
            }
            if (healthDevice instanceof cxh) {
                LogUtil.h("RestoreFactoryManager", "unbindConfirmed WiFiDevice and unBindLocalDevice");
                c();
                return;
            } else {
                LogUtil.h("RestoreFactoryManager", "device instanceof unknown unBindLocalDevice");
                c();
                return;
            }
        }
        LogUtil.h("RestoreFactoryManager", "device has unbind unBindLocalDevice");
        c();
    }

    private void b(ctk ctkVar) {
        final Context context = BaseApplication.getContext();
        if (!ctv.d(context)) {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.device.ui.measure.mgr.RestoreFactoryManager$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    cnc.a(context);
                }
            });
            return;
        }
        HagridDeviceManagerFragment hagridDeviceManagerFragment = this.e.get();
        if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory()) {
            LogUtil.h("RestoreFactoryManager", "onclick unbind, fragment is destroyed");
            return;
        }
        hagridDeviceManagerFragment.setWiFiDevice(ctkVar);
        if (ctkVar != null) {
            Message obtain = Message.obtain();
            obtain.what = 9;
            obtain.arg1 = 2;
            if (this.c == null) {
                this.c = new cmv(hagridDeviceManagerFragment);
            }
            this.c.sendMessageDelayed(obtain, 8000L);
            if (ctkVar.b().k() == 2) {
                c(context, ctkVar.d());
                return;
            } else {
                c(ctkVar.d(), false);
                return;
            }
        }
        LogUtil.h("R_Weight_RestoreFactoryManager", " onClickUnBind WiFiDevice is null");
    }

    public static /* synthetic */ void a(Context context) {
        LogUtil.a("RestoreFactoryManager", "onClickUnBind start runOnUiThread");
        nrh.c(context, R.string.IDS_device_wifi_not_network);
    }

    private void c(Context context, final String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("R_Weight_RestoreFactoryManager", "sendExitAuthorization deviceid is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("dltId", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        wifiDeviceControlDataModelReq.setDevId(str);
        jbs.a(context).d(wifiDeviceControlDataModelReq, new ICloudOperationResult() { // from class: cnk
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                cnc.this.b(str, (CloudCommonReponse) obj, str2, z);
            }
        });
    }

    /* synthetic */ void b(String str, CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
        if (z) {
            a(str);
            LogUtil.a("RestoreFactoryManager", "sendExitAuthorization success ");
        } else if (cloudCommonReponse != null) {
            int intValue = cloudCommonReponse.getResultCode().intValue();
            LogUtil.h("RestoreFactoryManager", "errorCode = ", Integer.valueOf(intValue), " | errorDes = ", cloudCommonReponse.getResultDesc());
            if (intValue == 112000030) {
                e();
                c();
            } else {
                a(str);
            }
        }
    }

    private void a(String str) {
        WifiDeviceExitAuthorizeSubUserReq wifiDeviceExitAuthorizeSubUserReq = new WifiDeviceExitAuthorizeSubUserReq();
        wifiDeviceExitAuthorizeSubUserReq.setDevId(str);
        jbs.a(cpp.a()).e(wifiDeviceExitAuthorizeSubUserReq, new ICloudOperationResult() { // from class: cnf
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                cnc.this.a((CloudCommonReponse) obj, str2, z);
            }
        });
    }

    /* synthetic */ void a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        LogUtil.a("RestoreFactoryManager", "subUserUnBind :", Boolean.valueOf(z));
        e();
        if (z) {
            c();
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        if (i == 112000030 || i == 112000000) {
            c();
        } else {
            j();
        }
        LogUtil.h("RestoreFactoryManager", " subUserUnBind error:", Integer.valueOf(i), ",resultDesc:", str2);
    }

    public void Je_(final Activity activity) {
        LogUtil.a("RestoreFactoryManager", "unBindDevice start");
        if (activity == null) {
            LogUtil.h("RestoreFactoryManager", "unBindDevice activity is null");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string.IDS_device_bluetooth_open);
        final MeasurableDevice d = ceo.d().d(this.j, false);
        if ((d instanceof ctk) && ((ctk) d).b().k() == 1) {
            builder.d(R.string.IDS_unbind_device_wear_home);
        } else {
            builder.d(R.string.IDS_device_selection_cancel_unbind_device);
        }
        builder.cyU_(R$string.IDS_hw_health_wear_connect_device_unpair_button, new View.OnClickListener() { // from class: cne
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cnc.this.Jb_(d, activity, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cng
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cnc.this.Jc_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    /* synthetic */ void Jb_(HealthDevice healthDevice, Activity activity, View view) {
        if ((healthDevice instanceof ctk) && ((ctk) healthDevice).b().k() == 1) {
            IX_(activity, healthDevice);
        } else {
            a(healthDevice);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void Jc_(View view) {
        HagridDeviceManagerFragment hagridDeviceManagerFragment = this.e.get();
        if (hagridDeviceManagerFragment != null && hagridDeviceManagerFragment.getWeightInteractor() != null) {
            LogUtil.a("RestoreFactoryManager", "start reconnect and start timer");
            hagridDeviceManagerFragment.getWeightInteractor().i();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void IX_(Activity activity, final HealthDevice healthDevice) {
        if (activity == null) {
            LogUtil.h("RestoreFactoryManager", "showStepOnScaleDialog activity is null");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string.IDS_device_bluetooth_open);
        builder.d(R.string.IDS_device_bluetooth_rebind_msg_tip_1).cyU_(R.string.IDS_device_permisson, new View.OnClickListener() { // from class: cna
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cnc.this.Ja_(healthDevice, view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    /* synthetic */ void Ja_(HealthDevice healthDevice, View view) {
        cpa.c(this.j);
        b().a(healthDevice);
        ViewClickInstrumentation.clickOnView(view);
    }

    static class e {
        private static final cnc e = new cnc();
    }
}
