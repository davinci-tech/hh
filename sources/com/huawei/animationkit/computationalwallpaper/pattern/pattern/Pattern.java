package com.huawei.animationkit.computationalwallpaper.pattern.pattern;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcelable;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import defpackage.abv;
import java.io.File;
import java.util.List;
import java.util.Properties;

/* loaded from: classes8.dex */
public interface Pattern extends Parcelable {
    default void cancelAnimation() {
    }

    void draw(Canvas canvas);

    Rect getBounds();

    List<Integer> getFinalColors() throws abv;

    String getTag();

    default void goFirstFrame() {
    }

    default void load(File file, Properties properties, ColorResult colorResult) throws abv {
    }

    void releaseBitmap();

    default void save(File file, Properties properties) throws abv {
    }

    void setBounds(Rect rect);

    void setColor(ColorResult colorResult);

    void setResource(List<String> list) throws abv;

    void setTag(String str);

    default void setUpdateListener(UpdateListener updateListener) {
    }

    default void startAnimation() {
    }

    Bitmap toBitmap();
}
