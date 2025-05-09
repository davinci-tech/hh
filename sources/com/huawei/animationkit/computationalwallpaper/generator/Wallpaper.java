package com.huawei.animationkit.computationalwallpaper.generator;

import android.graphics.Bitmap;
import android.os.Parcelable;
import defpackage.abv;
import java.io.File;

/* loaded from: classes8.dex */
public interface Wallpaper extends Parcelable {
    WatchFaceClock getClock();

    String getTag();

    void save(File file) throws abv;

    Bitmap toBitmap();
}
