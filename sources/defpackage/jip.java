package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.DataTotalMotion;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import defpackage.jhy;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jip {
    private static final Object b = new Object();
    private static jip e;
    private List<HiHealthData> c;
    private c h;
    private List<HiHealthData> j;
    private jih f = null;
    private int i = 0;
    private jib m = new jib();
    private long d = 0;

    /* renamed from: a, reason: collision with root package name */
    private long f13875a = 0;
    private long g = 0;

    private boolean c(int i, int i2) {
        return i == 2 ? i2 > 0 && i2 < 500 : i == 4 ? i2 > 0 && i2 < 65535 : (i == 2018 || i == 2002) ? i2 > 0 && i2 < 255 : i == 5 ? i2 > 0 && i2 < 1500 : i2 > 0;
    }

    private jip() {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "FitnessMgrAw70Storage Constructor");
        this.h = new c(this, BaseApplication.getContext().getMainLooper());
    }

    public static jip a() {
        jip jipVar;
        synchronized (b) {
            if (e == null) {
                e = new jip();
            }
            jipVar = e;
        }
        return jipVar;
    }

    static class c extends Handler {
        WeakReference<jip> c;

        c(jip jipVar, Looper looper) {
            super(looper);
            this.c = new WeakReference<>(jipVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("FitnessMgrAw70Storage", "handleMessage(), message is null");
                return;
            }
            super.handleMessage(message);
            jip jipVar = this.c.get();
            if (jipVar == null) {
                LogUtil.h("FitnessMgrAw70Storage", "handleMessage(), storageAdapter is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                jipVar.c(0);
                return;
            }
            if (i == 1) {
                jipVar.c(300006);
                return;
            }
            if (i == 101) {
                LogUtil.a("05", 1, "FitnessMgrAw70Storage", "save data success");
                jipVar.a(0);
            } else if (i == 102) {
                LogUtil.a("05", 1, "FitnessMgrAw70Storage", "save data fail");
                jipVar.a(300006);
            } else {
                LogUtil.h("05", 1, "FitnessMgrAw70Storage", "unknown message type");
            }
        }
    }

    public void d() {
        new jil().b();
    }

    public void e() {
        LogUtil.a("FitnessMgrAw70Storage", "initFitnessUserStorage.");
    }

    public void a(jik jikVar, jhw jhwVar) {
        if (jhwVar == null) {
            LogUtil.h("FitnessMgrAw70Storage", "saveTodayTotalData() todayTotalMotion is null");
            return;
        }
        c(jhwVar);
        List<DataTotalMotion> d = jhwVar.d();
        ArrayList arrayList = new ArrayList(16);
        FitnessTotalData fitnessTotalData = new FitnessTotalData();
        fitnessTotalData.setCalorie(jhwVar.c() * 1000);
        for (DataTotalMotion dataTotalMotion : d) {
            a(dataTotalMotion);
            c(dataTotalMotion, arrayList, fitnessTotalData, jikVar);
        }
        a(fitnessTotalData, arrayList);
    }

    private void c(DataTotalMotion dataTotalMotion, List<FitnessTotalData> list, FitnessTotalData fitnessTotalData, jik jikVar) {
        int motion_type = dataTotalMotion.getMotion_type();
        if (motion_type != 10) {
            switch (motion_type) {
                case 1:
                case 2:
                    fitnessTotalData.addSteps(dataTotalMotion.getStep());
                    fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                    list.add(new FitnessTotalData(dataTotalMotion));
                    break;
                case 3:
                    FitnessTotalData d = d(jikVar, dataTotalMotion);
                    fitnessTotalData.addSteps(d.getSteps());
                    fitnessTotalData.setHeight(d.getHeight());
                    fitnessTotalData.addDistance(d.getDistance());
                    list.add(a(dataTotalMotion, jikVar));
                    break;
                case 4:
                    fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                    list.add(new FitnessTotalData(dataTotalMotion));
                    break;
                case 5:
                    LogUtil.a("05", 1, "FitnessMgrAw70Storage", "stand type");
                    break;
                case 6:
                    LogUtil.a("05", 1, "FitnessMgrAw70Storage", "shallow sleep type");
                    break;
                case 7:
                    LogUtil.a("05", 1, "FitnessMgrAw70Storage", "deep sleep type");
                    break;
                default:
                    LogUtil.c("05", 1, "FitnessMgrAw70Storage", "unknown type");
                    break;
            }
        }
        fitnessTotalData.addSteps(dataTotalMotion.getStep());
        fitnessTotalData.addDistance(dataTotalMotion.getDistance());
        list.add(b(dataTotalMotion));
    }

    private FitnessTotalData d(jik jikVar, DataTotalMotion dataTotalMotion) {
        FitnessTotalData fitnessTotalData = new FitnessTotalData();
        fitnessTotalData.setSteps(dataTotalMotion.getStep());
        if (jikVar != null) {
            if (jikVar.e()) {
                fitnessTotalData.setHeight(dataTotalMotion.getDistance());
            } else {
                fitnessTotalData.setHeight(dataTotalMotion.getHeight());
                fitnessTotalData.setDistance(dataTotalMotion.getDistance());
            }
        }
        return fitnessTotalData;
    }

    private void a(FitnessTotalData fitnessTotalData, List<FitnessTotalData> list) {
        list.add(fitnessTotalData);
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveTodayTotalData steps:", Integer.valueOf(fitnessTotalData.getSteps()), " calorie:", Integer.valueOf(fitnessTotalData.getCalorie()), " distance:", Integer.valueOf(fitnessTotalData.getDistance()), " size:", Integer.valueOf(list.size()));
        c(fitnessTotalData);
        d(fitnessTotalData);
    }

    private void a(DataTotalMotion dataTotalMotion) {
        LogUtil.c("FitnessMgrAw70Storage", "type:", Integer.valueOf(dataTotalMotion.getMotion_type()), " step:", Integer.valueOf(dataTotalMotion.getStep()), " calorie:", Integer.valueOf(dataTotalMotion.getCalorie()), " distance:", Integer.valueOf(dataTotalMotion.getDistance()));
    }

    private void c(jhw jhwVar) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveTodayTotalData get data success total calorie is:", Integer.valueOf(jhwVar.c()));
    }

    private FitnessTotalData b(DataTotalMotion dataTotalMotion) {
        FitnessTotalData fitnessTotalData = new FitnessTotalData(dataTotalMotion);
        fitnessTotalData.setSportType(2);
        return fitnessTotalData;
    }

    private FitnessTotalData a(DataTotalMotion dataTotalMotion, jik jikVar) {
        FitnessTotalData fitnessTotalData = new FitnessTotalData(dataTotalMotion);
        if (jikVar != null && jikVar.e()) {
            fitnessTotalData.setHeight(fitnessTotalData.getDistance());
            fitnessTotalData.setDistance(0);
        }
        return fitnessTotalData;
    }

    private void d(FitnessTotalData fitnessTotalData) {
        String str;
        DeviceInfo e2 = jpu.e("FitnessMgrAw70Storage");
        if (e2 != null) {
            str = "_" + e2.getDeviceIdentify();
        } else {
            str = "";
        }
        StorageParams storageParams = new StorageParams(1);
        if (d(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps()) == 200004) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG" + str);
            d(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps());
        }
        String str2 = (fitnessTotalData.getCalorie() / 1000) + "|" + (System.currentTimeMillis() / 1000);
        if (SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_CAL_FROM_DEVICE_FLAG" + str, str2, storageParams) == 200004) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_CAL_FROM_DEVICE_FLAG" + str);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_CAL_FROM_DEVICE_FLAG" + str, str2, storageParams);
        }
        if (d(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance()) == 200004) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG" + str);
            d(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance());
        }
    }

    private int d(String str, StorageParams storageParams, String str2, int i) {
        return SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), str2 + str, i + "|" + (System.currentTimeMillis() / 1000), storageParams);
    }

    public static long e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public void c(FitnessTotalData fitnessTotalData) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveTodayTotalToHiHealth fitnessTotalData :", fitnessTotalData);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        ArrayList arrayList = new ArrayList(16);
        arrayList.addAll(a(fitnessTotalData));
        if (arrayList.isEmpty()) {
            LogUtil.h("FitnessMgrAw70Storage", "saveTodayTotalToHiHealth() dataList is empty");
        } else {
            hiDataInsertOption.setDatas(arrayList);
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jip.3
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveTodayTotalToHiHealth onResult type:", Integer.valueOf(i), " object:", obj);
                    if (i == 0) {
                        iyv.c();
                    }
                }
            });
        }
    }

    private ArrayList<HiHealthData> a(FitnessTotalData fitnessTotalData) {
        ArrayList<HiHealthData> arrayList = new ArrayList<>(16);
        if (fitnessTotalData == null) {
            LogUtil.h("FitnessMgrAw70Storage", "addData() fitnessTotalData is null");
            return arrayList;
        }
        long e2 = e(System.currentTimeMillis());
        if (fitnessTotalData.getSteps() > 0) {
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setType(40002);
            hiHealthData.setDeviceUuid(jpp.b());
            hiHealthData.setTimeInterval(e2, System.currentTimeMillis());
            hiHealthData.setValue(fitnessTotalData.getSteps());
            arrayList.add(hiHealthData);
            HiHealthData hiHealthData2 = new HiHealthData();
            hiHealthData2.setType(901);
            hiHealthData2.setDeviceUuid(jpp.b());
            hiHealthData2.setTimeInterval(e2, System.currentTimeMillis());
            hiHealthData2.setValue(fitnessTotalData.getSteps());
            arrayList.add(hiHealthData2);
        }
        if (fitnessTotalData.getCalorie() > 0) {
            HiHealthData hiHealthData3 = new HiHealthData();
            hiHealthData3.setType(40003);
            hiHealthData3.setDeviceUuid(jpp.b());
            hiHealthData3.setTimeInterval(e2, System.currentTimeMillis());
            hiHealthData3.setValue(fitnessTotalData.getCalorie());
            arrayList.add(hiHealthData3);
        }
        if (fitnessTotalData.getDistance() > 0) {
            HiHealthData hiHealthData4 = new HiHealthData();
            hiHealthData4.setType(40004);
            hiHealthData4.setDeviceUuid(jpp.b());
            hiHealthData4.setTimeInterval(e2, System.currentTimeMillis());
            hiHealthData4.setValue(fitnessTotalData.getDistance());
            arrayList.add(hiHealthData4);
        }
        if (fitnessTotalData.getHeight() > 0) {
            HiHealthData hiHealthData5 = new HiHealthData();
            hiHealthData5.setType(SmartMsgConstant.MSG_TYPE_RIDE_USER);
            hiHealthData5.setDeviceUuid(jpp.b());
            hiHealthData5.setTimeInterval(e2, System.currentTimeMillis());
            hiHealthData5.setValue(fitnessTotalData.getHeight());
            arrayList.add(hiHealthData5);
        }
        return arrayList;
    }

    public void c(List<jic> list, List<jif> list2) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveFitnessData in SEPARATED type");
        jib a2 = new jil().a();
        this.m = a2;
        this.d = a2.d();
        c();
        c(list);
        e(list2);
        h();
    }

    public void b(List<jie> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("FitnessMgrAw70Storage", "logSampleList() samplePoints is null");
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append("Total:" + list.size());
        int i = 0;
        for (jie jieVar : list) {
            sb.append("[index:");
            sb.append(i);
            sb.append("]");
            sb.append(jieVar);
            i++;
        }
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", sb.toString());
    }

    public void d(jic jicVar) {
        if (jicVar == null) {
            LogUtil.h("FitnessMgrAw70Storage", "saveSampleFrame() sampleFrame is null");
            return;
        }
        List<jie> e2 = jicVar.e();
        b(e2);
        for (jie jieVar : e2) {
            if (this.d >= jieVar.b()) {
                if (jieVar.d() == 1) {
                    d(jieVar);
                }
            } else {
                b(jieVar);
            }
        }
        if (e2.size() > 0) {
            long b2 = e2.get(e2.size() - 1).b();
            if (this.m.d() < b2) {
                this.m.e(b2);
            }
        }
    }

    public void c(List<jic> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("FitnessMgrAw70Storage", "saveSampleFrameList() sampleFrameList is null");
            return;
        }
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveSampleFrameList enter");
        Collections.sort(list, new Comparator<jic>() { // from class: jip.5
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(jic jicVar, jic jicVar2) {
                return (int) (jicVar.d() - jicVar2.d());
            }
        });
        Iterator<jic> it = list.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    public void d(jif jifVar) {
        if (jifVar == null) {
            LogUtil.h("FitnessMgrAw70Storage", "saveStatusFrame() statusFrame is null");
            return;
        }
        List<jih> b2 = jifVar.b();
        for (jih jihVar : b2) {
            if (jihVar.c() <= this.f13875a) {
                if (jihVar.b() == 1) {
                    b(jihVar);
                } else if ((jihVar.e() == 7 || jihVar.e() == 6) && jihVar.c() > this.f13875a - 1800) {
                    b(jihVar);
                } else {
                    LogUtil.c("FitnessMgrAw70Storage", "no need special handle!");
                }
            } else {
                d(jihVar);
            }
        }
        if (b2.size() > 0) {
            long c2 = b2.get(b2.size() - 1).c();
            if (c2 > this.g) {
                this.g = c2;
            }
        }
    }

    public void e(List<jif> list) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveStatusFrameList enter");
        long b2 = jio.b();
        this.f13875a = b2;
        this.g = b2;
        if (list == null || list.isEmpty()) {
            LogUtil.h("FitnessMgrAw70Storage", "saveStatusFrameList() or statusFrameList is null");
            return;
        }
        Iterator<jif> it = list.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    void c() {
        this.c = new ArrayList(16);
        this.j = new ArrayList(16);
    }

    private int d(int i) {
        switch (i) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 5;
            case 6:
                return 2018;
            case 7:
                return 2002;
            default:
                LogUtil.c("FitnessMgrAw70Storage", "getHiHealthDataType unknown type :", Integer.valueOf(i));
                return 0;
        }
    }

    private void d(jie jieVar) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "updateSamplePoint sample:", jieVar);
        b(jieVar);
    }

    private void b(jie jieVar) {
        if (c(d(jieVar.c()), jieVar.a())) {
            HiHealthData hiHealthData = new HiHealthData(d(jieVar.c()));
            hiHealthData.setTimeInterval(jieVar.b() * 1000, (jieVar.b() + jieVar.e()) * 1000);
            hiHealthData.setValue(jieVar.a());
            hiHealthData.setDeviceUuid(jpp.b());
            this.c.add(hiHealthData);
            return;
        }
        LogUtil.c("FitnessMgrAw70Storage", "insertSamplePoint invalid sample:", jieVar);
    }

    private int b(int i) {
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
        LogUtil.c("FitnessMgrAw70Storage", "getHiHealthSessionType unknown type :", Integer.valueOf(i));
        return 0;
    }

    private void b(jih jihVar) {
        LogUtil.c("FitnessMgrAw70Storage", "updateStatusToHiHealth:", jihVar);
        d(jihVar);
    }

    private void d(jih jihVar) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "statusPoint :", jihVar);
        for (long c2 = jihVar.c(); c2 < jihVar.a(); c2 += 60) {
            if (b(jihVar.e()) != 0) {
                HiHealthData hiHealthData = new HiHealthData(b(jihVar.e()));
                hiHealthData.setTimeInterval(c2 * 1000, (c2 + 60) * 1000);
                hiHealthData.setDeviceUuid(jpp.b());
                LogUtil.c("FitnessMgrAw70Storage", "saveStatusToHiHealth hiHealthData:", hiHealthData);
                this.j.add(hiHealthData);
            }
        }
    }

    public void b() {
        new jil().a(this.m);
        jio.a(this.g);
    }

    public void a(int i) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "processSaveDataComplete enter errCode:", Integer.valueOf(i));
        if (i == 0) {
            jrq.b("FitnessMgrAw70Storage", new Runnable() { // from class: jip.4
                @Override // java.lang.Runnable
                public void run() {
                    jip.this.b();
                }
            });
        }
        jik.d(BaseApplication.getContext()).b(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "startSyncIntensiveData enter errorCode:", Integer.valueOf(i));
        jik.d(BaseApplication.getContext()).j();
    }

    public void a(List<jhy> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("FitnessMgrAw70Storage", "saveIntensiveInfo() desFrames is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("FitnessMgrAw70Storage", "enter saveIntensiveInfo");
        d(arrayList, list);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        LogUtil.c("FitnessMgrAw70Storage", "saveIntensiveInfo dataList size:", Integer.valueOf(arrayList.size()), ",value", arrayList.toString());
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jip.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveIntensiveInfo onResult type:", Integer.valueOf(i), " object:", obj);
                if (i == 0) {
                    jip.this.h.sendEmptyMessage(101);
                } else {
                    jip.this.h.sendEmptyMessage(102);
                    LogUtil.a("FitnessMgrAw70Storage", "saveIntensiveInfo not correct");
                }
            }
        });
    }

    private void d(List<HiHealthData> list, List<jhy> list2) {
        String b2 = jpp.b();
        Iterator<jhy> it = list2.iterator();
        while (true) {
            char c2 = 1;
            if (!it.hasNext()) {
                break;
            }
            jhy next = it.next();
            List<jhy.a> e2 = next.e();
            int i = 0;
            int i2 = 0;
            while (i2 < e2.size()) {
                jhy.a aVar = e2.get(i2);
                Object[] objArr = new Object[2];
                objArr[i] = "DesFrame :";
                objArr[c2] = next.toString();
                LogUtil.a("FitnessMgrAw70Storage", objArr);
                int c3 = aVar.c();
                int d = aVar.d();
                int i3 = c3 - (c3 % 60);
                int i4 = ((d - (d % 60)) - i3) / 60;
                int i5 = i;
                while (i5 < i4) {
                    HiHealthData hiHealthData = new HiHealthData();
                    long j = ((i5 * 60) + i3) * 1000;
                    long j2 = (r13 + 60) * 1000;
                    hiHealthData.setTimeInterval(j, j2);
                    hiHealthData.setValue(e(aVar.b()));
                    hiHealthData.setType(7);
                    hiHealthData.setDeviceUuid(b2);
                    list.add(hiHealthData);
                    LogUtil.a("FitnessMgrAw70Storage", "Intensive data: startTime :", Long.valueOf(j), " endTime :", Long.valueOf(j2), " value :", Integer.valueOf(e(aVar.b())), " type :", 7);
                    i5++;
                    e2 = e2;
                    i2 = i2;
                }
                i2++;
                c2 = 1;
                i = 0;
            }
        }
        if (list2.size() > 0) {
            jhy jhyVar = list2.get(list2.size() - 1);
            int d2 = jhyVar.e().get(jhyVar.e().size() - 1).d();
            if (d2 > this.i) {
                this.i = d2;
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
        if (i == 10) {
            return 2;
        }
        LogUtil.c("FitnessMgrAw70Storage", "changType unknown type :", Integer.valueOf(i));
        return 0;
    }

    private void h() {
        LogUtil.a("05", 1, "FitnessMgrAw70Storage", "saveDataToHiHealth enter mHiHealthDataList.size:", Integer.valueOf(this.c.size()), " mStatusList.size:", Integer.valueOf(this.j.size()));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        this.c.addAll(this.j);
        LogUtil.a("FitnessMgrAw70Storage", "saveDataToHiHealth after add mHiHealthDataList.size:", Integer.valueOf(this.c.size()), ", mStatusList is ", Arrays.asList(this.j));
        hiDataInsertOption.setDatas(this.c);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jip.1
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("05", 1, "FitnessMgrAw70Storage", "insertSamplePointHiHealthData onResult type:", Integer.valueOf(i), " object:", obj);
                if (i == 0) {
                    jip.this.h.sendEmptyMessage(0);
                } else {
                    jip.this.h.sendEmptyMessage(1);
                    LogUtil.a("FitnessMgrAw70Storage", "insertSamplePointHiHealthData not correct");
                }
            }
        });
    }
}
