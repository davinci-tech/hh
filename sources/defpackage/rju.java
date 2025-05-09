package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.R$string;

/* loaded from: classes7.dex */
public class rju {

    /* renamed from: a, reason: collision with root package name */
    public static final String f16792a = BaseApplication.getAppPackage();

    public static class c {
        public static String b(int i) {
            Context context = BaseApplication.getContext();
            if (i == 2) {
                return context.getString(R$string.IDS_privacy_measure_with_wrong_posture);
            }
            if (i == 3) {
                return context.getString(R$string.IDS_privacy_measure_no_remain_stationary);
            }
            if (i == 4) {
                return context.getString(R$string.IDS_privacy_measure_after_exercise);
            }
            if (i == 5) {
                return context.getString(R$string.IDS_privacy_measure_wear_wrongly);
            }
            if (i != 9) {
                return i != 10 ? "" : context.getString(R$string.IDS_privacy_measure_wear_loose);
            }
            return context.getString(R$string.IDS_privacy_measure_abnomal_heart_rate);
        }
    }
}
