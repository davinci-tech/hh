package com.huawei.animationkit.computationalwallpaper.vectordrawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.collection.ArrayMap;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.operation.ble.BleConstants;
import defpackage.aek;
import defpackage.aem;
import defpackage.aes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes8.dex */
public class VectorDrawableCompat extends VectorDrawableCommon {
    static final PorterDuff.Mode c = PorterDuff.Mode.SRC_IN;

    /* renamed from: a, reason: collision with root package name */
    private ColorFilter f1857a;
    private boolean b;
    private PorterDuffColorFilter d;
    private boolean e;
    private e f;
    private final float[] h;
    private final Matrix i;
    private final Rect j;

    public static abstract class VObject {
        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            return false;
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    VectorDrawableCompat() {
        this.e = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.f = new e();
    }

    VectorDrawableCompat(e eVar) {
        this.e = true;
        this.h = new float[9];
        this.i = new Matrix();
        this.j = new Rect();
        this.f = eVar;
        this.d = gt_(this.d, eVar.f, eVar.n);
    }

    public void gs_(Matrix matrix) {
        this.f.m.f1859a = new Matrix(matrix);
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
            return this;
        }
        if (!this.b && super.mutate() == this) {
            this.f = new e(this.f);
            this.b = true;
        }
        return this;
    }

