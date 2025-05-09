package com.google.android.material.resources;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import androidx.core.content.res.ResourcesCompat;

/* loaded from: classes8.dex */
public class TextAppearance {
    private static final String TAG = "TextAppearance";
    private static final int TYPEFACE_MONOSPACE = 3;
    private static final int TYPEFACE_SANS = 1;
    private static final int TYPEFACE_SERIF = 2;
    private Typeface font;
    public final String fontFamily;
    private final int fontFamilyResourceId;
    private boolean fontResolved = false;
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final boolean textAllCaps;
    public final ColorStateList textColor;
    public final ColorStateList textColorHint;
    public final ColorStateList textColorLink;
    public final float textSize;
    public final int textStyle;
    public final int typeface;

    public TextAppearance(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{R.attr.textSize, R.attr.typeface, R.attr.textStyle, R.attr.textColor, R.attr.textColorHint, R.attr.textColorLink, R.attr.shadowColor, R.attr.shadowDx, R.attr.shadowDy, R.attr.shadowRadius, R.attr.fontFamily, R.attr.textFontWeight, com.huawei.health.R.attr._2131100112_res_0x7f0601d0, com.huawei.health.R.attr._2131100121_res_0x7f0601d9, com.huawei.health.R.attr._2131101214_res_0x7f06061e, com.huawei.health.R.attr._2131101247_res_0x7f06063f});
        this.textSize = obtainStyledAttributes.getDimension(0, 0.0f);
        this.textColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, 3);
        this.textColorHint = MaterialResources.getColorStateList(context, obtainStyledAttributes, 4);
        this.textColorLink = MaterialResources.getColorStateList(context, obtainStyledAttributes, 5);
        this.textStyle = obtainStyledAttributes.getInt(2, 0);
        this.typeface = obtainStyledAttributes.getInt(1, 1);
        int indexWithValue = MaterialResources.getIndexWithValue(obtainStyledAttributes, 12, 10);
        this.fontFamilyResourceId = obtainStyledAttributes.getResourceId(indexWithValue, 0);
        this.fontFamily = obtainStyledAttributes.getString(indexWithValue);
        this.textAllCaps = obtainStyledAttributes.getBoolean(14, false);
        this.shadowColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, 6);
        this.shadowDx = obtainStyledAttributes.getFloat(7, 0.0f);
        this.shadowDy = obtainStyledAttributes.getFloat(8, 0.0f);
        this.shadowRadius = obtainStyledAttributes.getFloat(9, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public Typeface getFont(Context context) {
        if (this.fontResolved) {
            return this.font;
        }
        if (!context.isRestricted()) {
            try {
                Typeface font = ResourcesCompat.getFont(context, this.fontFamilyResourceId);
                this.font = font;
                if (font != null) {
                    this.font = Typeface.create(font, this.textStyle);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e) {
                Log.d(TAG, "Error loading font " + this.fontFamily, e);
            }
        }
        createFallbackTypeface();
        this.fontResolved = true;
        return this.font;
    }

    public void getFontAsync(Context context, final TextPaint textPaint, final ResourcesCompat.FontCallback fontCallback) {
        if (this.fontResolved) {
            updateTextPaintMeasureState(textPaint, this.font);
            return;
        }
        createFallbackTypeface();
        if (context.isRestricted()) {
            this.fontResolved = true;
            updateTextPaintMeasureState(textPaint, this.font);
            return;
        }
        try {
            ResourcesCompat.getFont(context, this.fontFamilyResourceId, new ResourcesCompat.FontCallback() { // from class: com.google.android.material.resources.TextAppearance.1
                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                /* renamed from: onFontRetrieved */
                public void m17x46c88379(Typeface typeface) {
                    TextAppearance textAppearance = TextAppearance.this;
                    textAppearance.font = Typeface.create(typeface, textAppearance.textStyle);
                    TextAppearance.this.updateTextPaintMeasureState(textPaint, typeface);
                    TextAppearance.this.fontResolved = true;
                    fontCallback.m17x46c88379(typeface);
                }

                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                /* renamed from: onFontRetrievalFailed */
                public void m16xb24343b7(int i) {
                    TextAppearance.this.createFallbackTypeface();
                    TextAppearance.this.fontResolved = true;
                    fontCallback.m16xb24343b7(i);
                }
            }, null);
        } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
        } catch (Exception e) {
            Log.d(TAG, "Error loading font " + this.fontFamily, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createFallbackTypeface() {
        if (this.font == null) {
            this.font = Typeface.create(this.fontFamily, this.textStyle);
        }
        if (this.font == null) {
            int i = this.typeface;
            if (i == 1) {
                this.font = Typeface.SANS_SERIF;
            } else if (i == 2) {
                this.font = Typeface.SERIF;
            } else if (i == 3) {
                this.font = Typeface.MONOSPACE;
            } else {
                this.font = Typeface.DEFAULT;
            }
            Typeface typeface = this.font;
            if (typeface != null) {
                this.font = Typeface.create(typeface, this.textStyle);
            }
        }
    }

    public void updateDrawState(Context context, TextPaint textPaint, ResourcesCompat.FontCallback fontCallback) {
        updateMeasureState(context, textPaint, fontCallback);
        ColorStateList colorStateList = this.textColor;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, this.textColor.getDefaultColor()) : -16777216);
        float f = this.shadowRadius;
        float f2 = this.shadowDx;
        float f3 = this.shadowDy;
        ColorStateList colorStateList2 = this.shadowColor;
        textPaint.setShadowLayer(f, f2, f3, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, this.shadowColor.getDefaultColor()) : 0);
    }

    public void updateMeasureState(Context context, TextPaint textPaint, ResourcesCompat.FontCallback fontCallback) {
        if (TextAppearanceConfig.shouldLoadFontSynchronously()) {
            updateTextPaintMeasureState(textPaint, getFont(context));
            return;
        }
        getFontAsync(context, textPaint, fontCallback);
        if (this.fontResolved) {
            return;
        }
        updateTextPaintMeasureState(textPaint, this.font);
    }

    public void updateTextPaintMeasureState(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i = (~typeface.getStyle()) & this.textStyle;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.textSize);
    }
}
