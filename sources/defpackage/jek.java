package defpackage;

import android.os.Message;
import com.huawei.health.sleep.SleepApi;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcoresleepmgr.SyncBaseFunction;
import com.huawei.hwcoresleepmgr.datatype.NotificationData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jek {
    private static jek b;
    private SyncBaseFunction d;

    private jek() {
    }

    public static jek e() {
        jek jekVar;
        synchronized (jek.class) {
            LogUtil.a("CoreSleepMgrStorage", "get instance");
            if (b == null) {
                b = new jek();
            }
            jekVar = b;
        }
        return jekVar;
    }

    private List<HiHealthData> c(List<bfj> list, String str) {
        LogUtil.a("CoreSleepMgrStorage", "saveTotalSleepCalcFrame");
        ArrayList arrayList = new ArrayList(16);
        if (list == null || list.size() == 0) {
            LogUtil.h("CoreSleepMgrStorage", "currCalcFrameList size is 0");
            return arrayList;
        }
        for (int i = 0; i < list.size(); i++) {
            c(list.get(i), arrayList, str);
            e(list, i);
        }
        return arrayList;
    }

    private void e(List<bfj> list, int i) {
        if (i == list.size() - 1) {
            if (list.get(i).g() == 1.0f) {
                LogUtil.h("CoreSleepMgrStorage", "not validData");
                return;
            }
            int h = list.get(i).h();
            LogUtil.a("CoreSleepMgrStorage", "dailySleepScore :", Integer.valueOf(h));
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.a("CoreSleepMgrStorage", "current time :", Long.valueOf(currentTimeMillis));
            boolean z = currentTimeMillis - list.get(i).f() <= 86400000;
            LogUtil.a("CoreSleepMgrStorage", "is today :", Boolean.valueOf(z));
            if (z && h >= 0 && h <= 100) {
                jeo.c(h, this.d);
            } else {
                LogUtil.h("CoreSleepMgrStorage", "coreSleepScore is error");
            }
        }
    }

    private void c(bfj bfjVar, List<HiHealthData> list, String str) {
        LogUtil.a("CoreSleepMgrStorage", "saveSleepCalcFrame");
        float g = bfjVar.g();
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "validData: ", Float.valueOf(g), ", startTime: ", Long.valueOf(bfjVar.j()));
        if (g == 1.0f) {
            list.add(e(bfjVar, str));
            list.add(j(bfjVar, str));
            list.add(h(bfjVar, str));
            return;
        }
        list.add(e(bfjVar, str));
        list.add(b(bfjVar, str));
        list.add(d(bfjVar, str));
        list.add(c(bfjVar, str));
        list.add(f(bfjVar, str));
        list.add(g(bfjVar, str));
        list.add(h(bfjVar, str));
        list.add(j(bfjVar, str));
        list.add(a(bfjVar, str));
    }

    private HiHealthData d(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44207);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.c());
        return hiHealthData;
    }

    private HiHealthData e(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44201);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.b());
        return hiHealthData;
    }

    private HiHealthData b(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44205);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.d());
        return hiHealthData;
    }

    private HiHealthData c(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44204);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.a());
        return hiHealthData;
    }

    private HiHealthData f(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44203);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.h());
        LogUtil.a("CoreSleepMgrStorage", "updateSleepScore, score:", Integer.valueOf(bfjVar.h()));
        return hiHealthData;
    }

    private HiHealthData g(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44208);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.i());
        return hiHealthData;
    }

    private HiHealthData h(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44206);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.g());
        return hiHealthData;
    }

    private HiHealthData j(bfj bfjVar, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44202);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.f());
        return hiHealthData;
    }

    private HiHealthData a(bfj bfjVar, String str) {
        LogUtil.a("CoreSleepMgrStorage", "updateDeepPartCount:", Integer.valueOf(bfjVar.e()));
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(44106);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setEndTime(HiDateUtil.d(bfjVar.j()));
        hiHealthData.setValue(bfjVar.e());
        return hiHealthData;
    }

    private List<HiHealthData> b(List<bfl> list, String str) {
        LogUtil.a("CoreSleepMgrStorage", "saveSleepStatusFrameList");
        if (list == null || list.size() == 0) {
            LogUtil.h("CoreSleepMgrStorage", "sleepStatusFrameList size is 0");
            return new ArrayList(16);
        }
        Iterator<bfl> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().e().size();
        }
        ArrayList arrayList = new ArrayList(i);
        Iterator<bfl> it2 = list.iterator();
        while (it2.hasNext()) {
            d(it2.next(), arrayList, str);
        }
        return arrayList;
    }

    private void d(bfl bflVar, List<HiHealthData> list, String str) {
        LogUtil.a("CoreSleepMgrStorage", "saveStatusFrame");
        ArrayList<Integer> e = bflVar.e();
        long d = HiDateUtil.d(bflVar.d());
        LogUtil.a("CoreSleepMgrStorage", "start time : ", Long.valueOf(d), "end time : ", Long.valueOf(bflVar.b()));
        if (e == null) {
            LogUtil.h("CoreSleepMgrStorage", "statusFramelist is null");
            return;
        }
        for (Integer num : e) {
            if (d < HiDateUtil.d(bflVar.b())) {
                HiHealthData hiHealthData = new HiHealthData(a(num.intValue()));
                long j = 60000 + d;
                hiHealthData.setTimeInterval(d, j);
                hiHealthData.setDeviceUuid(str);
                list.add(hiHealthData);
                d = j;
            }
        }
    }

    private int a(int i) {
        if (i == 1) {
            return 22101;
        }
        if (i == 2) {
            return 22102;
        }
        if (i == 3) {
            return 22103;
        }
        if (i == 4) {
            return 22104;
        }
        if (i == 5) {
            return 22105;
        }
        LogUtil.a("CoreSleepMgrStorage", "getStatusType is else");
        return 0;
    }

    public void c(jen jenVar, List<bfj> list, List<bfl> list2) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "enter saveCoreSleepData");
        if (jenVar == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepMgrStorage", "coreSleepMgr is null");
            return;
        }
        if (list2 == null || list2.size() == 0) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepMgrStorage", "sleepStatusFrameList.size() is 0");
            jenVar.a();
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (list == null || list.size() == 0) {
            if (d(list2)) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepMgrStorage", "mSleepCalcFramesList size is 0, Has Night Sleep, Can not save sleep data");
                jenVar.a();
                return;
            } else {
                Iterator<bfl> it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                LogUtil.a("CoreSleepMgrStorage", "mSleepCalcFramesList size is 0, No Night Sleep, Only save noon sleep data");
            }
        } else {
            Iterator<bfj> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add(it2.next());
            }
            Iterator<bfl> it3 = list2.iterator();
            while (it3.hasNext()) {
                arrayList.add(it3.next());
            }
            LogUtil.a("CoreSleepMgrStorage", "sleepCalcFrameList size > 0, sleepStatusFrameList size > 0");
        }
        String a2 = jeo.a(this.d);
        List<HiHealthData> b2 = b(arrayList, a2);
        List<HiHealthData> c = c(arrayList2, a2);
        if (list != null && !list.isEmpty()) {
            bfj bfjVar = list.get(list.size() - 1);
            a(jenVar, c, b2, jem.d(bfjVar.b(), bfjVar.f(), bfjVar.h()));
            return;
        }
        a(jenVar, c, b2, null);
    }

    private void a(jen jenVar, List<HiHealthData> list, List<HiHealthData> list2, NotificationData notificationData) {
        LogUtil.a("CoreSleepMgrStorage", "saveCoreSleepDataToHiHealth dataSize:", Integer.valueOf(list.size()), "statusSize,", Integer.valueOf(list2.size()));
        e(list);
        d(jenVar, notificationData, list2);
    }

    private void d(final jen jenVar, final NotificationData notificationData, List<HiHealthData> list) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "enter saveCoreSleepStatusToHiHealth");
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(list);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jek.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "saveCoreSleepStatusToHiHealth onResult type:", Integer.valueOf(i));
                if (i == 0) {
                    jek.this.c();
                    jel.e();
                    jen jenVar2 = jenVar;
                    jenVar2.e(jenVar2.e());
                }
                if (VersionControlUtil.isSupportSleepManagement()) {
                    jek.this.d(jenVar, notificationData, i);
                    return;
                }
                Message obtain = Message.obtain();
                obtain.obj = notificationData;
                obtain.arg1 = i;
                jenVar.bGz_(obtain);
            }
        });
    }

    private void e(List<HiHealthData> list) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "enter saveCoreSleepCalcToHiHealth");
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(list);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jek.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "saveCoreSleepCalcToHiHealth onResult type:", Integer.valueOf(i));
                LogUtil.a("CoreSleepMgrStorage", "saveCoreSleepCalcToHiHealth onResult object: ", obj);
            }
        });
    }

    private boolean d(List<bfl> list) {
        Iterator<bfl> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().e().get(0).intValue() != 5) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("CoreSleepMgrStorage", "start syncSleepAfterInsert");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public void b(SyncBaseFunction syncBaseFunction) {
        this.d = syncBaseFunction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final jen jenVar, final NotificationData notificationData, final int i) {
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "requestAndUpdateDailyResult start");
        sleepApi.requestAndUpdateDailyResult(ggl.g(System.currentTimeMillis()), new SleepDailyProcessResultCallback<fda>() { // from class: jek.4
            @Override // com.huawei.hwbasemgr.SleepDailyProcessResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i2, fda fdaVar) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepMgrStorage", "requestAndUpdateDailyResult errorCode is ", Integer.valueOf(i2));
                Message obtain = Message.obtain();
                obtain.obj = notificationData;
                obtain.arg1 = i;
                obtain.arg2 = i2;
                jenVar.bGz_(obtain);
            }
        });
    }
}
