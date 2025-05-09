package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealthservice.store.merge.HiDataBusinessMerge;
import com.huawei.hihealthservice.store.merge.HiDataCoreSessionMerge;
import com.huawei.hihealthservice.store.merge.HiDataPointMerge;
import com.huawei.hihealthservice.store.merge.HiDataSessionMerge;
import com.huawei.hihealthservice.store.merge.HiDicHealthDataMerge;
import com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge;
import com.huawei.hihealthservice.store.merge.HiHealthDataPointStressMerge;
import com.huawei.hihealthservice.store.merge.HiHealthDataSessionMerge;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.thirdpartservice.OauthStatusCallback;
import com.huawei.thirdpartservice.runtasticapi.RuntasticProviderApi;
import health.compact.a.HiCommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class isc {

    /* renamed from: a, reason: collision with root package name */
    private static Context f13569a;
    private HiDataSessionMerge b;
    private HiDataBusinessMerge c;
    private HiDataPointMerge d;
    private HiDataCoreSessionMerge e;
    private HiHealthDataPointStressMerge f;
    private HiHealthDataSessionMerge g;
    private HiHealthDataPointMerge h;
    private irz i;
    private isg j;
    private HiHealthDictManager k;
    private ikr l;
    private iks n;

    private boolean a(int i) {
        return i != 7 && i >= 20001 && i < 22099;
    }

    /* synthetic */ isc(AnonymousClass3 anonymousClass3) {
        this();
    }

    private isc() {
        this.n = iks.e();
        this.l = ikr.b(f13569a);
        this.j = isg.d(f13569a);
        this.i = irz.d();
        this.h = new HiHealthDataPointMerge(f13569a);
        this.e = new HiDataCoreSessionMerge(f13569a);
        this.b = new HiDataSessionMerge(f13569a);
        this.g = new HiHealthDataSessionMerge(f13569a);
        this.d = new HiDataPointMerge(f13569a);
        this.f = new HiHealthDataPointStressMerge(f13569a);
        this.c = new HiDataBusinessMerge(f13569a);
        this.k = HiHealthDictManager.d(f13569a);
    }

    static class d {
        private static final isc c = new isc(null);
    }

    public static isc b(Context context) {
        if (context != null) {
            f13569a = context.getApplicationContext();
        }
        return d.c;
    }

    public int e(List<HiHealthData> list, int i) {
        if (a(list, i)) {
            LogUtil.h("HiH_HiHlhDtInsHlp", "saveBackgroundSyncHealthDetailData datas is null or who <= 0");
            return 7;
        }
        LogUtil.c("HiH_HiHlhDtInsHlp", "saveBackgroundSyncHealthDetailData() datas size is = ", Integer.valueOf(list.size()));
        long currentTimeMillis = System.currentTimeMillis();
        List<Integer> a2 = this.n.a(i);
        if (HiCommonUtil.d(a2)) {
            LogUtil.b("HiH_HiHlhDtInsHlp", "saveBackgroundSyncHealthDetailData() null or clients ||clients.isEmpty ()");
            return 10;
        }
        List<HiHealthData> e = ivu.e(f13569a, list);
        List<HiHealthData> d2 = ivu.d(f13569a, list);
        int e2 = e(e, i, a2);
        int e3 = e(d2, i, a2);
        if (e3 != 0) {
            e2 = e3;
        }
        LogUtil.c("HiH_HiHlhDtInsHlp", "saveBackgroundSyncHealthDetailData() end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return e2;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x010d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int e(java.util.List<com.huawei.hihealth.HiHealthData> r16, int r17, java.util.List<java.lang.Integer> r18) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isc.e(java.util.List, int, java.util.List):int");
    }

    private boolean c(List<HiHealthData> list) {
        return list.get(0).getType() == 2002 || list.get(0).getType() == 2018;
    }

    private boolean a(List<HiHealthData> list, int i) {
        return HiCommonUtil.d(list) || i <= 0;
    }

    private boolean a(List<HiHealthData> list, List<Integer> list2) {
        if (list.isEmpty()) {
            return true;
        }
        int type = list.get(0).getType();
        long startTime = list.get(list.size() - 1).getStartTime();
        long startTime2 = list.get(0).getStartTime();
        if (AnonymousClass3.d[HiHealthDataType.e(type).ordinal()] != 1) {
            return false;
        }
        if (type < 2019 || (2022 <= type && 2033 >= type)) {
            return this.h.c(list, startTime, startTime2, list2);
        }
        return false;
    }

    /* renamed from: isc$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            d = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HiHealthDataType.Category.SESSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[HiHealthDataType.Category.SEQUENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[HiHealthDataType.Category.SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                d[HiHealthDataType.Category.REALTIME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                d[HiHealthDataType.Category.STAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                d[HiHealthDataType.Category.CONFIG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                d[HiHealthDataType.Category.CONFIGSTAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                d[HiHealthDataType.Category.BUSINESS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public boolean b(HiHealthData hiHealthData, int i, List<Integer> list, int i2, int i3) {
        if (hiHealthData == null || list == null) {
            ReleaseLogUtil.d("HiH_HiHlhDtInsHlp", "saveOneDetailData null error");
            return false;
        }
        return e(hiHealthData, i, list, i2, i3);
    }

    private boolean e(HiHealthData hiHealthData, int i, List<Integer> list, int i2, int i3) {
        int type = hiHealthData.getType();
        switch (AnonymousClass3.d[HiHealthDataType.e(hiHealthData.getType()).ordinal()]) {
            case 1:
                if (this.k.c(type) == 0) {
                    return e(hiHealthData, i, list);
                }
                return a(hiHealthData, i, list, type);
            case 2:
                return d(i, hiHealthData, list, type);
            case 3:
                boolean c = this.j.c(hiHealthData, i, list);
                c(hiHealthData);
                return c;
            case 4:
                if (type == 10010) {
                    return this.j.d(hiHealthData, i3, i2);
                }
                if (this.k.d(type) != null) {
                    return this.j.b(hiHealthData, i, list);
                }
                return this.j.d(type, hiHealthData, i, list, this.h);
            case 5:
                return true;
            case 6:
                if (this.k.c(type) == 2) {
                    return this.j.a(hiHealthData, i3, i2);
                }
                return this.j.d(hiHealthData, i3, i2);
            case 7:
                return this.i.c(hiHealthData, i, list);
            case 8:
                return this.i.b(hiHealthData, i3, i2);
            case 9:
                return this.c.merge(hiHealthData, i, list);
            default:
                ReleaseLogUtil.e("HiH_HiHlhDtInsHlp", "saveOneDetailDataSync category default");
                return false;
        }
    }

    private void c(HiHealthData hiHealthData) {
        LogUtil.a("HiH_HiHlhDtInsHlp", "try to sendBroadcastToRuntasticTask");
        if (hiHealthData.getSyncStatus() == 0 && Utils.o()) {
            RuntasticProviderApi runtasticProviderApi = (RuntasticProviderApi) Services.c("FeatureDataOpen", RuntasticProviderApi.class);
            if (runtasticProviderApi.isSupportType(hiHealthData.getSubType())) {
                runtasticProviderApi.isOauth(new OauthStatusCallback() { // from class: isd
                    @Override // com.huawei.thirdpartservice.OauthStatusCallback
                    public final void onOauthStatusCallback(boolean z) {
                        isc.a(z);
                    }
                });
            }
        }
    }

    static /* synthetic */ void a(boolean z) {
        LogUtil.a("HiH_HiHlhDtInsHlp", "Runtastic isOauth", Boolean.valueOf(z));
        if (z) {
            String packageName = f13569a.getPackageName();
            LogUtil.a("HiH_HiHlhDtInsHlp", "sendBroadcast() ", " action == ", "com.huawei.health.track.broadcast");
            Intent intent = new Intent();
            intent.setPackage(packageName);
            intent.setAction("com.huawei.health.track.broadcast");
            intent.putExtra("command_type", RuntasticProviderApi.ACTION_RUNTASTIC_SYNC_DATA);
            f13569a.sendBroadcast(intent, SecurityConstant.d);
        }
    }

    private boolean a(HiHealthData hiHealthData, int i, List<Integer> list, int i2) {
        if (i2 < 2000) {
            if (i2 >= 901 && i2 <= 906) {
                return this.d.c(hiHealthData, i, 0);
            }
            return this.d.merge(hiHealthData, i, list);
        }
        if (HiHealthDataType.g(i2)) {
            return this.h.merge(hiHealthData, i, list);
        }
        if (HiHealthDataType.x(i2)) {
            return this.f.merge(hiHealthData, i, list);
        }
        LogUtil.h("HiH_HiHlhDtInsHlp", "type is invalid");
        return false;
    }

    public boolean e(HiHealthData hiHealthData, int i, List<Integer> list) {
        if (hiHealthData == null || list == null) {
            return false;
        }
        HiDicHealthDataMerge hiDicHealthDataMerge = new HiDicHealthDataMerge(f13569a);
        if (!hiDicHealthDataMerge.a(hiHealthData)) {
            LogUtil.h("HiH_HiHlhDtInsHlp", "hiDicHealthDataMerge init failed!");
            return false;
        }
        int c = this.k.c(hiHealthData.getType());
        if (c == 0) {
            return hiDicHealthDataMerge.a(hiHealthData, i, list);
        }
        if (c == 1) {
            return false;
        }
        LogUtil.h("HiH_HiHlhDtInsHlp", "This type is not supported merge! type is ", Integer.valueOf(hiHealthData.getType()));
        return false;
    }

    private boolean d(int i, HiHealthData hiHealthData, List<Integer> list, int i2) {
        if (i2 <= 21000) {
            return this.b.merge(hiHealthData, i, list);
        }
        if (i2 <= 22099) {
            return this.g.merge(hiHealthData, i, list);
        }
        return this.e.merge(hiHealthData, i, list);
    }

    public int c(List<HiHealthData> list, ikv ikvVar, int i) {
        int i2;
        int i3;
        int i4;
        HiHealthData hiHealthData;
        if (list == null) {
            return 10;
        }
        if (ikvVar == null) {
            LogUtil.h("HiH_HiHlhDtInsHlp", "saveHealthDetailData fail hiHealthContext = null");
            return 10;
        }
        int g = ikvVar.g();
        int d2 = ikvVar.d();
        int b = ikvVar.b();
        int j = ikvVar.j();
        ReleaseLogUtil.e("HiH_HiHlhDtInsHlp", "saveHlhDtlDt devId=", Integer.valueOf(d2), ",CID=", Integer.valueOf(b), ",devTp=", Integer.valueOf(j));
        List<Integer> c = ikvVar.c();
        if (c == null || c.isEmpty()) {
            LogUtil.h("HiH_HiHlhDtInsHlp", "saveHealthDetailData() fail null or clients ||clients.isEmpty ()");
            return 10;
        }
        int e = ikr.e(this.l, i, ikvVar.e(), g, d2);
        this.j.a(list);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i6 >= list.size()) {
                break;
            }
            HiHealthData hiHealthData2 = list.get(i6);
            if (iwp.c(hiHealthData2.getType())) {
                i2 = i6;
                i3 = d2;
                i4 = i5;
            } else {
                hiHealthData2.setUserId(g);
                hiHealthData2.setAppId(ikvVar.e());
                hiHealthData2.setDeviceId(d2);
                hiHealthData2.setClientId(b);
                hiHealthData2.setLastDataFlag(i6 != list.size() - 1 ? i5 : true);
                hiHealthData2.setMergedStatus(i5);
                i2 = i6;
                i3 = d2;
                i4 = i5;
                if (!b(hiHealthData2, b, c, g, e)) {
                    ReleaseLogUtil.d("HiH_HiHlhDtInsHlp", "saveOneDetailData fail sTm=", Long.valueOf(hiHealthData2.getStartTime()), ",tp=", Integer.valueOf(hiHealthData2.getType()));
                    i7 = 4;
                }
                if (iwp.g(hiHealthData2.getType())) {
                    hiHealthData = hiHealthData2;
                    iwl.c().j(hiHealthData);
                } else {
                    hiHealthData = hiHealthData2;
                }
                b(g, j, hiHealthData);
            }
            i6 = i2 + 1;
            i5 = i4;
            d2 = i3;
        }
        int i8 = i5;
        ikvVar.b(1);
        if (i7 == 0) {
            e(list, j, list.get(i8).getDeviceUuid());
        }
        iis.d().b(ikvVar);
        if (ism.i()) {
            ism.a(g, b);
        }
        return i7;
    }

    private void b(int i, int i2, HiHealthData hiHealthData) {
        long day = hiHealthData.getDay();
        if (hiHealthData.getType() == 40002) {
            double value = hiHealthData.getValue() - iuz.a(f13569a, i, (int) day, 40002);
            if (value > 5000.0d) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(8);
                linkedHashMap.put("errorCode", String.valueOf(1008));
                linkedHashMap.put("deviceType", String.valueOf(i2));
                linkedHashMap.put("date", String.valueOf(day));
                linkedHashMap.put("stepGap", String.valueOf(value));
                ivz.d(f13569a).e(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), linkedHashMap, false);
            }
        }
    }

    private void e(List<HiHealthData> list, int i, String str) {
        if (i == 32 || i == 1 || i == 30) {
            LogUtil.a("HiH_HiHlhDtInsHlp", "doExerciseIntensitySync do not need exercise intensity sync");
            return;
        }
        Iterator<HiHealthData> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (a(it.next().getType())) {
                z = true;
            }
        }
        if (z) {
            HiBroadcastUtil.b(f13569a, 8, str);
        }
    }
}
