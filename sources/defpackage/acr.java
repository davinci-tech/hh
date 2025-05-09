package defpackage;

import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class acr implements ColorPipelineNode {
    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode
    public void process(ColorResult colorResult) {
        List<acf> mainColors = colorResult.getMainColors();
        final int intValue = ((Integer) mainColors.stream().map(new Function() { // from class: acq
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((acf) obj).a());
            }
        }).reduce(new BinaryOperator() { // from class: acv
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                int sum;
                sum = Integer.sum(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                return Integer.valueOf(sum);
            }
        }).orElse(0)).intValue();
        List<acf> list = (List) mainColors.stream().filter(new Predicate() { // from class: acz
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return acr.e(intValue, (acf) obj);
            }
        }).sorted(new Comparator() { // from class: acx
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return acr.a((acf) obj, (acf) obj2);
            }
        }).collect(Collectors.toList());
        if (list.size() > 3) {
            list = list.subList(0, 3);
        }
        colorResult.setMainColors(list);
    }

    static /* synthetic */ boolean e(int i, acf acfVar) {
        return (((float) acfVar.a()) * 100.0f) / ((float) i) > 1.0f;
    }

    static /* synthetic */ int a(acf acfVar, acf acfVar2) {
        return -Float.compare(acfVar.a(), acfVar2.a());
    }
}
