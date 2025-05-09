package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kcl {
    private static kcl c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private long f14282a = 0;
    private ExtendHandler b = HandlerCenter.yt_(new b(), "HwMenstrualHelper");

    private kcl() {
    }

    public static kcl a() {
        kcl kclVar;
        synchronized (d) {
            if (c == null) {
                c = new kcl();
            }
            kclVar = c;
        }
        return kclVar;
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwMenstrualHelper", "queryManualData callback null");
        } else {
            ThreadPoolManager.d().d("HwMenstrualHelper", new Runnable() { // from class: kcl.5
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("com.huawei.health.mc");
                    if (userPreference == null || userPreference.getValue() == null) {
                        LogUtil.h("HwMenstrualHelper", "queryManualData userPreference value empty");
                        iBaseResponseCallback.d(-1, null);
                        return;
                    }
                    String value = userPreference.getValue();
                    LogUtil.a("HwMenstrualHelper", "queryManualData value:", value);
                    try {
                        JSONObject jSONObject = new JSONObject(value);
                        iBaseResponseCallback.d(0, new int[]{jSONObject.getInt("cycleLength"), jSONObject.getInt("periodLength")});
                    } catch (JSONException unused) {
                        LogUtil.b("HwMenstrualHelper", "queryMenstrualSwitch JSONException");
                        iBaseResponseCallback.d(-1, null);
                    }
                }
            });
        }
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwMenstrualHelper", "queryModifyTime callback is null");
            return;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long currentTimeMillis = System.currentTimeMillis();
        hiAggregateOption.setStartTime(currentTimeMillis - ThirdLoginDataStorageUtil.REFRESH_TOKEN_INTERVAL);
        hiAggregateOption.setEndTime(currentTimeMillis + 7776000000L);
        hiAggregateOption.setConstantsKey(new String[]{"menstrual_quantity"});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setType(new int[]{10010});
        hiAggregateOption.setSort("modified_time", 1);
        hiAggregateOption.setCount(1);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kcl.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "queryModifyTime errorCode :", Integer.valueOf(i));
                if (list == null || list.size() == 0) {
                    LogUtil.b("HwMenstrualHelper", "queryModifyTime datas is null");
                    iBaseResponseCallback.d(-1, null);
                } else {
                    ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "queryModifyTime:", Long.valueOf(list.get(0).getModifiedTime()));
                    iBaseResponseCallback.d(0, Long.valueOf(list.get(0).getModifiedTime() / 1000));
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.h("HwMenstrualHelper", "queryModifyTime onResultIntent");
            }
        });
    }

    public void b(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getSeveralMenstrualCycleData");
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwMenstrualHelper", "getSeveralMenstrualCycleData call back is null");
        } else {
            a(i, i2, iBaseResponseCallback);
        }
    }

    private void a(final int i, final int i2, final IBaseResponseCallback iBaseResponseCallback) {
        this.f14282a = 0L;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        final long currentTimeMillis = System.currentTimeMillis();
        ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "nowTime:", Long.valueOf(currentTimeMillis));
        hiAggregateOption.setStartTime(currentTimeMillis - (((i + 1) * 90) * 86400000));
        hiAggregateOption.setEndTime(((i2 + 1) * 90 * 86400000) + currentTimeMillis);
        hiAggregateOption.setType(new int[]{10010});
        hiAggregateOption.setConstantsKey(new String[]{"menstrual_status"});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setAggregateType(1);
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = iBaseResponseCallback;
        this.b.sendMessage(obtain, PreConnectManager.CONNECT_INTERNAL);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kcl.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getAllData errorCode:", Integer.valueOf(i3));
                kcl.this.b.removeMessages(3);
                if (list == null || list.size() == 0) {
                    ReleaseLogUtil.c("DEVMGR_HwMenstrualHelper", "getAllData empty");
                    iBaseResponseCallback.d(-1, null);
                } else {
                    ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getAllData size:", Integer.valueOf(list.size()));
                    kcl.this.a(list, currentTimeMillis, iBaseResponseCallback, i, i2);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
                LogUtil.h("HwMenstrualHelper", "getAllData onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, long j, IBaseResponseCallback iBaseResponseCallback, int i, int i2) {
        Iterator<HiHealthData> it = list.iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            HiHealthData next = it.next();
            int i4 = (int) next.getDouble("menstrual_status");
            long startTime = next.getStartTime();
            if ((i4 == 101 || i4 == 301) && j > startTime) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 < 0) {
            ReleaseLogUtil.c("DEVMGR_HwMenstrualHelper", "recentMenstrualFirstDayIndex error");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        LogUtil.a("HwMenstrualHelper", "key index:", Integer.valueOf(i3), "key time:", Long.valueOf(list.get(i3).getStartTime()));
        List<kcr> c2 = c(list, i3, i);
        List<kcr> a2 = a(list, i3, i2);
        if (c2.size() + a2.size() == 0) {
            LogUtil.b("HwMenstrualHelper", "dealKeyData no data error");
            iBaseResponseCallback.d(-1, null);
        } else {
            Collections.reverse(c2);
            c2.addAll(a2);
            iBaseResponseCallback.d(0, new kcs(this.f14282a / 1000, c2));
        }
    }

    private List<kcr> a(List<HiHealthData> list, int i, int i2) {
        ArrayList arrayList = new ArrayList(16);
        kcr kcrVar = new kcr();
        long startTime = list.get(i).getStartTime();
        int i3 = (int) list.get(i).getDouble("menstrual_status");
        kcrVar.e(i3, startTime / 1000);
        kcrVar.e(i3 == 101);
        LogUtil.a("HwMenstrualHelper", "recentMenstrualFirstDay:", Integer.valueOf(i3), ",time:", Long.valueOf(startTime));
        int i4 = i - 1;
        while (true) {
            if (i4 < 0) {
                break;
            }
            if (list.get(i4).getModifiedTime() > this.f14282a) {
                this.f14282a = list.get(i4).getModifiedTime();
            }
            int i5 = (int) list.get(i4).getDouble("menstrual_status");
            if (i5 == 101 || i5 == 301) {
                int startTime2 = (int) ((list.get(i4).getStartTime() - startTime) / 86400000);
                long startTime3 = list.get(i4).getStartTime();
                kcrVar.b(startTime2);
                arrayList.add(kcrVar);
                LogUtil.a("HwMenstrualHelper", "getFutureMenstrual start:", Long.valueOf(list.get(i4).getStartTime()));
                kcrVar = new kcr();
                kcrVar.e(i5 == 101);
                startTime = startTime3;
            }
            kcrVar.e(i5, list.get(i4).getStartTime() / 1000);
            if (arrayList.size() >= i2 + 1) {
                LogUtil.a("HwMenstrualHelper", "future data is enough");
                break;
            }
            if (i4 == 0) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "start getLastCycle");
                d(list, i, arrayList, kcrVar);
            }
            i4--;
        }
        return arrayList;
    }

    private void d(List<HiHealthData> list, int i, List<kcr> list2, kcr kcrVar) {
        if (!list2.isEmpty()) {
            int e = list2.get(list2.size() - 1).e();
            ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getLastPredictCycle with Previous cycle:", Integer.valueOf(e));
            kcrVar.b(e);
            list2.add(kcrVar);
            return;
        }
        for (int i2 = i + 1; i2 < list.size(); i2++) {
            int i3 = (int) list.get(i2).getDouble("menstrual_status");
            if (i3 == 101 || i3 == 301) {
                int startTime = (int) ((list.get(i).getStartTime() - list.get(i2).getStartTime()) / 86400000);
                ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getLastPredictCycle traverse:", Integer.valueOf(startTime));
                kcrVar.b(startTime);
                list2.add(kcrVar);
                return;
            }
        }
    }

    private List<kcr> c(List<HiHealthData> list, int i, int i2) {
        ArrayList arrayList = new ArrayList(16);
        if (i2 <= 0) {
            LogUtil.b("HwMenstrualHelper", "device do not need past data");
            return arrayList;
        }
        kcr kcrVar = new kcr();
        long startTime = list.get(i).getStartTime();
        int i3 = i + 1;
        while (true) {
            if (i3 >= list.size()) {
                break;
            }
            if (list.get(i3).getModifiedTime() > this.f14282a) {
                this.f14282a = list.get(i3).getModifiedTime();
            }
            int i4 = (int) list.get(i3).getDouble("menstrual_status");
            kcrVar.e(i4, list.get(i3).getStartTime() / 1000);
            if (i4 == 101 || i4 == 301) {
                int startTime2 = (int) ((startTime - list.get(i3).getStartTime()) / 86400000);
                long startTime3 = list.get(i3).getStartTime();
                if (startTime2 > 180) {
                    startTime2 = 180;
                }
                kcrVar.b(startTime2);
                kcrVar.e(i4 == 101);
                arrayList.add(kcrVar);
                LogUtil.a("HwMenstrualHelper", "getPastMenstrual start:", Long.valueOf(list.get(i3).getStartTime()));
                kcrVar = new kcr();
                startTime = startTime3;
            }
            if (arrayList.size() >= i2) {
                LogUtil.a("HwMenstrualHelper", "past data is enough");
                break;
            }
            i3++;
        }
        return arrayList;
    }

    public void c(int i, int i2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwMenstrualHelper", "getBodyStatus callback null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(currentTimeMillis - (i * 86400000));
        hiAggregateOption.setEndTime(currentTimeMillis + (i2 * 86400000));
        hiAggregateOption.setType(new int[]{10010});
        hiAggregateOption.setConstantsKey(new String[]{"menstrual_status"});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setAggregateType(1);
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = iBaseResponseCallback;
        this.b.sendMessage(obtain, PreConnectManager.CONNECT_INTERNAL);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kcl.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getBodyStatus errorCode:", Integer.valueOf(i3));
                kcl.this.b.removeMessages(2);
                if (list == null || list.isEmpty()) {
                    ReleaseLogUtil.d("DEVMGR_HwMenstrualHelper", "getBodyStatus null");
                    iBaseResponseCallback.d(-1, null);
                } else {
                    ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getBodyStatus size:", Integer.valueOf(list.size()));
                    iBaseResponseCallback.d(0, list);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
                LogUtil.h("HwMenstrualHelper", "getBodyStatus onResultIntent");
            }
        });
    }

    public void d(List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (list == null || list.isEmpty() || iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualHelper", "insertMenstrualDataToDatabase empty warning");
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(list);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = iBaseResponseCallback;
        this.b.sendMessage(obtain, PreConnectManager.CONNECT_INTERNAL);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kcl.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "insertData errorCode:", Integer.valueOf(i));
                kcl.this.b.removeMessages(1);
                iBaseResponseCallback.d(i, null);
            }
        });
    }

    public String c(List<kcr> list) {
        int i = 0;
        if (list == null || list.isEmpty()) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualHelper", "list is empty");
            return cvx.e(0) + cvx.e(0);
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int i2 = 0;
        for (kcr kcrVar : list) {
            if (kcrVar.h() + ((kcrVar.e() * 86400000) / 1000) < currentTimeMillis) {
                i++;
            }
            if (kcrVar.h() > currentTimeMillis) {
                i2++;
            }
        }
        ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getMouthModel:", Integer.valueOf(i), Integer.valueOf(i2));
        return cvx.e(i) + cvx.e(i2);
    }

    static class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.a("HwMenstrualHelper", "OperationTimeoutHandler operation:", Integer.valueOf(message.what));
            if (!(message.obj instanceof IBaseResponseCallback)) {
                return true;
            }
            ((IBaseResponseCallback) message.obj).d(150001, null);
            return true;
        }
    }

    public DeviceInfo c(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("HwMenstrualHelper", "getActiveMainNotAw70Device() deviceInfoList is null.");
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (!bku.e(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("HwMenstrualHelper", "getActiveMainNotAw70Device deviceInfo ", deviceInfo);
        return deviceInfo;
    }

    public void a(long j, IBaseResponseCallback iBaseResponseCallback) {
        if (j == 0) {
            LogUtil.h("HwMenstrualHelper", "getMenstrualEndData lastTime is 0");
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwMenstrualHelper", "getMenstrualEndData callback null");
            return;
        }
        long j2 = j + 86400000;
        long j3 = j + 7776000000L;
        LogUtil.a("HwMenstrualHelper", "getMenstrualEndData enter, startTime: ", Long.valueOf(j2), ", endTime: ", Long.valueOf(j3));
        c(j2, j3, 4, iBaseResponseCallback);
    }

    private void c(long j, long j2, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(new int[]{10010});
        hiAggregateOption.setConstantsKey(new String[]{"menstrual_status"});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setAggregateType(1);
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = iBaseResponseCallback;
        this.b.sendMessage(obtain, PreConnectManager.CONNECT_INTERNAL);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kcl.8
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "getMenstrualData errorCode:", Integer.valueOf(i2));
                kcl.this.b.removeMessages(i);
                kcl.this.c(i2, list, i, iBaseResponseCallback);
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                LogUtil.h("HwMenstrualHelper", "getMenstrualData onResultIntent");
                iBaseResponseCallback.d(i3, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, List<HiHealthData> list, int i2, IBaseResponseCallback iBaseResponseCallback) {
        if (i != 0) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualHelper", "getMenstrualEndData aggregateHiHealthData fail");
            iBaseResponseCallback.d(i, null);
        } else if (list == null || list.size() == 0) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualHelper", "getMenstrualEndData empty");
            iBaseResponseCallback.d(-1, null);
        } else if (i2 == 4) {
            iBaseResponseCallback.d(i, b(list));
        }
    }

    private List<HiHealthData> b(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(16);
        for (HiHealthData hiHealthData : list) {
            int i = (int) hiHealthData.getDouble("menstrual_status");
            if (i != 0) {
                HiHealthData hiHealthData2 = new HiHealthData(10010);
                LogUtil.a("HwMenstrualHelper", "getMenstrualEndData StartTime: ", Long.valueOf(hiHealthData.getStartTime()), ", old state: ", Integer.valueOf(i), ", new state: ", 0);
                hiHealthData.putDouble("menstrual_status", 0.0d);
                hiHealthData2.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
                hiHealthData2.putDouble("menstrual_quantity", hiHealthData.getDouble("menstrual_quantity"));
                hiHealthData2.putDouble("menstrual_dysmenorrhea", hiHealthData.getDouble("menstrual_dysmenorrhea"));
                hiHealthData2.putDouble("menstrual_physical", hiHealthData.getDouble("menstrual_physical"));
                hiHealthData2.putDouble("menstrual_sub_status", hiHealthData.getDouble("menstrual_sub_status"));
                hiHealthData2.setDeviceUuid("-1");
                arrayList.add(hiHealthData2);
            }
        }
        ReleaseLogUtil.e("DEVMGR_HwMenstrualHelper", "dealEndDirtyData newDataInfos size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }
}
