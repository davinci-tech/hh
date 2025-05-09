package defpackage;

import com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class abt implements ClockColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule
    public WatchFaceClock process(WatchFaceClock watchFaceClock, ColorResult colorResult, List<Integer> list) {
        int c;
        acf d = d(colorResult);
        int i = -1;
        if (d.b() <= 0.35f) {
            c = new acf(d.e(), d.d(), d.b() + 0.4f, d.a()).c();
        } else if (d.b() <= 0.7f) {
            c = d.c();
        } else {
            c = new acf(d.e(), d.d(), d.b() - 0.15f, d.a()).c();
            i = -16777216;
        }
        watchFaceClock.setColorList(Arrays.asList(Integer.valueOf(c), Integer.valueOf(i)));
        return watchFaceClock;
    }

    private acf d(ColorResult colorResult) {
        return aee.a(colorResult.getMainColors());
    }
}
