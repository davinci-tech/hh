package defpackage;

import android.text.TextUtils;
import com.huawei.ui.main.stories.template.BaseParser;

/* loaded from: classes7.dex */
public class ryd {
    public static BaseParser a(String str) {
        if (TextUtils.equals(str, "HealthDetailCommonTemplate")) {
            return new ryy();
        }
        if (TextUtils.equals(str, "SportTabConstructor")) {
            return new rzb();
        }
        if (TextUtils.equals(str, "PrivacyDetailTemplate")) {
            return new rsm();
        }
        return null;
    }
}
