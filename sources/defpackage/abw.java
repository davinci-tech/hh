package defpackage;

import com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class abw implements ClockColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule
    public WatchFaceClock process(WatchFaceClock watchFaceClock, ColorResult colorResult, List<Integer> list) {
        if (a(colorResult).b() <= 0.7f) {
            watchFaceClock.setColorList(Collections.singletonList(-1));
        } else {
            watchFaceClock.setColorList(Collections.singletonList(-16777216));
        }
        return watchFaceClock;
    }

    private acf a(ColorResult colorResult) {
        return aee.a(colorResult.getMainColors());
    }
}
