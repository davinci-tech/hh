package defpackage;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.LanguageUtil;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class cmp {
    private static final String d = File.separator + "WSP-BLE-LOG-FILE";

    /* renamed from: a, reason: collision with root package name */
    private int f788a;
    private WeakReference<HagridDeviceManagerFragment> c;
    private cfq h;
    private String i;
    private WeakReference<NoTitleCustomAlertDialog> b = new WeakReference<>(null);
    private boolean e = false;
    private WspFileTransferCallback g = new WspFileTransferCallback() { // from class: cmp.5
        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onStart(int i) {
            LogUtil.a("DfxManager", "getLogFileFromDevice initItemList onStart fileListSize = ", Integer.valueOf(i));
            cmp.this.i = null;
            cmp.this.f788a = i;
            cmp.this.e = true;
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onSuccess(int i, int i2, String str) {
            LogUtil.a("DfxManager", "getLogFileFromDevice initItemList onSuccess, fileIndex = ", Integer.valueOf(i), " successCode = ", Integer.valueOf(i2));
            if (i + 1 >= cmp.this.f788a) {
                cmp.this.e = false;
                HagridDeviceManagerFragment hagridDeviceManagerFragment = (HagridDeviceManagerFragment) cmp.this.c.get();
                if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory()) {
                    return;
                }
                hagridDeviceManagerFragment.updateItemView(null);
            }
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onFailure(int i, String str) {
            LogUtil.h("DfxManager", "getLogFileFromDevice initItemList onFailure errorCode = ", Integer.valueOf(i));
            cmp.this.e = false;
            HagridDeviceManagerFragment hagridDeviceManagerFragment = (HagridDeviceManagerFragment) cmp.this.c.get();
            if (hagridDeviceManagerFragment != null && !hagridDeviceManagerFragment.isDestory()) {
                hagridDeviceManagerFragment.updateItemView(null);
            }
            nrh.c(BaseApplication.getContext(), R.string._2130840671_res_0x7f020c5f);
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public void onProgress(int i, int i2, String str) {
            LogUtil.a("DfxManager", "getLogFileFromDevice initItemList onProgress progress = ", Integer.valueOf(i2), "fileIndex = ", Integer.valueOf(i));
            if (TextUtils.isEmpty(cmp.this.i) || !cmp.this.i.equals(str)) {
                cmp.this.i = str;
            }
            StringBuilder sb = new StringBuilder(16);
            StringBuilder sb2 = new StringBuilder(16);
            if (i2 >= 100) {
                i2 = 100;
            }
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                sb2.append("%");
                sb2.append(i2);
            } else {
                sb2.append(i2);
                sb2.append("%");
            }
            int i3 = i + 1;
            if (cmp.this.f788a >= i3) {
                sb.append((CharSequence) sb2);
                sb.append(Constants.LEFT_BRACKET_ONLY);
                sb.append(i3);
                sb.append("/");
                sb.append(cmp.this.f788a);
                sb.append(Constants.RIGHT_BRACKET_ONLY);
                HagridDeviceManagerFragment hagridDeviceManagerFragment = (HagridDeviceManagerFragment) cmp.this.c.get();
                if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory()) {
                    return;
                }
                hagridDeviceManagerFragment.updateItemView(sb.toString());
            }
        }

        @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
        public String getFilePath() {
            try {
                return BaseApplication.getContext().getFilesDir().getCanonicalPath() + cmp.d;
            } catch (IOException unused) {
                LogUtil.h("DfxManager", "createFileWithByte IOException");
                return "";
            }
        }
    };

    public static cmp e() {
        return e.d;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public void IH_(Activity activity, String str, String str2, HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        this.c = new WeakReference<>(hagridDeviceManagerFragment);
        cfq cfqVar = new cfq(activity, str, str2);
        this.h = cfqVar;
        cfqVar.b(this.g);
    }

    public void IK_(cld cldVar, ctk ctkVar, Activity activity) {
        HashMap hashMap = new HashMap(0);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UPLOAD_DEVICE_LOG_2060056.value(), hashMap, 0);
        if (nsn.o()) {
            LogUtil.h("DfxManager", "GET_DEVICE_LOG click too fast.");
            return;
        }
        boolean z = this.e;
        if (z) {
            LogUtil.h("DfxManager", "device log mBleLogUploading: ", Boolean.valueOf(z));
            nrh.b(BaseApplication.getContext(), R.string._2130847870_res_0x7f02287e);
        } else if (!e(cldVar) && ctkVar == null) {
            nrh.b(BaseApplication.getContext(), R.string.IDS_plugin_device_weight_device_not_connect);
        } else if (c()) {
            LogUtil.a("DfxManager", "log upload status: ", Boolean.valueOf(c()));
            this.h.c();
        } else {
            IG_(activity, new View.OnClickListener() { // from class: cms
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cmp.this.IJ_(view);
                }
            });
        }
    }

    /* synthetic */ void IJ_(View view) {
        a(true);
        this.h.c();
        LogUtil.a("DfxManager", "BetaUploadDialog on click positive");
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean e(cld cldVar) {
        if (cldVar != null) {
            return cldVar.b();
        }
        return false;
    }

    private void IG_(Activity activity, View.OnClickListener onClickListener) {
        if (activity == null) {
            LogUtil.a("DfxManager", "showBetaUploadDialog activity is null");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.b.get();
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.h("DfxManager", "showBetaUploadDialog already show");
            return;
        }
        LogUtil.a("DfxManager", "show showBetaUploadDialog");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(R.string.IDS_device_rope_beta_upload_prompt_1);
        builder.czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cmx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cmp.IF_(view);
            }
        });
        builder.czC_(R.string._2130841131_res_0x7f020e2b, onClickListener);
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
        this.b = new WeakReference<>(e2);
    }

    static /* synthetic */ void IF_(View view) {
        LogUtil.a("DfxManager", "BetaUploadDialog on click negative");
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean c() {
        return new DeviceCloudSharePreferencesManager(cpp.a()).e("user_agree_data_key");
    }

    private void a(boolean z) {
        LogUtil.a("DfxManager", "save log upload status", Boolean.valueOf(z));
        new DeviceCloudSharePreferencesManager(cpp.a()).b("user_agree_data_key", z);
    }

    public void a() {
        HashMap hashMap = new HashMap(0);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UPLOAD_DEVICE_BETA_LOG_2060057.value(), hashMap, 0);
        b();
    }

    private void b() {
        Activity activity = BaseApplication.getActivity();
        if (c()) {
            this.h.b();
        } else {
            IG_(activity, new View.OnClickListener() { // from class: cmy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cmp.this.II_(view);
                }
            });
        }
    }

    /* synthetic */ void II_(View view) {
        a(true);
        this.h.b();
        LogUtil.a("DfxManager", "betaFeedback on click positive");
        ViewClickInstrumentation.clickOnView(view);
    }

    static class e {
        private static final cmp d = new cmp();
    }
}
