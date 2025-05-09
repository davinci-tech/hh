package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiBloodOxygenRemindMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kej {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14316a = new Object();
    private int b;
    private List<ken> c;
    private int d;
    private ExtendHandler e;
    private boolean i;

    private kej() {
        this.d = 0;
        this.b = 0;
        this.c = new ArrayList(16);
        this.i = false;
    }

    public static kej e() {
        return d.c;
    }

    private void d(int i) {
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j) {
        String a2 = a();
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "setLastTimestamp lastTimestamp: ", Long.valueOf(j), " saveKey: ", a2);
        kdz.b().setSharedPreference(a2, String.valueOf(j), new StorageParams());
    }

    private long b() {
        String a2 = a();
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "getLastTimestamp saveKey: ", a2);
        String sharedPreference = kdz.b().getSharedPreference(a2);
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "getLastTimestamp lastTimestamp: ", sharedPreference);
        return CommonUtil.n(BaseApplication.getContext(), sharedPreference);
    }

    private String a() {
        return knl.a(KeyValDbManager.b(BaseApplication.getContext()).e("user_id") + keg.e());
    }

    public void b(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "enter getBloodOxygenDownRemind");
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "getHeartRateRaiseAlarm deviceIdentify is null");
            return;
        }
        DeviceCapability a2 = keg.a(deviceIdentify);
        if (a2 == null || !a2.isSupportBloodOxygenDownRemind()) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "BloodOxygenDownRemind is not support");
            return;
        }
        synchronized (f14316a) {
            if (this.i) {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "startSyncLowSpo2Alarm is syncing");
                return;
            }
            this.i = true;
            ExtendHandler yt_ = HandlerCenter.yt_(new c(), "AlarmData_BloodOxygenDownRemindMgr");
            this.e = yt_;
            yt_.sendEmptyMessage(1, 1200000L);
            long b = b();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "handleGetBloodOxygenDownRemindCount lastStatusTime: ", Long.valueOf(b), " endTime: ", Long.valueOf(currentTimeMillis));
            long j = b - currentTimeMillis;
            if (Math.abs(j) > k.b.l || b == 0) {
                b = keg.a(currentTimeMillis - k.b.l);
                e(b);
            } else if (b >= currentTimeMillis && j <= 300) {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "handleGetBloodOxygenDownRemindCount syncStatusPoint lastStatusTime is not correct.");
                b = currentTimeMillis - 61;
            } else if (j > 300) {
                ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "handleGetBloodOxygenDownRemindCount lastStatusTime is not correct and need write back.");
                b = currentTimeMillis - 61;
                e(b);
            } else {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "handleGetBloodOxygenDownRemindCount unknown");
            }
            keh.e(b, currentTimeMillis);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        synchronized (f14316a) {
            this.i = false;
            ExtendHandler extendHandler = this.e;
            if (extendHandler != null) {
                extendHandler.removeTasksAndMessages();
                this.e.quit(true);
                this.e = null;
            }
        }
    }

    private void c(cwe cweVar, kek kekVar) {
        if (cweVar == null || kekVar == null) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "parseBloodOxygenDownRemindInfoData tlvFather or commonSectionInfo is null");
            return;
        }
        e(kekVar, cweVar.e());
        List<cwe> a2 = cweVar.a();
        if (a2 == null || a2.isEmpty()) {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "parseBloodOxygenDownRemindInfoData tlvFatherList is empty");
        } else {
            kekVar.e(a(a2));
        }
    }

    private List<kel> a(List<cwe> list) {
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list.get(0).a().iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            kel kelVar = new kel();
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 10) {
                    kelVar.c(CommonUtil.y(cwdVar.c()));
                } else if (w == 11) {
                    kelVar.a(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.c("AlarmData_BloodOxygenDownRemindMgr", "parseBloodOxygenDownRemindValue command");
                }
            }
            arrayList.add(kelVar);
        }
        return arrayList;
    }

    private void e(kek kekVar, List<cwd> list) {
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 4) {
                kekVar.e(CommonUtil.y(cwdVar.c()));
            } else if (w == 5) {
                kekVar.c(CommonUtil.y(cwdVar.c()));
            } else if (w == 6) {
                kekVar.c(CommonUtil.w(cwdVar.c()));
            } else if (w == 7) {
                kekVar.a(CommonUtil.w(cwdVar.c()));
            } else if (w == 12) {
                kekVar.d(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.c("AlarmData_BloodOxygenDownRemindMgr", "parseBloodOxygenDownRemindInfoData command");
            }
        }
    }

    public void a(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownRemindCount");
        this.d = 0;
        this.b = 0;
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownRemindCount return errorCode: ", Integer.valueOf(jru.e(bArr)));
                c();
                return;
            }
            int a2 = kea.a(bArr);
            this.b = a2;
            ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownRemindCount get sample frame count :", Integer.valueOf(a2));
            if (this.b > 0) {
                a(deviceInfo);
            } else {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownRemindCount count is zero");
                c();
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownRemindCount: ", ExceptionUtils.d(e));
            c();
        }
    }

    public void d(byte[] bArr, DeviceInfo deviceInfo) {
        ken kenVar = new ken();
        String d2 = cvx.d(bArr);
        LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownRemindDetail tlvHex: ", d2);
        if (d2 != null && d2.length() > 4) {
            try {
                cwe a2 = new cwl().a(d2.substring(4));
                List<cwd> e = a2.e();
                List<cwe> a3 = a2.a();
                for (cwd cwdVar : e) {
                    if (CommonUtil.w(cwdVar.e()) == 1) {
                        kenVar.b(CommonUtil.w(cwdVar.c()));
                    } else {
                        LogUtil.c("AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownReminDetail deal else value");
                    }
                }
                if (a3 != null && !a3.isEmpty()) {
                    cwe cweVar = a3.get(0);
                    ArrayList arrayList = new ArrayList(16);
                    for (cwe cweVar2 : cweVar.a()) {
                        kek kekVar = new kek();
                        c(cweVar2, kekVar);
                        arrayList.add(kekVar);
                    }
                    kenVar.a(arrayList);
                    this.c.add(kenVar);
                    a(deviceInfo);
                    return;
                }
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownReminDetail tlvFatherList is empty");
                a(deviceInfo);
                return;
            } catch (cwg e2) {
                ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownRemindDetail: ", ExceptionUtils.d(e2));
                c();
                return;
            }
        }
        e(deviceInfo);
    }

    private void d() {
        synchronized (this) {
            if (!this.c.isEmpty()) {
                LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "saveBloodOxygenToHiHealth handle data insert");
                DeviceInfo e = keg.e("AlarmData_BloodOxygenDownRemindMgr");
                if (e != null) {
                    keg.d(e);
                    String e2 = keg.e();
                    ArrayList arrayList = new ArrayList(10);
                    d(e2, arrayList);
                    LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "dataList size:", Integer.valueOf(arrayList.size()), " dataList: ", Arrays.asList(arrayList));
                    HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
                    hiDataInsertOption.setDatas(arrayList);
                    List<ken> list = this.c;
                    List<kek> b = list.get(list.size() - 1).b();
                    if (!bkz.e(b)) {
                        b(hiDataInsertOption, b.get(b.size() - 1).b() + 1);
                    }
                } else {
                    ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "5.7.39 get deviceInfo fail.Save data fail.");
                    c();
                    return;
                }
            } else {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "saveBloodOxygenToHiHealth no data save on data.");
            }
            this.c.clear();
            c();
        }
    }

    private void d(String str, List<HiHealthData> list) {
        Iterator it = new ArrayList(this.c).iterator();
        while (it.hasNext()) {
            for (kek kekVar : ((ken) it.next()).b()) {
                HiBloodOxygenRemindMetaData hiBloodOxygenRemindMetaData = new HiBloodOxygenRemindMetaData(kekVar.e());
                for (kel kelVar : kekVar.c()) {
                    HiHealthData hiHealthData = new HiHealthData();
                    hiHealthData.setStartTime(kelVar.d());
                    hiHealthData.setEndTime(kelVar.d());
                    hiHealthData.setValue(kelVar.a());
                    hiHealthData.setType(2107);
                    hiHealthData.setTimeInterval(kekVar.d() * 1000, kekVar.b() * 1000);
                    hiHealthData.setMetaData(HiJsonUtil.e(hiBloodOxygenRemindMetaData));
                    hiHealthData.setDeviceUuid(str);
                    list.add(hiHealthData);
                }
            }
        }
    }

    private void e(DeviceInfo deviceInfo) {
        ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "processBloodOxygenDownReminDetail command less than 4");
        a(deviceInfo);
    }

    private void b(HiDataInsertOption hiDataInsertOption, final long j) {
        LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "insertBloodOxygenData insertData: ", hiDataInsertOption.toString());
        HiHealthNativeApi.a(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kej.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (obj == null) {
                    ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "insertBloodOxygenData object is null");
                    return;
                }
                LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "insertBloodOxygenData errorCode: ", Integer.valueOf(i), " object: ", HiJsonUtil.e(obj));
                if (i == 0) {
                    ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "insertBloodOxygenData success.updateTime is: ", Long.valueOf(j));
                    keg.c();
                    kej.this.e(j);
                    return;
                }
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "insertBloodOxygenData fail.");
            }
        });
    }

    private void a(DeviceInfo deviceInfo) {
        int i = this.d;
        if (i < this.b) {
            LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "getBloodOxygenDownRemindDetailData currentIndex: ", Integer.valueOf(i));
            keh.d(this.d, deviceInfo);
            d(this.d + 1);
        } else {
            LogUtil.a("AlarmData_BloodOxygenDownRemindMgr", "getBloodOxygenDownRemindDetailData sync success");
            d();
        }
    }

    static class d {
        private static final kej c = new kej();
    }

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmData_BloodOxygenDownRemindMgr", "DownRemindHandlerCallback time out");
                kej.this.c();
                return true;
            }
            LogUtil.h("AlarmData_BloodOxygenDownRemindMgr", "DownRemindHandlerCallback default");
            return false;
        }
    }
}
