package defpackage;

import android.graphics.Bitmap;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule;
import com.huawei.animationkit.computationalwallpaper.generator.DynamicWallpaper;
import com.huawei.animationkit.computationalwallpaper.generator.ResolverResult;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.ColorPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.KaleidoscopePattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.LayerPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.VectorPattern;
import defpackage.aec;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class adc {
    private static final adc b = new adc();
    private final ada d = new ada();

    private adc() {
    }

    public static adc b() {
        return b;
    }

    public List<DynamicWallpaper> fN_(Bitmap bitmap) throws abv {
        try {
            ResolverResult fO_ = this.d.fO_(bitmap);
            return c(fO_, aec.e().c().selectTemplate(fO_));
        } catch (RuntimeException e) {
            Log.e("DynamicWallpaperGenerator", "generate dynamic wallpaper failed.", e);
            throw new abv(e);
        }
    }

    private List<DynamicWallpaper> c(ResolverResult resolverResult, List<aec.d> list) throws abv {
        ArrayList arrayList = new ArrayList();
        for (aec.d dVar : list) {
            Pattern c = c(resolverResult.getColorResult(), dVar);
            arrayList.add(new DynamicWallpaper(resolverResult, c, abs.b(c, resolverResult.getColorResult(), dVar)));
        }
        return arrayList;
    }

    private Pattern c(ColorResult colorResult, aec.d dVar) throws abv {
        Pattern b2;
        if (dVar.b().equals(VectorPattern.class)) {
            b2 = ado.e(dVar, c(colorResult, dVar.d()), dVar.a());
        } else if (dVar.b().equals(KaleidoscopePattern.class)) {
            b2 = ado.c(dVar.i(), c(colorResult, dVar.d()));
        } else if (dVar.b().equals(LayerPattern.class)) {
            b2 = ado.e(dVar.i(), colorResult);
        } else if (dVar.b().equals(ColorPattern.class)) {
            b2 = ado.a(dVar.i(), c(colorResult, dVar.d()));
        } else {
            b2 = ado.b(dVar.b().getName(), colorResult);
        }
        b2.setTag(dVar.j());
        return b2;
    }

    private ColorResult c(ColorResult colorResult, ColorRule colorRule) {
        return colorRule == null ? colorResult : colorRule.process(colorResult);
    }
}
