package defpackage;

import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ijk {

    /* renamed from: a, reason: collision with root package name */
    private ihk f13397a;

    private ijk() {
        this.f13397a = ihk.d();
    }

    static class c {
        private static final ijk d = new ijk();
    }

    public static ijk b() {
        return c.d;
    }

    public void d(long j, long j2, List<Integer> list, int i) {
        int[] m = HiHealthDataType.m();
        String[] strArr = new String[m.length + list.size() + 6];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        strArr[2] = Long.toString(System.currentTimeMillis());
        strArr[3] = Long.toString(j);
        strArr[4] = Long.toString(j2);
        strArr[5] = Integer.toString(0);
        String a2 = iil.a(m, list, strArr, 6);
        LogUtil.c("Debug_ExecSQLManager", "copySportPointDatas() sql = ", a2, ",selectAgs = ", HiJsonUtil.e(strArr));
        this.f13397a.execSQL(a2, strArr);
    }

    public void b(long j, long j2, List<Integer> list, int i) {
        String[] strArr = new String[list.size() + 8];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        strArr[2] = Long.toString(System.currentTimeMillis());
        strArr[3] = Long.toString(j);
        strArr[4] = Long.toString(j2);
        strArr[5] = Integer.toString(20001);
        strArr[6] = Integer.toString(21000);
        strArr[7] = Integer.toString(0);
        String b = iil.b(list, strArr, 8);
        LogUtil.c("Debug_ExecSQLManager", "copySportSessionDatas() sql = ", b, ",selectAgs = ", HiJsonUtil.e(strArr));
        this.f13397a.execSQL(b, strArr);
    }

    public void a(long j, long j2, int i, int i2, int i3) {
        String[] strArr = {Integer.toString(i), Integer.toString(i3), Integer.toString(0), Long.toString(System.currentTimeMillis()), Integer.toString(HiDateUtil.c(j)), Integer.toString(HiDateUtil.c(j2)), Integer.toString(40001), Integer.toString(41000), Integer.toString(i2)};
        String d = iil.d();
        LogUtil.c("Debug_ExecSQLManager", "copySportStatDatas() sql = ", d, ",selectAgs = ", HiJsonUtil.e(strArr));
        this.f13397a.execSQL(d, strArr);
    }

    public void a(long j, long j2, List<Integer> list, int i) {
        int[] iArr = {DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()};
        String[] strArr = new String[list.size() + 7];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        strArr[2] = Long.toString(System.currentTimeMillis());
        strArr[3] = Long.toString(j);
        strArr[4] = Long.toString(j2);
        strArr[5] = Integer.toString(0);
        String d = iil.d(iArr, list, strArr, 6);
        LogUtil.c("Debug_ExecSQLManager", "copyActiveHourPointDatas() sql = ", d, ",selectAgs = ", HiJsonUtil.e(strArr));
        this.f13397a.execSQL(d, strArr);
    }

    public void e(long j, long j2, int i, int i2, int i3) {
        String[] strArr = {Integer.toString(i), Integer.toString(i3), Integer.toString(0), Long.toString(System.currentTimeMillis()), Integer.toString(HiDateUtil.c(j)), Integer.toString(HiDateUtil.c(j2)), Integer.toString(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()), Integer.toString(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()), Integer.toString(i2)};
        String d = iil.d();
        LogUtil.c("Debug_ExecSQLManager", "copyActiveHourStatDatas() sql = ", d, ",selectAgs = ", HiJsonUtil.e(strArr));
        this.f13397a.execSQL(d, strArr);
    }
}
