package defpackage;

import android.graphics.Bitmap;
import androidx.palette.graphics.Palette;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class acd {
    public ColorResult fI_(Bitmap bitmap) {
        Palette.Builder from = Palette.from(bitmap);
        from.clearFilters();
        List<acf> list = (List) from.generate().getSwatches().stream().sorted(new Comparator() { // from class: acc
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return acd.d((Palette.Swatch) obj, (Palette.Swatch) obj2);
            }
        }).map(new Function() { // from class: ace
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return acd.c((Palette.Swatch) obj);
            }
        }).collect(Collectors.toList());
        ColorResult colorResult = new ColorResult();
        colorResult.setOriginalColors(list);
        colorResult.setMainColors(new ArrayList(list));
        return colorResult;
    }

    static /* synthetic */ int d(Palette.Swatch swatch, Palette.Swatch swatch2) {
        return -(swatch.getPopulation() - swatch2.getPopulation());
    }

    static /* synthetic */ acf c(Palette.Swatch swatch) {
        return new acf(swatch.getRgb(), swatch.getPopulation());
    }
}
