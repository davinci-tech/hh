package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class ijt {
    private DBPointCommon d;

    /* synthetic */ ijt(AnonymousClass1 anonymousClass1) {
        this();
    }

    private ijt() {
        this.d = ihk.d();
    }

    protected ijt(DBPointCommon dBPointCommon) {
        this.d = dBPointCommon;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final ijt f13399a = new ijt((AnonymousClass1) null);
    }

    public static ijt b() {
        return e.f13399a;
    }

    public List<HiHealthData> d(List<Integer> list, HiDataReadOption hiDataReadOption) {
        String[] strArr = new String[(list.size() * 2) + hiDataReadOption.getType().length + 4];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(20001);
        strArr[3] = Integer.toString(21000);
        return iih.bAO_(this.d.rawQuery(iil.d(hiDataReadOption, list, strArr, 4, false), strArr), hiDataReadOption.getConstantsKey());
    }

    public List<HiHealthData> e(List<Integer> list, HiDataReadOption hiDataReadOption) {
        String[] strArr = new String[(list.size() * 2) + hiDataReadOption.getType().length + 6];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(20001);
        strArr[3] = Integer.toString(21000);
        strArr[4] = Integer.toString(0);
        strArr[5] = Integer.toString(0);
        return iih.bAO_(this.d.rawQuery(iil.d(hiDataReadOption, list, strArr, 6, true), strArr), hiDataReadOption.getConstantsKey());
    }

    public List<HiHealthData> b(int i, HiDataReadOption hiDataReadOption, int i2) {
        String[] strArr = new String[hiDataReadOption.getType().length + 3];
        strArr[0] = Integer.toString(0);
        strArr[1] = Integer.toString(i);
        strArr[2] = Integer.toString(i);
        return iih.bAE_(this.d.rawQuery(iil.a(hiDataReadOption.getType(), hiDataReadOption.getConstantsKey(), i2, strArr, 3), strArr), hiDataReadOption.getConstantsKey());
    }

    public List<HiHealthData> a(int i, HiDataReadOption hiDataReadOption) {
        String[] strArr = new String[hiDataReadOption.getType().length + 5];
        strArr[0] = Integer.toString(0);
        strArr[1] = Integer.toString(i);
        strArr[2] = Integer.toString(i);
        strArr[3] = Long.toString(hiDataReadOption.getStartTime());
        strArr[4] = Long.toString(hiDataReadOption.getEndTime());
        return iih.bAE_(this.d.rawQuery(iil.b(hiDataReadOption.getType(), hiDataReadOption.getConstantsKey(), strArr, 5), strArr), hiDataReadOption.getConstantsKey());
    }

    public List<HiHealthData> c(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        String[] strArr = new String[hiDataReadOption.getType().length + 1 + list.size()];
        strArr[0] = Integer.toString(0);
        return iih.bAC_(this.d.rawQuery(iil.c(list, hiDataReadOption.getType(), hiDataReadOption.getConstantsKey(), i, strArr, 1), strArr), hiDataReadOption.getConstantsKey());
    }

    public List<HiHealthData> b(List<Integer> list, HiAggregateOption hiAggregateOption) {
        String[] strArr;
        String d;
        int size = list.size();
        int alignType = hiAggregateOption.getAlignType();
        if (alignType == 20001) {
            strArr = new String[(size * 2) + 6];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr[2] = Integer.toString(20001);
            strArr[3] = Integer.toString(21000);
            strArr[4] = Integer.toString(0);
            strArr[5] = Integer.toString(0);
            d = iil.d(list, strArr, 6, hiAggregateOption, true);
        } else {
            strArr = new String[(size * 2) + 5];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr[2] = Integer.toString(alignType);
            strArr[3] = Integer.toString(0);
            strArr[4] = Integer.toString(0);
            d = iil.d(list, strArr, 5, hiAggregateOption, true);
        }
        return iih.bAc_(this.d.rawQuery(d, strArr), hiAggregateOption.getConstantsKey());
    }

    public List<HiHealthData> e(List<Integer> list, HiAggregateOption hiAggregateOption) {
        String[] strArr;
        String d;
        int size = list.size();
        int alignType = hiAggregateOption.getAlignType();
        if (alignType == 20001) {
            strArr = new String[(size * 2) + 6];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr[2] = Integer.toString(20001);
            strArr[3] = Integer.toString(21000);
            strArr[4] = Integer.toString(0);
            strArr[5] = Integer.toString(0);
            d = iin.d(list, strArr, 6, hiAggregateOption, true);
        } else {
            strArr = new String[(size * 2) + 5];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr[2] = Integer.toString(alignType);
            strArr[3] = Integer.toString(0);
            strArr[4] = Integer.toString(0);
            d = iin.d(list, strArr, 5, hiAggregateOption, true);
        }
        LogUtil.c("Debug_RawQueryManager", "querySportAggregateDataByAlignType() aggregateSQL = ", d, ",selectAgs = ", HiJsonUtil.e(strArr));
        return iih.bAc_(this.d.rawQuery(d, strArr), hiAggregateOption.getConstantsKey());
    }

    public List<HiHealthData> d(List<Integer> list, HiAggregateOption hiAggregateOption) {
        String[] strArr;
        String d;
        int size = list.size();
        int alignType = hiAggregateOption.getAlignType();
        if (alignType == 20001) {
            strArr = new String[(size * 2) + 4];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr[2] = Integer.toString(20001);
            strArr[3] = Integer.toString(21000);
            d = iil.d(list, strArr, 4, hiAggregateOption, false);
        } else {
            strArr = new String[(size * 2) + 3];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr[2] = Integer.toString(alignType);
            d = iil.d(list, strArr, 3, hiAggregateOption, false);
        }
        return iih.bAc_(this.d.rawQuery(d, strArr), hiAggregateOption.getConstantsKey());
    }

    public List<HiHealthData> c(List<Integer> list, HiAggregateOption hiAggregateOption, ifl iflVar) {
        String d;
        List<HiHealthData> bzY_;
        int[] d2;
        String[] b;
        String str;
        String[] strArr;
        int i;
        String a2;
        int i2;
        int size = list.size();
        int[] type = hiAggregateOption.getType();
        int i3 = type[0];
        HiHealthDataType.Category e2 = HiHealthDataType.e(i3);
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int i4 = AnonymousClass1.b[e2.ordinal()];
        if (i4 == 1) {
            if (i3 <= 21000) {
                return d(list, hiAggregateOption, true);
            }
            if (i3 <= 22099) {
                return a(list, hiAggregateOption, true);
            }
            return b(list, hiAggregateOption, true);
        }
        if (i4 == 2) {
            String[] strArr2 = new String[size + type.length + 3 + (hiAggregateOption.getModifiedEndTime() > 0 ? 2 : 0)];
            strArr2[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr2[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr2[2] = Integer.toString(0);
            int b2 = b(strArr2, 3, hiAggregateOption);
            if (i3 < 2000) {
                d = iil.b(list, strArr2, b2, hiAggregateOption, true);
                bzY_ = iih.bAa_(this.d.rawQuery(b(d, iflVar), strArr2), constantsKey);
            } else if (HiHealthDataType.x(i3)) {
                d = iil.a(list, strArr2, b2, hiAggregateOption, true);
                bzY_ = iih.bzY_(this.d.rawQuery(b(d, iflVar), strArr2), constantsKey);
            } else {
                d = iil.d(list, strArr2, b2, hiAggregateOption, true, iflVar);
                bzY_ = iih.bzY_(this.d.rawQuery(d, strArr2), constantsKey);
            }
            LogUtil.c("Debug_RawQueryManager", "queryMergeAggregateDataNoAlignType-point: sql= " + d, " ,param=" + HiJsonUtil.e(d));
            return bzY_;
        }
        if (i4 == 3) {
            String[] strArr3 = new String[size + 4];
            strArr3[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr3[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr3[2] = Long.toString(type[0]);
            strArr3[3] = Integer.toString(0);
            String c = iil.c(list, strArr3, 4, hiAggregateOption, true);
            LogUtil.c("Debug_RawQueryManager", "queryMergeAggregateDataNoAlignType-sequence: sql= " + c, " ,param=" + HiJsonUtil.e(c));
            return iih.bAd_(this.d.rawQuery(c, strArr3), constantsKey);
        }
        List<HiHealthData> list2 = null;
        if (i4 != 4) {
            return null;
        }
        HiHealthDictType d3 = HiHealthDictManager.d((Context) null).d(i3);
        if (10006 == i3 || d3 != null) {
            if (d3 != null) {
                List<Integer> g = HiHealthDictManager.d((Context) null).g(i3);
                List<String> e3 = HiHealthDictManager.d((Context) null).e(i3);
                if (i3 == DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()) {
                    g.add(2018);
                    e3.add("rest_heart_rate");
                }
                d2 = CommonUtil.b(g);
                b = (String[]) e3.toArray(new String[0]);
            } else {
                d2 = HiHealthDataType.d(i3);
                b = HiHealthDataKey.b(i3);
            }
            String[] strArr4 = b;
            hiAggregateOption.setType(d2);
            hiAggregateOption.setConstantsKey(strArr4);
            int count = hiAggregateOption.getCount();
            String filter = hiAggregateOption.getFilter();
            if (count > 0 && filter == null) {
                hiAggregateOption.setCount(0);
            }
            if (filter == null || "".equals(filter)) {
                str = filter;
                strArr = new String[size + d2.length + 3 + (hiAggregateOption.getModifiedEndTime() > 0 ? 2 : 0)];
                strArr[0] = Long.toString(hiAggregateOption.getStartTime());
                strArr[1] = Long.toString(hiAggregateOption.getEndTime());
                int b3 = b(strArr, 2, hiAggregateOption);
                strArr[b3] = Integer.toString(0);
                i = count;
                a2 = iil.a(list, strArr, b3 + 1, hiAggregateOption, true, (String) null, iflVar);
            } else {
                if ("NULL".equals(filter) || "-1".equals(filter)) {
                    str = filter;
                    strArr = new String[size + d2.length + 3 + (hiAggregateOption.getModifiedEndTime() > 0 ? 2 : 0)];
                    strArr[0] = Long.toString(hiAggregateOption.getStartTime());
                    strArr[1] = Long.toString(hiAggregateOption.getEndTime());
                    int b4 = b(strArr, 2, hiAggregateOption);
                    strArr[b4] = Integer.toString(0);
                    i2 = count;
                    a2 = iil.a(list, strArr, b4 + 1, hiAggregateOption, true, str, iflVar);
                } else {
                    strArr = new String[size + d2.length + 4 + (hiAggregateOption.getModifiedEndTime() > 0 ? 2 : 0)];
                    strArr[0] = Long.toString(hiAggregateOption.getStartTime());
                    strArr[1] = Long.toString(hiAggregateOption.getEndTime());
                    int b5 = b(strArr, 2, hiAggregateOption);
                    strArr[b5] = Integer.toString(0);
                    strArr[b5 + 1] = filter;
                    i2 = count;
                    str = filter;
                    a2 = iil.a(list, strArr, b5 + 2, hiAggregateOption, true, str, iflVar);
                }
                i = i2;
            }
            LogUtil.c("Debug_RawQueryManager", "queryMergeAggregateDataNoAlignType-set: sql= " + a2, " ,param=" + HiJsonUtil.e(a2));
            List<HiHealthData> bAi_ = iih.bAi_(this.d.rawQuery(a2, strArr), strArr4);
            a(i3, bAi_);
            if (i3 == 10006 && str == null) {
                return a(bAi_, i);
            }
            list2 = bAi_;
        }
        if (i3 < 10010 || i3 > 10060) {
            return list2;
        }
        List<HiHealthData> j = j(list, hiAggregateOption);
        a(i3, j);
        return j;
    }

    /* renamed from: ijt$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            b = iArr;
            try {
                iArr[HiHealthDataType.Category.SESSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[HiHealthDataType.Category.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[HiHealthDataType.Category.SEQUENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[HiHealthDataType.Category.SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static int b(String[] strArr, int i, HiAggregateOption hiAggregateOption) {
        if (hiAggregateOption.getModifiedEndTime() <= 0) {
            return i;
        }
        strArr[i] = Long.toString(hiAggregateOption.getModifiedStartTime());
        strArr[i + 1] = Long.toString(hiAggregateOption.getModifiedEndTime());
        return i + 2;
    }

    private List<HiHealthData> j(List<Integer> list, HiAggregateOption hiAggregateOption) {
        int[] d;
        String[] b;
        int h = iis.d().h(iis.d().j(list.get(0).intValue()));
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(Integer.valueOf(h));
        int[] type = hiAggregateOption.getType();
        hiAggregateOption.setType(type);
        if (HiHealthDataType.m(type[0])) {
            d = HiHealthDataType.d(10010);
            b = HiHealthDataKey.b(10010);
        } else {
            d = HiHealthDataType.d(type[0]);
            b = HiHealthDataKey.b(type[0]);
        }
        hiAggregateOption.setConstantsKey(b);
        hiAggregateOption.setType(d);
        hiAggregateOption.setFilter(String.valueOf(type[0]));
        String[] strArr = new String[arrayList.size() + d.length + 3];
        strArr[0] = Long.toString(HiDateUtil.c(hiAggregateOption.getStartTime()));
        strArr[1] = Long.toString(HiDateUtil.c(hiAggregateOption.getEndTime()));
        strArr[2] = Integer.toString(2);
        return iih.bAh_(this.d.rawQuery(iil.a(arrayList, strArr, 3, hiAggregateOption), strArr), b, false);
    }

    public List<HiHealthData> h(List<Integer> list, HiAggregateOption hiAggregateOption) {
        int size = list.size();
        int[] type = hiAggregateOption.getType();
        int i = type[0];
        HiHealthDataType.Category e2 = HiHealthDataType.e(i);
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        if (AnonymousClass1.b[e2.ordinal()] == 2) {
            String[] strArr = new String[size + type.length + 3];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr[2] = Integer.toString(0);
            if (i < 2000) {
                String e3 = iii.e(list, strArr, 3, hiAggregateOption, true);
                Cursor rawQuery = this.d.rawQuery(e3, strArr);
                LogUtil.c("Debug_RawQueryManager", "queryMergeSportPointAggregateDataNoAlignTypeByTimeZone() aggregateSQL = ", e3, ",selectAgs = ", HiJsonUtil.e(strArr));
                return iih.bAa_(rawQuery, constantsKey);
            }
        }
        return null;
    }

    private void a(int i, List<HiHealthData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            it.next().setType(i);
        }
    }

    private List<HiHealthData> a(List<HiHealthData> list, int i) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("Debug_RawQueryManager", "datas is null");
            return list;
        }
        LogUtil.c("Debug_RawQueryManager", "filterMainUserIDData enter datas =", list);
        String b = ijz.c().b(iis.d().j(list.get(0).getClientId()));
        if (b == null) {
            LogUtil.h("Debug_RawQueryManager", "huid is null");
            return list;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            String metaData = hiHealthData.getMetaData();
            if (metaData == null || metaData.length() == 0 || Constants.NULL.equalsIgnoreCase(metaData) || "0".equals(metaData) || metaData.equals(b)) {
                arrayList.add(hiHealthData);
            }
        }
        if (i > 0) {
            if (arrayList.size() <= i) {
                i = arrayList.size();
            }
            for (int i2 = 0; i2 < i; i2++) {
                arrayList2.add((HiHealthData) arrayList.get(i2));
            }
        } else {
            arrayList2.addAll(arrayList);
        }
        LogUtil.c("Debug_RawQueryManager", "filterMainUserIDData datas=", arrayList2);
        return arrayList2;
    }

    public List<HiHealthData> b(List<Integer> list, long j, long j2, int i, int i2, String[] strArr, int[] iArr, int i3) {
        String[] strArr2 = new String[list.size() + 5];
        strArr2[0] = Long.toString(j);
        strArr2[1] = Long.toString(j2);
        strArr2[2] = Integer.toString(i2);
        strArr2[3] = Integer.toString(0);
        strArr2[4] = Integer.toString(2);
        String a2 = iil.a(list, strArr2, 5, i, i2, strArr, iArr, i3);
        LogUtil.c("Debug_RawQueryManager", "queryMergeAggregateHealthPointDataNoAlignTypeEX() aggregateSQL = ", a2, ",selectAgs = ", HiJsonUtil.e(strArr2));
        return iih.bzY_(this.d.rawQuery(a2, strArr2), strArr);
    }

    public List<HiHealthData> d(List<Integer> list, long j, long j2, int i, int i2, String[] strArr, int[] iArr, int i3, double d) {
        String[] strArr2 = new String[list.size() + 5];
        strArr2[0] = Long.toString(j);
        strArr2[1] = Long.toString(j2);
        strArr2[2] = Integer.toString(i2);
        strArr2[3] = Integer.toString(0);
        strArr2[4] = Integer.toString(2);
        String b = iii.b(list, strArr2, 5, i, i2, strArr, iArr, i3, d);
        LogUtil.c("Debug_RawQueryManager", "queryMergeAggregateHealthPointDataNoAlignTypeEX() aggregateSQL = ", b, ",selectAgs = ", HiJsonUtil.e(strArr2));
        return iih.bzY_(this.d.rawQuery(b, strArr2), strArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x019a A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x018b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.hihealth.HiHealthData> d(java.util.List<java.lang.Integer> r23, com.huawei.hihealth.HiAggregateOption r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijt.d(java.util.List, com.huawei.hihealth.HiAggregateOption, boolean):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x01b7 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.hihealth.HiHealthData> a(java.util.List<java.lang.Integer> r23, com.huawei.hihealth.HiAggregateOption r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijt.a(java.util.List, com.huawei.hihealth.HiAggregateOption, boolean):java.util.List");
    }

    private String[] a(List<Integer> list, HiAggregateOption hiAggregateOption, int i) {
        String[] strArr = new String[list.size() + 9];
        strArr[0] = Integer.toString(i);
        strArr[1] = Long.toString(hiAggregateOption.getStartTime());
        strArr[2] = Long.toString(hiAggregateOption.getEndTime());
        strArr[3] = Integer.toString(0);
        strArr[4] = Integer.toString(i);
        strArr[5] = Integer.toString(i);
        strArr[6] = Long.toString(hiAggregateOption.getStartTime());
        strArr[7] = Long.toString(hiAggregateOption.getEndTime());
        strArr[8] = Integer.toString(0);
        return strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0207 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.hihealth.HiHealthData> b(java.util.List<java.lang.Integer> r25, com.huawei.hihealth.HiAggregateOption r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijt.b(java.util.List, com.huawei.hihealth.HiAggregateOption, boolean):java.util.List");
    }

    public List<HiHealthData> d(List<Integer> list, HiAggregateOption hiAggregateOption, ifl iflVar) {
        String d;
        List<HiHealthData> bzZ_;
        int[] d2;
        String[] b;
        int i;
        String str;
        String[] strArr;
        String a2;
        int size = list.size();
        int[] type = hiAggregateOption.getType();
        int i2 = type[0];
        int i3 = AnonymousClass1.b[HiHealthDataType.e(i2).ordinal()];
        if (i3 == 1) {
            if (i2 <= 21000) {
                return d(list, hiAggregateOption, false);
            }
            if (i2 <= 22099) {
                return a(list, hiAggregateOption, false);
            }
            return b(list, hiAggregateOption, false);
        }
        if (i3 == 2) {
            String[] strArr2 = new String[size + type.length + 3];
            strArr2[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr2[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr2[2] = Integer.toString(2);
            if (i2 < 2000) {
                d = iil.b(list, strArr2, 3, hiAggregateOption, false);
                bzZ_ = iih.bAc_(this.d.rawQuery(b(d, iflVar), strArr2), hiAggregateOption.getConstantsKey());
            } else if (i2 < 2019 || (2022 <= i2 && 2033 >= i2)) {
                d = iil.d(list, strArr2, 3, hiAggregateOption, false, null);
                bzZ_ = iih.bzZ_(this.d.rawQuery(b(d, iflVar), strArr2), hiAggregateOption.getConstantsKey());
            } else if (HiHealthDataType.x(i2)) {
                d = iil.a(list, strArr2, 3, hiAggregateOption, false);
                bzZ_ = iih.bzZ_(this.d.rawQuery(b(d, iflVar), strArr2), hiAggregateOption.getConstantsKey());
            } else {
                d = iil.d(list, strArr2, 3, hiAggregateOption, false, null);
                bzZ_ = iih.bzY_(this.d.rawQuery(b(d, iflVar), strArr2), hiAggregateOption.getConstantsKey());
            }
            LogUtil.c("Debug_RawQueryManager", "aggregateSQL=", d, "-selectAgs-", HiJsonUtil.e(strArr2));
            LogUtil.c("Debug_RawQueryManager", "aggregateSqlBuilder= " + b(d, iflVar));
            return bzZ_;
        }
        if (i3 == 3) {
            String[] strArr3 = new String[size + 3];
            strArr3[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr3[1] = Long.toString(hiAggregateOption.getEndTime());
            strArr3[2] = Long.toString(type[0]);
            return iih.bAd_(this.d.rawQuery(iil.c(list, strArr3, 3, hiAggregateOption, false), strArr3), hiAggregateOption.getConstantsKey());
        }
        List<HiHealthData> list2 = null;
        if (i3 != 4) {
            return null;
        }
        HiHealthDictType d3 = HiHealthDictManager.d((Context) null).d(i2);
        if (10006 == i2 || d3 != null) {
            if (d3 != null) {
                List<Integer> g = HiHealthDictManager.d((Context) null).g(i2);
                List<String> e2 = HiHealthDictManager.d((Context) null).e(i2);
                if (i2 == DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()) {
                    g.add(2018);
                    e2.add("rest_heart_rate");
                }
                d2 = CommonUtil.b(g);
                b = (String[]) e2.toArray(new String[0]);
            } else {
                d2 = HiHealthDataType.d(i2);
                b = HiHealthDataKey.b(i2);
            }
            String[] strArr4 = b;
            hiAggregateOption.setType(d2);
            hiAggregateOption.setConstantsKey(strArr4);
            int count = hiAggregateOption.getCount();
            String filter = hiAggregateOption.getFilter();
            if (count > 0 && filter == null) {
                hiAggregateOption.setCount(0);
            }
            if (filter == null || "".equals(filter)) {
                i = count;
                str = filter;
                strArr = new String[size + d2.length + 2];
                strArr[0] = Long.toString(hiAggregateOption.getStartTime());
                strArr[1] = Long.toString(hiAggregateOption.getEndTime());
                a2 = iil.a(list, strArr, 2, hiAggregateOption, false, (String) null, iflVar);
            } else if ("NULL".equals(filter)) {
                strArr = new String[size + d2.length + 2];
                strArr[0] = Long.toString(hiAggregateOption.getStartTime());
                strArr[1] = Long.toString(hiAggregateOption.getEndTime());
                i = count;
                str = filter;
                a2 = iil.a(list, strArr, 2, hiAggregateOption, false, str, iflVar);
            } else {
                i = count;
                str = filter;
                String[] strArr5 = new String[size + d2.length + 3];
                strArr5[0] = Long.toString(hiAggregateOption.getStartTime());
                strArr5[1] = Long.toString(hiAggregateOption.getEndTime());
                strArr5[2] = str;
                strArr = strArr5;
                a2 = iil.a(list, strArr5, 3, hiAggregateOption, false, str, iflVar);
            }
            LogUtil.c("Debug_RawQueryManager", "aggregateSQL=", a2, "-selectAgs-", HiJsonUtil.e(strArr));
            List<HiHealthData> bAi_ = iih.bAi_(this.d.rawQuery(a2, strArr), strArr4);
            a(i2, bAi_);
            if (i2 == 10006 && str == null) {
                return a(bAi_, i);
            }
            list2 = bAi_;
        }
        if (i2 < 10010 || i2 > 10060) {
            return list2;
        }
        List<HiHealthData> j = j(list, hiAggregateOption);
        a(i2, j);
        return j;
    }

    public List<HiHealthData> a(List<Integer> list, HiAggregateOption hiAggregateOption) {
        int size = list.size();
        int[] type = hiAggregateOption.getType();
        int i = type[0];
        if (AnonymousClass1.b[HiHealthDataType.e(i).ordinal()] == 2) {
            String[] strArr = new String[size + type.length + 2];
            strArr[0] = Long.toString(hiAggregateOption.getStartTime());
            strArr[1] = Long.toString(hiAggregateOption.getEndTime());
            if (i < 2000) {
                String e2 = iii.e(list, strArr, 2, hiAggregateOption, false);
                List<HiHealthData> bAc_ = iih.bAc_(this.d.rawQuery(e2, strArr), hiAggregateOption.getConstantsKey());
                LogUtil.c("Debug_RawQueryManager", "queryAggregateDataNoAlignTypeByTimeZone() aggregateSQL = ", e2, ",selectAgs = ", HiJsonUtil.e(strArr));
                return bAc_;
            }
        }
        return null;
    }

    public List<HiHealthData> a(int i, HiAggregateOption hiAggregateOption, ifl iflVar) {
        String[] strArr = new String[hiAggregateOption.getType().length + 4];
        boolean z = false;
        strArr[0] = Integer.toString(HiDateUtil.c(hiAggregateOption.getStartTime()));
        strArr[1] = Integer.toString(HiDateUtil.c(hiAggregateOption.getEndTime()));
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        String b = iil.b(hiAggregateOption, strArr, 4, iflVar);
        LogUtil.c("Debug_RawQueryManager", "queryAggregateStatData aggregateSQL= ", b);
        Cursor rawQuery = this.d.rawQuery(b, strArr);
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        if (iflVar != null && iflVar.f()) {
            z = true;
        }
        return iih.bAh_(rawQuery, constantsKey, z);
    }

    public List<HiHealthData> a(int i, HiSportStatDataAggregateOption hiSportStatDataAggregateOption) {
        int length = hiSportStatDataAggregateOption.getType().length;
        int[] hiHealthTypes = hiSportStatDataAggregateOption.getHiHealthTypes();
        int i2 = 0;
        String[] strArr = new String[length + 3 + ((hiHealthTypes == null || hiHealthTypes.length <= 0) ? 0 : hiHealthTypes.length)];
        strArr[0] = Integer.toString(HiDateUtil.c(hiSportStatDataAggregateOption.getStartTime()));
        strArr[1] = Integer.toString(HiDateUtil.c(hiSportStatDataAggregateOption.getEndTime()));
        strArr[2] = Integer.toString(i);
        String e2 = iil.e(hiSportStatDataAggregateOption, strArr, 3);
        LogUtil.c("Debug_RawQueryManager", "aggregateSql is ", e2, "selectArgs is ", strArr);
        Cursor rawQuery = this.d.rawQuery(e2, strArr);
        try {
            i2 = Integer.parseInt(hiSportStatDataAggregateOption.getFilter());
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("HiH_RawQueryManager", "queryAggregateSportStatData NumberFormatException");
        }
        return iih.bAg_(i2, rawQuery, hiSportStatDataAggregateOption.getConstantsKey());
    }

    public List<HiHealthData> e(int i, List<Integer> list, HiAggregateOption hiAggregateOption, boolean z) {
        int[] type = hiAggregateOption.getType();
        String[] strArr = new String[type.length + 1 + list.size()];
        strArr[0] = Integer.toString(i);
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        String e2 = iil.e(hiAggregateOption.getCount(), type, constantsKey, list, strArr, hiAggregateOption.getSortOrder(), 1);
        Cursor rawQuery = this.d.rawQuery(e2, strArr);
        LogUtil.c("Debug_RawQueryManager", "queryStatDataByDays sql=", e2, ", selectAgs=", HiJsonUtil.e(strArr));
        return iih.bAF_(rawQuery, constantsKey, z);
    }

    public List<HiHealthData> c(List<Integer> list, HiAggregateOption hiAggregateOption) {
        String[] strArr = new String[list.size() + hiAggregateOption.getType().length + 3];
        strArr[0] = Long.toString(hiAggregateOption.getStartTime());
        strArr[1] = Long.toString(hiAggregateOption.getEndTime());
        strArr[2] = Integer.toString(0);
        return iih.bAa_(this.d.rawQuery(iil.e(list, strArr, 3, hiAggregateOption, false), strArr), hiAggregateOption.getConstantsKey());
    }

    public List<Integer> e(long j, long j2, int i, List<Integer> list) {
        String[] strArr = new String[list.size() + 3];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        StringBuffer stringBuffer = new StringBuffer((list.size() * 2) + 128);
        stringBuffer.append("select distinct(client_id) from sample_point where start_time >=? and start_time <=? and type_id =? ");
        iil.a("client_id", list, stringBuffer, strArr, 3);
        return iih.bAJ_(this.d.rawQuery(stringBuffer.toString(), strArr), "client_id");
    }

    public List<Integer> a(long j, long j2, int i, List<Integer> list) {
        String[] strArr = new String[list.size() + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        StringBuffer stringBuffer = new StringBuffer((list.size() * 2) + 64);
        stringBuffer.append("select distinct(client_id) from sample_point_health_stress where start_time >=? and start_time <=? and type_id =? and sync_status !=? ");
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAJ_(this.d.rawQuery(stringBuffer.toString(), strArr), "client_id");
    }

    public boolean d(int i, int i2, int i3, String str) {
        return iih.bAv_(this.d.rawQuery(iil.b() + " and hihealth_permission.permission =? ", new String[]{Integer.toString(i), Integer.toString(i2), Integer.toString(i3), str}));
    }

    private String b(String str, ifl iflVar) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Debug_RawQueryManager", "transToWhereSql aggregateSQL null");
            return "";
        }
        if (iflVar == null || TextUtils.isEmpty(iflVar.c())) {
            return str;
        }
        try {
            HiDataFilter hiDataFilter = (HiDataFilter) HiJsonUtil.e(iflVar.c(), HiDataFilter.class);
            StringBuilder sb = new StringBuilder(16);
            sb.append("select * from (");
            sb.append(str);
            sb.append(") where ");
            sb.append(iil.c(hiDataFilter));
            return sb.toString();
        } catch (JsonSyntaxException unused) {
            throw new JsonSyntaxException("not HiDataFilter param");
        }
    }
}
