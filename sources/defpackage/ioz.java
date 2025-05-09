package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes7.dex */
public class ioz {
    public static final String d = BaseApplication.getAppPackage();

    public static int a(int i) {
        int i2 = 1;
        if (i != 1 && i != 2) {
            i2 = 3;
            if (i != 3) {
                return 2;
            }
        }
        return i2;
    }
}
