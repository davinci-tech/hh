package com.huawei.health.manager.util;

import android.content.Context;
import android.os.Handler;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import defpackage.jdl;
import health.compact.a.LogAnonymous;
import health.compact.a.StepsRecord;
import java.security.SecureRandom;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class UploadUtil {
    private Handler c;
    private Context d;
    private String g;
    private StepsRecord b = null;

    /* renamed from: a, reason: collision with root package name */
    private WriteDbRecord f2811a = new WriteDbRecord();
    private int j = -1;
    private int e = 0;

    public static class UploadStepData {

        /* renamed from: a, reason: collision with root package name */
        int f2812a;
        int b;
        int c;
        int d;
        int e;
    }

    public UploadUtil(Context context, String str, Handler handler) {
        this.d = null;
        this.g = null;
        this.c = null;
        if (context == null || handler == null) {
            LogUtil.a("Step_UploadUtil", "context or handler is null");
            return;
        }
        if (str == null || "".equals(str)) {
            LogUtil.a("Step_UploadUtil", "uuid is null");
            return;
        }
        synchronized (this) {
            this.g = str;
        }
        this.d = context;
        this.c = handler;
    }

    public boolean b(int i) {
        int i2 = this.j;
        if (i2 == -1) {
            this.j = i;
            return false;
        }
        if (i - i2 <= 200) {
            return false;
        }
        this.j = i;
        return true;
    }

    public boolean e(int i) {
        int i2 = this.e + i;
        this.e = i2;
        if (i2 <= 200) {
            return false;
        }
        this.e = 0;
        return true;
    }

    public void b(int i, int i2, int i3, int i4, int i5) {
        UploadStepData uploadStepData = new UploadStepData();
        uploadStepData.e = i;
        uploadStepData.d = i2;
        uploadStepData.b = i3;
        uploadStepData.c = i4;
        uploadStepData.f2812a = i5;
        a(uploadStepData, System.currentTimeMillis());
    }

    public void a(UploadStepData uploadStepData, long j) {
        if (uploadStepData == null) {
            LogUtil.h("Step_UploadUtil", "uploadStepData is null.");
            return;
        }
        LogUtil.a("Step_UploadUtil", "uploadStaticsToDB totalSteps = ", Integer.valueOf(uploadStepData.e), "deviceStep = ", Integer.valueOf(uploadStepData.d), " Floor = ", Integer.valueOf(uploadStepData.c), " Distance = ", Integer.valueOf(uploadStepData.f2812a), " startTime = ", Long.valueOf(j));
        StepsRecord stepsRecord = new StepsRecord();
        stepsRecord.g = uploadStepData.e;
        stepsRecord.d = uploadStepData.b;
        stepsRecord.b = uploadStepData.c;
        stepsRecord.f13139a = uploadStepData.f2812a;
        if (!this.f2811a.d(j, stepsRecord)) {
            LogUtil.h("Step_UploadUtil", "uploadStaticsToDB the same");
            return;
        }
        this.f2811a.c(j, uploadStepData.e, uploadStepData.b, uploadStepData.c, uploadStepData.f2812a);
        ArrayList arrayList = new ArrayList(16);
        if (uploadStepData.e > 0) {
            arrayList.add(a(uploadStepData.e, 40002, j));
        }
        if (uploadStepData.d > 0) {
            arrayList.add(a(uploadStepData.d, 901, j));
        }
        if (uploadStepData.b > 0) {
            arrayList.add(a(uploadStepData.b, 40003, j));
        }
        if (uploadStepData.c > 0) {
            arrayList.add(a(uploadStepData.c, SmartMsgConstant.MSG_TYPE_RIDE_USER, j));
        }
        if (uploadStepData.f2812a > 0) {
            arrayList.add(a(uploadStepData.f2812a, 40004, j));
        }
        if (arrayList.size() > 0) {
            HiHealthManager.d(this.d).insertHiHealthData(HiHealthHelper.b(arrayList), new MyStaticsInsertCallback());
        }
    }

    private HiHealthData a(int i, int i2, long j) {
        synchronized (this) {
            HiHealthData b = HiHealthHelper.b(i, i2, this.g);
            if (b == null) {
                LogUtil.h("Step_UploadUtil", "addUploadData hiHealthData is null.");
                return null;
            }
            if (i2 == 901) {
                b.setStartTime(jdl.t(j));
                b.setEndTime(jdl.e(j));
            } else {
                b.setStartTime(j);
            }
            return b;
        }
    }

    static class MyStaticsInsertCallback implements HiDataOperateListener {
        private MyStaticsInsertCallback() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a("Step_UploadUtil", "uploadStaticsToDb() onResult type = ", Integer.valueOf(i));
            if (i == 0) {
                LogUtil.a("Step_UploadUtil", "uploadStaticsToDb no error.");
            } else {
                LogUtil.a("Step_UploadUtil", "uploadStaticsToDb have error.");
            }
        }
    }

    public void b(String str) {
        synchronized (this) {
            this.g = str;
        }
    }

    public void d(StepsRecord stepsRecord) {
        this.b = stepsRecord;
    }

    public void a(long j) {
        LogUtil.a("Step_UploadUtil", "recognized next day,oldDayTimestamp:", Long.valueOf(j));
        c(j, this.b);
    }

    private void c(final long j, StepsRecord stepsRecord) {
        if (j == -1 || j == 0) {
            LogUtil.h("Step_UploadUtil", "oldDayTimestamp equal -1 or 0");
            return;
        }
        if (stepsRecord == null) {
            LogUtil.h("Step_UploadUtil", "oldDayStepsRecord is null");
            return;
        }
        StepsRecord stepsRecord2 = new StepsRecord();
        stepsRecord2.b(stepsRecord);
        if (!this.f2811a.d(j, stepsRecord2)) {
            LogUtil.a("Step_UploadUtil", "Upload nextDay refused for same,step:", LogAnonymous.b(stepsRecord2.g), " floor:", Integer.valueOf(stepsRecord2.b), " distance:", LogAnonymous.b(stepsRecord2.f13139a), " startTime:", Long.valueOf(j));
            return;
        }
        if (jdl.d(j, System.currentTimeMillis())) {
            LogUtil.h("Step_UploadUtil", "mTimestamp:", Long.valueOf(j), " isSameDay as:", Long.valueOf(System.currentTimeMillis()), " nextDay call error,pls check!!!");
            return;
        }
        int nextInt = new SecureRandom().nextInt(300) * 1000;
        LogUtil.a("Step_UploadUtil", "Upload nextDay machine delay mills:", Integer.valueOf(nextInt), " step:", LogAnonymous.b(stepsRecord2.g), "deviceStep:", LogAnonymous.b(stepsRecord2.c), " floor:", Integer.valueOf(stepsRecord2.b), " distance:", LogAnonymous.b(stepsRecord2.f13139a), " startTime:", Long.valueOf(j));
        final UploadStepData uploadStepData = new UploadStepData();
        uploadStepData.e = stepsRecord2.g;
        uploadStepData.d = stepsRecord2.c;
        uploadStepData.b = stepsRecord2.d;
        uploadStepData.c = stepsRecord2.b;
        uploadStepData.f2812a = stepsRecord2.f13139a;
        this.c.postDelayed(new Runnable() { // from class: com.huawei.health.manager.util.UploadUtil.1
            @Override // java.lang.Runnable
            public void run() {
                UploadUtil.this.a(uploadStepData, j);
            }
        }, nextInt);
    }

    static class WriteDbRecord {

        /* renamed from: a, reason: collision with root package name */
        private StepsRecord f2813a;
        private long c;

        private WriteDbRecord() {
        }

        public void c(long j, int i, int i2, int i3, int i4) {
            this.c = j;
            if (this.f2813a == null) {
                this.f2813a = new StepsRecord();
            }
            this.f2813a.g = i;
            this.f2813a.d = i2;
            this.f2813a.b = i3;
            this.f2813a.f13139a = i4;
        }

        public boolean d(long j, StepsRecord stepsRecord) {
            long j2 = this.c;
            if (j2 == 0 || this.f2813a == null || !jdl.d(j2, j)) {
                return true;
            }
            return !this.f2813a.d(stepsRecord);
        }
    }
}
