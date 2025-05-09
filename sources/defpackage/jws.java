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
import defpackage.jxr;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
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
public class jws {

    /* renamed from: a, reason: collision with root package name */
    private a f14151a;
    private int d;

    private boolean a(int i, int i2) {
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

    private jws() {
        this.f14151a = null;
        this.d = 0;
        LogUtil.a("HwBasicStorage", "FitnessMgrStorage Constructor");
        this.f14151a = new a(this, BaseApplication.getContext().getMainLooper());
    }

    public static jws b() {
        return b.c;
    }

    static class a extends Handler {
        WeakReference<jws> c;

        a(jws jwsVar, Looper looper) {
            super(looper);
            this.c = new WeakReference<>(jwsVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DeviceInfo deviceInfo;
            super.handleMessage(message);
            jws jwsVar = this.c.get();
            if (jwsVar == null) {
                return;
            }
            if (message == null) {
                LogUtil.h("HwBasicStorage", "msg is null");
                return;
            }
            jxq jxqVar = new jxq();
            if (message.obj instanceof DeviceInfo) {
                deviceInfo = (DeviceInfo) message.obj;
            } else if (message.obj instanceof jxq) {
                jxq jxqVar2 = (jxq) message.obj;
                jxqVar.a(jxqVar2.e());
                jxqVar.g(jxqVar2.f());
                jxqVar.i(jxqVar2.a());
                jxqVar.b(jxqVar2.c());
                jxqVar.d(jxqVar2.b());
                jxqVar.a(jxqVar2.d());
                deviceInfo = jxqVar2.d();
            } else {
                LogUtil.h("HwBasicStorage", "msg what is:", Integer.valueOf(message.what));
                deviceInfo = null;
            }
            int i = message.what;
            if (i == 0) {
                LogUtil.a("HwBasicStorage", "save data success");
                jwsVar.bKH_(0, jxqVar, message.getData());
                return;
            }
            if (i == 1) {
                LogUtil.a("HwBasicStorage", "save data fail");
                jwsVar.bKH_(300006, jxqVar, message.getData());
            } else if (i == 2) {
                LogUtil.a("HwBasicStorage", "save des data success");
                jwsVar.a(0, deviceInfo);
            } else if (i == 3) {
                LogUtil.a("HwBasicStorage", "save des data fail");
                jwsVar.a(300006, deviceInfo);
            } else {
                LogUtil.h("HwBasicStorage", "unknown msg type");
            }
        }
    }

    public void b(jwy jwyVar) {
        if (jwyVar == null) {
            LogUtil.h("HwBasicStorage", "initFitnessStorage fitnessManager is null");
        } else {
            new jwt().d(jwyVar);
        }
    }

    private boolean e(String str) {
        boolean z;
        DeviceCapability c = jxi.c(str);
        if (c != null) {
            z = c.isClimb();
        } else {
            LogUtil.h("HwBasicStorage", "getDeviceDataType deviceCapability is null");
            z = false;
        }
        LogUtil.a("HwBasicStorage", "isDeviceSupportClimeHeight isSupportClimb:", Boolean.valueOf(z));
        return z;
    }

    public void b(jxt jxtVar, DeviceInfo deviceInfo) {
        if (jxtVar == null) {
            LogUtil.h("HwBasicStorage", "saveTodayTotalData todayTotalMotion is null");
        } else {
            LogUtil.a("HwBasicStorage", "saveTodayTotalData get data success total calorie:", Integer.valueOf(jxtVar.a()));
            e(jxtVar, deviceInfo);
        }
    }

    private void e(jxt jxtVar, DeviceInfo deviceInfo) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.clear();
        List<DataTotalMotion> c = jxtVar.c();
        FitnessTotalData fitnessTotalData = new FitnessTotalData();
        fitnessTotalData.setCalorie((int) (jxtVar.a() * 1000));
        for (DataTotalMotion dataTotalMotion : c) {
            LogUtil.a("HwBasicStorage", "type", Integer.valueOf(dataTotalMotion.getMotion_type()), "step:", Integer.valueOf(dataTotalMotion.getStep()), "calorie:", Integer.valueOf(dataTotalMotion.getCalorie()), "distance:", Integer.valueOf(dataTotalMotion.getDistance()));
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
                if (jxi.a(deviceInfo)) {
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
                LogUtil.h("HwBasicStorage", "TotalDataSportType type:", 5);
            } else {
                FitnessTotalData fitnessTotalData3 = new FitnessTotalData(dataTotalMotion);
                fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                arrayList.add(fitnessTotalData3);
            }
        }
        arrayList.add(fitnessTotalData);
        e(fitnessTotalData, arrayList);
    }

    private void e(FitnessTotalData fitnessTotalData, List<FitnessTotalData> list) {
        String str;
        LogUtil.a("HwBasicStorage", "saveTodayTotalData steps:", Integer.valueOf(fitnessTotalData.getSteps()), "total fitness calorie:", Integer.valueOf(fitnessTotalData.getCalorie()), "total fitness distance:", Integer.valueOf(fitnessTotalData.getDistance()), "fitnessTotalDataList size:", Integer.valueOf(list.size()));
        c(fitnessTotalData);
        DeviceInfo e = jxi.e("HwBasicStorage");
        if (e != null) {
            str = "_" + e.getDeviceIdentify();
        } else {
            str = "";
        }
        StorageParams storageParams = new StorageParams(1);
        int e2 = e(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps());
        LogUtil.h("HwBasicStorage", "saveTotalFitnessData stepCode:", Integer.valueOf(e2));
        if (e2 == 200004) {
            c(str, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG");
            e(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps());
        }
        if (e(str, storageParams, "KEY_TOTAL_CAL_FROM_DEVICE_FLAG", fitnessTotalData.getCalorie() / 1000) == 200004) {
            c(str, "KEY_TOTAL_CAL_FROM_DEVICE_FLAG");
            e(str, storageParams, "KEY_TOTAL_CAL_FROM_DEVICE_FLAG", fitnessTotalData.getCalorie() / 1000);
        }
        if (e(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance()) == 200004) {
            c(str, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG");
            e(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance());
        }
    }

    private void c(String str, String str2) {
        SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), str2 + str);
    }

    private int e(String str, StorageParams storageParams, String str2, long j) {
        return SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), str2 + str, j + "|" + (System.currentTimeMillis() / 1000), storageParams);
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

