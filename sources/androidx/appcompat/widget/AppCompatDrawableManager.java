package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.graphics.ColorUtils;
import com.huawei.health.R;

/* loaded from: classes2.dex */
public final class AppCompatDrawableManager {
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE = null;
    private static final String TAG = "AppCompatDrawableManag";
    private ResourceManagerInternal mResourceManager;

    public static void preload() {
        synchronized (AppCompatDrawableManager.class) {
            if (INSTANCE == null) {
                AppCompatDrawableManager appCompatDrawableManager = new AppCompatDrawableManager();
                INSTANCE = appCompatDrawableManager;
                appCompatDrawableManager.mResourceManager = ResourceManagerInternal.get();
                INSTANCE.mResourceManager.setHooks(new ResourceManagerInternal.ResourceManagerHooks() { // from class: androidx.appcompat.widget.AppCompatDrawableManager.1
                    private final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {R.drawable._2131427461_res_0x7f0b0085, R.drawable._2131427459_res_0x7f0b0083, R.drawable._2131427385_res_0x7f0b0039};
                    private final int[] TINT_COLOR_CONTROL_NORMAL = {R.drawable._2131427409_res_0x7f0b0051, R.drawable._2131427444_res_0x7f0b0074, R.drawable._2131427416_res_0x7f0b0058, R.drawable._2131427411_res_0x7f0b0053, R.drawable._2131427412_res_0x7f0b0054, R.drawable._2131427415_res_0x7f0b0057, R.drawable._2131427414_res_0x7f0b0056};
                    private final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {R.drawable._2131427458_res_0x7f0b0082, R.drawable._2131427460_res_0x7f0b0084, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable._2131427454_res_0x7f0b007e, R.drawable._2131427455_res_0x7f0b007f, R.drawable._2131427456_res_0x7f0b0080, R.drawable._2131427457_res_0x7f0b0081};
                    private final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable._2131427433_res_0x7f0b0069};
                    private final int[] TINT_COLOR_CONTROL_STATE_LIST = {R.drawable._2131427452_res_0x7f0b007c, R.drawable._2131427462_res_0x7f0b0086};
                    private final int[] TINT_CHECKABLE_BUTTON_LIST = {R.drawable._2131427388_res_0x7f0b003c, R.drawable._2131427394_res_0x7f0b0042, R.drawable._2131427389_res_0x7f0b003d, R.drawable._2131427395_res_0x7f0b0043};

                    private ColorStateList createDefaultButtonColorStateList(Context context) {
                        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr._2131099914_res_0x7f06010a));
                    }

                    private ColorStateList createBorderlessButtonColorStateList(Context context) {
                        return createButtonColorStateList(context, 0);
                    }

                    private ColorStateList createColoredButtonColorStateList(Context context) {
                        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr._2131099912_res_0x7f060108));
                    }

                    private ColorStateList createButtonColorStateList(Context context, int i) {
                        int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R.attr._2131099916_res_0x7f06010c);
                        int disabledThemeAttrColor = ThemeUtils.getDisabledThemeAttrColor(context, R.attr._2131099914_res_0x7f06010a);
                        int[] iArr = ThemeUtils.DISABLED_STATE_SET;
                        int[] iArr2 = ThemeUtils.PRESSED_STATE_SET;
                        int compositeColors = ColorUtils.compositeColors(themeAttrColor, i);
                        return new ColorStateList(new int[][]{iArr, iArr2, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{disabledThemeAttrColor, compositeColors, ColorUtils.compositeColors(themeAttrColor, i), i});
                    }

