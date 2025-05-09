package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cux {
    private static final int[] e = {8, 15, 13, 11, 16, 20, 21, 18, 19, 34, 35, 44, 45};
    private static final int[] c = {8, 15, 13, 16, 20, 21, 18, 19, 34, 35, 44, 45};

    public static boolean e(int i) {
        for (int i2 : e) {
            if (i2 == i) {
                LogUtil.a("ProductType", "isSupportHeartRate() is true, productType is:", Integer.valueOf(i));
                return true;
            }
        }
        LogUtil.a("ProductType", "isSupportHeartRate() is false, productType is:", Integer.valueOf(i));
        return false;
    }

    public static int[] b() {
        return (int[]) e.clone();
    }
}
