package defpackage;

import com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class aca implements ClockColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule
    public WatchFaceClock process(WatchFaceClock watchFaceClock, ColorResult colorResult, List<Integer> list) {
        int i;
        int i2;
        acf c = c(colorResult);
        if (c.b() <= 0.35f) {
            i = new acf(c.e(), c.d(), c.b() + 0.4f, c.a()).c();
            i2 = -1;
        } else if (c.b() <= 0.7f) {
            i2 = c.c();
            i = c.c();
        } else {
            i = -16777216;
            i2 = -16777216;
        }
        watchFaceClock.setColorList(Arrays.asList(Integer.valueOf(i2), Integer.valueOf(i)));
        return watchFaceClock;
    }

    private acf c(ColorResult colorResult) {
        return aee.a(colorResult.getMainColors());
    }
}
