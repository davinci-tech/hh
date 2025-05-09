package defpackage;

import android.content.Context;
import com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyDayDataView;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyDoubleGroupDataView;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyGroupDataView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rsk {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Class> f16900a = new HashMap<String, Class>(16) { // from class: rsk.5
        private static final long serialVersionUID = -6059043297075236209L;

        {
            put("privacy_group_data_view", PrivacyGroupDataView.class);
            put("privacy_double_group_data_view", PrivacyDoubleGroupDataView.class);
            put("privacy_day_data_view", PrivacyDayDataView.class);
        }
    };

    public static <T extends BaseComponent> T a(String str, Context context) {
        return (T) b(f16900a.get(str), context);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T extends com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent> T b(java.lang.Class<T> r5, android.content.Context r6) {
        /*
            java.lang.String r0 = "PrivacyComponentProvider"
            r1 = 0
            if (r5 == 0) goto L1d
            if (r6 == 0) goto L1d
            r2 = 1
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L14
            java.lang.Class<android.content.Context> r3 = android.content.Context.class
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.NoSuchMethodException -> L14
            java.lang.reflect.Constructor r5 = r5.getConstructor(r2)     // Catch: java.lang.NoSuchMethodException -> L14
            goto L1e
        L14:
            java.lang.String r5 = "ClassNotFoundException or NoSuchMethodException:"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
        L1d:
            r5 = r1
        L1e:
            if (r5 == 0) goto L35
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch: java.lang.Throwable -> L2c
            java.lang.Object r5 = r5.newInstance(r6)     // Catch: java.lang.Throwable -> L2c
            com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent r5 = (com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent) r5     // Catch: java.lang.Throwable -> L2c
            r1 = r5
            goto L35
        L2c:
            java.lang.String r5 = "IllegalAccessException or InstantiationException or InvocationTargetException"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
        L35:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rsk.b(java.lang.Class, android.content.Context):com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent");
    }
}
