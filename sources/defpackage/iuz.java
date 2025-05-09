package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.amap.api.services.core.AMapException;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.FirstSyncConfig;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteHealthStatReq;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DataDeleteCondition;
import com.huawei.hwcloudmodel.model.unite.DataTimeDelCondition;
import com.huawei.hwcloudmodel.model.unite.ParamValidDetail;
import com.huawei.hwcloudmodel.model.unite.ParamValidFailDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwcloudmodel.model.userprofile.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OperationKey;
import health.compact.a.HiBroadcastManager;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* loaded from: classes4.dex */
public class iuz {
    private static int c = -30;
    private static final int[] i = {2019, 2020, 2021, 2034, 2035, 2036, 2037, 2050};
    private static final int[] b = {22103, 22102, 22105, 22101, 22104, 22106, 22107};
    private static final int[] g = {22001, 22002, 22003};
    private static final int[] d = {2104, 2108, AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR, 2109, 2018, 2002, 2101, 2102, 2105};
    private static final int[] e = {31001};
    private static final List<Integer> h = new ArrayList<Integer>() { // from class: iuz.2
        {
            add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()));
        }
    };

    /* renamed from: a, reason: collision with root package name */
    public static final Map<Integer, FirstSyncConfig> f13619a = new LinkedHashMap<Integer, FirstSyncConfig>() { // from class: iuz.3
        {
            put(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR.value()), new FirstSyncConfig(30, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()));
        }
    };
    private static boolean f = false;
    private static int j = -1;

    private static int d(int i2) {
        if (i2 >= 44000 && i2 <= 44099) {
            return 3;
        }
        if (i2 <= 44299) {
            return 9;
        }
        if (i2 <= 44399) {
            return 11;
        }
        if (i2 == 46016 || i2 == 46017 || i2 == 46019) {
            return 500023;
        }
        if (i2 == 46020 || i2 == 46018) {
            return 500024;
        }
        return (i2 < 47401 || i2 > 47405) ? 0 : 1001;
    }

    public static long d(long j2, int i2) {
        return j2 - (i2 * 86400000);
    }

    public static boolean f() {
        return f;
    }

    public static void e(boolean z) {
        f = z;
    }

    public static List<SyncKey> a(Context context, int i2, int i3) {
        if (context == null) {
            return null;
        }
        return iuw.b(context, i2, i3);
    }

    public static HiDeviceInfo b(Context context, long j2) throws iut {
        if (context == null) {
            return null;
        }
        return iuw.a(context, j2);
    }

    public static DeviceInfo c(Context context, long j2) throws iut {
        if (context == null) {
            return null;
        }
        return iuw.c(context, j2);
    }

    public static SparseArray<Integer> bCF_(long j2, long j3, int i2) {
        return iuw.bCE_(j2, j3, i2);
    }

    public static int b(long j2, int i2) {
        return HiDateUtil.c(j2 - (i2 * 86400000));
    }

    public static int d(Context context) {
        if (context == null) {
            return 0;
        }
        return ivw.b(context.getPackageName());
    }

    public static List<SyncKey> b(Context context, int i2, List<Integer> list) throws iut {
        if (context == null || list == null) {
            return null;
        }
        if (!Utils.i()) {
            LogUtil.a("HiH_HiSyncUtil", "getALLVersion fail, no cloud version");
            throw new iut("no cloud version");
        }
        return iuw.b(context, i2, list);
    }

    public static Map<Integer, Long> c(Context context, List<Integer> list) throws iut {
        if (!Utils.i()) {
            LogUtil.a("HiH_HiSyncUtil", "getBusinessDataVersion, no cloud version");
            throw new iut("no cloud version for business datas");
        }
        return iuw.e(context, list);
    }

    public static boolean e(Context context, int i2, List<Integer> list) {
        if (context == null || list == null) {
            return false;
        }
        return ivf.b(context, i2, list);
    }

    public static HiHealthData c(Context context, int i2) {
        if (context == null) {
            return null;
        }
        return ivf.e(context, i2);
    }

    public static boolean d(Context context, int i2, int i3, long j2) {
        if (context == null || ijr.d().c(i2, j2, i3) != null) {
            return false;
        }
        LogUtil.a("HiH_HiSyncUtil", "checkFirstSyncByType no such data in db ,type is ", Integer.valueOf(i3));
        return true;
    }

    public static boolean a(Context context, List<DataDeleteCondition> list) throws iut {
        if (context == null || list == null) {
            return false;
        }
        DeleteHealthDataReq deleteHealthDataReq = new DeleteHealthDataReq();
        deleteHealthDataReq.setDelHealthDataConditons(list);
        return ius.a(jbs.a(context).a(deleteHealthDataReq), false);
    }

    public static boolean d(Context context, List<DataTimeDelCondition> list) throws iut {
        if (context == null || HiCommonUtil.d(list)) {
            return false;
        }
        DeleteDataByTimeReq deleteDataByTimeReq = new DeleteDataByTimeReq();
        deleteDataByTimeReq.setDelDayDataConditons(list);
        return ius.a(jbs.a(context).e(deleteDataByTimeReq), false);
    }

    public static boolean e(Context context, List<igo> list) throws iut {
        if (context == null || HiCommonUtil.d(list)) {
            ReleaseLogUtil.e("HiH_HiSyncUtil", "deleteHealthStatData null");
            return false;
        }
        for (igo igoVar : list) {
            int f2 = igoVar.f();
            HiHealthDictType h2 = HiHealthDictManager.d(context).h(igoVar.f());
            if (h2 != null) {
                f2 = h2.i();
            }
            int d2 = d(igoVar.f());
            if (d2 != 0) {
                f2 = d2;
            }
            if (h.contains(Integer.valueOf(f2))) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put("type", "deleteHealthStat " + f2);
                linkedHashMap.put("date", "deleteHealthStat " + igoVar.e());
                ivz.d(context).e(OperationKey.THREE_CIRCLE_ERROR_2129010.value(), linkedHashMap, true);
            } else {
                HashSet hashSet = new HashSet(1);
                hashSet.add(Integer.valueOf(f2));
                DeleteHealthStatReq build = new DeleteHealthStatReq.Builder().startTime(igoVar.e()).endTime(igoVar.e()).dataSource(1).types(hashSet).build();
                if (!ius.a(jbs.a(context).c(build), false)) {
                    return false;
                }
                ReleaseLogUtil.e("HiH_HiSyncUtil", "delStat,", build);
            }
        }
        return true;
    }

    public static boolean e(Context context, int i2) throws iut {
        if (context == null) {
            ReleaseLogUtil.e("HiH_HiSyncUtil", "deleteCoreSleepStat null");
            return false;
        }
        HashSet hashSet = new HashSet(1);
        hashSet.add(9);
        DeleteHealthStatReq build = new DeleteHealthStatReq.Builder().startTime(i2).endTime(i2).dataSource(1).types(hashSet).build();
        ReleaseLogUtil.e("HiH_HiSyncUtil", "delStat,", build);
        return ius.a(jbs.a(context).c(build), false);
    }

    public static void e(int i2, int i3) throws iut {
        if (i3 != 1 && i2 >= 200) {
            LogUtil.b("HiH_HiSyncUtil", "uploadCountMaxCheck too much upload count is ", Integer.valueOf(i2));
            throw new iut("SYNC_EX: UPLOAD_TOO_MUCH ");
        }
    }

    public static void c(int i2, int i3) throws iut {
        if (i3 != 1 && i2 >= 20) {
            LogUtil.b("HiH_HiSyncUtil", "uploadSportDataCountMaxCheck too much upload count is ", Integer.valueOf(i2));
            throw new iut("SYNC_EX: UPLOAD_TOO_MUCH ");
        }
    }

    public static void e(Context context, List<HiHealthData> list, int[] iArr, int i2) {
        if (list == null || context == null || iArr == null) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            long modifiedTime = hiHealthData.getModifiedTime();
            for (int i3 : iArr) {
                ivu.a(context, i3).a(i2, hiHealthData.getStartTime(), i3, modifiedTime);
            }
        }
    }

    public static List<HiHealthData> e(Context context, int i2, int[] iArr, String[] strArr, int[] iArr2) {
        if (iArr == null || strArr == null || iArr2 == null) {
            return null;
        }
        return ivf.e(context, i2, iArr, strArr, iArr2);
    }

    public static void d(Context context, List<HiHealthData> list, int[] iArr, int i2) {
        if (list == null || context == null || iArr == null) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            for (int i3 : iArr) {
                ivu.a(context, i3).c(i2, hiHealthData.getStartTime(), i3);
            }
        }
    }

    public static void h(Context context, List<HiHealthData> list) {
        if (list == null || context == null) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            iiy.e(context).e(hiHealthData.getClientId(), hiHealthData.getStartTime(), hiHealthData.getEndTime(), 901, 1);
            iiy.e(context).e(hiHealthData.getClientId(), hiHealthData.getStartTime(), hiHealthData.getEndTime(), 902, 1);
            iiy.e(context).e(hiHealthData.getClientId(), hiHealthData.getStartTime(), hiHealthData.getEndTime(), 903, 1);
            iiy.e(context).e(hiHealthData.getClientId(), hiHealthData.getStartTime(), hiHealthData.getEndTime(), 904, 1);
            iiy.e(context).e(hiHealthData.getClientId(), hiHealthData.getStartTime(), hiHealthData.getEndTime(), 905, 1);
            iiy.e(context).e(hiHealthData.getClientId(), hiHealthData.getStartTime(), hiHealthData.getEndTime(), TypedValues.Custom.TYPE_REFERENCE, 1);
        }
    }

    public static int b(int i2) {
        return ivf.c(i2);
    }

    public static int g(Context context, int i2) {
        if (context == null) {
            return 0;
        }
        return ivd.c().e(context, i2);
    }

    public static int a(Context context, int i2, int i3, int i4) {
        if (context == null) {
            return 0;
        }
        igo d2 = ivu.a(context, i4).d(i3, i4, iis.d().h(i2));
        if (d2 == null) {
            return 0;
        }
        return (int) d2.l();
    }

    public static List<DataDeleteCondition> b(Context context, List<HiHealthData> list) {
        if (context == null || list == null) {
            return null;
        }
        return ivi.a(context, list);
    }

    public static double[] b(Context context, double[] dArr) {
        if (context == null || dArr == null) {
            return null;
        }
        return ivi.e(context, dArr);
    }

    public static void b(Context context, int i2, int i3) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("sync_record", 0).edit().putInt("sportsyncrecord" + i2, i3).apply();
    }

    public static boolean b(List<igo> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        try {
            final int parseInt = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(jdl.d(System.currentTimeMillis(), c))));
            return list.stream().anyMatch(new Predicate() { // from class: iva
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return iuz.a(parseInt, (igo) obj);
                }
            });
        } catch (NumberFormatException e2) {
            ReleaseLogUtil.c("HiH_HiSyncUtil", "Failed to isLastOneMonth, cause:", e2.getMessage());
            return true;
        }
    }

    static /* synthetic */ boolean a(int i2, igo igoVar) {
        return igoVar.e() >= i2;
    }

    public static void c(Context context, int i2, boolean z) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("sync_record", 0).edit().putBoolean("firstsynccompleteflag" + i2, z).apply();
    }

    public static boolean i(Context context, int i2) {
        if (context == null) {
            return true;
        }
        return context.getSharedPreferences("sync_record", 0).getBoolean("firstsynccompleteflag" + i2, false);
    }

    public static void b(Context context, int i2, boolean z) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("sync_record", 0).edit().putBoolean("syncalldatacompleteflag" + i2, z).apply();
    }

    public static boolean h(Context context, int i2) {
        if (context == null) {
            return true;
        }
        return context.getSharedPreferences("sync_record", 0).getBoolean("syncalldatacompleteflag" + i2, false);
    }

    public static int e(int i2) {
        return SharedPreferenceManager.a("HiHealthService", "firstSyncFailCountFlag" + i2, 0);
    }

    public static void a(int i2, int i3) {
        SharedPreferenceManager.b("HiHealthService", "firstSyncFailCountFlag" + i2, i3);
    }

    public static void e(Context context, int i2, int i3) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("sync_record", 0).edit().putInt("backgroudflag" + i2, i3).apply();
        j = i3;
    }

    public static int b(Context context, int i2) {
        if (context == null) {
            return 0;
        }
        int i3 = context.getSharedPreferences("sync_record", 0).getInt("backgroudflag" + i2, 0);
        j = i3;
        return i3;
    }

    public static void d(Context context, int i2, long j2) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("sync_record", 0).edit().putLong("backgroudstarttime" + i2, j2).apply();
    }

    public static long d(Context context, int i2) {
        if (context == null) {
            return 0L;
        }
        return context.getSharedPreferences("sync_record", 0).getLong("backgroudstarttime" + i2, 0L);
    }

    public static void c() {
        BaseApplication.getContext().deleteSharedPreferences("sync_record");
    }

    public static int e() {
        return j;
    }

    public static void b(List<HiHealthData> list, ParamValidDetail paramValidDetail) {
        if (trg.d(list) || paramValidDetail == null) {
            return;
        }
        List<ParamValidFailDetail> failList = paramValidDetail.getFailList();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            HiHealthData next = it.next();
            if (next != null) {
                for (ParamValidFailDetail paramValidFailDetail : failList) {
                    if (paramValidFailDetail != null && paramValidFailDetail.getDataId() == next.getDataId()) {
                        c(next, paramValidFailDetail);
                        it.remove();
                    }
                }
            }
        }
    }

    public static int d(Context context, int i2, int i3) {
        igq e2;
        if (context == null) {
            return 0;
        }
        igq e3 = ijr.d().e(i2, 0L, i3);
        if (e3 != null) {
            return e3.d();
        }
        if (10016 != i3 || (e2 = ijr.d().e(i2, 0L, 10015)) == null) {
            return 0;
        }
        return e2.d();
    }

    public static List<Integer> a(Context context, int i2) {
        if (context == null) {
            return null;
        }
        return ivf.b(context, i2);
    }

    public static boolean i() {
        return Utils.o();
    }

    public static void h() throws iut {
        if (Utils.i()) {
            ivd.c().c(BaseApplication.getContext(), igm.e().d());
        }
    }

    public static boolean e(double d2, double d3, double d4) {
        return Double.compare(d2, d4) >= 0 && Double.compare(d2, d3) <= 0;
    }

    public static void c(int i2, boolean z) {
        if (i2 >= 100) {
            try {
                if (i2 % 100 == 0) {
                    Thread.sleep(z ? 50L : 100L);
                }
            } catch (InterruptedException e2) {
                ReleaseLogUtil.d("HiH_HiSyncUtil", "InterruptedException", LogAnonymous.b((Throwable) e2));
            }
        }
    }

    public static void c(int i2) {
        if (i2 <= 0) {
            return;
        }
        try {
            Thread.sleep(i2);
        } catch (InterruptedException e2) {
            ReleaseLogUtil.d("HiH_HiSyncUtil", "InterruptedException", LogAnonymous.b((Throwable) e2));
        }
    }

    public static void f(Context context, int i2) {
        String str;
        String str2;
        if (context == null) {
            return;
        }
        LogUtil.a("HiH_HiSyncUtil", "sendPullFinishBroadcastWithFlag");
        if (i2 == 10004) {
            str = "com.huawei.hihealth.action_sync_sport_stat_data";
            str2 = "pullSportStatStatus";
        } else if (i2 != 20000) {
            LogUtil.b("HiH_HiSyncUtil", "sendPullFinishBroadcastWithFlag incorrect syncType");
            return;
        } else {
            str = "com.huawei.hihealth.action_sync_all_data";
            str2 = "pullAllStatus";
        }
        Intent intent = new Intent(str);
        intent.setPackage(BaseApplication.getAppPackage());
        HiBroadcastManager.bwk_(context, intent);
        SharedPreferenceManager.e("HiHealthService", str2, true);
    }

    public static void b(CloudCommonReponse cloudCommonReponse, ParamValidDetail paramValidDetail) {
        b(cloudCommonReponse, paramValidDetail, (LinkedHashMap<String, String>) null);
    }

    public static void b(CloudCommonReponse cloudCommonReponse, ParamValidDetail paramValidDetail, LinkedHashMap<String, String> linkedHashMap) {
        LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(CollectionUtils.e(linkedHashMap) ? 7 : 7 + linkedHashMap.size());
        linkedHashMap2.put("errorCode", Integer.toString(cloudCommonReponse.getResultCode().intValue()));
        linkedHashMap2.put(OpAnalyticsConstants.REQUEST_INTERFACE, cloudCommonReponse.getClass().toString());
        linkedHashMap2.put(OpAnalyticsConstants.RESULT_DESCRIBE, paramValidDetail == null ? cloudCommonReponse.getResultDesc() : paramValidDetail.toString());
        if (!CollectionUtils.e(linkedHashMap)) {
            linkedHashMap2.putAll(linkedHashMap);
        }
        ivz.d(BaseApplication.getContext()).e(OperationKey.HEALTH_APP_SYNC_CLOUD_FAILED_85070023.value(), linkedHashMap2, false);
    }

    private static void c(HiHealthData hiHealthData, ParamValidFailDetail paramValidFailDetail) {
        int type = hiHealthData.getType();
        int i2 = AnonymousClass1.f13620a[HiHealthDataType.e(type).ordinal()];
        if (i2 == 1) {
            iiz.a(BaseApplication.getContext()).e(hiHealthData.getDataId(), 3);
        } else if (i2 == 2) {
            ijd.c(BaseApplication.getContext()).e((int) hiHealthData.getDataId(), 3);
        } else {
            ReleaseLogUtil.d("HiH_HiSyncUtil", "updateInvalidSyncStatus ignored by unsupported data type=", Integer.valueOf(type));
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncUtil", "Data is invalid, dataId is ", Long.valueOf(paramValidFailDetail.getDataId()), ", startTime is ", Long.valueOf(hiHealthData.getStartTime()), ", endTime is ", Long.valueOf(hiHealthData.getEndTime()), ", type is ", Integer.valueOf(type), ", sportType is ", Integer.valueOf(hiHealthData.getSubType()), ", resultCode is ", Integer.valueOf(paramValidFailDetail.getSubResultCode()), ", resultMsg is ", paramValidFailDetail.getSubResultMsg());
    }

    /* renamed from: iuz$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f13620a;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            f13620a = iArr;
            try {
                iArr[HiHealthDataType.Category.SEQUENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13620a[HiHealthDataType.Category.STAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static List<Integer> c(String str, int i2) {
        List<Integer> b2 = ijj.a(BaseApplication.getContext()).b(str, i2);
        List<Integer> b3 = iji.b().b(str, i2);
        ArrayList arrayList = new ArrayList(2);
        if (!HiCommonUtil.d(b2)) {
            arrayList.addAll(b2);
        }
        if (!HiCommonUtil.d(b3)) {
            arrayList.addAll(b3);
        }
        if ("client_id".equals(str)) {
            return arrayList;
        }
        HashSet hashSet = new HashSet(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HiHealthDictType f2 = HiHealthDictManager.d(BaseApplication.getContext()).f(((Integer) it.next()).intValue());
            if (f2 != null) {
                hashSet.add(Integer.valueOf(f2.i()));
            }
        }
        return new ArrayList(hashSet);
    }

    public static List<Integer> d() {
        ArrayList arrayList = new ArrayList(10);
        List<Integer> b2 = iiz.a(BaseApplication.getContext()).b(e);
        if (!HiCommonUtil.d(b2)) {
            arrayList.addAll(b2);
        }
        List<Integer> b3 = ijo.b(BaseApplication.getContext()).b(i);
        if (!HiCommonUtil.d(b3)) {
            arrayList.addAll(b3);
        }
        List<Integer> d2 = iix.a(BaseApplication.getContext()).d(b);
        if (!HiCommonUtil.d(d2)) {
            arrayList.addAll(d2);
        }
        List<Integer> a2 = ijn.a(BaseApplication.getContext()).a(g);
        if (!HiCommonUtil.d(a2)) {
            arrayList.addAll(a2);
        }
        List<Integer> c2 = ijj.a(BaseApplication.getContext()).c(d);
        if (!HiCommonUtil.d(c2)) {
            arrayList.addAll(c2);
        }
        return arrayList;
    }

    public static boolean a() {
        int i2 = Calendar.getInstance().get(12);
        return i2 == 0 || i2 == 30;
    }

    public static long b() {
        return new SecureRandom().nextInt(480000);
    }

    public static void e(HiSyncOption hiSyncOption) {
        if (hiSyncOption.getSyncDataType() == 20000 && hiSyncOption.getSyncAction() == 5) {
            String c2 = HiDateUtil.c("yyyyMMdd");
            String e2 = lra.e();
            try {
                String c3 = HiDateUtil.c(c2, "yyyyMMdd", 1);
                if (c3.equals(e2)) {
                    return;
                }
                if (e2 == null) {
                    e2 = HiDateUtil.c(c2, "yyyyMMdd", 5);
                } else if (HiDateUtil.c(e2, c2, "yyyyMMdd") - 1 > 5) {
                    e2 = HiDateUtil.c(c2, "yyyyMMdd", 5);
                }
                while (c3.compareTo(e2) > 0) {
                    d(c3);
                    c3 = HiDateUtil.c(c3, "yyyyMMdd", 1);
                }
                lra.e(c2, HiDateUtil.c(c2, "yyyyMMdd", 1));
            } catch (ParseException unused) {
                LogUtil.b("HiH_HiSyncUtil", "reportTrafficBI parse error");
            }
        }
    }

    private static void d(String str) {
        Map<String, Long> d2 = lra.d(str);
        if (d2 == null) {
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("date", str);
        linkedHashMap.put(lra.f14845a, String.valueOf(d2.get(lra.f14845a) == null ? 0L : d2.get(lra.f14845a).longValue()));
        linkedHashMap.put(lra.c, String.valueOf(d2.get(lra.c) == null ? 0L : d2.get(lra.c).longValue()));
        linkedHashMap.put(lra.d, String.valueOf(d2.get(lra.d) == null ? 0L : d2.get(lra.d).longValue()));
        linkedHashMap.put(lra.e, String.valueOf(d2.get(lra.e) == null ? 0L : d2.get(lra.e).longValue()));
        ivz.d(BaseApplication.getContext()).e(OperationKey.HEALTH_APP_TOTAL_DATA_TRAFFIC_2129022.value(), linkedHashMap, false);
        Map<String, Map<String, Long>> e2 = lra.e(str);
        if (e2 == null) {
            return;
        }
        for (Map.Entry<String, Map<String, Long>> entry : e2.entrySet()) {
            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
            linkedHashMap2.put("date", str);
            linkedHashMap2.put("url", entry.getKey());
            Long valueOf = Long.valueOf(entry.getValue().get(lra.f14845a) == null ? 0L : entry.getValue().get(lra.f14845a).longValue());
            Long valueOf2 = Long.valueOf(entry.getValue().get(lra.c) == null ? 0L : entry.getValue().get(lra.c).longValue());
            Long valueOf3 = Long.valueOf(entry.getValue().get(lra.d) == null ? 0L : entry.getValue().get(lra.d).longValue());
            long longValue = valueOf.longValue();
            long longValue2 = valueOf2.longValue();
            long longValue3 = valueOf3.longValue();
            linkedHashMap2.put(lra.f14845a, String.valueOf(valueOf));
            linkedHashMap2.put(lra.c, String.valueOf(valueOf2));
            linkedHashMap2.put(lra.d, String.valueOf(valueOf3));
            linkedHashMap2.put(lra.e, String.valueOf(Long.valueOf(longValue + longValue2 + longValue3)));
            ivz.d(BaseApplication.getContext()).e(OperationKey.HEALTH_APP_URL_DATA_TRAFFIC_2129023.value(), linkedHashMap2, false);
        }
    }
}
