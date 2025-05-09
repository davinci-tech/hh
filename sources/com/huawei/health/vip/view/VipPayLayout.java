package com.huawei.health.vip.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProBridgeManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.vip.VipInnerApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import defpackage.bzs;
import java.util.Map;

/* loaded from: classes.dex */
public class VipPayLayout extends LinearLayout {
    private H5ProWebView b;
    private View c;
    private Context e;

    public VipPayLayout(Context context, String str, String str2) {
        super(context);
        this.e = context;
        c(str, str2, null);
    }

    public VipPayLayout(Context context, String str, String str2, Map<String, Object> map) {
        super(context);
        this.e = context;
        c(str, str2, map);
    }

    private void c(String str, String str2, Map<String, Object> map) {
        LogUtil.a("VipPayLayout", "VipPayLayout initView");
        if (TextUtils.isEmpty(str2)) {
            str2 = "#/PayPopup";
        } else {
            LogUtil.a("VipPayLayout", "jump into the path: ", str2);
        }
        if (TextUtils.isEmpty(str)) {
            str = "com.huawei.health.h5.vip";
        } else {
            LogUtil.a("VipPayLayout", "start the package: ", str);
        }
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.vip_pay_info_layout, this);
        this.c = inflate;
        this.b = (H5ProWebView) inflate.findViewById(R.id.vip_pay_webview_bar);
        bzs.e().initH5Pro();
        bzs e = bzs.e();
        if (map != null) {
            c(str, str2, e, map);
        } else {
            H5ProClient.startH5MiniProgram(str, this.b, new H5ProLaunchOption.Builder().addPath(str2).setForceDarkMode(1).addCustomizeJsModule("VipInnerApi", VipInnerApi.class).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).build());
        }
    }

    private void c(String str, String str2, bzs bzsVar, Map<String, Object> map) {
        String c = c(map, WebViewHelp.BI_KEY_PULL_FROM);
        String c2 = c(map, "resourceId");
        String c3 = c(map, "resourceName");
        String c4 = c(map, "pullOrder");
        String c5 = c(map, "workout_id");
        H5ProClient.startH5MiniProgram(str, this.b, new H5ProLaunchOption.Builder().addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, c).addGlobalBiParam("resourceId", c2).addGlobalBiParam("resourceName", c3).addGlobalBiParam("pullOrder", c4).addGlobalBiParam("itemId", c5).addGlobalBiParam("algId", c(map, "algId")).addPath(str2).setForceDarkMode(1).addCustomizeJsModule("VipInnerApi", VipInnerApi.class).addCustomizeJsModule("innerapi", bzsVar.getCommonJsModule("innerapi")).build());
    }

    private String c(Map<String, Object> map, String str) {
        return ((map.get(str) instanceof String) || (map.get(str) instanceof Integer)) ? (String) map.get(str) : "";
    }

    public void aRt_(int i, int i2, Intent intent) {
        if (this.b == null) {
            LogUtil.h("VipPayLayout", "mH5ProWebView is null in notifyActivityResult.");
        } else {
            H5ProBridgeManager.getInstance().notifyActivityResult(this.b.getInstance(), i, i2, intent);
        }
    }
}
