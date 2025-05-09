package defpackage;

import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule;
import java.util.Collections;

/* loaded from: classes8.dex */
public class acy implements ColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule
    public ColorResult process(ColorResult colorResult) {
        acf b = b(b(colorResult));
        ColorResult colorResult2 = new ColorResult();
        colorResult2.setMainColors(Collections.singletonList(b));
        colorResult2.setMainGradients(Collections.singletonList(b));
        return colorResult2;
    }

    private acf b(ColorResult colorResult) {
        return aee.a(colorResult.getMainColors());
    }

    private acf b(acf acfVar) {
        return acfVar.b() <= 0.7f ? new acf(-16777216, acfVar.a()) : acfVar;
    }
}
