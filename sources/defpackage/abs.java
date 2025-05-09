package defpackage;

import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import defpackage.aec;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class abs {
    private static final Random c = new Random();

    public static WatchFaceClock b(Pattern pattern, ColorResult colorResult, aec.d dVar) throws abv {
        WatchFaceClock watchFaceClock;
        aec.e a2 = a(colorResult, dVar);
        if (abo.a().equals(new Locale(MLAsrConstants.LAN_ZH, "", "").getLanguage())) {
            watchFaceClock = new WatchFaceClock(a2.e(), a2.d());
        } else {
            watchFaceClock = new WatchFaceClock(a2.e(), a2.c());
        }
        if (a2.b() != null && abo.g()) {
            a2.b().process(watchFaceClock, colorResult, pattern.getFinalColors());
        }
        return watchFaceClock;
    }

    private static aec.e a(final ColorResult colorResult, aec.d dVar) throws abv {
        if (dVar.c().isEmpty()) {
            throw new abv("clock is not configured");
        }
        List list = (List) dVar.c().stream().filter(new Predicate() { // from class: abr
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean d;
                d = abs.d(ColorResult.this, (aec.e) obj);
                return d;
            }
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new abv("clock is not configured");
        }
        return (aec.e) list.get(c.nextInt(list.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(ColorResult colorResult, aec.e eVar) {
        return abo.g() ? eVar.a() == null : aee.a(colorResult.getMainColors()).b() <= 0.7f ? "light".equals(eVar.a()) || "dark|light".equals(eVar.a()) : "dark".equals(eVar.a()) || "dark|light".equals(eVar.a());
    }
}
