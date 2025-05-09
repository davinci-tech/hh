package defpackage;

import com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class abx implements ClockColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule
    public WatchFaceClock process(WatchFaceClock watchFaceClock, ColorResult colorResult, List<Integer> list) {
        int i;
        int i2;
        acf b = b(colorResult);
        if (b.b() <= 0.35f) {
            i = new acf(b.e(), b.d(), b.b() + 0.4f, b.a()).c();
            i2 = -1;
        } else if (b.b() <= 0.7f) {
            i2 = b.c();
            i = b.c();
        } else {
            i = -16777216;
            i2 = -16777216;
        }
        watchFaceClock.setColorList(Arrays.asList(Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i2)));
        return watchFaceClock;
    }

    private acf b(ColorResult colorResult) {
        return aee.a(colorResult.getMainColors());
    }
}
