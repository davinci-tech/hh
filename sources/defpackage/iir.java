package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iir {
    private ihy b;

    private iir() {
        this.b = ihy.c();
    }

    static class c {
        private static final iir d = new iir();
    }

    public static iir d() {
        return c.d;
    }

    public long b(int i, long j, int i2, long j2) {
        if (!a(i, j, i2, j2)) {
            return this.b.insert(iid.bzl_(i, j, i2, j2));
        }
        LogUtil.a("HiH_DataBusinessRelationManager", "insertBusinessDataRelation data exist.");
        return 0L;
    }

    public boolean a(int i, long j, int i2, long j2) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("business_type =? and business_id =? and relation_type =? and relation_id =? ");
        String[] strArr = {Integer.toString(i), Long.toString(j), Integer.toString(i2), Long.toString(j2)};
        LogUtil.c("HiH_DataBusinessRelationManager", "queryBusinessRelationExist() selector = ", stringBuffer, ",selectorAgs = ", HiJsonUtil.e(strArr));
        return iih.bAv_(this.b.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> a(int i, long j, int i2) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("relation_id =? and relation_type =? and business_type =? ");
        String[] strArr = {Long.toString(j), Integer.toString(i), Integer.toString(i2)};
        LogUtil.c("HiH_DataBusinessRelationManager", "queryBusinessIdByRelationId() selector = ", stringBuffer, ",selectAgs = ", HiJsonUtil.e(strArr));
        return iih.bAn_(this.b.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<HiHealthData> d(long j, int i) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("business_id =? and business_type =? ");
        return iih.bAn_(this.b.query(stringBuffer.toString(), new String[]{Long.toString(j), Long.toString(i)}, null, null, null));
    }

    public int b(long j) {
        return this.b.delete("relation_id =? ", new String[]{Long.toString(j)});
    }
}
