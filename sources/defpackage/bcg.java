package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bcg {
    public static void b(HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_SleepHelper", "getSleepData listener is null");
            return;
        }
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_SleepHelper", "getSleepData healthTaskBean is null");
            d((HealthLifeBean) null, taskDayDataListener, -1);
        } else {
            long c = azi.c(healthLifeBean) / 1000;
            LogUtil.a("HealthLife_SleepHelper", "getSleepData dayBeginTime ", Long.valueOf(c), " healthTaskBean ", healthLifeBean);
            d(c, healthLifeBean, taskDayDataListener);
        }
    }

    public static void nb_(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_SleepHelper", "getWeekSleepData listener is null");
            return;
        }
        if (sparseArray == null || sparseArray.size() < 0) {
            LogUtil.h("HealthLife_SleepHelper", "getWeekSleepData healthTaskBeanArray ", sparseArray);
            nd_(sparseArray, taskDataListener, -1);
        } else {
            int keyAt = sparseArray.keyAt(0);
            long lN_ = azi.lN_(sparseArray) / 1000;
            azi.a(DateFormatUtil.b(jdl.d(System.currentTimeMillis(), -60)) <= keyAt, "HealthLife_SleepHelper", "getWeekSleepData dayBeginTime ", Long.valueOf(lN_), " healthTaskBeanArray ", sparseArray);
            ng_(lN_, sparseArray, taskDataListener);
        }
    }

    private static void d(long j, final HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("HealthLife_SleepHelper", "requestGetCoreSleepDetail healthDataMgrApi is null");
            d(healthLifeBean, taskDayDataListener, -1);
        } else {
            healthDataMgrApi.getCoreSleepDetail(j, 3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 2, new IBaseResponseCallback() { // from class: bcj
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    bcg.d(HealthLifeBean.this, taskDayDataListener, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void d(HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener, int i, Object obj) {
        if (obj != null) {
            c(i, obj, healthLifeBean, taskDayDataListener);
        } else {
            LogUtil.h("HealthLife_SleepHelper", "requestGetCoreSleepDetail errorCode ", Integer.valueOf(i));
            d(healthLifeBean, taskDayDataListener, -1);
        }
    }

    private static void ng_(long j, final SparseArray<HealthLifeBean> sparseArray, final TaskDataListener taskDataListener) {
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("HealthLife_SleepHelper", "requestWeekCoreSleepDetailData weekHealthDataMgrApi is null");
        } else {
            healthDataMgrApi.getCoreSleepDetail(j, 3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 8, new IBaseResponseCallback() { // from class: bci
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    bcg.nc_(sparseArray, taskDataListener, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void nc_(SparseArray sparseArray, TaskDataListener taskDataListener, int i, Object obj) {
        if (obj != null) {
            ne_(i, obj, sparseArray, taskDataListener);
        } else {
            LogUtil.h("HealthLife_SleepHelper", "requestWeekCoreSleepDetailData errorCode ", Integer.valueOf(i));
            nd_(sparseArray, taskDataListener, -1);
        }
    }

    private static void c(int i, Object obj, HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener) {
        if (i == 0 && obj != null) {
            List arrayList = new ArrayList(16);
            if (koq.e(obj, SleepTotalData.class)) {
                arrayList = (List) obj;
            }
            if (arrayList.size() != 0) {
                if (healthLifeBean.getId() == 7 && koq.d(arrayList, 1)) {
                    e(healthLifeBean, (SleepTotalData) arrayList.get(1));
                    d(healthLifeBean, taskDayDataListener, 0);
                    return;
                } else if (healthLifeBean.getId() == 6) {
                    e(healthLifeBean, (SleepTotalData) arrayList.get(0));
                    d(healthLifeBean, taskDayDataListener, 0);
                    return;
                } else {
                    LogUtil.h("HealthLife_SleepHelper", "processFitnessSleepStatisticData other type");
                    d(healthLifeBean, taskDayDataListener, -1);
                    return;
                }
            }
            LogUtil.h("HealthLife_SleepHelper", "sleepTotalDataList is null");
            d(healthLifeBean, taskDayDataListener, -1);
            return;
        }
        LogUtil.h("HealthLife_SleepHelper", "requestGetCoreSleepDetailData else.");
        d(healthLifeBean, taskDayDataListener, -1);
    }

    private static void ne_(int i, Object obj, SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener) {
        if (i == 0 && obj != null) {
            List arrayList = new ArrayList(16);
            if (koq.e(obj, SleepTotalData.class)) {
                arrayList = (List) obj;
            }
            if (arrayList.size() != 0) {
                nf_(sparseArray, taskDataListener, arrayList);
                return;
            } else {
                LogUtil.h("HealthLife_SleepHelper", "sleepTotalDataList is null");
                nd_(sparseArray, taskDataListener, -1);
                return;
            }
        }
        LogUtil.h("HealthLife_SleepHelper", "requestGetCoreSleepDetailData else.");
        nd_(sparseArray, taskDataListener, -1);
    }

    private static void nf_(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener, List<SleepTotalData> list) {
        for (SleepTotalData sleepTotalData : list) {
            if (sleepTotalData != null) {
                long sleepDayTime = sleepTotalData.getSleepDayTime();
                HealthLifeBean healthLifeBean = sparseArray.get(DateFormatUtil.b(jdl.v(sleepDayTime)));
                if (healthLifeBean != null && healthLifeBean.getId() == 7) {
                    e(healthLifeBean, sleepTotalData);
                }
                HealthLifeBean healthLifeBean2 = sparseArray.get(DateFormatUtil.b(sleepDayTime));
                if (healthLifeBean2 != null && healthLifeBean2.getId() == 6) {
                    e(healthLifeBean2, sleepTotalData);
                }
            }
        }
        nd_(sparseArray, taskDataListener, 0);
    }

    private static void nd_(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener, int i) {
        if (taskDataListener != null) {
            taskDataListener.onDataChange(i, sparseArray);
        }
    }

    private static void d(HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener, int i) {
        if (taskDayDataListener != null) {
            taskDayDataListener.onDataChange(i, healthLifeBean);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x005c, code lost:
    
        if (r2 > 1440) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void e(com.huawei.health.healthmodel.bean.HealthLifeBean r8, com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData r9) {
        /*
            java.lang.String r0 = r8.getTarget()
            int r1 = r8.getRecordDay()
            int r0 = defpackage.bcm.c(r0)
            int r2 = r8.getId()
            r3 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r5 = 7
            r6 = 1440(0x5a0, float:2.018E-42)
            java.lang.String r7 = "HealthLife_SleepHelper"
            if (r2 != r5) goto L45
            int r2 = b(r9)
            java.lang.String r3 = "setHealthTaskBean ID_SLEEP taskTime "
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            com.huawei.hwlogsmodel.LogUtil.a(r7, r3)
            int r2 = r2 + 1200
            if (r2 < r6) goto L43
            java.lang.String r1 = r8.getTimeZone()
            java.util.TimeZone r1 = defpackage.jdl.d(r1)
            long r3 = defpackage.azi.c(r8)
            long r3 = defpackage.jdl.s(r3, r1)
            int r1 = com.huawei.hwcommonmodel.utils.DateFormatUtil.c(r3, r1)
            goto L5e
        L43:
            r3 = r2
            goto L6a
        L45:
            int r2 = r8.getId()
            r5 = 6
            if (r2 != r5) goto L60
            int r2 = a(r9)
            java.lang.String r3 = "setHealthTaskBean ID_WAKE_UP taskTime "
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            com.huawei.hwlogsmodel.LogUtil.a(r7, r3)
            int r2 = r2 + 1200
            if (r2 <= r6) goto L43
        L5e:
            int r2 = r2 - r6
            goto L43
        L60:
            java.lang.String r2 = "setHealthTaskBean other type"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.h(r7, r2)
        L6a:
            a(r8, r3, r1, r0, r9)
            a(r8, r9, r3, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bcg.e(com.huawei.health.healthmodel.bean.HealthLifeBean, com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData):void");
    }

    private static int b(SleepTotalData sleepTotalData) {
        int fallTime = sleepTotalData.getFallTime();
        if (fallTime <= 0) {
            fallTime = sleepTotalData.getCommonFallTime();
        }
        if (fallTime <= 0) {
            fallTime = sleepTotalData.getManualFallTime();
        }
        LogUtil.a("HealthLife_SleepHelper", "getSleepTime fallTime ", Integer.valueOf(sleepTotalData.getFallTime()), " commonFallTime ", Integer.valueOf(sleepTotalData.getCommonFallTime()), " manualFallTime ", Integer.valueOf(sleepTotalData.getManualFallTime()));
        return fallTime;
    }

    private static int a(SleepTotalData sleepTotalData) {
        int wakeUpTime = sleepTotalData.getWakeUpTime();
        if (wakeUpTime <= 0) {
            wakeUpTime = sleepTotalData.getCommonWakeUpTime();
        }
        if (wakeUpTime <= 0) {
            wakeUpTime = sleepTotalData.getManualWakeUpTime();
        }
        LogUtil.a("HealthLife_SleepHelper", "getWakeTime wakeUpTime ", Integer.valueOf(sleepTotalData.getWakeUpTime()), " commonWakeUpTime ", Integer.valueOf(sleepTotalData.getCommonWakeUpTime()), " manualRisingTime ", Integer.valueOf(sleepTotalData.getManualWakeUpTime()));
        return wakeUpTime;
    }

    private static void a(HealthLifeBean healthLifeBean, int i, int i2, int i3, SleepTotalData sleepTotalData) {
        if (healthLifeBean.getId() == 6 && i2 == healthLifeBean.getRecordDay() && Math.abs(i3 - i) <= 60 && healthLifeBean.getComplete() == 0 && d(sleepTotalData)) {
            LogUtil.a("HealthLife_SleepHelper", "getHealthTaskBean ID_WAKE_UP ", bcm.a(i2, i));
            d(healthLifeBean);
        }
        if (healthLifeBean.getId() == 7 && i2 == healthLifeBean.getRecordDay() && i <= i3 + 60 && healthLifeBean.getComplete() == 0 && d(sleepTotalData)) {
            LogUtil.a("HealthLife_SleepHelper", "getHealthTaskBean ID_SLEEP ", bcm.a(i2, i));
            d(healthLifeBean);
        }
    }

    private static void a(HealthLifeBean healthLifeBean, SleepTotalData sleepTotalData, int i, int i2) {
        String a2 = bcm.a(i2, i);
        LogUtil.a("HealthLife_SleepHelper", "setHealthTaskBeanResult result ", a2, " healthTaskBean ", healthLifeBean);
        if (d(sleepTotalData)) {
            healthLifeBean.setResult(a2);
        }
    }

    private static boolean d(SleepTotalData sleepTotalData) {
        int type = sleepTotalData.getType();
        int totalSleepTime = sleepTotalData.getTotalSleepTime();
        int manualBedTime = sleepTotalData.getManualBedTime();
        int shallowSleepTime = sleepTotalData.getShallowSleepTime();
        int deepSleepTime = sleepTotalData.getDeepSleepTime();
        int commonWakeUpTime = sleepTotalData.getCommonWakeUpTime();
        int commonFallTime = sleepTotalData.getCommonFallTime();
        LogUtil.a("HealthLife_SleepHelper", "checkDataValid type ", Integer.valueOf(type), " totalSleepTime ", Integer.valueOf(totalSleepTime), " manualBedTime ", Integer.valueOf(manualBedTime), " shallowSleepTime ", Integer.valueOf(shallowSleepTime), " deepSleepTime ", Integer.valueOf(deepSleepTime), " commonWakeUpTime ", Integer.valueOf(commonWakeUpTime), " commonFallTime ", Integer.valueOf(commonFallTime));
        if (type == 31 && (totalSleepTime > 0 || manualBedTime > 0)) {
            return true;
        }
        if (type == 30) {
            return totalSleepTime > 0 || manualBedTime > 0 || shallowSleepTime > 0 || deepSleepTime > 0 || commonWakeUpTime != commonFallTime;
        }
        return false;
    }

    private static void d(HealthLifeBean healthLifeBean) {
        if (healthLifeBean.getComplete() <= 0) {
            aza.d(healthLifeBean.getId(), 1);
        }
        healthLifeBean.setComplete(1);
        long currentTimeMillis = System.currentTimeMillis();
        healthLifeBean.setTimestamp(currentTimeMillis);
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        healthLifeBean.setIsUpdated(true);
        healthLifeBean.setSyncStatus(0);
    }
}
