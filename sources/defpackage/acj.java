package defpackage;

import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import java.util.function.Function;

/* loaded from: classes8.dex */
public final /* synthetic */ class acj implements Function {
    public final /* synthetic */ ColorResult b;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String formatColor;
        formatColor = this.b.formatColor((acf) obj);
        return formatColor;
    }

    public /* synthetic */ acj(ColorResult colorResult) {
        this.b = colorResult;
    }
}
