package com.huawei.health.routeradapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.alipay.sdk.m.p.e;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.arkuix.IntentParams;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gnm;
import java.util.Objects;

/* loaded from: classes.dex */
public class ArkUIPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.b("EmotionCardPretreatmentService", "uri is null.");
            return false;
        }
        LogUtil.a("EmotionCardPretreatmentService", "uri:", zN_.toString());
        if (zN_.toString().contains("emotionHealth")) {
            avj_(context, zN_);
        } else {
            LogUtil.a("EmotionCardPretreatmentService", "uri does not match");
        }
        return false;
    }

    private void avj_(Context context, Uri uri) {
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), "com.huawei.hwarkuix.EntryAbilityActivity");
        String queryParameter = uri.getQueryParameter("tab");
        String str = "emotion";
        if (!Objects.equals(queryParameter, "emotion")) {
            if (Objects.equals(queryParameter, "stress")) {
                str = "stress";
            } else {
                LogUtil.a("EmotionCardPretreatmentService", "uri params not matched!");
            }
        }
        String intentParams = IntentParams.builder().addPageId(ArkUIXConstants.KNIT_EMOTION).addPageType("8").addTimeStamp("0").addRecord("{'tab':\"" + str + "\"}").build().toString();
        LogUtil.a("EmotionCardPretreatmentService", "params start with: ", intentParams);
        intent.putExtra(e.n, intentParams);
        intent.putExtra(ArkUIXConstants.ARKUIX_MODULE_TYPE_KEY, 1);
        gnm.aPC_(context, intent);
    }
}
