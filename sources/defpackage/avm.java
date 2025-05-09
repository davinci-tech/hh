package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.InvalidProtocolBufferException;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.devicelink.bean.ResponseMsgBody;
import com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelReportMessage;
import com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody;
import com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class avm {

    /* renamed from: a, reason: collision with root package name */
    private static long f252a;
    private static volatile avm b;
    private static final Object d = new Object();
    private volatile int f;
    private boolean j = false;
    private final Handler e = new Handler(Looper.getMainLooper());
    private final Runnable i = new Runnable() { // from class: avm.1
        @Override // java.lang.Runnable
        public void run() {
            avm.this.f = 0;
        }
    };
    private final Runnable c = new Runnable() { // from class: avk
        @Override // java.lang.Runnable
        public final void run() {
            ava.e("");
        }
    };

    private avm() {
    }

    public static avm a() {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new avm();
                }
            }
        }
        return b;
    }

    public void b(int i) {
        this.f = i;
    }

    public void d(int i, spn spnVar) {
        ByteString msgBody;
        HealthModelRequestBody.e b2;
        LogUtil.a("HealthLife_PolicyControl", "receiveDataFromDevice code ", Integer.valueOf(i), " p2pMessage ", spnVar);
        if (i != 530003 || spnVar == null) {
            return;
        }
        byte[] b3 = spnVar.b();
        if (b3 == null || b3.length <= 0) {
            ReleaseLogUtil.a("R_HealthLife_PolicyControl", "receiveDataFromDevice messageData ", b3);
            return;
        }
        try {
            HealthModelRequestMessage.Request parseFrom = HealthModelRequestMessage.Request.parseFrom(b3);
            LogUtil.a("HealthLife_PolicyControl", "receiveDataFromDevice request ", parseFrom);
            if (parseFrom != null && parseFrom.getMsgType() == 2001) {
                double msgVer = parseFrom.getMsgVer();
                bao.c("health_model_device_link_version", String.valueOf(msgVer));
                if (c(msgVer) || (msgBody = parseFrom.getMsgBody()) == null || (b2 = HealthModelRequestBody.e.b(msgBody)) == null) {
                    return;
                }
                int packageSize = msgVer >= 3.0d ? b2.getPackageSize() : 0;
                int pageSize = b2.getPageSize();
                int recordDay = b2.getRecordDay();
                String requestTaskIds = b2.getRequestTaskIds();
                bao.c("health_model_device_link_package_size", String.valueOf(packageSize));
                LogUtil.a("HealthLife_PolicyControl", "receiveDataFromDevice packageSize ", Integer.valueOf(packageSize), " pageSize ", Integer.valueOf(pageSize), " recordDay ", Integer.valueOf(recordDay), " requestId ", requestTaskIds, " version ", Double.valueOf(msgVer));
                e(recordDay, requestTaskIds, pageSize, msgVer, packageSize);
                return;
            }
            ReleaseLogUtil.a("R_HealthLife_PolicyControl", "receiveDataFromDevice request is null or not 2001 type");
        } catch (InvalidProtocolBufferException e) {
            ReleaseLogUtil.c("R_HealthLife_PolicyControl", "receiveDataFromDevice exception ", ExceptionUtils.d(e));
        }
    }

    private boolean c(double d2) {
        if (!azi.ah() || d2 >= 3.0d) {
            return false;
        }
        ReleaseLogUtil.a("R_HealthLife_PolicyControl", "sendLowVersionMessageToDevice version ", Double.valueOf(d2));
        a(1, bae.b(2001, DescriptorProtos.Edition.EDITION_99999_TEST_ONLY_VALUE, nsf.h(R$string.IDS_device_version_low_new)), null, 0);
        return true;
    }

    public void a(int i, ResponseMsgBody responseMsgBody) {
        ReleaseLogUtil.b("R_HealthLife_PolicyControl", "activeSendMsgToDevice action ", Integer.valueOf(i), " mSyncStatus ", Integer.valueOf(this.f));
        if (i == 1 && this.f == 1) {
            return;
        }
        b(i, responseMsgBody, null);
    }

    public void b(final int i, final ResponseMsgBody responseMsgBody, final ResponseCallback<ResponseMsgBody> responseCallback) {
        avc.e().pingComand(new PingCallback() { // from class: avi
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public final void onPingResult(int i2) {
                avm.this.c(i, responseMsgBody, responseCallback, i2);
            }
        }, "com.huawei.health.healthyliving");
    }

    /* synthetic */ void c(final int i, final ResponseMsgBody responseMsgBody, final ResponseCallback responseCallback, int i2) {
        ReleaseLogUtil.b("R_HealthLife_PolicyControl", "activeSendMsgToDevice code ", Integer.valueOf(i2));
        if (i2 == 202) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: avl
                @Override // java.lang.Runnable
                public final void run() {
                    avm.this.a(i, responseMsgBody, responseCallback);
                }
            }, 1000L);
        } else if (responseCallback != null) {
            responseCallback.onResponse(-1, responseMsgBody);
        }
    }

    /* synthetic */ void a(int i, ResponseMsgBody responseMsgBody, ResponseCallback responseCallback) {
        a(i, responseMsgBody, responseCallback, 0);
    }

    private void a(int i, ResponseMsgBody responseMsgBody, ResponseCallback<ResponseMsgBody> responseCallback, int i2) {
        ReleaseLogUtil.b("R_HealthLife_PolicyControl", "sendMsgToDevice action ", Integer.valueOf(i), " callback ", responseCallback, " packageSize ", Integer.valueOf(i2));
        if (responseMsgBody == null) {
            if (responseCallback != null) {
                responseCallback.onResponse(-1, null);
                return;
            }
            return;
        }
        LogUtil.a("HealthLife_PolicyControl", "sendMsgToDevice msgBody ", responseMsgBody.toString());
        if (i == 1) {
            this.f = 1;
            this.e.postDelayed(this.i, 9000L);
        }
        if (i2 > 0) {
            e(i, responseMsgBody, responseCallback, i2);
        } else {
            d(i, responseMsgBody, responseCallback);
        }
    }

    private void d(int i, ResponseMsgBody responseMsgBody, ResponseCallback<ResponseMsgBody> responseCallback) {
        HealthModelReportMessage.Report b2 = bae.b(responseMsgBody);
        spn.b bVar = new spn.b();
        byte[] byteArray = b2.toByteArray();
        bVar.c(byteArray);
        ReleaseLogUtil.b("R_HealthLife_PolicyControl", "packing action ", Integer.valueOf(i), " byteArraySize ", Integer.valueOf(bae.c(byteArray)));
        b(bVar, i, responseCallback, responseMsgBody);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(int r15, com.huawei.basichealthmodel.devicelink.bean.ResponseMsgBody r16, com.huawei.hwbasemgr.ResponseCallback<com.huawei.basichealthmodel.devicelink.bean.ResponseMsgBody> r17, int r18) {
        /*
            r14 = this;
            r1 = r16
            r2 = r18
            java.lang.String r3 = "R_HealthLife_PolicyControl"
            java.util.List r4 = r16.getTasks()
            java.lang.String r5 = "unPacking unPackingList "
            java.lang.Object[] r0 = new java.lang.Object[]{r5, r4}
            java.lang.String r6 = "HealthLife_PolicyControl"
            com.huawei.hwlogsmodel.LogUtil.a(r6, r0)
            boolean r0 = defpackage.koq.b(r4)
            if (r0 == 0) goto L20
            r14.d(r15, r16, r17)
            return
        L20:
            r7 = 0
            r8 = 1
            r9 = 0
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L58
            r10.<init>()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L58
            java.io.DataOutputStream r11 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4f
            r11.<init>(r10)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4f
            java.util.Iterator r0 = r4.iterator()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> Lcd
        L31:
            boolean r12 = r0.hasNext()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> Lcd
            if (r12 == 0) goto L45
            java.lang.Object r12 = r0.next()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> Lcd
            com.huawei.basichealthmodel.devicelink.bean.TaskBean r12 = (com.huawei.basichealthmodel.devicelink.bean.TaskBean) r12     // Catch: java.io.IOException -> L4a java.lang.Throwable -> Lcd
            java.lang.String r12 = r12.toString()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> Lcd
            r11.writeUTF(r12)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> Lcd
            goto L31
        L45:
            byte[] r9 = r10.toByteArray()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> Lcd
            goto L6c
        L4a:
            r0 = move-exception
            goto L5b
        L4c:
            r0 = move-exception
            r11 = r9
            goto L55
        L4f:
            r0 = move-exception
            r11 = r9
            goto L5b
        L52:
            r0 = move-exception
            r10 = r9
            r11 = r10
        L55:
            r9 = r14
            goto Lcf
        L58:
            r0 = move-exception
            r10 = r9
            r11 = r10
        L5b:
            r12 = 2
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r13 = "unPacking exception "
            r12[r7] = r13     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r0 = com.huawei.haf.common.exception.ExceptionUtils.d(r0)     // Catch: java.lang.Throwable -> Lcd
            r12[r8] = r0     // Catch: java.lang.Throwable -> Lcd
            health.compact.a.ReleaseLogUtil.c(r3, r12)     // Catch: java.lang.Throwable -> Lcd
        L6c:
            defpackage.blv.d(r10)
            defpackage.blv.d(r11)
            java.lang.String r0 = "unPacking byteArray "
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r6, r0)
            if (r9 != 0) goto L82
            r14.d(r15, r16, r17)
            return
        L82:
            int r0 = defpackage.bae.c(r9)
            int r0 = r0 / r2
        L87:
            int r9 = r0 + 1
            if (r7 >= r9) goto Lcb
            boolean r9 = defpackage.koq.b(r4)
            if (r9 == 0) goto L99
            java.lang.Object[] r0 = new java.lang.Object[]{r5, r4}
            health.compact.a.ReleaseLogUtil.a(r3, r0)
            goto Lcb
        L99:
            com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelReportMessage$Report r9 = defpackage.bae.e(r1, r2, r4)
            spn$b r10 = new spn$b
            r10.<init>()
            byte[] r9 = r9.toByteArray()
            r10.c(r9)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)
            int r9 = defpackage.bae.c(r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r12 = "unPacking index "
            java.lang.String r13 = " reportByteArray "
            java.lang.Object[] r9 = new java.lang.Object[]{r12, r11, r13, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r6, r9)
            r9 = r14
            r11 = r15
            r12 = r17
            r14.b(r10, r15, r12, r1)
            int r7 = r7 + 1
            goto L87
        Lcb:
            r9 = r14
            return
        Lcd:
            r0 = move-exception
            goto L55
        Lcf:
            defpackage.blv.d(r10)
            defpackage.blv.d(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.avm.e(int, com.huawei.basichealthmodel.devicelink.bean.ResponseMsgBody, com.huawei.hwbasemgr.ResponseCallback, int):void");
    }

    private void b(spn.b bVar, final int i, final ResponseCallback<ResponseMsgBody> responseCallback, final ResponseMsgBody responseMsgBody) {
        avc.e().sendComand(bVar.e(), new SendCallback() { // from class: avm.3
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i2) {
                ReleaseLogUtil.b("R_HealthLife_PolicyControl", "sendCommanded onSendResult code ", Integer.valueOf(i2), " action ", Integer.valueOf(i), " mSyncStatus ", Integer.valueOf(avm.this.f), " callback ", responseCallback);
                if (i == 1) {
                    avm.this.f = 0;
                    avm.this.e.removeCallbacks(avm.this.i);
                }
                if (i == 3) {
                    avm.this.e.removeCallbacks(avm.this.c);
                }
                ResponseCallback responseCallback2 = responseCallback;
                if (responseCallback2 != null) {
                    responseCallback2.onResponse(i2 != 207 ? -1 : 0, responseMsgBody);
                }
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("HealthLife_PolicyControl", "sendCommanded onSendProgress progress ", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("HealthLife_PolicyControl", "sendCommanded onFileTransferReport transferWay ", str);
            }
        });
    }

    public void h() {
        if (!dsl.c("healthLifeThreeLeafSwitch", true)) {
            ReleaseLogUtil.a("R_HealthLife_PolicyControl", "sendDeviceCelebratingMsg switch is closed");
            return;
        }
        long g = CommonUtil.g(bao.a("health_model_device_celebrating"));
        if (g > 0) {
            this.e.postDelayed(this.c, 9000L);
            ReleaseLogUtil.a("R_HealthLife_PolicyControl", "sendDeviceCelebratingMsg lastResult ", Long.valueOf(g));
        } else {
            ava.e(String.valueOf(System.currentTimeMillis()));
            c();
            ava.d();
        }
    }

    public void i() {
        c();
        ava.e();
    }

    public void k() {
        c();
        ava.b();
    }

    public void g() {
        c();
        if (!this.j) {
            LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskDataCache not support");
            return;
        }
        final String a2 = bao.a("health_model_device_link_version");
        LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskDataCache version ", a2);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        final String a3 = bao.a("health_model_device_link_package_size");
        LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskDataCache packageSize ", a3);
        if (this.f == 1) {
            ReleaseLogUtil.a("R_HealthLife_PolicyControl", "sendDeviceTaskDataCache last task is doing");
        } else {
            ava.a(DateFormatUtil.b(System.currentTimeMillis()), null, 0, true, new ResponseCallback() { // from class: avo
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    avm.this.b(a2, a3, i, (ResponseMsgBody) obj);
                }
            });
        }
    }

    /* synthetic */ void b(String str, String str2, int i, ResponseMsgBody responseMsgBody) {
        LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskDataCache resultCode ", Integer.valueOf(i));
        if (responseMsgBody != null) {
            responseMsgBody.setMsgVer(CommonUtil.a(str));
        }
        a(1, responseMsgBody, null, CommonUtil.h(str2));
    }

    public void j() {
        c();
        if (!this.j) {
            LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskData not support");
            return;
        }
        String a2 = bao.a("health_model_device_link_version");
        LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskData version ", a2);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        String a3 = bao.a("health_model_device_link_package_size");
        LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskData packageSize ", a3);
        e(DateFormatUtil.b(System.currentTimeMillis()), null, 0, CommonUtil.a(a2), CommonUtil.h(a3));
    }

    public void e(int i, String str, int i2, final double d2, final int i3) {
        if (this.f == 1) {
            ReleaseLogUtil.a("R_HealthLife_PolicyControl", "sendDeviceTaskData last task is sync doing");
        } else {
            ava.a(i, str, i2, false, new ResponseCallback() { // from class: avj
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i4, Object obj) {
                    avm.this.c(d2, i3, i4, (ResponseMsgBody) obj);
                }
            });
        }
    }

    /* synthetic */ void c(double d2, int i, int i2, ResponseMsgBody responseMsgBody) {
        LogUtil.a("HealthLife_PolicyControl", "sendDeviceTaskData resultCode ", Integer.valueOf(i2));
        if (responseMsgBody != null) {
            responseMsgBody.setMsgVer(d2);
        }
        a(1, responseMsgBody, null, i);
    }

    public boolean d() {
        LogUtil.a("HealthLife_PolicyControl", "isSupportHealthModel mIsSupportCapability ", Boolean.valueOf(this.j));
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public void c() {
        if (this.j) {
            return;
        }
        boolean c = cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthLife_PolicyControl"), 37);
        this.j = c;
        if (c) {
            avc.e();
        }
        LogUtil.a("HealthLife_PolicyControl", "initSupportCapability mIsSupportCapability ", Boolean.valueOf(this.j));
    }

    public void f() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthLife_PolicyControl");
        LogUtil.a("HealthLife_PolicyControl", "registerHealthLife deviceInfo ", deviceInfo);
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.a("HealthLife_PolicyControl", "registerHealthLife device disconnected");
            return;
        }
        this.f = 0;
        boolean c = cwi.c(deviceInfo, 37);
        this.j = c;
        LogUtil.a("HealthLife_PolicyControl", "registerHealthLife mIsSupportCapability ", Boolean.valueOf(c));
        if (this.j) {
            avc.e();
            g();
        }
    }

    public void e() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HealthLife_PolicyControl", "deviceRequestRegister currentTimeMillis ", Long.valueOf(currentTimeMillis), " sLastRequestTime ", Long.valueOf(f252a));
        if (currentTimeMillis - f252a < 12000) {
            return;
        }
        f252a = currentTimeMillis;
        avc.e().c("HealthLife_PolicyControl");
        this.j = true;
        avc.e();
    }
}
