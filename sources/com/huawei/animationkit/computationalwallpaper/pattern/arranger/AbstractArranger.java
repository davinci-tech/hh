package com.huawei.animationkit.computationalwallpaper.pattern.arranger;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.shape.Shape;
import com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat;
import defpackage.abv;
import defpackage.aen;
import defpackage.aep;
import defpackage.aeq;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class AbstractArranger implements Arranger {
    private final Rect mBound = new Rect();
    private ColorResult mColor;
    private String mResource;
    private Shape mShape;

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setBounds(Rect rect) {
        this.mBound.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setShape(Shape shape) {
        this.mShape = shape;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setColor(ColorResult colorResult) {
        this.mColor = colorResult;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setResource(String str) {
        this.mResource = str;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public List<Integer> getFinalColors() throws abv {
        ColorResult colorResult = this.mColor;
        if (colorResult == null) {
            return Collections.emptyList();
        }
        if (this.mResource != null) {
            return getResourceColors();
        }
        return colorResult.getAllColors();
    }

    protected Rect getBound() {
        return this.mBound;
    }

    protected Shape getShape() {
        return this.mShape;
    }

    protected ColorResult getColor() {
        return this.mColor;
    }

    protected String getResource() {
        return this.mResource;
    }

    protected Bitmap getResourceBitmap(aen aenVar) throws abv {
        VectorDrawableCompat e = aeq.e(this.mResource, aenVar);
        aep.a(e, this.mColor);
        return aeq.gI_(e);
    }

    protected List<Integer> getResourceColors() throws abv {
        String str = this.mResource;
        if (str == null) {
            throw new abv("resource is not set");
        }
        return aep.a(aeq.e(str, null), this.mColor);
    }
}
