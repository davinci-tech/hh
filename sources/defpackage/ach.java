package defpackage;

import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class ach {

    /* renamed from: a, reason: collision with root package name */
    private final List<ColorPipelineNode> f170a = new ArrayList();

    public ach c(ColorPipelineNode colorPipelineNode) {
        this.f170a.add(colorPipelineNode);
        return this;
    }

    public ColorResult d(ColorResult colorResult) {
        Iterator<ColorPipelineNode> it = this.f170a.iterator();
        while (it.hasNext()) {
            it.next().process(colorResult);
            if (abo.f()) {
                colorResult.addHistory(new ArrayList(colorResult.getMainColors()));
            }
        }
        return colorResult;
    }
}
