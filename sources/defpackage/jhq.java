package defpackage;

import com.huawei.datatype.FitnessUserInfo;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes5.dex */
public class jhq {
    private static cwe l(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        return new cwl().a((d == null || d.length() <= 4) ? null : d.substring(4, d.length()));
    }

    public static UserInfomation b(byte[] bArr) throws cwg {
        return jht.d(l(bArr));
    }

    public static jhw a(byte[] bArr) throws cwg {
        return jht.c(l(bArr));
    }

    public static jhz g(byte[] bArr) throws cwg {
        return jhp.e(l(bArr));
    }

    public static jhv d(byte[] bArr) throws cwg {
        return jhp.b(l(bArr));
    }

    public static int j(byte[] bArr) throws bmk {
        return jhr.c(bArr);
    }

    public static jic h(byte[] bArr) throws bmk {
        return jhr.b(bArr);
    }

    public static int m(byte[] bArr) throws bmk {
        return jhs.c(bArr);
    }

    public static jif i(byte[] bArr) throws bmk {
        return jhs.e(bArr);
    }

    public static FitnessUserInfo n(byte[] bArr) throws cwg {
        return jht.b(l(bArr));
    }

    public static int c(byte[] bArr) throws cwg {
        return jhr.a(l(bArr));
    }

    public static jhy e(byte[] bArr) throws cwg {
        return jhr.b(l(bArr));
    }

    public static HeartRateThresholdConfig f(byte[] bArr) throws cwg {
        return jhr.c(l(bArr));
    }
}
