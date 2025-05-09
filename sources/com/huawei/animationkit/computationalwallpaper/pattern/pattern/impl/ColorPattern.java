package com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.ColorPattern;
import com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat;
import defpackage.abv;
import defpackage.aep;
import defpackage.aeq;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

/* loaded from: classes8.dex */
public class ColorPattern extends AbstractPattern {
    public static final Parcelable.Creator<ColorPattern> CREATOR = new Parcelable.Creator<ColorPattern>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.ColorPattern.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: gf_, reason: merged with bridge method [inline-methods] */
        public ColorPattern createFromParcel(Parcel parcel) {
            return new ColorPattern(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ColorPattern[] newArray(int i) {
            return new ColorPattern[i];
        }
    };
    private static final String RESOURCE = "resource";
    private static final String TAG = "ColorPattern";
    private ColorResult mColorResult;
    private String mResource;
    private Bitmap mResourceBitmap;
    private UpdateListener mUpdateListener;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void releaseBitmap() {
    }

    public ColorPattern() {
    }

    protected ColorPattern(Parcel parcel) {
        super(parcel);
        this.mColorResult = (ColorResult) parcel.readParcelable(ColorResult.class.getClassLoader());
        this.mResource = parcel.readString();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setBounds(Rect rect) {
        super.setBounds(rect);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setColor(ColorResult colorResult) {
        this.mColorResult = colorResult;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void draw(Canvas canvas) {
        if (this.mResourceBitmap == null && !this.mResource.isEmpty()) {
            this.mResourceBitmap = getResourceBitmap();
        }
        canvas.drawColor(getBgColor());
        Bitmap bitmap = this.mResourceBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, new Rect(0, 0, this.mResourceBitmap.getWidth(), this.mResourceBitmap.getHeight()), getBounds(), (Paint) null);
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public List<Integer> getFinalColors() {
        return Collections.singletonList(Integer.valueOf(getBgColor()));
    }

    private int getBgColor() {
        return this.mColorResult.getMainColors().get(0).c();
    }

    private int getScaleColor() {
        return this.mColorResult.getMainColors().get(1).c();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void save(File file, Properties properties) throws abv {
        super.save(file, properties);
        properties.setProperty(RESOURCE, this.mResource);
        this.mColorResult.save(properties);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void load(File file, Properties properties, ColorResult colorResult) throws abv {
        super.load(file, properties, colorResult);
        this.mColorResult = colorResult;
        this.mResource = properties.getProperty(RESOURCE, "");
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setUpdateListener(UpdateListener updateListener) {
        this.mUpdateListener = updateListener;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void startAnimation() {
        UpdateListener updateListener = this.mUpdateListener;
        if (updateListener != null) {
            updateListener.onUpdate();
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mColorResult, i);
        parcel.writeString(this.mResource);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setResource(List<String> list) throws abv {
        super.setResource(list);
        this.mResource = (String) Optional.ofNullable(list).map(new Function() { // from class: adw
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ColorPattern.lambda$setResource$0((List) obj);
            }
        }).orElse("");
    }

    public static /* synthetic */ String lambda$setResource$0(List list) {
        return (String) list.get(0);
    }

    private Bitmap getResourceBitmap() {
        try {
            List singletonList = Collections.singletonList(Integer.valueOf(getScaleColor()));
            VectorDrawableCompat e = aeq.e(this.mResource, null);
            aep.a(e, singletonList, singletonList);
            return aeq.gI_(e);
        } catch (abv unused) {
            Log.e(TAG, "load vector drawable failed");
            return Bitmap.createBitmap(getBounds().width(), getBounds().height(), Bitmap.Config.ARGB_8888);
        }
    }
}
