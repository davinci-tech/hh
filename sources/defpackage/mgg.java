package defpackage;

import android.util.Pair;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mgg extends BaseCalculator {
    private PluginAchieveAdapter c = getPluginAchieveAdapter();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        e(i);
    }

    private void e(int i) {
        if (this.c == null) {
            this.c = mcv.d(BaseApplication.getContext()).getAdapter();
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long b = mht.b(i, true);
        long b2 = mht.b(i, false);
        ArrayList arrayList = new ArrayList();
        d(b, b2, countDownLatch, arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        a(b, b2, countDownLatch, arrayList3, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_LivingCalculator", "getDietRecordData.await(): catch InterruptedException");
        }
        long size = arrayList.size();
        LogUtil.a("PLGACHIEVE_LivingCalculator", "getLivingRecordData threeRingReachNum = ", Long.valueOf(size), " finishThreeCircleDayList =", arrayList.toString());
        insertData(i, EnumAnnualType.REPORT_LIVING.value(), PrebakedEffectId.RT_SPRING, String.valueOf(size));
        int c = mgx.c(arrayList);
        LogUtil.a("PLGACHIEVE_LivingCalculator", "getLivingRecordData maxContinuousThreeRingDay = ", Integer.valueOf(c));
        insertData(i, EnumAnnualType.REPORT_LIVING.value(), PrebakedEffectId.RT_SWING, String.valueOf(c));
        int size2 = arrayList2.size();
        LogUtil.a("PLGACHIEVE_LivingCalculator", "getLivingRecordData cloversReachNum = ", Integer.valueOf(size2), " finishCloversDayList =", arrayList2.toString());
        insertData(i, EnumAnnualType.REPORT_LIVING.value(), PrebakedEffectId.RT_WIND, String.valueOf(size2));
        int c2 = mgx.c(arrayList2);
        LogUtil.a("PLGACHIEVE_LivingCalculator", "getLivingRecordData maxContinuousCloversDay = ", Integer.valueOf(c2));
        insertData(i, EnumAnnualType.REPORT_LIVING.value(), PrebakedEffectId.RT_VICTORY, String.valueOf(c2));
        int size3 = arrayList3.size();
        LogUtil.a("PLGACHIEVE_LivingCalculator", "getLivingRecordData cloverLeafReachNum = ", Integer.valueOf(size3), " finishRelaxDayList =", arrayList3.toString());
        insertData(i, EnumAnnualType.REPORT_LIVING.value(), PrebakedEffectId.RT_AWARD, String.valueOf(size3));
    }

    private void d(final long j, final long j2, final CountDownLatch countDownLatch, final List<Long> list) {
        LogUtil.a("PLGACHIEVE_LivingCalculator", "dealHistoryThreeCircleData startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateHiHealthData(cgQ_(new Pair<>(Long.valueOf(j), Long.valueOf(j2))), new HiAggregateListener() { // from class: mgg.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list2, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(final List<HiHealthData> list2, int i, int i2) {
                if (koq.b(list2)) {
                    LogUtil.h("PLGACHIEVE_LivingCalculator", "getThreeCircleRecordData dataList isEmpty!");
                    countDownLatch.countDown();
                }
                ThreadPoolManager.d().execute(new Runnable() { // from class: mgg.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        mgg.this.c(j, j2, countDownLatch, list, list2);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, long j2, final CountDownLatch countDownLatch, final List<Long> list, final List<HiHealthData> list2) {
        LogUtil.a("PLGACHIEVE_LivingCalculator", "dealHistoryThreeCircleData startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
        nix.c().c(j, j2, new ResponseCallback() { // from class: mgo
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                mgg.this.e(countDownLatch, list, list2, i, (List) obj);
            }
        });
    }

    /* synthetic */ void e(CountDownLatch countDownLatch, List list, List list2, int i, List list3) {
        if (koq.b(list3)) {
            LogUtil.h("PLGACHIEVE_LivingCalculator", "getThreeCircleRecordData dataList isEmpty!");
            countDownLatch.countDown();
        } else {
            c(list, list2, list3);
            countDownLatch.countDown();
        }
    }

    private void c(List<Long> list, List<HiHealthData> list2, List<HiHealthData> list3) {
        for (HiHealthData hiHealthData : list3) {
            if (hiHealthData != null) {
                Iterator<HiHealthData> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    HiHealthData next = it.next();
                    if (next != null) {
                        long startTime = next.getStartTime();
                        if (HiDateUtil.t(startTime) == HiDateUtil.t(hiHealthData.getStartTime())) {
                            int i = hiHealthData.getInt("calorieGoalValue");
                            int i2 = hiHealthData.getInt("durationGoalValue");
                            int i3 = hiHealthData.getInt("activeGoalValue");
                            int i4 = hiHealthData.getInt("stepGoalValue");
                            int i5 = next.getInt("sport_calorie_sum");
                            int i6 = next.getInt("durationUserValue");
                            int i7 = next.getInt("activeUserValue");
                            int i8 = next.getInt("stepUserValue");
                            boolean z = hiHealthData.getInt("calorieIsRingNew") == 1;
                            boolean z2 = i5 >= i && i != 0;
                            boolean z3 = i6 >= i2 && i2 != 0;
                            boolean z4 = i7 >= i3 && i3 != 0;
                            boolean z5 = i8 >= i4 && i4 != 0;
                            if (!z || !z2 || !z3 || !z4) {
                                if (!z && z5 && z3 && z4) {
                                    list.add(Long.valueOf(startTime));
                                    break;
                                }
                            } else {
                                list.add(Long.valueOf(startTime));
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
    }

    private HiAggregateOption cgQ_(Pair<Long, Long> pair) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(((Long) pair.first).longValue());
        hiAggregateOption.setEndTime(((Long) pair.second).longValue());
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setType(new int[]{40002, 47101, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value(), 40004, 40003, SmartMsgConstant.MSG_TYPE_RIDE_USER});
        hiAggregateOption.setConstantsKey(new String[]{"stepUserValue", "durationUserValue", "activeUserValue", "sport_distance_sum", "sport_calorie_sum", "sport_altitude_offset_sum"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    private void a(long j, long j2, CountDownLatch countDownLatch, List<Long> list, List<Long> list2) {
        List<HealthLifeBean> a2 = dsl.a(j, j2);
        if (koq.b(a2)) {
            LogUtil.h("PLGACHIEVE_LivingCalculator", "getThreeCircleRecordData dataList isEmpty!");
            countDownLatch.countDown();
            return;
        }
        for (HealthLifeBean healthLifeBean : a2) {
            if (healthLifeBean != null) {
                long a3 = HiDateUtil.a(healthLifeBean.getRecordDay());
                if (healthLifeBean.getId() == 5 && a3 != 0 && healthLifeBean.getComplete() >= 1) {
                    list.add(Long.valueOf(a3));
                } else if (healthLifeBean.getId() == 1 && a3 != 0 && healthLifeBean.getComplete() >= 1) {
                    list2.add(Long.valueOf(a3));
                }
            }
        }
        countDownLatch.countDown();
    }
}
