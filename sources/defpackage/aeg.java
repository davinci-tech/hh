package defpackage;

import com.huawei.animationkit.computationalwallpaper.generator.ResolverResult;
import com.huawei.animationkit.computationalwallpaper.template.TemplateSelector;
import defpackage.aec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class aeg implements TemplateSelector {
    private final Map<String, d> c;

    public aeg() {
        HashMap hashMap = new HashMap();
        this.c = hashMap;
        hashMap.put("Color", new d("Color", 6, "Other", 3));
        hashMap.put("Character", new d("Character", 6, "Other", 3));
        hashMap.put("Beeline", new d("Beeline", 6, "Plaid", 3));
    }

    @Override // com.huawei.animationkit.computationalwallpaper.template.TemplateSelector
    public List<aec.d> selectTemplate(ResolverResult resolverResult) throws abv {
        String e = resolverResult.getAiResult().c().get(0).e();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (this.c.containsKey(e)) {
            d dVar = this.c.get(e);
            linkedHashSet.addAll(a(dVar.f184a, dVar.e));
            linkedHashSet.addAll(a(dVar.d, dVar.c));
        } else {
            linkedHashSet.addAll(a(e, 9));
        }
        if (linkedHashSet.size() < 9) {
            linkedHashSet.addAll(a("Other", 9));
        }
        return new ArrayList(linkedHashSet).subList(0, Math.min(9, linkedHashSet.size()));
    }

    private List<aec.d> a(String str, int i) throws abv {
        ArrayList arrayList = new ArrayList(aec.e().a(str));
        Collections.shuffle(arrayList);
        return arrayList.subList(0, Math.min(i, arrayList.size()));
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private final String f184a;
        private final int c;
        private final String d;
        private final int e;

        private d(String str, int i, String str2, int i2) {
            this.f184a = str;
            this.d = str2;
            this.e = i;
            this.c = i2;
        }
    }
}
