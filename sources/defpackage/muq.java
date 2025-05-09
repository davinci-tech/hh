package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.mediaprocess.VideoUtil;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.gles.Constant;
import health.compact.a.Services;
import java.io.File;

/* loaded from: classes6.dex */
public class muq {
    private Context b;
    private fdu c;

    muq(Context context, fdu fduVar) {
        this.b = context;
        this.c = fduVar;
    }

    void c() {
        fdu fduVar = this.c;
        if (fduVar == null || this.b == null) {
            LogUtil.b("Share_FamilyGroupInteractors", "shareByFamilyGroup fail:mShareContent/mShareHandler/mContext is null");
            return;
        }
        int u = fduVar.u();
        if (u != 0) {
            if (u != 1) {
                if (u != 2) {
                    if (u != 4) {
                        if (u == 5) {
                            LogUtil.a("Share_FamilyGroupInteractors", "SHARE_WAY_MULTI_IMG");
                            a();
                            return;
                        } else if (u != 6) {
                            if (u == 8 || u == 9) {
                                LogUtil.a("Share_FamilyGroupInteractors", "SHARE_VIDEO");
                                b();
                                return;
                            } else {
                                LogUtil.b("Share_FamilyGroupInteractors", "UNKNOWN FamilyGroupWay!");
                                return;
                            }
                        }
                    }
                }
            }
            LogUtil.a("Share_FamilyGroupInteractors", "SHARE_WAY_PIC");
            a();
            return;
        }
        LogUtil.a("Share_FamilyGroupInteractors", "SHARE_WAY_TEXT");
        b(this.c);
    }

    private void b(fdu fduVar) {
        Bitmap cpI_ = cpI_();
        dst dstVar = new dst();
        dstVar.d("link");
        dstVar.f(fduVar.y());
        dstVar.i(fduVar.t());
        dstVar.c(fduVar.q());
        String cqB_ = mwd.cqB_(this.b, "linkimage.webp", cpI_);
        dstVar.b(cqB_);
        dstVar.e(String.valueOf(moh.a(cqB_)));
        LogUtil.h("Share_FamilyGroupInteractors", "shareLink shareParamData: ", dstVar.toString());
        a(dstVar);
    }

    private void a() {
        String cqB_ = mwd.cqB_(this.b, "shareimage.webp", cpI_());
        dst dstVar = new dst();
        dstVar.d(Constant.TYPE_PHOTO);
        dstVar.g(cqB_);
        dstVar.e(String.valueOf(moh.a(cqB_)));
        LogUtil.h("Share_FamilyGroupInteractors", "shareImage shareParamData: ", dstVar.toString());
        a(dstVar);
    }

    private Bitmap cpI_() {
        Bitmap bitmap;
        if (this.c.awm_() == null || this.c.awm_().isRecycled()) {
            LogUtil.b("Share_FamilyGroupInteractors", "ERROR getSharePicContent in getShareContentBitmap()");
            bitmap = null;
        } else {
            bitmap = this.c.awm_();
        }
        return (bitmap != null || TextUtils.isEmpty(this.c.i())) ? bitmap : BitmapFactory.decodeFile(this.c.i());
    }

    private void a(dst dstVar) {
        ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).startFamilySpaceH5(BaseApplication.e(), dstVar);
    }

    private void b() {
        Uri uri;
        LogUtil.a("Share_FamilyGroupInteractors", "shareFamilyGroupVideo enter");
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
            LogUtil.a("Share_FamilyGroupInteractors", "video uri err");
            uri = null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "shareFamilyGroupVideo uri:";
        objArr[1] = uri != null ? uri.getPath() : "";
        LogUtil.a("Share_FamilyGroupInteractors", objArr);
        Bitmap zg_ = VideoUtil.zg_(uri);
        String cqB_ = zg_ != null ? mwd.cqB_(this.b, "videoimage.webp", zg_) : null;
        if (this.c.u() == 8) {
            dst dstVar = new dst();
            dstVar.d("video");
            dstVar.g(this.c.x());
            dstVar.b(cqB_);
            dstVar.a(String.valueOf(moh.a(this.c.x())));
            dstVar.e(String.valueOf(moh.a(cqB_)));
            LogUtil.a("Share_FamilyGroupInteractors", "sharevideo with path");
            a(dstVar);
        }
    }
}
