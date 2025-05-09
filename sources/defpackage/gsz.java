package defpackage;

import android.webkit.JavascriptInterface;
import com.google.gson.JsonParseException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gsz extends JsBaseModule {
    @JavascriptInterface
    public void getContent(long j, String str) {
        try {
            gta gtaVar = (gta) HiJsonUtil.e(str, gta.class);
            MotionPath c = gwk.c(this.mContext, gtaVar.b(), gtaVar.e());
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) HiJsonUtil.e(gtaVar.a(), MotionPathSimplify.class);
            if (gtaVar.b() != null && c != null && motionPathSimplify != null) {
                onSuccessCallback(j, HiJsonUtil.e(gvz.c(c, motionPathSimplify)));
            } else {
                onFailureCallback(j, AuthInternalPickerConstant.UNKOWN_ERROR);
            }
        } catch (JsonParseException e) {
            LogUtil.b(this.TAG, "getContent JsonParseException:", ExceptionUtils.d(e));
            onFailureCallback(j, ExceptionUtils.d(e));
        }
    }
}
