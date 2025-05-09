package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.views.audition.AuditionActivity;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes3.dex */
public class ekx {
    public static void aqu_(final View view, final ImageView imageView, final SingleGridContent singleGridContent, final Map<String, String> map) {
        if (imageView == null || singleGridContent == null) {
            LogUtil.h("AuditionUtils", "setAuditionPlayBtnClick auditionBtn or content is null.");
            return;
        }
        if (TextUtils.equals(singleGridContent.getDynamicDataId(), ele.c().b())) {
            imageView.setImageResource(R.drawable._2131428570_res_0x7f0b04da);
            aqt_(imageView, singleGridContent);
        } else {
            imageView.setImageResource(R.drawable._2131430254_res_0x7f0b0b6e);
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: elb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ekx.aqs_(imageView, view, singleGridContent, map, view2);
            }
        });
    }

    static /* synthetic */ void aqs_(ImageView imageView, View view, SingleGridContent singleGridContent, Map map, View view2) {
        if (nsn.cLj_(500, imageView)) {
            LogUtil.h("AuditionUtils", "onClick() view click too fast.");
            ViewClickInstrumentation.clickOnView(view2);
        } else if (!LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            aqv_(view, imageView, singleGridContent, map);
            ViewClickInstrumentation.clickOnView(view2);
        } else {
            LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: ela
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.h("AuditionUtils", "AuditionPlayBtnClick login errorCode = ", Integer.valueOf(i));
                }
            }, "");
            ViewClickInstrumentation.clickOnView(view2);
        }
    }

    private static void aqv_(View view, ImageView imageView, SingleGridContent singleGridContent, Map<String, String> map) {
        int vip = singleGridContent.getVip();
        UserMemberInfo a2 = gpn.a();
        boolean z = (a2 == null || a2.getMemberFlag() != 1 || gpn.d(a2)) ? false : true;
        if (vip == 0 || ((vip == 1 && z) || (vip == 2 && singleGridContent.getIsPay() == 1))) {
            LogUtil.a("AuditionUtils", "setAuditionPlayBtnClick, isVip==", Integer.valueOf(vip), " isVipUser=", Boolean.valueOf(z), " isPayed=", Integer.valueOf(singleGridContent.getIsPay()));
            view.performClick();
            return;
        }
        if (TextUtils.equals(singleGridContent.getDynamicDataId(), ele.c().b())) {
            LogUtil.a("AuditionUtils", "stopAudio()");
            ele.c().f();
            imageView.setImageResource(R.drawable._2131430254_res_0x7f0b0b6e);
            return;
        }
        if (!TextUtils.isEmpty(ele.c().b())) {
            ObserverManagerUtil.c("MARKETING_AUDITION_BTN_REFRESH", Integer.valueOf(R.drawable._2131430254_res_0x7f0b0b6e));
            ObserverManagerUtil.e("MARKETING_AUDITION_BTN_REFRESH");
        }
        Context e = BaseApplication.e();
        Intent intent = new Intent(e, (Class<?>) AuditionActivity.class);
        intent.setFlags(268435456);
        if (map != null) {
            intent.putExtra("resource_bi_map", (Serializable) map);
        }
        intent.putExtra("audition_content", singleGridContent);
        intent.putExtra("is_vip_expired", gpn.d(a2));
        e.startActivity(intent);
        imageView.setImageResource(R.drawable._2131428570_res_0x7f0b04da);
        aqt_(imageView, singleGridContent);
    }

    private static void aqt_(final ImageView imageView, final SingleGridContent singleGridContent) {
        ObserverManagerUtil.d(new Observer() { // from class: ekz
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str, Object[] objArr) {
                ekx.aqr_(imageView, singleGridContent, str, objArr);
            }
        }, "MARKETING_AUDITION_BTN_REFRESH");
    }

    static /* synthetic */ void aqr_(ImageView imageView, SingleGridContent singleGridContent, String str, Object[] objArr) {
        if (koq.e(objArr, 0) && (objArr[0] instanceof Integer)) {
            LogUtil.a("AuditionUtils", "receiver play button statu change message");
            int intValue = ((Integer) objArr[0]).intValue();
            if (intValue == 0) {
                intValue = R.drawable._2131428570_res_0x7f0b04da;
            }
            imageView.setImageResource(intValue);
        }
        if (koq.e(objArr, 1)) {
            Object obj = objArr[1];
            if (obj instanceof Integer) {
                singleGridContent.setIsPay(((Integer) obj).intValue());
                LogUtil.a("AuditionUtils", "receiver course IsPay = ", Integer.valueOf(singleGridContent.getIsPay()));
            }
        }
    }
}
