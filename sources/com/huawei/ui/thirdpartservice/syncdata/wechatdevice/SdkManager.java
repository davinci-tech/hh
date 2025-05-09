package com.huawei.ui.thirdpartservice.syncdata.wechatdevice;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.syncdata.wechatdevice.SdkManager;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import defpackage.nsn;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes8.dex */
public class SdkManager {
    private IWXAPI b = WXAPIFactory.createWXAPI(BaseApplication.e(), "wx36bda3d35fbcfd06");

    public interface launchWechatCallBack {
        void onLaunchFailed();
    }

    public static boolean e() {
        return Utils.c(BaseApplication.e(), "com.tencent.mm");
    }

    public void e(Context context, String str, launchWechatCallBack launchwechatcallback) {
        LogUtil.a("WechatSdkManager", "bind thread:", Thread.currentThread().getName());
        if (!e()) {
            launchwechatcallback.onLaunchFailed();
            return;
        }
        this.b.registerApp("wx36bda3d35fbcfd06");
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_471f5f7b25a9";
        req.path = "pages/discover-new/discover-new?from=wxsport&&ticket=" + str;
        req.miniprogramType = 0;
        b(context, launchwechatcallback, req);
    }

    public void e(Context context, List<String> list, launchWechatCallBack launchwechatcallback) {
        LogUtil.a("WechatSdkManager", "bind thread:", Thread.currentThread().getName());
        if (!e()) {
            launchwechatcallback.onLaunchFailed();
            return;
        }
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_471f5f7b25a9";
        req.path = "pages/delete-devices/delete-devices?from=wxsport&&sdkIdList=" + new Gson().toJson(list);
        req.miniprogramType = 0;
        b(context, launchwechatcallback, req);
    }

    private void a() {
        LocalBroadcastManager.getInstance(BaseApplication.e()).sendBroadcast(new Intent("com.huawei.ui.thirdpartservice.WX_MINIPTOGRAM_LAUNCH"));
    }

    private void b(Context context, final launchWechatCallBack launchwechatcallback, final WXLaunchMiniProgram.Req req) {
        nsn.cLO_("com.tencent.mm", context, context.getString(R.string._2130844962_res_0x7f021d22), new View.OnClickListener() { // from class: sjv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SdkManager.this.dYi_(req, view);
            }
        }, new View.OnClickListener() { // from class: sjw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SdkManager.dYh_(SdkManager.launchWechatCallBack.this, view);
            }
        });
    }

    public /* synthetic */ void dYi_(WXLaunchMiniProgram.Req req, View view) {
        this.b.sendReq(req);
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dYh_(launchWechatCallBack launchwechatcallback, View view) {
        launchwechatcallback.onLaunchFailed();
        ViewClickInstrumentation.clickOnView(view);
    }
}
