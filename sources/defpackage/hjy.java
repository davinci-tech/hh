package defpackage;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.util.Pair;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class hjy {
    public static int a(float[] fArr) {
        if (fArr == null) {
            LogUtil.a("SkippingPerformanceUtils", "getPerformanceOfBestIndex, ranks is null");
            return -1;
        }
        float[] fArr2 = new float[5];
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        Arrays.sort(fArr2);
        LogUtil.a("SkippingPerformanceUtils", "ranks = ", Arrays.toString(fArr), ", tempRanks = ", Arrays.toString(fArr2));
        for (int i = 0; i < fArr.length; i++) {
            if (fArr[i] == fArr2[4]) {
                LogUtil.a("SkippingPerformanceUtils", "The index of the best performance score is ", Integer.valueOf(i));
                return i;
            }
        }
        return -1;
    }

    public static Pair<Integer, String> a(int i) {
        String string;
        int i2;
        Context context = BaseApplication.getContext();
        Resources resources = context.getResources();
        if (i != 0) {
            string = "";
            i2 = 0;
            if (i == 1) {
                string = resources.getQuantityString(R.plurals._2130903233_res_0x7f0300c1, 0, "");
                i2 = R.string._2130847688_res_0x7f0227c8;
            } else if (i == 2) {
                string = resources.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, "");
                i2 = R.string._2130847690_res_0x7f0227ca;
            } else if (i == 3) {
                string = resources.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, "");
                i2 = R.string._2130847689_res_0x7f0227c9;
            } else if (i == 4) {
                string = resources.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, "");
                i2 = R.string._2130847687_res_0x7f0227c7;
            }
        } else {
            string = context.getString(R.string._2130843710_res_0x7f02183e);
            i2 = R.string._2130847686_res_0x7f0227c6;
        }
        return new Pair<>(Integer.valueOf(i2), string);
    }

    public static String e(int i) {
        int i2 = i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? 0 : R.string._2130847683_res_0x7f0227c3 : R.string._2130847685_res_0x7f0227c5 : R.string._2130847658_res_0x7f0227aa : R.string._2130847684_res_0x7f0227c4 : R.string._2130847682_res_0x7f0227c2;
        return i2 == 0 ? "" : BaseApplication.getContext().getString(i2);
    }
}
