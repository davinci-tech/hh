package defpackage;

import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.ColorPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.KaleidoscopePattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.LayerPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.VectorPattern;
import defpackage.aec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class ado {
    public static Pattern c(String str, ColorResult colorResult) throws abv {
        KaleidoscopePattern kaleidoscopePattern = new KaleidoscopePattern();
        kaleidoscopePattern.setResource(Collections.singletonList(str));
        kaleidoscopePattern.setColor(colorResult);
        return kaleidoscopePattern;
    }

    public static Pattern e(aec.d dVar, ColorResult colorResult, String str) throws abv {
        VectorPattern vectorPattern = new VectorPattern();
        vectorPattern.setResource(Collections.singletonList(dVar.i()));
        vectorPattern.setColor(colorResult);
        vectorPattern.setAnimation(str);
        vectorPattern.setTransform(dVar.g(), dVar.h(), dVar.f(), dVar.e());
        return vectorPattern;
    }

    public static Pattern e(String str, ColorResult colorResult) throws abv {
        List<String> list = (List) Arrays.stream(str.split(",")).map(new Function() { // from class: adk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String trim;
                trim = ((String) obj).trim();
                return trim;
            }
        }).collect(Collectors.toList());
        LayerPattern layerPattern = new LayerPattern(list.size());
        layerPattern.setResource(list);
        layerPattern.setColor(colorResult);
        return layerPattern;
    }

    public static Pattern a(String str, ColorResult colorResult) throws abv {
        ColorPattern colorPattern = new ColorPattern();
        colorPattern.setResource(Collections.singletonList(str));
        colorPattern.setColor(colorResult);
        return colorPattern;
    }

    public static Pattern b(String str, ColorResult colorResult) throws abv {
        try {
            Pattern pattern = (Pattern) Class.forName(str).asSubclass(Pattern.class).newInstance();
            pattern.setColor(colorResult);
            return pattern;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            Log.e("PatternBuilder", "build pattern failed.", e);
            throw new abv(e);
        }
    }
}
