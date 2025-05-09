package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealthservice.store.interfaces.IDataClient;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes7.dex */
public class isb implements IDataClient {

    /* renamed from: a, reason: collision with root package name */
    private static Context f13568a;
    private static final Map<Integer, e> c = new TreeMap<Integer, e>(new Comparator() { // from class: iry
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return isb.a((Integer) obj, (Integer) obj2);
        }
    }) { // from class: isb.2
        {
            put(1000, new e("DataPointManager", null, true));
            put(3000, new e("HealthDataPointSensitiveManager", "HealthDataPointManager", true));
            put(21000, new e("DataSessionManager", null, false));
            put(22099, new e("HealthDataSessionManager", null, false));
            put(22100, new e("DataCoreSessionManagerAll", null, false));
            put(22107, new e("DataCoreSessionManager", null, true));
            put(22199, new e("DataCoreSessionManagerAll", null, false));
        }
    };
    private iio b;
    private iis d;
    private iip e;
    private ijz h;
    private ijf i;

    static /* synthetic */ int a(Integer num, Integer num2) {
        return num.intValue() - num2.intValue();
    }

    private isb() {
        this.i = ijf.d(f13568a);
        this.h = ijz.c();
        this.b = iio.c();
        this.d = iis.d();
        this.e = iip.b();
    }

    static class a {
        private static final isb d = new isb();
    }

    public static isb e(Context context) {
        if (context != null) {
            f13568a = context.getApplicationContext();
        }
        return a.d;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataClient
    public HiHealthClient saveDeviceInfo(HiDeviceInfo hiDeviceInfo, int i) {
        if (hiDeviceInfo == null) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "saveDeviceInfo() deviceInfo == null");
            return null;
        }
        if (!ijf.d(f13568a).e(hiDeviceInfo)) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "saveDeviceInfo() isInserted false");
            return null;
        }
        c(i, hiDeviceInfo.getDeviceUniqueCode());
        HiUserInfo a2 = this.h.a(this.b.a(i), 0);
        HiHealthClient hiHealthClient = new HiHealthClient();
        hiHealthClient.setHiDeviceInfo(hiDeviceInfo);
        hiHealthClient.setHiUserInfo(a2);
        LogUtil.a("HiH_HiDataClntStr", "saveDeviceInfo() client = ", hiHealthClient, ", appId=", Integer.valueOf(i));
        ReleaseLogUtil.e("HiH_HiDataClntStr", "saveDeviceInfo  client and appId");
        return hiHealthClient;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataClient
    public HiHealthClient saveDeviceInfoWithUserInfo(HiDeviceInfo hiDeviceInfo, HiUserInfo hiUserInfo, int i) {
        if (hiDeviceInfo == null || hiUserInfo == null || i <= 0) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "saveDeviceInfoWithUserInfo() deviceInfo == null or userInfo == null or app <= 0 ");
            return null;
        }
        if (!this.i.e(hiDeviceInfo)) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "saveDeviceInfoWithUserInfo() isInserted false");
            return null;
        }
        String a2 = this.b.a(i);
        hiUserInfo.setHuid(a2);
        this.h.a(hiUserInfo, a2, 0);
        c(i, hiDeviceInfo.getDeviceUniqueCode());
        HiHealthClient hiHealthClient = new HiHealthClient();
        hiHealthClient.setHiDeviceInfo(hiDeviceInfo);
        hiHealthClient.setHiUserInfo(hiUserInfo);
        LogUtil.c("HiH_HiDataClntStr", "saveDeviceInfoWithUserInfo() client = ", hiHealthClient);
        return hiHealthClient;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataClient
    public List<HiHealthClient> getHealthClientList(String str, int i) {
        HiDeviceInfo d = this.i.d(str);
        HiUserInfo a2 = this.h.a(this.b.a(i), 0);
        ArrayList arrayList = new ArrayList(10);
        HiHealthClient hiHealthClient = new HiHealthClient();
        hiHealthClient.setHiDeviceInfo(d);
        hiHealthClient.setHiUserInfo(a2);
        arrayList.add(hiHealthClient);
        LogUtil.c("HiH_HiDataClntStr", "getHealthClientList() client = ", hiHealthClient);
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataClient
    public List<HiHealthClient> getAllHealthClientList(int i) {
        int d = ijl.d(f13568a, i);
        List<Integer> c2 = this.d.c(d);
        if (HiCommonUtil.d(c2)) {
            LogUtil.h("HiH_HiDataClntStr", "getAllHealthClientList() deviceIds is null who = ", Integer.valueOf(d));
            return null;
        }
        List<HiDeviceInfo> b = this.i.b(c2);
        if (b == null || b.isEmpty()) {
            LogUtil.h("HiH_HiDataClntStr", "getAllHealthClientList() deviceInfos is null devices = ", c2);
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        for (HiDeviceInfo hiDeviceInfo : b) {
            HiHealthClient hiHealthClient = new HiHealthClient();
            hiHealthClient.setHiDeviceInfo(hiDeviceInfo);
            arrayList.add(hiHealthClient);
        }
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataClient
    public List<HiHealthClient> getHealthClientListByTime(int i, HiTimeInterval hiTimeInterval, int i2) {
        return d(e(i, hiTimeInterval), hiTimeInterval, i2);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataClient
    public List<HiHealthClient> getHealthClientMergedListByTime(List<Integer> list, HiTimeInterval hiTimeInterval, int i) {
        return d(c(list, hiTimeInterval), hiTimeInterval, i);
    }

    private List<HiHealthClient> d(List<Integer> list, HiTimeInterval hiTimeInterval, int i) {
        HiHealthClient b;
        if (HiCommonUtil.d(list)) {
            LogUtil.h("HiH_HiDataClntStr", "getHealthClientListByTime dataClientIDs is null timeInterval = ", hiTimeInterval);
            return null;
        }
        int d = ijl.d(f13568a, i);
        if (d <= 0) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "getHealthClientListByTime who <= 0 app = ", Integer.valueOf(i));
            return null;
        }
        LogUtil.h("HiH_HiDataClntStr", "getClientsByIds userId end", Integer.valueOf(d));
        List<ikv> a2 = this.d.a(d, list);
        LogUtil.h("HiH_HiDataClntStr", "getClientsByIds healthContexts end");
        c();
        ReleaseLogUtil.e("HiH_HiDataClntStr", "getClientsByIds start");
        if (koq.b(a2)) {
            LogUtil.h("HiH_HiDataClntStr", "getAllClientsByUserAndClientIds is null dataClients = ", list);
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        for (ikv ikvVar : a2) {
            if (ikvVar != null && (b = b(ikvVar.e(), ikvVar.d())) != null) {
                arrayList.add(b);
            }
        }
        ReleaseLogUtil.e("HiH_HiDataClntStr", "getClientsByIds end");
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private List<Integer> c(List<Integer> list, HiTimeInterval hiTimeInterval) {
        char c2;
        List<Integer> b;
        HashSet hashSet = new HashSet();
        ReleaseLogUtil.e("HiH_HiDataClntStr", "getDataClientIds start");
        for (Map.Entry<String, Set<Integer>> entry : d(list).entrySet()) {
            int[] d = d(entry.getValue());
            if (d.length != 0) {
                String key = entry.getKey();
                key.hashCode();
                switch (key.hashCode()) {
                    case -1909817023:
                        if (key.equals("HealthDataPointSensitiveManager")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case -1276827333:
                        if (key.equals("DataStressPointManager")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 376171296:
                        if (key.equals("DataCoreSessionManager")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 876955879:
                        if (key.equals("DataPointManager")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 949469793:
                        if (key.equals("DataCoreSessionManagerAll")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1523367453:
                        if (key.equals("HealthDataSessionManager")) {
                            c2 = 5;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1636482915:
                        if (key.equals("HealthDataPointManager")) {
                            c2 = 6;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1762326177:
                        if (key.equals("DataSessionManager")) {
                            c2 = 7;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                switch (c2) {
                    case 0:
                    case 6:
                        b = ivu.d(f13568a, d[0]).b(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), d);
                        break;
                    case 1:
                        b = ijo.b(f13568a).e(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), d);
                        break;
                    case 2:
                        b = iix.a(f13568a).c(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), d);
                        break;
                    case 3:
                        b = iiy.e(f13568a).d(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), d);
                        break;
                    case 4:
                        b = iix.a(f13568a).c(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime());
                        break;
                    case 5:
                        b = ijn.a(f13568a).a(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime());
                        break;
                    case 7:
                        b = ijc.d(f13568a).a(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime());
                        break;
                    default:
                        b = null;
                        break;
                }
                if (b != null) {
                    hashSet.addAll(b);
                }
            }
        }
        ReleaseLogUtil.e("HiH_HiDataClntStr", "getDataClientIds end");
        return new ArrayList(hashSet);
    }

    private int[] d(Collection<Integer> collection) {
        collection.remove(null);
        int[] iArr = new int[collection.size()];
        Iterator<Integer> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    private void c(int i, String str) {
        if (i == igm.e().c()) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "SaveDeviceInfoWithUserInfo, and Insert the DataClient table");
            ikr.b(f13568a).e(i, i, 0, str);
        }
    }

    static class e {
        private final String b;
        private final String d;
        private final boolean e;

        e(String str, String str2, boolean z) {
            this.d = str;
            this.b = str2;
            this.e = z;
        }

        void d(Map<String, Set<Integer>> map, int i, boolean z) {
            String str = z ? this.d : this.b;
            if (str == null) {
                return;
            }
            if (this.e || !map.containsKey(str)) {
                isb.e(map, i, str);
            }
        }
    }

    private void d(Map<String, Set<Integer>> map, int i) {
        Map.Entry<Integer, e> entry;
        boolean z;
        if (HiHealthDataType.x(i)) {
            e(map, i, "DataStressPointManager");
            return;
        }
        Iterator<Map.Entry<Integer, e>> it = c.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                entry = null;
                break;
            }
            entry = it.next();
            int intValue = entry.getKey().intValue();
            if (i <= intValue) {
                if (intValue == 3000) {
                    z = ivu.g(f13568a, i);
                }
            }
        }
        z = true;
        if (entry == null) {
            if (HiHealthDictManager.d(f13568a).c(i) == 0) {
                e(map, i, ivu.g(f13568a, i) ? "HealthDataPointSensitiveManager" : "HealthDataPointManager");
                return;
            } else {
                LogUtil.c("HiH_HiDataClntStr", "getHealthClientListByTime other condition");
                return;
            }
        }
        entry.getValue().d(map, i, z);
    }

    private Map<String, Set<Integer>> d(List<Integer> list) {
        HashMap hashMap = new HashMap();
        for (Integer num : list) {
            if (num != null) {
                d(hashMap, num.intValue());
            }
        }
        Set<Integer> b = b(hashMap, "DataPointManager");
        if (b.contains(2)) {
            b.add(901);
        }
        if (hashMap.containsKey("DataCoreSessionManagerAll")) {
            hashMap.remove("DataCoreSessionManager");
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Map<String, Set<Integer>> map, int i, String str) {
        Set<Integer> b = b(map, str);
        b.add(Integer.valueOf(i));
        map.put(str, b);
    }

    private static Set<Integer> b(Map<String, Set<Integer>> map, String str) {
        Set<Integer> set = map.get(str);
        return set == null ? new HashSet() : set;
    }

    private List<Integer> e(int i, HiTimeInterval hiTimeInterval) {
        if (i <= 1000) {
            if (2 == i) {
                return iiy.e(f13568a).d(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), new int[]{2, 901});
            }
            return iiy.e(f13568a).d(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), i);
        }
        if (i <= 3000) {
            return ivu.d(f13568a, i).a(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), i);
        }
        if (i <= 21000) {
            return ijc.d(f13568a).a(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime());
        }
        if (i <= 22099) {
            return ijn.a(f13568a).a(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime());
        }
        if (i <= 22107 && i >= 22101) {
            return iix.a(f13568a).a(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), i);
        }
        if (i <= 22199) {
            return iix.a(f13568a).c(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime());
        }
        if (HiHealthDictManager.d(f13568a).c(i) == 0) {
            return ivu.d(f13568a, i).a(hiTimeInterval.getStartTime(), hiTimeInterval.getEndTime(), i);
        }
        LogUtil.c("HiH_HiDataClntStr", "getHealthClientListByTime other condition");
        return null;
    }

    private void c() {
        String androidId = FoundationCommonUtil.getAndroidId(f13568a);
        HiDeviceInfo d = this.i.d(androidId);
        if (d == null) {
            LogUtil.b("HiH_HiDataClntStr", "updateModelColumnOnLocalPhone device ", a(androidId), "is not exist!");
            return;
        }
        LogUtil.a("HiH_HiDataClntStr", "updateModelColumnOnLocalPhone deviceInfo:", Integer.valueOf(d.getDeviceId()), "--", a(d.getDeviceUniqueCode()));
        if (HiCommonUtil.b(d.getModel())) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("model", iik.a());
            this.i.bBl_(d.getDeviceId(), contentValues);
        }
    }

    private String a(String str) {
        if (HiCommonUtil.b(str) || str.length() <= 8) {
            return "";
        }
        return str.substring(0, 4) + str.substring(str.length() - 4, str.length());
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataClient
    public List<HiHealthClient> getHealthClientListByUserId(int i, HiDataSourceFetchOption hiDataSourceFetchOption) {
        if (hiDataSourceFetchOption == null) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "fetchOption is null");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(10);
        if (hiDataSourceFetchOption.getFetchType() == null) {
            ReleaseLogUtil.d("HiH_HiDataClntStr", "FetchType is null");
            return new ArrayList();
        }
        int intValue = hiDataSourceFetchOption.getFetchType().intValue();
        if (intValue == 0) {
            List<ikv> b = this.d.b(i);
            if (HiCommonUtil.d(b)) {
                LogUtil.h("HiH_HiDataClntStr", "getHealthClientListByUserId healthContexts is null who = ", Integer.valueOf(i));
                return Collections.emptyList();
            }
            for (ikv ikvVar : b) {
                HiHealthClient b2 = b(ikvVar.e(), ikvVar.d());
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
        } else if (intValue == 1) {
            if (HiCommonUtil.e(hiDataSourceFetchOption.getClientIds())) {
                ReleaseLogUtil.d("HiH_HiDataClntStr", "ClientIds is null or empty");
                return new ArrayList();
            }
            for (int i2 : hiDataSourceFetchOption.getClientIds()) {
                HiHealthClient b3 = b(this.d.d(i2), this.d.a(i2));
                if (b3 != null) {
                    b3.setClientId(i2);
                    arrayList.add(b3);
                }
            }
        } else {
            LogUtil.h("HiH_HiDataClntStr", "Unsupport data source fetch type ", hiDataSourceFetchOption.getFetchType());
        }
        return arrayList;
    }

    private HiHealthClient b(int i, int i2) {
        HiAppInfo c2 = this.e.c(i);
        HiDeviceInfo a2 = this.i.a(i2);
        if (c2 == null || a2 == null) {
            LogUtil.h("HiH_HiDataClntStr", "appInfo is null or deviceInfo is null");
            return null;
        }
        HiHealthClient hiHealthClient = new HiHealthClient();
        hiHealthClient.setHiAppInfo(c2);
        hiHealthClient.setHiDeviceInfo(a2);
        return hiHealthClient;
    }
}
