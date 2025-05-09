package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.HiRelationInformation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.InsertExecutor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ini {
    private static ini c;
    private ima b = ima.a();
    private Context f;
    private InsertExecutor h;
    private int j;

    /* renamed from: a, reason: collision with root package name */
    private static List<Integer> f13467a = new ArrayList(10);
    private static Map<Integer, int[]> d = new HashMap();
    private static final List e = new ArrayList(0);

    static {
        f13467a.add(10001);
        f13467a.add(10002);
        f13467a.add(10006);
        f13467a.add(22101);
        f13467a.add(22102);
        f13467a.add(22103);
        f13467a.add(22104);
        f13467a.add(22105);
        f13467a.add(10010);
        f13467a.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE.value()));
        f13467a.add(Integer.valueOf(DicDataTypeUtil.DataType.DRINK_WATER_SET.value()));
        f13467a.add(Integer.valueOf(DicDataTypeUtil.DataType.BODY_TEMPERATURE.value()));
        f13467a.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_LINKAGES.value()));
        f13467a.add(Integer.valueOf(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()));
        f13467a.add(61002);
        f13467a.add(61001);
        int[] iArr = {9, 10007};
        d.put(22100, iArr);
        d.put(22101, iArr);
        d.put(22102, iArr);
        d.put(22103, iArr);
        d.put(22104, iArr);
        d.put(22105, iArr);
        d.put(22106, iArr);
        d.put(22107, iArr);
        int[] iArr2 = {5000};
        d.put(61002, iArr2);
        d.put(31001, new int[]{15});
        d.put(61001, iArr2);
    }

    private ini(Context context) {
        this.f = context.getApplicationContext();
        c = this;
    }

    public static void b(Context context, InsertExecutor insertExecutor) {
        synchronized (ini.class) {
            if (c == null) {
                ini iniVar = new ini(context);
                c = iniVar;
                iniVar.h = insertExecutor;
                LogUtil.a("HiHealthKitInsertHelper", "initialized");
                return;
            }
            LogUtil.a("HiHealthKitInsertHelper", "already initialized");
        }
    }

    public static ini c() {
        ini iniVar = c;
        if (iniVar == null || iniVar.h == null) {
            LogUtil.b("HiHealthKitInsertHelper", "HiHealthKitInsertHelper not initialized");
            return null;
        }
        iniVar.j = iniVar.b.j();
        return c;
    }

    public void a(iqy iqyVar, List<HiHealthKitData> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        irc ircVar = new irc();
        if (list.get(0).getType() == 31001) {
            d(list, iDataOperateListener, ircVar, iqyVar);
        } else {
            c(iqyVar, list, iDataOperateListener, ircVar);
        }
    }

    private void c(final iqy iqyVar, final List<HiHealthKitData> list, final IDataOperateListener iDataOperateListener, final irc ircVar) throws RemoteException {
        LogUtil.a("HiHealthKitInsertHelper", iqyVar.b(), " appId:", Integer.valueOf(this.b.d(iqyVar.a())), " userId:", Integer.valueOf(this.j));
        this.h.execute(new Runnable() { // from class: ini.5
            @Override // java.lang.Runnable
            public void run() {
                HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
                for (HiHealthKitData hiHealthKitData : list) {
                    hiDataDeleteOption.addTimeInterval(new HiTimeInterval(hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime()));
                }
                try {
                    int type = ((HiHealthKitData) list.get(0)).getType();
                    hiDataDeleteOption.setTypes(ira.c(type));
                    final boolean a2 = ini.this.a(type);
                    final boolean a3 = ini.this.a((List<HiHealthKitData>) list);
                    ify.e().b(hiDataDeleteOption, new IDataOperateListener() { // from class: ini.5.1
                        @Override // android.os.IInterface
                        public IBinder asBinder() {
                            return null;
                        }

                        @Override // com.huawei.hihealth.IDataOperateListener
                        public void onResult(int i, List list2) throws RemoteException {
                            ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", "HiHealthApiManager deleteHiHealthData errorCode = ", Integer.valueOf(i));
                            iDataOperateListener.onResult(i, Collections.EMPTY_LIST);
                            ircVar.c(ini.this.f, iqyVar.b(i));
                            if (i == 0) {
                                if (a2 || a3) {
                                    ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", "delete success and sync cloud");
                                    ini.this.e(Integer.MIN_VALUE);
                                }
                            }
                        }
                    }, true, false);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c("HiH_HiHealthKitInsertHelper", "deleteHealthData() RemoteException = ", e2.getMessage());
                    try {
                        iDataOperateListener.onResult(4, ini.e);
                    } catch (RemoteException e3) {
                        ReleaseLogUtil.c("HiH_HiHealthKitInsertHelper", "onResult RemoteException = ", e3.getMessage());
                    }
                    ircVar.c(ini.this.f, iqyVar.b(4));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", "syncType = ", Integer.valueOf(i));
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        if (i == Integer.MIN_VALUE) {
            hiSyncOption.setSyncDataType(20000);
        } else {
            hiSyncOption.setSyncDataType(i);
        }
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(this.f).synCloud(hiSyncOption, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<Integer> list) {
        ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", "syncType = ", list.toString());
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataTypes(list);
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(this.f).synCloud(hiSyncOption, null);
    }

    public void e(final List<HiHealthKitData> list, final IDataOperateListener iDataOperateListener, String str) throws RemoteException {
        d(list.get(0), str);
        LogUtil.a("HiHealthKitInsertHelper", "save type ", Integer.valueOf(list.get(0).getType()));
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (HiHealthKitData hiHealthKitData : list) {
            HiHealthData hiHealthData = new HiHealthData(hiHealthKitData.getType());
            String string = hiHealthKitData.getString("device_uniquecode");
            if (string == null) {
                string = "-1";
            }
            hiHealthData.setDeviceUuid(string);
            hiHealthData.setStartTime(hiHealthKitData.getStartTime());
            hiHealthData.setEndTime(hiHealthKitData.getEndTime());
            if (hiHealthKitData.getUpdateTime() != 0) {
                hiHealthData.setModifiedTime(hiHealthKitData.getUpdateTime());
            }
            hiHealthData.setMetaData(hiHealthKitData.getString("metadata"));
            if (ipa.a(hiHealthKitData.getType())) {
                arrayList2.add(e(hiHealthKitData));
            } else {
                f(hiHealthKitData, hiHealthData);
                arrayList.add(hiHealthData);
            }
        }
        if (arrayList2.isEmpty()) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption(arrayList);
            hiDataInsertOption.setPackageName(str);
            final boolean e2 = e(arrayList);
            final boolean a2 = a(list);
            ify.e().d(hiDataInsertOption, new IDataOperateListener.Stub() { // from class: ini.3
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list2) throws RemoteException {
                    if (i == 0 && (e2 || a2)) {
                        LogUtil.a("HiHealthKitInsertHelper", "some need sync cloud types");
                        ini.this.c(new ArrayList(ini.this.a((Collection<Integer>) ini.this.d((List<HiHealthKitData>) list))));
                    }
                    ReleaseLogUtil.c("HiH_HiHealthKitInsertHelper", "HiHealthApiManager insertHiHealthData errorCode =", Integer.valueOf(i));
                    iDataOperateListener.onResult(i, list2);
                }
            }, list.get(0).getBoolean("insertRealTime"), false);
            return;
        }
        a(arrayList2, iDataOperateListener);
    }

    private boolean e(List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            if (f13467a.contains(Integer.valueOf(it.next().getType()))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i) {
        return f13467a.contains(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(List<HiHealthKitData> list) {
        Iterator<HiHealthKitData> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getBoolean("sync_cloud_now")) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Collection<Integer> a(Collection<Integer> collection) {
        HashSet hashSet = new HashSet();
        Iterator<Integer> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Integer next = it.next();
            if (HiHealthDictManager.d(this.f).d(next.intValue()) != null) {
                hashSet.add(next);
            } else if (d.containsKey(next)) {
                for (int i : d.get(next)) {
                    hashSet.add(Integer.valueOf(i));
                }
            } else {
                hashSet.clear();
                hashSet.add(20000);
                break;
            }
        }
        LogUtil.a("HiHealthKitInsertHelper", "dataType2SyncDataType: ", hashSet);
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Collection<Integer> d(List<HiHealthKitData> list) {
        HashSet hashSet = new HashSet();
        Iterator<HiHealthKitData> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(Integer.valueOf(it.next().getType()));
        }
        LogUtil.a("HiHealthKitInsertHelper", "getTypesByDataList: ", hashSet);
        return hashSet;
    }

    private HiUserPreference e(HiHealthKitData hiHealthKitData) {
        return new HiUserPreference(ipa.c(hiHealthKitData.getType()), (String) hiHealthKitData.getMap().get(Integer.valueOf(hiHealthKitData.getType())));
    }

    /* renamed from: ini$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            c = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[HiHealthDataType.Category.STAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[HiHealthDataType.Category.REALTIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[HiHealthDataType.Category.SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[HiHealthDataType.Category.SEQUENCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[HiHealthDataType.Category.BUSINESS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void f(HiHealthKitData hiHealthKitData, HiHealthData hiHealthData) {
        switch (AnonymousClass4.c[HiHealthDataType.e(hiHealthKitData.getType()).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c(hiHealthKitData, hiHealthData);
                break;
            case 4:
                i(hiHealthKitData, hiHealthData);
                break;
            case 5:
                e(hiHealthKitData, hiHealthData);
                break;
            case 6:
                b(hiHealthKitData, hiHealthData);
                break;
        }
    }

    private void c(HiHealthKitData hiHealthKitData, HiHealthData hiHealthData) {
        int a2 = irj.a(hiHealthKitData.getType());
        if (a2 == 0) {
            hiHealthData.setValue(hiHealthKitData.getIntValue());
        } else if (a2 == 3) {
            hiHealthData.setValue(hiHealthKitData.getDoubleValue());
        } else {
            ReleaseLogUtil.d("HiH_HiHealthKitInsertHelper", "precision is not supported");
        }
    }

    private void i(HiHealthKitData hiHealthKitData, HiHealthData hiHealthData) {
        if (hiHealthKitData.getType() == DicDataTypeUtil.DataType.OSA_SET.value()) {
            a(hiHealthKitData, hiHealthData);
            return;
        }
        if (HiHealthDictManager.d(this.f).l(hiHealthData.getType()) && hiHealthKitData.getMap() == null) {
            ira.bBZ_(hiHealthKitData.getContentValue(), String.valueOf(2031), String.valueOf(2001));
            ira.bBZ_(hiHealthKitData.getContentValue(), String.valueOf(2028), String.valueOf(2090));
            d(hiHealthKitData, hiHealthData);
            return;
        }
        Map map = hiHealthKitData.getMap();
        if (map.containsKey(2018)) {
            int value = DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value();
            map.put(Integer.valueOf(value), (Double) map.get(2018));
            map.remove(2018);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (((Integer) entry.getKey()).intValue() == 2031) {
                hiHealthData.putDouble(BleConstants.BODY_FAT_RATE, ((Double) entry.getValue()).doubleValue());
            } else if (((Integer) entry.getKey()).intValue() == 2028) {
                hiHealthData.putDouble("protein", ((Double) entry.getValue()).doubleValue());
            } else {
                hiHealthData.putDouble(HiHealthDataKey.c(((Integer) entry.getKey()).intValue()), ((Double) entry.getValue()).doubleValue());
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(HiHealthKitData hiHealthKitData, HiHealthData hiHealthData) {
        char c2;
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        Iterator<Integer> it = HiHealthDictManager.d(this.f).g(hiHealthData.getType()).iterator();
        while (it.hasNext()) {
            HiHealthDictField b = HiHealthDictManager.d(this.f).b(it.next().intValue());
            if (b == null || b.e() == null) {
                ReleaseLogUtil.c("HiH_HiHealthKitInsertHelper", "invalid dict field");
                return;
            }
            String e2 = b.e();
            int c3 = b.c();
            if (hiHealthKitData.getContentValue().containsKey(String.valueOf(c3))) {
                e2.hashCode();
                switch (e2.hashCode()) {
                    case -672261858:
                        if (e2.equals("Integer")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 2374300:
                        if (e2.equals("Long")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 67973692:
                        if (e2.equals("Float")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 2052876273:
                        if (e2.equals("Double")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                if (c2 == 0) {
                    hashMap.put(Integer.valueOf(c3), Double.valueOf(hiHealthKitData.getInt(String.valueOf(c3))));
                } else if (c2 == 1) {
                    hashMap.put(Integer.valueOf(c3), Double.valueOf(hiHealthKitData.getLong(String.valueOf(c3))));
                } else if (c2 == 2) {
                    hashMap.put(Integer.valueOf(c3), Double.valueOf(hiHealthKitData.getFloat(String.valueOf(c3))));
                } else if (c2 == 3) {
                    hashMap.put(Integer.valueOf(c3), Double.valueOf(hiHealthKitData.getDouble(String.valueOf(c3))));
                } else {
                    hashMap2.put(Integer.valueOf(c3), hiHealthKitData.getString(String.valueOf(c3)));
                }
            }
        }
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
    }

    private void a(HiHealthKitData hiHealthKitData, HiHealthData hiHealthData) {
        Map map = hiHealthKitData.getMap();
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_LEVEL.value()), Double.valueOf(c(map, Integer.valueOf(DicDataTypeUtil.DataType.OSA_LEVEL.value()))));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()), Double.valueOf(c(map, Integer.valueOf(DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()))));
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        Object obj = map.get(Integer.valueOf(DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value()));
        if (obj != null) {
            HashMap hashMap2 = new HashMap(16);
            hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value()), obj.toString());
            hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
        }
        new HiDataInsertOption().addData(hiHealthData);
    }

    private void e(HiHealthKitData hiHealthKitData, HiHealthData hiHealthData) {
        if (HiHealthDictManager.d(this.f).l(hiHealthKitData.getType())) {
            hiHealthData.setSequenceData(hiHealthKitData.getString("detail_data"));
            hiHealthData.setSimpleData(hiHealthKitData.getString("simple_data"));
            return;
        }
        Map map = hiHealthKitData.getMap();
        if (hiHealthKitData.getType() == 31001) {
            if (map == null || map.isEmpty()) {
                hiHealthData.setSequenceData(hiHealthKitData.getString("detail_data"));
                hiHealthData.setSimpleData(hiHealthKitData.getString("simple_data"));
                hiHealthData.setMetaData(hiHealthKitData.getString("meta_data"));
                return;
            } else {
                hiHealthData.setSequenceData(ima.c(map, "detail_data"));
                hiHealthData.setSimpleData(ima.c(map, "simple_data"));
                hiHealthData.setMetaData(ima.c(map, "meta_data"));
                return;
            }
        }
        if (hiHealthKitData.getType() == 30029) {
            c(map, hiHealthData);
        } else {
            ReleaseLogUtil.d("HiH_HiHealthKitInsertHelper", "wrong sequence type");
        }
    }

    private void b(HiHealthKitData hiHealthKitData, HiHealthData hiHealthData) {
        Map map = hiHealthKitData.getMap();
        double c2 = c(map, (Object) 2201);
        double c3 = c(map, (Object) 2202);
        double c4 = c(map, (Object) 2203);
        String valueOf = String.valueOf(map.get(2204));
        LogUtil.a("HiHealthKitInsertHelper", " save value: ", Double.valueOf(c2), " dataId: ", Double.valueOf(c4), " source: ", valueOf);
        ArrayList arrayList = new ArrayList(10);
        HiRelationInformation hiRelationInformation = new HiRelationInformation();
        hiRelationInformation.e((int) c4);
        hiRelationInformation.a((int) c3);
        arrayList.add(hiRelationInformation);
        String json = new Gson().toJson(arrayList, new TypeToken<List<HiRelationInformation>>() { // from class: ini.2
        }.getType());
        hiHealthData.setValue(c2);
        hiHealthData.setDataSource(valueOf);
        hiHealthData.setRelationInformations(json);
        hiHealthData.setRelationFlag(true);
    }

    private void a(List<HiUserPreference> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        if (list.isEmpty()) {
            ReleaseLogUtil.d("HiH_HiHealthKitInsertHelper", "userPreferences is empty");
            iDataOperateListener.onResult(2, Collections.EMPTY_LIST);
            return;
        }
        if (this.j <= 0) {
            ReleaseLogUtil.d("HiH_HiHealthKitInsertHelper", "userID <= 0");
            iDataOperateListener.onResult(4, Collections.EMPTY_LIST);
            return;
        }
        for (HiUserPreference hiUserPreference : list) {
            hiUserPreference.setUserId(this.j);
            if (!ijy.c(this.f).b(hiUserPreference)) {
                iDataOperateListener.onResult(4, Collections.EMPTY_LIST);
                return;
            }
        }
        iDataOperateListener.onResult(0, Arrays.asList(true, true));
        ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", "save config success");
    }

    private void d(HiHealthKitData hiHealthKitData, String str) {
        if (str.startsWith("com.huawei.health.device")) {
            ReleaseLogUtil.d("HiH_HiHealthKitInsertHelper", "registerNewDevice third device for h5");
            return;
        }
        String string = hiHealthKitData.getString("device_uniquecode");
        int i = hiHealthKitData.getInt("deviceType");
        LogUtil.c("HiHealthKitInsertHelper", "saveSample registDeviceInfo uniqueCode:", string);
        if (TextUtils.isEmpty(string) || i == 0) {
            ReleaseLogUtil.d("HiH_HiHealthKitInsertHelper", "uniqueCode or deviceType is empty, doesn't need registerNewDevice");
            return;
        }
        if (i == 1 || "-1".equals(string)) {
            ReleaseLogUtil.d("HiH_HiHealthKitInsertHelper", "deviceType or uuid is manual_input, register is not allowed");
            return;
        }
        if (ijf.d(this.f).d(string) == null) {
            HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(i);
            hiDeviceInfo.setDeviceUniqueCode(string);
            hiDeviceInfo.setDeviceName(hiHealthKitData.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME));
            hiDeviceInfo.setModel(hiHealthKitData.getString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL));
            long b = ijf.d(this.f).b(hiDeviceInfo);
            Object[] objArr = new Object[2];
            objArr[0] = "saveSample registerDeviceInfo result:";
            objArr[1] = Boolean.valueOf(b > 0);
            ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", objArr);
            return;
        }
        ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", "device has been registered");
    }

    protected HiHealthData c(Map map, HiHealthData hiHealthData) {
        if (map != null && hiHealthData != null) {
            HiTrackMetaData hiTrackMetaData = new HiTrackMetaData();
            hiTrackMetaData.setSportType(ima.a(map, BleConstants.SPORT_TYPE));
            hiTrackMetaData.setTotalTime(ima.e(map, "totalTime"));
            hiTrackMetaData.setTotalCalories(ima.a(map, BleConstants.TOTAL_CALORIES));
            HashMap hashMap = new HashMap(16);
            hashMap.put("skipNum", ima.c(map, "skipNum"));
            hashMap.put("skipSpeed", ima.c(map, "skipSpeed"));
            hashMap.put("stumblingRope", map.containsKey("stumblingRope") ? ima.c(map, "stumblingRope") : "-1");
            hashMap.put("maxSkippingTimes", map.containsKey("maxSkippingTimes") ? ima.c(map, "maxSkippingTimes") : "-1");
            hiTrackMetaData.setExtendTrackDataMap(hashMap);
            hiTrackMetaData.setHasTrackPoint(false);
            hiTrackMetaData.setChiefSportDataType(7);
            hiTrackMetaData.setSportDataSource(5);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeSpecialFloatingPointValues();
            hiHealthData.setMetaData(gsonBuilder.create().toJson(hiTrackMetaData, HiTrackMetaData.class));
            hiHealthData.setSequenceData("0");
            hiHealthData.setType(30001);
        }
        return hiHealthData;
    }

    private double c(Map map, Object obj) throws NumberFormatException {
        Object obj2 = map.get(obj);
        if (obj2 instanceof Double) {
            return ((Double) obj2).doubleValue();
        }
        if (obj2 instanceof Integer) {
            return ((Integer) obj2).doubleValue();
        }
        if (obj2 instanceof String) {
            return Double.valueOf((String) obj2).doubleValue();
        }
        return 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(IDataOperateListener iDataOperateListener, int i, List list) {
        try {
            iDataOperateListener.onResult(i, list);
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_HiHealthKitInsertHelper", "notifyResult RemoteException: ", e2.getMessage());
        }
    }

    private void d(List<HiHealthKitData> list, final IDataOperateListener iDataOperateListener, final irc ircVar, final iqy iqyVar) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption(new int[]{31001});
        for (HiHealthKitData hiHealthKitData : list) {
            hiDataDeleteOption.addTimeInterval(new HiTimeInterval(hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime()));
        }
        HiHealthManager.d(this.f).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: ini.1
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("HiH_HiHealthKitInsertHelper", "deleteEcgData, errorCode: ", Integer.valueOf(i));
                int i2 = i == 0 ? 0 : 4;
                ini.this.d(iDataOperateListener, i2, Collections.EMPTY_LIST);
                ircVar.c(ini.this.f, iqyVar.b(i2));
                if (i == 0) {
                    ini.this.e(Integer.MIN_VALUE);
                    LogUtil.a("HiHealthKitInsertHelper", "Sync cloud after deleting ECG data");
                }
            }
        });
    }
}
