package defpackage;

import com.huawei.haf.application.BaseApplication;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class djx {
    private static List<String> d = Arrays.asList("IndoorEquipDisplayActivity", "IndoorEquipLandDisplayActivity", "SportBaseActivity", "CoachActivity");

    public static String d() {
        return BaseApplication.wa_() == null ? "" : BaseApplication.wa_().getClass().getSimpleName();
    }

    public static boolean b() {
        return d.contains(d());
    }
}
