package defpackage;

import com.huawei.animationkit.computationalwallpaper.generator.ResolverResult;
import com.huawei.animationkit.computationalwallpaper.template.TemplateSelector;
import defpackage.aec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class aei implements TemplateSelector {
    @Override // com.huawei.animationkit.computationalwallpaper.template.TemplateSelector
    public List<aec.d> selectTemplate(ResolverResult resolverResult) throws abv {
        List<aec.d> a2 = a(resolverResult.getAiResult().c().get(0).e(), 6);
        if (a2.size() < 6) {
            a2.addAll(a("Other", 6 - a2.size()));
        }
        return a2;
    }

    private List<aec.d> a(String str, int i) throws abv {
        List<aec.d> a2 = aec.e().a(str);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < a2.size(); i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        Collections.shuffle(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < i && i3 < arrayList.size(); i3++) {
            arrayList2.add(a2.get(((Integer) arrayList.get(i3)).intValue()));
        }
        return arrayList2;
    }
}
