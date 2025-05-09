package com.huawei.ui.main.stories.exhibitioninfo.activity;

import android.content.Context;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import defpackage.pgw;
import defpackage.sqd;
import health.compact.a.IoUtils;
import java.io.Closeable;
import java.io.File;
import java.math.BigDecimal;

/* loaded from: classes6.dex */
public class ExhibitionBodyFragment extends BaseFragment {
    private Context b;
    private ImageView c;
    private View e;

    public void a(Context context) {
        if (context == null) {
            this.b = BaseApplication.e();
            LogUtil.h("ExhibitionBodyFragment", "initViewInActivity Activity is null");
        } else {
            this.b = context;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.report_body_layout, (ViewGroup) null);
        this.e = inflate;
        doS_(inflate);
    }

    public void e() {
        a();
    }

    private void doS_(View view) {
        pgw.dpt_((RelativeLayout) view.findViewById(R.id.report_body), 102);
        this.c = (ImageView) view.findViewById(R.id.report_body_pic);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0051 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a() {
        /*
            r7 = this;
            android.content.Context r0 = r7.b
            java.lang.String r1 = "100001"
            java.lang.String r2 = "SP_REPORT_BODY"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r1, r2)
            android.net.Uri r0 = android.net.Uri.parse(r0)
            boolean r1 = com.huawei.hwcommonmodel.utils.PermissionUtil.c()
            if (r1 == 0) goto L20
            if (r0 != 0) goto L17
            return
        L17:
            java.lang.String r0 = r7.doT_(r0)
            android.graphics.Bitmap r0 = defpackage.nrf.cHA_(r0)
            goto L4f
        L20:
            r1 = 0
            android.content.Context r2 = r7.b     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            java.io.InputStream r0 = r2.openInputStream(r0)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L5c
            goto L4b
        L30:
            r2 = move-exception
            goto L37
        L32:
            r0 = move-exception
            goto L60
        L34:
            r0 = move-exception
            r2 = r0
            r0 = r1
        L37:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "initData Failed to obtain the bitmap exception :"
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L5c
            java.lang.String r2 = com.huawei.haf.common.exception.ExceptionUtils.d(r2)     // Catch: java.lang.Throwable -> L5c
            r4 = 1
            r3[r4] = r2     // Catch: java.lang.Throwable -> L5c
            java.lang.String r2 = "ExhibitionBodyFragment"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r3)     // Catch: java.lang.Throwable -> L5c
        L4b:
            health.compact.a.IoUtils.e(r0)
            r0 = r1
        L4f:
            if (r0 != 0) goto L52
            return
        L52:
            android.widget.ImageView r1 = r7.c
            android.graphics.Bitmap r0 = r7.doR_(r0)
            r1.setImageBitmap(r0)
            return
        L5c:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L60:
            health.compact.a.IoUtils.e(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.exhibitioninfo.activity.ExhibitionBodyFragment.a():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r13v7, types: [android.database.Cursor] */
    private String doT_(Uri uri) {
        Throwable th;
        SQLException e;
        String str = "ExhibitionBodyFragment";
        String[] strArr = {"_data"};
        Closeable closeable = null;
        r11 = null;
        r11 = null;
        String str2 = null;
        try {
            try {
            } catch (Throwable th2) {
                th = th2;
                closeable = uri;
                IoUtils.e(closeable);
                throw th;
            }
        } catch (SQLException e2) {
            e = e2;
            uri = 0;
        } catch (Throwable th3) {
            th = th3;
            IoUtils.e(closeable);
            throw th;
        }
        if (!sqd.b(new File(uri.toString()))) {
            LogUtil.h("ExhibitionBodyFragment", "path_crossing queryImages file is invalid,contentUri ", uri);
            IoUtils.e((Closeable) null);
            return null;
        }
        uri = this.b.getContentResolver().query(uri, strArr, null, null, null);
        try {
            if (uri != 0) {
                uri.moveToFirst();
                str = uri.getString(uri.getColumnIndex(strArr[0]));
                str2 = str;
            } else {
                LogUtil.h("ExhibitionBodyFragment", "queryImages cursor = null");
            }
        } catch (SQLException e3) {
            e = e3;
            LogUtil.h(str, "queryImagesSQLException! exception : ", ExceptionUtils.d(e));
            IoUtils.e((Closeable) uri);
            return str2;
        }
        IoUtils.e((Closeable) uri);
        return str2;
    }

    private Bitmap doR_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double d = width / 400.0d;
        int intValue = Long.valueOf(Math.round(new BigDecimal(Double.toString(13.2d)).multiply(new BigDecimal(d)).doubleValue())).intValue();
        int intValue2 = Long.valueOf(Math.round(new BigDecimal(Double.toString(30.5d)).multiply(new BigDecimal(d)).doubleValue())).intValue();
        int intValue3 = Long.valueOf(Math.round(new BigDecimal(Double.toString(40.0d)).multiply(new BigDecimal(d)).doubleValue())).intValue();
        return Bitmap.createBitmap(bitmap, intValue, intValue3, width - intValue, (height - intValue3) - intValue2);
    }

    public Bitmap doU_() {
        return pgw.dps_(this.e);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("ExhibitionBodyFragment", "onDestroy");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }
}
