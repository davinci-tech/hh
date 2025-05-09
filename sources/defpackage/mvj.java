package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class mvj {

    /* renamed from: a, reason: collision with root package name */
    private Context f15201a;
    private fdu c;

    mvj(Context context, fdu fduVar) {
        this.f15201a = context;
        this.c = fduVar;
    }

    private boolean b() {
        return CommonUtil.e(this.f15201a, "com.sina.weibo");
    }

    void a() {
        if (this.c == null || this.f15201a == null) {
            LogUtil.b("Share_SinaShareInteractors", "shareBySina fail:mShareContent/mShareHandler/mContext is null");
            return;
        }
        if (!b()) {
            Toast.makeText(this.f15201a, R.string._2130839537_res_0x7f0207f1, 0).show();
            LogUtil.b("Share_SinaShareInteractors", "shareBySina fail: sina not installed");
            return;
        }
        int u = this.c.u();
        if (u != 0) {
            if (u != 1) {
                if (u != 2) {
                    if (u != 4) {
                        if (u == 5) {
                            LogUtil.a("Share_SinaShareInteractors", "SHARE_WAY_MULTI_IMG");
                            c();
                            return;
                        } else if (u == 8 || u == 9) {
                            d();
                            return;
                        } else {
                            LogUtil.b("Share_SinaShareInteractors", "UNKNOWN SinaShareWay!");
                            return;
                        }
                    }
                }
            }
            LogUtil.a("Share_SinaShareInteractors", "SHARE_WAY_PIC");
            e();
            return;
        }
        LogUtil.h("Share_SinaShareInteractors", "SHARE_WAY_TEXT unsupported!");
    }

    private void e() {
        String i;
        if (this.c.u() == 1) {
            i = mwd.cqA_(this.f15201a, this.c.awm_());
        } else {
            i = this.c.i();
        }
        if (TextUtils.isEmpty(i)) {
            return;
        }
        Uri cqv_ = mwd.cqv_(this.f15201a, new File(i));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(Constants.IMAGE_TYPE);
        intent.putExtra("android.intent.extra.STREAM", cqv_);
        cqk_(intent);
    }

    private void d() {
        Uri uri;
        LogUtil.a("Share_SinaShareInteractors", "shareSingleVideo enter");
        if (this.c.u() == 8) {
            String x = this.c.x();
            if (TextUtils.isEmpty(x)) {
                return;
            } else {
                uri = Uri.fromFile(new File(x));
            }
        } else if (this.c.u() == 9) {
            uri = this.c.awo_();
        } else {
            LogUtil.h("Share_SinaShareInteractors", "shareSingleVideo is other share type");
            uri = null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "shareSingleVideo uri:";
        objArr[1] = uri != null ? uri.getPath() : "";
        LogUtil.a("Share_SinaShareInteractors", objArr);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("video/*");
        intent.putExtra("android.intent.extra.STREAM", uri);
        cqk_(intent);
    }

    private void c() {
        ArrayList<Uri> a2 = mwd.a(this.f15201a, this.c, 3);
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.setType(Constants.IMAGE_TYPE);
        intent.putExtra("android.intent.extra.STREAM", a2);
        cqk_(intent);
    }

    private void cqk_(Intent intent) {
        intent.setComponent(new ComponentName("com.sina.weibo", "com.sina.weibo.composerinde.ComposerDispatchActivity"));
        intent.addFlags(268435456);
        intent.setFlags(1);
        intent.setFlags(2);
        try {
            this.f15201a.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Share_SinaShareInteractors", "systemShareWeibo ActivityNotFoundException: ", e.getMessage());
        }
    }
}
