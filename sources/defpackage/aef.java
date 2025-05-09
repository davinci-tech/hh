package defpackage;

import com.huawei.animationkit.computationalwallpaper.generator.ResolverResult;
import com.huawei.animationkit.computationalwallpaper.template.TemplateSelector;
import defpackage.aec;
import java.util.List;

/* loaded from: classes8.dex */
public class aef implements TemplateSelector {
    @Override // com.huawei.animationkit.computationalwallpaper.template.TemplateSelector
    public List<aec.d> selectTemplate(ResolverResult resolverResult) throws abv {
        return aec.e().a(resolverResult.getAiResult().c().get(0).e());
    }
}
