package com.huawei.hwdevice.mainprocess.receiver.hwdfx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jad;
import defpackage.jeq;
import defpackage.jgp;
import defpackage.jsd;
import defpackage.knx;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class DeviceTriggerDfxReceiver extends BroadcastReceiver {
    private Context c = null;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("DeviceTriggerDfxReceiver", "DeviceTriggerDfxReceiver enter");
        this.c = context;
        if (!e()) {
            LogUtil.a("DeviceTriggerDfxReceiver", "not support DXF");
            return;
        }
        if (intent == null) {
            LogUtil.b("DeviceTriggerDfxReceiver", "intent == null");
            return;
        }
        CommonUtil.d();
        jeq.e().init(this.c);
        String action = intent.getAction();
        int intExtra = intent.getIntExtra("isUploadAppLog", 1);
        boolean booleanExtra = intent.getBooleanExtra("isFromBetaClub", false);
        int intExtra2 = intent.getIntExtra("netType", 0);
        LogUtil.a("DeviceTriggerDfxReceiver", "DeviceTriggerDfxReceiver action: ", action, ",netType:", Integer.valueOf(intExtra2));
        if ("com.huawei.bone.dfx.device_trigger_log".equalsIgnoreCase(action)) {
            e(intExtra, booleanExtra, context, intExtra2);
            return;
        }
        if ("com.huawei.bone.dfx.device_trigger_upload_log".equalsIgnoreCase(action)) {
            jsd.a(intExtra2 == 1);
            jgp.a(context).d(intent.getBooleanExtra("isAuto", false), intent.getStringExtra("pre_log_export_mac"));
        } else if ("com.huawei.bone.dfx.device_trigger_retry".equalsIgnoreCase(action)) {
            jsd.a(intExtra2 == 1);
            jgp.a(context).d(intent.getBooleanExtra("isAuto", false), intent.getStringExtra("pre_log_export_mac"));
        } else {
            LogUtil.a("DeviceTriggerDfxReceiver", "DeviceTriggerDfxReceiver others action");
        }
    }

    private void e(final int i, final boolean z, final Context context, final int i2) {
        LogUtil.a("DeviceTriggerDfxReceiver", "enter handleBetaClubCollect");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.receiver.hwdfx.DeviceTriggerDfxReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                jgp.a(context).c(0, new DeviceDfxBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.receiver.hwdfx.DeviceTriggerDfxReceiver.1.2
                    @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
                    public void onProgress(int i3, String str) {
                    }

                    @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
                    public void onSuccess(int i3, String str) {
                        LogUtil.a("DeviceTriggerDfxReceiver", "onSuccess successCode:", Integer.valueOf(i3), ",isUploadAppLog:", Integer.valueOf(i), "，0 upload，1 don't upload");
                        boolean z2 = false;
                        jsd.a(i2 == 1);
                        jgp a2 = jgp.a(DeviceTriggerDfxReceiver.this.c);
                        if (i == 1 && !z) {
                            z2 = true;
                        }
                        a2.d(z2, str);
                    }

                    @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
                    public void onFailure(int i3, String str) {
                        ReleaseLogUtil.e("DEVMGR_DeviceTriggerDfxReceiver", "onFailure errorCode:", Integer.valueOf(i3), ", errorMessage = ", str);
                        if (z) {
                            String j = jsd.j(str);
                            jsd.a(i2 == 1);
                            jgp.a(DeviceTriggerDfxReceiver.this.c).d(false, j);
                        }
                    }
                }, z);
            }
        });
    }

    private boolean e() {
        boolean e = knx.e();
        LogUtil.a("DeviceTriggerDfxReceiver", "BuildConfig.SUPPORT_LOG_FEEDBACK " + CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") + ",experence = " + e);
        return e && jad.d(48) && CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK");
    }
}
