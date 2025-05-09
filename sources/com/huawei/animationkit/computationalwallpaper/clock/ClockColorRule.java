package com.huawei.animationkit.computationalwallpaper.clock;

import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock;
import java.util.List;

/* loaded from: classes8.dex */
public interface ClockColorRule {
    WatchFaceClock process(WatchFaceClock watchFaceClock, ColorResult colorResult, List<Integer> list);
}
