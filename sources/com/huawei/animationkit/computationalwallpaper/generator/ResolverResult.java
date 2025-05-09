package com.huawei.animationkit.computationalwallpaper.generator;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import defpackage.abv;
import defpackage.adj;
import java.util.Properties;

/* loaded from: classes8.dex */
public class ResolverResult implements Parcelable {
    public static final Parcelable.Creator<ResolverResult> CREATOR = new Parcelable.Creator<ResolverResult>() { // from class: com.huawei.animationkit.computationalwallpaper.generator.ResolverResult.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: fP_, reason: merged with bridge method [inline-methods] */
        public ResolverResult createFromParcel(Parcel parcel) {
            return new ResolverResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResolverResult[] newArray(int i) {
            return new ResolverResult[i];
        }
    };
    private final adj mAiResult;
    private final ColorResult mColorResult;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ResolverResult(ColorResult colorResult, adj adjVar) {
        this.mColorResult = colorResult;
        this.mAiResult = adjVar;
    }

    protected ResolverResult(Parcel parcel) {
        this.mColorResult = (ColorResult) parcel.readParcelable(ColorResult.class.getClassLoader());
        this.mAiResult = null;
    }

    public ColorResult getColorResult() {
        return this.mColorResult;
    }

    public adj getAiResult() {
        return this.mAiResult;
    }

    public void save(Properties properties) {
        this.mColorResult.save(properties);
    }

    public void load(Properties properties) throws abv {
        this.mColorResult.load(properties);
    }

    public String toString() {
        return "{aiResult=" + this.mAiResult + ", colorResult=" + this.mColorResult + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mColorResult, i);
    }
}
