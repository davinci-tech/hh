package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class jdu {
    private static jdu c;

    /* renamed from: a, reason: collision with root package name */
    private ScriptIntrinsicBlur f13756a;
    private Context b;
    private RenderScript d;

    private jdu(Context context) {
        if (context == null) {
            this.b = BaseApplication.getContext();
        } else {
            this.b = context.getApplicationContext();
        }
        RenderScript create = RenderScript.create(this.b);
        this.d = create;
        if (create == null) {
            return;
        }
        this.f13756a = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
    }

    public static jdu e(Context context) {
        jdu jduVar = c;
        return jduVar == null ? new jdu(context) : jduVar;
    }

    public void c() {
        ScriptIntrinsicBlur scriptIntrinsicBlur = this.f13756a;
        if (scriptIntrinsicBlur != null) {
            scriptIntrinsicBlur.destroy();
        }
        RenderScript renderScript = this.d;
        if (renderScript != null) {
            renderScript.destroy();
        }
    }

    public void bGd_(Bitmap bitmap, Bitmap bitmap2, int i) {
        this.f13756a.setRadius(i);
        Allocation createFromBitmap = Allocation.createFromBitmap(this.d, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
        Allocation createTyped = Allocation.createTyped(this.d, createFromBitmap.getType());
        this.f13756a.setInput(createFromBitmap);
        this.f13756a.forEach(createTyped);
        createTyped.copyTo(bitmap2);
        createFromBitmap.destroy();
        createTyped.destroy();
    }

    public Bitmap bGb_(Bitmap bitmap, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        bGd_(bitmap, createBitmap, i);
        return createBitmap;
    }

    public Bitmap bGc_(Bitmap bitmap, int i, int i2) {
        return bGb_(bFZ_(bitmap, i2), i);
    }

    private static Bitmap bFZ_(Bitmap bitmap, int i) {
        int i2;
        int i3 = 1;
        if (bitmap == null || i == 0) {
            i2 = 1;
        } else {
            int max = Math.max(1, bitmap.getWidth() / i);
            i2 = Math.max(1, bitmap.getHeight() / i);
            i3 = max;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i3, i2, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, i3, i2);
        Paint paint = new Paint();
        paint.setShader(bGa_(bitmap, createBitmap));
        canvas.drawRect(rectF, paint);
        return createBitmap;
    }

    private static BitmapShader bGa_(Bitmap bitmap, Bitmap bitmap2) {
        float f;
        float f2;
        if (bitmap == null) {
            LogUtil.b("RenderScriptBlur", "getBitmapScaleShader : in is null");
            return null;
        }
        if (bitmap2 == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            f = 1.0f;
            f2 = 1.0f;
        } else {
            f = bitmap2.getWidth() / bitmap.getWidth();
            f2 = bitmap2.getHeight() / bitmap.getHeight();
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }
}
