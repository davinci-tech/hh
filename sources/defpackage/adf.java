package defpackage;

import android.graphics.Bitmap;
import com.huawei.animationkit.computationalwallpaper.graphicanalysis.AiAnalysisStrategy;

/* loaded from: classes8.dex */
public class adf {

    /* renamed from: a, reason: collision with root package name */
    private final AiAnalysisStrategy f171a;

    public adf(AiAnalysisStrategy aiAnalysisStrategy) {
        this.f171a = aiAnalysisStrategy;
    }

    public adj fV_(Bitmap bitmap) throws abv {
        return this.f171a.analyze(bitmap);
    }
}
