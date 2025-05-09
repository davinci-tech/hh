package defpackage;

import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule;
import java.util.Arrays;

/* loaded from: classes8.dex */
public class acw implements ColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule
    public ColorResult process(ColorResult colorResult) {
        acf b = b(colorResult);
        acf e = e(b);
        acf d = d(b);
        ColorResult colorResult2 = new ColorResult();
        colorResult2.setMainColors(Arrays.asList(e, d));
        colorResult2.setMainGradients(Arrays.asList(e, d));
        Log.i("DigitalWatchFaceColorRule", "result: " + colorResult2.getMainColors());
        return colorResult2;
    }

    private acf b(ColorResult colorResult) {
        return aee.a(colorResult.getMainColors());
    }

    private acf e(acf acfVar) {
        return (acfVar.b() <= 0.35f || acfVar.b() > 0.7f) ? acfVar : new acf(-16777216, acfVar.a());
    }

    private acf d(acf acfVar) {
        if (acfVar.b() <= 0.35f) {
            return new acf(acfVar.e(), acfVar.d(), acfVar.b() + 0.4f, acfVar.a());
        }
        return acfVar.b() <= 0.7f ? acfVar : new acf(-16777216, acfVar.a());
    }
}
