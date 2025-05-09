package com.huawei.health.market.comment;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import defpackage.ixx;
import defpackage.jdm;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class MarketCommentDialog {
    private final Context b;
    private CustomViewDialog d;

    public MarketCommentDialog(Context context) {
        this.b = context;
    }

    public void e() {
        View inflate = View.inflate(this.b, R.layout.market_comment_view, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_market);
        Drawable drawable = this.b.getResources().getDrawable(R.mipmap._2131820804_res_0x7f110104);
        if (drawable != null) {
            drawable.setAutoMirrored(true);
        }
        imageView.setImageDrawable(drawable);
        CustomViewDialog e = new CustomViewDialog.Builder(this.b).czg_(inflate).czd_(this.b.getString(R$string.IDS_hwh_market_comment_reject).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: com.huawei.health.market.comment.MarketCommentDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MarketCommentDialog.this.c(AnalyticsValue.HEALTH_MARKET_COMMENT_DIALOG_2040060.value(), "1");
                MarketCommentDialog.this.d.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czf_(this.b.getString(R$string.IDS_hwh_market_comment_evaluate).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: com.huawei.health.market.comment.MarketCommentDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MarketCommentDialog.this.c(AnalyticsValue.HEALTH_MARKET_COMMENT_DIALOG_2040060.value(), "2");
                MarketCommentDialog.this.a();
                MarketCommentDialog.this.d.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.d = e;
        e.setCancelable(false);
        this.d.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.b, str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Uri parse;
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.addFlags(268435456);
            if (Utils.o()) {
                if (jdm.a(this.b, "com.huawei.appmarket")) {
                    intent.setPackage("com.huawei.appmarket");
                    parse = null;
                } else {
                    LogUtil.a("MarketCommentActivity", " oversea Not installed Market");
                    return;
                }
            } else if (jdm.b(this.b, "com.huawei.appmarket")) {
                intent.setPackage("com.huawei.appmarket");
                parse = Uri.parse("hiapplink://com.huawei.appmarket?appId=C10414141&channelId=%E8%BF%90%E5%8A%A8%E5%81%A5%E5%BA%B7&id=d794c47a311e42558c2d9da299863601&s=54FAAEE06C524F8594B41D8550C4C39CE65C93322ACB8B3586D215D175EBDA44&detailType=0&v=&callType=AGDLINK&installType=0000");
            } else {
                LogUtil.a("MarketCommentActivity", "Not installed Market");
                return;
            }
            if (parse == null) {
                parse = Uri.parse("market://details?id=com.huawei.health");
            }
            if (parse == null) {
                LogUtil.h("MarketCommentActivity", "uri is null");
            } else {
                intent.setData(parse);
                aly_(intent);
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("MarketCommentActivity", "Exception: jumpToMarket");
        }
    }

    private void aly_(Intent intent) {
        Context context = this.b;
        if (context == null) {
            LogUtil.h("MarketCommentActivity", "startActivityForMarket mContext is null");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.h("MarketCommentActivity", "startActivityForMarket packageManager is null");
            return;
        }
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (queryIntentActivities.isEmpty()) {
            LogUtil.h("MarketCommentActivity", "startActivityForMarket resolveInfoList is empty");
            return;
        }
        Intent intent2 = new Intent(intent);
        ResolveInfo resolveInfo = queryIntentActivities.get(0);
        if (resolveInfo == null) {
            LogUtil.h("MarketCommentActivity", "startActivityForMarket resolveInfo is null");
            return;
        }
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        if (activityInfo == null || activityInfo.packageName == null || activityInfo.name == null) {
            LogUtil.h("MarketCommentActivity", "startActivityForMarket resolveInfo activityInfo is null");
            return;
        }
        try {
            intent2.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
            nsn.cLM_(intent2, activityInfo.packageName, this.b, nsf.h(com.huawei.ui.commonui.R$string.IDS_device_fragment_application_market));
        } catch (ActivityNotFoundException e) {
            LogUtil.b("MarketCommentActivity", "startActivityForMarket ActivityNotFoundException e:", e.getMessage());
        }
    }
}