    public a b() {
        return this.f.m.h;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.mDelegateDrawable != null) {
            return new h(this.mDelegateDrawable.getConstantState());
        }
        this.f.j = getChangingConfigurations();
        return this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas);
            return;
        }
        copyBounds(this.j);
        if (this.j.width() <= 0 || this.j.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f1857a;
        if (colorFilter == null) {
            colorFilter = this.d;
        }
        canvas.getMatrix(this.i);
        this.i.getValues(this.h);
        float abs = Math.abs(this.h[0]);
        float abs2 = Math.abs(this.h[4]);
        float abs3 = Math.abs(this.h[1]);
        float abs4 = Math.abs(this.h[3]);
        if (abs3 != 0.0f || abs4 != 0.0f) {
            abs = 1.0f;
            abs2 = 1.0f;
        }
        int min = Math.min(2048, (int) (this.j.width() * abs));
        int min2 = Math.min(2048, (int) (this.j.height() * abs2));
        if (min <= 0 || min2 <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(this.j.left, this.j.top);
        if (a()) {
            canvas.translate(this.j.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.j.offsetTo(0, 0);
        this.f.e(min, min2);
        if (!this.e) {
            this.f.b(min, min2);
        } else if (!this.f.a()) {
            this.f.b(min, min2);
            this.f.d();
        }
        this.f.gF_(canvas, colorFilter, this.j);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }
        return this.f.m.e();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(i);
        } else if (this.f.m.e() != i) {
            this.f.m.e(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter);
        } else {
            this.f1857a = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.getColorFilter(this.mDelegateDrawable);
        }
        return this.f1857a;
    }

    PorterDuffColorFilter gt_(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable, com.huawei.animationkit.computationalwallpaper.vectordrawable.TintAwareDrawable
    public void setTint(int i) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable, com.huawei.animationkit.computationalwallpaper.vectordrawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, colorStateList);
            return;
        }
        e eVar = this.f;
        if (eVar.f != colorStateList) {
            eVar.f = colorStateList;
            this.d = gt_(this.d, colorStateList, eVar.n);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, com.huawei.animationkit.computationalwallpaper.vectordrawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, mode);
            return;
        }
        e eVar = this.f;
        if (eVar.n != mode) {
            eVar.n = mode;
            this.d = gt_(this.d, eVar.f, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        e eVar;
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }
        return super.isStateful() || ((eVar = this.f) != null && (eVar.c() || (this.f.f != null && this.f.f.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z;
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(iArr);
        }
        e eVar = this.f;
        if (eVar.f == null || eVar.n == null) {
            z = false;
        } else {
            this.d = gt_(this.d, eVar.f, eVar.n);
            invalidateSelf();
            z = true;
        }
        if (!eVar.c() || !eVar.e(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }
        return (int) this.f.m.b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }
        return (int) this.f.m.c;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        if (this.mDelegateDrawable == null) {
            return false;
        }
        DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
        }
        return this.f.c;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, z);
        } else {
            this.f.c = z;
        }
    }

    public static VectorDrawableCompat d(String str) throws XmlPullParserException, IOException {
        int next;
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new InputStreamReader(new FileInputStream(new File(str))));
            AttributeSet asAttributeSet = Xml.asAttributeSet(newPullParser);
            do {
                next = newPullParser.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            return gp_(null, newPullParser, asAttributeSet, null);
        } catch (IOException | XmlPullParserException e2) {
            Log.e("VectorDrawableCompat", "parser error");
            throw e2;
        }
    }

    public static VectorDrawableCompat gp_(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
        vectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
        return vectorDrawableCompat;
    }

    static int b(int i, float f) {
        return (((int) (Color.alpha(i) * f)) << 24) | (16777215 & i);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        e eVar = this.f;
        eVar.m = new b();
        c(xmlPullParser);
        eVar.j = getChangingConfigurations();
        eVar.d = true;
        gq_(resources, xmlPullParser, attributeSet, theme);
        this.d = gt_(this.d, eVar.f, eVar.n);
    }

    private static PorterDuff.Mode gr_(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    private void c(XmlPullParser xmlPullParser) throws XmlPullParserException {
        e eVar = this.f;
        b bVar = eVar.m;
        eVar.n = gr_(aes.b(xmlPullParser, "android:tintMode", -1), PorterDuff.Mode.SRC_IN);
        eVar.c = aes.b(xmlPullParser, "android:autoMirrored", eVar.c);
        bVar.n = aes.d(xmlPullParser, "android:viewportWidth", bVar.n);
        bVar.l = aes.d(xmlPullParser, "android:viewportHeight", bVar.l);
        if (bVar.n <= 0.0f) {
            throw new XmlPullParserException(aes.d(xmlPullParser) + "<vector> tag requires viewportWidth > 0");
        }
        if (bVar.l <= 0.0f) {
            throw new XmlPullParserException(aes.d(xmlPullParser) + "<vector> tag requires viewportHeight > 0");
        }
        bVar.b = aes.a(xmlPullParser, "android:width", bVar.b);
        bVar.c = aes.a(xmlPullParser, "android:height", bVar.c);
        if (bVar.b <= 0.0f) {
            throw new XmlPullParserException(aes.d(xmlPullParser) + "<vector> tag requires width > 0");
        }
        if (bVar.c <= 0.0f) {
            throw new XmlPullParserException(aes.d(xmlPullParser) + "<vector> tag requires height > 0");
        }
        bVar.e(aes.d(xmlPullParser, "android:alpha", bVar.a()));
        String b2 = aes.b(xmlPullParser, "android:name");
        if (b2 != null) {
            bVar.i = b2;
            bVar.g.put(b2, bVar);
        }
    }

    private void gq_(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        e eVar = this.f;
        b bVar = eVar.m;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(bVar.h);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth();
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth + 1 || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                a aVar = (a) arrayDeque.peek();
                if (BleConstants.KEY_PATH.equals(name)) {
                    d dVar = new d();
                    dVar.gy_(resources, attributeSet, theme, xmlPullParser);
                    aVar.d.add(dVar);
                    if (dVar.getPathName() != null) {
                        bVar.g.put(dVar.getPathName(), dVar);
                    }
                    eVar.j = dVar.mChangingConfigurations | eVar.j;
                    z = false;
                } else if ("clip-path".equals(name)) {
                    c cVar = new c();
                    cVar.gu_(resources, attributeSet, theme, xmlPullParser);
                    aVar.d.add(cVar);
                    if (cVar.getPathName() != null) {
                        bVar.g.put(cVar.getPathName(), cVar);
                    }
                    eVar.j = cVar.mChangingConfigurations | eVar.j;
                } else if (MessageConstant.GROUP_MEDAL_TYPE.equals(name)) {
                    a aVar2 = new a();
                    aVar2.gz_(resources, attributeSet, theme, xmlPullParser);
                    aVar.d.add(aVar2);
                    arrayDeque.push(aVar2);
                    if (aVar2.d() != null) {
                        bVar.g.put(aVar2.d(), aVar2);
                    }
                    eVar.j = aVar2.b | eVar.j;
                }
            } else if (eventType == 3 && MessageConstant.GROUP_MEDAL_TYPE.equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    public void c(boolean z) {
        this.e = z;
    }

    private boolean a() {
        return isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.f.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    static class h extends Drawable.ConstantState {
        private final Drawable.ConstantState e;

        public h(Drawable.ConstantState constantState) {
            this.e = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.e.newDrawable();
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.e.newDrawable(resources);
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.e.newDrawable(resources, theme);
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.e.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.e.getChangingConfigurations();
        }
    }

    static class e extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        boolean f1861a;
        int b;
        boolean c;
        boolean d;
        Bitmap e;
        ColorStateList f;
        PorterDuff.Mode g;
        ColorStateList h;
        Paint i;
        int j;
        b m;
        PorterDuff.Mode n;

        public e(e eVar) {
            this.f = null;
            this.n = VectorDrawableCompat.c;
            if (eVar != null) {
                this.j = eVar.j;
                this.m = new b(eVar.m);
                if (eVar.m.d != null) {
                    this.m.d = new Paint(eVar.m.d);
                }
                if (eVar.m.j != null) {
                    this.m.j = new Paint(eVar.m.j);
                }
                this.f = eVar.f;
                this.n = eVar.n;
                this.c = eVar.c;
            }
        }

        public void gF_(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.e, (Rect) null, rect, gG_(colorFilter));
        }

        public boolean b() {
            return this.m.e() < 255;
        }

        public Paint gG_(ColorFilter colorFilter) {
            if (!b() && colorFilter == null) {
                return null;
            }
            if (this.i == null) {
                Paint paint = new Paint();
                this.i = paint;
                paint.setFilterBitmap(true);
            }
            this.i.setAlpha(this.m.e());
            this.i.setColorFilter(colorFilter);
            return this.i;
        }

        public void b(int i, int i2) {
            this.e.eraseColor(0);
            this.m.gE_(new Canvas(this.e), i, i2, null);
        }

        public void e(int i, int i2) {
            if (this.e == null || !c(i, i2)) {
                this.e = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.d = true;
            }
        }

        public boolean c(int i, int i2) {
            return i == this.e.getWidth() && i2 == this.e.getHeight();
        }

        public boolean a() {
            return !this.d && this.h == this.f && this.g == this.n && this.f1861a == this.c && this.b == this.m.e();
        }

        public void d() {
            this.h = this.f;
            this.g = this.n;
            this.b = this.m.e();
            this.f1861a = this.c;
            this.d = false;
        }

        public e() {
            this.f = null;
            this.n = VectorDrawableCompat.c;
            this.m = new b();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.j;
        }

        public boolean c() {
            return this.m.d();
        }

        public boolean e(int[] iArr) {
            boolean b = this.m.b(iArr);
            this.d |= b;
            return b;
        }
    }

    static class b {
        private static final Matrix o = new Matrix();

        /* renamed from: a, reason: collision with root package name */
        Matrix f1859a;
        float b;
        float c;
        Paint d;
        Boolean e;
        int f;
        final ArrayMap<String, Object> g;
        final a h;
        String i;
        Paint j;
        private final Matrix k;
        float l;
        private int m;
        float n;
        private final Path p;
        private final Path r;
        private PathMeasure t;

        private static float a(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        public b() {
            this.k = new Matrix();
            this.b = 0.0f;
            this.c = 0.0f;
            this.n = 0.0f;
            this.l = 0.0f;
            this.f = 255;
            this.i = null;
            this.e = null;
            this.g = new ArrayMap<>();
            this.h = new a();
            this.r = new Path();
            this.p = new Path();
        }

        public void e(int i) {
            this.f = i;
        }

        public int e() {
            return this.f;
        }

        public void e(float f) {
            e((int) (f * 255.0f));
        }

        public float a() {
            return e() / 255.0f;
        }

        public b(b bVar) {
            this.k = new Matrix();
            this.b = 0.0f;
            this.c = 0.0f;
            this.n = 0.0f;
            this.l = 0.0f;
            this.f = 255;
            this.i = null;
            this.e = null;
            ArrayMap<String, Object> arrayMap = new ArrayMap<>();
            this.g = arrayMap;
            this.h = new a(bVar.h, arrayMap);
            this.r = new Path(bVar.r);
            this.p = new Path(bVar.p);
            this.b = bVar.b;
            this.c = bVar.c;
            this.n = bVar.n;
            this.l = bVar.l;
            this.m = bVar.m;
            this.f = bVar.f;
            this.i = bVar.i;
            String str = bVar.i;
            if (str != null) {
                arrayMap.put(str, this);
            }
            this.e = bVar.e;
            this.f1859a = new Matrix(bVar.f1859a);
        }

        private void gA_(a aVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            aVar.c.set(matrix);
            aVar.c.preConcat(aVar.e);
            canvas.save();
            for (int i3 = 0; i3 < aVar.d.size(); i3++) {
                VObject vObject = aVar.d.get(i3);
                if (vObject instanceof a) {
                    gA_((a) vObject, aVar.c, canvas, i, i2, colorFilter);
                } else if (vObject instanceof VPath) {
                    gB_(aVar, (VPath) vObject, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        public void gE_(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            gA_(this.h, o, canvas, i, i2, colorFilter);
        }

        private void gB_(a aVar, VPath vPath, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f;
            float f2 = i / this.n;
            float f3 = i2 / this.l;
            Matrix matrix = aVar.c;
            this.k.set(matrix);
            gD_(this.k, f2, f3);
            float gC_ = gC_(matrix);
            if (gC_ == 0.0f) {
                return;
            }
            vPath.toPath(this.r);
            Path path = this.r;
            this.p.reset();
            if (vPath.isClipPath()) {
                this.p.setFillType(vPath.mFillRule == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.p.addPath(path, this.k);
                canvas.clipPath(this.p);
                return;
            }
            d dVar = (d) vPath;
            if (dVar.o != 0.0f || dVar.i != 1.0f) {
                float f4 = dVar.o;
                float f5 = dVar.j;
                float f6 = dVar.i;
                float f7 = dVar.j;
                if (this.t == null) {
                    this.t = new PathMeasure();
                }
                this.t.setPath(this.r, false);
                float length = this.t.getLength();
                float f8 = ((f4 + f5) % 1.0f) * length;
                float f9 = ((f6 + f7) % 1.0f) * length;
                path.reset();
                if (f8 > f9) {
                    this.t.getSegment(f8, length, path, true);
                    f = 0.0f;
                    this.t.getSegment(0.0f, f9, path, true);
                } else {
                    f = 0.0f;
                    this.t.getSegment(f8, f9, path, true);
                }
                path.rLineTo(f, f);
            }
            this.p.addPath(path, this.k);
            if (dVar.e.d()) {
                aek aekVar = dVar.e;
                if (this.d == null) {
                    Paint paint = new Paint(1);
                    this.d = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.d;
                if (aekVar.a()) {
                    Shader gm_ = aekVar.gm_();
                    gm_.setLocalMatrix(this.k);
                    paint2.setShader(gm_);
                    paint2.setAlpha(Math.round(dVar.b * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(VectorDrawableCompat.b(aekVar.c(), dVar.b));
                }
                paint2.setColorFilter(colorFilter);
                this.p.setFillType(dVar.mFillRule == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.p, paint2);
            }
            if (dVar.f1860a.d()) {
                aek aekVar2 = dVar.f1860a;
                if (this.j == null) {
                    Paint paint3 = new Paint(1);
                    this.j = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.j;
                if (dVar.f != null) {
                    paint4.setStrokeJoin(dVar.f);
                }
                if (dVar.c != null) {
                    paint4.setStrokeCap(dVar.c);
                }
                paint4.setStrokeMiter(dVar.h);
                if (aekVar2.a()) {
                    Shader gm_2 = aekVar2.gm_();
                    gm_2.setLocalMatrix(this.k);
                    paint4.setShader(gm_2);
                    paint4.setAlpha(Math.round(dVar.d * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(VectorDrawableCompat.b(aekVar2.c(), dVar.d));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(dVar.g * Math.min(f2, f3) * gC_);
                canvas.drawPath(this.p, paint4);
            }
        }

        private void gD_(Matrix matrix, float f, float f2) {
            matrix.postScale(f, f2);
            Matrix matrix2 = this.f1859a;
            if (matrix2 != null) {
                matrix.postConcat(matrix2);
            }
        }

        private float gC_(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float hypot = (float) Math.hypot(fArr[0], fArr[1]);
            float hypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float a2 = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max(hypot, hypot2);
            if (max > 0.0f) {
                return Math.abs(a2) / max;
            }
            return 0.0f;
        }

        public boolean d() {
            if (this.e == null) {
                this.e = Boolean.valueOf(this.h.isStateful());
            }
            return this.e.booleanValue();
        }

        public boolean b(int[] iArr) {
            return this.h.onStateChanged(iArr);
        }
    }

    public static class a extends VObject {

        /* renamed from: a, reason: collision with root package name */
        float f1858a;
        int b;
        final Matrix c;
        final ArrayList<VObject> d;
        final Matrix e;
        private String f;
        private float g;
        private float h;
        private float i;
        private float j;
        private int[] k;
        private float l;
        private float o;

        public a(a aVar, ArrayMap<String, Object> arrayMap) {
            VPath cVar;
            this.c = new Matrix();
            this.d = new ArrayList<>();
            this.f1858a = 0.0f;
            this.g = 0.0f;
            this.i = 0.0f;
            this.j = 1.0f;
            this.h = 1.0f;
            this.o = 0.0f;
            this.l = 0.0f;
            Matrix matrix = new Matrix();
            this.e = matrix;
            this.f = null;
            this.f1858a = aVar.f1858a;
            this.g = aVar.g;
            this.i = aVar.i;
            this.j = aVar.j;
            this.h = aVar.h;
            this.o = aVar.o;
            this.l = aVar.l;
            this.k = aVar.k;
            String str = aVar.f;
            this.f = str;
            this.b = aVar.b;
            if (str != null) {
                arrayMap.put(str, this);
            }
            matrix.set(aVar.e);
            ArrayList<VObject> arrayList = aVar.d;
            for (int i = 0; i < arrayList.size(); i++) {
                VObject vObject = arrayList.get(i);
                if (vObject instanceof a) {
                    this.d.add(new a((a) vObject, arrayMap));
                } else {
                    if (vObject instanceof d) {
                        cVar = new d((d) vObject);
                    } else if (vObject instanceof c) {
                        cVar = new c((c) vObject);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.d.add(cVar);
                    if (cVar.mPathName != null) {
                        arrayMap.put(cVar.mPathName, cVar);
                    }
                }
            }
        }

        public a() {
            this.c = new Matrix();
            this.d = new ArrayList<>();
            this.f1858a = 0.0f;
            this.g = 0.0f;
            this.i = 0.0f;
            this.j = 1.0f;
            this.h = 1.0f;
            this.o = 0.0f;
            this.l = 0.0f;
            this.e = new Matrix();
            this.f = null;
        }

        public String d() {
            return this.f;
        }

        public void gz_(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            b(xmlPullParser);
        }

        private void b(XmlPullParser xmlPullParser) {
            this.k = null;
            this.f1858a = aes.d(xmlPullParser, "android:rotation", this.f1858a);
            this.g = aes.d(xmlPullParser, "android:pivotX", this.g);
            this.i = aes.d(xmlPullParser, "android:pivotY", this.i);
            this.j = aes.d(xmlPullParser, "android:scaleX", this.j);
            this.h = aes.d(xmlPullParser, "android:scaleY", this.h);
            this.o = aes.d(xmlPullParser, "android:translateX", this.o);
            this.l = aes.d(xmlPullParser, "android:translateY", this.l);
            String b = aes.b(xmlPullParser, "android:name");
            if (b != null) {
                this.f = b;
            }
            c();
        }

        private void c() {
            this.e.reset();
            this.e.postTranslate(-this.g, -this.i);
            this.e.postScale(this.j, this.h);
            this.e.postRotate(this.f1858a, 0.0f, 0.0f);
            this.e.postTranslate(this.o + this.g, this.l + this.i);
        }

        public ArrayList<VObject> e() {
            return this.d;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VObject
        public boolean isStateful() {
            for (int i = 0; i < this.d.size(); i++) {
                if (this.d.get(i).isStateful()) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VObject
        public boolean onStateChanged(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.d.size(); i++) {
                z |= this.d.get(i).onStateChanged(iArr);
            }
            return z;
        }
    }

    static abstract class VPath extends VObject {
        protected static final int FILL_TYPE_WINDING = 0;
        int mChangingConfigurations;
        int mFillRule;
        protected PathParser.PathDataNode[] mNodes;
        String mPathName;

        public void applyTheme(Resources.Theme theme) {
        }

        public boolean canApplyTheme() {
            return false;
        }

        public boolean isClipPath() {
            return false;
        }

        public VPath() {
            this.mNodes = null;
            this.mFillRule = 0;
        }

        public void printVPath(int i) {
            String str = "";
            for (int i2 = 0; i2 < i; i2++) {
                str = str + "    ";
            }
            Log.v("VectorDrawableCompat", str + "current path is :" + this.mPathName + " pathData is " + nodesToString(this.mNodes));
        }

        public String nodesToString(PathParser.PathDataNode[] pathDataNodeArr) {
            String str = " ";
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                str = str + pathDataNodeArr[i].mType + ":";
                for (float f : pathDataNodeArr[i].mParams) {
                    str = str + f + ",";
                }
            }
            return str;
        }

        public VPath(VPath vPath) {
            this.mNodes = null;
            this.mFillRule = 0;
            this.mPathName = vPath.mPathName;
            this.mChangingConfigurations = vPath.mChangingConfigurations;
            this.mNodes = PathParser.deepCopyNodes(vPath.mNodes);
        }

        public void toPath(Path path) {
            path.reset();
            PathParser.PathDataNode[] pathDataNodeArr = this.mNodes;
            if (pathDataNodeArr != null) {
                PathParser.PathDataNode.nodesToPath(pathDataNodeArr, path);
            }
        }

        public String getPathName() {
            return this.mPathName;
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
            if (!PathParser.canMorph(this.mNodes, pathDataNodeArr)) {
                this.mNodes = PathParser.deepCopyNodes(pathDataNodeArr);
            } else {
                PathParser.updateNodes(this.mNodes, pathDataNodeArr);
            }
        }
    }

    static class c extends VPath {
        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public boolean isClipPath() {
            return true;
        }

        c() {
        }

        c(c cVar) {
            super(cVar);
        }

        public void gu_(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (aes.a(xmlPullParser, "android:pathData")) {
                c(xmlPullParser);
            }
        }

        private void c(XmlPullParser xmlPullParser) {
            String b = aes.b(xmlPullParser, "android:name");
            if (b != null) {
                this.mPathName = b;
            }
            String b2 = aes.b(xmlPullParser, "android:pathData");
            if (b2 != null) {
                this.mNodes = PathParser.createNodesFromPathData(b2);
            }
            this.mFillRule = b(aes.b(xmlPullParser, "android:fillType"), this.mFillRule);
        }

        private int b(String str, int i) {
            if (str == null) {
                return i;
            }
            str.hashCode();
            if (str.equals("evenOdd")) {
                return 1;
            }
            if (str.equals("nonZero")) {
                return 0;
            }
            return i;
        }
    }

    public static class d extends VPath {

        /* renamed from: a, reason: collision with root package name */
        aek f1860a;
        float b;
        Paint.Cap c;
        float d;
        aek e;
        Paint.Join f;
        float g;
        float h;
        float i;
        float j;
        private int[] k;
        float o;

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public void applyTheme(Resources.Theme theme) {
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public /* bridge */ /* synthetic */ PathParser.PathDataNode[] getPathData() {
            return super.getPathData();
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public /* bridge */ /* synthetic */ String getPathName() {
            return super.getPathName();
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public /* bridge */ /* synthetic */ boolean isClipPath() {
            return super.isClipPath();
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public /* bridge */ /* synthetic */ String nodesToString(PathParser.PathDataNode[] pathDataNodeArr) {
            return super.nodesToString(pathDataNodeArr);
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public /* bridge */ /* synthetic */ void printVPath(int i) {
            super.printVPath(i);
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public /* bridge */ /* synthetic */ void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
            super.setPathData(pathDataNodeArr);
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public /* bridge */ /* synthetic */ void toPath(Path path) {
            super.toPath(path);
        }

        d() {
            this.g = 0.0f;
            this.d = 1.0f;
            this.b = 1.0f;
            this.o = 0.0f;
            this.i = 1.0f;
            this.j = 0.0f;
            this.c = Paint.Cap.BUTT;
            this.f = Paint.Join.MITER;
            this.h = 4.0f;
        }

        d(d dVar) {
            super(dVar);
            this.g = 0.0f;
            this.d = 1.0f;
            this.b = 1.0f;
            this.o = 0.0f;
            this.i = 1.0f;
            this.j = 0.0f;
            this.c = Paint.Cap.BUTT;
            this.f = Paint.Join.MITER;
            this.h = 4.0f;
            this.k = dVar.k;
            this.f1860a = dVar.f1860a;
            this.g = dVar.g;
            this.d = dVar.d;
            this.e = dVar.e;
            this.mFillRule = dVar.mFillRule;
            this.b = dVar.b;
            this.o = dVar.o;
            this.i = dVar.i;
            this.j = dVar.j;
            this.c = dVar.c;
            this.f = dVar.f;
            this.h = dVar.h;
        }

        private Paint.Cap gv_(String str, Paint.Cap cap) {
            char c;
            str.hashCode();
            int hashCode = str.hashCode();
            if (hashCode == -894674659) {
                if (str.equals("square")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 3035667) {
                if (hashCode == 108704142 && str.equals("round")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (str.equals("butt")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c == 0) {
                return Paint.Cap.SQUARE;
            }
            if (c != 1) {
                return c != 2 ? cap : Paint.Cap.ROUND;
            }
            return Paint.Cap.BUTT;
        }

        private Paint.Join gw_(String str, Paint.Join join) {
            char c;
            str.hashCode();
            int hashCode = str.hashCode();
            if (hashCode == 93630586) {
                if (str.equals("bevel")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 103906565) {
                if (hashCode == 108704142 && str.equals("round")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (str.equals("miter")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c == 0) {
                return Paint.Join.BEVEL;
            }
            if (c != 1) {
                return c != 2 ? join : Paint.Join.ROUND;
            }
            return Paint.Join.MITER;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VPath
        public boolean canApplyTheme() {
            return this.k != null;
        }

        public void gy_(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            d(xmlPullParser);
        }

        private void d(XmlPullParser xmlPullParser) {
            this.k = null;
            if (aes.a(xmlPullParser, "android:pathData")) {
                String b = aes.b(xmlPullParser, "android:name");
                if (b != null) {
                    this.mPathName = b;
                }
                String b2 = aes.b(xmlPullParser, "android:pathData");
                if (b2 != null) {
                    this.mNodes = PathParser.createNodesFromPathData(b2);
                }
                this.e = aes.c(xmlPullParser, "android:fillColor", 0);
                this.b = aes.d(xmlPullParser, "android:fillAlpha", this.b);
                String b3 = aes.b(xmlPullParser, "android:strokeLineCap");
                if (b3 != null) {
                    this.c = gv_(b3, Paint.Cap.BUTT);
                }
                String b4 = aes.b(xmlPullParser, "android:strokeLineJoin");
                if (b4 != null) {
                    this.f = gw_(b4, this.f);
                }
                this.h = aes.d(xmlPullParser, "android:strokeMiterLimit", this.h);
                this.f1860a = aes.c(xmlPullParser, "android:strokeColor", 0);
                this.d = aes.d(xmlPullParser, "android:strokeAlpha", this.d);
                this.g = aes.d(xmlPullParser, "android:strokeWidth", this.g);
                this.i = aes.d(xmlPullParser, "android:trimPathEnd", this.i);
                this.j = aes.d(xmlPullParser, "android:trimPathOffset", this.j);
                this.o = aes.d(xmlPullParser, "android:trimPathStart", this.o);
                this.mFillRule = a(aes.b(xmlPullParser, "android:fillType"), this.mFillRule);
            }
        }

        private int a(String str, int i) {
            if (str == null) {
                return i;
            }
            str.hashCode();
            if (str.equals("evenOdd")) {
                return 1;
            }
            if (str.equals("nonZero")) {
                return 0;
            }
            return i;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VObject
        public boolean isStateful() {
            return this.e.b() || this.f1860a.b();
        }

        @Override // com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat.VObject
        public boolean onStateChanged(int[] iArr) {
            return this.f1860a.a(iArr) | this.e.a(iArr);
        }

        public int c() {
            return this.f1860a.c();
        }

        public void e(int i) {
            this.f1860a.c(i);
        }

        public int d() {
            return this.e.c();
        }

        public void a(int i) {
            this.e.c(i);
        }

        public boolean b() {
            return this.e.a();
        }

        public Shader gx_() {
            return this.e.gm_();
        }

        public void a(int[] iArr) {
            Shader gm_ = this.e.gm_();
            if (gm_ instanceof aem) {
                d((aem) gm_, iArr);
            }
        }

        private void d(aem aemVar, int[] iArr) {
            this.e.gn_(new aem(aemVar.b(), aemVar.g(), aemVar.c(), aemVar.f(), iArr, aemVar.e(), aemVar.go_()));
        }
    }
}
