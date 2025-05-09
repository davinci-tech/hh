package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iix {

    /* renamed from: a, reason: collision with root package name */
    private ihp f13390a;

    private iix() {
        this.f13390a = ihp.c();
    }

    static class e {
        private static final iix e = new iix();
    }

    public static iix a(Context context) {
        return e.e;
    }

    public long e(HiHealthData hiHealthData, int i, int i2) {
        return this.f13390a.insert(iid.bzq_(hiHealthData, i, i2));
    }

    public int e(long j, long j2, int i) {
        return this.f13390a.delete(e(), e(j, j2, i, 2));
    }

    public List<HiHealthData> a(long j, long j2, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        String[] strArr = new String[size + 3];
        stringBuffer.append("start_time =? and end_time =? and sync_status !=? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        stringBuffer.append(" ORDER BY ").append("start_time").append(" ASC ");
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> e(HiDataReadOption hiDataReadOption, List<Integer> list, int i, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 6];
        stringBuffer.append("start_time >=? and start_time <=? and type_id >=? and type_id <=? and merged =? and sync_status !=? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        strArr[4] = Integer.toString(0);
        strArr[5] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 6);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public boolean b(HiDataReadOption hiDataReadOption, List<Integer> list, int i, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer(128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=? and type_id >=? and type_id <=? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAv_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> e(HiDataReadOption hiDataReadOption, List<Integer> list, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=?  and type_id =? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, List<Integer> list, int i, int i2) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 5];
        stringBuffer.append("start_time >=? and start_time <=? and type_id >=? and type_id <=? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(i2);
        strArr[4] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 5);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> c(HiDataReadOption hiDataReadOption, List<Integer> list, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=?  and type_id =? and merged =? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(0);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public int b(HiHealthData hiHealthData, int i, int i2) {
        ContentValues bzH_ = iid.bzH_(hiHealthData, i2);
        if (hiHealthData.getSyncStatus() == 1) {
            bzH_.remove("sync_status");
        }
        return this.f13390a.update(bzH_, e(), e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i, 2));
    }

    public int b(HiHealthData hiHealthData, int i) {
        ContentValues bzH_ = iid.bzH_(hiHealthData, i);
        if (hiHealthData.getSyncStatus() == 1) {
            bzH_.remove("sync_status");
        }
        return this.f13390a.update(bzH_, "_id =? ", new String[]{Long.toString(hiHealthData.getDataId())});
    }

    private boolean b(long j, long j2, int i) {
        return iih.bAv_(this.f13390a.query(e(), e(j, j2, i, 2), null, null, null));
    }

    public int d(long j, long j2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.f13390a.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public boolean a(HiHealthData hiHealthData, int i, int i2) {
        long e2;
        if (b(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i)) {
            e2 = b(hiHealthData, i2);
        } else {
            e2 = e(hiHealthData, i, i2);
        }
        return iil.a(e2);
    }

    private String[] e(long j, long j2, int i, int i2) {
        return new String[]{Long.toString(j), Long.toString(j2), Integer.toString(i), Integer.toString(i2)};
    }

    public List<HiHealthData> e(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("start_time >=? and start_time <=? ");
        iil.a("type_id", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> e(int i, List<Integer> list, int i2) {
        int size = list.size() + 2;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("client_id =? and sync_status =? ");
        iil.a("type_id", list, stringBuffer, strArr, 2);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, "start_time DESC  limit 0 ," + i2));
    }

    public List<HiHealthData> e(int i, List<Integer> list, long j, long j2) {
        int size = list.size() + 4;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        strArr[2] = Long.toString(j);
        strArr[3] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        stringBuffer.append("client_id =? and sync_status =? and start_time >=? and start_time <=? ");
        iil.a("type_id", list, stringBuffer, strArr, 4);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, "start_time DESC "));
    }

    public int c(long j, int i, int i2) {
        return this.f13390a.update(iid.bzI_(i, i2), "_id =? ", new String[]{Long.toString(j)});
    }

    public List<Integer> c(long j, long j2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? and sync_status !=? ");
        String[] strArr = {Long.toString(j), Long.toString(j2), Long.toString(2L)};
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> a(long j, long j2, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? and type_id =? and sync_status !=? ");
        String[] strArr = {Long.toString(j), Long.toString(j2), Long.toString(i), Long.toString(2L)};
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> c(long j, long j2, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? and sync_status !=? ");
        String[] strArr = new String[iArr.length + 3];
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Long.toString(2L);
        iil.a("type_id", iArr, stringBuffer, strArr, 3);
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<HiHealthData> b(List<Integer> list, int i, int i2, int i3) {
        if (list == null) {
            LogUtil.h("DataCoreSessionManager", "queryCoreSessonDataListByClientIdsWithOrder clientIds is null");
            return null;
        }
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", i, i2, i3)));
    }

    public List<HiHealthData> e(int i, int i2, List<Integer> list, int i3) {
        int size = list.size() + 2;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(i2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("type_id =? and sync_status =? ");
        iil.a("client_id", list, stringBuffer, strArr, 2);
        iij.a(stringBuffer, 0, i3);
        return iih.bAw_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int b(long j) {
        return this.f13390a.delete("_id =? ", new String[]{Long.toString(j)});
    }

    public List<Integer> c(List<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[list.size() + 1];
        strArr[0] = Integer.toString(0);
        iil.a("type_id", list, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<Integer> d(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[iArr.length + 1];
        strArr[0] = Integer.toString(2);
        iil.a("type_id", iArr, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "type_id");
        return iih.bAJ_(this.f13390a.query(stringBuffer.toString(), strArr, null, null, null), "type_id");
    }

    private String e() {
        return "start_time =? and end_time =? and client_id =? and sync_status !=? ";
    }
}