    private void c(FitnessTotalData fitnessTotalData) {
        LogUtil.a("HwBasicStorage", "saveTodayTotal to hiHealth fitnessTotalData:", fitnessTotalData);
        long e = e(System.currentTimeMillis());
        if (fitnessTotalData.getSteps() > (((System.currentTimeMillis() - jdl.t(System.currentTimeMillis())) / 60000) + 1) * 500) {
            LogUtil.a("HwBasicStorage", "saveTodayTotalToHiHealth to Steps error: ", Integer.valueOf(fitnessTotalData.getSteps()), " ,startTime: ", Long.valueOf(e));
            return;
        }
        ArrayList<HiHealthData> arrayList = new ArrayList<>(16);
        if (fitnessTotalData.getSteps() > 0) {
            arrayList.add(b(40002, e, System.currentTimeMillis(), fitnessTotalData.getSteps()));
            arrayList.add(b(901, e, System.currentTimeMillis(), fitnessTotalData.getSteps()));
        }
        if (fitnessTotalData.getCalorie() > 0) {
            arrayList.add(b(40003, e, System.currentTimeMillis(), fitnessTotalData.getCalorie()));
            arrayList.add(b(903, e, System.currentTimeMillis(), fitnessTotalData.getCalorie()));
        }
        if (fitnessTotalData.getDistance() > 0) {
            arrayList.add(b(40004, e, System.currentTimeMillis(), fitnessTotalData.getDistance()));
            arrayList.add(b(902, e, System.currentTimeMillis(), fitnessTotalData.getDistance()));
        }
        if (fitnessTotalData.getHeight() > 0) {
            arrayList.add(b(SmartMsgConstant.MSG_TYPE_RIDE_USER, e, System.currentTimeMillis(), fitnessTotalData.getHeight()));
            arrayList.add(b(904, e, System.currentTimeMillis(), fitnessTotalData.getHeight()));
        }
        e(arrayList);
    }

