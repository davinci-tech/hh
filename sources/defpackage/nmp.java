package defpackage;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.lettertiles.TileDrawable;

/* loaded from: classes6.dex */
public class nmp extends TileDrawable {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f15394a;
    private static volatile int b;
    private static volatile int c;
    private static volatile int d;
    private static volatile int e;
    private static volatile int f;
    private static volatile TypedArray g;
    private static volatile int h;
    private static volatile int i;
    private static volatile Drawable j;
    private static volatile TypedArray k;
    private static volatile TypedArray l;
    private static volatile TypedArray m;
    private int o;
    private boolean r;
    private float s;
    private String t;
    private Bitmap u;
    private final Paint v;
    private byte[] x;
    private final Rect y = new Rect();
    private String n = null;
    private float w = 1.0f;
    private float p = 0.0f;
    private boolean q = true;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // com.huawei.ui.commonui.lettertiles.TileDrawable
    public void setIsPure(boolean z) {
    }

    static {
        Object obj = new Object();
        f15394a = obj;
        if (BaseApplication.getContext() != null) {
            synchronized (obj) {
                cBf_(BaseApplication.getContext().getResources());
            }
        }
    }

    public nmp(Resources resources, boolean z, int i2, byte[] bArr) {
        this.o = 1;
        this.r = false;
        Paint paint = new Paint();
        this.v = paint;
        paint.setFilterBitmap(true);
        paint.setDither(true);
        this.r = z;
        this.o = i2;
        this.x = bArr != null ? (byte[]) bArr.clone() : null;
        if (z) {
            this.s = resources.getFraction(R.dimen._2131362064_res_0x7f0a0110, 1, 1);
        } else if (e()) {
            this.s = resources.getFraction(R.dimen._2131362065_res_0x7f0a0111, 1, 1);
        } else {
            this.s = resources.getFraction(R.dimen._2131362066_res_0x7f0a0112, 1, 1);
        }
        synchronized (f15394a) {
            cBf_(resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        synchronized (f15394a) {
            if (nmn.a(m, g, k, l)) {
                return;
            }
            Rect bounds = getBounds();
            if (isVisible() && !bounds.isEmpty()) {
                byte[] bArr = this.x;
                Bitmap decodeByteArray = bArr != null ? BitmapFactory.decodeByteArray(bArr, 0, bArr.length) : null;
                if (this.q) {
                    this.u = nmn.cBg_(decodeByteArray, e());
                } else {
                    this.u = decodeByteArray;
                }
                cBe_(canvas);
            }
        }
    }

    private static void cBf_(Resources resources) {
        m = resources.obtainTypedArray(R.array._2130968667_res_0x7f04005b);
        g = resources.obtainTypedArray(R.array._2130968666_res_0x7f04005a);
        k = resources.obtainTypedArray(R.array._2130968661_res_0x7f040055);
        l = resources.obtainTypedArray(R.array._2130968662_res_0x7f040056);
        b = resources.getColor(R$color.commonui_default_littleavatar_list_color);
        f = resources.getColor(R$color.commonui_default_pure_detail_color);
        h = resources.getColor(R$color.commonui_default_letterfont_list_color);
        i = resources.getColor(R$color.commonui_default_letterfont_detail_color);
        c = resources.getColor(R$color.commonui_default_avatar_list_color);
        d = resources.getColor(R$color.commonui_default_avatar_detail_color);
        j = resources.getDrawable(R$drawable.ic_commonui_contact_default_avater);
        e = k.length();
    }

    private void cBb_(Canvas canvas) {
        Drawable cJH_ = nrf.cJH_(j, e() ? d : c);
        Bitmap cHF_ = nrf.cHF_(cJH_);
        Rect copyBounds = copyBounds();
        int height = (int) ((this.w * (copyBounds.width() > copyBounds.height() ? copyBounds.height() : copyBounds.width())) / 2.0f);
        copyBounds.set(copyBounds.centerX() - height, (int) ((copyBounds.centerY() - height) + (this.p * copyBounds.height())), copyBounds.centerX() + height, (int) (copyBounds.centerY() + height + (this.p * copyBounds.height())));
        this.y.set(0, 0, cJH_.getIntrinsicWidth(), cJH_.getIntrinsicHeight());
        if (cHF_ != null) {
            canvas.drawBitmap(cHF_, this.y, copyBounds, this.v);
        }
        this.v.reset();
    }

    private void cBe_(Canvas canvas) {
        int color;
        int color2;
        this.v.setTextAlign(Paint.Align.CENTER);
        this.v.setAntiAlias(true);
        Rect bounds = getBounds();
        int a2 = a(this.n);
        if (e()) {
            color = l.getColor(a2, f);
            color2 = color;
        } else {
            color = m.getColor(a2, b);
            color2 = g.getColor(a2, b);
        }
        this.v.setShader(new LinearGradient(0.0f, 0.0f, bounds.width(), bounds.height(), color, color2, Shader.TileMode.CLAMP));
        int height = bounds.width() > bounds.height() ? bounds.height() : bounds.width();
        if (this.q) {
            canvas.drawCircle(bounds.centerX(), bounds.centerY(), height / 2.0f, this.v);
        } else {
            canvas.drawRect(bounds, this.v);
        }
        this.v.setShader(null);
        if (this.q || this.r) {
            if (this.o == 3 && this.u != null) {
                cBc_(canvas);
                this.u.recycle();
            } else if (this.t != null) {
                cBd_(canvas, bounds, a2);
            } else {
                cBb_(canvas);
            }
        }
    }

    private void cBc_(Canvas canvas) {
        this.v.setStyle(Paint.Style.FILL);
        this.v.setAntiAlias(true);
        Rect copyBounds = copyBounds();
        int height = (int) ((this.w * (copyBounds.width() > copyBounds.height() ? copyBounds.height() : copyBounds.width())) / 2.0f);
        copyBounds.set(copyBounds.centerX() - height, copyBounds.centerY() - height, copyBounds.centerX() + height, copyBounds.centerY() + height);
        this.y.set(0, 0, this.u.getWidth(), this.u.getHeight());
        if (this.u.isRecycled()) {
            return;
        }
        canvas.drawBitmap(this.u, this.y, copyBounds, this.v);
    }

    private void cBd_(Canvas canvas, Rect rect, int i2) {
        this.v.setTextSize(this.w * this.s * (rect.width() > rect.height() ? rect.height() : rect.width()));
        Paint paint = this.v;
        String str = this.t;
        paint.getTextBounds(str, 0, str.length(), this.y);
        this.v.setTypeface(Typeface.create(BaseApplication.getContext().getResources().getString(R$string.textFontFamilyMedium), 0));
        if (e()) {
            this.v.setColor(i);
        } else {
            this.v.setColor(k.getColor(i2, h));
        }
        String str2 = this.t;
        canvas.drawText(str2, 0, str2.length(), rect.centerX(), (rect.centerY() + (this.p * rect.height())) - this.y.exactCenterY(), this.v);
        this.v.reset();
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return Math.abs(str.hashCode() & 4095) % e;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.v.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.v.setColorFilter(colorFilter);
    }

    @Override // com.huawei.ui.commonui.lettertiles.TileDrawable
    public void setScale(float f2) {
        this.w = f2;
    }

    @Override // com.huawei.ui.commonui.lettertiles.TileDrawable
    public void setOffset(float f2) {
        Preconditions.checkArgument(f2 >= -0.5f && f2 <= 0.5f);
        this.p = f2;
    }

    @Override // com.huawei.ui.commonui.lettertiles.TileDrawable
    public void setContactDetails(String str, String str2) {
        this.n = str2;
        e(str);
    }

    @Override // com.huawei.ui.commonui.lettertiles.TileDrawable
    public void setIsCircular(boolean z) {
        this.q = z;
    }

    private void e(String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if (charAt >= 19968 && charAt <= 40869) {
                this.t = String.valueOf(charAt);
                return;
            }
        }
        char charAt2 = str.charAt(0);
        if (nmn.b(charAt2)) {
            this.t = String.valueOf(Character.toUpperCase(charAt2));
        }
    }

    private boolean e() {
        int i2 = this.o;
        return i2 == 2 || i2 == 3;
    }
}
