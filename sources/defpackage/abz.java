package defpackage;

import androidx.core.graphics.ColorUtils;
import com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class abz implements ClockColorRule {
    @Override // com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule
    public WatchFaceClock process(WatchFaceClock watchFaceClock, ColorResult colorResult, List<Integer> list) {
        double[] dArr = new double[3];
        ColorUtils.colorToLAB(colorResult.getMainColors().get(0).c(), dArr);
        double d = dArr[0];
        if (d < 50.0d) {
            int LABToColor = ColorUtils.LABToColor(d > 40.0d ? d - 40.0d : 0.0d, dArr[1], dArr[2]);
            watchFaceClock.setColor(LABToColor, -1);
            watchFaceClock.setColorList(Arrays.asList(Integer.valueOf(LABToColor), -1));
        } else {
            int LABToColor2 = ColorUtils.LABToColor(d > 60.0d ? 100.0d : d + 40.0d, dArr[1], dArr[2]);
            watchFaceClock.setColor(-16777216, LABToColor2);
            watchFaceClock.setColorList(Arrays.asList(-16777216, Integer.valueOf(LABToColor2)));
        }
        return watchFaceClock;
    }
}