    private void e(ArrayList<HiHealthData> arrayList) {
        if (arrayList.size() > 0) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(arrayList);
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jws.1
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    if (obj != null) {
                        ReleaseLogUtil.e("R_HwBasicStorage", "saveTodayTotal to hiHealth onResult type:", Integer.valueOf(i), "insertHiHealthData object:", obj);
                    } else {
                        LogUtil.h("HwBasicStorage", "insertHiHealthData object is null");
                    }
                    if (i == 0) {
                        iyv.c();
                    }
                }
            });
        }
    }

    private HiHealthData b(int i, long j, long j2, double d) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(i);
        hiHealthData.setDeviceUuid(keg.e());
        hiHealthData.setTimeInterval(j, j2);
        hiHealthData.setValue(d);
        return hiHealthData;
    }

    private boolean d(long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
        long j3 = j * 1000;
        String format = simpleDateFormat.format(new Date(j3));
        long j4 = 1000 * j2;
        String format2 = simpleDateFormat.format(new Date(j4));
        LogUtil.a("HwBasicStorage", "firstDay:", format, "firstTime:", new Date(j3), "secondDay:", format2, "secondTime:", new Date(j4));
        return format.equals(format2);
    }

    private jxu b(jxu jxuVar, jxq jxqVar) {
        long j;
        int a2 = jxqVar.a();
        int c = jxqVar.c();
        int b2 = jxqVar.b();
        long e = jxqVar.e();
        int b3 = jxuVar.b();
        int c2 = jxuVar.c();
        int e2 = jxuVar.e();
        if (b3 < a2 || c2 < c || e2 < b2) {
            j = e;
            LogUtil.a("HwBasicStorage", "convert lastTimetamp:", Long.valueOf(e), "convert firstDate:", new Date(e * 1000), "convert current timestamp:", Long.valueOf(jxuVar.d()), "convert secondDate:", new Date(jxuVar.d() * 1000), "convert last calorie:", Integer.valueOf(c), "convert last step:", Integer.valueOf(a2), "convert last distance:", Integer.valueOf(b2), "convert calorie:", Integer.valueOf(c2), "convert step:", Integer.valueOf(b3), "convert distance:", Integer.valueOf(e2));
        } else {
            j = e;
        }
        if (!d(j, jxuVar.d())) {
            a2 = 0;
            jxqVar.i(0);
            jxqVar.b(0);
            jxqVar.d(0);
            c = 0;
            b2 = 0;
        }
        jxu jxuVar2 = new jxu();
        jxuVar2.d(jxuVar.a());
        jxuVar2.c(b3 - a2);
        jxuVar2.b(c2 - c);
        jxuVar2.e(e2 - b2);
        jxuVar2.e(jxuVar.d());
        return jxuVar2;
    }

    private jxu a(jxu jxuVar, int i) {
        String str = "HwBasicStorage";
        if (!a(a(1), jxuVar.b())) {
            str = "HwBasicStorage";
            LogUtil.a(str, "step invalid checkAndModifySportData type:", Integer.valueOf(jxuVar.a()), "check modify step:", Integer.valueOf(jxuVar.b()), "step timestamp:", Long.valueOf(jxuVar.d()), "step date:", new Date(jxuVar.d() * 1000));
            jxuVar.c(0);
        }
        if (!a(a(2), jxuVar.c())) {
            LogUtil.a(str, "calorie invalid date type:", Integer.valueOf(jxuVar.a()), "check modify calorie:", Integer.valueOf(jxuVar.c()), "calorie Timestamp:", Long.valueOf(jxuVar.d()), "calorie date:", new Date(jxuVar.d() * 1000));
            jxuVar.b(0);
        }
        if (!a(i, jxuVar.e())) {
            LogUtil.a(str, "distance invalid date type:", Integer.valueOf(jxuVar.a()), "check modify distance:", Integer.valueOf(jxuVar.e()), "distance timestamp:", Long.valueOf(jxuVar.d()), "distance date:", new Date(1000 * jxuVar.d()));
            jxuVar.e(0);
        }
        return jxuVar;
    }

    private void e(jxu jxuVar, List<HiHealthData> list, String str, String str2) {
        int a2 = jxuVar.a();
        int b2 = jxuVar.b();
        int c = jxuVar.c();
        int e = jxuVar.e();
        int i = 3;
        if (e(str2) && jxuVar.a() == 3) {
            i = 5;
        }
        LogUtil.a("HwBasicStorage", "saveSportData type:", Integer.valueOf(a2), "calorie:", Integer.valueOf(c), "step:", Integer.valueOf(b2), "distance:", Integer.valueOf(e), "saveSportData Timestamp:", Long.valueOf(jxuVar.d()), "saveSportData date:", new Date(jxuVar.d() * 1000));
        jxu a3 = a(jxuVar, i);
        if (a3.b() > 0) {
            HiHealthData hiHealthData = new HiHealthData(2);
            hiHealthData.setTimeInterval(a3.d() * 1000, (a3.d() + 60) * 1000);
            hiHealthData.setValue(a3.b());
            hiHealthData.setDeviceUuid(str);
            list.add(hiHealthData);
        } else {
            LogUtil.h("HwBasicStorage", "tempSportData invalid date step:", Integer.valueOf(a3.b()), "getTotalSteps Timestamp:", Long.valueOf(a3.d()));
        }
        if (a3.e() > 0) {
            HiHealthData hiHealthData2 = new HiHealthData(i);
            hiHealthData2.setValue(a3.e());
            hiHealthData2.setTimeInterval(a3.d() * 1000, (a3.d() + 60) * 1000);
            hiHealthData2.setDeviceUuid(str);
            list.add(hiHealthData2);
        } else {
            LogUtil.h("HwBasicStorage", "tempSportData invalid date distance:", Integer.valueOf(a3.e()), "getTotalDistance Timestamp:", Long.valueOf(a3.d()));
        }
        if (a3.c() > 0) {
            HiHealthData hiHealthData3 = new HiHealthData(4);
            hiHealthData3.setTimeInterval(a3.d() * 1000, (a3.d() + 60) * 1000);
            hiHealthData3.setValue(a3.c());
            hiHealthData3.setDeviceUuid(str);
            list.add(hiHealthData3);
            return;
        }
        LogUtil.h("HwBasicStorage", "tempSportData invalid date calorie:", Integer.valueOf(a3.c()), "getTotalCalorie startTime:", Long.valueOf(a3.d()));
    }

    private void d(jxu jxuVar, jxq jxqVar) {
        if (jxuVar.a() == 0) {
            LogUtil.h("HwBasicStorage", "updateLastTotal getCurrentStatus is 0");
            return;
        }
        int b2 = jxuVar.b();
        int c = jxuVar.c();
        int e = jxuVar.e();
        if (b2 == 0 && c == 0 && e == 0) {
            LogUtil.h("HwBasicStorage", "updateLastTotal getCurrentStatus:step,calorie,distance:0");
            return;
        }
        jxqVar.c(e);
        jxqVar.a(b2);
        jxqVar.e(c);
        jxqVar.a(jxuVar.d());
    }

    public void a(jwy jwyVar, List<jxo> list, int i, DeviceInfo deviceInfo) {
        if (jwyVar == null) {
            LogUtil.h("HwBasicStorage", "saveFitnessData fitnessManager is null");
            return;
        }
        if (list == null) {
            LogUtil.h("HwBasicStorage", "saveFitnessData healthDataList is null");
            return;
        }
        LogUtil.a("HwBasicStorage", "saveFitnessData enter, stage:", Integer.valueOf(i));
        Collections.sort(list, new Comparator<jxo>() { // from class: jws.5
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(jxo jxoVar, jxo jxoVar2) {
                if (jxoVar == null || jxoVar2 == null) {
                    return 0;
                }
                return jxoVar.e() - jxoVar2.e();
            }
        });
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        jxq b2 = new jwt().b(jwyVar);
        long e = b2.e();
        int b3 = jxi.b(deviceInfo);
        b2.a(deviceInfo);
        if (b3 == 4) {
            Iterator<jxo> it = list.iterator();
            while (it.hasNext()) {
                c(it.next(), e, b2, arrayList, arrayList2);
            }
        } else if (jxi.d(deviceInfo) == 0) {
            Iterator<jxo> it2 = list.iterator();
            while (it2.hasNext()) {
                c(it2.next(), e, b2, arrayList, arrayList2);
            }
        } else {
            Iterator<jxo> it3 = list.iterator();
            while (it3.hasNext()) {
                c(it3.next(), e, b2, arrayList, arrayList2);
            }
        }
        c(i, arrayList, arrayList2, e, b2);
        LogUtil.a("HwBasicStorage", "saveFitnessData leave");
    }

    public void c(jwy jwyVar, List<jxw> list, List<jxx> list2, int i, DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2;
        long j;
        if (jwyVar == null) {
            LogUtil.h("HwBasicStorage", "saveFitnessData statusFrameList fitnessManager is null");
            return;
        }
        LogUtil.a("HwBasicStorage", "saveFitnessData in separated type");
        jxq b2 = new jwt().b(jwyVar);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        String e = keg.e();
        if (list != null) {
            e(list, b2, arrayList, e);
        }
        long d = jwr.d(jwy.b());
        if (list2 != null) {
            j = e(list2, arrayList2, d, e);
            deviceInfo2 = deviceInfo;
        } else {
            deviceInfo2 = deviceInfo;
            j = d;
        }
        b2.a(deviceInfo2);
        c(i, arrayList, arrayList2, j, b2);
    }

    private void d(jxu jxuVar, List<HiHealthData> list, jxy jxyVar, HashMap<Integer, Integer> hashMap, String str) {
        LogUtil.a("HwBasicStorage", "updateStatus DataRawSportData:", jxuVar);
        if (jxuVar.b() == 0 && jxuVar.c() == 0 && jxuVar.e() == 0) {
            return;
        }
        if (jxyVar.e() == jxuVar.a() && jxyVar.b() == jxuVar.d()) {
            jxyVar.d(60);
            return;
        }
        b(jxyVar, list, hashMap, str);
        jxyVar.e(jxuVar.d());
        jxyVar.e(jxuVar.a());
        jxyVar.a(60);
    }

    private void b(jxn jxnVar, List<HiHealthData> list, jxy jxyVar, HashMap<Integer, Integer> hashMap, String str) {
        LogUtil.a("HwBasicStorage", "updateStatus DataRawSleepData:", jxnVar);
        if (jxyVar.e() == jxnVar.b() && jxyVar.b() == jxnVar.c()) {
            jxyVar.d(60);
            return;
        }
        b(jxyVar, list, hashMap, str);
        jxyVar.e(jxnVar.c());
        jxyVar.e(jxnVar.b());
        jxyVar.a(60);
    }

    private void a(List<HiHealthData> list, jxy jxyVar, HashMap<Integer, Integer> hashMap, String str) {
        LogUtil.a("HwBasicStorage", "updateStatus null");
        if (jxyVar != null) {
            b(jxyVar, list, hashMap, str);
        }
    }

    private void c(jxo jxoVar, long j, jxq jxqVar, List<HiHealthData> list, List<HiHealthData> list2) {
        if (jxoVar == null) {
            LogUtil.b("HwBasicStorage", "dataHealthData is null");
            return;
        }
        List<jxu> b2 = jxoVar.b();
        LogUtil.a("HwBasicStorage", "dataHealthData time:", Integer.valueOf(jxoVar.e()), "dataHealthData date:", new Date(jxoVar.e() * 1000));
        jxy jxyVar = new jxy();
        String str = jxqVar.d().getSecurityUuid() + "#ANDROID21";
        String deviceIdentify = jxqVar.d().getDeviceIdentify();
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        if (b2 != null) {
            for (jxu jxuVar : b2) {
                if (jxuVar.a() != 255 && j < jxuVar.d()) {
                    jxu b3 = b(jxuVar, jxqVar);
                    d(b3, jxqVar);
                    e(b3, list, str, deviceIdentify);
                    d(jxuVar, list2, jxyVar, hashMap, str);
                }
            }
        }
        List<jxn> a2 = jxoVar.a();
        if (a2 != null) {
            Iterator<jxn> it = a2.iterator();
            while (it.hasNext()) {
                b(it.next(), list2, jxyVar, hashMap, str);
            }
        }
        a(list2, jxyVar, hashMap, str);
    }

    private void d(List<jxv> list) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("Total:" + list.size());
        int i = 0;
        for (jxv jxvVar : list) {
            sb.append("[i:");
            sb.append(i);
            sb.append("]");
            sb.append(jxvVar);
            i++;
        }
        LogUtil.a("HwBasicStorage", sb.toString());
    }

    private void c(jxw jxwVar, jxq jxqVar, List<HiHealthData> list, String str) {
        if (jxwVar != null) {
            List<jxv> b2 = jxwVar.b();
            d(b2);
            Iterator<jxv> it = b2.iterator();
            while (it.hasNext()) {
                b(it.next(), list, str);
            }
            if (b2.size() > 0) {
                long b3 = b2.get(b2.size() - 1).b();
                if (jxqVar.e() < b3) {
                    jxqVar.a(b3);
                }
            }
        }
    }

    private void e(List<jxw> list, jxq jxqVar, List<HiHealthData> list2, String str) {
        LogUtil.a("HwBasicStorage", "saveSampleFrameList enter");
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwBasicStorage", "sampleFrameList is null");
            return;
        }
        Collections.sort(list, new Comparator<jxw>() { // from class: jws.4
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(jxw jxwVar, jxw jxwVar2) {
                if (jxwVar == null || jxwVar2 == null) {
                    return 0;
                }
                return (int) (jxwVar.e() - jxwVar2.e());
            }
        });
        Iterator<jxw> it = list.iterator();
        while (it.hasNext()) {
            c(it.next(), jxqVar, list2, str);
        }
    }

    private long a(jxx jxxVar, long j, List<HiHealthData> list, HashMap<Integer, Integer> hashMap, String str) {
        if (jxxVar == null) {
            LogUtil.h("HwBasicStorage", "statusFrame is null");
            return j;
        }
        List<jxy> a2 = jxxVar.a();
        Iterator<jxy> it = a2.iterator();
        while (it.hasNext()) {
            b(it.next(), list, hashMap, str);
        }
        if (a2.size() > 0) {
            long d = a2.get(a2.size() - 1).d();
            if (d > j) {
                return d;
            }
        }
        return j;
    }

    private long e(List<jxx> list, List<HiHealthData> list2, long j, String str) {
        LogUtil.a("HwBasicStorage", "saveStatusFrameList enter");
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwBasicStorage", "statusFrameList is null");
            return j;
        }
        Iterator<jxx> it = list.iterator();
        long j2 = j;
        while (it.hasNext()) {
            j2 = a(it.next(), j2, list2, hashMap, str);
        }
        LogUtil.a("HwBasicStorage", "saveStatusFrameList errorSportTypeMap:", hashMap.toString());
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
                LogUtil.h("HwBasicStorage", "getHiHealthDataType dataType:", Integer.valueOf(i));
                i2 = 0;
                break;
        }
        LogUtil.a("HwBasicStorage", "getHiHealthSessionType sportType:", Integer.valueOf(i2));
        return i2;
    }

    private void b(jxv jxvVar, List<HiHealthData> list, String str) {
        if (a(a(jxvVar.a()), jxvVar.e())) {
            HiHealthData hiHealthData = new HiHealthData(a(jxvVar.a()));
            hiHealthData.setTimeInterval(jxvVar.b() * 1000, (jxvVar.b() + jxvVar.d()) * 1000);
            hiHealthData.setValue(jxvVar.e());
            hiHealthData.setDeviceUuid(str);
            LogUtil.a("HwBasicStorage", "insertSamplePoint hiHealthData:", hiHealthData);
            list.add(hiHealthData);
            return;
        }
        LogUtil.h("HwBasicStorage", "insertSamplePoint invalid samplePoint:", jxvVar);
    }

    private int a(int i, HashMap<Integer, Integer> hashMap) {
        switch (i) {
            case 0:
            case 1:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
                return 20002;
            case 2:
                return 20003;
            case 3:
                return 20004;
            case 4:
                return 20005;
            case 5:
            case 8:
            case 13:
            default:
                LogUtil.h("HwBasicStorage", "getHiHealthSessionType dataType:", Integer.valueOf(i));
                e(i, hashMap);
                return 0;
            case 6:
                return 22002;
            case 7:
                return 22001;
            case 9:
                return 20007;
        }
    }

    private void e(int i, HashMap<Integer, Integer> hashMap) {
        if (hashMap.containsKey(Integer.valueOf(i))) {
            hashMap.put(Integer.valueOf(i), Integer.valueOf(hashMap.get(Integer.valueOf(i)).intValue() + 1));
        } else {
            hashMap.put(Integer.valueOf(i), 1);
        }
    }

    private void b(jxy jxyVar, List<HiHealthData> list, HashMap<Integer, Integer> hashMap, String str) {
        if (jxyVar == null) {
            LogUtil.h("HwBasicStorage", "statusPoint is null");
            return;
        }
        LogUtil.a("HwBasicStorage", "statusPoint:", jxyVar);
        for (long d = jxyVar.d(); d < jxyVar.b(); d += 60) {
            if (a(jxyVar.e(), hashMap) != 0) {
                HiHealthData hiHealthData = new HiHealthData(a(jxyVar.e(), hashMap));
                hiHealthData.setTimeInterval(d * 1000, (d + 60) * 1000);
                hiHealthData.setDeviceUuid(str);
                LogUtil.a("HwBasicStorage", "saveStatusToHiHealth hiHealthData:", hiHealthData);
                list.add(hiHealthData);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(jxq jxqVar, long j) {
        jwy b2 = jwy.b();
        jwt jwtVar = new jwt();
        if (jxqVar != null) {
            if (jxqVar.f() == 2) {
                b2.ab.b(b2, jxqVar.e());
                b2.ab.a(b2, j);
            } else {
                jwtVar.b(b2, jxqVar);
                jwr.b(b2, j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKH_(int i, final jxq jxqVar, final Bundle bundle) {
        LogUtil.a("HwBasicStorage", "processSaveDataComplete enter errorCode:", Integer.valueOf(i));
        if (jxqVar == null) {
            LogUtil.h("HwBasicStorage", "processSaveDataComplete totalValue is null.");
            return;
        }
        if (bundle == null || bundle.isEmpty()) {
            LogUtil.h("HwBasicStorage", "processSaveDataComplete bundle is null.");
            return;
        }
        if (i == 0) {
            jrq.b("HwBasicStorage", new Runnable() { // from class: jws.3
                @Override // java.lang.Runnable
                public void run() {
                    jws.this.c(jxqVar, bundle.getLong("newStatusTime"));
                }
            });
        }
        jwy.b().d(0, jxqVar.d());
    }

    private void c(final int i, List<HiHealthData> list, List<HiHealthData> list2, final long j, final jxq jxqVar) {
        LogUtil.a("HwBasicStorage", "saveDataToHiHealth enter mDataList.size:", Integer.valueOf(list.size()), " mStatusList.size:", Integer.valueOf(list2.size()), "stage:", Integer.valueOf(i), " newStatusTime", Long.valueOf(j));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        list.addAll(list2);
        hiDataInsertOption.setDatas(list);
        final boolean a2 = a(list);
        LogUtil.a("HwBasicStorage", "saveDataToHiHealth mDataList: ", Arrays.asList(list));
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jws.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                ReleaseLogUtil.e("R_HwBasicStorage", "saveDataToHiHealth onResult type:", Integer.valueOf(i2), ", object:", obj);
                jxqVar.g(i);
                Message obtain = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putLong("newStatusTime", j);
                obtain.setData(bundle);
                obtain.obj = jxqVar;
                if (i2 == 0) {
                    iyv.c();
                    obtain.what = 0;
                    if (a2) {
                        jws.this.a();
                    }
                } else {
                    obtain.what = 1;
                }
                jws.this.f14151a.sendMessage(obtain);
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
                if (localBroadcastManager == null) {
                    LogUtil.h("HwBasicStorage", "sendBroadcast, but localBroadcastManager is null.");
                } else if (i == 1) {
                    localBroadcastManager.sendBroadcast(new Intent("com.huawei.health.action.COMMON_DATA_SAVE_COMPLETED"));
                }
            }
        });
    }

    private boolean a(List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            if (HiHealthDataType.r(it.next().getType())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public void d(List<jxr> list, final DeviceInfo deviceInfo) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        arrayList2.addAll(list);
        list.clear();
        LogUtil.a("HwBasicStorage", "enter saveIntensiveInfo");
        a(arrayList, arrayList2);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jws.6
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("R_HwBasicStorage", "saveIntensiveInfo onResult type:", Integer.valueOf(i), ", object:", obj);
                Message obtain = Message.obtain();
                if (i == 0) {
                    obtain.what = 2;
                } else {
                    obtain.what = 3;
                }
                obtain.obj = deviceInfo;
                jws.this.f14151a.sendMessage(obtain);
            }
        });
    }

    private void a(List<HiHealthData> list, List<jxr> list2) {
        int c;
        String e = keg.e();
        if (list2 == null || list2.isEmpty()) {
            LogUtil.h("HwBasicStorage", "desFrameList is null or empty");
            return;
        }
        for (jxr jxrVar : list2) {
            LogUtil.a("HwBasicStorage", "DesFrame:", jxrVar.toString());
            List<jxr.e> d = jxrVar.d();
            int i = 0;
            while (i < d.size()) {
                jxr.e eVar = d.get(i);
                int d2 = eVar.d();
                int c2 = eVar.c();
                int i2 = d2 - (d2 % 60);
                int i3 = ((c2 - (c2 % 60)) - i2) / 60;
                int i4 = 0;
                while (i4 < i3) {
                    HiHealthData hiHealthData = new HiHealthData();
                    long j = ((i4 * 60) + i2) * 1000;
                    int i5 = i4;
                    long j2 = (r2 + 60) * 1000;
                    hiHealthData.setTimeInterval(j, j2);
                    hiHealthData.setValue(b(eVar.a()));
                    hiHealthData.setType(7);
                    hiHealthData.setDeviceUuid(e);
                    list.add(hiHealthData);
                    LogUtil.a("HwBasicStorage", "Intensive data tempStartTime:", Long.valueOf(j), "tempEndTime:", Long.valueOf(j2), "value:", Integer.valueOf(b(eVar.a())), "type:", 7, "uuid:", e);
                    i4 = i5 + 1;
                    i3 = i3;
                    i = i;
                    d = d;
                }
                i++;
            }
        }
        if (list2.size() > 0) {
            jxr jxrVar2 = list2.get(list2.size() - 1);
            if (jxrVar2.d().isEmpty() || jxrVar2.d().size() <= 0 || (c = jxrVar2.d().get(jxrVar2.d().size() - 1).c()) <= this.d) {
                return;
            }
            this.d = c;
        }
    }

    private int b(int i) {
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
        LogUtil.h("HwBasicStorage", "intensityType sportType:", Integer.valueOf(i));
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicStorage", "processSaveDesDataComplete enter errorCode:", Integer.valueOf(i));
        jwy b2 = jwy.b();
        int c = b2.ab.c();
        if (i == 0) {
            if (c == 1) {
                jwr.d(b2, this.d);
            } else {
                jwr.e(b2, jxi.d(System.currentTimeMillis() / 1000));
                return;
            }
        }
        b2.e(i, deviceInfo);
    }

    static class b {
        private static final jws c = new jws();
    }
}
