package defpackage;

import android.os.Message;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Utils;
import com.huawei.syncmgr.SyncOption;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jwz {

    /* renamed from: a, reason: collision with root package name */
    private int f14161a = 0;
    private jwy g;
    private static final Object b = new Object();
    private static final Object d = new Object();
    private static final Object c = new Object();
    private static final Object e = new Object();

    public jwz(jwy jwyVar) {
        this.g = jwyVar;
    }

    public void d(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr.length < 3) {
            LogUtil.h("HwBasicSyncHelper", "dataInfos length is less than 3.");
        } else {
            blt.d("HwBasicSyncHelper", bArr, "getResult(): ");
            b(bArr, deviceInfo);
        }
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        byte b2 = bArr[1];
        if (b2 == 3) {
            jrb.b("HwBasicSyncHelper", bArr[0], b2);
            n(bArr, deviceInfo);
            return;
        }
        if (b2 == 4) {
            j(bArr, deviceInfo);
            return;
        }
        if (b2 == 5) {
            g(bArr, deviceInfo);
            return;
        }
        if (b2 == 31) {
            a(bArr, deviceInfo);
            return;
        }
        if (b2 != 32) {
            switch (b2) {
                case 10:
                    f(bArr, deviceInfo);
                    break;
                case 11:
                    h(bArr, deviceInfo);
                    break;
                case 12:
                    o(bArr, deviceInfo);
                    break;
                case 13:
                    i(bArr, deviceInfo);
                    break;
                case 14:
                    c(bArr, 14);
                    break;
                case 15:
                    e(bArr, deviceInfo);
                    break;
                default:
                    blt.d("HwBasicSyncHelper", bArr, "command id is other value", Byte.valueOf(b2), "; dataInfos is ");
                    break;
            }
            return;
        }
        c(bArr, deviceInfo);
    }

    public void bKI_(Message message) {
        if (this.g == null) {
            return;
        }
        if (message == null) {
            LogUtil.h("HwBasicSyncHelper", "processFitnessManagerHandler message is null");
            return;
        }
        DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
        LogUtil.a("HwBasicSyncHelper", "handleMessage message ", Integer.valueOf(message.what));
        a(message.what, deviceInfo);
    }

    private void a(int i, DeviceInfo deviceInfo) {
        if (i == 0) {
            LogUtil.b("HwBasicSyncHelper", "sync detail timeout");
            this.g.c(300001, deviceInfo);
            this.g.a(0, deviceInfo);
        } else if (i == 1003) {
            LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailData");
            this.g.e(false, deviceInfo, (IBaseResponseCallback) null);
        } else if (i == 4) {
            LogUtil.a("HwBasicSyncHelper", "Sync today timeout message");
            c(300001, deviceInfo);
            this.g.a(4, deviceInfo);
        } else if (i == 5) {
            LogUtil.a("HwBasicSyncHelper", "Save fitness data timeout message");
            this.g.b(300001, deviceInfo);
        } else {
            LogUtil.h("HwBasicSyncHelper", "unknown message type");
        }
    }

    public void e(byte[] bArr, DeviceInfo deviceInfo) {
        int i;
        LogUtil.a("HwBasicSyncHelper", "onResponse receive bluetooth data", cvx.d(bArr));
        try {
            i = jro.a(bArr);
        } catch (cwg e2) {
            LogUtil.b("HwBasicSyncHelper", "processDeviceDataReport Exception ", ExceptionUtils.d(e2));
            i = 0;
        }
        LogUtil.a("HwBasicSyncHelper", "processDeviceDataReport action :", Integer.valueOf(i));
        if (jxi.e()) {
            if (this.f14161a != 6) {
                jxi.d(deviceInfo.getDeviceIdentify(), 6);
                this.f14161a = 6;
            }
        } else if (this.f14161a != 1) {
            jxi.d(deviceInfo.getDeviceIdentify(), 1);
            this.f14161a = 1;
        }
        if (i == 1) {
            a(deviceInfo);
            return;
        }
        if (i == 2) {
            jxk.d();
            this.g.e(false, deviceInfo, (IBaseResponseCallback) null);
        } else if (i == 3) {
            this.g.e(false, deviceInfo, (IBaseResponseCallback) null);
        } else {
            LogUtil.a("HwBasicSyncHelper", "processDeviceDataReport dataInfos is other action");
            b(i, deviceInfo, bArr);
        }
    }

    private void b(int i, final DeviceInfo deviceInfo, byte[] bArr) {
        if (i == 4) {
            LogUtil.a("HwBasicSyncHelper", "5.7.15 normal trigger, action is DeviceReportThreshold.ACTION_SYNC_CORE_SLEEP_MASK");
            SyncOption c2 = jqr.c(bArr);
            if (c2 == null) {
                ReleaseLogUtil.d("R_HwBasicSyncHelper", "5.7.15 not sync sleep");
                return;
            } else {
                jen.e(kec.c()).a(c2, new IBaseResponseCallback() { // from class: jwz.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.a("HwBasicSyncHelper", "processDeviceDataReportOther sync core sleep onResponse errorCode :", Integer.valueOf(i2));
                        if (i2 == 0) {
                            jwz.this.g.e(false, deviceInfo, new IBaseResponseCallback() { // from class: jwz.5.2
                                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                                /* renamed from: onResponse */
                                public void d(int i3, Object obj2) {
                                    LogUtil.a("HwBasicSyncHelper", "phoneService to syncFitnessDetailData, errorCode=", Integer.valueOf(i3));
                                    if (i3 == 0) {
                                        Utils.syncCloudAfterInsert();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            }
        }
        if (i == 8) {
            LogUtil.a("HwBasicSyncHelper", "action is DeviceReportThreshold.ACTION_SYNC_WORKOUT_MASK");
            if (deviceInfo != null) {
                LogUtil.a("HwBasicSyncHelper", "5.7.15 notify to sync workout data.");
                a(deviceInfo);
                HwExerciseAdviceManager.getInstance().syncDeviceWorkoutRecordInfo();
                return;
            }
            return;
        }
        LogUtil.h("HwBasicSyncHelper", "unknown action");
    }

    private void a(DeviceInfo deviceInfo) {
        this.g.c(new IBaseResponseCallback() { // from class: jwz.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_HwBasicSyncHelper", "syncFitnessTodayData onResponse errorCode:", Integer.valueOf(i));
            }
        }, deviceInfo);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback, boolean z, DeviceInfo deviceInfo) {
        synchronized (b) {
            LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailDataRun enter thread isNeedSyncWork:", Boolean.valueOf(z));
            if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
                Boolean bool = false;
                if (this.g.t.containsKey(deviceInfo.getDeviceIdentify())) {
                    bool = this.g.t.get(deviceInfo.getDeviceIdentify());
                }
                if (bool != null && bool.booleanValue()) {
                    LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailData data syncing");
                    iBaseResponseCallback.d(300002, null);
                    this.g.d(10009, iBaseResponseCallback);
                    if (this.g.d()) {
                        jxi.a("com.huawei.health.fitness_detail_sync_success");
                    }
                    return;
                }
                this.g.t.put(deviceInfo.getDeviceIdentify(), true);
                this.g.ab.d(1);
                a(0, 240000L, deviceInfo);
                this.g.d(10009, iBaseResponseCallback);
                this.g.c();
                jwy jwyVar = this.g;
                jwyVar.aa = jxi.b(jwyVar);
                this.g.h = (int) (System.currentTimeMillis() / 1000);
                long d2 = jxi.d(this.g.h);
                if (this.g.ab.a(this.g) == -1) {
                    jwv jwvVar = this.g.ab;
                    jwy jwyVar2 = this.g;
                    jwvVar.b(jwyVar2, jxi.d(jwyVar2.aa));
                }
                if (jxi.b(deviceInfo) != 3) {
                    d(deviceInfo);
                } else {
                    c(d2);
                }
                return;
            }
            LogUtil.h("HwBasicSyncHelper", "syncFitnessDetailDataRun get device info error");
            iBaseResponseCallback.d(300004, null);
        }
    }

    private void n(byte[] bArr, DeviceInfo deviceInfo) {
        int i;
        ReleaseLogUtil.e("R_HwBasicSyncHelper", "processGetTodayFitnessData Complete");
        LogUtil.a("HwBasicSyncHelper", "onResponse receive bluetooth data", cvx.d(bArr));
        c(4);
        int i2 = 201000;
        try {
            i = 0;
        } catch (cwg unused) {
            iyv.b(100007);
            ReleaseLogUtil.c("R_HwBasicSyncHelper", "processGetTodayFitnessData Exception.");
            this.g.a(-1, deviceInfo);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            i2 = jru.e(bArr);
            iyv.b(i2);
            ReleaseLogUtil.d("R_HwBasicSyncHelper", "processGetTodayFitnessData return errorCode:", Integer.valueOf(i2));
            this.g.a(i2, deviceInfo);
            i = i2;
            c(i, deviceInfo);
        }
        this.g.e.b(jxh.c(bArr), deviceInfo);
        c(i, deviceInfo);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void j(byte[] r6, com.huawei.health.devicemgr.business.entity.DeviceInfo r7) {
        /*
            r5 = this;
            java.lang.String r0 = "processGetRealTimeFrameCount"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HwBasicSyncHelper"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 2
            r2 = 201000(0x31128, float:2.81661E-40)
            r3 = r6[r0]     // Catch: defpackage.cwg -> L44
            r4 = 127(0x7f, float:1.78E-43)
            if (r3 != r4) goto L3b
            int r6 = defpackage.jru.e(r6)     // Catch: defpackage.cwg -> L44
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: defpackage.cwg -> L38
            java.lang.String r2 = "procGetRealTimeFrameCount return errorCode:"
            r3 = 0
            r0[r3] = r2     // Catch: defpackage.cwg -> L38
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)     // Catch: defpackage.cwg -> L38
            r3 = 1
            r0[r3] = r2     // Catch: defpackage.cwg -> L38
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)     // Catch: defpackage.cwg -> L38
            jwy r0 = r5.g     // Catch: defpackage.cwg -> L38
            r2 = 300007(0x493e7, float:4.204E-40)
            r0.b(r2, r7)     // Catch: defpackage.cwg -> L38
            jwy r0 = r5.g     // Catch: defpackage.cwg -> L38
            r0.a(r6, r7)     // Catch: defpackage.cwg -> L38
            return
        L38:
            r0 = move-exception
            r2 = r6
            goto L46
        L3b:
            jwy r0 = r5.g     // Catch: defpackage.cwg -> L44
            jxs r6 = defpackage.jxh.a(r6)     // Catch: defpackage.cwg -> L44
            r0.u = r6     // Catch: defpackage.cwg -> L44
            goto L59
        L44:
            r6 = move-exception
            r0 = r6
        L46:
            java.lang.String r6 = "processGetRealTimeFrameCount Exception "
            java.lang.String r0 = com.huawei.haf.common.exception.ExceptionUtils.d(r0)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)
            jwy r6 = r5.g
            r0 = -1
            r6.a(r0, r7)
        L59:
            jwy r6 = r5.g
            jxs r6 = r6.u
            if (r6 == 0) goto L70
            java.lang.String r6 = "processGetRealTimeFrameCount RealTimeFramePageList is not null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            jwy r6 = r5.g
            jxs r0 = r6.u
            r6.e(r0, r7)
            goto L75
        L70:
            jwy r6 = r5.g
            r6.c(r2, r7)
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jwz.j(byte[], com.huawei.health.devicemgr.business.entity.DeviceInfo):void");
    }

    private void g(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncHelper", "processGetRealTimeCompressedData");
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int e2 = jru.e(bArr);
                LogUtil.b("HwBasicSyncHelper", "processGetRealTimeCompressedData return errorCode:", Integer.valueOf(e2));
                this.g.b(300007, deviceInfo);
                this.g.a(e2, deviceInfo);
            } else {
                this.g.e(jxh.b(bArr));
            }
        } catch (cwg e3) {
            LogUtil.b("HwBasicSyncHelper", "processGetRealTimeCompressedData Exception ", ExceptionUtils.d(e3));
            this.g.a(-1, deviceInfo);
        }
        this.g.n++;
        if (this.g.n < this.g.l) {
            jxk.a(this.g.x.get(this.g.n).intValue(), deviceInfo);
        } else {
            this.g.c(0, deviceInfo);
        }
    }

    private void f(byte[] bArr, DeviceInfo deviceInfo) {
        this.g.w = 0;
        this.g.i = 0;
        LogUtil.a("HwBasicSyncHelper", "processGetSamplePointFrameCount");
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicSyncHelper", "processGetSamplePointFrameCount Exception ", ExceptionUtils.d(e2));
            iyv.b(100007);
            this.g.a(-1, deviceInfo);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            LogUtil.b("HwBasicSyncHelper", "processGetSamplePointFrameCount return errorCode:", Integer.valueOf(e3));
            iyv.b(e3);
            this.g.b(300007, deviceInfo);
            this.g.a(e3, deviceInfo);
            return;
        }
        this.g.w = jxh.f(bArr);
        ReleaseLogUtil.e("R_HwBasicSyncHelper", "7-10 count : ", Integer.valueOf(this.g.w));
        if (this.g.w > 0) {
            LogUtil.a("HwBasicSyncHelper", "processGetSamplePointFrameCount get sample frame index :", Integer.valueOf(this.g.i));
            jxk.c(this.g.i, deviceInfo);
            this.g.i++;
            return;
        }
        b(deviceInfo);
    }

    private void c(long j) {
        if (this.g.aa < this.g.h) {
            if (this.g.aa < j) {
                this.g.aa = j;
            }
        } else if (this.g.aa >= this.g.h && this.g.aa - this.g.h <= 300) {
            LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailData lastSync time is not correct.");
            jwy jwyVar = this.g;
            jwyVar.aa = jwyVar.h - 61;
        } else if (this.g.aa - this.g.h > 300) {
            LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailData lastSync time is not correct and need write back.");
            jwy jwyVar2 = this.g;
            jwyVar2.aa = jwyVar2.h - 61;
            jxi.e(this.g.aa, this.g);
        } else {
            LogUtil.h("HwBasicSyncHelper", "processFitnessDetailOtherDataFormat unknown");
        }
        jxk.a(this.g.aa, this.g.h);
    }

    private void d(DeviceInfo deviceInfo) {
        if (this.g.h - this.g.aa > k.b.l || this.g.aa == 0) {
            jwy jwyVar = this.g;
            jwyVar.aa = jxi.d(jwyVar.h - k.b.l);
            jxi.e(this.g.aa, this.g);
        } else if (this.g.aa >= this.g.h && this.g.aa - this.g.h <= 300) {
            LogUtil.a("HwBasicSyncHelper", "processFitnessDetailSpecialDataFormat lastSync time is not correct.");
            jwy jwyVar2 = this.g;
            jwyVar2.aa = jwyVar2.h - 61;
        } else if (this.g.aa - this.g.h > 300) {
            LogUtil.a("HwBasicSyncHelper", "processFitnessDetailSpecialDataFormat lastSync time is not correct and need write back.");
            jwy jwyVar3 = this.g;
            jwyVar3.aa = jwyVar3.h - 61;
            jxi.e(this.g.aa, this.g);
        } else {
            LogUtil.h("HwBasicSyncHelper", "processFitnessDetailSpecialDataFormat unknown");
        }
        jxk.c(this.g.aa, this.g.h, deviceInfo);
    }

    private void h(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncHelper", "processGetSamplePointFrame");
        synchronized (d) {
            try {
            } catch (cwg e2) {
                LogUtil.b("HwBasicSyncHelper", "processGetSamplePointFrame Exception ", ExceptionUtils.d(e2));
                iyv.b(100007);
                this.g.a(-1, deviceInfo);
            }
            if (bArr[2] == Byte.MAX_VALUE) {
                int e3 = jru.e(bArr);
                iyv.b(e3);
                LogUtil.b("HwBasicSyncHelper", "processGetSamplePointFrame return errorCode:", Integer.valueOf(e3));
                this.g.b(300007, deviceInfo);
                this.g.a(e3, deviceInfo);
                return;
            }
            this.g.v.add(jxh.j(bArr));
            if (this.g.i < this.g.w) {
                jxk.c(this.g.i, deviceInfo);
                this.g.i++;
            } else {
                if (this.g.ab.c() == 2) {
                    jwv jwvVar = this.g.ab;
                    jwy jwyVar = this.g;
                    jwvVar.b(jwyVar, jwyVar.y);
                }
                b(deviceInfo);
            }
        }
    }

    private void o(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncHelper", "processGetSamplePointFrameCount");
        this.g.z = 0;
        this.g.g = 0;
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicSyncHelper", "processGetStatusFrameCount Exception ", ExceptionUtils.d(e2));
            iyv.b(100007);
            this.g.a(-1, deviceInfo);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.b("HwBasicSyncHelper", "processGetStatusFrameCount return errorCode:", Integer.valueOf(e3));
            this.g.b(300007, deviceInfo);
            this.g.a(e3, deviceInfo);
            return;
        }
        this.g.z = jxh.h(bArr);
        ReleaseLogUtil.e("R_HwBasicSyncHelper", "7-12 count : ", Integer.valueOf(this.g.z));
        if (this.g.z > 0) {
            jxk.e(this.g.g, deviceInfo);
            this.g.g++;
            return;
        }
        this.g.c(0, deviceInfo);
    }

    private void i(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncHelper", "processGetStatusFrame Complete");
        synchronized (c) {
            int i = 0;
            try {
            } catch (cwg e2) {
                LogUtil.b("HwBasicSyncHelper", "processGetStatusFrame Exception ", ExceptionUtils.d(e2));
                iyv.b(100007);
                this.g.a(-1, deviceInfo);
                i = 201000;
            }
            if (bArr[2] == Byte.MAX_VALUE) {
                int e3 = jru.e(bArr);
                iyv.b(e3);
                LogUtil.b("HwBasicSyncHelper", "processGetStatusFrame return errorCode:", Integer.valueOf(e3));
                this.g.b(300007, deviceInfo);
                this.g.a(e3, deviceInfo);
                return;
            }
            this.g.ad.add(jxh.i(bArr));
            if (this.g.g < this.g.z) {
                jxk.e(this.g.g, deviceInfo);
                this.g.g++;
            } else {
                this.g.c(i, deviceInfo);
                if (this.g.ab.c() == 2) {
                    this.g.ab.a(this.g, jxi.d(System.currentTimeMillis() / 1000));
                }
            }
        }
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo) {
        this.g.j = 0;
        this.g.d = 0;
        LogUtil.a("HwBasicSyncHelper", "processGetDesFrameCount enter processGetDesFrameCount");
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicSyncHelper", "processGetDesFrameCount Exception ", ExceptionUtils.d(e2));
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            LogUtil.b("HwBasicSyncHelper", "processGetDesFrameCount return errorCode:", Integer.valueOf(jru.e(bArr)));
            this.g.e(300007, deviceInfo);
            return;
        }
        this.g.j = jxh.e(bArr);
        ReleaseLogUtil.e("R_HwBasicSyncHelper", "7-31 count : ", Integer.valueOf(this.g.j));
        if (this.g.j <= 0) {
            LogUtil.h("HwBasicSyncHelper", "desFrameCount Less than zero :", Integer.valueOf(this.g.j));
            this.g.e(0, deviceInfo);
        } else {
            LogUtil.a("HwBasicSyncHelper", "processGetDesFrameCount get sample frame index :", Integer.valueOf(this.g.i), "desFrameCount Greater than zero :", Integer.valueOf(this.g.j));
            jxk.d(this.g.d, deviceInfo);
            this.g.d++;
        }
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        synchronized (e) {
            try {
            } catch (cwg e2) {
                LogUtil.b("HwBasicSyncHelper", "processGetDesFrame Exception ", ExceptionUtils.d(e2));
                sqo.t("processGetDesFrame Exception");
            }
            if (bArr[2] == Byte.MAX_VALUE) {
                this.g.e(300007, deviceInfo);
                return;
            }
            this.g.f.add(jxh.d(bArr));
            if (this.g.d < this.g.j) {
                jxk.d(this.g.d, deviceInfo);
                this.g.d++;
            } else {
                this.g.q = true;
                this.g.e.d(this.g.f, deviceInfo);
            }
        }
    }

    public void d(int i, DeviceInfo deviceInfo) {
        if (i == 1) {
            synchronized (jwy.c) {
                this.g.r = 1;
                this.g.ab.d(1);
                if ((deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) && this.g.p) {
                    jwx.c();
                    return;
                }
                if (this.g.s && this.g.p) {
                    LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailData data syncing");
                    jwx.c();
                    return;
                } else {
                    this.g.s = true;
                    a(0, 240000L, deviceInfo);
                    c();
                    a();
                    return;
                }
            }
        }
        e();
    }

    private void a() {
        long c2 = jwr.c(this.g);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        long j = currentTimeMillis;
        long d2 = jxi.d(j);
        if (jwr.e(this.g) == -1) {
            jwr.e(this.g, jxi.d(c2));
        }
        if (c2 < j) {
            if (c2 < d2) {
                c2 = d2;
            }
        } else if (c2 >= j && c2 - j <= 300) {
            LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailData lastSync time is not correct.");
            c2 = currentTimeMillis - 61;
        } else if (c2 - j > 300) {
            LogUtil.a("HwBasicSyncHelper", "syncFitnessDetailData lastSync time is not correct and need write back.");
            c2 = currentTimeMillis - 61;
            jwr.d(this.g, c2);
        } else {
            LogUtil.h("HwBasicSyncHelper", "syncIntensiveOneDayOldSolutionData unknown");
        }
        LogUtil.a("HwBasicSyncHelper", "mIntensiveStartTime2:", Long.valueOf(c2), ",mIntensiveEndTime", Integer.valueOf(currentTimeMillis));
        jxk.c(c2, j, 0);
    }

    private void e() {
        synchronized (jwy.c) {
            this.g.r = 7;
            this.g.ab.d(2);
            long e2 = jwr.e(this.g);
            long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            if (e2 > jxi.d(currentTimeMillis) - 14400) {
                LogUtil.a("HwBasicSyncHelper", "status stage two already ok");
                this.g.s = false;
                return;
            }
            if (currentTimeMillis - e2 > k.b.l) {
                e2 = jxi.d(currentTimeMillis - k.b.l);
            }
            this.g.y = currentTimeMillis;
            LogUtil.a("HwBasicSyncHelper", "syncIntensiveData2 enter lastStatusTime:", Long.valueOf(e2), "endTime", Long.valueOf(currentTimeMillis));
            jxk.c(e2, currentTimeMillis, 0);
        }
    }

    public void b(DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncHelper", "syncStatusPoint current stage is ", Integer.valueOf(this.g.ab.c()));
        if (deviceInfo != null && this.g.ab.c() == 2) {
            e(deviceInfo);
            return;
        }
        long d2 = jwr.d(this.g);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        LogUtil.a("HwBasicSyncHelper", "syncStatusPoint");
        long d3 = jxi.d(currentTimeMillis) - 14400;
        if (this.g.ab.b(this.g) == -1) {
            this.g.ab.a(this.g, jxi.d(d2));
        }
        if (d2 < currentTimeMillis) {
            if (d2 < d3) {
                d2 = d3;
            }
        } else if (d2 >= currentTimeMillis && d2 - currentTimeMillis <= 300) {
            LogUtil.b("HwBasicSyncHelper", "syncStatusPoint lastStatusTime is not correct.");
            d2 = currentTimeMillis - 61;
        } else if (d2 - currentTimeMillis > 300) {
            LogUtil.b("HwBasicSyncHelper", "syncStatusPoint lastStatusTime is not correct and need write back.");
            d2 = currentTimeMillis - 61;
            jwr.b(this.g, d2);
        } else {
            LogUtil.h("HwBasicSyncHelper", "syncStatusPoint unknown");
            sqo.t("syncStatusPoint unknown");
        }
        jxk.b(d2, currentTimeMillis);
    }

    private void e(DeviceInfo deviceInfo) {
        this.g.ab.d(2);
        long b2 = this.g.ab.b(this.g);
        long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (b2 > jxi.d(currentTimeMillis) - 14400) {
            LogUtil.a("HwBasicSyncHelper", "status stage two already ok");
            this.g.t.remove(deviceInfo.getDeviceIdentify());
        } else {
            if (currentTimeMillis - b2 > k.b.l) {
                b2 = jxi.d(currentTimeMillis - k.b.l);
            }
            jxk.b(b2, currentTimeMillis);
        }
    }

    private void c(byte[] bArr, int i) {
        LogUtil.a("HwBasicSyncHelper", "processSetCmdResult Complete command :", Integer.valueOf(i));
        int i2 = 201000;
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                i2 = jru.e(bArr);
                LogUtil.b("HwBasicSyncHelper", "processSetCmdResult return errorCode:", Integer.valueOf(i2));
            }
        } catch (cwg e2) {
            LogUtil.b("HwBasicSyncHelper", "processSetCmdResult Exception ", ExceptionUtils.d(e2));
        }
        c(i, i2);
    }

    private void c(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwBasicSyncHelper", "doSyncTodayComplete deviceInfo is null");
            return;
        }
        LogUtil.a("HwBasicSyncHelper", "doSyncTodayComplete errorCode", Integer.valueOf(i));
        this.g.t.remove(deviceInfo.getDeviceIdentify());
        c(10008, i);
        jxi.a("com.huawei.bone.action.FITNESS_DATA_TODAY_SYNC");
    }

    private void c(int i, int i2) {
        LogUtil.a("HwBasicSyncHelper", "processCallback callback command:", Integer.valueOf(i), "errorCode:", Integer.valueOf(i2));
        synchronized (jwy.f14154a) {
            e(i, i2);
        }
    }

    private void e(int i, int i2) {
        List<IBaseResponseCallback> list = this.g.b.get(Integer.valueOf(i));
        if (list != null) {
            int i3 = 0;
            while (list.size() > 0) {
                IBaseResponseCallback iBaseResponseCallback = list.get(i3);
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.d(i2, null);
                    list.remove(i3);
                    return;
                } else {
                    list.remove(i3);
                    i3++;
                }
            }
        }
    }

    private void c(int i) {
        if (this.g.k != null) {
            if (this.g.k.hasMessages(i)) {
                this.g.k.removeMessages(i);
                return;
            }
            return;
        }
        LogUtil.h("HwBasicSyncHelper", "fitnessManagerRemoveMessage mHwBasicSyncManagerHandler is null");
    }

    public void a(int i, long j, DeviceInfo deviceInfo) {
        if (this.g.k != null) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = deviceInfo;
            this.g.k.sendMessageDelayed(obtain, j);
            return;
        }
        LogUtil.h("HwBasicSyncHelper", "fitnessManagerSendMessageDelay mHwBasicSyncManagerHandler is null");
    }

    private void c() {
        this.g.f.clear();
    }
}
