package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import java.io.Closeable;

/* loaded from: classes6.dex */
public class pgw {
    public static int c(short s, short s2) {
        int c = (int) ddz.c(s, s2);
        return c <= 20 ? (c * 100) / 30 : (int) ((((c - 20) * 33.33f) / 40.0f) + 66.67f);
    }

    public static Bitmap dps_(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        if (view.getHeight() == 0) {
            LogUtil.h("ExhibitionUtil", "contentView scrollView.height == 0");
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.widget.RelativeLayout] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v12, types: [android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    public static void dpt_(RelativeLayout relativeLayout, int i) {
        String str;
        IllegalArgumentException e;
        ?? r7;
        Closeable closeable;
        Closeable closeable2 = null;
        Bitmap bitmap = null;
        switch (i) {
            case 100:
                str = "pic_report_cover.webp";
                break;
            case 101:
                str = "pic_report_blood_bg.webp";
                break;
            case 102:
                str = "pic_report_body_bg.webp";
                break;
            case 103:
                str = "pic_report_ecg_bg.webp";
                break;
            case 104:
                str = "pic_report_run_bg.webp";
                break;
            default:
                LogUtil.a("ExhibitionUtil", "wrong position");
                str = null;
                break;
        }
        try {
            r7 = moh.e("exhibitioninfo", str);
            try {
                try {
                    bitmap = BitmapFactory.decodeStream(r7);
                    closeable = r7;
                } catch (Throwable th) {
                    th = th;
                    closeable2 = r7;
                    IoUtils.e(closeable2);
                    throw th;
                }
            } catch (IllegalArgumentException e2) {
                e = e2;
                LogUtil.b("ExhibitionUtil", "setBackGround catch Exception is ", e.getMessage());
                closeable = r7;
                IoUtils.e(closeable);
                r7 = new BitmapDrawable(bitmap);
                relativeLayout.setBackground(r7);
            }
        } catch (IllegalArgumentException e3) {
            e = e3;
            r7 = 0;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(closeable2);
            throw th;
        }
        IoUtils.e(closeable);
        r7 = new BitmapDrawable(bitmap);
        relativeLayout.setBackground(r7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11, types: [android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v8 */
    public static Drawable dpr_(int i) {
        String str;
        IllegalArgumentException e;
        ?? r6;
        Closeable closeable;
        Closeable closeable2 = null;
        Bitmap bitmap = null;
        if (i == 105) {
            str = "report_blood_sugar_not_limosis_bar.webp";
        } else if (i != 106) {
            LogUtil.a("ExhibitionUtil", "wrong position");
            str = null;
        } else {
            str = "report_health_seekbar_bg.png";
        }
        try {
            r6 = moh.e("exhibitioninfo", str);
            try {
                try {
                    bitmap = BitmapFactory.decodeStream(r6);
                    closeable = r6;
                } catch (Throwable th) {
                    closeable2 = r6;
                    th = th;
                    IoUtils.e(closeable2);
                    throw th;
                }
            } catch (IllegalArgumentException e2) {
                e = e2;
                LogUtil.b("ExhibitionUtil", "getSeekBarRule catch Exception is ", e.getMessage());
                closeable = r6;
                IoUtils.e(closeable);
                r6 = new BitmapDrawable(bitmap);
                return r6;
            }
        } catch (IllegalArgumentException e3) {
            e = e3;
            r6 = 0;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(closeable2);
            throw th;
        }
        IoUtils.e(closeable);
        r6 = new BitmapDrawable(bitmap);
        return r6;
    }
}
