package com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorArranger;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern;
import defpackage.abv;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/* loaded from: classes8.dex */
public class LayerPattern extends AbstractPattern {
    public static final Parcelable.Creator<LayerPattern> CREATOR = new Parcelable.Creator<LayerPattern>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.LayerPattern.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: gh_, reason: merged with bridge method [inline-methods] */
        public LayerPattern createFromParcel(Parcel parcel) {
            try {
                return new LayerPattern(parcel);
            } catch (abv e) {
                parcel.writeException(e);
                return null;
            }
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public LayerPattern[] newArray(int i) {
            return new LayerPattern[i];
        }
    };
    private static final String TAG = "VectorPattern";
    private List<String> mResources;
    private final List<VectorArranger> mVectorArrangers;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LayerPattern(int i) {
        this.mVectorArrangers = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            this.mVectorArrangers.add(new VectorArranger());
        }
    }

    private LayerPattern(Parcel parcel) throws abv {
        super(parcel);
        this.mVectorArrangers = new ArrayList();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setBounds(Rect rect) {
        super.setBounds(rect);
        Iterator<VectorArranger> it = this.mVectorArrangers.iterator();
        while (it.hasNext()) {
            it.next().setBounds(rect);
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setColor(ColorResult colorResult) {
        Iterator<VectorArranger> it = this.mVectorArrangers.iterator();
        while (it.hasNext()) {
            it.next().setColor(colorResult);
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setResource(List<String> list) throws abv {
        this.mResources = list;
        if (list.size() != this.mVectorArrangers.size()) {
            throw new abv("resource size mismatch");
        }
        for (int i = 0; i < this.mVectorArrangers.size(); i++) {
            this.mVectorArrangers.get(i).setResource(list.get(i));
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void draw(Canvas canvas) {
        Iterator<VectorArranger> it = this.mVectorArrangers.iterator();
        while (it.hasNext()) {
            it.next().arrange(canvas);
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public List<Integer> getFinalColors() throws abv {
        HashSet hashSet = new HashSet();
        Iterator<VectorArranger> it = this.mVectorArrangers.iterator();
        while (it.hasNext()) {
            hashSet.addAll(it.next().getFinalColors());
        }
        return new ArrayList(hashSet);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void releaseBitmap() {
        Iterator<VectorArranger> it = this.mVectorArrangers.iterator();
        while (it.hasNext()) {
            it.next().releaseBitmap();
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void startAnimation() {
        Iterator<VectorArranger> it = this.mVectorArrangers.iterator();
        while (it.hasNext()) {
            it.next().startAnimation();
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void setUpdateListener(UpdateListener updateListener) {
        Iterator<VectorArranger> it = this.mVectorArrangers.iterator();
        while (it.hasNext()) {
            it.next().setUpdateListener(updateListener);
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void save(File file, Properties properties) throws abv {
        super.save(file, properties);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern
    public void load(File file, Properties properties, ColorResult colorResult) throws abv {
        super.load(file, properties, colorResult);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.pattern.AbstractPattern, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
