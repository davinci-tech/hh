package com.huawei.hwdevice.mainprocess.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cvx;
import defpackage.jez;
import defpackage.jfo;
import defpackage.jfq;
import defpackage.jgh;
import defpackage.jhg;
import health.compact.a.CommonUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes9.dex */
public class SendDataToDeviceBroadcastReceiver extends BroadcastReceiver {
    private boolean a(int i, int i2) {
        return i == 26 && i2 == 7;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (bIO_(context, intent)) {
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            LogUtil.h("SendDataToDeviceBroadcastReceiver", "onReceive action is null");
            return;
        }
        LogUtil.a("SendDataToDeviceBroadcastReceiver", "onReceive: action: ", action);
        action.hashCode();
        if (action.equals("com.huawei.bone.ACTION_RECEIVE_SEND_SPORT_GOAL")) {
            bIP_(intent);
        } else if (action.equals("com.huawei.bone.ACTION_RECEIVE_SEND_HEART_CONFIG")) {
            b(context);
        } else {
            LogUtil.h("SendDataToDeviceBroadcastReceiver", "onReceive: no support action: ", action);
        }
    }

    private boolean bIO_(Context context, Intent intent) {
        if (!CommonUtil.ce()) {
            LogUtil.h("SendDataToDeviceBroadcastReceiver", "onReceive: not support wear product");
            return true;
        }
        if (context != null && intent != null) {
            return false;
        }
        LogUtil.h("SendDataToDeviceBroadcastReceiver", "onReceive: context or intent is null");
        return true;
    }

    private void b(Context context) {
        jhg.c(context).a(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.receiver.SendDataToDeviceBroadcastReceiver.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("SendDataToDeviceBroadcastReceiver", "setHeartZoneConfig onResponse errorCode: ", Integer.valueOf(i));
                if (i != 0 || obj == null) {
                    return;
                }
                LogUtil.a("SendDataToDeviceBroadcastReceiver", "setHeartZoneConfig onResponse objectData: ", obj.toString());
            }
        }, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void bIP_(android.content.Intent r6) {
        /*
            r5 = this;
            java.lang.String r0 = "SendDataToDeviceBroadcastReceiver"
            r1 = 0
            java.lang.String r2 = "synchronize_data_key"
            byte[] r2 = r6.getByteArrayExtra(r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L22
            java.lang.String r3 = "alarm_data_key"
            byte[] r3 = r6.getByteArrayExtra(r3)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L1f
            java.lang.String r4 = "synchronize_data_deviceinfo_key"
            android.os.Parcelable r6 = r6.getParcelableExtra(r4)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L1d
            boolean r4 = r6 instanceof com.huawei.health.devicemgr.business.entity.DeviceInfo     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L1d
            if (r4 == 0) goto L32
            com.huawei.health.devicemgr.business.entity.DeviceInfo r6 = (com.huawei.health.devicemgr.business.entity.DeviceInfo) r6     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L1d
            r1 = r6
            goto L32
        L1d:
            r6 = move-exception
            goto L25
        L1f:
            r6 = move-exception
            r3 = r1
            goto L25
        L22:
            r6 = move-exception
            r2 = r1
            r3 = r2
        L25:
            java.lang.String r4 = "processActionSportGoal Exception : "
            java.lang.String r6 = com.huawei.haf.common.exception.ExceptionUtils.d(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r4, r6}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r6)
        L32:
            if (r2 == 0) goto L41
            java.lang.String r6 = "tlvDataInfos"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r6)
            r5.c(r2, r1)
            return
        L41:
            if (r3 == 0) goto L58
            java.lang.String r6 = "tlvAlarmInfos"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r6)
            com.huawei.haf.threadpool.ThreadPoolManager r6 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            com.huawei.hwdevice.mainprocess.receiver.SendDataToDeviceBroadcastReceiver$4 r0 = new com.huawei.hwdevice.mainprocess.receiver.SendDataToDeviceBroadcastReceiver$4
            r0.<init>()
            r6.execute(r0)
        L58:
            r5.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.mainprocess.receiver.SendDataToDeviceBroadcastReceiver.bIP_(android.content.Intent):void");
    }

    private int c(byte[] bArr) {
        if (bArr != null && bArr.length >= 2) {
            return a(bArr[0], bArr[1]) ? 1 : -1;
        }
        LogUtil.a("SendDataToDeviceBroadcastReceiver", "tlvDataInfos is null or length is less than 2.");
        return -1;
    }

    private void a(final byte[] bArr, final DeviceInfo deviceInfo) {
        LogUtil.a("SendDataToDeviceBroadcastReceiver", "tlvSyncAccountInfos");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.receiver.SendDataToDeviceBroadcastReceiver.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    LogUtil.b("SendDataToDeviceBroadcastReceiver", "processSyncAccountReport InterruptedException ", e.getMessage());
                }
                jfo.e().a(bArr, deviceInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr) {
        LogUtil.a("SendDataToDeviceBroadcastReceiver", "processSmartAlarmReport enter.");
        jgh d = jgh.d(BaseApplication.getContext());
        for (int i = 0; i < 10; i++) {
            if (jez.e() == null) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    LogUtil.b("SendDataToDeviceBroadcastReceiver", "processSmartAlarmReport Exception : ", ExceptionUtils.d(e));
                }
            } else {
                LogUtil.h("SendDataToDeviceBroadcastReceiver", "phoneService binder is not null.");
                if (bArr != null && bArr.length > 2) {
                    byte b = bArr[1];
                    LogUtil.a("SendDataToDeviceBroadcastReceiver", "commandId: ", Integer.valueOf(b));
                    if (b == 8) {
                        d.b(cvx.d(bArr));
                        return;
                    }
                }
                d.c(cvx.d(bArr));
                return;
            }
        }
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.receiver.SendDataToDeviceBroadcastReceiver.3
            @Override // java.lang.Runnable
            public void run() {
                jfq.c().j();
            }
        });
        if (c(bArr) == 1) {
            a(bArr, deviceInfo);
        } else {
            LogUtil.a("SendDataToDeviceBroadcastReceiver", "tlvDataInfos default");
        }
    }

    private void a() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.submit(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.receiver.SendDataToDeviceBroadcastReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                jhg.c(BaseApplication.getContext()).j();
            }
        });
        newSingleThreadExecutor.shutdown();
    }
}
