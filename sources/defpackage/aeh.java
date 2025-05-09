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
public class aeh implements TemplateSelector {
    private final Map<String, a> d;

    public aeh() {
        HashMap hashMap = new HashMap();
        this.d = hashMap;
        hashMap.put("Color", new a("Color", 5, "Other", 3));
        hashMap.put("Character", new a("Character", 5, "Other", 3));
        hashMap.put("Beeline", new a("Beeline", 5, "Plaid", 3));
    }

    @Override // com.huawei.animationkit.computationalwallpaper.template.TemplateSelector
    public List<aec.d> selectTemplate(ResolverResult resolverResult) throws abv {
        String e = resolverResult.getAiResult().c().get(0).e();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (this.d.containsKey(e)) {
            a aVar = this.d.get(e);
            linkedHashSet.addAll(e(aVar.b, aVar.f185a));
            linkedHashSet.addAll(e(aVar.c, aVar.e));
        } else {
            linkedHashSet.addAll(e(e, 8));
        }
        if (linkedHashSet.size() < 8) {
            linkedHashSet.addAll(e("Other", 8));
        }
        return new ArrayList(linkedHashSet).subList(0, Math.min(8, linkedHashSet.size()));
    }

    private List<aec.d> e(String str, int i) throws abv {
        ArrayList arrayList = new ArrayList(aec.e().a(str));
        Collections.shuffle(arrayList);
        return arrayList.subList(0, Math.min(i, arrayList.size()));
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private final int f185a;
        private final String b;
        private final String c;
        private final int e;

        private a(String str, int i, String str2, int i2) {
            this.b = str;
            this.c = str2;
            this.f185a = i;
            this.e = i2;
        }
    }
}
