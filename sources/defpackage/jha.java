package defpackage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.DataTotalMotion;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import defpackage.jhy;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jha {
    private static jha c;
    private static final Object e = new Object();
    private int b = 0;
    private a d;

    private boolean d(int i, int i2) {
        if (i2 <= 0) {
            return false;
        }
        if (i == 2) {
            if (i2 < 500) {
                return true;
            }
        } else if (i == 4) {
            if (i2 < 65535) {
                return true;
            }
        } else if (i == 2018 || i == 2002) {
            if (i2 < 255) {
                return true;
            }
        } else if (i == 5) {
            if (i2 < 1500) {
                return true;
            }
        } else if (i == 2103) {
            if (i2 <= 100) {
                return true;
            }
        } else if (i != 2105 || i2 < 255) {
            return true;
        }
        return false;
    }

    private jha() {
        this.d = null;
        LogUtil.a("05", 1, "FitnessMgrStorage", "FitnessMgrStorage Constructor");
        this.d = new a(this, BaseApplication.getContext().getMainLooper());
    }

    public static jha b() {
        jha jhaVar;
        synchronized (e) {
            if (c == null) {
                LogUtil.h("FitnessMgrStorage", "sInstance is null");
                c = new jha();
            }
            jhaVar = c;
        }
        return jhaVar;
    }

    static class a extends Handler {
        WeakReference<jha> d;

        a(jha jhaVar, Looper looper) {
            super(looper);
            this.d = new WeakReference<>(jhaVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DeviceInfo deviceInfo;
            super.handleMessage(message);
            jha jhaVar = this.d.get();
            if (jhaVar == null) {
                return;
            }
            if (message == null) {
                LogUtil.h("FitnessMgrStorage", "msg is null");
                return;
            }
            jib jibVar = new jib();
            if (message.obj instanceof DeviceInfo) {
                deviceInfo = (DeviceInfo) message.obj;
            } else if (message.obj instanceof jib) {
                jib jibVar2 = (jib) message.obj;
                jibVar.e(jibVar2.d());
                jibVar.f(jibVar2.h());
                jibVar.j(jibVar2.c());
                jibVar.a(jibVar2.b());
                jibVar.e(jibVar2.e());
                jibVar.a(jibVar2.a());
                deviceInfo = jibVar2.a();
            } else {
                LogUtil.h("FitnessMgrStorage", "msg what is:", Integer.valueOf(message.what));
                deviceInfo = null;
            }
            int i = message.what;
            if (i == 0) {
                LogUtil.a("05", 1, "FitnessMgrStorage", "save data success");
                jhaVar.bHi_(0, jibVar, message.getData());
                return;
            }
            if (i == 1) {
                LogUtil.a("05", 1, "FitnessMgrStorage", "save data fail");
                jhaVar.bHi_(300006, jibVar, message.getData());
            } else if (i == 2) {
                LogUtil.a("05", 1, "FitnessMgrStorage", "save des data success");
                jhaVar.e(0, deviceInfo);
            } else if (i == 3) {
                LogUtil.a("05", 1, "FitnessMgrStorage", "save des data fail");
                jhaVar.e(300006, deviceInfo);
            } else {
                LogUtil.h("05", 1, "FitnessMgrStorage", "unknown msg type");
            }
        }
    }

    public void c(jhg jhgVar) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrStorage", "initFitnessStorage fitnessManager is null");
        } else {
            new jhh().a(jhgVar);
        }
    }

    public void d(jhg jhgVar) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrStorage", "initFitnessUserStorage fitnessManager is null");
        } else {
            LogUtil.a("FitnessMgrStorage", "initFitnessUserStorage");
        }
    }

    private boolean a() {
        boolean z;
        DeviceCapability d = cvs.d();
        if (d != null) {
            z = d.isClimb();
        } else {
            LogUtil.h("FitnessMgrStorage", "getDeviceDataType deviceCapability is null");
            z = false;
        }
        LogUtil.a("05", 1, "FitnessMgrStorage", "isDeviceSupportClimeHeight isSupportClimb:", Boolean.valueOf(z));
        return z;
    }

    public void a(jhg jhgVar, jhw jhwVar, DeviceInfo deviceInfo) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrStorage", "saveTodayTotalData fitnessManager is null");
        } else if (jhwVar == null) {
            LogUtil.h("FitnessMgrStorage", "saveTodayTotalData todayTotalMotion is null");
        } else {
            LogUtil.a("05", 1, "FitnessMgrStorage", "saveTodayTotalData get data success total calorie:", Integer.valueOf(jhwVar.c()));
            c(jhgVar, jhwVar, deviceInfo);
        }
    }

    private void c(jhg jhgVar, jhw jhwVar, DeviceInfo deviceInfo) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.clear();
        List<DataTotalMotion> d = jhwVar.d();
        FitnessTotalData fitnessTotalData = new FitnessTotalData();
        fitnessTotalData.setCalorie((int) (jhwVar.c() * 1000));
        for (DataTotalMotion dataTotalMotion : d) {
            LogUtil.c("FitnessMgrStorage", "type", Integer.valueOf(dataTotalMotion.getMotion_type()), "step:", Integer.valueOf(dataTotalMotion.getStep()), "calorie:", Integer.valueOf(dataTotalMotion.getCalorie()), "distance:", Integer.valueOf(dataTotalMotion.getDistance()));
            int motion_type = dataTotalMotion.getMotion_type();
            if (motion_type == 1) {
                fitnessTotalData.addSteps(dataTotalMotion.getStep());
                fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                arrayList.add(new FitnessTotalData(dataTotalMotion));
            } else if (motion_type == 2) {
                fitnessTotalData.addSteps(dataTotalMotion.getStep());
                fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                arrayList.add(new FitnessTotalData(dataTotalMotion));
            } else if (motion_type == 3) {
                fitnessTotalData.addSteps(dataTotalMotion.getStep());
                FitnessTotalData fitnessTotalData2 = new FitnessTotalData(dataTotalMotion);
                if (jhc.d(deviceInfo)) {
                    fitnessTotalData.setHeight(dataTotalMotion.getDistance());
                    fitnessTotalData2.setHeight(fitnessTotalData2.getDistance());
                    fitnessTotalData2.setDistance(0);
                } else {
                    fitnessTotalData2.setHeight(fitnessTotalData2.getHeight());
                    fitnessTotalData2.setDistance(fitnessTotalData2.getDistance());
                    fitnessTotalData.setHeight(dataTotalMotion.getHeight());
                    fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                }
                arrayList.add(fitnessTotalData2);
            } else if (motion_type != 4) {
                LogUtil.h("FitnessMgrStorage", "TotalDataSportType type:", 5);
            } else {
                FitnessTotalData fitnessTotalData3 = new FitnessTotalData(dataTotalMotion);
                fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                arrayList.add(fitnessTotalData3);
            }
        }
        arrayList.add(fitnessTotalData);
        b(fitnessTotalData, arrayList);
    }

    private void b(FitnessTotalData fitnessTotalData, List<FitnessTotalData> list) {
        String str;
        LogUtil.a("05", 1, "FitnessMgrStorage", "saveTodayTotalData steps:", Integer.valueOf(fitnessTotalData.getSteps()), "total fitness calorie:", Integer.valueOf(fitnessTotalData.getCalorie()), "total fitness distance:", Integer.valueOf(fitnessTotalData.getDistance()), "fitnessTotalDataList size:", Integer.valueOf(list.size()));
        e(fitnessTotalData);
        DeviceInfo d = jpt.d("FitnessMgrStorage");
        if (d != null) {
            str = "_" + d.getDeviceIdentify();
        } else {
            str = "";
        }
        StorageParams storageParams = new StorageParams(1);
        if (d(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps()) == 200004) {
            a(str, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG");
            d(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps());
        }
        if (d(str, storageParams, "KEY_TOTAL_CAL_FROM_DEVICE_FLAG", fitnessTotalData.getCalorie() / 1000) == 200004) {
            a(str, "KEY_TOTAL_CAL_FROM_DEVICE_FLAG");
            d(str, storageParams, "KEY_TOTAL_CAL_FROM_DEVICE_FLAG", fitnessTotalData.getCalorie() / 1000);
        }
        if (d(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance()) == 200004) {
            a(str, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG");
            d(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance());
        }
    }

    private void a(String str, String str2) {
        SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), str2 + str);
    }

    private int d(String str, StorageParams storageParams, String str2, long j) {
        return SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), str2 + str, String.valueOf(j + "|" + (System.currentTimeMillis() / 1000)), storageParams);
    }

    private static long e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private void e(FitnessTotalData fitnessTotalData) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "saveTodayTotal to hiHealth fitnessTotalData:", fitnessTotalData);
        long e2 = e(System.currentTimeMillis());
        if (fitnessTotalData.getSteps() > (((System.currentTimeMillis() - jdl.t(System.currentTimeMillis())) / 60000) + 1) * 500) {
            LogUtil.a("FitnessMgrStorage", "saveTodayTotalToHiHealth to Steps error: ", Integer.valueOf(fitnessTotalData.getSteps()), " ,startTime: ", Long.valueOf(e2));
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        if (fitnessTotalData.getSteps() > 0) {
            arrayList.add(d(40002, e2, System.currentTimeMillis(), fitnessTotalData.getSteps()));
            arrayList.add(d(901, e2, System.currentTimeMillis(), fitnessTotalData.getSteps()));
        }
        if (fitnessTotalData.getCalorie() > 0) {
            arrayList.add(d(40003, e2, System.currentTimeMillis(), fitnessTotalData.getCalorie()));
            arrayList.add(d(903, e2, System.currentTimeMillis(), fitnessTotalData.getCalorie()));
        }
        if (fitnessTotalData.getDistance() > 0) {
            arrayList.add(d(40004, e2, System.currentTimeMillis(), fitnessTotalData.getDistance()));
            arrayList.add(d(902, e2, System.currentTimeMillis(), fitnessTotalData.getDistance()));
        }
        if (fitnessTotalData.getHeight() > 0) {
            arrayList.add(d(SmartMsgConstant.MSG_TYPE_RIDE_USER, e2, System.currentTimeMillis(), fitnessTotalData.getHeight()));
            arrayList.add(d(904, e2, System.currentTimeMillis(), fitnessTotalData.getHeight()));
        }
        if (arrayList.size() > 0) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(arrayList);
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jha.4
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    if (obj != null) {
                        LogUtil.a("05", 1, "FitnessMgrStorage", "saveTodayTotal to hiHealth onResult type:", Integer.valueOf(i), "insertHiHealthData object:", obj);
                    } else {
                        LogUtil.h("FitnessMgrStorage", "insertHiHealthData object is null");
                    }
                    if (i == 0) {
                        iyv.c();
                    }
                }
            });
        }
    }

    private HiHealthData d(int i, long j, long j2, double d) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(i);
        hiHealthData.setDeviceUuid(jpp.d());
        hiHealthData.setTimeInterval(j, j2);
        hiHealthData.setValue(d);
        return hiHealthData;
    }

    private boolean a(long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
        long j3 = j * 1000;
        String format = simpleDateFormat.format(new Date(j3));
        long j4 = 1000 * j2;
        String format2 = simpleDateFormat.format(new Date(j4));
        LogUtil.a("FitnessMgrStorage", "firstDay:", format, "firstTime:", new Date(j3), "secondDay:", format2, "secondTime:", new Date(j4));
        return format.equals(format2);
    }

    private jhx c(jhx jhxVar, jib jibVar) {
        int c2 = jibVar.c();
        int b = jibVar.b();
        int e2 = jibVar.e();
        long d = jibVar.d();
        int c3 = jhxVar.c();
        int e3 = jhxVar.e();
        int b2 = jhxVar.b();
        if (c3 < c2 || e3 < b || b2 < e2) {
            LogUtil.a("05", 1, "FitnessMgrStorage", "convert lastTimetamp:", Long.valueOf(d), "convert firstDate:", new Date(d * 1000), "convert current timestamp:", Long.valueOf(jhxVar.d()), "convert secondDate:", new Date(jhxVar.d() * 1000), "convert last calorie:", Integer.valueOf(b), "convert last step:", Integer.valueOf(c2), "convert last distance:", Integer.valueOf(e2), "convert calorie:", Integer.valueOf(e3), "convert step:", Integer.valueOf(c3), "convert distance:", Integer.valueOf(b2));
        }
        if (!a(d, jhxVar.d())) {
            c2 = 0;
            jibVar.j(0);
            jibVar.a(0);
            jibVar.e(0);
            b = 0;
            e2 = 0;
        }
        jhx jhxVar2 = new jhx();
        jhxVar2.c(jhxVar.a());
        jhxVar2.b(c3 - c2);
        jhxVar2.e(e3 - b);
        jhxVar2.d(b2 - e2);
        jhxVar2.a(jhxVar.d());
        return jhxVar2;
    }

    private jhx e(jhx jhxVar, int i) {
        if (!d(a(1), jhxVar.c())) {
            LogUtil.a("05", 1, "FitnessMgrStorage", "step invalid checkAndModifySportData type:", Integer.valueOf(jhxVar.a()), "check modify step:", Integer.valueOf(jhxVar.c()), "step timestamp:", Long.valueOf(jhxVar.d()), "step date:", new Date(jhxVar.d() * 1000));
            jhxVar.b(0);
        }
        if (!d(a(2), jhxVar.e())) {
            LogUtil.a("05", 1, "FitnessMgrStorage", "calorie invalid date type:", Integer.valueOf(jhxVar.a()), "check modify calorie:", Integer.valueOf(jhxVar.e()), "calorie Timestamp:", Long.valueOf(jhxVar.d()), "calorie date:", new Date(jhxVar.d() * 1000));
            jhxVar.e(0);
        }
        if (!d(i, jhxVar.b())) {
            LogUtil.a("05", 1, "FitnessMgrStorage", "distance invalid date type:", Integer.valueOf(jhxVar.a()), "check modify distance:", Integer.valueOf(jhxVar.b()), "distance timestamp:", Long.valueOf(jhxVar.d()), "distance date:", new Date(jhxVar.d() * 1000));
            jhxVar.d(0);
        }
        return jhxVar;
    }

    private void c(jhx jhxVar, List<HiHealthData> list, String str) {
        int a2 = jhxVar.a();
        int c2 = jhxVar.c();
        int e2 = jhxVar.e();
        int b = jhxVar.b();
        int i = 3;
        if (a() && jhxVar.a() == 3) {
            i = 5;
        }
        LogUtil.a("05", 1, "FitnessMgrStorage", "saveSportData type:", Integer.valueOf(a2), "calorie:", Integer.valueOf(e2), "step:", Integer.valueOf(c2), "distance:", Integer.valueOf(b), "saveSportData Timestamp:", Long.valueOf(jhxVar.d()), "saveSportData date:", new Date(jhxVar.d() * 1000));
        jhx e3 = e(jhxVar, i);
        if (e3.c() > 0) {
            HiHealthData hiHealthData = new HiHealthData(2);
            hiHealthData.setTimeInterval(e3.d() * 1000, (e3.d() + 60) * 1000);
            hiHealthData.setValue(e3.c());
            hiHealthData.setDeviceUuid(str);
            list.add(hiHealthData);
        } else {
            LogUtil.h("FitnessMgrStorage", "tempSportData invalid date step:", Integer.valueOf(e3.c()), "getTotalSteps Timestamp:", Long.valueOf(e3.d()));
        }
        if (e3.b() > 0) {
            HiHealthData hiHealthData2 = new HiHealthData(i);
            hiHealthData2.setValue(e3.b());
            hiHealthData2.setTimeInterval(e3.d() * 1000, (e3.d() + 60) * 1000);
            hiHealthData2.setDeviceUuid(str);
            list.add(hiHealthData2);
        } else {
            LogUtil.h("FitnessMgrStorage", "tempSportData invalid date distance:", Integer.valueOf(e3.b()), "getTotalDistance Timestamp:", Long.valueOf(e3.d()));
        }
        if (e3.e() > 0) {
            HiHealthData hiHealthData3 = new HiHealthData(4);
            hiHealthData3.setTimeInterval(e3.d() * 1000, (e3.d() + 60) * 1000);
            hiHealthData3.setValue(e3.e());
            hiHealthData3.setDeviceUuid(str);
            list.add(hiHealthData3);
            return;
        }
        LogUtil.h("FitnessMgrStorage", "tempSportData invalid date calorie:", Integer.valueOf(e3.e()), "getTotalCalorie startTime:", Long.valueOf(e3.d()));
    }

    private void e(jhx jhxVar, jib jibVar) {
        if (jhxVar.a() == 0) {
            LogUtil.h("FitnessMgrStorage", "updateLastTotal getCurrentStatus is 0");
            return;
        }
        int c2 = jhxVar.c();
        int e2 = jhxVar.e();
        int b = jhxVar.b();
        if (c2 == 0 && e2 == 0 && b == 0) {
            LogUtil.h("FitnessMgrStorage", "updateLastTotal getCurrentStatus:step,calorie,distance:0");
            return;
        }
        jibVar.b(b);
        jibVar.c(c2);
        jibVar.d(e2);
        jibVar.e(jhxVar.d());
    }

    public void c(jhg jhgVar, List<jhv> list, int i, DeviceInfo deviceInfo) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrStorage", "saveFitnessData fitnessManager is null");
            return;
        }
        if (list == null) {
            LogUtil.h("FitnessMgrStorage", "saveFitnessData healthDataList is null");
            return;
        }
        LogUtil.a("05", 1, "FitnessMgrStorage", "saveFitnessData enter, stage:", Integer.valueOf(i));
        Collections.sort(list, new Comparator<jhv>() { // from class: jha.5
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(jhv jhvVar, jhv jhvVar2) {
                return jhvVar.b() - jhvVar2.b();
            }
        });
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        jib d = new jhh().d(jhgVar);
        long d2 = d.d();
        int a2 = jhc.a();
        d.a(deviceInfo);
        if (a2 == 4) {
            Iterator<jhv> it = list.iterator();
            while (it.hasNext()) {
                b(it.next(), d2, d, arrayList, arrayList2);
            }
        } else if (jhc.c(deviceInfo) == 0) {
            Iterator<jhv> it2 = list.iterator();
            while (it2.hasNext()) {
                b(it2.next(), d2, d, arrayList, arrayList2);
            }
        } else {
            Iterator<jhv> it3 = list.iterator();
            while (it3.hasNext()) {
                b(it3.next(), d2, d, arrayList, arrayList2);
            }
        }
        e(i, arrayList, arrayList2, d2, d);
        LogUtil.a("FitnessMgrStorage", "saveFitnessData leave");
    }

    public void e(jhg jhgVar, List<jic> list, List<jif> list2, int i, DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2;
        long j;
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrStorage", "saveFitnessData statusFrameList fitnessManager is null");
            return;
        }
        LogUtil.a("05", 1, "FitnessMgrStorage", "saveFitnessData in separated type");
        jib d = new jhh().d(jhgVar);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        String d2 = jpp.d();
        if (list != null) {
            a(list, d, arrayList, d2);
        }
        long e2 = jgz.e(jhgVar);
        if (list2 != null) {
            j = c(list2, arrayList2, e2, d2);
            deviceInfo2 = deviceInfo;
        } else {
            deviceInfo2 = deviceInfo;
            j = e2;
        }
        d.a(deviceInfo2);
        e(i, arrayList, arrayList2, j, d);
    }

    private void c(jhx jhxVar, List<HiHealthData> list, jih jihVar, HashMap<Integer, Integer> hashMap, String str) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "updateStatus DataRawSportData:", jhxVar);
        if (jhxVar.c() == 0 && jhxVar.e() == 0 && jhxVar.b() == 0) {
            return;
        }
        if (jihVar.e() == jhxVar.a() && jihVar.a() == jhxVar.d()) {
            jihVar.e(60);
            return;
        }
        d(jihVar, list, hashMap, str);
        jihVar.c(jhxVar.d());
        jihVar.a(jhxVar.a());
        jihVar.d(60);
    }

    private void e(jia jiaVar, List<HiHealthData> list, jih jihVar, HashMap<Integer, Integer> hashMap, String str) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "updateStatus DataRawSleepData:", jiaVar);
        if (jihVar.e() == jiaVar.e() && jihVar.a() == jiaVar.b()) {
            jihVar.e(60);
            return;
        }
        d(jihVar, list, hashMap, str);
        jihVar.c(jiaVar.b());
        jihVar.a(jiaVar.e());
        jihVar.d(60);
    }

    private void a(List<HiHealthData> list, jih jihVar, HashMap<Integer, Integer> hashMap, String str) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "updateStatus null");
        if (jihVar != null) {
            d(jihVar, list, hashMap, str);
        }
    }

    private void b(jhv jhvVar, long j, jib jibVar, List<HiHealthData> list, List<HiHealthData> list2) {
        List<jhx> e2 = jhvVar.e();
        LogUtil.a("05", 1, "FitnessMgrStorage", "dataHealthData time:", Integer.valueOf(jhvVar.b()), "dataHealthData date:", new Date(jhvVar.b() * 1000));
        jih jihVar = new jih();
        String str = jibVar.a().getSecurityUuid() + "#ANDROID21";
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        if (e2 != null) {
            for (jhx jhxVar : e2) {
                if (jhxVar.a() != 255 && j < jhxVar.d()) {
                    jhx c2 = c(jhxVar, jibVar);
                    e(c2, jibVar);
                    c(c2, list, str);
                    c(jhxVar, list2, jihVar, hashMap, str);
                }
            }
        }
        List<jia> a2 = jhvVar.a();
        if (a2 != null) {
            Iterator<jia> it = a2.iterator();
            while (it.hasNext()) {
                e(it.next(), list2, jihVar, hashMap, str);
            }
        }
        a(list2, jihVar, hashMap, str);
    }

    private void a(List<jie> list) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("Total:" + list.size());
        int i = 0;
        for (jie jieVar : list) {
            sb.append("[i:");
            sb.append(i);
            sb.append("]");
            sb.append(jieVar);
            i++;
        }
        LogUtil.a("05", 1, "FitnessMgrStorage", sb.toString());
    }

    private void e(jic jicVar, jib jibVar, List<HiHealthData> list, String str) {
        List<jie> e2 = jicVar.e();
        a(e2);
        Iterator<jie> it = e2.iterator();
        while (it.hasNext()) {
            d(it.next(), list, str);
        }
        if (e2.size() > 0) {
            long b = e2.get(e2.size() - 1).b();
            if (jibVar.d() < b) {
                jibVar.e(b);
            }
        }
    }

    private void a(List<jic> list, jib jibVar, List<HiHealthData> list2, String str) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "saveSampleFrameList enter");
        Collections.sort(list, new Comparator<jic>() { // from class: jha.3
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(jic jicVar, jic jicVar2) {
                return (int) (jicVar.d() - jicVar2.d());
            }
        });
        Iterator<jic> it = list.iterator();
        while (it.hasNext()) {
            e(it.next(), jibVar, list2, str);
        }
    }

    private long b(jif jifVar, long j, List<HiHealthData> list, HashMap<Integer, Integer> hashMap, String str) {
        List<jih> b = jifVar.b();
        Iterator<jih> it = b.iterator();
        while (it.hasNext()) {
            d(it.next(), list, hashMap, str);
        }
        if (b.size() > 0) {
            long c2 = b.get(b.size() - 1).c();
            if (c2 > j) {
                return c2;
            }
        }
        return j;
    }

    private long c(List<jif> list, List<HiHealthData> list2, long j, String str) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "saveStatusFrameList enter");
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        Iterator<jif> it = list.iterator();
        long j2 = j;
        while (it.hasNext()) {
            j2 = b(it.next(), j2, list2, hashMap, str);
        }
        LogUtil.a("FitnessMgrStorage", "saveStatusFrameList errorSportTypeMap:", hashMap.toString());
        return j2;
    }

    private int a(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = 2;
                break;
            case 2:
                i2 = 4;
                break;
            case 3:
                i2 = 3;
                break;
            case 4:
                i2 = 6;
                break;
            case 5:
                i2 = 5;
                break;
            case 6:
                i2 = 2018;
                break;
            case 7:
                i2 = 2002;
                break;
            case 8:
                i2 = 2103;
                break;
            case 9:
                i2 = 2105;
                break;
            default:
                LogUtil.h("FitnessMgrStorage", "getHiHealthDataType dataType:", Integer.valueOf(i));
                i2 = 0;
                break;
        }
        LogUtil.c("FitnessMgrStorage", "getHiHealthSessionType sportType:", Integer.valueOf(i2));
        return i2;
    }

    private void d(jie jieVar, List<HiHealthData> list, String str) {
        if (d(a(jieVar.c()), jieVar.a())) {
            HiHealthData hiHealthData = new HiHealthData(a(jieVar.c()));
            hiHealthData.setTimeInterval(jieVar.b() * 1000, (jieVar.b() + jieVar.e()) * 1000);
            hiHealthData.setValue(jieVar.a());
            hiHealthData.setDeviceUuid(str);
            LogUtil.a("FitnessMgrStorage", "insertSamplePoint hiHealthData:", hiHealthData);
            list.add(hiHealthData);
            return;
        }
        LogUtil.h("FitnessMgrStorage", "insertSamplePoint invalid samplePoint:", jieVar);
    }

    private int b(int i, HashMap<Integer, Integer> hashMap) {
        if (i == 1) {
            return 20002;
        }
        if (i == 2) {
            return 20003;
        }
        if (i == 3) {
            return 20004;
        }
        if (i == 4) {
            return 20005;
        }
        if (i == 6) {
            return 22002;
        }
        if (i == 7) {
            return 22001;
        }
        if (i == 9) {
            return 20007;
        }
        LogUtil.h("FitnessMgrStorage", "getHiHealthSessionType dataType:", Integer.valueOf(i));
        d(i, hashMap);
        return 0;
    }

    private void d(int i, HashMap<Integer, Integer> hashMap) {
        if (hashMap.containsKey(Integer.valueOf(i))) {
            hashMap.put(Integer.valueOf(i), Integer.valueOf(hashMap.get(Integer.valueOf(i)).intValue() + 1));
        } else {
            hashMap.put(Integer.valueOf(i), 1);
        }
    }

    private void d(jih jihVar, List<HiHealthData> list, HashMap<Integer, Integer> hashMap, String str) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "statusPoint:", jihVar);
        for (long c2 = jihVar.c(); c2 < jihVar.a(); c2 += 60) {
            if (b(jihVar.e(), hashMap) != 0) {
                HiHealthData hiHealthData = new HiHealthData(b(jihVar.e(), hashMap));
                hiHealthData.setTimeInterval(c2 * 1000, (c2 + 60) * 1000);
                hiHealthData.setDeviceUuid(str);
                LogUtil.c("FitnessMgrStorage", "saveStatusToHiHealth hiHealthData:", hiHealthData);
                list.add(hiHealthData);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(jib jibVar, long j) {
        jhg c2 = jhg.c(BaseApplication.getContext());
        jhh jhhVar = new jhh();
        if (jibVar != null) {
            if (jibVar.h() == 2) {
                c2.ag.a(c2, jibVar.d());
                c2.ag.b(c2, j);
            } else {
                jhhVar.b(c2, jibVar);
                jgz.d(c2, j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bHi_(int i, final jib jibVar, final Bundle bundle) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "processSaveDataComplete enter errorCode:", Integer.valueOf(i));
        if (jibVar == null) {
            LogUtil.h("FitnessMgrStorage", "processSaveDataComplete totalValue is null.");
            return;
        }
        if (bundle == null || bundle.isEmpty()) {
            LogUtil.h("FitnessMgrStorage", "processSaveDataComplete bundle is null.");
            return;
        }
        if (i == 0) {
            jrq.b("FitnessMgrStorage", new Runnable() { // from class: jha.1
                @Override // java.lang.Runnable
                public void run() {
                    jha.this.b(jibVar, bundle.getLong("newStatusTime"));
                }
            });
        }
        jhg.c(BaseApplication.getContext()).e(0, jibVar.a());
    }

    private void e(final int i, List<HiHealthData> list, List<HiHealthData> list2, final long j, final jib jibVar) {
        LogUtil.a("FitnessMgrStorage", "saveDataToHiHealth enter mDataList.size:", Integer.valueOf(list.size()), " mStatusList.size:", Integer.valueOf(list2.size()), "stage:", Integer.valueOf(i), " newStatusTime", Long.valueOf(j));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        list.addAll(list2);
        hiDataInsertOption.setDatas(list);
        final boolean e2 = e(list);
        LogUtil.a("FitnessMgrStorage", "saveDataToHiHealth mDataList: ", Arrays.asList(list));
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jha.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                LogUtil.a("05", 1, "FitnessMgrStorage", "saveDataToHiHealth onResult type:", Integer.valueOf(i2), ", object:", obj);
                jibVar.f(i);
                Message obtain = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putLong("newStatusTime", j);
                obtain.setData(bundle);
                obtain.obj = jibVar;
                if (i2 == 0) {
                    iyv.c();
                    obtain.what = 0;
                    if (e2) {
                        jha.this.d();
                    }
                } else {
                    obtain.what = 1;
                }
                jha.this.d.sendMessage(obtain);
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
                if (localBroadcastManager == null) {
                    LogUtil.h("FitnessMgrStorage", "sendBroadcast, but localBroadcastManager is null.");
                } else if (i == 1) {
                    localBroadcastManager.sendBroadcast(new Intent("com.huawei.health.action.COMMON_DATA_SAVE_COMPLETED"));
                }
            }
        });
    }

    private boolean e(List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            if (HiHealthDataType.r(it.next().getType())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public void c(List<jhy> list, final DeviceInfo deviceInfo) {
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("FitnessMgrStorage", "enter saveIntensiveInfo");
        d(arrayList, list);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jha.9
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("05", 1, "FitnessMgrStorage", "saveIntensiveInfo onResult type:", Integer.valueOf(i), ", object:", obj);
                Message obtain = Message.obtain();
                if (i == 0) {
                    obtain.what = 2;
                } else {
                    obtain.what = 3;
                }
                obtain.obj = deviceInfo;
                jha.this.d.sendMessage(obtain);
            }
        });
    }

    private void d(List<HiHealthData> list, List<jhy> list2) {
        String d = jpp.d();
        for (jhy jhyVar : list2) {
            LogUtil.c("FitnessMgrStorage", "DesFrame:", jhyVar.toString());
            List<jhy.a> e2 = jhyVar.e();
            int i = 0;
            while (i < e2.size()) {
                jhy.a aVar = e2.get(i);
                int c2 = aVar.c();
                int d2 = aVar.d();
                int i2 = c2 - (c2 % 60);
                int i3 = ((d2 - (d2 % 60)) - i2) / 60;
                int i4 = 0;
                while (i4 < i3) {
                    HiHealthData hiHealthData = new HiHealthData();
                    long j = ((i4 * 60) + i2) * 1000;
                    long j2 = (r1 + 60) * 1000;
                    hiHealthData.setTimeInterval(j, j2);
                    hiHealthData.setValue(e(aVar.b()));
                    hiHealthData.setType(7);
                    hiHealthData.setDeviceUuid(d);
                    list.add(hiHealthData);
                    LogUtil.c("FitnessMgrStorage", "Intensive data tempStartTime:", Long.valueOf(j), "tempEndTime:", Long.valueOf(j2), "value:", Integer.valueOf(e(aVar.b())), "type:", 7, "uuid:", d);
                    i4++;
                    i3 = i3;
                    i = i;
                }
                i++;
            }
        }
        if (list2.size() > 0) {
            int d3 = list2.get(list2.size() - 1).e().get(r1.e().size() - 1).d();
            if (d3 > this.b) {
                this.b = d3;
            }
        }
    }

    private int e(int i) {
        if (i == 0) {
            return 8;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 6;
        }
        if (i == 4) {
            return 3;
        }
        if (i == 9) {
            return 7;
        }
        LogUtil.h("FitnessMgrStorage", "intensityType sportType:", Integer.valueOf(i));
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "FitnessMgrStorage", "processSaveDesDataComplete enter errorCode:", Integer.valueOf(i));
        jhg c2 = jhg.c(BaseApplication.getContext());
        int b = c2.ag.b();
        if (i == 0) {
            if (b == 1) {
                jgz.c(c2, this.b);
            } else {
                jgz.e(c2, jhc.a(System.currentTimeMillis() / 1000));
                return;
            }
        }
        c2.a(i, deviceInfo);
    }
}
