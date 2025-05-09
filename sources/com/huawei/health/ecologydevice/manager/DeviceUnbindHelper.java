package com.huawei.health.ecologydevice.manager;

import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.ecologydevice.manager.DeviceUnbindHelper;
import com.huawei.health.ecologydevice.networkclient.BindStatusCheckResponse;
import com.huawei.health.ecologydevice.networkclient.OnRequestCallBack;
import com.huawei.health.ecologydevice.networkclient.ResponseResult;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.cpp;
import defpackage.dcz;
import defpackage.dks;
import defpackage.ixx;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class DeviceUnbindHelper {

    /* renamed from: a, reason: collision with root package name */
    private final BaseFragment f2295a;
    private dcz b;
    private final DeviceUnBingListen c;
    private final String d;
    private final String i;
    private CustomTextAlertDialog f = null;
    private final OnRequestCallBack<ResponseResult> j = new OnRequestCallBack<ResponseResult>() { // from class: com.huawei.health.ecologydevice.manager.DeviceUnbindHelper.5
        @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(ResponseResult responseResult) {
            if (DeviceUnbindHelper.this.f2295a == null || DeviceUnbindHelper.this.f2295a.getActivity() == null) {
                LogUtil.h("DeviceUnBindHelper", "onSuccess mBaseFragment = null");
                return;
            }
            if (responseResult.isSuccess()) {
                LogUtil.a("DeviceUnBindHelper", "UnbindSinoDeviceRequest onSuccess");
                DeviceUnbindHelper.this.a();
                return;
            }
            ReleaseLogUtil.e("DEVMGR_DeviceUnBindHelper", "UnbindSinoDeviceRequest onFail");
            dks.WB_(DeviceUnbindHelper.this.f2295a.getActivity(), responseResult.getMsg(), false);
            if (DeviceUnbindHelper.this.c != null) {
                DeviceUnbindHelper.this.c.onFail();
            }
        }

        @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
        public void onFail(int i, Throwable th) {
            ReleaseLogUtil.c("DEVMGR_DeviceUnBindHelper", "onFail errorCode ", Integer.valueOf(i), ",throwable ", th.toString());
        }
    };
    private final HealthDeviceEntryApi e = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);

    public interface DeviceUnBingListen {
        void onFail();

        void onSuccess(int i);
    }

    public DeviceUnbindHelper(BaseFragment baseFragment, String str, String str2, DeviceUnBingListen deviceUnBingListen) {
        this.f2295a = baseFragment;
        this.d = str;
        this.i = str2;
        this.c = deviceUnBingListen;
    }

    public void d() {
        LogUtil.a("DeviceUnBindHelper", "unBindHaveBindDevice to enter");
        if (TextUtils.isEmpty(this.d)) {
            LogUtil.a("DeviceUnBindHelper", "unBindHaveBindDevice productId is null");
            return;
        }
        dcz d = ResourceManager.e().d(this.d);
        this.b = d;
        if (d == null) {
            LogUtil.a("DeviceUnBindHelper", "unBindHaveBindDevice productInfo is null");
        } else if (a(d) || GprsDeviceHelper.c(this.d)) {
            e();
        } else {
            b();
        }
    }

    private boolean a(dcz dczVar) {
        int c = dczVar.x().c();
        return c == 2 || c == 1;
    }

    private void e() {
        BaseFragment baseFragment = this.f2295a;
        if (baseFragment == null || baseFragment.getContext() == null) {
            LogUtil.h("DeviceUnBindHelper", "showBluetoothUnBindDialog mBaseFragment = null");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.f2295a.getContext()).b(R.string.IDS_hw_health_wear_connect_device_unpair_button).d(R.string.IDS_unbind_device_wear_home).cyU_(R.string.IDS_hw_health_wear_connect_device_unpair_button, new View.OnClickListener() { // from class: dbr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceUnbindHelper.this.Tt_(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: dby
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceUnbindHelper.this.Tu_(view);
            }
        }).a();
        this.f = a2;
        a2.setCancelable(false);
        this.f.show();
    }

    public /* synthetic */ void Tt_(View view) {
        CustomTextAlertDialog customTextAlertDialog = this.f;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.f = null;
            j();
        } else {
            LogUtil.a("DeviceUnBindHelper", "ProductIntroductionFragment setPositiveButton mUnbindDialog = null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Tu_(View view) {
        CustomTextAlertDialog customTextAlertDialog = this.f;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.f = null;
        } else {
            LogUtil.a("DeviceUnBindHelper", "ProductIntroductionFragment setNegativeButton mUnbindDialog = null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        BaseFragment baseFragment = this.f2295a;
        if (baseFragment == null || baseFragment.getContext() == null) {
            LogUtil.h("DeviceUnBindHelper", "showOtherUnBindDialog mBaseFragment = null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f2295a.getContext());
        builder.e(R.string.IDS_device_selection_cancel_unbind_device);
        builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: dbv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceUnbindHelper.this.Tv_(view);
            }
        });
        builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: dbs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    public /* synthetic */ void Tv_(View view) {
        j();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void j() {
        BaseFragment baseFragment = this.f2295a;
        if (baseFragment == null || baseFragment.getActivity() == null) {
            LogUtil.h("DeviceUnBindHelper", "unBindDevice mBaseFragment = null");
            return;
        }
        if (dks.i(this.d)) {
            ReleaseLogUtil.e("DEVMGR_DeviceUnBindHelper", "unBindSino request");
            GprsDeviceHelper.TF_(this.f2295a.getActivity(), this.i, this.j);
        } else if (GprsDeviceHelper.c(this.d)) {
            c();
        } else {
            a();
        }
    }

    private void c() {
        ReleaseLogUtil.e("DEVMGR_DeviceUnBindHelper", "deviceUnBind");
        GprsDeviceHelper.c(new OnRequestCallBack<BindStatusCheckResponse.DeviceBindResp>() { // from class: com.huawei.health.ecologydevice.manager.DeviceUnbindHelper.4
            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(BindStatusCheckResponse.DeviceBindResp deviceBindResp) {
                LogUtil.a("DeviceUnBindHelper", "deviceUnBind onSuccess");
                DeviceUnbindHelper.this.a();
            }

            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("DEVMGR_DeviceUnBindHelper", "deviceUnBind onFail errorCode = ", Integer.valueOf(i), "Error:", th.getMessage());
                String str = "errorCode = " + i + "," + th.getMessage();
                if (DeviceUnbindHelper.this.f2295a != null && DeviceUnbindHelper.this.f2295a.getActivity() != null) {
                    dks.WB_(DeviceUnbindHelper.this.f2295a.getActivity(), str, false);
                    if (DeviceUnbindHelper.this.c != null) {
                        DeviceUnbindHelper.this.c.onFail();
                        return;
                    }
                    return;
                }
                LogUtil.h("DeviceUnBindHelper", "onFail mBaseFragment = null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        f();
        ResourceManager.e().f();
        BaseFragment baseFragment = this.f2295a;
        if (baseFragment != null) {
            baseFragment.popupFragment(null);
        }
    }

    private void f() {
        if (this.b == null) {
            LogUtil.h("DeviceUnBindHelper", "unBindDeviceLocal mProductInfo = null");
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(this.d, this.b.n().b()));
        if (i()) {
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), hashMap, 0);
            if (dks.b(this.f2295a.getContext())) {
                this.f2295a.popupFragment(null);
            }
            DeviceUnBingListen deviceUnBingListen = this.c;
            if (deviceUnBingListen != null) {
                deviceUnBingListen.onSuccess(1);
            }
        }
    }

    private boolean i() {
        if (this.e.isDeviceKitUniversal(this.d)) {
            return this.e.unbindDeviceUniversalByUniqueId(this.d, this.i);
        }
        if (!TextUtils.isEmpty(this.i)) {
            return this.e.unbindDeviceByUniqueId(this.i);
        }
        return this.e.unbindDevice(this.d);
    }
}
