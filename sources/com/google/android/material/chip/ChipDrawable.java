package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.text.BidiFormatter;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes8.dex */
public class ChipDrawable extends Drawable implements TintAwareDrawable, Drawable.Callback {
    private static final boolean DEBUG = false;
    private static final int[] DEFAULT_STATE = {R.attr.state_enabled};
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    private int alpha;
    private boolean checkable;
    private Drawable checkedIcon;
    private boolean checkedIconVisible;
    private ColorStateList chipBackgroundColor;
    private float chipCornerRadius;
    private float chipEndPadding;
    private Drawable chipIcon;
    private float chipIconSize;
    private ColorStateList chipIconTint;
    private boolean chipIconVisible;
    private float chipMinHeight;
    private final Paint chipPaint;
    private float chipStartPadding;
    private ColorStateList chipStrokeColor;
    private float chipStrokeWidth;
    private Drawable closeIcon;
    private CharSequence closeIconContentDescription;
    private float closeIconEndPadding;
    private float closeIconSize;
    private float closeIconStartPadding;
    private int[] closeIconStateSet;
    private ColorStateList closeIconTint;
    private boolean closeIconVisible;
    private ColorFilter colorFilter;
    private ColorStateList compatRippleColor;
    private final Context context;
    private boolean currentChecked;
    private int currentChipBackgroundColor;
    private int currentChipStrokeColor;
    private int currentCompatRippleColor;
    private int currentTextColor;
    private int currentTint;
    private final Paint debugPaint;
    private WeakReference<Delegate> delegate;
    private final ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback() { // from class: com.google.android.material.chip.ChipDrawable.1
        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        /* renamed from: onFontRetrievalFailed */
        public void m16xb24343b7(int i) {
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        /* renamed from: onFontRetrieved */
        public void m17x46c88379(Typeface typeface) {
            ChipDrawable.this.textWidthDirty = true;
            ChipDrawable.this.onSizeChange();
            ChipDrawable.this.invalidateSelf();
        }
    };
    private final Paint.FontMetrics fontMetrics;
    private MotionSpec hideMotionSpec;
    private float iconEndPadding;
    private float iconStartPadding;
    private int maxWidth;
    private final PointF pointF;
    private CharSequence rawText;
    private final RectF rectF;
    private ColorStateList rippleColor;
    private boolean shouldDrawText;
    private MotionSpec showMotionSpec;
    private TextAppearance textAppearance;
    private float textEndPadding;
    private final TextPaint textPaint;
    private float textStartPadding;
    private float textWidth;
    private boolean textWidthDirty;
    private ColorStateList tint;
    private PorterDuffColorFilter tintFilter;
    private PorterDuff.Mode tintMode;
    private TextUtils.TruncateAt truncateAt;
    private CharSequence unicodeWrappedText;
    private boolean useCompatRipple;

    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public static ChipDrawable createFromAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        ChipDrawable chipDrawable = new ChipDrawable(context);
        chipDrawable.loadFromAttributes(attributeSet, i, i2);
        return chipDrawable;
    }

    public static ChipDrawable createFromResource(Context context, int i) {
        int next;
        try {
            XmlResourceParser xml = context.getResources().getXml(i);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            if (!TextUtils.equals(xml.getName(), "chip")) {
                throw new XmlPullParserException("Must have a <chip> start tag");
            }
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            int styleAttribute = asAttributeSet.getStyleAttribute();
            if (styleAttribute == 0) {
                styleAttribute = com.huawei.health.R.style.Widget_MaterialComponents_Chip_Entry;
            }
            return createFromAttributes(context, asAttributeSet, com.huawei.health.R.attr._2131099884_res_0x7f0600ec, styleAttribute);
        } catch (IOException | XmlPullParserException e) {
            Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load chip resource ID #0x" + Integer.toHexString(i));
            notFoundException.initCause(e);
            throw notFoundException;
        }
    }

    private ChipDrawable(Context context) {
        TextPaint textPaint = new TextPaint(1);
        this.textPaint = textPaint;
        this.chipPaint = new Paint(1);
        this.fontMetrics = new Paint.FontMetrics();
        this.rectF = new RectF();
        this.pointF = new PointF();
        this.alpha = 255;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.delegate = new WeakReference<>(null);
        this.textWidthDirty = true;
        this.context = context;
        this.rawText = "";
        textPaint.density = context.getResources().getDisplayMetrics().density;
        this.debugPaint = null;
        int[] iArr = DEFAULT_STATE;
        setState(iArr);
        setCloseIconState(iArr);
        this.shouldDrawText = true;
    }

