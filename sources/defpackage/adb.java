package defpackage;

import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule;
import java.util.Arrays;

/* loaded from: classes8.dex */
public class adb implements ColorRule {
    private acf b(acf acfVar) {
        return acfVar;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule
    public ColorResult process(ColorResult colorResult) {
        acf d = d(colorResult);
        acf b = b(d);
        acf c = c(d);
        ColorResult colorResult2 = new ColorResult();
        colorResult2.setMainColors(Arrays.asList(b, c));
        colorResult2.setMainGradients(Arrays.asList(b, c));
        return colorResult2;
    }

    private acf d(ColorResult colorResult) {
        return aee.a(colorResult.getMainColors());
    }

    private acf c(acf acfVar) {
        if (acfVar.b() <= 0.7f) {
            return new acf(-1, acfVar.a());
        }
        return new acf(-16777216, acfVar.a());
    }
}
