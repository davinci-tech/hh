package defpackage;

import android.graphics.Bitmap;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.generator.ResolverResult;
import com.huawei.animationkit.computationalwallpaper.graphicanalysis.impl.AiStrategyImpl;

/* loaded from: classes8.dex */
public class ada {
    private final acb b;
    private final adf e;

    public ada() {
        ach achVar = new ach();
        achVar.c(new ack()).c(new acm()).c(new acs()).c(new acr()).c(new acl());
        this.b = new acb(achVar);
        this.e = new adf(AiStrategyImpl.d());
    }

    public ResolverResult fO_(Bitmap bitmap) throws abv {
        ResolverResult resolverResult = new ResolverResult(this.b.fH_(bitmap), this.e.fV_(bitmap));
        Log.i("ImageResolver", resolverResult.getColorResult().toString());
        Log.i("ImageResolver", resolverResult.getAiResult().toString());
        return resolverResult;
    }
}
