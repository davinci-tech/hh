package defpackage;

import android.content.res.AssetManager;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public class mwx {
    public static boolean d() {
        return false;
    }

    private static mwv e() {
        AssetManager assets = BaseApplication.e().getAssets();
        try {
            String c = CommonUtil.c(drd.d("com.huawei.health_Sport_Common", "automatic_recognize_walk", "json"));
            if (TextUtils.isEmpty(c)) {
                LogUtil.h("Track_AutoTrackUtils", "cloudConfigPath is illegal!");
                return null;
            }
            String e = mrx.e(new File(c));
            LogUtil.h("Track_AutoTrackUtils", "getAutoWalkLocalPhoneModel.readFileToData jsonData = ", e);
            if (TextUtils.isEmpty(e)) {
                mwv mwvVar = (mwv) ixu.d(assets.open("automatic_recognize_walk.json"), mwv.class);
                Object[] objArr = new Object[2];
                objArr[0] = "mDefaultConfig : ";
                objArr[1] = mwvVar == null ? 0 : mwvVar.b();
                ReleaseLogUtil.e("Track_AutoTrackUtils", objArr);
                d(mwvVar);
                return mwvVar;
            }
            mwv mwvVar2 = (mwv) new Gson().fromJson(e, mwv.class);
            d(mwvVar2);
            return mwvVar2;
        } catch (JsonParseException unused) {
            ReleaseLogUtil.c("Track_AutoTrackUtils", "JsonParseException failed");
            return null;
        } catch (IOException e2) {
            ReleaseLogUtil.c("Track_AutoTrackUtils", "loadDefaultCardSetConfig failed, ", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static void d(mwv mwvVar) {
        if (mwvVar != null) {
            mwvVar.e(0);
        }
    }

    public static boolean b() {
        mwv e = e();
        if (e == null) {
            LogUtil.h("Track_AutoTrackUtils", "read native file is false");
            return false;
        }
        List<String> d = e.d();
        return CommonUtil.c((String[]) d.toArray(new String[d.size()]));
    }
}
