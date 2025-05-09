package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.PeriodRriEntity;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class kes {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f14324a;
    private HiStressMetaData b;
    private List<Integer> c;
    private Semaphore d;

    static class e {

        /* renamed from: a, reason: collision with root package name */
        static final kes f14326a = new kes();
    }

    private int a() {
        return 1;
    }

    private int b() {
        return 1;
    }

    private kes() {
        this.b = null;
        this.d = new Semaphore(0);
        this.f14324a = new ArrayList(16);
        this.c = new ArrayList(16);
    }

    public static kes c() {
        return e.f14326a;
    }

    public List<HiStressMetaData> d(PeriodRriEntity periodRriEntity) {
        int i;
        ArrayList arrayList = new ArrayList(16);
        int fetchVersion = periodRriEntity.fetchVersion();
        if (fetchVersion != 1) {
            i = fetchVersion == 2 ? 120 : 60;
            return arrayList;
        }
        PeriodRriEntity b = b(periodRriEntity);
        Iterator<PeriodRriEntity.RriDataEntity> it = b.fetchRriDataList().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PeriodRriEntity.RriDataEntity next = it.next();
            long fetchStartTime = next.fetchStartTime() * 1000;
            LogUtil.a("PeriodRriParseUtil", "mCycleStartMeasureTime = ", Long.valueOf(fetchStartTime));
            e(next.fetchRriList(), next.fetchSqiList());
            long j = fetchStartTime + (i * 1000);
            float[] d = d(fetchStartTime, j);
            LogUtil.a("PeriodRriParseUtil", "mMeasureCycleLibBack = openLibMeasure:", Long.valueOf(System.currentTimeMillis()));
            if (!kep.d().b()) {
                b.configFailTime(fetchStartTime);
                LogUtil.a("PeriodRriParseUtil", "fail configFailTime:", Long.valueOf(fetchStartTime));
                break;
            }
            if (d != null && d[18] != 0.0f) {
                arrayList.add(a(d, fetchStartTime, j));
                LogUtil.a("PeriodRriParseUtil", "cycleDataList add");
            }
            b(this.f14324a);
            b(this.c);
        }
        ReleaseLogUtil.e("BTSYNC_PeriodRri_PeriodRriParseUtil", "cycleDataList size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private void b(List<?> list) {
        if (list != null) {
            list.clear();
            LogUtil.a("PeriodRriParseUtil", "clearList");
        }
    }

    private HiStressMetaData a(float[] fArr, long j, long j2) {
        LogUtil.a("PeriodRriParseUtil", "getCycleMeasureHiStressMetaData start");
        HiStressMetaData hiStressMetaData = new HiStressMetaData();
        hiStressMetaData.configStressStartTime(j);
        hiStressMetaData.configStressEndTime(j2);
        hiStressMetaData.configStressAlgorithmVer(c(fArr));
        hiStressMetaData.configStressDevNo(keb.a());
        hiStressMetaData.configStressScore(e(fArr));
        hiStressMetaData.configStressGrade(b(fArr));
        hiStressMetaData.configStressCalibFlag(a(fArr));
        hiStressMetaData.configStressMeasureType(1);
        hiStressMetaData.configStressAccFlag(b());
        hiStressMetaData.configStressPpgFlag(a());
        hiStressMetaData.configStressFeature(d(fArr));
        hiStressMetaData.configStressFeatureAtts(e());
        return hiStressMetaData;
    }

    private int c(float[] fArr) {
        return (int) fArr[10];
    }

    private int e(float[] fArr) {
        return (int) fArr[15];
    }

    private int b(float[] fArr) {
        return (int) fArr[16];
    }

    private int a(float[] fArr) {
        int i = (int) fArr[17];
        if (i == 0) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        LogUtil.c("PeriodRriParseUtil", "getStressFlag no match");
        return (int) fArr[17];
    }

    private List<Float> d(float[] fArr) {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < 12; i++) {
            if (i >= 0 && i < 10) {
                arrayList.add(Float.valueOf(fArr[i]));
            }
            if (i >= 10 && i < 12) {
                arrayList.add(Float.valueOf(0.0f));
            }
        }
        return arrayList;
    }

    private List<String> e() {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < 12; i++) {
            arrayList.add("0");
        }
        return arrayList;
    }

    private PeriodRriEntity b(PeriodRriEntity periodRriEntity) {
        ArrayList arrayList = new ArrayList(16);
        long j = 0;
        PeriodRriEntity.RriDataEntity rriDataEntity = null;
        for (PeriodRriEntity.RriDataEntity rriDataEntity2 : periodRriEntity.fetchRriDataList()) {
            long fetchStartTime = rriDataEntity2.fetchStartTime();
            if (fetchStartTime - j > 60 || rriDataEntity == null) {
                PeriodRriEntity.RriDataEntity rriDataEntity3 = new PeriodRriEntity.RriDataEntity();
                rriDataEntity3.configStartTime(fetchStartTime);
                rriDataEntity3.configRriList(new ArrayList(15));
                rriDataEntity3.configSqiList(new ArrayList(15));
                arrayList.add(rriDataEntity3);
                rriDataEntity = rriDataEntity3;
            }
            rriDataEntity.fetchSqiList().addAll(rriDataEntity2.fetchSqiList());
            rriDataEntity.fetchRriList().addAll(rriDataEntity2.fetchRriList());
            j = fetchStartTime;
        }
        periodRriEntity.configRriDataList(arrayList);
        ReleaseLogUtil.e("BTSYNC_PeriodRri_PeriodRriParseUtil", "wrapJavaBean datas size :", Integer.valueOf(arrayList.size()));
        return periodRriEntity;
    }

    private void e(List<Integer> list, List<Integer> list2) {
        for (Integer num : list) {
            if (num.intValue() != 0) {
                this.f14324a.add(num);
            }
        }
        for (Integer num2 : list2) {
            if (num2.intValue() != 0) {
                this.c.add(num2);
            }
        }
    }

    private float[] d(long j, long j2) {
        return kep.d().a(((int) (j2 - j)) / 1000, c(0), this.f14324a, this.c);
    }

    private float[] c(int i) {
        float[] b;
        synchronized (this) {
            b = b(i);
            if (kep.d().b(b)) {
                LogUtil.a("PeriodRriParseUtil", "getStressData is all 0");
                b = b(i);
            }
        }
        return b;
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kes.5
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.pressure_adjust_userinfo");
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    LogUtil.h("PeriodRriParseUtil", "callback is null");
                    return;
                }
                if (userPreference != null) {
                    LogUtil.a("PeriodRriParseUtil", "hiUserPreferenceBase != null");
                    if (!TextUtils.isEmpty(userPreference.getValue())) {
                        String value = userPreference.getValue();
                        LogUtil.a("PeriodRriParseUtil", "getUserPressureAdjustDatas value :", value);
                        iBaseResponseCallback.d(0, value);
                        return;
                    }
                    iBaseResponseCallback.d(100001, null);
                    return;
                }
                iBaseResponseCallback2.d(100006, null);
            }
        });
    }

    private float[] b(final int i) {
        final float[] fArr = new float[18];
        e(new IBaseResponseCallback() { // from class: kes.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0 && (obj instanceof String)) {
                    LogUtil.a("PeriodRriParseUtil", "getUserPressureAdjustDatas objectData :", obj);
                    kes.this.b = jlg.c((String) obj);
                    kes.this.c(fArr, i);
                } else {
                    LogUtil.h("PeriodRriParseUtil", "getUserPressureAdjustDatas error");
                }
                kes.this.d.release();
                LogUtil.a("PeriodRriParseUtil", "getCalibratedData semaphore.release()");
            }
        });
        try {
            if (this.d.tryAcquire(2L, TimeUnit.SECONDS)) {
                LogUtil.a("PeriodRriParseUtil", "getCalibratedData semaphore isTryAcquire()");
            } else {
                LogUtil.a("PeriodRriParseUtil", "getCalibratedData semaphore isTryAcquire() fail");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("PeriodRriParseUtil", "getCalibratedData semaphore InterruptedException");
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float[] fArr, int i) {
        HiStressMetaData hiStressMetaData = this.b;
        if (hiStressMetaData == null) {
            return;
        }
        LogUtil.a("PeriodRriParseUtil", "setCalibratedData mHiStressMetaData:", hiStressMetaData.toString());
        List<Float> fetchStressFeature = this.b.fetchStressFeature();
        for (int i2 = 0; i2 < 18; i2++) {
            if (i2 >= 0 && i2 < 10) {
                fArr[i2] = fetchStressFeature.get(i2).floatValue();
            }
            if (i2 >= 10 && i2 <= 13) {
                fArr[i2] = 0.0f;
            }
            if (i2 > 13 && i2 <= 15) {
                fArr[i2] = 55.0f;
            }
            if (i2 == 16) {
                fArr[i2] = this.b.fetchStressScore();
            }
            if (i2 == 17) {
                fArr[i2] = i;
            }
        }
    }
}
