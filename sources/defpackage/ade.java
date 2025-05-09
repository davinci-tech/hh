package defpackage;

import android.util.Log;
import androidx.core.graphics.ColorUtils;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule;

/* loaded from: classes8.dex */
public class ade implements ColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule
    public ColorResult process(ColorResult colorResult) {
        ColorResult colorResult2 = new ColorResult(colorResult);
        acf acfVar = colorResult2.getMainColors().get(0);
        if (colorResult2.getMainColors().size() == 3) {
            colorResult2.getMainColors().set(colorResult2.getMainColors().size() - 1, c(acfVar));
        } else {
            colorResult2.getAdditionalColors().set(colorResult2.getAdditionalColors().size() - 1, c(acfVar));
        }
        Log.i("PlaidColorRule", "input: " + colorResult2);
        Log.i("PlaidColorRule", "output: " + colorResult2);
        return colorResult2;
    }

    private acf c(acf acfVar) {
        double[] dArr = new double[3];
        ColorUtils.colorToLAB(acfVar.c(), dArr);
        return new acf(ColorUtils.LABToColor(Math.min(dArr[0] - 20.0d, 0.0d), dArr[1], dArr[2]), acfVar.a());
    }
}