                    private ColorStateList createSwitchThumbColorStateList(Context context) {
                        int[][] iArr = new int[3][];
                        int[] iArr2 = new int[3];
                        ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, R.attr._2131099923_res_0x7f060113);
                        if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
                            int[] iArr3 = ThemeUtils.DISABLED_STATE_SET;
                            iArr[0] = iArr3;
                            iArr2[0] = themeAttrColorStateList.getColorForState(iArr3, 0);
                            iArr[1] = ThemeUtils.CHECKED_STATE_SET;
                            iArr2[1] = ThemeUtils.getThemeAttrColor(context, R.attr._2131099915_res_0x7f06010b);
                            iArr[2] = ThemeUtils.EMPTY_STATE_SET;
                            iArr2[2] = themeAttrColorStateList.getDefaultColor();
                        } else {
                            iArr[0] = ThemeUtils.DISABLED_STATE_SET;
                            iArr2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr._2131099923_res_0x7f060113);
                            iArr[1] = ThemeUtils.CHECKED_STATE_SET;
                            iArr2[1] = ThemeUtils.getThemeAttrColor(context, R.attr._2131099915_res_0x7f06010b);
                            iArr[2] = ThemeUtils.EMPTY_STATE_SET;
                            iArr2[2] = ThemeUtils.getThemeAttrColor(context, R.attr._2131099923_res_0x7f060113);
                        }
                        return new ColorStateList(iArr, iArr2);
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public Drawable createDrawableFor(ResourceManagerInternal resourceManagerInternal, Context context, int i) {
                        if (i == R.drawable.abc_cab_background_top_material) {
                            return new LayerDrawable(new Drawable[]{resourceManagerInternal.getDrawable(context, R.drawable.abc_cab_background_internal_bg), resourceManagerInternal.getDrawable(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
                        }
                        if (i == R.drawable._2131427436_res_0x7f0b006c) {
                            return getRatingBarLayerDrawable(resourceManagerInternal, context, R.dimen._2131361924_res_0x7f0a0084);
                        }
                        if (i == R.drawable._2131427435_res_0x7f0b006b) {
                            return getRatingBarLayerDrawable(resourceManagerInternal, context, R.dimen._2131361925_res_0x7f0a0085);
                        }
                        if (i == R.drawable._2131427437_res_0x7f0b006d) {
                            return getRatingBarLayerDrawable(resourceManagerInternal, context, R.dimen._2131361926_res_0x7f0a0086);
                        }
                        return null;
                    }

                    private LayerDrawable getRatingBarLayerDrawable(ResourceManagerInternal resourceManagerInternal, Context context, int i) {
                        BitmapDrawable bitmapDrawable;
                        BitmapDrawable bitmapDrawable2;
                        BitmapDrawable bitmapDrawable3;
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i);
                        Drawable drawable = resourceManagerInternal.getDrawable(context, R.drawable._2131427448_res_0x7f0b0078);
                        Drawable drawable2 = resourceManagerInternal.getDrawable(context, R.drawable._2131427449_res_0x7f0b0079);
                        if ((drawable instanceof BitmapDrawable) && drawable.getIntrinsicWidth() == dimensionPixelSize && drawable.getIntrinsicHeight() == dimensionPixelSize) {
                            bitmapDrawable = (BitmapDrawable) drawable;
                            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
                        } else {
                            Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(createBitmap);
                            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                            drawable.draw(canvas);
                            bitmapDrawable = new BitmapDrawable(createBitmap);
                            bitmapDrawable2 = new BitmapDrawable(createBitmap);
                        }
                        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
                        if ((drawable2 instanceof BitmapDrawable) && drawable2.getIntrinsicWidth() == dimensionPixelSize && drawable2.getIntrinsicHeight() == dimensionPixelSize) {
                            bitmapDrawable3 = (BitmapDrawable) drawable2;
                        } else {
                            Bitmap createBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                            Canvas canvas2 = new Canvas(createBitmap2);
                            drawable2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                            drawable2.draw(canvas2);
                            bitmapDrawable3 = new BitmapDrawable(createBitmap2);
                        }
                        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
                        layerDrawable.setId(0, android.R.id.background);
                        layerDrawable.setId(1, android.R.id.secondaryProgress);
                        layerDrawable.setId(2, android.R.id.progress);
                        return layerDrawable;
                    }

                    private void setPorterDuffColorFilter(Drawable drawable, int i, PorterDuff.Mode mode) {
                        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                            drawable = drawable.mutate();
                        }
                        if (mode == null) {
                            mode = AppCompatDrawableManager.DEFAULT_MODE;
                        }
                        drawable.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(i, mode));
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public boolean tintDrawable(Context context, int i, Drawable drawable) {
                        if (i == R.drawable._2131427445_res_0x7f0b0075) {
                            LayerDrawable layerDrawable = (LayerDrawable) drawable;
                            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(android.R.id.background), ThemeUtils.getThemeAttrColor(context, R.attr._2131099917_res_0x7f06010d), AppCompatDrawableManager.DEFAULT_MODE);
                            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(android.R.id.secondaryProgress), ThemeUtils.getThemeAttrColor(context, R.attr._2131099917_res_0x7f06010d), AppCompatDrawableManager.DEFAULT_MODE);
                            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(android.R.id.progress), ThemeUtils.getThemeAttrColor(context, R.attr._2131099915_res_0x7f06010b), AppCompatDrawableManager.DEFAULT_MODE);
                            return true;
                        }
                        if (i != R.drawable._2131427436_res_0x7f0b006c && i != R.drawable._2131427435_res_0x7f0b006b && i != R.drawable._2131427437_res_0x7f0b006d) {
                            return false;
                        }
                        LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                        setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(android.R.id.background), ThemeUtils.getDisabledThemeAttrColor(context, R.attr._2131099917_res_0x7f06010d), AppCompatDrawableManager.DEFAULT_MODE);
                        setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(android.R.id.secondaryProgress), ThemeUtils.getThemeAttrColor(context, R.attr._2131099915_res_0x7f06010b), AppCompatDrawableManager.DEFAULT_MODE);
                        setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(android.R.id.progress), ThemeUtils.getThemeAttrColor(context, R.attr._2131099915_res_0x7f06010b), AppCompatDrawableManager.DEFAULT_MODE);
                        return true;
                    }

                    private boolean arrayContains(int[] iArr, int i) {
                        for (int i2 : iArr) {
                            if (i2 == i) {
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public ColorStateList getTintListForDrawableRes(Context context, int i) {
                        if (i == R.drawable._2131427405_res_0x7f0b004d) {
                            return AppCompatResources.getColorStateList(context, R.color._2131296363_res_0x7f09006b);
                        }
                        if (i == R.drawable._2131427451_res_0x7f0b007b) {
                            return AppCompatResources.getColorStateList(context, R.color._2131296366_res_0x7f09006e);
                        }
                        if (i == R.drawable._2131427450_res_0x7f0b007a) {
                            return createSwitchThumbColorStateList(context);
                        }
                        if (i == R.drawable._2131427393_res_0x7f0b0041) {
                            return createDefaultButtonColorStateList(context);
                        }
                        if (i == R.drawable._2131427387_res_0x7f0b003b) {
                            return createBorderlessButtonColorStateList(context);
                        }
                        if (i == R.drawable._2131427392_res_0x7f0b0040) {
                            return createColoredButtonColorStateList(context);
                        }
                        if (i == R.drawable._2131427446_res_0x7f0b0076 || i == R.drawable.abc_spinner_textfield_background_material) {
                            return AppCompatResources.getColorStateList(context, R.color._2131296365_res_0x7f09006d);
                        }
                        if (arrayContains(this.TINT_COLOR_CONTROL_NORMAL, i)) {
                            return ThemeUtils.getThemeAttrColorStateList(context, R.attr._2131099917_res_0x7f06010d);
                        }
                        if (arrayContains(this.TINT_COLOR_CONTROL_STATE_LIST, i)) {
                            return AppCompatResources.getColorStateList(context, R.color._2131296362_res_0x7f09006a);
                        }
                        if (arrayContains(this.TINT_CHECKABLE_BUTTON_LIST, i)) {
                            return AppCompatResources.getColorStateList(context, R.color._2131296361_res_0x7f090069);
                        }
                        if (i == R.drawable._2131427443_res_0x7f0b0073) {
                            return AppCompatResources.getColorStateList(context, R.color._2131296364_res_0x7f09006c);
                        }
                        return null;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:15:0x0069 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:7:0x004e  */
                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public boolean tintDrawableUsingColorFilter(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
                        /*
                            r6 = this;
                            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.AppCompatDrawableManager.access$000()
                            int[] r1 = r6.COLORFILTER_TINT_COLOR_CONTROL_NORMAL
                            boolean r1 = r6.arrayContains(r1, r8)
                            r2 = 0
                            r3 = -1
                            r4 = 1
                            if (r1 == 0) goto L13
                            r8 = 2131099917(0x7f06010d, float:1.78122E38)
                            goto L1e
                        L13:
                            int[] r1 = r6.COLORFILTER_COLOR_CONTROL_ACTIVATED
                            boolean r1 = r6.arrayContains(r1, r8)
                            if (r1 == 0) goto L20
                            r8 = 2131099915(0x7f06010b, float:1.7812197E38)
                        L1e:
                            r1 = r4
                            goto L4a
                        L20:
                            int[] r1 = r6.COLORFILTER_COLOR_BACKGROUND_MULTIPLY
                            boolean r1 = r6.arrayContains(r1, r8)
                            if (r1 == 0) goto L2b
                            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                            goto L42
                        L2b:
                            r1 = 2131427422(0x7f0b005e, float:1.847646E38)
                            if (r8 != r1) goto L3d
                            r8 = 1109603123(0x42233333, float:40.8)
                            int r8 = java.lang.Math.round(r8)
                            r1 = 16842800(0x1010030, float:2.3693693E-38)
                            r5 = r1
                        L3b:
                            r1 = r4
                            goto L4c
                        L3d:
                            r1 = 2131427404(0x7f0b004c, float:1.8476423E38)
                            if (r8 != r1) goto L48
                        L42:
                            r1 = 16842801(0x1010031, float:2.3693695E-38)
                            r5 = r1
                            r8 = r3
                            goto L3b
                        L48:
                            r8 = r2
                            r1 = r8
                        L4a:
                            r5 = r8
                            r8 = r3
                        L4c:
                            if (r1 == 0) goto L69
                            boolean r1 = androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(r9)
                            if (r1 == 0) goto L58
                            android.graphics.drawable.Drawable r9 = r9.mutate()
                        L58:
                            int r7 = androidx.appcompat.widget.ThemeUtils.getThemeAttrColor(r7, r5)
                            android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.AppCompatDrawableManager.getPorterDuffColorFilter(r7, r0)
                            r9.setColorFilter(r7)
                            if (r8 == r3) goto L68
                            r9.setAlpha(r8)
                        L68:
                            return r4
                        L69:
                            return r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatDrawableManager.AnonymousClass1.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public PorterDuff.Mode getTintModeForDrawableRes(int i) {
                        if (i == R.drawable._2131427450_res_0x7f0b007a) {
                            return PorterDuff.Mode.MULTIPLY;
                        }
                        return null;
                    }
                });
            }
        }
    }

    public static AppCompatDrawableManager get() {
        AppCompatDrawableManager appCompatDrawableManager;
        synchronized (AppCompatDrawableManager.class) {
            if (INSTANCE == null) {
                preload();
            }
            appCompatDrawableManager = INSTANCE;
        }
        return appCompatDrawableManager;
    }

    public Drawable getDrawable(Context context, int i) {
        Drawable drawable;
        synchronized (this) {
            drawable = this.mResourceManager.getDrawable(context, i);
        }
        return drawable;
    }

    Drawable getDrawable(Context context, int i, boolean z) {
        Drawable drawable;
        synchronized (this) {
            drawable = this.mResourceManager.getDrawable(context, i, z);
        }
        return drawable;
    }

    public void onConfigurationChanged(Context context) {
        synchronized (this) {
            this.mResourceManager.onConfigurationChanged(context);
        }
    }

    Drawable onDrawableLoadedFromResources(Context context, VectorEnabledTintResources vectorEnabledTintResources, int i) {
        Drawable onDrawableLoadedFromResources;
        synchronized (this) {
            onDrawableLoadedFromResources = this.mResourceManager.onDrawableLoadedFromResources(context, vectorEnabledTintResources, i);
        }
        return onDrawableLoadedFromResources;
    }

    boolean tintDrawableUsingColorFilter(Context context, int i, Drawable drawable) {
        return this.mResourceManager.tintDrawableUsingColorFilter(context, i, drawable);
    }

    ColorStateList getTintList(Context context, int i) {
        ColorStateList tintList;
        synchronized (this) {
            tintList = this.mResourceManager.getTintList(context, i);
        }
        return tintList;
    }

    static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        ResourceManagerInternal.tintDrawable(drawable, tintInfo, iArr);
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (AppCompatDrawableManager.class) {
            porterDuffColorFilter = ResourceManagerInternal.getPorterDuffColorFilter(i, mode);
        }
        return porterDuffColorFilter;
    }
}