    private void loadFromAttributes(AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(this.context, attributeSet, new int[]{R.attr.textAppearance, R.attr.ellipsize, R.attr.maxWidth, R.attr.text, R.attr.checkable, com.huawei.health.R.attr._2131099867_res_0x7f0600db, com.huawei.health.R.attr._2131099868_res_0x7f0600dc, com.huawei.health.R.attr._2131099869_res_0x7f0600dd, com.huawei.health.R.attr._2131099871_res_0x7f0600df, com.huawei.health.R.attr._2131099872_res_0x7f0600e0, com.huawei.health.R.attr._2131099873_res_0x7f0600e1, com.huawei.health.R.attr._2131099875_res_0x7f0600e3, com.huawei.health.R.attr._2131099876_res_0x7f0600e4, com.huawei.health.R.attr._2131099877_res_0x7f0600e5, com.huawei.health.R.attr._2131099878_res_0x7f0600e6, com.huawei.health.R.attr._2131099879_res_0x7f0600e7, com.huawei.health.R.attr._2131099880_res_0x7f0600e8, com.huawei.health.R.attr._2131099885_res_0x7f0600ed, com.huawei.health.R.attr._2131099886_res_0x7f0600ee, com.huawei.health.R.attr._2131099887_res_0x7f0600ef, com.huawei.health.R.attr._2131099899_res_0x7f0600fb, com.huawei.health.R.attr._2131099900_res_0x7f0600fc, com.huawei.health.R.attr._2131099901_res_0x7f0600fd, com.huawei.health.R.attr._2131099902_res_0x7f0600fe, com.huawei.health.R.attr._2131099903_res_0x7f0600ff, com.huawei.health.R.attr._2131099904_res_0x7f060100, com.huawei.health.R.attr._2131099905_res_0x7f060101, com.huawei.health.R.attr._2131100162_res_0x7f060202, com.huawei.health.R.attr._2131100611_res_0x7f0603c3, com.huawei.health.R.attr._2131100615_res_0x7f0603c7, com.huawei.health.R.attr._2131101016_res_0x7f060558, com.huawei.health.R.attr._2131101103_res_0x7f0605af, com.huawei.health.R.attr._2131101244_res_0x7f06063c, com.huawei.health.R.attr._2131101253_res_0x7f060645}, i, i2, new int[0]);
        setChipBackgroundColor(MaterialResources.getColorStateList(this.context, obtainStyledAttributes, 8));
        setChipMinHeight(obtainStyledAttributes.getDimension(16, 0.0f));
        setChipCornerRadius(obtainStyledAttributes.getDimension(9, 0.0f));
        setChipStrokeColor(MaterialResources.getColorStateList(this.context, obtainStyledAttributes, 18));
        setChipStrokeWidth(obtainStyledAttributes.getDimension(19, 0.0f));
        setRippleColor(MaterialResources.getColorStateList(this.context, obtainStyledAttributes, 30));
        setText(obtainStyledAttributes.getText(3));
        setTextAppearance(MaterialResources.getTextAppearance(this.context, obtainStyledAttributes, 0));
        int i3 = obtainStyledAttributes.getInt(1, 0);
        if (i3 == 1) {
            setEllipsize(TextUtils.TruncateAt.START);
        } else if (i3 == 2) {
            setEllipsize(TextUtils.TruncateAt.MIDDLE);
        } else if (i3 == 3) {
            setEllipsize(TextUtils.TruncateAt.END);
        }
        setChipIconVisible(obtainStyledAttributes.getBoolean(15, false));
        if (attributeSet != null && attributeSet.getAttributeValue(NAMESPACE_APP, "chipIconEnabled") != null && attributeSet.getAttributeValue(NAMESPACE_APP, "chipIconVisible") == null) {
            setChipIconVisible(obtainStyledAttributes.getBoolean(12, false));
        }
        setChipIcon(MaterialResources.getDrawable(this.context, obtainStyledAttributes, 11));
        setChipIconTint(MaterialResources.getColorStateList(this.context, obtainStyledAttributes, 14));
        setChipIconSize(obtainStyledAttributes.getDimension(13, 0.0f));
        setCloseIconVisible(obtainStyledAttributes.getBoolean(26, false));
        if (attributeSet != null && attributeSet.getAttributeValue(NAMESPACE_APP, "closeIconEnabled") != null && attributeSet.getAttributeValue(NAMESPACE_APP, "closeIconVisible") == null) {
            setCloseIconVisible(obtainStyledAttributes.getBoolean(21, false));
        }
        setCloseIcon(MaterialResources.getDrawable(this.context, obtainStyledAttributes, 20));
        setCloseIconTint(MaterialResources.getColorStateList(this.context, obtainStyledAttributes, 25));
        setCloseIconSize(obtainStyledAttributes.getDimension(23, 0.0f));
        setCheckable(obtainStyledAttributes.getBoolean(4, false));
        setCheckedIconVisible(obtainStyledAttributes.getBoolean(7, false));
        if (attributeSet != null && attributeSet.getAttributeValue(NAMESPACE_APP, "checkedIconEnabled") != null && attributeSet.getAttributeValue(NAMESPACE_APP, "checkedIconVisible") == null) {
            setCheckedIconVisible(obtainStyledAttributes.getBoolean(6, false));
        }
        setCheckedIcon(MaterialResources.getDrawable(this.context, obtainStyledAttributes, 5));
        setShowMotionSpec(MotionSpec.createFromAttribute(this.context, obtainStyledAttributes, 31));
        setHideMotionSpec(MotionSpec.createFromAttribute(this.context, obtainStyledAttributes, 27));
        setChipStartPadding(obtainStyledAttributes.getDimension(17, 0.0f));
        setIconStartPadding(obtainStyledAttributes.getDimension(29, 0.0f));
        setIconEndPadding(obtainStyledAttributes.getDimension(28, 0.0f));
        setTextStartPadding(obtainStyledAttributes.getDimension(33, 0.0f));
        setTextEndPadding(obtainStyledAttributes.getDimension(32, 0.0f));
        setCloseIconStartPadding(obtainStyledAttributes.getDimension(24, 0.0f));
        setCloseIconEndPadding(obtainStyledAttributes.getDimension(22, 0.0f));
        setChipEndPadding(obtainStyledAttributes.getDimension(10, 0.0f));
        setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(2, Integer.MAX_VALUE));
        obtainStyledAttributes.recycle();
    }

    public void setUseCompatRipple(boolean z) {
        if (this.useCompatRipple != z) {
            this.useCompatRipple = z;
            updateCompatRippleColor();
            onStateChange(getState());
        }
    }

    public boolean getUseCompatRipple() {
        return this.useCompatRipple;
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = new WeakReference<>(delegate);
    }

    protected void onSizeChange() {
        Delegate delegate = this.delegate.get();
        if (delegate != null) {
            delegate.onChipDrawableSizeChange();
        }
    }

    public void getChipTouchBounds(RectF rectF) {
        calculateChipTouchBounds(getBounds(), rectF);
    }

    public void getCloseIconTouchBounds(RectF rectF) {
        calculateCloseIconTouchBounds(getBounds(), rectF);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        float f = this.chipStartPadding;
        float calculateChipIconWidth = calculateChipIconWidth();
        float f2 = this.textStartPadding;
        float textWidth = getTextWidth();
        float f3 = this.textEndPadding;
        return Math.min(Math.round(f + calculateChipIconWidth + f2 + textWidth + f3 + calculateCloseIconWidth() + this.chipEndPadding), this.maxWidth);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    private boolean showsChipIcon() {
        return this.chipIconVisible && this.chipIcon != null;
    }

    private boolean showsCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.currentChecked;
    }

    private boolean showsCloseIcon() {
        return this.closeIconVisible && this.closeIcon != null;
    }

    private boolean canShowCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.checkable;
    }

    float calculateChipIconWidth() {
        if (showsChipIcon() || showsCheckedIcon()) {
            return this.iconStartPadding + this.chipIconSize + this.iconEndPadding;
        }
        return 0.0f;
    }

    private float getTextWidth() {
        if (!this.textWidthDirty) {
            return this.textWidth;
        }
        float calculateTextWidth = calculateTextWidth(this.unicodeWrappedText);
        this.textWidth = calculateTextWidth;
        this.textWidthDirty = false;
        return calculateTextWidth;
    }

    private float calculateTextWidth(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.textPaint.measureText(charSequence, 0, charSequence.length());
    }

    private float calculateCloseIconWidth() {
        if (showsCloseIcon()) {
            return this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding;
        }
        return 0.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int saveLayerAlpha = this.alpha < 255 ? CanvasCompat.saveLayerAlpha(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, this.alpha) : 0;
        drawChipBackground(canvas, bounds);
        drawChipStroke(canvas, bounds);
        drawCompatRipple(canvas, bounds);
        drawChipIcon(canvas, bounds);
        drawCheckedIcon(canvas, bounds);
        if (this.shouldDrawText) {
            drawText(canvas, bounds);
        }
        drawCloseIcon(canvas, bounds);
        drawDebug(canvas, bounds);
        if (this.alpha < 255) {
            canvas.restoreToCount(saveLayerAlpha);
        }
    }

    private void drawChipBackground(Canvas canvas, Rect rect) {
        this.chipPaint.setColor(this.currentChipBackgroundColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.chipPaint.setColorFilter(getTintColorFilter());
        this.rectF.set(rect);
        RectF rectF = this.rectF;
        float f = this.chipCornerRadius;
        canvas.drawRoundRect(rectF, f, f, this.chipPaint);
    }

    private void drawChipStroke(Canvas canvas, Rect rect) {
        if (this.chipStrokeWidth > 0.0f) {
            this.chipPaint.setColor(this.currentChipStrokeColor);
            this.chipPaint.setStyle(Paint.Style.STROKE);
            this.chipPaint.setColorFilter(getTintColorFilter());
            this.rectF.set(rect.left + (this.chipStrokeWidth / 2.0f), rect.top + (this.chipStrokeWidth / 2.0f), rect.right - (this.chipStrokeWidth / 2.0f), rect.bottom - (this.chipStrokeWidth / 2.0f));
            float f = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
            canvas.drawRoundRect(this.rectF, f, f, this.chipPaint);
        }
    }

    private void drawCompatRipple(Canvas canvas, Rect rect) {
        this.chipPaint.setColor(this.currentCompatRippleColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.rectF.set(rect);
        RectF rectF = this.rectF;
        float f = this.chipCornerRadius;
        canvas.drawRoundRect(rectF, f, f, this.chipPaint);
    }

    private void drawChipIcon(Canvas canvas, Rect rect) {
        if (showsChipIcon()) {
            calculateChipIconBounds(rect, this.rectF);
            float f = this.rectF.left;
            float f2 = this.rectF.top;
            canvas.translate(f, f2);
            this.chipIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.chipIcon.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void drawCheckedIcon(Canvas canvas, Rect rect) {
        if (showsCheckedIcon()) {
            calculateChipIconBounds(rect, this.rectF);
            float f = this.rectF.left;
            float f2 = this.rectF.top;
            canvas.translate(f, f2);
            this.checkedIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.checkedIcon.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void drawText(Canvas canvas, Rect rect) {
        if (this.unicodeWrappedText != null) {
            Paint.Align calculateTextOriginAndAlignment = calculateTextOriginAndAlignment(rect, this.pointF);
            calculateTextBounds(rect, this.rectF);
            if (this.textAppearance != null) {
                this.textPaint.drawableState = getState();
                this.textAppearance.updateDrawState(this.context, this.textPaint, this.fontCallback);
            }
            this.textPaint.setTextAlign(calculateTextOriginAndAlignment);
            int i = 0;
            boolean z = Math.round(getTextWidth()) > Math.round(this.rectF.width());
            if (z) {
                i = canvas.save();
                canvas.clipRect(this.rectF);
            }
            CharSequence charSequence = this.unicodeWrappedText;
            if (z && this.truncateAt != null) {
                charSequence = TextUtils.ellipsize(charSequence, this.textPaint, this.rectF.width(), this.truncateAt);
            }
            CharSequence charSequence2 = charSequence;
            canvas.drawText(charSequence2, 0, charSequence2.length(), this.pointF.x, this.pointF.y, this.textPaint);
            if (z) {
                canvas.restoreToCount(i);
            }
        }
    }

    private void drawCloseIcon(Canvas canvas, Rect rect) {
        if (showsCloseIcon()) {
            calculateCloseIconBounds(rect, this.rectF);
            float f = this.rectF.left;
            float f2 = this.rectF.top;
            canvas.translate(f, f2);
            this.closeIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.closeIcon.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void drawDebug(Canvas canvas, Rect rect) {
        Paint paint = this.debugPaint;
        if (paint != null) {
            paint.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
            canvas.drawRect(rect, this.debugPaint);
            if (showsChipIcon() || showsCheckedIcon()) {
                calculateChipIconBounds(rect, this.rectF);
                canvas.drawRect(this.rectF, this.debugPaint);
            }
            if (this.unicodeWrappedText != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.debugPaint);
            }
            if (showsCloseIcon()) {
                calculateCloseIconBounds(rect, this.rectF);
                canvas.drawRect(this.rectF, this.debugPaint);
            }
            this.debugPaint.setColor(ColorUtils.setAlphaComponent(SupportMenu.CATEGORY_MASK, 127));
            calculateChipTouchBounds(rect, this.rectF);
            canvas.drawRect(this.rectF, this.debugPaint);
            this.debugPaint.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
            calculateCloseIconTouchBounds(rect, this.rectF);
            canvas.drawRect(this.rectF, this.debugPaint);
        }
    }

    private void calculateChipIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsChipIcon() || showsCheckedIcon()) {
            float f = this.chipStartPadding + this.iconStartPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = rect.left + f;
                rectF.right = rectF.left + this.chipIconSize;
            } else {
                rectF.right = rect.right - f;
                rectF.left = rectF.right - this.chipIconSize;
            }
            rectF.top = rect.exactCenterY() - (this.chipIconSize / 2.0f);
            rectF.bottom = rectF.top + this.chipIconSize;
        }
    }

    Paint.Align calculateTextOriginAndAlignment(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.unicodeWrappedText != null) {
            float calculateChipIconWidth = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                pointF.x = rect.left + calculateChipIconWidth;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - calculateChipIconWidth;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - calculateTextCenterFromBaseline();
        }
        return align;
    }

    private float calculateTextCenterFromBaseline() {
        this.textPaint.getFontMetrics(this.fontMetrics);
        return (this.fontMetrics.descent + this.fontMetrics.ascent) / 2.0f;
    }

    private void calculateTextBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.unicodeWrappedText != null) {
            float calculateChipIconWidth = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
            float calculateCloseIconWidth = this.chipEndPadding + calculateCloseIconWidth() + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = rect.left + calculateChipIconWidth;
                rectF.right = rect.right - calculateCloseIconWidth;
            } else {
                rectF.left = rect.left + calculateCloseIconWidth;
                rectF.right = rect.right - calculateChipIconWidth;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private void calculateCloseIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = rect.right - f;
                rectF.left = rectF.right - this.closeIconSize;
            } else {
                rectF.left = rect.left + f;
                rectF.right = rectF.left + this.closeIconSize;
            }
            rectF.top = rect.exactCenterY() - (this.closeIconSize / 2.0f);
            rectF.bottom = rectF.top + this.closeIconSize;
        }
    }

    private void calculateChipTouchBounds(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = rect.right - f;
            } else {
                rectF.left = rect.left + f;
            }
        }
    }

    private void calculateCloseIconTouchBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = rect.right;
                rectF.left = rectF.right - f;
            } else {
                rectF.left = rect.left;
                rectF.right = rect.left + f;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return isStateful(this.chipBackgroundColor) || isStateful(this.chipStrokeColor) || (this.useCompatRipple && isStateful(this.compatRippleColor)) || isStateful(this.textAppearance) || canShowCheckedIcon() || isStateful(this.chipIcon) || isStateful(this.checkedIcon) || isStateful(this.tint);
    }

    public boolean isCloseIconStateful() {
        return isStateful(this.closeIcon);
    }

    public boolean setCloseIconState(int[] iArr) {
        if (Arrays.equals(this.closeIconStateSet, iArr)) {
            return false;
        }
        this.closeIconStateSet = iArr;
        if (showsCloseIcon()) {
            return onStateChange(getState(), iArr);
        }
        return false;
    }

    public int[] getCloseIconState() {
        return this.closeIconStateSet;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        return onStateChange(iArr, getCloseIconState());
    }

    private boolean onStateChange(int[] iArr, int[] iArr2) {
        boolean z;
        boolean onStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.chipBackgroundColor;
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, this.currentChipBackgroundColor) : 0;
        boolean z2 = true;
        if (this.currentChipBackgroundColor != colorForState) {
            this.currentChipBackgroundColor = colorForState;
            onStateChange = true;
        }
        ColorStateList colorStateList2 = this.chipStrokeColor;
        int colorForState2 = colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.currentChipStrokeColor) : 0;
        if (this.currentChipStrokeColor != colorForState2) {
            this.currentChipStrokeColor = colorForState2;
            onStateChange = true;
        }
        ColorStateList colorStateList3 = this.compatRippleColor;
        int colorForState3 = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.currentCompatRippleColor) : 0;
        if (this.currentCompatRippleColor != colorForState3) {
            this.currentCompatRippleColor = colorForState3;
            if (this.useCompatRipple) {
                onStateChange = true;
            }
        }
        TextAppearance textAppearance = this.textAppearance;
        int colorForState4 = (textAppearance == null || textAppearance.textColor == null) ? 0 : this.textAppearance.textColor.getColorForState(iArr, this.currentTextColor);
        if (this.currentTextColor != colorForState4) {
            this.currentTextColor = colorForState4;
            onStateChange = true;
        }
        boolean z3 = hasState(getState(), R.attr.state_checked) && this.checkable;
        if (this.currentChecked == z3 || this.checkedIcon == null) {
            z = false;
        } else {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.currentChecked = z3;
            if (calculateChipIconWidth != calculateChipIconWidth()) {
                onStateChange = true;
                z = true;
            } else {
                z = false;
                onStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.tint;
        int colorForState5 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.currentTint) : 0;
        if (this.currentTint != colorForState5) {
            this.currentTint = colorForState5;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, this.tintMode);
        } else {
            z2 = onStateChange;
        }
        if (isStateful(this.chipIcon)) {
            z2 |= this.chipIcon.setState(iArr);
        }
        if (isStateful(this.checkedIcon)) {
            z2 |= this.checkedIcon.setState(iArr);
        }
        if (isStateful(this.closeIcon)) {
            z2 |= this.closeIcon.setState(iArr2);
        }
        if (z2) {
            invalidateSelf();
        }
        if (z) {
            onSizeChange();
        }
        return z2;
    }

    private static boolean isStateful(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private static boolean isStateful(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private static boolean isStateful(TextAppearance textAppearance) {
        return (textAppearance == null || textAppearance.textColor == null || !textAppearance.textColor.isStateful()) ? false : true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (showsChipIcon()) {
            onLayoutDirectionChanged |= this.chipIcon.setLayoutDirection(i);
        }
        if (showsCheckedIcon()) {
            onLayoutDirectionChanged |= this.checkedIcon.setLayoutDirection(i);
        }
        if (showsCloseIcon()) {
            onLayoutDirectionChanged |= this.closeIcon.setLayoutDirection(i);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (showsChipIcon()) {
            onLevelChange |= this.chipIcon.setLevel(i);
        }
        if (showsCheckedIcon()) {
            onLevelChange |= this.checkedIcon.setLevel(i);
        }
        if (showsCloseIcon()) {
            onLevelChange |= this.closeIcon.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (showsChipIcon()) {
            visible |= this.chipIcon.setVisible(z, z2);
        }
        if (showsCheckedIcon()) {
            visible |= this.checkedIcon.setVisible(z, z2);
        }
        if (showsCloseIcon()) {
            visible |= this.closeIcon.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.alpha != i) {
            this.alpha = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.colorFilter != colorFilter) {
            this.colorFilter = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.tint != colorStateList) {
            this.tint = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.tintMode != mode) {
            this.tintMode = mode;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.chipCornerRadius);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.chipCornerRadius);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    private void unapplyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void applyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.closeIcon) {
                if (drawable.isStateful()) {
                    drawable.setState(getCloseIconState());
                }
                DrawableCompat.setTintList(drawable, this.closeIconTint);
            } else if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    private ColorFilter getTintColorFilter() {
        ColorFilter colorFilter = this.colorFilter;
        return colorFilter != null ? colorFilter : this.tintFilter;
    }

    private void updateCompatRippleColor() {
        this.compatRippleColor = this.useCompatRipple ? RippleUtils.convertToRippleDrawableColor(this.rippleColor) : null;
    }

    private static boolean hasState(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public ColorStateList getChipBackgroundColor() {
        return this.chipBackgroundColor;
    }

    public void setChipBackgroundColorResource(int i) {
        setChipBackgroundColor(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        if (this.chipBackgroundColor != colorStateList) {
            this.chipBackgroundColor = colorStateList;
            onStateChange(getState());
        }
    }

    public float getChipMinHeight() {
        return this.chipMinHeight;
    }

    public void setChipMinHeightResource(int i) {
        setChipMinHeight(this.context.getResources().getDimension(i));
    }

    public void setChipMinHeight(float f) {
        if (this.chipMinHeight != f) {
            this.chipMinHeight = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getChipCornerRadius() {
        return this.chipCornerRadius;
    }

    public void setChipCornerRadiusResource(int i) {
        setChipCornerRadius(this.context.getResources().getDimension(i));
    }

    public void setChipCornerRadius(float f) {
        if (this.chipCornerRadius != f) {
            this.chipCornerRadius = f;
            invalidateSelf();
        }
    }

    public ColorStateList getChipStrokeColor() {
        return this.chipStrokeColor;
    }

    public void setChipStrokeColorResource(int i) {
        setChipStrokeColor(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        if (this.chipStrokeColor != colorStateList) {
            this.chipStrokeColor = colorStateList;
            onStateChange(getState());
        }
    }

    public float getChipStrokeWidth() {
        return this.chipStrokeWidth;
    }

    public void setChipStrokeWidthResource(int i) {
        setChipStrokeWidth(this.context.getResources().getDimension(i));
    }

    public void setChipStrokeWidth(float f) {
        if (this.chipStrokeWidth != f) {
            this.chipStrokeWidth = f;
            this.chipPaint.setStrokeWidth(f);
            invalidateSelf();
        }
    }

    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public void setRippleColorResource(int i) {
        setRippleColor(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            updateCompatRippleColor();
            onStateChange(getState());
        }
    }

    public CharSequence getText() {
        return this.rawText;
    }

    public void setTextResource(int i) {
        setText(this.context.getResources().getString(i));
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (this.rawText != charSequence) {
            this.rawText = charSequence;
            this.unicodeWrappedText = BidiFormatter.getInstance().unicodeWrap(charSequence);
            this.textWidthDirty = true;
            invalidateSelf();
            onSizeChange();
        }
    }

    public TextAppearance getTextAppearance() {
        return this.textAppearance;
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(new TextAppearance(this.context, i));
    }

    public void setTextAppearance(TextAppearance textAppearance) {
        if (this.textAppearance != textAppearance) {
            this.textAppearance = textAppearance;
            if (textAppearance != null) {
                textAppearance.updateMeasureState(this.context, this.textPaint, this.fontCallback);
                this.textWidthDirty = true;
            }
            onStateChange(getState());
            onSizeChange();
        }
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.truncateAt;
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        this.truncateAt = truncateAt;
    }

    public boolean isChipIconVisible() {
        return this.chipIconVisible;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public void setChipIconVisible(int i) {
        setChipIconVisible(this.context.getResources().getBoolean(i));
    }

    public void setChipIconVisible(boolean z) {
        if (this.chipIconVisible != z) {
            boolean showsChipIcon = showsChipIcon();
            this.chipIconVisible = z;
            boolean showsChipIcon2 = showsChipIcon();
            if (showsChipIcon != showsChipIcon2) {
                if (showsChipIcon2) {
                    applyChildDrawable(this.chipIcon);
                } else {
                    unapplyChildDrawable(this.chipIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    public Drawable getChipIcon() {
        Drawable drawable = this.chipIcon;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    public void setChipIconResource(int i) {
        setChipIcon(AppCompatResources.getDrawable(this.context, i));
    }

    public void setChipIcon(Drawable drawable) {
        Drawable chipIcon = getChipIcon();
        if (chipIcon != drawable) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.chipIcon = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            unapplyChildDrawable(chipIcon);
            if (showsChipIcon()) {
                applyChildDrawable(this.chipIcon);
            }
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public ColorStateList getChipIconTint() {
        return this.chipIconTint;
    }

    public void setChipIconTintResource(int i) {
        setChipIconTint(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        if (this.chipIconTint != colorStateList) {
            this.chipIconTint = colorStateList;
            if (showsChipIcon()) {
                DrawableCompat.setTintList(this.chipIcon, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float getChipIconSize() {
        return this.chipIconSize;
    }

    public void setChipIconSizeResource(int i) {
        setChipIconSize(this.context.getResources().getDimension(i));
    }

    public void setChipIconSize(float f) {
        if (this.chipIconSize != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.chipIconSize = f;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public boolean isCloseIconVisible() {
        return this.closeIconVisible;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(this.context.getResources().getBoolean(i));
    }

    public void setCloseIconVisible(boolean z) {
        if (this.closeIconVisible != z) {
            boolean showsCloseIcon = showsCloseIcon();
            this.closeIconVisible = z;
            boolean showsCloseIcon2 = showsCloseIcon();
            if (showsCloseIcon != showsCloseIcon2) {
                if (showsCloseIcon2) {
                    applyChildDrawable(this.closeIcon);
                } else {
                    unapplyChildDrawable(this.closeIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    public Drawable getCloseIcon() {
        Drawable drawable = this.closeIcon;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    public void setCloseIconResource(int i) {
        setCloseIcon(AppCompatResources.getDrawable(this.context, i));
    }

    public void setCloseIcon(Drawable drawable) {
        Drawable closeIcon = getCloseIcon();
        if (closeIcon != drawable) {
            float calculateCloseIconWidth = calculateCloseIconWidth();
            this.closeIcon = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            float calculateCloseIconWidth2 = calculateCloseIconWidth();
            unapplyChildDrawable(closeIcon);
            if (showsCloseIcon()) {
                applyChildDrawable(this.closeIcon);
            }
            invalidateSelf();
            if (calculateCloseIconWidth != calculateCloseIconWidth2) {
                onSizeChange();
            }
        }
    }

    public ColorStateList getCloseIconTint() {
        return this.closeIconTint;
    }

    public void setCloseIconTintResource(int i) {
        setCloseIconTint(AppCompatResources.getColorStateList(this.context, i));
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        if (this.closeIconTint != colorStateList) {
            this.closeIconTint = colorStateList;
            if (showsCloseIcon()) {
                DrawableCompat.setTintList(this.closeIcon, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float getCloseIconSize() {
        return this.closeIconSize;
    }

    public void setCloseIconSizeResource(int i) {
        setCloseIconSize(this.context.getResources().getDimension(i));
    }

    public void setCloseIconSize(float f) {
        if (this.closeIconSize != f) {
            this.closeIconSize = f;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        if (this.closeIconContentDescription != charSequence) {
            this.closeIconContentDescription = BidiFormatter.getInstance().unicodeWrap(charSequence);
            invalidateSelf();
        }
    }

    public CharSequence getCloseIconContentDescription() {
        return this.closeIconContentDescription;
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    public void setCheckableResource(int i) {
        setCheckable(this.context.getResources().getBoolean(i));
    }

    public void setCheckable(boolean z) {
        if (this.checkable != z) {
            this.checkable = z;
            float calculateChipIconWidth = calculateChipIconWidth();
            if (!z && this.currentChecked) {
                this.currentChecked = false;
            }
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public boolean isCheckedIconVisible() {
        return this.checkedIconVisible;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public void setCheckedIconVisible(int i) {
        setCheckedIconVisible(this.context.getResources().getBoolean(i));
    }

    public void setCheckedIconVisible(boolean z) {
        if (this.checkedIconVisible != z) {
            boolean showsCheckedIcon = showsCheckedIcon();
            this.checkedIconVisible = z;
            boolean showsCheckedIcon2 = showsCheckedIcon();
            if (showsCheckedIcon != showsCheckedIcon2) {
                if (showsCheckedIcon2) {
                    applyChildDrawable(this.checkedIcon);
                } else {
                    unapplyChildDrawable(this.checkedIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(this.context.getResources().getBoolean(i));
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    public void setCheckedIconResource(int i) {
        setCheckedIcon(AppCompatResources.getDrawable(this.context, i));
    }

    public void setCheckedIcon(Drawable drawable) {
        if (this.checkedIcon != drawable) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.checkedIcon = drawable;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            unapplyChildDrawable(this.checkedIcon);
            applyChildDrawable(this.checkedIcon);
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(MotionSpec.createFromResource(this.context, i));
    }

    public void setShowMotionSpec(MotionSpec motionSpec) {
        this.showMotionSpec = motionSpec;
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(MotionSpec.createFromResource(this.context, i));
    }

    public void setHideMotionSpec(MotionSpec motionSpec) {
        this.hideMotionSpec = motionSpec;
    }

    public float getChipStartPadding() {
        return this.chipStartPadding;
    }

    public void setChipStartPaddingResource(int i) {
        setChipStartPadding(this.context.getResources().getDimension(i));
    }

    public void setChipStartPadding(float f) {
        if (this.chipStartPadding != f) {
            this.chipStartPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getIconStartPadding() {
        return this.iconStartPadding;
    }

    public void setIconStartPaddingResource(int i) {
        setIconStartPadding(this.context.getResources().getDimension(i));
    }

    public void setIconStartPadding(float f) {
        if (this.iconStartPadding != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.iconStartPadding = f;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public float getIconEndPadding() {
        return this.iconEndPadding;
    }

    public void setIconEndPaddingResource(int i) {
        setIconEndPadding(this.context.getResources().getDimension(i));
    }

    public void setIconEndPadding(float f) {
        if (this.iconEndPadding != f) {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.iconEndPadding = f;
            float calculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (calculateChipIconWidth != calculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public float getTextStartPadding() {
        return this.textStartPadding;
    }

    public void setTextStartPaddingResource(int i) {
        setTextStartPadding(this.context.getResources().getDimension(i));
    }

    public void setTextStartPadding(float f) {
        if (this.textStartPadding != f) {
            this.textStartPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getTextEndPadding() {
        return this.textEndPadding;
    }

    public void setTextEndPaddingResource(int i) {
        setTextEndPadding(this.context.getResources().getDimension(i));
    }

    public void setTextEndPadding(float f) {
        if (this.textEndPadding != f) {
            this.textEndPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getCloseIconStartPadding() {
        return this.closeIconStartPadding;
    }

    public void setCloseIconStartPaddingResource(int i) {
        setCloseIconStartPadding(this.context.getResources().getDimension(i));
    }

    public void setCloseIconStartPadding(float f) {
        if (this.closeIconStartPadding != f) {
            this.closeIconStartPadding = f;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public float getCloseIconEndPadding() {
        return this.closeIconEndPadding;
    }

    public void setCloseIconEndPaddingResource(int i) {
        setCloseIconEndPadding(this.context.getResources().getDimension(i));
    }

    public void setCloseIconEndPadding(float f) {
        if (this.closeIconEndPadding != f) {
            this.closeIconEndPadding = f;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public float getChipEndPadding() {
        return this.chipEndPadding;
    }

    public void setChipEndPaddingResource(int i) {
        setChipEndPadding(this.context.getResources().getDimension(i));
    }

    public void setChipEndPadding(float f) {
        if (this.chipEndPadding != f) {
            this.chipEndPadding = f;
            invalidateSelf();
            onSizeChange();
        }
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setMaxWidth(int i) {
        this.maxWidth = i;
    }

    boolean shouldDrawText() {
        return this.shouldDrawText;
    }

    void setShouldDrawText(boolean z) {
        this.shouldDrawText = z;
    }
}
