package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.operation.utils.Constants;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class csh {
    private List<csi> b;
    private boolean c;
    private HiHealthData e;

    public csh(HiHealthData hiHealthData) {
        this.e = hiHealthData;
    }

    public List<csi> b() {
        return this.b;
    }

    public void d(List<csi> list) {
        this.b = list;
    }

    public double d() {
        HiHealthData hiHealthData = this.e;
        if (hiHealthData != null) {
            return hiHealthData.getDouble("bodyWeight");
        }
        return 0.0d;
    }

    public String a() {
        HiHealthData hiHealthData = this.e;
        if (hiHealthData != null) {
            return UnitUtil.a("yyyy/MM/dd HH:mm", hiHealthData.getStartTime());
        }
        return null;
    }

    public long e() {
        HiHealthData hiHealthData = this.e;
        if (hiHealthData != null) {
            return hiHealthData.getStartTime();
        }
        return 0L;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof csh)) {
            return false;
        }
        csh cshVar = (csh) obj;
        if (TextUtils.isEmpty(a()) || TextUtils.isEmpty(cshVar.a())) {
            return false;
        }
        return a().equals(cshVar.a());
    }

    public int hashCode() {
        HiHealthData hiHealthData = this.e;
        if (hiHealthData != null) {
            return hiHealthData.hashCode();
        }
        return 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ClaimWeightDataBean{StartTime =");
        stringBuffer.append(a() == null ? Constants.NULL : a()).append("', mIsChoose ='");
        stringBuffer.append(this.c);
        stringBuffer.append(", mData ='").append(this.e.toString());
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public HiHealthData c() {
        return this.e;
    }

    public boolean f() {
        return this.c;
    }

    public void e(boolean z) {
        this.c = z;
    }
}
