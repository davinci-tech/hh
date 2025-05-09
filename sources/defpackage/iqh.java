package defpackage;

import android.content.Context;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.model.DurationGoal;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Goal;
import com.huawei.hihealth.model.GoalInfo;
import com.huawei.hihealth.model.MetricGoal;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class iqh extends DataObservableNoCallback<Subscriber> {
    private static Context sContext;
    private static List<Integer> sDataTypeSupported;
    private static iqh sInstance;
    private ConcurrentMap<Integer, Integer> subscribedUids = new ConcurrentHashMap();

    private int transformSubscribeType(int i) {
        if (i == 40002) {
            return 1;
        }
        if (i == 10007) {
            return 3;
        }
        return i;
    }

    static {
        ArrayList arrayList = new ArrayList();
        sDataTypeSupported = arrayList;
        arrayList.add(40002);
        sDataTypeSupported.add(10007);
    }

    private iqh() {
    }

    public static iqh getInstance(Context context) {
        iqh iqhVar;
        synchronized (iqh.class) {
            if (sInstance == null) {
                sInstance = new iqh();
                sContext = context;
            }
            iqhVar = sInstance;
        }
        return iqhVar;
    }

    public static boolean supportType(int i) {
        return sDataTypeSupported.contains(Integer.valueOf(i));
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback
    public boolean registerObserver(Parcelable parcelable, Subscriber subscriber) {
        if (!(parcelable instanceof GoalInfo)) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (Goal goal : ((GoalInfo) parcelable).getGoals()) {
            if ((goal instanceof DurationGoal) || (goal instanceof MetricGoal)) {
                int transformSubscribeType = transformSubscribeType(goal.getDataType());
                if (!this.subscribedUids.containsKey(Integer.valueOf(transformSubscribeType))) {
                    arrayList.add(Integer.valueOf(transformSubscribeType));
                }
            }
        }
        super.registerObserver(parcelable, (Parcelable) subscriber);
        if (arrayList.isEmpty()) {
            ReleaseLogUtil.e("HiH_PlatformObservable", "no need to register again");
            return true;
        }
        return subscribeToPlatform(arrayList);
    }

    public boolean unregisterObserver(EventTypeInfo eventTypeInfo, Subscriber subscriber) {
        if (super.unregisterObserver((Parcelable) eventTypeInfo, (EventTypeInfo) subscriber)) {
            if (!isEmpty()) {
                return true;
            }
            clearSubscribeEvent();
            unSubscribePlatformData();
            this.subscribedUids.clear();
            return true;
        }
        ReleaseLogUtil.c("HiH_PlatformObservable", "unregisterObserver failed");
        return false;
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback
    public void notifyDataChanged(Object obj) {
        int intValue = Integer.valueOf(String.valueOf(obj)).intValue();
        for (EventTypeInfo eventTypeInfo : this.eventInfos.keySet()) {
            GoalInfo goalInfo = (GoalInfo) eventTypeInfo;
            if (!goalInfo.isAllAchieved() || !isAllSubscriberNotified(eventTypeInfo)) {
                Iterator<Goal> it = goalInfo.getGoals().iterator();
                while (it.hasNext()) {
                    distributeDataChanged(intValue, goalInfo, it.next(), (Set) this.eventInfos.get(eventTypeInfo));
                }
            }
        }
    }

    public void clearSubscribeEvent() {
        clearObserver();
    }

    public void getDataAndReportNow() {
        Iterator<Integer> it = this.subscribedUids.keySet().iterator();
        while (it.hasNext()) {
            notifyDataChanged(Integer.valueOf(it.next().intValue()));
        }
    }

    private void distributeDataChanged(int i, GoalInfo goalInfo, Goal goal, Set<Subscriber> set) {
        if (i == transformSubscribeType(goal.getDataType())) {
            if (goal.getDataType() == 40002) {
                queryStepDuration(2, (DurationGoal) goal, goalInfo, set);
            } else if (goal.getDataType() == 10007) {
                querySleepDuration((MetricGoal) goal, goalInfo, set);
            } else {
                ReleaseLogUtil.d("HiH_PlatformObservable", "distributeDataChanged nothing to do");
            }
        }
    }

    private boolean subscribeToPlatform(final List<Integer> list) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(list, new HiSubscribeListener() { // from class: iqh.3
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list2, List<Integer> list3) {
                ReleaseLogUtil.d("PlatformObservable", "subscribe fail list ", list3.toString());
                list.removeAll(list3 == null ? new ArrayList<>() : list3);
                for (int i = 0; i < list2.size(); i++) {
                    iqh.this.subscribedUids.put((Integer) list.get(i), list2.get(i));
                }
                zArr[0] = list3 == null || list3.isEmpty();
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                if (ArkUIXConstants.INSERT.equals(str) && hiHealthData != null && hiHealthData.getChangeDataInfos().contains(String.valueOf(40002))) {
                    iqh.this.notifyDataChanged(Integer.valueOf(i));
                }
            }
        });
        try {
            ReleaseLogUtil.e("HiH_PlatformObservable", "subscribeHiHealthData isOnTime: ", Boolean.valueOf(countDownLatch.await(1000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("HiH_PlatformObservable", "interrupted exception when subscribe to platform", LogAnonymous.b((Throwable) e));
        }
        return zArr[0];
    }

    private void queryStepDuration(int i, final DurationGoal durationGoal, final GoalInfo goalInfo, final Set<Subscriber> set) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            hiDataReadOption.setStartTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime());
        } catch (ParseException e) {
            ReleaseLogUtil.c("HiH_PlatformObservable", "parse exception", LogAnonymous.b((Throwable) e));
        }
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{i});
        final ArrayList arrayList = new ArrayList();
        try {
            ify.e().e(hiDataReadOption, (IDataReadResultListener) new IDataReadResultListener.Stub() { // from class: iqh.5
                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i2, int i3) {
                    ReleaseLogUtil.e("HiH_PlatformObservable", "query step errorCode = ", Integer.valueOf(i2), " resultType = ", Integer.valueOf(i3));
                    if (i3 != 2) {
                        List list2 = arrayList;
                        if (list == null) {
                            list = new ArrayList();
                        }
                        list2.addAll(list);
                        return;
                    }
                    ReleaseLogUtil.e("HiH_PlatformObservable", "step duration is ", Integer.valueOf(arrayList.size()));
                    if (arrayList.size() >= durationGoal.getDuration()) {
                        durationGoal.setAchievedFlag(true);
                        irf.c(iqh.sContext, goalInfo, set);
                    }
                }
            }, false);
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_PlatformObservable", "queryStepDuration remote exception", LogAnonymous.b((Throwable) e2));
        }
    }

    private void querySleepDuration(final MetricGoal metricGoal, final GoalInfo goalInfo, final Set<Subscriber> set) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(HiHealthDataType.a());
        hiAggregateOption.setConstantsKey(HiHealthDataKey.b());
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setTimeInterval(System.currentTimeMillis() - Constants.ANALYSIS_EVENT_KEEP_TIME, System.currentTimeMillis());
        final ArrayList arrayList = new ArrayList();
        try {
            ify.e().e(hiAggregateOption, (IAggregateListener) new IAggregateListener.Stub() { // from class: iqh.1
                @Override // com.huawei.hihealth.IAggregateListener
                public void onResult(List list, int i, int i2) {
                    ReleaseLogUtil.e("HiH_PlatformObservable", "query sleep data errorCode = ", Integer.valueOf(i), " resultType ", Integer.valueOf(i2));
                    if (i2 != 2) {
                        List list2 = arrayList;
                        if (list == null) {
                            list = new ArrayList();
                        }
                        list2.addAll(list);
                        return;
                    }
                    HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
                    float f = hiHealthData.getFloat("stat_core_sleep_duration_sum") + hiHealthData.getFloat("stat_core_sleep_noon_duration");
                    ReleaseLogUtil.e("HiH_PlatformObservable", "total time is ", Float.valueOf(f));
                    metricGoal.setAchievedFlag(false);
                    if (f > metricGoal.getValue()) {
                        metricGoal.setAchievedFlag(true);
                    }
                    if (goalInfo.isAllAchieved()) {
                        irf.c(iqh.sContext, goalInfo, set);
                    }
                }
            }, false);
        } catch (RemoteException e) {
            ReleaseLogUtil.c("HiH_PlatformObservable", "querySleepDuration remote exception", LogAnonymous.b((Throwable) e));
        }
    }

    private void unSubscribePlatformData() {
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).unSubscribeHiHealthData(new ArrayList(this.subscribedUids.values()), new HiUnSubscribeListener() { // from class: iqe
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public final void onResult(boolean z) {
                iqh.lambda$unSubscribePlatformData$0(z);
            }
        });
    }

    static /* synthetic */ void lambda$unSubscribePlatformData$0(boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = "unregister from platform ";
        objArr[1] = z ? " success " : " fail ";
        ReleaseLogUtil.e("HiH_PlatformObservable", objArr);
    }
}
