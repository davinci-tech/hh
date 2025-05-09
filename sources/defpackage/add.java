package defpackage;

import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class add implements ColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule
    public ColorResult process(ColorResult colorResult) {
        ColorResult colorResult2 = new ColorResult(colorResult);
        if (c(colorResult2)) {
            e(colorResult2);
        }
        if (d(colorResult2)) {
            a(colorResult2);
        }
        Log.i("FlowerColorRule", "input: " + colorResult);
        Log.i("FlowerColorRule", "output: " + colorResult2);
        return colorResult2;
    }

    private boolean c(ColorResult colorResult) {
        Iterator<acf> it = colorResult.getMainColors().iterator();
        while (it.hasNext()) {
            if (b(it.next())) {
                return true;
            }
        }
        Iterator<acf> it2 = colorResult.getAdditionalColors().iterator();
        while (it2.hasNext()) {
            if (b(it2.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean d(ColorResult colorResult) {
        Iterator<acf> it = colorResult.getMainColors().iterator();
        while (it.hasNext()) {
            if (c(it.next())) {
                return true;
            }
        }
        Iterator<acf> it2 = colorResult.getAdditionalColors().iterator();
        while (it2.hasNext()) {
            if (c(it2.next())) {
                return true;
            }
        }
        return false;
    }

    private void e(ColorResult colorResult) {
        colorResult.getMainColors().addAll(colorResult.getAdditionalColors());
        colorResult.getMainGradients().addAll(colorResult.getAdditionalGradients());
        colorResult.getAdditionalColors().clear();
        colorResult.getAdditionalGradients().clear();
        float f = 0.0f;
        int i = 0;
        for (int i2 = 0; i2 < colorResult.getMainColors().size(); i2++) {
            acf acfVar = colorResult.getMainColors().get(i2);
            if (b(acfVar)) {
                float d = acfVar.d() + (acfVar.b() * 1.4f);
                if (d > f) {
                    i = i2;
                    f = d;
                }
            }
        }
        Log.i("FlowerColorRule", "find flower color at " + i);
        a(colorResult.getMainColors(), i, 0);
        a(colorResult.getMainGradients(), i, 0);
    }

    private void a(ColorResult colorResult) {
        colorResult.getMainColors().addAll(colorResult.getAdditionalColors());
        colorResult.getMainGradients().addAll(colorResult.getAdditionalGradients());
        colorResult.getAdditionalColors().clear();
        colorResult.getAdditionalGradients().clear();
        float f = 0.0f;
        int i = 0;
        for (int i2 = 0; i2 < colorResult.getMainColors().size(); i2++) {
            acf acfVar = colorResult.getMainColors().get(i2);
            if (c(acfVar)) {
                float d = acfVar.d() + (acfVar.b() * 1.4f);
                if (d > f) {
                    i = i2;
                    f = d;
                }
            }
        }
        Log.i("FlowerColorRule", "find leaf color at " + i);
        a(colorResult.getMainColors(), i, colorResult.getMainColors().size() + (-1));
        a(colorResult.getMainGradients(), i, colorResult.getMainGradients().size() + (-1));
    }

    private boolean c(acf acfVar) {
        return acfVar.e() > 70.0f && acfVar.e() < 260.0f && ((double) acfVar.d()) > 0.08d;
    }

    private boolean b(acf acfVar) {
        return (acfVar.e() < 70.0f || acfVar.e() > 150.0f) && ((double) acfVar.d()) > 0.08d;
    }

    private void a(List<acf> list, int i, int i2) {
        acf acfVar = list.get(i);
        list.set(i, list.get(i2));
        list.set(i2, acfVar);
    }
}
