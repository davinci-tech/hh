package defpackage;

import android.R;

/* loaded from: classes9.dex */
public class ndf {
    private static int d(int i, int i2, int i3, int i4, int i5, int i6) {
        return i != 0 ? i : i2 < 11 ? i3 : i2 < 14 ? i4 : i2 < 24 ? i5 : i6;
    }

    public static int e(int i, int i2) {
        return d(i, i2, R.style.Theme, R.style.Theme.Holo, R.style.Theme.DeviceDefault, R.style.Theme.DeviceDefault.Light.DarkActionBar);
    }
}
