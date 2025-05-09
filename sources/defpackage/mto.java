package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.activity.SharePopupActivity;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class mto extends mml {

    /* renamed from: a, reason: collision with root package name */
    private static UserInfomation f15170a;
    private static fdu c;
    private static fec d;
    private static fdz e;
    private mur b = null;

    private mto() {
    }

    public static mto b() {
        return new mto();
    }

    private static void b(UserInfomation userInfomation) {
        f15170a = userInfomation;
    }

    public static fdz c() {
        return e;
    }

    private static void d(fdz fdzVar) {
        e = fdzVar;
    }

    public static fdu d() {
        return c;
    }

    public static void d(fdu fduVar) {
        c = fduVar;
    }

    public static fec a() {
        return d;
    }

    public static void e(fec fecVar) {
        d = fecVar;
    }

    @Override // defpackage.mml
    public void finish() {
        super.finish();
        e();
    }

    public void c(fdu fduVar, Context context) {
        if (fduVar == null || context == null) {
            LogUtil.b("Share_PluginSocialShare", "exeShare() shareContent/mContext == null");
            return;
        }
        int u = fduVar.u();
        if (u == 12) {
            LogUtil.h("Share_PluginSocialShare", "exeShare() shareType is SHARE_WAY_EDIT_IMG");
            a(context, fduVar);
            return;
        }
        d(fduVar);
        boolean z = true;
        if (u != 1 && u != 7 && u != 4 && u != 6 && (u != 10 || fduVar.awm_() == null)) {
            z = false;
        }
        if (!Utils.o() || z || u == 8) {
            try {
                Intent intent = new Intent(context, (Class<?>) SharePopupActivity.class);
                intent.setFlags(268435456);
                mwd.a(context.getResources().getConfiguration().uiMode & 48);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("Share_PluginSocialShare", "exeShare:", e2.getMessage());
                return;
            }
        }
        mur b = mur.b(context);
        this.b = b;
        b.a(context, 5, fduVar);
    }

    public void e(Context context, int i, fdu fduVar) {
        if (fduVar == null || context == null) {
            LogUtil.b("Share_PluginSocialShare", "exeShare() shareContent/mContext == null");
            return;
        }
        if (this.b == null) {
            this.b = mur.b(context);
        }
        this.b.a(context, i, fduVar);
    }

    public void e() {
        mur murVar = this.b;
        if (murVar != null) {
            murVar.d();
            this.b = null;
        }
        f();
    }

    private void f() {
        fdu fduVar = c;
        if (fduVar != null) {
            fduVar.ah();
            d((fdu) null);
        }
        fdz fdzVar = e;
        if (fdzVar != null) {
            fdzVar.n();
            d((fdz) null);
        }
    }

    public void b(Context context, fdz fdzVar, UserInfomation userInfomation) {
        if (fdzVar == null || context == null) {
            LogUtil.b("Share_PluginSocialShare", "exeShare() shareContent/mContext == null");
        } else {
            d(fdzVar);
            b(userInfomation);
        }
    }

    private void a(Context context, fdu fduVar) {
        if (CollectionUtils.d(fduVar.b())) {
            ReleaseLogUtil.d("Share_PluginSocialShare", "shareEditContentList is empty");
            return;
        }
        fdz fdzVar = fduVar.b().get(0);
        if (fdzVar == null) {
            ReleaseLogUtil.d("Share_PluginSocialShare", "shareEditContent is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("shareWaterMarkData", fdzVar.g());
        if (!CollectionUtils.d(fdzVar.f())) {
            bundle.putSerializable("waterMarkIds", new ArrayList(fdzVar.f()));
        }
        bundle.putString("shareSource", String.valueOf(fduVar.aa()));
        bundle.putInt("downLoadId", fdzVar.c());
        bundle.putBoolean("isDownloadMarkFromCloud", true);
        bundle.putString("shareModuleId", fduVar.m());
        AppRouter.b("/PluginSocialShare/CustomizeShareActivity").zF_(bundle).c(context);
    }
}
