package defpackage;

import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;

/* loaded from: classes8.dex */
public class acs implements ColorPipelineNode {
    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode
    public void process(ColorResult colorResult) {
        Log.i("ColorOptimizer", "before optimize: " + colorResult.getMainColors());
        for (int i = 0; i < colorResult.getMainColors().size(); i++) {
            acf acfVar = colorResult.getMainColors().get(i);
            float d = acfVar.d();
            if (Float.compare(d, 0.0f) != 0) {
                if (d < 0.3f) {
                    d *= 1.5f;
                }
                d = Math.max(Math.min(d, 0.9f), 0.1f);
            }
            float b = acfVar.b();
            if (b < 0.7f) {
                b *= 1.5f;
            }
            colorResult.getMainColors().set(i, new acf(acfVar.e(), d, Math.max(Math.min(b, 0.9f), 0.35f), acfVar.a()));
        }
        Log.i("ColorOptimizer", "after optimize: " + colorResult.getMainColors());
    }
}
