package com.huawei.animationkit.computationalwallpaper.graphicanalysis.impl;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.graphicanalysis.AiAnalysisStrategy;
import defpackage.abo;
import defpackage.abv;
import defpackage.adj;
import defpackage.adm;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class AiStrategyImpl implements AiAnalysisStrategy {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f1852a = {0.485f, 0.456f, 0.406f};
    private final float[] b = {0.229f, 0.224f, 0.225f};
    private boolean c = false;
    private static final List<RectF> e = Arrays.asList(new RectF(0.0625f, 0.0625f, 0.9375f, 0.9375f), new RectF(0.0f, 0.0f, 1.0f, 1.0f), new RectF(0.0f, 0.0f, 0.875f, 0.875f), new RectF(0.125f, 0.0f, 1.0f, 0.875f), new RectF(0.0f, 0.125f, 0.875f, 1.0f), new RectF(0.125f, 0.125f, 1.0f, 1.0f), new RectF(0.0f, 0.0f, 0.666f, 1.0f), new RectF(0.1665f, 0.0f, 0.8335f, 1.0f), new RectF(0.333f, 0.0f, 1.0f, 1.0f), new RectF(0.0f, 0.0f, 1.0f, 0.666f), new RectF(0.0f, 0.1665f, 1.0f, 0.8335f), new RectF(0.0f, 0.333f, 1.0f, 1.0f), new RectF(0.0f, 0.0f, 0.75f, 1.0f), new RectF(0.125f, 0.0f, 0.875f, 1.0f), new RectF(0.25f, 0.0f, 1.0f, 1.0f), new RectF(0.0f, 0.0f, 1.0f, 0.75f), new RectF(0.0f, 0.125f, 1.0f, 0.875f), new RectF(0.0f, 0.25f, 1.0f, 1.0f));
    private static final AiStrategyImpl d = new AiStrategyImpl();

    private native void getResult(float[] fArr, int i, float[] fArr2);

    private native int loadModel(String str);

    static {
        System.loadLibrary("mindspore-lite-wear");
        System.loadLibrary("dlliteclient_mindspore-wear");
        System.loadLibrary("dlliteclient-wear");
        System.loadLibrary("model");
    }

    private AiStrategyImpl() {
    }

    public static AiStrategyImpl d() {
        return d;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.graphicanalysis.AiAnalysisStrategy
    public adj analyze(Bitmap bitmap) throws abv {
        adj adjVar;
        synchronized (this) {
            int i = 0;
            if (!this.c) {
                StringBuilder sb = new StringBuilder();
                sb.append(abo.e());
                sb.append(File.separator);
                sb.append("model_weight8.ms");
                this.c = loadModel(sb.toString()) == 0;
            }
            if (!this.c) {
                Log.e("AiStrategyImpl", "model load failed");
                throw new abv("model load failed");
            }
            Log.d("AiStrategyImpl", "width " + bitmap.getWidth() + " height " + bitmap.getHeight());
            long currentTimeMillis = System.currentTimeMillis();
            HashMap<String, Float> hashMap = new HashMap<>();
            while (true) {
                List<RectF> list = e;
                if (i >= list.size()) {
                    break;
                }
                fW_(fX_(bitmap, list.get(i)), hashMap);
                i++;
            }
            Log.d("AiStrategyImpl", "Image Recognition cost " + (System.currentTimeMillis() - currentTimeMillis));
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, Float> entry : hashMap.entrySet()) {
                arrayList.add(new adj.b(entry.getKey(), entry.getValue().floatValue()));
            }
            adjVar = new adj(arrayList);
        }
        return adjVar;
    }

    private Bitmap fX_(Bitmap bitmap, RectF rectF) {
        return Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, (int) (bitmap.getWidth() * rectF.left), (int) (bitmap.getHeight() * rectF.top), Math.max((int) (bitmap.getWidth() * rectF.width()), 1), Math.max((int) (bitmap.getHeight() * rectF.height()), 1)), 224, 224, true);
    }

    private void fW_(Bitmap bitmap, HashMap<String, Float> hashMap) {
        int[] iArr = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        float[] fArr = new float[bitmap.getWidth() * bitmap.getHeight() * 3];
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int i2 = 0; i2 < bitmap.getWidth(); i2++) {
                int i3 = iArr[(bitmap.getWidth() * i) + i2];
                fArr[(bitmap.getWidth() * i) + i2] = (((((16711680 & i3) >> 16) * 1.0f) / 255.0f) - this.f1852a[0]) / this.b[0];
                fArr[(bitmap.getHeight() * bitmap.getWidth()) + (bitmap.getWidth() * i) + i2] = (((((65280 & i3) >> 8) * 1.0f) / 255.0f) - this.f1852a[1]) / this.b[1];
                fArr[(bitmap.getHeight() * 2 * bitmap.getWidth()) + (bitmap.getWidth() * i) + i2] = ((((i3 & 255) * 1.0f) / 255.0f) - this.f1852a[2]) / this.b[2];
            }
        }
        getResult(fArr, bitmap.getWidth() * bitmap.getHeight() * 12, new float[10]);
        double d2 = 0.0d;
        for (int i4 = 0; i4 < 10; i4++) {
            d2 += Math.exp(r3[i4]);
        }
        for (int i5 = 0; i5 < 10; i5++) {
            String str = adm.c[i5];
            hashMap.put(str, Float.valueOf(hashMap.getOrDefault(str, Float.valueOf(0.0f)).floatValue() + ((float) (Math.exp(r3[i5]) / d2))));
        }
    }
}
