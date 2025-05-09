package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import health.compact.a.EnvironmentInfo;

/* loaded from: classes6.dex */
public class nmn {
    private static volatile Drawable b;

    public static boolean b(char c2) {
        return ('A' <= c2 && c2 <= 'Z') || ('a' <= c2 && c2 <= 'z');
    }

    private static boolean c(int i) {
        return i == 2 || i == 3;
    }

    public static Drawable cBh_(Resources resources, c cVar) {
        if (resources == null) {
            return null;
        }
        return cBi_(resources, cVar, null);
    }

    public static Drawable cBi_(Resources resources, c cVar, byte[] bArr) {
        cBj_(resources, cVar, bArr);
        if (EnvironmentInfo.d()) {
            cBk_(resources, cVar);
        }
        nmp nmpVar = new nmp(resources, cVar.i(), cVar.b, bArr);
        nmpVar.setContactDetails(cVar.c, cVar.e);
        nmpVar.setScale(cVar.e());
        nmpVar.setOffset(cVar.a());
        nmpVar.setIsCircular(cVar.b());
        nmpVar.setIsPure(cVar.c());
        return nmpVar;
    }

    public static Bitmap cBg_(Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.getHeight() <= 0 || bitmap.getWidth() <= 0) {
            return null;
        }
        int height = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        boolean z2 = z && height <= 96;
        Matrix matrix = new Matrix();
        if (z2) {
            matrix.setScale(5.0f, 5.0f);
        }
        bitmapShader.setLocalMatrix(matrix);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        int i = z2 ? height * 5 : height;
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            float f = i;
            float f2 = f / 2.0f;
            canvas.drawCircle(f2, f2, f2, paint);
            if (z2 || i != 0) {
                float f3 = height / f;
                canvas.scale(f3, f3, f2, f2);
            }
            bitmap.recycle();
            return createBitmap;
        } catch (IllegalArgumentException | OutOfMemoryError e) {
            LogUtil.b("PersonalAvatarManager", "createRoundPhotoWithScale()", ExceptionUtils.d(e));
            return null;
        }
    }

    public static boolean a(Object... objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static Drawable cBj_(Resources resources, c cVar, byte[] bArr) {
        Drawable nmpVar;
        if (resources == null) {
            return null;
        }
        if (cVar == null) {
            if (b == null) {
                if (EnvironmentInfo.d()) {
                    nmpVar = c(1) ? resources.getDrawable(R$drawable.commonui_honor_avatar_detail) : resources.getDrawable(R$drawable.commonui_honor_avatar);
                } else {
                    nmpVar = new nmp(resources, false, 1, bArr);
                }
                b = nmpVar;
            }
            return b;
        }
        return b;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private boolean f15393a;
        private int b;
        private String c;
        private boolean d;
        private String e;
        private boolean g;
        private float h;
        private float i;

        public c() {
            this.b = 1;
            this.i = 1.0f;
            this.h = 0.0f;
            this.g = false;
            this.d = false;
            this.f15393a = false;
        }

        public c(String str, String str2, boolean z) {
            this(str, str2, 1, 1.0f, 0.0f, z, false, false);
        }

        public c(String str, String str2, int i, float f, float f2, boolean z, boolean z2, boolean z3) {
            this.c = str;
            this.e = str2;
            this.b = i;
            this.i = f;
            this.h = f2;
            this.d = z;
            this.g = z2;
            this.f15393a = z3;
        }

        public int d() {
            return this.b;
        }

        public float e() {
            return this.i;
        }

        public float a() {
            return this.h;
        }

        public boolean i() {
            return this.g;
        }

        public boolean b() {
            return this.d;
        }

        public boolean c() {
            return this.f15393a;
        }
    }

    private static Drawable cBk_(Resources resources, c cVar) {
        if (c(cVar.d())) {
            return resources.getDrawable(R$drawable.commonui_honor_avatar_detail);
        }
        return resources.getDrawable(R$drawable.commonui_honor_avatar);
    }
}
