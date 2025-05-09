package defpackage;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.util.HiFileUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.HiZipUtil;
import com.huawei.hihealthservice.store.merge.HiDataSequenceMerge;
import com.huawei.hihealthservice.store.merge.HiDicHealthDataMerge;
import com.huawei.hihealthservice.store.merge.HiDicSequenceMerge;
import com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge;
import com.huawei.hihealthservice.store.stat.HiDicHealthDataStat;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiValidatorUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class isg {
    private static Context d;
    private HiHealthDictManager b;
    private HiDataSequenceMerge c;

    private isg() {
        this.c = new HiDataSequenceMerge(d);
        this.b = HiHealthDictManager.d(d);
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final isg f13575a = new isg();
    }

    public static isg d(Context context) {
        if (context != null) {
            d = context.getApplicationContext();
        }
        return e.f13575a;
    }

    public boolean c(HiHealthData hiHealthData, int i, List<Integer> list) {
        HiTrackMetaData hiTrackMetaData;
        String d2;
        if (hiHealthData == null || list == null) {
            return false;
        }
        ReleaseLogUtil.e("HiH_HiHlthSaveTrk", "saveSequenceData start clientId is ", Integer.valueOf(i), " sequenceData type is ", Integer.valueOf(hiHealthData.getType()));
        int type = hiHealthData.getType();
        if (type == 30001) {
            Boolean c2 = c(hiHealthData, i);
            if (c2 != null) {
                return c2.booleanValue();
            }
        } else if (type != 30003) {
            if (type == 31001) {
                LogUtil.a("HiH_HiHealthSaveData", "saveSequenceData DATA_SEQUENCE_ECG 1 clientId is ", Integer.valueOf(i));
                if (hiHealthData.getBoolean("is_sequence_zip")) {
                    if (hiHealthData.getBoolean("is_dividing")) {
                        ikt.e().a(hiHealthData);
                        return true;
                    }
                    LogUtil.c("HiH_HiHealthSaveData", "saveSequenceData DATA_SEQUENCE_ECG 1 end ! clientId is ", Integer.valueOf(i));
                    d2 = ikt.e().d(hiHealthData);
                    hiHealthData.setType(31001);
                } else {
                    try {
                        LogUtil.c("HiH_HiHealthSaveData", "saveSequenceData DATA_SEQUENCE_ECG 2 clientId is ", Integer.valueOf(i));
                        d2 = HiZipUtil.d(ikt.e().d(hiHealthData));
                    } catch (IOException e2) {
                        ReleaseLogUtil.c("HiH_HiHealthSaveData", "saveSequenceData DATA_SEQUENCE_ECG compress e = ", LogAnonymous.b((Throwable) e2));
                        return false;
                    }
                }
                hiHealthData.setSequenceData(d2);
            } else if (HiHealthDictManager.d(d).l(hiHealthData.getType())) {
                return e(hiHealthData, i, list);
            }
        } else if (!b(hiHealthData, i, 30001)) {
            return false;
        }
        if (hiHealthData.getType() == 30001 && hiHealthData.getSubType() == 0 && (hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class)) != null) {
            hiHealthData.setSubType(hiTrackMetaData.getSportType());
        }
        if (HiCommonUtil.b(hiHealthData.getSequenceData())) {
            ReleaseLogUtil.c("HiH_HiHealthSaveData", "saveSequenceData track data error");
            return false;
        }
        ReleaseLogUtil.e("HiH_HiHealthSaveData", "saveSequenceData sequence length = ", Integer.valueOf(hiHealthData.getSequenceData().length()));
        return this.c.merge(hiHealthData, i, list);
    }

    private boolean e(HiHealthData hiHealthData, int i, List<Integer> list) {
        if (hiHealthData.getSequenceData() != null) {
            Boolean c2 = c(hiHealthData, i);
            if (c2 != null) {
                return c2.booleanValue();
            }
        } else if (hiHealthData.getSequenceFileUrl() != null) {
            if (!b(hiHealthData, i, hiHealthData.getType())) {
                return false;
            }
        } else {
            ReleaseLogUtil.c("HiH_HiHlthSaveTrk", "saveSequenceData and getSequenceFileUrl all null");
            return false;
        }
        if (HiCommonUtil.b(hiHealthData.getSequenceData())) {
            ReleaseLogUtil.c("HiH_HiHealthSaveData", "saveSequenceData data error");
            return false;
        }
        return new HiDicSequenceMerge(d).merge(hiHealthData, i, list);
    }

    private Boolean c(HiHealthData hiHealthData, int i) {
        String d2;
        LogUtil.a("HiH_HiHlthSaveTrk", "saveSequenceData DATA_SEQUENCE 1 clientId is ", Integer.valueOf(i));
        if (hiHealthData.getBoolean("is_sequence_zip")) {
            if (hiHealthData.getBoolean("is_dividing")) {
                LogUtil.a("HiH_HiHlthSaveTrk", "saveSequenceData is dividing");
                ikt.e().a(hiHealthData);
                return true;
            }
            LogUtil.a("HiH_HiHlthSaveTrk", "saveSequenceData DATA_SEQUENCE 1 end ! clientId is ", Integer.valueOf(i));
            d2 = ikt.e().d(hiHealthData);
        } else {
            try {
                LogUtil.c("HiH_HiHlthSaveTrk", "saveSequenceData DATA_SEQUENCE 2 clientId is ", Integer.valueOf(i));
                d2 = HiZipUtil.d(ikt.e().d(hiHealthData));
            } catch (IOException e2) {
                ReleaseLogUtil.c("HiH_HiHlthSaveTrk", "saveSequenceData DATA_SEQUENCE compress e = ", LogAnonymous.b((Throwable) e2));
                return false;
            }
        }
        hiHealthData.setSequenceData(d2);
        return null;
    }

    private boolean b(HiHealthData hiHealthData, int i, int i2) {
        LogUtil.a("HiH_HiHlthSaveTrk", "saveSequenceData DATA_SEQUENCE_FILE clientId is ", Integer.valueOf(i));
        try {
            String d2 = HiZipUtil.d(HiFileUtil.d(d, hiHealthData.getSequenceFileUrl()));
            hiHealthData.setType(i2);
            hiHealthData.setSequenceData(d2);
            return true;
        } catch (IOException e2) {
            ReleaseLogUtil.c("HiH_HiHlthSaveTrk", "saveSequenceData DATA_SEQUENCE_FILE compress e = ", LogAnonymous.b((Throwable) e2));
            return false;
        }
    }

    public boolean d(int i, HiHealthData hiHealthData, int i2, List<Integer> list, HiHealthDataPointMerge hiHealthDataPointMerge) {
        int[] d2 = HiHealthDataType.d(i);
        HiHealthData hiHealthData2 = new HiHealthData();
        hiHealthData2.setStartTime(hiHealthData.getStartTime());
        hiHealthData2.setEndTime(hiHealthData.getEndTime());
        hiHealthData2.setMetaData(hiHealthData.getMetaData());
        hiHealthData2.setSyncStatus(hiHealthData.getSyncStatus());
        int length = d2.length;
        int i3 = 0;
        for (int i4 : d2) {
            LogUtil.c("HiH_HiHealthSaveData", "saveSetData() type = ", Integer.valueOf(i), ", pointType = ", Integer.valueOf(i4));
            hiHealthData2.setType(i4);
            String c2 = HiHealthDataKey.c(i4);
            double d3 = hiHealthData.getDouble(c2);
            if (HiValidatorUtil.b(i4, d3)) {
                hiHealthData2.setValue(d3);
                hiHealthData2.setPointUnit(hiHealthData.getInt(HiHealthDataKey.a(c2)));
                if (hiHealthDataPointMerge.merge(hiHealthData2, i2, list)) {
                    i3++;
                }
            } else {
                i3++;
            }
        }
        return i3 >= length;
    }

    public boolean b(HiHealthData hiHealthData, int i, List<Integer> list) {
        if (hiHealthData == null || i <= 0 || HiCommonUtil.d(list)) {
            ReleaseLogUtil.d("HiH_HiHealthSaveData", "saveDicSetData setData is null or clientId <= 0 or clients is null or empty");
            return false;
        }
        try {
            HashMap<Integer, Double> hashMap = (HashMap) new Gson().fromJson(hiHealthData.getFieldsValue(), new TypeToken<HashMap<Integer, Double>>() { // from class: isg.5
            }.getType());
            HashMap<Integer, String> hashMap2 = (HashMap) new Gson().fromJson(hiHealthData.getFieldsMetaData(), new TypeToken<HashMap<Integer, String>>() { // from class: isg.3
            }.getType());
            HashMap<Integer, Long> hashMap3 = (HashMap) new Gson().fromJson(hiHealthData.getFieldsModifyTime(), new TypeToken<HashMap<Integer, Long>>() { // from class: isg.4
            }.getType());
            HashSet hashSet = new HashSet(16);
            if (hashMap != null && !hashMap.isEmpty()) {
                hashSet.addAll(hashMap.keySet());
            }
            if (hashMap2 != null && !hashMap2.isEmpty()) {
                hashSet.addAll(hashMap2.keySet());
            }
            if (hashMap3 != null && !hashMap3.isEmpty()) {
                hashSet.addAll(hashMap3.keySet());
            }
            if (hashSet.isEmpty()) {
                hashMap = new HashMap<>(16);
                hashMap2 = new HashMap<>(16);
                b(hiHealthData, hashMap, hashMap2);
                if (!hashMap.isEmpty()) {
                    hashSet.addAll(hashMap.keySet());
                }
                if (!hashMap2.isEmpty()) {
                    hashSet.addAll(hashMap2.keySet());
                }
                if (hashSet.isEmpty()) {
                    ReleaseLogUtil.d("HiH_HiHealthSaveData", "values and metaDatas all empty!");
                    return false;
                }
            }
            HashMap<Integer, Double> hashMap4 = hashMap;
            HashMap<Integer, String> hashMap5 = hashMap2;
            Iterator it = hashSet.iterator();
            int i2 = 0;
            int i3 = 0;
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                if (!iwp.c(intValue)) {
                    if (HiHealthDictManager.d(d).b(intValue) == null) {
                        ReleaseLogUtil.d("HiH_HiHealthSaveData", "HiHealthDictField is null, type is ", Integer.valueOf(intValue));
                    } else {
                        HiHealthData e2 = e(hiHealthData, hashMap4, hashMap5, hashMap3, intValue);
                        i2++;
                        HiDicHealthDataMerge hiDicHealthDataMerge = new HiDicHealthDataMerge(d);
                        if (!hiDicHealthDataMerge.a(e2)) {
                            ReleaseLogUtil.d("HiH_HiHealthSaveData", "hiDicHealthDataMerge init failed!");
                            return false;
                        }
                        if (hiDicHealthDataMerge.a(e2, i, list)) {
                            LogUtil.c("HiH_HiHealthSaveData", "saveDicSetData typeId = ", Integer.valueOf(hiHealthData.getType()), ", healthType = ", Integer.valueOf(e2.getType()), ", value = ", Double.valueOf(e2.getValue()));
                            i3++;
                        }
                    }
                }
            }
            return i3 == i2;
        } catch (JsonSyntaxException e3) {
            ReleaseLogUtil.d("HiH_HiHealthSaveData", "FromJson JsonSyntaxException is ", LogAnonymous.b((Throwable) e3));
            return false;
        }
    }

    private void b(HiHealthData hiHealthData, HashMap<Integer, Double> hashMap, HashMap<Integer, String> hashMap2) {
        for (int i : HiHealthDataType.d(hiHealthData.getType())) {
            double d2 = hiHealthData.getDouble(HiHealthDataKey.c(i));
            if (HiValidatorUtil.b(i, d2)) {
                hashMap.put(Integer.valueOf(i), Double.valueOf(d2));
                hashMap2.put(Integer.valueOf(i), hiHealthData.getMetaData());
            }
        }
    }

    private HiHealthData e(HiHealthData hiHealthData, HashMap<Integer, Double> hashMap, HashMap<Integer, String> hashMap2, HashMap<Integer, Long> hashMap3, int i) {
        Long l;
        Double d2;
        HiHealthData hiHealthData2 = new HiHealthData();
        hiHealthData2.setStartTime(hiHealthData.getStartTime());
        hiHealthData2.setEndTime(hiHealthData.getEndTime());
        hiHealthData2.setSyncStatus(hiHealthData.getSyncStatus());
        hiHealthData2.setDeviceUuid(hiHealthData.getDeviceUuid());
        hiHealthData2.setModifiedTime(hiHealthData.getModifiedTime());
        hiHealthData2.setTimeZone(hiHealthData.getTimeZone());
        if (hashMap != null && !hashMap.isEmpty() && hashMap.containsKey(Integer.valueOf(i)) && (d2 = hashMap.get(Integer.valueOf(i))) != null) {
            hiHealthData2.setValue(d2.doubleValue());
        }
        if (hashMap2 != null && !hashMap2.isEmpty() && hashMap2.containsKey(Integer.valueOf(i))) {
            hiHealthData2.setMetaData(hashMap2.get(Integer.valueOf(i)));
        }
        if (hashMap3 != null && !hashMap3.isEmpty() && hashMap3.containsKey(Integer.valueOf(i)) && (l = hashMap3.get(Integer.valueOf(i))) != null) {
            hiHealthData2.setModifiedTime(l.longValue());
        }
        hiHealthData2.setType(i);
        return hiHealthData2;
    }

    public boolean a(HiHealthData hiHealthData, int i, int i2) {
        if (hiHealthData == null || i <= 0) {
            ReleaseLogUtil.d("HiH_HiHealthSaveData", "saveDicStatData  statData is null or statClient <= 0");
            return false;
        }
        ReleaseLogUtil.e("HiH_HiHealthSaveData", "saveDicStatData type =", Integer.valueOf(hiHealthData.getType()), ",time = ", Long.valueOf(hiHealthData.getStartTime()), ",statClient = ", Integer.valueOf(i), ",userId = ", Integer.valueOf(i2));
        igo igoVar = new igo();
        igoVar.d(hiHealthData.getStartTime());
        igoVar.j(i2);
        igoVar.g(hiHealthData.getSyncStatus());
        HiHealthDictField i3 = this.b.i(hiHealthData.getType());
        if (i3 == null) {
            ReleaseLogUtil.d("HiH_HiHealthSaveData", "saveDicStatData healthDictField is null");
            return false;
        }
        igoVar.c(i3.c());
        igoVar.h(0);
        igoVar.b(i);
        igoVar.a(hiHealthData.getModifiedTime());
        igoVar.d(hiHealthData.getType());
        igoVar.a(hiHealthData.getValue());
        if (this.b.a(hiHealthData.getType()) == null) {
            ReleaseLogUtil.d("HiH_HiHealthSaveData", "saveDicStatData  healthDictStat is null, type is ", Integer.valueOf(hiHealthData.getType()));
            return false;
        }
        return new HiDicHealthDataStat(d).b(igoVar);
    }

    public boolean d(HiHealthData hiHealthData, int i, int i2) {
        if (hiHealthData == null || i <= 0) {
            ReleaseLogUtil.d("HiH_HiHealthSaveData", "saveStatData() statClient <= 0");
            return false;
        }
        ReleaseLogUtil.e("HiH_HiHealthSaveData", "saveStatDtTp=", Integer.valueOf(hiHealthData.getType()), ",tm", Long.valueOf(hiHealthData.getStartTime()), ",statClient=", Integer.valueOf(i), ",uId=", Integer.valueOf(i2));
        igo igoVar = new igo();
        igoVar.d(hiHealthData.getStartTime());
        igoVar.j(i2);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(hiHealthData.getInt("hihealth_type"));
        igoVar.h(hiHealthData.getPointUnit());
        igoVar.b(i);
        igoVar.a(hiHealthData.getModifiedTime());
        if (hiHealthData.getType() == 10010) {
            return c(hiHealthData, igoVar);
        }
        igoVar.d(hiHealthData.getType());
        igoVar.a(hiHealthData.getValue());
        return ivu.a(d, igoVar.f()).a(igoVar);
    }

    private boolean c(HiHealthData hiHealthData, igo igoVar) {
        for (int i : HiHealthDataType.d(hiHealthData.getType())) {
            String c2 = HiHealthDataKey.c(i);
            double d2 = hiHealthData.getDouble(c2);
            if (!c2.equals("menstrual_physical") || d2 != 2.147483647E9d) {
                igoVar.d(i);
                igoVar.a(d2);
                if (!ivu.a(d, igoVar.f()).a(igoVar)) {
                    ReleaseLogUtil.d("HiH_HiHealthSaveData", "saveStatSet false");
                    return false;
                }
            }
        }
        return true;
    }

    public void a(List<HiHealthData> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            int type = hiHealthData.getType();
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    c cVar = new c();
                    cVar.f13574a = 1;
                    cVar.b = hiHealthData.getStartTime();
                    cVar.e = hiHealthData.getEndTime();
                    cVar.g = hiHealthData.getType();
                    cVar.c = hiHealthData.getModifiedTime();
                    arrayList.add(cVar);
                    break;
                }
                c cVar2 = (c) it.next();
                if (cVar2.g == type) {
                    if (cVar2.b > hiHealthData.getStartTime()) {
                        cVar2.b = hiHealthData.getStartTime();
                    }
                    if (cVar2.e < hiHealthData.getEndTime()) {
                        cVar2.e = hiHealthData.getEndTime();
                    }
                    c.b(cVar2);
                }
            }
        }
        StringBuilder sb = new StringBuilder("insDt=[");
        for (int i = 0; i < arrayList.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append('{');
            sb.append(((c) arrayList.get(i)).toString());
            sb.append('}');
        }
        sb.append(']');
        ReleaseLogUtil.e("HiH_HiHealthSaveData", sb.toString());
    }

    class c {

        /* renamed from: a, reason: collision with root package name */
        private int f13574a;
        private long b;
        private long c;
        private long e;
        private int g;

        private c() {
        }

        static /* synthetic */ int b(c cVar) {
            int i = cVar.f13574a;
            cVar.f13574a = i + 1;
            return i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(16);
            sb.append("tp=");
            sb.append(this.g);
            sb.append(",ct=");
            sb.append(this.f13574a);
            sb.append(",sTm=");
            sb.append(this.b);
            sb.append(",eTm=");
            sb.append(this.e);
            sb.append(",mTm=");
            sb.append(this.c);
            return sb.toString();
        }
    }
}
