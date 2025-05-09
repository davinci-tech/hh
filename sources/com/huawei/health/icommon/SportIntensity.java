package com.huawei.health.icommon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.health.icommon.SportIntensity;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.dql;
import defpackage.drd;
import defpackage.jdl;
import defpackage.koq;
import defpackage.mxf;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.DeviceUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.SportIntensityUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class SportIntensity {
    private static mxf d = new mxf();
    private static volatile SportIntensity e;

    /* renamed from: a, reason: collision with root package name */
    private ScheduledExecutorService f2500a;
    private Context c;
    private byte[] i = null;
    private int[] g = null;
    private AtomicBoolean b = new AtomicBoolean(true);
    private String j = null;
    private Handler h = new Handler() { // from class: com.huawei.health.icommon.SportIntensity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                ReleaseLogUtil.c("ISportIntensity", "msg is null!");
                return;
            }
            super.handleMessage(message);
            Bundle data = message.getData();
            int i = message.what;
            if (i == 1000) {
                if (SportIntensity.this.j != null || message.arg1 <= 0) {
                    SportIntensity.this.j();
                    return;
                }
                SportIntensity sportIntensity = SportIntensity.this;
                sportIntensity.j = FoundationCommonUtil.getAndroidId(sportIntensity.c);
                Message obtainMessage = obtainMessage(1000);
                obtainMessage.arg1 = message.arg1 - 1;
                sendMessageDelayed(obtainMessage, 1000L);
                return;
            }
            if (i == 1001) {
                if (data != null) {
                    SportIntensity.this.b(data.getLong("current_day_zero_time"), data.getLong("current_time"));
                }
            } else {
                if (i != 2000) {
                    return;
                }
                if (data == null) {
                    SportIntensity.this.b(jdl.t(System.currentTimeMillis()));
                } else if (jdl.d(data.getLong("current_day_zero_time"), System.currentTimeMillis())) {
                    SportIntensity.this.b(data.getLong("current_day_zero_time"));
                } else {
                    ReleaseLogUtil.e("ISportIntensity", "should not calculate diff day data");
                    SportIntensity.this.b.set(true);
                }
            }
        }
    };

    public SportIntensity(Context context) {
        if (context == null) {
            ReleaseLogUtil.d("ISportIntensity", "SportIntensity context is null");
            this.c = BaseApplication.getContext();
        }
        this.c = context;
        g();
    }

    public static SportIntensity a(Context context) {
        if (e == null) {
            synchronized (SportIntensity.class) {
                if (e == null) {
                    e = new SportIntensity(context);
                }
            }
        }
        return e;
    }

    public void a() {
        LogUtil.c("ISportIntensity", "start module");
        o();
        h();
    }

    private void o() {
        try {
            if (this.f2500a == null) {
                LogUtil.a("ISportIntensity", "startAutoCheck");
                ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.f2500a = newSingleThreadScheduledExecutor;
                newSingleThreadScheduledExecutor.scheduleAtFixedRate(new Runnable() { // from class: dyi
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportIntensity.this.c();
                    }
                }, 0L, 1800000L, TimeUnit.MILLISECONDS);
            } else {
                LogUtil.a("ISportIntensity", "startAutoCheck has execute.");
                e(4);
            }
        } catch (RejectedExecutionException e2) {
            LogUtil.b("ISportIntensity", "startMeasureReconnect exception = ", LogAnonymous.b((Throwable) e2));
        }
    }

    public /* synthetic */ void c() {
        e(1);
    }

    public void e() {
        Object[] objArr = new Object[2];
        objArr[0] = "resetTime";
        objArr[1] = Boolean.valueOf(this.f2500a != null);
        LogUtil.a("ISportIntensity", objArr);
        ScheduledExecutorService scheduledExecutorService = this.f2500a;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.f2500a = null;
        }
    }

    public void d() {
        if (!CommonUtil.aa(this.c)) {
            LogUtil.h("ISportIntensity", "Network not connect");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.icommon.SportIntensity.2
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("ISportIntensity", "updateIndexFile, mIsNewProcess is true");
                    SportIntensity.this.b();
                }
            });
        }
    }

    public void b() {
        if (!AuthorizationUtils.a(this.c)) {
            LogUtil.h("ISportIntensity", "updateNewIndexFile is not authorized");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("configType", "SportIntensity");
        drd.e(new dql("com.huawei.health_Sport_Common", hashMap), "sport_intensity_index", 7, (DownloadCallback<File>) new DownloadCallback() { // from class: com.huawei.health.icommon.SportIntensity.3
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFinish(Object obj) {
                LogUtil.a("ISportIntensity", "updateNewIndexFile onFinish ");
                SportIntensity.this.g();
                SportIntensity.this.i();
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
                LogUtil.a("ISportIntensity", "updateNewIndexFile onProgress ", Boolean.valueOf(z), " fileId ", str);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                LogUtil.h("ISportIntensity", "updateNewIndexFile onFail ");
                SportIntensity.this.g();
                SportIntensity.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("ISportIntensity", "enter sendBroadcast");
        Intent intent = new Intent();
        intent.setPackage(this.c.getPackageName());
        intent.setAction("com.huawei.health.action.ACTION_SPORT_INTENSITY_INDEX");
        this.c.sendBroadcast(intent, LocalBroadcast.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        d = SportIntensityUtil.b();
    }

    public boolean e(int i) {
        ReleaseLogUtil.e("ISportIntensity", "calculateSportIntensity : type ", Integer.valueOf(i), " state ", Boolean.valueOf(this.b.get()));
        if (!this.b.get()) {
            return false;
        }
        this.b.set(false);
        this.i = new byte[ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL];
        this.g = new int[ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL];
        Message obtainMessage = this.h.obtainMessage(1000);
        obtainMessage.arg1 = 3;
        this.h.sendMessage(obtainMessage);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("ISportIntensity", "readStepData");
        final long[] f = f();
        LogUtil.c("ISportIntensity", " timeInterval  ", Long.valueOf(f[0]), "  ", Long.valueOf(f[1]));
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{2});
        hiDataReadOption.setConstantsKey(new String[]{"step"});
        hiDataReadOption.setTimeInterval(f[0], f[1]);
        hiDataReadOption.setAlignType(20001);
        String str = this.j;
        if (str == null) {
            str = FoundationCommonUtil.getAndroidId(this.c);
            this.j = str;
        }
        hiDataReadOption.setDeviceUuid(str);
        e(hiDataReadOption);
        HiHealthManager.d(this.c).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.icommon.SportIntensity.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("ISportIntensity", "readStepData onResult: ", "errorCode = ", Integer.valueOf(i), "anchor = ", Integer.valueOf(i2));
                if (!(obj instanceof SparseArray)) {
                    LogUtil.a("ISportIntensity", "readStepData onResult data is not instanceof SparseArray");
                    SportIntensity sportIntensity = SportIntensity.this;
                    long[] jArr = f;
                    sportIntensity.c(jArr[0], jArr[1]);
                    return;
                }
                Object obj2 = ((SparseArray) obj).get(20001);
                if (!koq.e(obj2, HiHealthData.class)) {
                    LogUtil.a("ISportIntensity", "stepObj is not instanceof List<HiHealthData>");
                    SportIntensity sportIntensity2 = SportIntensity.this;
                    long[] jArr2 = f;
                    sportIntensity2.c(jArr2[0], jArr2[1]);
                    return;
                }
                List list = (List) obj2;
                LogUtil.a("ISportIntensity", "steplist size = ", Integer.valueOf(list.size()));
                SportIntensity.this.e((List<HiHealthData>) list, f);
                SportIntensity sportIntensity3 = SportIntensity.this;
                long[] jArr3 = f;
                sportIntensity3.c(jArr3[0], jArr3[1]);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                if (obj != null) {
                    LogUtil.a("ISportIntensity", "errorCode = ", Integer.valueOf(i2), ", anchor = ", Integer.valueOf(i3));
                } else {
                    LogUtil.h("ISportIntensity", "data is null. errorCode = ", Integer.valueOf(i2), ", anchor = ", Integer.valueOf(i3));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, long[] jArr) {
        StringBuilder sb = new StringBuilder(16);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                ReleaseLogUtil.d("ISportIntensity", "handleStepData hiHealthData is null");
            } else {
                int c = c(hiHealthData.getStartTime() - jArr[0]);
                int i = hiHealthData.getInt("step");
                int type = hiHealthData.getType();
                this.g[c] = type;
                sb.append(" step = ");
                sb.append(i);
                sb.append(", type = ");
                sb.append(type);
                sb.append(",");
                if (i > d.k()) {
                    if (type == 20002 || type == 20004) {
                        this.i[c] = 1;
                    } else if (type == 20003) {
                        this.i[c] = 2;
                    } else {
                        LogUtil.h("ISportIntensity", "type is unknown, type = ", Integer.valueOf(type), ", minuteNumber = ", Integer.valueOf(c));
                    }
                } else if (i <= d.e()) {
                    this.i[c(hiHealthData.getStartTime() - jArr[0])] = 0;
                } else if (type == 20004) {
                    this.i[c(hiHealthData.getStartTime() - jArr[0])] = 1;
                }
            }
        }
        ReleaseLogUtil.e("ISportIntensity", "step list is ", sb.toString());
    }

    private void e(HiDataReadOption hiDataReadOption) {
        String[] ac = SharedPerferenceUtils.ac(this.c);
        if (ac != null) {
            try {
                if ((ac.length >= 2 && Boolean.parseBoolean(ac[1])) || !DeviceUtil.a()) {
                    hiDataReadOption.setReadType(2);
                    return;
                } else {
                    hiDataReadOption.setReadType(0);
                    return;
                }
            } catch (Error e2) {
                LogUtil.b("ISportIntensity", "queryMethod error", LogAnonymous.b((Throwable) e2));
                return;
            } catch (Exception e3) {
                LogUtil.h("ISportIntensity", "queryMethod exception", LogAnonymous.b((Throwable) e3));
                return;
            }
        }
        ReleaseLogUtil.e("ISportIntensity", "device capability is null");
        hiDataReadOption.setReadType(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, long j2) {
        Message obtainMessage = this.h.obtainMessage(1001);
        Bundle bundle = new Bundle();
        bundle.putLong("current_day_zero_time", j);
        bundle.putLong("current_time", j2);
        obtainMessage.setData(bundle);
        this.h.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final long j, long j2) {
        LogUtil.a("ISportIntensity", "readTrackData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{30007});
        String str = this.j;
        if (str == null) {
            str = FoundationCommonUtil.getAndroidId(this.c);
            this.j = str;
        }
        hiDataReadOption.setDeviceUuid(str);
        e(hiDataReadOption);
        ReleaseLogUtil.e("ISportIntensity", "readTrackData optionReadType is ", Integer.valueOf(hiDataReadOption.getReadType()));
        HiHealthNativeApi.a(this.c).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.icommon.SportIntensity.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("ISportIntensity", "readTrackData onResult: ", " errorCode = ", Integer.valueOf(i), " anchor = ", Integer.valueOf(i2));
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() > 0) {
                        Object obj2 = sparseArray.get(30007);
                        if (!koq.e(obj2, HiHealthData.class)) {
                            LogUtil.a("ISportIntensity", "readTrackData onResult obj is not instanceof List<HiHealthData>");
                            return;
                        }
                        List list = (List) obj2;
                        if (SportIntensity.this.i == null) {
                            SportIntensity.this.i = new byte[ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL];
                        }
                        SportIntensity.this.e((List<HiHealthData>) list, j);
                    }
                }
                Message obtainMessage = SportIntensity.this.h.obtainMessage(2000);
                Bundle bundle = new Bundle();
                bundle.putLong("current_day_zero_time", j);
                obtainMessage.setData(bundle);
                SportIntensity.this.h.sendMessage(obtainMessage);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.h("ISportIntensity", "onResultIntent", Integer.valueOf(i), "errorCode: ", Integer.valueOf(i2), "anchor:", Integer.valueOf(i3));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, long j) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                if (hiTrackMetaData.getSportDataSource() != 2 && hiTrackMetaData.getTrackType() != 0) {
                    a(hiHealthData, j);
                }
            } catch (JsonSyntaxException e2) {
                LogUtil.h("ISportIntensity", LogAnonymous.b((Throwable) e2));
            }
        }
    }

    private void a(HiHealthData hiHealthData, long j) {
        if (hiHealthData == null) {
            ReleaseLogUtil.d("ISportIntensity", "setSingleMotionCycleTime hiHealthData is null.");
            return;
        }
        int c = c(hiHealthData.getEndTime() - j);
        for (int c2 = c(hiHealthData.getStartTime() - j); c2 <= c; c2++) {
            this.i[c2] = 3;
        }
    }

    private long[] f() {
        return new long[]{jdl.t(System.currentTimeMillis()), jdl.e(System.currentTimeMillis())};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        LogUtil.a("ISportIntensity", " calculateTotalData");
        if (this.i == null) {
            ReleaseLogUtil.c("ISportIntensity", "time to calculateIntensity, but oneDay array is empty");
            this.b.set(true);
            return;
        }
        List<HiHealthData> e2 = e(j);
        if (e2.size() > 0) {
            LogUtil.a("ISportIntensity", "dataList size is ", Integer.valueOf(e2.size()), "dataList is ", e2.toString());
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(e2);
            HiHealthManager.d(this.c).insertHiHealthData(hiDataInsertOption, new b());
            return;
        }
        this.b.set(true);
        LogUtil.a("ISportIntensity", "sport intensity data null ");
    }

    class b implements HiDataOperateListener {
        private b() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            if (obj != null) {
                LogUtil.a("ISportIntensity", "InsertCallBack errorCode = ", Integer.valueOf(i));
            } else {
                LogUtil.a("ISportIntensity", "InsertCallBack errorCode = ", Integer.valueOf(i), " obj == null ");
            }
            SportIntensity.this.b.set(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.hihealth.HiHealthData> e(long r10) {
        /*
            r9 = this;
            java.lang.String r0 = "setUploadMinuteToList "
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "ISportIntensity"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r2 = 16
            r0.<init>(r2)
            r2 = 0
        L14:
            byte[] r3 = r9.i
            int r4 = r3.length
            if (r2 >= r4) goto L60
            r3 = r3[r2]
            if (r3 != 0) goto L1e
            goto L5d
        L1e:
            long r4 = (long) r2
            r6 = 60000(0xea60, double:2.9644E-319)
            long r4 = r4 * r6
            long r4 = r4 + r10
            r8 = 1
            if (r3 == r8) goto L3a
            r8 = 2
            if (r3 == r8) goto L3a
            r8 = 3
            if (r3 == r8) goto L3a
            r8 = 4
            if (r3 == r8) goto L3a
            java.lang.String r8 = " sportType unkown"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            goto L3b
        L3a:
            r3 = r8
        L3b:
            com.huawei.hihealth.HiHealthData r8 = new com.huawei.hihealth.HiHealthData
            r8.<init>()
            long r6 = r6 + r4
            r8.setTimeInterval(r4, r6)
            r8.setValue(r3)
            r3 = 7
            r8.setType(r3)
            java.lang.String r3 = r9.j
            if (r3 != 0) goto L57
            android.content.Context r3 = r9.c
            java.lang.String r3 = com.huawei.utils.FoundationCommonUtil.getAndroidId(r3)
            r9.j = r3
        L57:
            r8.setDeviceUuid(r3)
            r0.add(r8)
        L5d:
            int r2 = r2 + 1
            goto L14
        L60:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.icommon.SportIntensity.e(long):java.util.List");
    }

    private int c(long j) {
        return (int) (j / 60000);
    }

    private void h() {
        this.j = FoundationCommonUtil.getAndroidId(this.c);
    }
}
