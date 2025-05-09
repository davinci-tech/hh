package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.data.listener.HealthTrendListener;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class ikf {
    public static void a(List<String> list, int[] iArr, int i, HealthTrendListener healthTrendListener, boolean z) {
        ReleaseLogUtil.e("R_HealthTrendManager", "enter getHealthTrend dataTypes = ", list, ", periodTypes = ", Arrays.toString(iArr), ", timeOut = ", Integer.valueOf(i));
        if (healthTrendListener == null) {
            ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrend listener is null");
            return;
        }
        if (koq.b(list)) {
            ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrend dataTypes is null");
            healthTrendListener.onResult(2, new ArrayList());
        } else if (iArr == null || iArr.length == 0) {
            ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrend periodTypes is null");
            healthTrendListener.onResult(2, new ArrayList());
        } else if (i < 0) {
            ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrend timeOut is invalid");
            healthTrendListener.onResult(2, new ArrayList());
        } else {
            ika.e(list, iArr, i, healthTrendListener, z);
        }
    }

    public static void c(List<String> list, int[] iArr, int i, HealthTrendListener healthTrendListener, String str) {
        ReleaseLogUtil.e("R_HealthTrendManager", "enter getHealthTrendWithRecordDay dataTypes = ", list, ", periodTypes = ", Arrays.toString(iArr), ", timeOut = ", Integer.valueOf(i));
        if (healthTrendListener == null) {
            ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrendWithRecordDay listener is null");
            return;
        }
        if (koq.b(list)) {
            ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrendWithRecordDay dataTypes is null");
            healthTrendListener.onResult(2, new ArrayList());
        } else {
            if (iArr == null || iArr.length == 0) {
                ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrendWithRecordDay periodTypes is null");
                healthTrendListener.onResult(2, new ArrayList());
                return;
            }
            if (TextUtils.isEmpty(str) || !c(str)) {
                ReleaseLogUtil.d("R_HealthTrendManager", "getHealthTrendWithRecordDay recordDay is invalid");
                healthTrendListener.onResult(2, new ArrayList());
            }
            ika.d(list, iArr, i, healthTrendListener, str);
        }
    }

    private static boolean c(String str) {
        try {
            if (DateFormatUtil.c(Integer.parseInt(str)) == 0) {
                return false;
            }
            return !jdl.ad(r1);
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
