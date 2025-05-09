package defpackage;

import com.huawei.ui.main.stories.oa.goodthingsrecommended.api.GoodThingsRecommendedApi;

/* loaded from: classes9.dex */
public class rjd {
    private static volatile GoodThingsRecommendedApi b;
    private static final Object c = new Object();

    public static GoodThingsRecommendedApi b() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new rjc();
                }
            }
        }
        return b;
    }
}
