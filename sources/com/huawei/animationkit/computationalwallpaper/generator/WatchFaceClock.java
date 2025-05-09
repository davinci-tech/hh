package com.huawei.animationkit.computationalwallpaper.generator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat;
import defpackage.abo;
import defpackage.abv;
import defpackage.adg;
import defpackage.aej;
import defpackage.aep;
import defpackage.aeq;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class WatchFaceClock implements Parcelable {
    private static final String BACKGROUND_COLOR = "clock_background_color";
    private static final String CLOCK_COLOR = "clock_color";
    private static final String CLOCK_COLOR_LIST = "clock_color_list";
    private static final String CLOCK_RESOURCE = "clock_resource";
    private static final String CLOCK_TYPE = "clock_type";
    private static final String COLOR_DELIMITER = ",";
    public static final Parcelable.Creator<WatchFaceClock> CREATOR = new Parcelable.Creator<WatchFaceClock>() { // from class: com.huawei.animationkit.computationalwallpaper.generator.WatchFaceClock.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: fU_, reason: merged with bridge method [inline-methods] */
        public WatchFaceClock createFromParcel(Parcel parcel) {
            return new WatchFaceClock(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public WatchFaceClock[] newArray(int i) {
            return new WatchFaceClock[i];
        }
    };
    private static final String TAG = "WatchFaceClock";
    private int mBackgroundColor;
    private int mClockColor;
    private String mClockType;
    private List<Integer> mColorList;
    private String mResource;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WatchFaceClock() {
        this.mClockColor = -1;
        this.mBackgroundColor = 0;
        this.mColorList = Collections.emptyList();
    }

    public WatchFaceClock(String str, String str2) {
        this.mClockColor = -1;
        this.mBackgroundColor = 0;
        this.mColorList = Collections.emptyList();
        this.mClockType = str;
        this.mResource = str2;
    }

    private WatchFaceClock(Parcel parcel) {
        this.mClockColor = -1;
        this.mBackgroundColor = 0;
        this.mColorList = Collections.emptyList();
        this.mClockType = parcel.readString();
        this.mResource = parcel.readString();
        this.mClockColor = parcel.readInt();
        this.mBackgroundColor = parcel.readInt();
        this.mColorList = parseColorList(parcel.readString());
    }

    public void setColor(int i, int i2) {
        this.mClockColor = i2;
        this.mBackgroundColor = i;
    }

    public String getClockType() {
        return this.mClockType;
    }

    public String getResource() {
        return this.mResource;
    }

    public int getClockColor() {
        return this.mClockColor;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setColorList(List<Integer> list) {
        this.mColorList = list;
    }

    public List<Integer> getColorList() {
        return this.mColorList;
    }

    public void save(Properties properties) {
        properties.setProperty(CLOCK_TYPE, this.mClockType);
        properties.setProperty(CLOCK_RESOURCE, this.mResource);
        properties.setProperty(CLOCK_COLOR, Integer.toString(this.mClockColor));
        properties.setProperty(BACKGROUND_COLOR, Integer.toString(this.mBackgroundColor));
        properties.setProperty(CLOCK_COLOR_LIST, formatColorList(this.mColorList));
    }

    public void load(Properties properties) throws abv {
        this.mClockType = properties.getProperty(CLOCK_TYPE, "");
        this.mResource = properties.getProperty(CLOCK_RESOURCE, "");
        try {
            this.mClockColor = Integer.parseInt(properties.getProperty(CLOCK_COLOR));
            this.mBackgroundColor = Integer.parseInt(properties.getProperty(BACKGROUND_COLOR));
            this.mColorList = parseColorList(properties.getProperty(CLOCK_COLOR_LIST), this.mBackgroundColor, this.mClockColor);
        } catch (NumberFormatException e) {
            throw new abv("invalidate color value", e);
        }
    }

    public void draw(Canvas canvas) {
        try {
            drawClock(canvas);
        } catch (abv e) {
            Log.e(TAG, "draw clock failed", e);
        }
    }

    private void drawClock(Canvas canvas) throws abv {
        if (this.mResource.toLowerCase(Locale.ENGLISH).endsWith(".png")) {
            drawPngClock(canvas);
        } else if (abo.g()) {
            drawColoringVectorClock(canvas);
        } else {
            drawVectorClock(canvas);
        }
    }

    private void drawPngClock(Canvas canvas) throws abv {
        try {
            Bitmap gk_ = aej.gk_(new File(abo.d()), this.mResource);
            canvas.drawBitmap(gk_, new Rect(0, 0, gk_.getWidth(), gk_.getHeight()), new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), (Paint) null);
        } catch (IOException unused) {
            throw new abv("load bitmap failed, file name: " + this.mResource);
        }
    }

    private void drawColoringVectorClock(Canvas canvas) throws abv {
        VectorDrawableCompat e = aeq.e(this.mResource, null);
        List<Integer> list = this.mColorList;
        aep.a(e, list, list);
        e.setBounds(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()));
        e.draw(canvas);
    }

    private void drawVectorClock(Canvas canvas) throws abv {
        VectorDrawableCompat e = aeq.e(this.mResource, null);
        e.setBounds(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()));
        e.draw(canvas);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mClockType);
        parcel.writeString(this.mResource);
        parcel.writeInt(this.mClockColor);
        parcel.writeInt(this.mBackgroundColor);
        parcel.writeString(formatColorList(this.mColorList));
    }

    private String formatColorList(List<Integer> list) {
        return (String) list.stream().map(new Function() { // from class: adi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String valueOf;
                valueOf = String.valueOf((Integer) obj);
                return valueOf;
            }
        }).collect(Collectors.joining(","));
    }

    private List<Integer> parseColorList(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }
        return (List) Arrays.stream(str.split(",")).map(new adg()).collect(Collectors.toList());
    }

    private List<Integer> parseColorList(String str, int i, int i2) {
        if (str == null || str.isEmpty()) {
            return Arrays.asList(Integer.valueOf(i), Integer.valueOf(i2));
        }
        return (List) Arrays.stream(str.split(",")).map(new adg()).collect(Collectors.toList());
    }
}
