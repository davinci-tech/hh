package com.huawei.animationkit.computationalwallpaper.pattern.arranger;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcelable;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import com.huawei.animationkit.computationalwallpaper.pattern.shape.Shape;
import defpackage.abv;
import java.io.File;
import java.util.List;
import java.util.Properties;

/* loaded from: classes8.dex */
public interface Arranger extends Parcelable {
    void arrange(Canvas canvas);

    default void cancelAnimation() {
    }

    List<Integer> getFinalColors() throws abv;

    default void goFirstFrame() {
    }

    default void load(File file, Properties properties, ColorResult colorResult) throws abv {
    }

    void releaseBitmap();

    default void save(File file, Properties properties) throws abv {
    }

    void setBounds(Rect rect);

    void setColor(ColorResult colorResult);

    void setResource(String str);

    void setShape(Shape shape);

    default void setUpdateListener(UpdateListener updateListener) {
    }

    default void startAnimation() {
    }
}
