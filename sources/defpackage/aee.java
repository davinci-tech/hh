package defpackage;

import android.graphics.Color;
import java.util.List;

/* loaded from: classes8.dex */
public class aee {
    public static int c(int i, float f) {
        Color valueOf = Color.valueOf(i);
        return Color.valueOf(valueOf.red(), valueOf.green(), valueOf.blue(), f).toArgb();
    }

    public static acf a(List<acf> list) {
        for (acf acfVar : list) {
            if (acfVar.d() >= 0.08f) {
                return acfVar;
            }
        }
        return list.get(0);
    }
}
