package com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.KaleidoscopeArranger;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.KaleidoscopeArranger2;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.KaleidoscopeArranger3;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType;
import defpackage.abv;
import defpackage.adx;
import defpackage.aeb;
import java.io.File;
import java.util.List;
import java.util.Properties;

/* loaded from: classes8.dex */
public class KaleidoscopePattern extends AbstractPattern {
    public static final Parcelable.Creator<KaleidoscopePattern> CREATOR = new Parcelable.Creator<KaleidoscopePattern>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.KaleidoscopePattern.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: gg_, reason: merged with bridge method [inline-methods] */
        public KaleidoscopePattern createFromParcel(Parcel parcel) {
            return new KaleidoscopePattern(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public KaleidoscopePattern[] newArray(int i) {
            return new KaleidoscopePattern[i];
        }
    };
    private static final String KALEIDOSCOPE_TYPE = "kaleidoscope_type";
    private static final String TAG = "KaleidoscopePattern";
    private Arranger mKaleidoscopeArranger;
    private int mKaleidoscopeType;
    private final adx mRoot;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public KaleidoscopePattern() {
        this.mRoot = new adx();
        this.mKaleidoscopeType = aeb.a().nextInt(VariableType.Random.RANDOM.getKey(), 0, 3);
        createArranger();
    }

    private KaleidoscopePattern(Parcel parcel) {
        super(parcel);
        adx adxVar = new adx();
        this.mRoot = adxVar;
        int readInt = parcel.readInt();
        this.mKaleidoscopeType = readInt;
        if (readInt == 0) {
            this.mKaleidoscopeArranger = (Arranger) parcel.readParcelable(KaleidoscopeArranger.class.getClassLoader());
        } else if (readInt == 1) {
            this.mKaleidoscopeArranger = (Arranger) parcel.readParcelable(KaleidoscopeArranger2.class.getClassLoader());
        } else {
            this.mKaleidoscopeArranger = (Arranger) parcel.readParcelable(KaleidoscopeArranger3.class.getClassLoader());
        }
        adxVar.c(this.mKaleidoscopeArranger);
    }

    private void createArranger() {
        int i = this.mKaleidoscopeType;
        if (i == 0) {
            this.mKaleidoscopeArranger = new KaleidoscopeArranger();
        } else if (i == 1) {
            this.mKaleidoscopeArranger = new KaleidoscopeArranger2();
        } else {
            this.mKaleidoscopeArranger = new KaleidoscopeArranger3();
        }
        this.mRoot.c(this.mKaleidoscopeArranger);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setBounds(Rect rect) {
        super.setBounds(rect);
        this.mRoot.setLocation(rect);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setColor(ColorResult colorResult) {
        this.mRoot.d(colorResult);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setResource(List<String> list) {
        this.mRoot.d(list.get(0));
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void draw(Canvas canvas) {
        this.mRoot.draw(canvas);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public List<Integer> getFinalColors() throws abv {
        return this.mKaleidoscopeArranger.getFinalColors();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void releaseBitmap() {
        this.mKaleidoscopeArranger.releaseBitmap();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void startAnimation() {
        this.mKaleidoscopeArranger.startAnimation();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void cancelAnimation() {
        this.mKaleidoscopeArranger.cancelAnimation();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setUpdateListener(UpdateListener updateListener) {
        this.mKaleidoscopeArranger.setUpdateListener(updateListener);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void save(File file, Properties properties) throws abv {
        super.save(file, properties);
        properties.setProperty(KALEIDOSCOPE_TYPE, String.valueOf(this.mKaleidoscopeType));
        this.mKaleidoscopeArranger.save(file, properties);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void load(File file, Properties properties, ColorResult colorResult) throws abv {
        super.load(file, properties, colorResult);
        try {
            this.mKaleidoscopeType = Integer.parseInt(properties.getProperty(KALEIDOSCOPE_TYPE));
            createArranger();
            this.mKaleidoscopeArranger.load(file, properties, colorResult);
        } catch (NumberFormatException e) {
            Log.e(TAG, "load kaleidoscope pattern failed.", e);
            throw new abv(e);
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mKaleidoscopeType);
        parcel.writeParcelable(this.mKaleidoscopeArranger, i);
    }
}
