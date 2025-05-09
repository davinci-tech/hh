package defpackage;

import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class ack implements ColorPipelineNode {
    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode
    public void process(ColorResult colorResult) {
        Log.i("ColorFilter", "before filter: " + colorResult.getMainColors().size() + " " + colorResult.getMainColors());
        List<acf> list = (List) colorResult.getMainColors().stream().filter(new Predicate() { // from class: acp
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ack.b((acf) obj);
            }
        }).collect(Collectors.toList());
        if (list.size() > 0) {
            colorResult.setMainColors(list);
        } else {
            Log.w("ColorFilter", "no color after filter");
        }
        Log.i("ColorFilter", "after filter: " + colorResult.getMainColors().size() + " " + colorResult.getMainColors());
    }

    static /* synthetic */ boolean b(acf acfVar) {
        return (acfVar.d() >= 0.2f || acfVar.b() <= 0.85f) && acfVar.b() >= 0.2f;
    }
}
