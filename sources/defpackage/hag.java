package defpackage;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.gles.Constant;

/* loaded from: classes4.dex */
public class hag {
    public static int d() {
        Object systemService = BaseApplication.getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h("Track_ScreenDescription", "getScreenHeight WindowManager is null or class cast exception");
            return Constant.FBO_HEIGHT;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int a() {
        Object systemService = BaseApplication.getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h("Track_ScreenDescription", "getScreenWidth WindowManager is null or class cast exception");
            return 1080;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int a(float f) {
        return nsn.c(BaseApplication.getContext(), f);
    }
}
