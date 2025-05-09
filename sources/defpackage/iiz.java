package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiMetadataFilter;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class iiz {

    /* renamed from: a, reason: collision with root package name */
    private ihq f13392a;

    private static boolean a(int i) {
        return i == 31003 || i == 31004 || i == 31005 || i == 31006;
    }

    private static boolean d(int i) {
        return i == 31007;
    }

    private iiz() {
        this.f13392a = ihq.a();
    }

    static class b {
        private static final iiz c = new iiz();
    }

    public static iiz a(Context context) {
        return b.c;
    }

    public long a(HiHealthData hiHealthData, int i, int i2) {
        return this.f13392a.insert(iid.bzw_(hiHealthData, i, i2));
    }

    public List<HiHealthData> b(long j, long j2, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time =? and end_time =? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, null), false);
    }

    public List<HiHealthData> e(long j, long j2, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and end_time >=? and end_time <=? and type_id =? and merged != ?");
        String[] strArr = new String[size + 6];
        strArr[0] = Long.toString(HiDateUtil.s(j));
        strArr[1] = Long.toString(HiDateUtil.h(j));
        strArr[2] = Long.toString(HiDateUtil.s(j2));
        strArr[3] = Long.toString(HiDateUtil.h(j2));
        strArr[4] = Integer.toString(i);
        strArr[5] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 6);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, null), false);
    }

    private int c(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzL_ = iid.bzL_(hiHealthData, i, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzL_.remove("sync_status");
        }
        if (hiHealthData.getInt("merged") == 2) {
            bzL_.remove("merged");
        }
        return this.f13392a.update(bzL_, "_id =? ", new String[]{Long.toString(hiHealthData.getDataId())});
    }

    public int d(HiHealthData hiHealthData, int i) {
        return this.f13392a.update(iid.bzM_(hiHealthData), b(), e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i));
    }

    public long b(HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("merged", Integer.valueOf(i2));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.f13392a.update(contentValues, b(), e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i));
    }

    public long b(HiHealthData hiHealthData, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("meta_data", hiHealthData.getMetaData());
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.f13392a.update(contentValues, "_id =? ", new String[]{Long.toString(hiHealthData.getDataId())});
    }

    public long e(HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("sync_status", Integer.valueOf(i2));
        contentValues.put("meta_data", hiHealthData.getMetaData());
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.f13392a.update(contentValues, "_id =? ", new String[]{Long.toString(hiHealthData.getDataId())});
    }

    private boolean d(long j, long j2, int i, int i2) {
        return iih.bAv_(this.f13392a.byo_(b(), e(j, j2, i, i2), null, null, null));
    }

    public boolean d(HiHealthData hiHealthData, int i, int i2) {
        long a2;
        if (d(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i)) {
            a2 = c(hiHealthData, i, i2);
        } else {
            a2 = a(hiHealthData, i, i2);
        }
        return iil.a(a2);
    }

    private String[] e(long j, long j2, int i, int i2) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2), Integer.toString(2)};
    }

    public List<HiHealthData> b(List<Integer> list, long j, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time =? and type_id =? and sync_status !=? ");
        String[] strArr = new String[size + 3];
        strArr[0] = Long.toString(j);
        strArr[1] = Integer.toString(i);
        strArr[2] = Long.toString(2L);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        return iih.bAA_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> c(List<Integer> list, long j, long j2, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        stringBuffer.append("start_time =? and end_time =? and type_id =? and sync_status !=? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        strArr[3] = Long.toString(2L);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAA_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> e(List<Integer> list, long j, long j2, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and sync_status !=? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        strArr[3] = Long.toString(2L);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAA_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", 1, 0, 0)));
    }

    public List<HiHealthData> b(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        return c(list, hiDataReadOption, i, "");
    }

    public List<HiHealthData> c(List<Integer> list, HiDataReadOption hiDataReadOption, int i, String str) {
        int size = list.size();
        HiMetadataFilter hiMetadataFilter = (HiMetadataFilter) HiJsonUtil.e(str, HiMetadataFilter.class);
        int size2 = (hiMetadataFilter == null || hiMetadataFilter.getFilterOptions() == null) ? 0 : hiMetadataFilter.getFilterOptions().size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4 + size2];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        e(stringBuffer, strArr, 4, hiMetadataFilter);
        iil.a("client_id", list, stringBuffer, strArr, size2 + 4);
        LogUtil.c("Debug_DataSequenceManager", "queryMergeSequenceMataDatasByClientsWithOrder the sql is ", stringBuffer.toString(), strArr, ",selectionArgs = ", HiJsonUtil.e(strArr));
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), null);
    }

    private void e(StringBuffer stringBuffer, String[] strArr, int i, HiMetadataFilter hiMetadataFilter) {
        if (hiMetadataFilter == null) {
            return;
        }
        List<HiMetadataFilter.MetadataFilterExpression> filterOptions = hiMetadataFilter.getFilterOptions();
        if (koq.b(filterOptions)) {
            return;
        }
        stringBuffer.append(" and ").append(g4.k);
        for (int i2 = 0; i2 < filterOptions.size(); i2++) {
            if (i2 > 0) {
                stringBuffer.append(" and ");
            }
            stringBuffer.append("meta_data").append(" like ? ");
            strArr[i2 + i] = filterOptions.get(i2).toSqlString();
        }
        stringBuffer.append(g4.l);
    }

    public List<HiHealthData> c(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), false);
    }

    public List<HiHealthData> a(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        int i2;
        int size = list.size();
        StringBuffer c = c(i);
        String[] c2 = c(i, size, hiDataReadOption);
        if (d(i)) {
            i2 = 8;
        } else {
            i2 = a(i) ? 5 : 4;
        }
        iil.a("client_id", list, c, c2, i2);
        return iih.bAS_(this.f13392a.byo_(c.toString(), c2, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), null);
    }

    private StringBuffer c(int i) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? ");
        if (d(i)) {
            stringBuffer.append(" and ");
            for (int i2 = 0; i2 < 3; i2++) {
                stringBuffer.append("sub_type_id !=? and ");
            }
            stringBuffer.append("sub_type_id !=? ");
            return stringBuffer;
        }
        if (a(i)) {
            stringBuffer.append(" and sub_type_id =? ");
        }
        return stringBuffer;
    }

    private String[] c(int i, int i2, HiDataReadOption hiDataReadOption) {
        if (d(i)) {
            String[] strArr = new String[i2 + 8];
            strArr[0] = Long.toString(hiDataReadOption.getStartTime());
            strArr[1] = Long.toString(hiDataReadOption.getEndTime());
            strArr[2] = Integer.toString(0);
            strArr[3] = Integer.toString(31001);
            strArr[4] = b(31003);
            strArr[5] = b(31004);
            strArr[6] = b(31005);
            strArr[7] = b(31006);
            return strArr;
        }
        if (a(i)) {
            String[] strArr2 = new String[i2 + 5];
            strArr2[0] = Long.toString(hiDataReadOption.getStartTime());
            strArr2[1] = Long.toString(hiDataReadOption.getEndTime());
            strArr2[2] = Integer.toString(0);
            strArr2[3] = Integer.toString(31001);
            strArr2[4] = b(i);
            return strArr2;
        }
        String[] strArr3 = new String[i2 + 4];
        strArr3[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr3[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr3[2] = Integer.toString(0);
        strArr3[3] = Integer.toString(31001);
        return strArr3;
    }

    public List<HiHealthData> e(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("modified_time >=? and modified_time <=? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        LogUtil.c("Debug_DataSequenceManager", "queryMergeSequenceMataDatasByClientsWithOrderWithCreatetime the sql is ", stringBuffer.toString(), strArr, ",selectionArgs = ", HiJsonUtil.e(strArr));
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), null);
    }

    public List<HiHealthData> d(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        return d(list, hiDataReadOption, i, "");
    }

    public List<HiHealthData> d(List<Integer> list, HiDataReadOption hiDataReadOption, int i, String str) {
        int size = list.size();
        HiMetadataFilter hiMetadataFilter = (HiMetadataFilter) HiJsonUtil.e(str, HiMetadataFilter.class);
        int size2 = (hiMetadataFilter == null || hiMetadataFilter.getFilterOptions() == null) ? 0 : hiMetadataFilter.getFilterOptions().size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged != ?");
        String[] strArr = new String[size + 4 + size2];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        e(stringBuffer, strArr, 4, hiMetadataFilter);
        iil.a("client_id", list, stringBuffer, strArr, size2 + 4);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), null);
    }

    public List<HiHealthData> h(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("end_time >=? and end_time <=? and type_id =? and merged != ?");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("end_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), null);
    }

    public List<HiHealthData> j(List<Integer> list, HiDataReadOption hiDataReadOption, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("modified_time >=? and modified_time <=? and type_id =? and merged != ?");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())), null);
    }

    public List<String> a(List<Integer> list, long j, long j2, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAR_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", 1, 0, 0)));
    }

    public List<String> a(List<Integer> list, long j, long j2, int i, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? and sub_type_id =? ");
        String[] strArr = new String[size + 5];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        strArr[4] = Integer.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 5);
        return iih.bAR_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", 1, 0, 0)));
    }

    public List<HiHealthData> d(List<Integer> list, long j, long j2, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and type_id =? ");
        String[] strArr = new String[size + 4];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAB_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", 1, 0, 0)));
    }

    public List<HiHealthData> b(List<Integer> list, long j, long j2, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? ");
        String[] strArr = new String[size + 3];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, iil.a("start_time", 1, 0, 0)), null);
    }

    public List<HiHealthData> a(List<Integer> list) {
        String[] strArr = new String[list.size()];
        StringBuffer stringBuffer = new StringBuffer();
        iil.a("_id", list, stringBuffer, strArr, 0);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null), null);
    }

    public List<HiHealthData> d(int i, int i2, int[] iArr, int i3, long j, long j2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("client_id =? and type_id =? and sync_status =? and start_time >=? and start_time <=? ");
        String[] strArr = new String[iArr.length + 5];
        strArr[0] = Long.toString(i);
        strArr[1] = Long.toString(i2);
        strArr[2] = Long.toString(i3);
        strArr[3] = Long.toString(j);
        strArr[4] = Long.toString(j2);
        iij.d("sub_type_id", iArr, stringBuffer, strArr, 5);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, "start_time DESC "), true);
    }

    public List<HiHealthData> d(int i, int i2, int i3, int i4) {
        StringBuffer stringBuffer = new StringBuffer("start_time DESC  limit 0,");
        stringBuffer.append(i4);
        return iih.bAQ_(this.f13392a.query("client_id =? and type_id =? and sync_status =? ", new String[]{Integer.toString(i), Integer.toString(i2), Integer.toString(i3)}, null, null, stringBuffer.toString()), true);
    }

    public List<HiHealthData> e(List<Integer> list, int i, int i2, int i3) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("type_id =? and sync_status =? ");
        String[] strArr = new String[size + 2];
        strArr[0] = Long.toString(i);
        strArr[1] = Long.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 2);
        StringBuffer stringBuffer2 = new StringBuffer("start_time limit 0,");
        stringBuffer2.append(i3);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, stringBuffer2.toString()), true);
    }

    public List<HiHealthData> b(List<Integer> list, int[] iArr, int[] iArr2, int i, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[size + iArr.length + iArr2.length + 1];
        strArr[0] = Long.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 1);
        iil.a("sub_type_id", iArr2, stringBuffer, strArr, list.size() + 1);
        iil.a("type_id", iArr, stringBuffer, strArr, iArr2.length + list.size() + 1);
        StringBuffer stringBuffer2 = new StringBuffer("start_time DESC  limit 0,");
        stringBuffer2.append(i2);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, stringBuffer2.toString()), true);
    }

    public List<HiHealthData> d(List<Integer> list, int i, int[] iArr, int i2, int i3) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("type_id =? and sync_status =? ");
        String[] strArr = new String[size + iArr.length + 2];
        strArr[0] = Long.toString(i);
        strArr[1] = Long.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 2);
        iij.d("sub_type_id", iArr, stringBuffer, strArr, list.size() + 2);
        StringBuffer stringBuffer2 = new StringBuffer("start_time DESC  limit 0,");
        stringBuffer2.append(i3);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, stringBuffer2.toString()), true);
    }

    public boolean d(long j, long j2) {
        return iih.bAv_(this.f13392a.byo_("_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)}, null, null, null));
    }

    public int a(long j, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", (Integer) 1);
        return this.f13392a.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public List<HiHealthData> c(List<Integer> list, int i, int i2, int i3) {
        if (list == null) {
            LogUtil.h("Debug_DataSequenceManager", "querySequenceDataListByClientIdsWithOrder clientIds is null");
            return null;
        }
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", i, i2, i3)), true);
    }

    public List<HiHealthData> a(long j, long j2, int[] iArr, List<Integer> list, int i) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        if (i == 1) {
            stringBuffer.append("start_time >=? and start_time <=? ");
        } else {
            stringBuffer.append("start_time =? and end_time =? ");
        }
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null), null);
    }

    public List<HiHealthData> d(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("end_time >=? and end_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null), null);
    }

    public int e(HiHealthData hiHealthData) {
        String[] strArr = {Long.toString(hiHealthData.getDataId())};
        iwh.e(iwh.c(hiHealthData.getStartTime(), hiHealthData.getType(), hiHealthData.getClientId()));
        return this.f13392a.delete("_id =? ", strArr);
    }

    public int d(long j, int i, int i2) {
        return this.f13392a.update(iid.bzN_(i, i2), "_id =? ", new String[]{Long.toString(j)});
    }

    public int e(long j, int i) {
        return this.f13392a.update(iid.bzO_(i), "_id =? ", new String[]{Long.toString(j)});
    }

    public List<Integer> b(List<Integer> list) {
        if (list == null) {
            LogUtil.h("Debug_DataSequenceManager", "fetchSportTypeList clientIds is null");
            return null;
        }
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("select sub_type_id, min(start_time) as start_time  from sample_sequence  where ");
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        stringBuffer.append(" and type_id = ").append(30001);
        stringBuffer.append(" and merged = ").append(0);
        stringBuffer.append(" group by sub_type_id ");
        stringBuffer.append(" order by start_time ");
        return iih.bAV_(this.f13392a.rawQuery(stringBuffer.toString(), strArr));
    }

    public List<HiHealthData> a(List<Integer> list, HiDataReadOption hiDataReadOption) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and merged =? ");
        int[] type = hiDataReadOption.getType();
        int length = type == null ? 0 : type.length;
        String[] strArr = new String[size + 4 + length];
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(30001);
        strArr[3] = Integer.toString(0);
        if (length != 0) {
            iil.a("sub_type_id", type, stringBuffer, strArr, 4);
        }
        iil.a("client_id", list, stringBuffer, strArr, length + 4);
        String a2 = iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        LogUtil.c("Debug_DataSequenceManager", "selection is ", stringBuffer.toString(), " selectionArgs is ", strArr);
        return iih.bAS_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, a2), null);
    }

    public List<Integer> e(List<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[list.size() + 1];
        strArr[0] = Integer.toString(0);
        iil.a("type_id", list, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> d() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status =? or sync_status =? ");
        String[] strArr = {Integer.toString(0), Integer.toString(2)};
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> b(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer(64);
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[iArr.length + 1];
        strArr[0] = Integer.toString(2);
        iil.a("type_id", iArr, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "type_id");
        return iih.bAJ_(this.f13392a.byo_(stringBuffer.toString(), strArr, null, null, null), "type_id");
    }

    public List<HiHealthData> b(long j, int i, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time =? and type_id =? and sync_status !=? ");
        String[] strArr = new String[size + 3];
        strArr[0] = Long.toString(j);
        strArr[1] = Integer.toString(i);
        strArr[2] = Long.toString(2L);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        return iih.bAQ_(this.f13392a.query(stringBuffer.toString(), strArr, null, null, null), false);
    }

    private String b() {
        return "start_time =? and end_time =? and type_id =? and client_id =? and sync_status !=? ";
    }

    private String b(int i) {
        switch (i) {
            case 31003:
                return "1";
            case 31004:
                return "2";
            case 31005:
                return "4";
            case 31006:
                return "8";
            default:
                return "0";
        }
    }
}
