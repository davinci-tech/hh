package com.github.mikephil.charting.utils;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;

/* loaded from: classes2.dex */
public class Fill {
    private int mAlpha;
    private Integer mColor;
    protected Drawable mDrawable;
    private Integer mFinalColor;
    private int[] mGradientColors;
    private float[] mGradientPositions;
    private Type mType;

    public enum Direction {
        DOWN,
        UP,
        RIGHT,
        LEFT
    }

    public enum Type {
        EMPTY,
        COLOR,
        LINEAR_GRADIENT,
        DRAWABLE
    }

    public Fill() {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
    }

    public Fill(int i) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.COLOR;
        this.mColor = Integer.valueOf(i);
        calculateFinalColor();
    }

    public Fill(int i, int i2) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.LINEAR_GRADIENT;
        this.mGradientColors = new int[]{i, i2};
    }

    public Fill(int[] iArr) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.LINEAR_GRADIENT;
        this.mGradientColors = iArr;
    }

    public Fill(int[] iArr, float[] fArr) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.LINEAR_GRADIENT;
        this.mGradientColors = iArr;
        this.mGradientPositions = fArr;
    }

    public Fill(Drawable drawable) {
        this.mType = Type.EMPTY;
        this.mColor = null;
        this.mFinalColor = null;
        this.mAlpha = 255;
        this.mType = Type.DRAWABLE;
        this.mDrawable = drawable;
    }

    public Type getType() {
        return this.mType;
    }

    public void setType(Type type) {
        this.mType = type;
    }

    public Integer getColor() {
        return this.mColor;
    }

    public void setColor(int i) {
        this.mColor = Integer.valueOf(i);
        calculateFinalColor();
    }

    public int[] getGradientColors() {
        return this.mGradientColors;
    }

    public void setGradientColors(int[] iArr) {
        this.mGradientColors = iArr;
    }

    public float[] getGradientPositions() {
        return this.mGradientPositions;
    }

    public void setGradientPositions(float[] fArr) {
        this.mGradientPositions = fArr;
    }

    public void setGradientColors(int i, int i2) {
        this.mGradientColors = new int[]{i, i2};
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(int i) {
        this.mAlpha = i;
        calculateFinalColor();
    }

    private void calculateFinalColor() {
        if (this.mColor == null) {
            this.mFinalColor = null;
        } else {
            this.mFinalColor = Integer.valueOf((((int) Math.floor((((r0.intValue() >> 24) / 255.0d) * (this.mAlpha / 255.0d)) * 255.0d)) << 24) | (this.mColor.intValue() & ViewCompat.MEASURED_SIZE_MASK));
        }
    }

    /* renamed from: com.github.mikephil.charting.utils.Fill$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$utils$Fill$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$github$mikephil$charting$utils$Fill$Type = iArr;
            try {
                iArr[Type.EMPTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$utils$Fill$Type[Type.COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$utils$Fill$Type[Type.LINEAR_GRADIENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$utils$Fill$Type[Type.DRAWABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void fillRect(Canvas canvas, Paint paint, float f, float f2, float f3, float f4, Direction direction) {
        float f5;
        float f6;
        Drawable drawable;
        int i = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$utils$Fill$Type[this.mType.ordinal()];
        if (i == 2) {
            if (this.mFinalColor == null) {
                return;
            }
            if (isClipPathSupported()) {
                int save = canvas.save();
                canvas.clipRect(f, f2, f3, f4);
                canvas.drawColor(this.mFinalColor.intValue());
                canvas.restoreToCount(save);
                return;
            }
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.mFinalColor.intValue());
            canvas.drawRect(f, f2, f3, f4, paint);
            paint.setColor(color);
            paint.setStyle(style);
            return;
        }
        if (i != 3) {
            if (i == 4 && (drawable = this.mDrawable) != null) {
                drawable.setBounds((int) f, (int) f2, (int) f3, (int) f4);
                this.mDrawable.draw(canvas);
                return;
            }
            return;
        }
        if (this.mGradientColors == null) {
            return;
        }
        if (direction == Direction.RIGHT) {
            f5 = f3;
        } else {
            Direction direction2 = Direction.LEFT;
            f5 = f;
        }
        float f7 = (int) f5;
        if (direction == Direction.UP) {
            f6 = f4;
        } else {
            Direction direction3 = Direction.DOWN;
            f6 = f2;
        }
        paint.setShader(new LinearGradient(f7, (int) f6, (int) ((direction != Direction.RIGHT && direction == Direction.LEFT) ? f3 : f), (int) ((direction != Direction.UP && direction == Direction.DOWN) ? f4 : f2), this.mGradientColors, this.mGradientPositions, Shader.TileMode.MIRROR));
        canvas.drawRect(f, f2, f3, f4, paint);
    }

    public void fillPath(Canvas canvas, Path path, Paint paint, RectF rectF) {
        int i = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$utils$Fill$Type[this.mType.ordinal()];
        if (i != 2) {
            if (i == 3) {
                if (this.mGradientColors == null) {
                    return;
                }
                paint.setShader(new LinearGradient(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.mGradientColors, this.mGradientPositions, Shader.TileMode.MIRROR));
                canvas.drawPath(path, paint);
                return;
            }
            if (i == 4 && this.mDrawable != null) {
                ensureClipPathSupported();
                int save = canvas.save();
                canvas.clipPath(path);
                this.mDrawable.setBounds(rectF == null ? 0 : (int) rectF.left, rectF != null ? (int) rectF.top : 0, rectF == null ? canvas.getWidth() : (int) rectF.right, rectF == null ? canvas.getHeight() : (int) rectF.bottom);
                this.mDrawable.draw(canvas);
                canvas.restoreToCount(save);
                return;
            }
            return;
        }
        if (this.mFinalColor == null) {
            return;
        }
        if (rectF != null && isClipPathSupported()) {
            int save2 = canvas.save();
            canvas.clipPath(path);
            canvas.drawColor(this.mFinalColor.intValue());
            canvas.restoreToCount(save2);
            return;
        }
        Paint.Style style = paint.getStyle();
        int color = paint.getColor();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.mFinalColor.intValue());
        canvas.drawPath(path, paint);
        paint.setColor(color);
        paint.setStyle(style);
    }

    private boolean isClipPathSupported() {
        return Utils.getSDKInt() >= 18;
    }

    private void ensureClipPathSupported() {
        if (Utils.getSDKInt() >= 18) {
            return;
        }
        throw new RuntimeException("Fill-drawables not (yet) supported below API level 18, this code was run on API level " + Utils.getSDKInt() + ".");
    }
}
