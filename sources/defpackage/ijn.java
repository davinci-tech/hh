package defpackage;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ijn {
    private ihr e;

    private ijn() {
        this.e = ihr.c();
    }

    static class c {
        private static final ijn d = new ijn();
    }

    public static ijn a(Context context) {
        return c.d;
    }

    public int c(long j, long j2, int i) {
        LogUtil.c("Debug_HealthDataSessionManager", "deleteSessionData() startTime = ", Long.valueOf(j), ",endTime = ", Long.valueOf(j2), ",clientID = ", Integer.valueOf(i));
        return this.e.delete(d(), e(j, j2, i, 2));
    }

    public long d(HiHealthData hiHealthData, int i, int i2) {
        return this.e.insert(iid.bzq_(hiHealthData, i, i2));
    }

    public List<HiHealthData> d(long j, long j2, List<Integer> list) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 64);
        String[] strArr = new String[size + 3];
        stringBuffer.append("start_time =? and end_time =? and sync_status !=? ");
        strArr[0] = Long.toString(j);
        strArr[1] = Long.toString(j2);
        strArr[2] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        stringBuffer.append(" ORDER BY ").append("start_time").append(" ASC ");
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, List<Integer> list, List<Integer> list2) {
        int size = list.size();
        int size2 = list2.size() + size + 3;
        String[] strArr = new String[size2];
        StringBuffer stringBuffer = new StringBuffer((size2 * 2) + 64);
        stringBuffer.append("start_time >=? and start_time <=? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 3);
        iil.a("type_id", list2, stringBuffer, strArr, size + 3);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> a(HiDataReadOption hiDataReadOption, List<Integer> list, List<Integer> list2) {
        int size = list.size();
        int size2 = list2.size() + size + 4;
        String[] strArr = new String[size2];
        StringBuffer stringBuffer = new StringBuffer((size2 * 2) + 64);
        stringBuffer.append("start_time >=? and start_time <=? and merged =? and sync_status !=? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(0);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        iil.a("type_id", list2, stringBuffer, strArr, size + 4);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public boolean b(HiDataReadOption hiDataReadOption, List<Integer> list, List<Integer> list2) {
        int size = list.size();
        String[] strArr = new String[list2.size() + size + 2];
        StringBuffer stringBuffer = new StringBuffer(64);
        stringBuffer.append("start_time >=? and start_time <=? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        iil.a("client_id", list, stringBuffer, strArr, 2);
        iil.a("type_id", list2, stringBuffer, strArr, size + 2);
        return iih.bAv_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> b(HiDataReadOption hiDataReadOption, List<Integer> list, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 128);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=?  and type_id =? and merged != ?");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, List<Integer> list, int i) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size + 4];
        stringBuffer.append("start_time >=? and start_time <=?  and type_id =? and merged =? ");
        strArr[0] = Long.toString(hiDataReadOption.getStartTime());
        strArr[1] = Long.toString(hiDataReadOption.getEndTime());
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(0);
        iil.a("client_id", list, stringBuffer, strArr, 4);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public int d(long j, long j2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return this.e.update(contentValues, "_id =? and modified_time =? ", new String[]{Long.toString(j), Long.toString(j2)});
    }

    public boolean e(long j, long j2, int i) {
        return iih.bAv_(this.e.query(d(), e(j, j2, i, 2), null, null, null));
    }

    public boolean e(HiHealthData hiHealthData, int i, int i2) {
        long d;
        if (e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i)) {
            d = d(hiHealthData, i2);
        } else {
            d = d(hiHealthData, i, i2);
        }
        return iil.a(d);
    }

    public int d(HiHealthData hiHealthData, int i) {
        ContentValues bzH_ = iid.bzH_(hiHealthData, i);
        if (hiHealthData.getSyncStatus() == 1) {
            bzH_.remove("sync_status");
        }
        return this.e.update(bzH_, "_id =? ", new String[]{Long.toString(hiHealthData.getDataId())});
    }

    public int a(HiHealthData hiHealthData, int i) {
        return this.e.update(iid.bzH_(hiHealthData, i), d(), e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getClientId(), 2));
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
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> b(int i, List<Integer> list, int i2) {
        String[] strArr = new String[list.size() + 2];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        StringBuffer stringBuffer = new StringBuffer((list.size() * 2) + 32);
        stringBuffer.append("client_id =? and sync_status =? ");
        iil.a("type_id", list, stringBuffer, strArr, 2);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, "start_time DESC  limit 0 ," + i2));
    }

    public List<HiHealthData> a(int i, List<Integer> list, long j, long j2) {
        String[] strArr = new String[list.size() + 4];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        strArr[2] = Long.toString(j);
        strArr[3] = Long.toString(j2);
        StringBuffer stringBuffer = new StringBuffer((list.size() * 2) + 64);
        stringBuffer.append("client_id =? and sync_status =? and start_time >=? and start_time <=? ");
        iil.a("type_id", list, stringBuffer, strArr, 4);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, "start_time DESC "));
    }

    public int b(long j, int i, int i2) {
        return this.e.update(iid.bzI_(i, i2), "_id =? ", new String[]{Long.toString(j)});
    }

    public List<Integer> a(long j, long j2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("start_time >=? and start_time <=? and sync_status !=? ");
        String[] strArr = {Long.toString(j), Long.toString(j2), Long.toString(2L)};
        iil.b(stringBuffer, "client_id");
        return iih.bAJ_(this.e.query(stringBuffer.toString(), strArr, null, null, null), "client_id");
    }

    public List<HiHealthData> a(List<Integer> list, int i, int i2, int i3) {
        if (list == null) {
            LogUtil.h("Debug_HealthDataSessionManager", "queryHealthSessonDataListByClientIdsWithOrder() clientIds is null");
            return null;
        }
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((list.size() * 2) + 32);
        String[] strArr = new String[size];
        iil.a("client_id", list, stringBuffer, strArr, 0);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, iil.a("start_time", i, i2, i3)));
    }

    public List<HiHealthData> b(int i, int i2, List<Integer> list, int i3) {
        String[] strArr = new String[list.size() + 2];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(i2);
        StringBuffer stringBuffer = new StringBuffer((list.size() * 2) + 64);
        stringBuffer.append("type_id =? and sync_status =? ");
        iil.a("client_id", list, stringBuffer, strArr, 2);
        iij.a(stringBuffer, 0, i3);
        return iih.bAw_(this.e.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int d(long j) {
        return this.e.delete("_id =? ", new String[]{Long.toString(j)});
    }

    public List<Integer> a(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("sync_status =? ");
        String[] strArr = new String[iArr.length + 1];
        strArr[0] = Integer.toString(2);
        iil.a("type_id", iArr, stringBuffer, strArr, 1);
        iil.b(stringBuffer, "type_id");
        return iih.bAJ_(this.e.query(stringBuffer.toString(), strArr, null, null, null), "type_id");
    }

    private String d() {
        return "start_time =? and end_time =? and client_id =? and sync_status !=? ";
    }
}
