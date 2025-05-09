package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import androidx.collection.LongSparseArray;
import com.amap.api.col.p0003sl.de;
import com.amap.api.maps.AMapException;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes8.dex */
public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT;
    private static final int[] DEFAULT_GRADIENT_COLORS;
    private static final float[] DEFAULT_GRADIENT_START_POINTS;
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 21;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 256;
    private de mBounds;
    private int[] mColorMap;
    private Collection<WeightedLatLng> mData;
    private Gradient mGradient;
    private double[] mKernel;
    private double[] mMaxIntensity;
    private double mOpacity;
    private int mRadius;
    private a mTree;

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileHeight() {
        return 256;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileWidth() {
        return 256;
    }

    /* synthetic */ HeatmapTileProvider(Builder builder, byte b) {
        this(builder);
    }

    static {
        int[] iArr = {Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
        DEFAULT_GRADIENT_COLORS = iArr;
        float[] fArr = {0.2f, 1.0f};
        DEFAULT_GRADIENT_START_POINTS = fArr;
        DEFAULT_GRADIENT = new Gradient(iArr, fArr);
    }

    public static class Builder {
        private Collection<WeightedLatLng> data;
        private int radius = 12;
        private Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
        private double opacity = 0.6d;

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.c(collection));
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.data = collection;
            return this;
        }

        public Builder radius(int i) {
            this.radius = Math.max(10, Math.min(i, 50));
            return this;
        }

        public Builder gradient(Gradient gradient) {
            this.gradient = gradient;
            return this;
        }

        public Builder transparency(double d) {
            this.opacity = Math.max(0.0d, Math.min(d, 1.0d));
            return this;
        }

        public HeatmapTileProvider build() {
            Collection<WeightedLatLng> collection = this.data;
            if (collection == null || collection.size() == 0) {
                try {
                    throw new AMapException("No input points.");
                } catch (AMapException e) {
                    Log.e("amap", e.getErrorMessage());
                    e.printStackTrace();
                    return null;
                }
            }
            try {
                return new HeatmapTileProvider(this, (byte) 0);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    private HeatmapTileProvider(Builder builder) {
        this.mData = builder.data;
        this.mRadius = builder.radius;
        Gradient gradient = builder.gradient;
        this.mGradient = gradient;
        if (gradient == null || !gradient.isAvailable()) {
            this.mGradient = DEFAULT_GRADIENT;
        }
        this.mOpacity = builder.opacity;
        int i = this.mRadius;
        this.mKernel = a(i, i / 3.0d);
        a(this.mGradient);
        b(this.mData);
    }

    private void b(Collection<WeightedLatLng> collection) {
        try {
            ArrayList arrayList = new ArrayList();
            for (WeightedLatLng weightedLatLng : collection) {
                if (weightedLatLng.latLng.latitude < 85.0d && weightedLatLng.latLng.latitude > -85.0d) {
                    arrayList.add(weightedLatLng);
                }
            }
            this.mData = arrayList;
            de d = d(arrayList);
            this.mBounds = d;
            this.mTree = new a(d);
            Iterator<WeightedLatLng> it = this.mData.iterator();
            while (it.hasNext()) {
                this.mTree.a(it.next());
            }
            this.mMaxIntensity = a(this.mRadius);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Collection<WeightedLatLng> c(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(new WeightedLatLng(it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00a9  */
    @Override // com.amap.api.maps.model.TileProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amap.api.maps.model.Tile getTile(int r36, int r37, int r38) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.model.HeatmapTileProvider.getTile(int, int, int):com.amap.api.maps.model.Tile");
    }

    private void a(Gradient gradient) {
        this.mGradient = gradient;
        this.mColorMap = gradient.generateColorMap(this.mOpacity);
    }

    private double[] a(int i) {
        int i2;
        double[] dArr = new double[21];
        int i3 = 5;
        while (true) {
            if (i3 >= 11) {
                break;
            }
            dArr[i3] = a(this.mData, this.mBounds, i, (int) (Math.pow(2.0d, i3) * 1280.0d));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
            i3++;
        }
        for (i2 = 11; i2 < 21; i2++) {
            dArr[i2] = dArr[10];
        }
        return dArr;
    }

    private static Tile a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Tile.obtain(256, 256, byteArrayOutputStream.toByteArray());
    }

    private static de d(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d = next.getPoint().x;
        double d2 = next.getPoint().x;
        double d3 = next.getPoint().y;
        double d4 = next.getPoint().y;
        double d5 = d;
        double d6 = d2;
        double d7 = d3;
        while (true) {
            double d8 = d4;
            while (it.hasNext()) {
                WeightedLatLng next2 = it.next();
                double d9 = next2.getPoint().x;
                d4 = next2.getPoint().y;
                if (d9 < d5) {
                    d5 = d9;
                }
                if (d9 > d6) {
                    d6 = d9;
                }
                if (d4 < d7) {
                    d7 = d4;
                }
                if (d4 > d8) {
                    break;
                }
            }
            return new de(d5, d6, d7, d8);
        }
    }

    private static double[] a(int i, double d) {
        double[] dArr = new double[(i * 2) + 1];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((-i2) * i2) / ((2.0d * d) * d));
        }
        return dArr;
    }

    private static double[][] a(double[][] dArr, double[] dArr2) {
        int floor = (int) Math.floor(dArr2.length / 2.0d);
        int length = dArr.length;
        int i = length - (floor * 2);
        int i2 = floor + i;
        int i3 = i2 - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, length);
        int i4 = 0;
        while (true) {
            double d = 0.0d;
            if (i4 >= length) {
                break;
            }
            int i5 = 0;
            while (i5 < length) {
                double d2 = dArr[i4][i5];
                if (d2 != d) {
                    int i6 = i4 + floor;
                    if (i3 < i6) {
                        i6 = i3;
                    }
                    int i7 = i4 - floor;
                    for (int i8 = floor > i7 ? floor : i7; i8 < i6 + 1; i8++) {
                        double[] dArr4 = dArr3[i8];
                        dArr4[i5] = dArr4[i5] + (dArr2[i8 - i7] * d2);
                    }
                }
                i5++;
                d = 0.0d;
            }
            i4++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i);
        for (int i9 = floor; i9 < i2; i9++) {
            for (int i10 = 0; i10 < length; i10++) {
                double d3 = dArr3[i9][i10];
                if (d3 != 0.0d) {
                    int i11 = i10 + floor;
                    if (i3 < i11) {
                        i11 = i3;
                    }
                    int i12 = i10 - floor;
                    for (int i13 = floor > i12 ? floor : i12; i13 < i11 + 1; i13++) {
                        double[] dArr6 = dArr5[i9 - floor];
                        int i14 = i13 - floor;
                        dArr6[i14] = dArr6[i14] + (dArr2[i13 - i12] * d3);
                    }
                }
            }
        }
        return dArr5;
    }

    private static Bitmap a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = (iArr.length - 1) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 != 0.0d) {
                    if (i5 < iArr.length) {
                        iArr2[i4] = iArr[i5];
                    } else {
                        iArr2[i4] = i;
                    }
                } else {
                    iArr2[i4] = 0;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    private static double a(Collection<WeightedLatLng> collection, de deVar, int i, int i2) {
        double d = deVar.f966a;
        double d2 = deVar.c;
        double d3 = deVar.b;
        double d4 = d2 - d;
        double d5 = deVar.d - d3;
        if (d4 <= d5) {
            d4 = d5;
        }
        double d6 = ((int) ((i2 / (i * 2)) + 0.5d)) / d4;
        LongSparseArray longSparseArray = new LongSparseArray();
        double d7 = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            double d8 = weightedLatLng.getPoint().x;
            int i3 = (int) ((weightedLatLng.getPoint().y - d3) * d6);
            long j = (int) ((d8 - d) * d6);
            LongSparseArray longSparseArray2 = (LongSparseArray) longSparseArray.get(j);
            if (longSparseArray2 == null) {
                longSparseArray2 = new LongSparseArray();
                longSparseArray.put(j, longSparseArray2);
            }
            long j2 = i3;
            Double d9 = (Double) longSparseArray2.get(j2);
            if (d9 == null) {
                d9 = Double.valueOf(0.0d);
            }
            LongSparseArray longSparseArray3 = longSparseArray;
            double d10 = d;
            Double valueOf = Double.valueOf(d9.doubleValue() + weightedLatLng.intensity);
            longSparseArray2.put(j2, valueOf);
            if (valueOf.doubleValue() > d7) {
                d7 = valueOf.doubleValue();
            }
            longSparseArray = longSparseArray3;
            d = d10;
        }
        return d7;
    }
}
