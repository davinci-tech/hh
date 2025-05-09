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
import defpackage.jxr;
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
public class jxe {

    /* renamed from: a, reason: collision with root package name */
    private int f14166a;
    private long b;
    private List<HiHealthData> c;
    private d d;
    private long e;
    private List<HiHealthData> f;
    private jxy h;
    private long i;
    private jxq j;

    private boolean c(int i, int i2) {
        return i == 2 ? i2 > 0 && i2 < 500 : i == 4 ? i2 > 0 && i2 < 65535 : (i == 2018 || i == 2002) ? i2 > 0 && i2 < 255 : i == 5 ? i2 > 0 && i2 < 1500 : i2 > 0;
    }

    private jxe() {
        this.h = null;
        this.f14166a = 0;
        this.j = new jxq();
        this.b = 0L;
        this.e = 0L;
        this.i = 0L;
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "FitnessMgrAw70Storage Constructor");
        this.d = new d(this, BaseApplication.getContext().getMainLooper());
    }

    public static jxe b() {
        return c.f14170a;
    }

    static class d extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<jxe> f14171a;

        d(jxe jxeVar, Looper looper) {
            super(looper);
            this.f14171a = new WeakReference<>(jxeVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("BasicMgrAw70Storage", "handleMessage(), message is null");
                return;
            }
            super.handleMessage(message);
            jxe jxeVar = this.f14171a.get();
            if (jxeVar == null) {
                LogUtil.h("BasicMgrAw70Storage", "handleMessage(), storageAdapter is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                jxeVar.b(0);
                return;
            }
            if (i == 1) {
                jxeVar.b(300006);
                return;
            }
            if (i == 101) {
                LogUtil.a("05", 1, "BasicMgrAw70Storage", "save data success");
                jxeVar.e(0);
            } else if (i == 102) {
                LogUtil.a("05", 1, "BasicMgrAw70Storage", "save data fail");
                jxeVar.e(300006);
            } else {
                LogUtil.h("05", 1, "BasicMgrAw70Storage", "unknown message type");
            }
        }
    }

    public void c() {
        new jxa().b();
    }

    public void e(jxc jxcVar, jxt jxtVar) {
        if (jxtVar == null) {
            LogUtil.h("BasicMgrAw70Storage", "saveTodayTotalData() todayTotalMotion is null");
            return;
        }
        a(jxtVar);
        List<DataTotalMotion> c2 = jxtVar.c();
        ArrayList arrayList = new ArrayList(16);
        FitnessTotalData fitnessTotalData = new FitnessTotalData();
        fitnessTotalData.setCalorie(jxtVar.a() * 1000);
        for (DataTotalMotion dataTotalMotion : c2) {
            b(dataTotalMotion);
            a(dataTotalMotion, arrayList, fitnessTotalData, jxcVar);
        }
        d(fitnessTotalData, arrayList);
    }

    private void a(DataTotalMotion dataTotalMotion, List<FitnessTotalData> list, FitnessTotalData fitnessTotalData, jxc jxcVar) {
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
                    FitnessTotalData b = b(jxcVar, dataTotalMotion);
                    fitnessTotalData.addSteps(b.getSteps());
                    fitnessTotalData.setHeight(b.getHeight());
                    fitnessTotalData.addDistance(b.getDistance());
                    list.add(new FitnessTotalData(dataTotalMotion));
                    break;
                case 4:
                    fitnessTotalData.addDistance(dataTotalMotion.getDistance());
                    list.add(new FitnessTotalData(dataTotalMotion));
                    break;
                case 5:
                    LogUtil.a("05", 1, "BasicMgrAw70Storage", "stand type");
                    break;
                case 6:
                    LogUtil.a("05", 1, "BasicMgrAw70Storage", "shallow sleep type");
                    break;
                case 7:
                    LogUtil.a("05", 1, "BasicMgrAw70Storage", "deep sleep type");
                    break;
                default:
                    LogUtil.c("05", 1, "BasicMgrAw70Storage", "unknown type");
                    break;
            }
        }
        fitnessTotalData.addSteps(dataTotalMotion.getStep());
        fitnessTotalData.addDistance(dataTotalMotion.getDistance());
        list.add(d(dataTotalMotion));
    }

    private FitnessTotalData b(jxc jxcVar, DataTotalMotion dataTotalMotion) {
        FitnessTotalData fitnessTotalData = new FitnessTotalData();
        fitnessTotalData.setSteps(dataTotalMotion.getStep());
        if (jxcVar != null) {
            fitnessTotalData.setHeight(dataTotalMotion.getHeight());
            fitnessTotalData.setDistance(dataTotalMotion.getDistance());
        }
        return fitnessTotalData;
    }

    private void d(FitnessTotalData fitnessTotalData, List<FitnessTotalData> list) {
        list.add(fitnessTotalData);
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveTodayTotalData steps:", Integer.valueOf(fitnessTotalData.getSteps()), " calorie:", Integer.valueOf(fitnessTotalData.getCalorie()), " distance:", Integer.valueOf(fitnessTotalData.getDistance()), " size:", Integer.valueOf(list.size()));
        c(fitnessTotalData);
        b(fitnessTotalData);
    }

    private void b(DataTotalMotion dataTotalMotion) {
        LogUtil.c("BasicMgrAw70Storage", "type:", Integer.valueOf(dataTotalMotion.getMotion_type()), " step:", Integer.valueOf(dataTotalMotion.getStep()), " calorie:", Integer.valueOf(dataTotalMotion.getCalorie()), " distance:", Integer.valueOf(dataTotalMotion.getDistance()));
    }

    private void a(jxt jxtVar) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveTodayTotalData get data success total calorie is:", Integer.valueOf(jxtVar.a()));
    }

    private FitnessTotalData d(DataTotalMotion dataTotalMotion) {
        FitnessTotalData fitnessTotalData = new FitnessTotalData(dataTotalMotion);
        fitnessTotalData.setSportType(2);
        return fitnessTotalData;
    }

    private void b(FitnessTotalData fitnessTotalData) {
        String str;
        DeviceInfo b = jxi.b("BasicMgrAw70Storage");
        if (b != null) {
            str = "_" + b.getDeviceIdentify();
        } else {
            str = "";
        }
        StorageParams storageParams = new StorageParams(1);
        if (a(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps()) == 200004) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG" + str);
            a(str, storageParams, "KEY_TOTAL_STEPS_FROM_DEVICE_FLAG", fitnessTotalData.getSteps());
        }
        String str2 = (fitnessTotalData.getCalorie() / 1000) + "|" + (System.currentTimeMillis() / 1000);
        if (SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_CAL_FROM_DEVICE_FLAG" + str, str2, storageParams) == 200004) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_CAL_FROM_DEVICE_FLAG" + str);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_CAL_FROM_DEVICE_FLAG" + str, str2, storageParams);
        }
        if (a(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance()) == 200004) {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG" + str);
            a(str, storageParams, "KEY_TOTAL_DISTANCE_FROM_DEVICE_FLAG", fitnessTotalData.getDistance());
        }
    }

    private int a(String str, StorageParams storageParams, String str2, int i) {
        return SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), str2 + str, i + "|" + (System.currentTimeMillis() / 1000), storageParams);
    }

    public static long d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public void c(FitnessTotalData fitnessTotalData) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveTodayTotalToHiHealth fitnessTotalData :", fitnessTotalData);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        ArrayList arrayList = new ArrayList(16);
        arrayList.addAll(e(fitnessTotalData));
        if (arrayList.isEmpty()) {
            LogUtil.h("BasicMgrAw70Storage", "saveTodayTotalToHiHealth() dataList is empty");
        } else {
            hiDataInsertOption.setDatas(arrayList);
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jxe.5
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveTodayTotalToHiHealth onResult type:", Integer.valueOf(i), " object:", obj);
                    if (i == 0) {
                        iyv.c();
                    }
                }
            });
        }
    }

    private ArrayList<HiHealthData> e(FitnessTotalData fitnessTotalData) {
        ArrayList<HiHealthData> arrayList = new ArrayList<>(16);
        if (fitnessTotalData == null) {
            LogUtil.h("BasicMgrAw70Storage", "addData() fitnessTotalData is null");
            return arrayList;
        }
        long d2 = d(System.currentTimeMillis());
        if (fitnessTotalData.getSteps() > 0) {
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setType(40002);
            hiHealthData.setDeviceUuid(jxi.d());
            hiHealthData.setTimeInterval(d2, System.currentTimeMillis());
            hiHealthData.setValue(fitnessTotalData.getSteps());
            arrayList.add(hiHealthData);
            HiHealthData hiHealthData2 = new HiHealthData();
            hiHealthData2.setType(901);
            hiHealthData2.setDeviceUuid(jxi.d());
            hiHealthData2.setTimeInterval(d2, System.currentTimeMillis());
            hiHealthData2.setValue(fitnessTotalData.getSteps());
            arrayList.add(hiHealthData2);
        }
        if (fitnessTotalData.getCalorie() > 0) {
            HiHealthData hiHealthData3 = new HiHealthData();
            hiHealthData3.setType(40003);
            hiHealthData3.setDeviceUuid(jxi.d());
            hiHealthData3.setTimeInterval(d2, System.currentTimeMillis());
            hiHealthData3.setValue(fitnessTotalData.getCalorie());
            arrayList.add(hiHealthData3);
        }
        if (fitnessTotalData.getDistance() > 0) {
            HiHealthData hiHealthData4 = new HiHealthData();
            hiHealthData4.setType(40004);
            hiHealthData4.setDeviceUuid(jxi.d());
            hiHealthData4.setTimeInterval(d2, System.currentTimeMillis());
            hiHealthData4.setValue(fitnessTotalData.getDistance());
            arrayList.add(hiHealthData4);
        }
        if (fitnessTotalData.getHeight() > 0) {
            HiHealthData hiHealthData5 = new HiHealthData();
            hiHealthData5.setType(SmartMsgConstant.MSG_TYPE_RIDE_USER);
            hiHealthData5.setDeviceUuid(jxi.d());
            hiHealthData5.setTimeInterval(d2, System.currentTimeMillis());
            hiHealthData5.setValue(fitnessTotalData.getHeight());
            arrayList.add(hiHealthData5);
        }
        return arrayList;
    }

    public void b(List<jxw> list, List<jxx> list2) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveFitnessData in SEPARATED type");
        jxq c2 = new jxa().c();
        this.j = c2;
        this.b = c2.e();
        a();
        c(list);
        d(list2);
        d();
    }

    public void e(List<jxv> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("BasicMgrAw70Storage", "logSampleList() samplePoints is null");
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append("Total:" + list.size());
        int i = 0;
        for (jxv jxvVar : list) {
            sb.append("[index:");
            sb.append(i);
            sb.append("]");
            sb.append(jxvVar);
            i++;
        }
        LogUtil.a("05", 1, "BasicMgrAw70Storage", sb.toString());
    }

    public void e(jxw jxwVar) {
        if (jxwVar == null) {
            LogUtil.h("BasicMgrAw70Storage", "saveSampleFrame() sampleFrame is null");
            return;
        }
        List<jxv> b = jxwVar.b();
        e(b);
        for (jxv jxvVar : b) {
            if (this.b >= jxvVar.b()) {
                if (jxvVar.c() == 1) {
                    d(jxvVar);
                }
            } else {
                e(jxvVar);
            }
        }
        if (b.size() > 0) {
            long b2 = b.get(b.size() - 1).b();
            if (this.j.e() < b2) {
                this.j.a(b2);
            }
        }
    }

    public void c(List<jxw> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("BasicMgrAw70Storage", "saveSampleFrameList() sampleFrameList is null");
            return;
        }
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveSampleFrameList enter");
        Collections.sort(list, new Comparator<jxw>() { // from class: jxe.3
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(jxw jxwVar, jxw jxwVar2) {
                return (int) (jxwVar.e() - jxwVar2.e());
            }
        });
        Iterator<jxw> it = list.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    public void b(jxx jxxVar) {
        if (jxxVar == null) {
            LogUtil.h("BasicMgrAw70Storage", "saveStatusFrame() statusFrame is null");
            return;
        }
        List<jxy> a2 = jxxVar.a();
        for (jxy jxyVar : a2) {
            if (jxyVar.d() <= this.e) {
                if (jxyVar.c() == 1) {
                    d(jxyVar);
                } else if ((jxyVar.e() == 7 || jxyVar.e() == 6) && jxyVar.d() > this.e - 1800) {
                    d(jxyVar);
                } else {
                    LogUtil.c("BasicMgrAw70Storage", "no need special handle!");
                }
            } else {
                a(jxyVar);
            }
        }
        if (a2.size() > 0) {
            long d2 = a2.get(a2.size() - 1).d();
            if (d2 > this.i) {
                this.i = d2;
            }
        }
    }

    public void d(List<jxx> list) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveStatusFrameList enter");
        long d2 = jww.d();
        this.e = d2;
        this.i = d2;
        if (list == null || list.isEmpty()) {
            LogUtil.h("BasicMgrAw70Storage", "saveStatusFrameList() or statusFrameList is null");
            return;
        }
        Iterator<jxx> it = list.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    void a() {
        this.c = new ArrayList(16);
        this.f = new ArrayList(16);
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
                LogUtil.c("BasicMgrAw70Storage", "getHiHealthDataType unknown type :", Integer.valueOf(i));
                return 0;
        }
    }

    private void d(jxv jxvVar) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "updateSamplePoint sample:", jxvVar);
        e(jxvVar);
    }

    private void e(jxv jxvVar) {
        if (c(d(jxvVar.a()), jxvVar.e())) {
            HiHealthData hiHealthData = new HiHealthData(d(jxvVar.a()));
            hiHealthData.setTimeInterval(jxvVar.b() * 1000, (jxvVar.b() + jxvVar.d()) * 1000);
            hiHealthData.setValue(jxvVar.e());
            hiHealthData.setDeviceUuid(jxi.d());
            this.c.add(hiHealthData);
            return;
        }
        LogUtil.c("BasicMgrAw70Storage", "insertSamplePoint invalid sample:", jxvVar);
    }

    private int a(int i) {
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
        LogUtil.c("BasicMgrAw70Storage", "getHiHealthSessionType unknown type :", Integer.valueOf(i));
        return 0;
    }

    private void d(jxy jxyVar) {
        LogUtil.c("BasicMgrAw70Storage", "updateStatusToHiHealth:", jxyVar);
        a(jxyVar);
    }

    private void a(jxy jxyVar) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "statusPoint :", jxyVar);
        for (long d2 = jxyVar.d(); d2 < jxyVar.b(); d2 += 60) {
            if (a(jxyVar.e()) != 0) {
                HiHealthData hiHealthData = new HiHealthData(a(jxyVar.e()));
                hiHealthData.setTimeInterval(d2 * 1000, (d2 + 60) * 1000);
                hiHealthData.setDeviceUuid(jxi.d());
                LogUtil.c("BasicMgrAw70Storage", "saveStatusToHiHealth hiHealthData:", hiHealthData);
                this.f.add(hiHealthData);
            }
        }
    }

    public void e() {
        new jxa().a(this.j);
        jww.d(this.i);
    }

    public void e(int i) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "processSaveDataComplete enter errCode:", Integer.valueOf(i));
        if (i == 0) {
            jrq.b("BasicMgrAw70Storage", new Runnable() { // from class: jxe.4
                @Override // java.lang.Runnable
                public void run() {
                    jxe.this.e();
                }
            });
        }
        jxc.b().e(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "startSyncIntensiveData enter errorCode:", Integer.valueOf(i));
        jxc.b().a();
    }

    public void b(List<jxr> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("BasicMgrAw70Storage", "saveIntensiveInfo() desFrames is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("BasicMgrAw70Storage", "enter saveIntensiveInfo");
        a(arrayList, list);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        LogUtil.c("BasicMgrAw70Storage", "saveIntensiveInfo dataList size:", Integer.valueOf(arrayList.size()), ",value", arrayList.toString());
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jxe.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveIntensiveInfo onResult type:", Integer.valueOf(i), " object:", obj);
                if (i == 0) {
                    jxe.this.d.sendEmptyMessage(101);
                } else {
                    jxe.this.d.sendEmptyMessage(102);
                    LogUtil.a("BasicMgrAw70Storage", "saveIntensiveInfo not correct");
                }
            }
        });
    }

    private void a(List<HiHealthData> list, List<jxr> list2) {
        String d2 = jxi.d();
        Iterator<jxr> it = list2.iterator();
        while (true) {
            char c2 = 1;
            if (!it.hasNext()) {
                break;
            }
            jxr next = it.next();
            List<jxr.e> d3 = next.d();
            int i = 0;
            int i2 = 0;
            while (i2 < d3.size()) {
                jxr.e eVar = d3.get(i2);
                Object[] objArr = new Object[2];
                objArr[i] = "DesFrame :";
                objArr[c2] = next.toString();
                LogUtil.a("BasicMgrAw70Storage", objArr);
                int d4 = eVar.d();
                int c3 = eVar.c();
                int i3 = d4 - (d4 % 60);
                int i4 = ((c3 - (c3 % 60)) - i3) / 60;
                int i5 = i;
                while (i5 < i4) {
                    HiHealthData hiHealthData = new HiHealthData();
                    long j = ((i5 * 60) + i3) * 1000;
                    long j2 = (r13 + 60) * 1000;
                    hiHealthData.setTimeInterval(j, j2);
                    hiHealthData.setValue(c(eVar.a()));
                    hiHealthData.setType(7);
                    hiHealthData.setDeviceUuid(d2);
                    list.add(hiHealthData);
                    LogUtil.a("BasicMgrAw70Storage", "Intensive data: startTime :", Long.valueOf(j), " endTime :", Long.valueOf(j2), " value :", Integer.valueOf(c(eVar.a())), " type :", 7);
                    i5++;
                    d3 = d3;
                    i2 = i2;
                }
                i2++;
                c2 = 1;
                i = 0;
            }
        }
        if (list2.size() > 0) {
            jxr jxrVar = list2.get(list2.size() - 1);
            int c4 = jxrVar.d().get(jxrVar.d().size() - 1).c();
            if (c4 > this.f14166a) {
                this.f14166a = c4;
            }
        }
    }

    private int c(int i) {
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
        LogUtil.c("BasicMgrAw70Storage", "changType unknown type :", Integer.valueOf(i));
        return 0;
    }

    private void d() {
        LogUtil.a("05", 1, "BasicMgrAw70Storage", "saveDataToHiHealth enter mHiHealthDataList.size:", Integer.valueOf(this.c.size()), " mStatusList.size:", Integer.valueOf(this.f.size()));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        this.c.addAll(this.f);
        LogUtil.a("BasicMgrAw70Storage", "saveDataToHiHealth after add mHiHealthDataList.size:", Integer.valueOf(this.c.size()), ", mStatusList is ", Arrays.asList(this.f));
        hiDataInsertOption.setDatas(this.c);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: jxe.1
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("05", 1, "BasicMgrAw70Storage", "insertSamplePointHiHealthData onResult type:", Integer.valueOf(i), " object:", obj);
                if (i == 0) {
                    jxe.this.d.sendEmptyMessage(0);
                } else {
                    jxe.this.d.sendEmptyMessage(1);
                    LogUtil.a("BasicMgrAw70Storage", "insertSamplePointHiHealthData not correct");
                }
            }
        });
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final jxe f14170a = new jxe();
    }
}
