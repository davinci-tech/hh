package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.util.TypedValue;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.uikit.hwadvancednumberpicker.utils.HwFormatter;
import com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class ski {
    private static final String b = "PickerHelper";

    static final class a implements SoundPool.OnLoadCompleteListener {
        final /* synthetic */ HwAdvancedNumberPicker c;

        a(HwAdvancedNumberPicker hwAdvancedNumberPicker) {
            this.c = hwAdvancedNumberPicker;
        }

        @Override // android.media.SoundPool.OnLoadCompleteListener
        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            if (i2 == 0) {
                this.c.i = true;
            }
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public String f17089a;
        public int c;
        public int d;
        public int e;
    }

    public static float a(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    private static String a(HwFormatter hwFormatter, int i, String str) {
        return (hwFormatter == null || hwFormatter != HwAdvancedNumberPicker.c || str.length() >= 3 || str.length() <= 0) ? str : String.format("%02d", Integer.valueOf(i));
    }

    public static String a(String[] strArr) {
        String str = null;
        if (strArr == null) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (!TextUtils.isEmpty(strArr[i2]) && strArr[i2].length() > i) {
                str = strArr[i2];
                i = str.length();
            }
        }
        return str;
    }

    public static boolean c(skw skwVar, float f) {
        if (skwVar == null) {
            return true;
        }
        boolean h = skwVar.h();
        float e = skwVar.e();
        return h || !(Float.compare(e * f, 0.0f) >= 0) || Math.abs(f) > Math.abs(e);
    }

    public static String[] c(HwAdvancedNumberPicker hwAdvancedNumberPicker) {
        int minValue = hwAdvancedNumberPicker.getMinValue();
        int maxValue = hwAdvancedNumberPicker.getMaxValue();
        if (maxValue < minValue) {
            Log.e(b, "error get displayed values");
            return new String[0];
        }
        int i = (maxValue - minValue) + 1;
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = Integer.toString(i2 + minValue);
        }
        return (String[]) strArr.clone();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(com.huawei.uikit.hwadvancednumberpicker.utils.HwFormatter r2, java.lang.String r3, boolean r4, boolean r5, java.lang.String r6) {
        /*
            if (r2 != 0) goto L28
            if (r5 == 0) goto L27
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L27
            boolean r2 = r3.endsWith(r6)
            if (r2 != 0) goto L27
            java.lang.String r2 = "-- --"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L27
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r3)
            r2.append(r6)
            java.lang.String r3 = r2.toString()
        L27:
            return r3
        L28:
            r0 = 0
            int r1 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L4b
            java.lang.String r3 = defpackage.skh.b(r1)     // Catch: java.lang.NumberFormatException -> L4c
            if (r5 == 0) goto L53
            if (r3 == 0) goto L53
            boolean r5 = r3.endsWith(r6)     // Catch: java.lang.NumberFormatException -> L4c
            if (r5 != 0) goto L53
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> L4c
            r5.<init>()     // Catch: java.lang.NumberFormatException -> L4c
            r5.append(r3)     // Catch: java.lang.NumberFormatException -> L4c
            r5.append(r6)     // Catch: java.lang.NumberFormatException -> L4c
            java.lang.String r3 = r5.toString()     // Catch: java.lang.NumberFormatException -> L4c
            goto L53
        L4b:
            r1 = r0
        L4c:
            java.lang.String r5 = defpackage.ski.b
            java.lang.String r6 = "number format Exception"
            android.util.Log.w(r5, r6)
        L53:
            java.lang.String r2 = a(r2, r1, r3)
            if (r4 == 0) goto L74
            int r3 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L6d
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.NumberFormatException -> L6d
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.NumberFormatException -> L6d
            r4[r0] = r3     // Catch: java.lang.NumberFormatException -> L6d
            java.lang.String r3 = "%02d"
            java.lang.String r2 = java.lang.String.format(r3, r4)     // Catch: java.lang.NumberFormatException -> L6d
            goto L74
        L6d:
            java.lang.String r3 = defpackage.ski.b
            java.lang.String r4 = "flag branch -> number format Exception"
            android.util.Log.w(r3, r4)
        L74:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ski.d(com.huawei.uikit.hwadvancednumberpicker.utils.HwFormatter, java.lang.String, boolean, boolean, java.lang.String):java.lang.String");
    }

    public static void d(Context context, HwAdvancedNumberPicker hwAdvancedNumberPicker, boolean z) {
        Method b2 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b2 == null) {
            hwAdvancedNumberPicker.setExtendScrollEnabled(z);
            return;
        }
        Object c = slc.c((Object) null, b2, new Object[]{context, hwAdvancedNumberPicker, "pickerScrollEnabled", Boolean.valueOf(z)});
        if (c instanceof Boolean) {
            hwAdvancedNumberPicker.setExtendScrollEnabled(((Boolean) c).booleanValue());
        } else {
            hwAdvancedNumberPicker.setExtendScrollEnabled(z);
        }
    }

    public static void dZe_(HashMap<Integer, String> hashMap, View view, View view2) {
        if (hashMap == null || view == null || view2 == null || hashMap.size() <= 6) {
            return;
        }
        if (TextUtils.isEmpty(hashMap.get(0)) && TextUtils.isEmpty(hashMap.get(1)) && TextUtils.isEmpty(hashMap.get(2))) {
            view.setImportantForAccessibility(2);
        } else {
            view.setImportantForAccessibility(1);
        }
        if (TextUtils.isEmpty(hashMap.get(4)) && TextUtils.isEmpty(hashMap.get(5)) && TextUtils.isEmpty(hashMap.get(6))) {
            view2.setImportantForAccessibility(2);
        } else {
            view2.setImportantForAccessibility(1);
        }
    }

    public static int dZf_(View view, Paint paint, b bVar) {
        int i = bVar.d;
        paint.setTextSize(i);
        int measureText = (int) paint.measureText(bVar.f17089a);
        int width = view.getWidth();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        while (i > bVar.e && measureText > (width - paddingLeft) - paddingRight) {
            i -= bVar.c;
            paint.setTextSize(i);
            measureText = (int) paint.measureText(bVar.f17089a);
        }
        return i;
    }

    public static int dZg_(Paint paint, String[] strArr, int i) {
        int i2 = 0;
        if (strArr == null) {
            float f = 0.0f;
            for (int i3 = 0; i3 <= 9; i3++) {
                float measureText = paint.measureText(String.valueOf(i3));
                f = measureText > f ? measureText : 0;
            }
            return (int) (String.valueOf(Math.abs(i)).length() * f);
        }
        for (String str : strArr) {
            int measureText2 = (int) paint.measureText(str);
            if (measureText2 > i2) {
                i2 = measureText2;
            }
        }
        return i2;
    }

    public static Bitmap dZh_(LruCache<String, Bitmap> lruCache, String str, Paint paint) {
        String str2 = str + SampleEvent.SEPARATOR + paint.getColor() + SampleEvent.SEPARATOR + paint.getTextSize();
        Bitmap bitmap = lruCache.get(str2);
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap dZl_ = dZl_(str, paint);
        lruCache.put(str2, dZl_);
        return dZl_;
    }

    public static SoundPool dZi_(HwAdvancedNumberPicker hwAdvancedNumberPicker) {
        if (hwAdvancedNumberPicker.isInEditMode()) {
            return null;
        }
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setUsage(13);
        AudioAttributes build = builder.build();
        SoundPool.Builder builder2 = new SoundPool.Builder();
        builder2.setAudioAttributes(build);
        builder2.setMaxStreams(7);
        SoundPool build2 = builder2.build();
        build2.setOnLoadCompleteListener(new a(hwAdvancedNumberPicker));
        hwAdvancedNumberPicker.o = build2.load(hwAdvancedNumberPicker.getContext(), R.raw._2131886192_res_0x7f120070, 1);
        return build2;
    }

    public static void dZj_(HwAdvancedNumberPicker hwAdvancedNumberPicker, SoundPool soundPool) {
        if (soundPool != null) {
            soundPool.release();
            hwAdvancedNumberPicker.o = 0;
            hwAdvancedNumberPicker.i = false;
        }
    }

    public static void dZk_(Context context, View view) {
        int measuredHeight = view.getMeasuredHeight();
        float dimension = context.getResources().getDimension(R.dimen._2131363871_res_0x7f0a081f);
        float dimension2 = context.getResources().getDimension(R.dimen._2131363873_res_0x7f0a0821);
        float dimension3 = context.getResources().getDimension(R.dimen._2131363872_res_0x7f0a0820);
        float dimension4 = context.getResources().getDimension(R.dimen._2131363869_res_0x7f0a081d);
        float f = measuredHeight;
        if (f > dimension2) {
            view.setVerticalFadingEdgeEnabled(true);
            if (f < dimension3) {
                view.setFadingEdgeLength((int) (dimension4 - ((dimension3 - f) / 2.0f)));
            } else if (f > dimension) {
                view.setFadingEdgeLength((int) (((f - dimension) / 2.0f) + dimension4));
            } else {
                view.setFadingEdgeLength((int) dimension4);
            }
        }
    }

    public static Bitmap dZl_(String str, Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float ceil = (float) Math.ceil(paint.measureText(str));
        float ceil2 = (float) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        if (Float.compare(ceil, 1.0f) < 0) {
            ceil = 1.0f;
        }
        if (Float.compare(ceil2, 1.0f) < 0) {
            ceil2 = 1.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) ceil, (int) ceil2, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawText(str, ceil / 2.0f, -fontMetrics.top, paint);
        return createBitmap;
    }

    public static float e(Context context, float f) {
        return TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }
}
