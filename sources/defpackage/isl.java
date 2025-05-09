package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.store.interfaces.IDataRead;
import com.huawei.hihealthservice.store.stat.HiTrackStat;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class isl implements IDataRead {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Integer> f13578a;
    private static final Map<String, int[]> b;
    private static final int[] c;
    private static final int[] d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private static final int[] j;
    private static Context o;
    private isi k;
    private ikr l;
    private iks m;
    private boolean n;

    private boolean a(int i2) {
        return i2 == 2002 || i2 == 2018 || i2 == 2105;
    }

    private boolean b(int i2) {
        return i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5;
    }

    private boolean d(int i2) {
        switch (i2) {
            case 22000:
            case 22001:
            case 22002:
            case 22003:
                return true;
            default:
                switch (i2) {
                    case 22100:
                    case 22101:
                    case 22102:
                    case 22103:
                    case 22104:
                    case 22105:
                    case 22106:
                    case 22107:
                        return true;
                    default:
                        return false;
                }
        }
    }

    private boolean e(int i2) {
        switch (i2) {
            case 2034:
            case 2035:
            case 2036:
            case 2037:
                return true;
            default:
                return false;
        }
    }

    static {
        HashMap hashMap = new HashMap(16);
        b = hashMap;
        HashMap hashMap2 = new HashMap(16);
        f13578a = hashMap2;
        int[] iArr = {42051, 42052, 42053, 42054, 42055, 42056, 42057, 42058, 42059, 42060, 42061};
        i = iArr;
        int[] iArr2 = {42101, 42102, 42103, 42104, 42105, 42106, 42107, 42108, 42109, 42110, 42111};
        h = iArr2;
        int[] iArr3 = {42151, 42152, 42153, 42154, 42155, 42156, 42157, 42158, 42159, 42160, 42161};
        e = iArr3;
        int[] iArr4 = {42201, 42202, 42203, 42204, 42205, 42206, 42207, 42208, 42209, 42210, 42211};
        g = iArr4;
        int[] iArr5 = {42251, 42252, 42253, 42254, 42255, 42256, 42257, 42258, 42259, 42260, 42261};
        j = iArr5;
        int[] iArr6 = {42351, 42352, 42353, 42354, 42355, 42356, 42357, 42358, 42359, 42360, 42361};
        f = iArr6;
        int[] iArr7 = {42401, 42402, 42403, 42404, 42405, 42406, 42407, 42408, 42409, 42410, 42411};
        d = iArr7;
        int[] iArr8 = {42310, 42301, 42302, 42303, 42304, 42311, 42305, 42306, 42307, 42308, 42309, 12, 42305, 42306};
        c = iArr8;
        hashMap.put(BleConstants.SPORT_TYPE_WALK, iArr);
        hashMap.put(BleConstants.SPORT_TYPE_RUN, iArr2);
        hashMap.put(BleConstants.SPORT_TYPE_BIKE, iArr3);
        hashMap.put("262", iArr4);
        hashMap.put("512", iArr5);
        hashMap.put("217", iArr6);
        hashMap.put("220", iArr7);
        hashMap.put("271", iArr8);
        hashMap2.put(22000, 44004);
        hashMap2.put(2105, 46020);
        hashMap2.put(2018, 46018);
        hashMap2.put(2002, 46016);
        hashMap2.put(2034, 44305);
        hashMap2.put(7, 47101);
        hashMap2.put(2103, 47201);
    }

    private isl() {
        a();
    }

    static class a {
        private static final isl b = new isl();
    }

    public static isl b(Context context) {
        if (context != null) {
            o = context.getApplicationContext();
        }
        return a.b;
    }

    private void a() {
        this.l = ikr.b(o);
        this.m = iks.e();
        this.k = isi.a(o);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataRead
    public List<HiHealthData> readAggregateData(ikv ikvVar, HiAggregateOption hiAggregateOption, ifl iflVar) {
        if (ikvVar == null || hiAggregateOption == null) {
            return null;
        }
        int alignType = hiAggregateOption.getAlignType();
        int readType = hiAggregateOption.getReadType();
        int g2 = ikvVar.g();
        List<Integer> b2 = b(readType, g2, ikvVar.e(), hiAggregateOption.getDeviceUuid());
        String deviceUuid = hiAggregateOption.getDeviceUuid();
        int[] type = hiAggregateOption.getType();
        HiHealthDataType.Category e2 = HiHealthDataType.e(type[0]);
        List<HiHealthData> e3 = e(e2, new int[]{alignType, readType, g2}, ikvVar, hiAggregateOption, deviceUuid, iflVar);
        this.n = false;
        if (HiCommonUtil.d(e3)) {
            e(g2, type[0], hiAggregateOption.getStartTime(), hiAggregateOption.getEndTime(), e(readType, g2, ikvVar.e(), hiAggregateOption.getDeviceUuid()), false, b2);
        }
        if (e3 != null) {
            ReleaseLogUtil.e("HiH_HiHlhDataRds", "readAggregateData types = ", Arrays.toString(type), " ret hiHealthDatas size = ", Integer.valueOf(e3.size()));
        }
        return this.n ? e(e2, new int[]{alignType, readType, g2}, ikvVar, hiAggregateOption, deviceUuid, iflVar) : e3;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataRead
    public List<HiHealthData> readSportStatAggregateData(ikv ikvVar, HiSportStatDataAggregateOption hiSportStatDataAggregateOption) {
        String str;
        if (ikvVar == null || hiSportStatDataAggregateOption == null) {
            return null;
        }
        b(hiSportStatDataAggregateOption);
        int alignType = hiSportStatDataAggregateOption.getAlignType();
        int readType = hiSportStatDataAggregateOption.getReadType();
        int g2 = ikvVar.g();
        List<Integer> b2 = b(readType, g2, ikvVar.e(), hiSportStatDataAggregateOption.getDeviceUuid());
        String deviceUuid = hiSportStatDataAggregateOption.getDeviceUuid();
        List<HiHealthData> e2 = e(new int[]{alignType, readType, g2}, ikvVar, hiSportStatDataAggregateOption, deviceUuid);
        this.n = false;
        int[] type = hiSportStatDataAggregateOption.getType();
        if (HiCommonUtil.d(e2)) {
            str = deviceUuid;
            e(g2, type[0], hiSportStatDataAggregateOption.getStartTime(), hiSportStatDataAggregateOption.getEndTime(), e(readType, g2, ikvVar.e(), hiSportStatDataAggregateOption.getDeviceUuid()), false, b2);
        } else {
            str = deviceUuid;
        }
        if (e2 != null) {
            LogUtil.a("HiH_HiHlhDataRds", "readAggregateData types = ", Arrays.toString(type), " ret hiHealthData size = ", Integer.valueOf(e2.size()));
        }
        return this.n ? e(new int[]{alignType, readType, g2}, ikvVar, hiSportStatDataAggregateOption, str) : e2;
    }

    private List<HiHealthData> e(HiHealthDataType.Category category, int[] iArr, ikv ikvVar, HiAggregateOption hiAggregateOption, String str, ifl iflVar) {
        List<HiHealthData> c2;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        ijt b2 = ivu.b(o, hiAggregateOption.getType()[0]);
        if (AnonymousClass4.e[category.ordinal()] == 1) {
            int e2 = e(i3, i4, ikvVar.e(), str);
            LogUtil.c("HiH_HiHlhDataRds", "readAggregateData statClient is ", Integer.valueOf(e2), "hiContext is ", ikvVar);
            if (e2 <= 0) {
                LogUtil.h("HiH_HiHlhDataRds", "readAggregateData() stat  statClient <= 0 hiContext = ", ikvVar);
                return null;
            }
            c2 = c(ikvVar, hiAggregateOption, str, iArr, e2, iflVar);
        } else {
            List<Integer> b3 = b(i3, i4, ikvVar.e(), str);
            if (b3 == null || b3.isEmpty()) {
                LogUtil.h("HiH_HiHlhDataRds", "readAggregateData()  default clients is null hiContext = ", ikvVar);
                return null;
            }
            if (i2 <= 0) {
                if (i3 == 0) {
                    c2 = b2.c(b3, hiAggregateOption, iflVar);
                } else {
                    c2 = b2.d(b3, hiAggregateOption, iflVar);
                }
            } else if (i3 == 0) {
                c2 = b2.b(b3, hiAggregateOption);
            } else {
                c2 = b2.d(b3, hiAggregateOption);
            }
        }
        if (c2 != null) {
            Iterator<HiHealthData> it = c2.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
        return c2;
    }

    private List<HiHealthData> e(int[] iArr, ikv ikvVar, HiSportStatDataAggregateOption hiSportStatDataAggregateOption, String str) {
        int e2 = e(iArr[1], iArr[2], ikvVar.e(), str);
        LogUtil.c("HiH_HiHlhDataRds", "getSportStatAggregateData statClient is ", Integer.valueOf(e2), "hiContext is ", ikvVar);
        if (e2 <= 0) {
            LogUtil.h("HiH_HiHlhDataRds", "getSportStatAggregateData() stat  statClient <= 0 hiContext = ", ikvVar);
            return null;
        }
        List<HiHealthData> e3 = e(ikvVar, hiSportStatDataAggregateOption, str, iArr, e2);
        if (e3 != null) {
            Iterator<HiHealthData> it = e3.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
        return e3;
    }

    private List<HiHealthData> c(ikv ikvVar, HiAggregateOption hiAggregateOption, String str, int[] iArr, int i2, ifl iflVar) {
        ijt b2 = ivu.b(o, hiAggregateOption.getType()[0]);
        int i3 = iArr[1];
        int i4 = iArr[2];
        List<HiHealthData> a2 = b2.a(i2, hiAggregateOption, iflVar);
        if (a2 != null && !a2.isEmpty()) {
            return a2;
        }
        List<Integer> b3 = b(i3, i4, ikvVar.e(), str);
        if (b3 != null && !b3.isEmpty()) {
            return c(hiAggregateOption, b3, i4) ? b2.a(i2, hiAggregateOption, iflVar) : a2;
        }
        LogUtil.h("HiH_HiHlhDataRds", "readAggregateData()  default clients is null hiContext = ", ikvVar);
        return a2;
    }

    private List<HiHealthData> e(ikv ikvVar, HiSportStatDataAggregateOption hiSportStatDataAggregateOption, String str, int[] iArr, int i2) {
        b(hiSportStatDataAggregateOption);
        ijt b2 = ivu.b(o, hiSportStatDataAggregateOption.getType()[0]);
        int i3 = iArr[1];
        final int i4 = iArr[2];
        List<HiHealthData> a2 = b2.a(i2, hiSportStatDataAggregateOption);
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HiH_HiHlhDataRds", "getSportStatData() result is null");
            final List<Integer> b3 = b(i3, i4, ikvVar.e(), str);
            if (b3 == null || b3.isEmpty()) {
                LogUtil.h("HiH_HiHlhDataRds", "getSportStatData() default clients is null hiContext = ", ikvVar);
            } else {
                final HiAggregateOption hiAggregateOption = new HiAggregateOption();
                hiAggregateOption.setEndTime(hiSportStatDataAggregateOption.getEndTime());
                hiAggregateOption.setSortOrder(hiSportStatDataAggregateOption.getSortOrder());
                hiAggregateOption.setAnchor(hiSportStatDataAggregateOption.getAnchor());
                hiAggregateOption.setCount(hiSportStatDataAggregateOption.getCount());
                hiAggregateOption.setType(hiSportStatDataAggregateOption.getType());
                ThreadPoolManager.d().execute(new Runnable() { // from class: isl.5
                    @Override // java.lang.Runnable
                    public void run() {
                        isl.this.c(hiAggregateOption, (List<Integer>) b3, i4);
                    }
                });
            }
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(HiAggregateOption hiAggregateOption, List<Integer> list, int i2) {
        long j2;
        int i3;
        int[] type = hiAggregateOption.getType();
        hiAggregateOption.getStartTime();
        switch (type[0]) {
            case 42301:
            case 42302:
            case 42303:
            case 42304:
            case 42305:
            case 42306:
            case 42307:
            case 42308:
            case 42309:
            case 42310:
            case 42311:
                j2 = 1559318400000L;
                i3 = 30023;
                break;
            default:
                j2 = 0;
                i3 = 0;
                break;
        }
        long j3 = j2;
        if (iwg.b(o, i2, i3)) {
            return false;
        }
        iwg.a(o, i2, i3, true);
        LogUtil.a("HiH_HiHlhDataRds", "First stat basketball data begin!");
        List<HiHealthData> d2 = d(hiAggregateOption, j3, i3, list);
        if (koq.b(d2)) {
            LogUtil.h("HiH_HiHlhDataRds", "getNewTrackStat basketball data is null!");
            return false;
        }
        HiTrackStat hiTrackStat = new HiTrackStat(o);
        for (HiHealthData hiHealthData : d2) {
            hiHealthData.setUserId(i2);
            hiTrackStat.stat(hiHealthData);
        }
        LogUtil.a("HiH_HiHlhDataRds", "First stat basketball data end");
        return true;
    }

    private List<HiHealthData> d(HiAggregateOption hiAggregateOption, long j2, int i2, List<Integer> list) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j2);
        hiDataReadOption.setEndTime(hiAggregateOption.getEndTime());
        hiDataReadOption.setSortOrder(hiAggregateOption.getSortOrder());
        hiDataReadOption.setAnchor(hiAggregateOption.getAnchor());
        hiDataReadOption.setCount(hiAggregateOption.getCount());
        LogUtil.a("HiH_HiHlhDataRds", "getSequenceHealthDatas() sequenceType = ", Integer.valueOf(i2), " hiDataReadOption = ", hiDataReadOption.toString());
        List<HiHealthData> c2 = ish.c(iiz.a(o).b(list, hiDataReadOption, 30001), i2);
        if (!koq.b(c2)) {
            LogUtil.a("HiH_HiHlhDataRds", "getSequenceHealthDatas() sequenceType = ", Integer.valueOf(i2), " sequenceHealthDatas.size = ", Integer.valueOf(c2.size()));
        }
        return c2;
    }

    private void b(HiHealthData hiHealthData) {
        ikk a2 = ikk.a(o);
        hiHealthData.putInt("trackdata_deviceType", a2.b(hiHealthData.getClientId()));
        HiDeviceInfo e2 = a2.e(hiHealthData.getClientId());
        String c2 = a2.c(e2);
        String b2 = a2.b(e2);
        String d2 = a2.d(e2);
        String a3 = a2.a(e2);
        hiHealthData.putString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, b2);
        hiHealthData.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, c2);
        hiHealthData.putString("device_uniquecode", d2);
        hiHealthData.putString("device_prodid", a3);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataRead
    public void readDataByAlignType(int i2, ikv ikvVar, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        List<HiHealthData> a2;
        List<HiHealthData> a3;
        if (ikvVar == null || hiDataReadOption == null || iDataReadResultListener == null) {
            return;
        }
        int readType = hiDataReadOption.getReadType();
        List<Integer> b2 = b(readType, ikvVar.g(), ikvVar.e(), hiDataReadOption.getDeviceUuid());
        if (b2 == null || b2.isEmpty()) {
            iDataReadResultListener.onResult(new ArrayList(), 0, 1);
            LogUtil.h("HiH_HiHlhDataRds", "readDataByAlignType()  clients is null hiContext = ", ikvVar);
            return;
        }
        if (i2 == 20001) {
            int[] e2 = ivu.e(o, hiDataReadOption.getType());
            if (e2.length > 0 && !c("hihealth_sensitive.db")) {
                throw new RuntimeException("can not open sensitive db");
            }
            int[] c2 = ivu.c(o, hiDataReadOption.getType());
            if (readType == 0) {
                hiDataReadOption.setType(e2);
                a2 = c(b2, hiDataReadOption);
                hiDataReadOption.setType(c2);
                a3 = c(b2, hiDataReadOption);
            } else {
                hiDataReadOption.setType(e2);
                a2 = a(b2, hiDataReadOption);
                hiDataReadOption.setType(c2);
                a3 = a(b2, hiDataReadOption);
            }
            ArrayList arrayList = new ArrayList();
            if (koq.c(a2)) {
                arrayList.addAll(a2);
            }
            if (koq.c(a3)) {
                arrayList.addAll(a3);
            }
            HiDivideUtil.d(i2, arrayList, iDataReadResultListener);
            return;
        }
        iDataReadResultListener.onResult(null, i2, 1);
    }

    private boolean c(String str) {
        if (HiHealthDBHelper.e(str).getWritableDatabase() != null) {
            return true;
        }
        LogUtil.b("HiH_HiHlhDataRds", "Can not open db ", str);
        return false;
    }

    private List<HiHealthData> c(List<Integer> list, HiDataReadOption hiDataReadOption) {
        if (hiDataReadOption.getType() == null || hiDataReadOption.getType().length == 0) {
            return null;
        }
        return ivu.b(o, hiDataReadOption.getType()[0]).e(list, hiDataReadOption);
    }

    private List<HiHealthData> a(List<Integer> list, HiDataReadOption hiDataReadOption) {
        if (hiDataReadOption.getType() == null || hiDataReadOption.getType().length == 0) {
            return null;
        }
        return ivu.b(o, hiDataReadOption.getType()[0]).d(list, hiDataReadOption);
    }

    private List<Integer> b(int i2, int i3, int i4, String str) {
        ikv a2;
        if (i2 == 0) {
            return this.m.a(i3);
        }
        if (i2 == 1) {
            return this.m.a(i4, i3);
        }
        if (i2 == 2) {
            return this.m.b(i3, str);
        }
        if (i2 != 3 || (a2 = this.l.a(i4, i3, str)) == null) {
            return null;
        }
        int b2 = a2.b();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(b2));
        return arrayList;
    }

    private int e(int i2, int i3, int i4, String str) {
        ikv a2;
        LogUtil.c("HiH_HiHlhDataRds", "getStatClientID() readType = ", Integer.valueOf(i2), ",who =  ", Integer.valueOf(i3), ",app = ", Integer.valueOf(i4));
        int i5 = 0;
        if (i2 == 0) {
            i5 = this.l.e(0, i3, 0);
        } else if (i2 == 1) {
            i5 = this.l.e(i4, i3, 0);
        } else if (i2 == 2) {
            ikv a3 = this.l.a(0, i3, str);
            if (a3 != null) {
                i5 = a3.b();
            }
        } else if (i2 == 3 && (a2 = this.l.a(i4, i3, str)) != null) {
            i5 = a2.b();
        }
        LogUtil.c("HiH_HiHlhDataRds", "getStatClientID() statClient = ", Integer.valueOf(i5));
        return i5;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataRead
    public void readDataByType(HiDataReadOption hiDataReadOption, ikv ikvVar, ifl iflVar, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        if (ikvVar == null || hiDataReadOption == null || iDataReadResultListener == null) {
            return;
        }
        int readType = hiDataReadOption.getReadType();
        int g2 = ikvVar.g();
        List<Integer> b2 = b(readType, g2, ikvVar.e(), hiDataReadOption.getDeviceUuid());
        int e2 = e(readType, g2, ikvVar.e(), hiDataReadOption.getDeviceUuid());
        LogUtil.c("HiH_HiHlhDataRds", "readDataByType who is ", Integer.valueOf(g2), " statClient is ", Integer.valueOf(e2));
        if (HiCommonUtil.d(b2)) {
            iDataReadResultListener.onResult(new ArrayList(), 0, 1);
            LogUtil.h("HiH_HiHlhDataRds", "readDataByType clients null or empty");
            return;
        }
        int[] type = hiDataReadOption.getType();
        if (iflVar.k()) {
            if (HiCommonUtil.a(type, 44102) != -1 || HiCommonUtil.a(type, 2004) != -1 || HiCommonUtil.a(type, DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value()) != -1) {
                d(b2, e2, g2, hiDataReadOption, iDataReadResultListener, type);
                return;
            }
            List<HiHealthData> e3 = e(hiDataReadOption, e2, b2);
            if (HiCommonUtil.d(e3)) {
                iDataReadResultListener.onResult(new ArrayList(10), type[0], 1);
                return;
            }
            for (HiHealthData hiHealthData : e3) {
                hiDataReadOption.setStartTime(HiDateUtil.t(hiHealthData.getStartTime()));
                hiDataReadOption.setEndTime(HiDateUtil.f(hiHealthData.getStartTime()));
                if (!HiCommonUtil.d(b(hiDataReadOption, iflVar, iDataReadResultListener, ikvVar, b2, new int[]{e2, readType, g2}))) {
                    return;
                }
            }
            return;
        }
        b(hiDataReadOption, iflVar, iDataReadResultListener, ikvVar, b2, new int[]{e2, readType, g2});
    }

    private List<HiHealthData> b(HiDataReadOption hiDataReadOption, ifl iflVar, IDataReadResultListener iDataReadResultListener, ikv ikvVar, List<Integer> list, int[] iArr) throws RemoteException {
        int i2;
        int i3;
        ArrayList arrayList;
        int[] iArr2;
        boolean z = false;
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int[] type = hiDataReadOption.getType();
        ArrayList arrayList2 = new ArrayList(10);
        int length = type.length;
        List<Integer> list2 = list;
        int i7 = 0;
        while (i7 < length) {
            int i8 = type[i7];
            this.n = z;
            List<HiHealthData> c2 = c(hiDataReadOption, iDataReadResultListener, iflVar, list2, new int[]{i4, i8, i6});
            if (HiCommonUtil.d(c2)) {
                i2 = i7;
                i3 = length;
                arrayList = arrayList2;
                iArr2 = type;
                e(i6, i8, hiDataReadOption.getStartTime(), hiDataReadOption.getEndTime(), i4, true, list2);
            } else {
                i2 = i7;
                i3 = length;
                arrayList = arrayList2;
                iArr2 = type;
            }
            if (this.n) {
                List<Integer> b2 = b(i5, i6, ikvVar.e(), hiDataReadOption.getDeviceUuid());
                c2 = c(hiDataReadOption, iDataReadResultListener, iflVar, b2, new int[]{i4, i8, i6});
                list2 = b2;
            }
            List<HiHealthData> list3 = c2;
            if (!HiCommonUtil.d(list3)) {
                arrayList.addAll(list3);
            }
            i7 = i2 + 1;
            arrayList2 = arrayList;
            length = i3;
            type = iArr2;
            z = false;
        }
        ArrayList arrayList3 = arrayList2;
        if (arrayList3.size() == 0 && 2 == hiDataReadOption.getReadType()) {
            LogUtil.a("HiH_HiHlhDataRds", "clients=" + HiJsonUtil.e(list2));
        }
        return arrayList3;
    }

    private void d(List<Integer> list, int i2, int i3, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, int[] iArr) throws RemoteException {
        if (HiCommonUtil.a(iArr, 44102) != -1) {
            HiDivideUtil.d(44102, ivu.a(o, 44102).b(hiDataReadOption, hiDataReadOption.getType(), i2), iDataReadResultListener);
        }
        if (HiCommonUtil.a(iArr, 2004) != -1) {
            HiDivideUtil.d(2004, ivu.d(o, 2004).c(hiDataReadOption, hiDataReadOption.getType(), list, ijz.c().b(i3)), iDataReadResultListener);
        }
        if (HiCommonUtil.a(iArr, DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value()) != -1) {
            HiDivideUtil.d(DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value(), ivu.d(o, DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value()).c(hiDataReadOption, hiDataReadOption.getType(), list), iDataReadResultListener);
        }
    }

    private List<HiHealthData> e(HiDataReadOption hiDataReadOption, int i2, List<Integer> list) {
        HiDataReadOption hiDataReadOption2 = new HiDataReadOption();
        hiDataReadOption2.setTimeInterval(hiDataReadOption.getStartTime(), hiDataReadOption.getEndTime());
        hiDataReadOption2.setSortOrder(1);
        int i3 = hiDataReadOption.getType()[0];
        hiDataReadOption2.setCount(1);
        if (i3 == 2002) {
            hiDataReadOption2.setType(new int[]{46019});
            return this.k.e(46019, i2, hiDataReadOption2, (ifl) null);
        }
        if (i3 == 2034) {
            hiDataReadOption2.setType(new int[]{44307});
            return this.k.e(44307, i2, hiDataReadOption2, (ifl) null);
        }
        if (i3 == 2103 || i3 == 2107) {
            int[] iArr = {2103};
            hiDataReadOption2.setType(iArr);
            return ivu.d(o, 2103).c(hiDataReadOption2, iArr, list);
        }
        int[] d2 = HiHealthDataType.d(10001);
        int[] copyOf = Arrays.copyOf(d2, d2.length + 1);
        copyOf[copyOf.length - 1] = 2108;
        if (HiCommonUtil.a(copyOf, i3) == -1 && i3 != 10001) {
            return null;
        }
        hiDataReadOption2.setType(copyOf);
        return ivu.d(o, i3).c(hiDataReadOption2, copyOf, list);
    }

    private List<HiHealthData> c(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, ifl iflVar, List<Integer> list, int[] iArr) throws RemoteException {
        List<HiHealthData> e2;
        boolean z = false;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        HiHealthDataType.Category e3 = HiHealthDataType.e(i3);
        LogUtil.c("HiH_HiHlhDataRds", "readHiHealthData() sampleType = ", Integer.valueOf(i3), ", category = ", e3);
        switch (AnonymousClass4.e[e3.ordinal()]) {
            case 1:
                e2 = this.k.e(i3, i2, hiDataReadOption, iflVar);
                z = true;
                break;
            case 2:
                e2 = this.k.a(i3, list, hiDataReadOption);
                if (e2 != null) {
                    for (HiHealthData hiHealthData : e2) {
                        hiHealthData.putInt("trackdata_deviceType", ikk.a(o).b(hiHealthData.getClientId()));
                    }
                }
                z = true;
                break;
            case 3:
                e2 = this.k.b(i3, list, hiDataReadOption, iflVar);
                if (e2 != null) {
                    for (HiHealthData hiHealthData2 : e2) {
                        hiHealthData2.putInt("trackdata_deviceType", ikk.a(o).b(hiHealthData2.getClientId()));
                    }
                }
                z = true;
                break;
            case 4:
                this.k.e(i3, list, hiDataReadOption, iflVar, iDataReadResultListener);
                e2 = null;
                break;
            case 5:
                e2 = this.k.d(i4, hiDataReadOption);
                z = true;
                break;
            case 6:
                e2 = this.k.c(i3, i4, hiDataReadOption);
                z = true;
                break;
            default:
                e2 = a(e3, hiDataReadOption, list, i2, i3, iflVar);
                z = true;
                break;
        }
        if (e2 != null) {
            ReleaseLogUtil.e("HiH_HiHlhDataRds", "rDataByTp sTp=", Integer.valueOf(i3), " rDtSize=", Integer.valueOf(e2.size()));
            Iterator<HiHealthData> it = e2.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
        if (z) {
            HiDivideUtil.d(i3, e2, iDataReadResultListener);
        }
        return e2;
    }

    public List<HiHealthData> d(int i2, long j2, long j3, List<Integer> list) {
        if (AnonymousClass4.e[HiHealthDataType.e(i2).ordinal()] != 4) {
            return null;
        }
        return iiz.a(o).d(list, j2, j3, i2);
    }

    public List<HiHealthData> a(int i2, List<Integer> list) {
        if (AnonymousClass4.e[HiHealthDataType.e(i2).ordinal()] != 4) {
            return null;
        }
        return iiz.a(o).a(list);
    }

    private List<HiHealthData> a(HiHealthDataType.Category category, HiDataReadOption hiDataReadOption, List<Integer> list, int i2, int i3, ifl iflVar) {
        switch (category) {
            case SET:
                List<HiHealthData> e2 = this.k.e(i3, list, hiDataReadOption, iflVar);
                if (e2 == null) {
                    return e2;
                }
                for (HiHealthData hiHealthData : e2) {
                    hiHealthData.putInt("trackdata_deviceType", ikk.a(o).b(hiHealthData.getClientId()));
                }
                return e2;
            case REALTIME:
                return this.k.e(i3, list, hiDataReadOption.getDeviceUuid());
            case CONFIG:
                return this.k.d(i3, list, hiDataReadOption);
            case CONFIGSTAT:
                return this.k.a(i3, i2, hiDataReadOption);
            default:
                return null;
        }
    }

    private boolean e(HiDataReadOption hiDataReadOption, int i2) {
        return !HiCommonUtil.d(ijd.c(o).b(hiDataReadOption, new int[]{44105, 44109, 44108}, i2));
    }

    private void e(int i2, int i3, long j2, long j3, int i4, boolean z, List<Integer> list) {
        int i5;
        int i6;
        LogUtil.a("HiH_HiHlhDataRds", "startBackInsert  types[0]=" + i3 + " readStartTime = " + j2 + " readEndTime = " + j3);
        long j4 = j3 - j2;
        if (j4 > 86400000 || j4 < 3600000) {
            ReleaseLogUtil.e("HiH_HiHlhDataRds", "interval > 1day or < 1hour, type=", Integer.valueOf(i3));
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j2);
        hiDataReadOption.setEndTime(j3);
        HiDataReadOption hiDataReadOption2 = new HiDataReadOption();
        hiDataReadOption2.setStartTime(HiDateUtil.t(j2));
        hiDataReadOption2.setEndTime(HiDateUtil.f(j2));
        if (b(i3)) {
            i5 = PrebakedEffectId.RT_CALENDAR_DATE;
            i6 = 10011;
        } else if (d(i3)) {
            if (HiDateUtil.q(j2) != j2) {
                return;
            }
            hiDataReadOption.setStartTime(j3);
            if (i3 == 22100 && !e(hiDataReadOption, i4)) {
                ReleaseLogUtil.e("HiH_HiHlhDataRds", "DATA_SESSION_CORE_SLEEP StatData null");
                return;
            }
            if (c(i3, i4, hiDataReadOption, 22000)) {
                return;
            }
            hiDataReadOption2.setTimeInterval(j2, j3);
            if (this.k.c(i3, list, hiDataReadOption2)) {
                ReleaseLogUtil.e("HiH_HiHlhDataRds", "readSleepAllData have data");
                return;
            } else {
                i5 = 10016;
                i6 = 10012;
            }
        } else if (a(i3)) {
            if (c(i3, i4, hiDataReadOption, 2105) || c(i3, i4, hiDataReadOption, 2018) || c(i3, i4, hiDataReadOption, 2002)) {
                return;
            }
            i5 = 10017;
            i6 = 10013;
        } else if (e(i3)) {
            if (c(i3, i4, hiDataReadOption, 2034)) {
                return;
            }
            i5 = 10020;
            i6 = 10019;
        } else if (i3 == 7) {
            if (c(i3, i4, hiDataReadOption, 7)) {
                return;
            }
            i5 = 10022;
            i6 = 10021;
        } else if (i3 == 2103) {
            if (c(i3, i4, hiDataReadOption, 2103)) {
                return;
            }
            i5 = 10024;
            i6 = 10023;
        } else {
            e(i3, i4, hiDataReadOption);
            return;
        }
        if (!d(i3) && !a(i3) && !HiCommonUtil.d(this.k.b(i3, list, hiDataReadOption2, (ifl) null))) {
            ReleaseLogUtil.e("HiH_HiHlhDataRds", "other type readPointData null");
            return;
        }
        if (a(i3)) {
            HashSet hashSet = new HashSet();
            String b2 = SharedPreferenceManager.b(o, "huawei_hihealth", "heartRate_" + i2);
            if (!TextUtils.isEmpty(b2)) {
                hashSet.addAll((Collection) HiJsonUtil.b(b2, new TypeToken<Set<Integer>>() { // from class: isl.3
                }.getType()));
            }
            if (koq.c(hashSet) && hashSet.contains(Integer.valueOf(HiDateUtil.c(j2)))) {
                ReleaseLogUtil.e("HiH_HiHlhDataRds", "heartRateDay already download-", Long.valueOf(j2));
                return;
            }
        }
        int d2 = iuz.d(o, i2, i5);
        if (e(i5, d2)) {
            return;
        }
        boolean b3 = PowerKitManager.e().b();
        if (b3 || !CommonUtil.aa(o)) {
            ReleaseLogUtil.e("HiH_HiHlhDataRds", "network is disconnected, isUserSleeping=", Boolean.valueOf(b3));
            return;
        }
        try {
            int c2 = HiDateUtil.c(j2);
            int b4 = HiDateUtil.b(d2, c2, "yyyyMMdd");
            ReleaseLogUtil.e("HiH_HiHlhDataRds", "startDownload Day=", Integer.valueOf(d2), ", sTm=", Integer.valueOf(c2));
            if (!z && b4 > 0 && b4 <= 7) {
                c(j2, i6, d2);
            }
            if (z || d2 > c2) {
                c(j2, i6, d2, i3);
            }
        } catch (ParseException e2) {
            ReleaseLogUtil.c("HiH_HiHlhDataRds", "startBackInsert parseException e=", e2.getMessage());
        }
    }

    private void e(int i2, int i3, HiDataReadOption hiDataReadOption) {
        if (hiDataReadOption.getEndTime() - hiDataReadOption.getStartTime() <= 86400000 && hiDataReadOption.getStartTime() == HiDateUtil.t(hiDataReadOption.getStartTime())) {
            boolean b2 = PowerKitManager.e().b();
            if (b2 || !CommonUtil.aa(o)) {
                ReleaseLogUtil.e("HiH_HiHlhDataRds", "isUserSleeping=", Boolean.valueOf(b2));
                return;
            }
            HiHealthDictType f2 = HiHealthDictManager.d((Context) null).f(i2);
            if (f2 != null) {
                i2 = f2.i();
            }
            int i4 = i2;
            if (i4 == DicDataTypeUtil.DataType.ALTITUDE_TYPE_SET.value()) {
                List<SyncKey> d2 = ity.a(o).d(i4);
                if (HiCommonUtil.d(d2) || d2.get(0).getVersion().longValue() == 0) {
                    ReleaseLogUtil.d("HiH_HiHlhDataRds", "data not exist in cloud");
                    return;
                } else {
                    c(hiDataReadOption.getStartTime(), i4, 0, i4);
                    return;
                }
            }
            if (iuz.f13619a.containsKey(Integer.valueOf(i4))) {
                if (HiCommonUtil.d(this.k.e((int) iuz.f13619a.get(Integer.valueOf(i4)).e(), i3, hiDataReadOption, (ifl) null))) {
                    return;
                }
                c(hiDataReadOption.getStartTime(), i4, 0, i4);
            }
        }
    }

    private boolean c(int i2, int i3, HiDataReadOption hiDataReadOption, int i4) {
        Map<Integer, Integer> map = f13578a;
        int intValue = map.containsKey(Integer.valueOf(i4)) ? map.get(Integer.valueOf(i4)).intValue() : 0;
        if (intValue == 0) {
            ReleaseLogUtil.e("HiH_HiHlhDataRds", "no match statType");
            return false;
        }
        if (i2 != i4 || !HiCommonUtil.d(this.k.e(intValue, i3, hiDataReadOption, (ifl) null))) {
            return false;
        }
        ReleaseLogUtil.e("HiH_HiHlhDataRds", Integer.valueOf(i2), " Stat null");
        return true;
    }

    private boolean e(int i2, int i3) {
        if (i3 != 0) {
            return false;
        }
        ReleaseLogUtil.e("HiH_HiHlhDataRds", "download detailDay=0, No download again!,syncTp=", Integer.valueOf(i2));
        return true;
    }

    private void c(long j2, int i2, int i3) {
        LogUtil.a("HiH_HiHlhDataRds", "startBackInsert seven Day download  syncDay  is ", Integer.valueOf(i3), "startTime is ", Integer.valueOf(HiDateUtil.c(j2)));
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(i2);
        hiSyncOption.setSyncDay(j2);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        ism.f().a(hiSyncOption, iip.b().a(o.getPackageName()));
    }

    private void c(long j2, int i2, int i3, int i4) {
        LogUtil.a("HiH_HiHlhDataRds", "buildDaySyncOption single Day download  syncDay  is ", Integer.valueOf(i3), "startTime is ", Integer.valueOf(HiDateUtil.c(j2)));
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDay(j2);
        hiSyncOption.setSyncDataType(i2);
        hiSyncOption.setSyncType(i4);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        ism.f().e(hiSyncOption, iip.b().a(o.getPackageName()));
        this.n = true;
    }

    private void b(HiSportStatDataAggregateOption hiSportStatDataAggregateOption) {
        int[] hiHealthTypes = hiSportStatDataAggregateOption.getHiHealthTypes();
        if (hiHealthTypes == null || hiHealthTypes.length != 1) {
            return;
        }
        int i2 = hiHealthTypes[0];
        Map<String, int[]> map = b;
        if (map.get(String.valueOf(i2)) != null) {
            int[] iArr = map.get(String.valueOf(i2));
            int[] type = hiSportStatDataAggregateOption.getType();
            for (int i3 = 0; i3 < type.length; i3++) {
                LogUtil.c("HiH_HiHlhDataRds", "type is ", Integer.valueOf(type[i3]));
                int i4 = iArr[type[i3] - 1];
                type[i3] = i4;
                LogUtil.c("HiH_HiHlhDataRds", "old type is ", Integer.valueOf(i4));
            }
            hiSportStatDataAggregateOption.setType(type);
            hiSportStatDataAggregateOption.setHiHealthTypes(null);
            hiSportStatDataAggregateOption.setFilter(String.valueOf(i2));
        }
    }
}
