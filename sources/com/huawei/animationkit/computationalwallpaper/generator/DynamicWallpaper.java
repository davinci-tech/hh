package com.huawei.animationkit.computationalwallpaper.generator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern;
import defpackage.abo;
import defpackage.abv;
import defpackage.adj;
import defpackage.aej;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes8.dex */
public class DynamicWallpaper implements Wallpaper {
    public static final Parcelable.Creator<DynamicWallpaper> CREATOR = new Parcelable.Creator<DynamicWallpaper>() { // from class: com.huawei.animationkit.computationalwallpaper.generator.DynamicWallpaper.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: fM_, reason: merged with bridge method [inline-methods] */
        public DynamicWallpaper createFromParcel(Parcel parcel) {
            try {
                return new DynamicWallpaper(parcel);
            } catch (ClassNotFoundException e) {
                Log.e(DynamicWallpaper.TAG, "class not found", e);
                return null;
            } catch (RuntimeException e2) {
                Log.e(DynamicWallpaper.TAG, "create from parcel failed", e2);
                return null;
            }
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public DynamicWallpaper[] newArray(int i) {
            return new DynamicWallpaper[i];
        }
    };
    private static final String PACKAGE_NAME = "com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.";
    private static final String PATTERN = "pattern";
    private static final String PREVIEW_PNG = "preview.png";
    private static final String TAG = "DynamicWallpaper";
    private static final String XML_FILE = "wallpaper.xml";
    private final WatchFaceClock mClock;
    private final Pattern mPattern;
    private final Bitmap mPreview;
    private final ResolverResult mResolverResult;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DynamicWallpaper(ResolverResult resolverResult, Pattern pattern, WatchFaceClock watchFaceClock) {
        this.mResolverResult = resolverResult;
        this.mPattern = pattern;
        this.mClock = watchFaceClock;
        this.mPreview = generatePreviewBitmap();
    }

    protected DynamicWallpaper(Parcel parcel) throws ClassNotFoundException {
        String readString = parcel.readString();
        this.mResolverResult = (ResolverResult) parcel.readParcelable(ResolverResult.class.getClassLoader());
        this.mPattern = (Pattern) parcel.readParcelable(Class.forName(readString).asSubclass(Pattern.class).getClassLoader());
        this.mClock = (WatchFaceClock) parcel.readParcelable(WatchFaceClock.class.getClassLoader());
        this.mPreview = generatePreviewBitmap();
    }

    public Pattern getPattern() {
        return this.mPattern;
    }

    public ResolverResult getResolverResult() {
        return this.mResolverResult;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.generator.Wallpaper
    public WatchFaceClock getClock() {
        return this.mClock;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.generator.Wallpaper
    public String getTag() {
        return this.mPattern.getTag();
    }

    public Bitmap getPreview() {
        return this.mPreview;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.generator.Wallpaper
    public void save(File file) throws abv {
        Properties properties = new Properties();
        properties.put(PATTERN, this.mPattern.getClass().getSimpleName());
        this.mResolverResult.save(properties);
        this.mPattern.save(file, properties);
        this.mClock.save(properties);
        saveProperties(properties, file);
        savePreview(file);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.generator.Wallpaper
    public Bitmap toBitmap() {
        return this.mPattern.toBitmap();
    }

    private void savePreview(File file) throws abv {
        try {
            aej.gl_(file, PREVIEW_PNG, generatePreviewWithClock());
        } catch (IOException unused) {
            throw new abv("save preview failed, file name: preview.png");
        }
    }

    private Bitmap generatePreviewWithClock() {
        Bitmap createBitmap = Bitmap.createBitmap(this.mPreview.getWidth(), this.mPreview.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(this.mPreview, 0.0f, 0.0f, (Paint) null);
        this.mClock.draw(canvas);
        return createBitmap;
    }

    private Bitmap generatePreviewBitmap() {
        Rect bounds = this.mPattern.getBounds();
        this.mPattern.setBounds(new Rect(0, 0, abo.j(), abo.i()));
        Bitmap bitmap = toBitmap();
        this.mPattern.setBounds(bounds);
        return bitmap;
    }

    private void saveProperties(Properties properties, File file) throws abv {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, XML_FILE));
            try {
                properties.storeToXML(fileOutputStream, null);
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
            Log.e(TAG, "store xml failed, file name: wallpaper.xml");
            throw new abv("store xml failed, file name: wallpaper.xml");
        }
    }

    public static DynamicWallpaper load(File file) throws abv {
        Properties loadProperties = loadProperties(file);
        ResolverResult resolverResult = new ResolverResult(new ColorResult(), new adj());
        resolverResult.load(loadProperties);
        Pattern buildPattern = buildPattern(loadProperties.getProperty(PATTERN));
        buildPattern.load(file, loadProperties, resolverResult.getColorResult());
        buildPattern.setColor(resolverResult.getColorResult());
        WatchFaceClock watchFaceClock = new WatchFaceClock();
        watchFaceClock.load(loadProperties);
        return new DynamicWallpaper(resolverResult, buildPattern, watchFaceClock);
    }

    private static Pattern buildPattern(String str) throws abv {
        try {
            if (!str.startsWith(PACKAGE_NAME)) {
                str = PACKAGE_NAME + str;
            }
            return (Pattern) Class.forName(str).asSubclass(Pattern.class).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            Log.e(TAG, "build pattern failed.", e);
            throw new abv("error class name");
        }
    }

    private static Properties loadProperties(File file) throws abv {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(file, XML_FILE));
            try {
                properties.loadFromXML(fileInputStream);
                fileInputStream.close();
                return properties;
            } finally {
            }
        } catch (IOException unused) {
            Log.e(TAG, "load xml failed, file name: wallpaper.xml");
            throw new abv("load xml failed, file name: wallpaper.xml");
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPattern.getClass().getName());
        parcel.writeParcelable(this.mResolverResult, i);
        parcel.writeParcelable(this.mPattern, i);
        parcel.writeParcelable(this.mClock, i);
    }
}
