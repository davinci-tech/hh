package com.huawei.ui.main.stories.smartcenter.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.gso;
import defpackage.hps;
import defpackage.ixx;
import defpackage.knu;
import defpackage.kvs;
import defpackage.rvz;
import health.compact.a.Utils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class SmartMsgSkipActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f10464a;
    private String b = "";
    private int d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f10464a = this;
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        int intExtra = intent.getIntExtra("id", 0);
        int intExtra2 = intent.getIntExtra("msgType", 0);
        String stringExtra = intent.getStringExtra("msgContent");
        this.b = intent.getStringExtra(CommonUtil.MSG_TITLE);
        this.d = intent.getIntExtra("from", 0);
        LogUtil.c("SmartMsgSkipActivity", "SmartCard_mSmartMsgKeyId", Integer.valueOf(intExtra));
        e(intExtra, intExtra2, stringExtra);
        finish();
    }

    private void e(int i, int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 2);
        kvs.b(this.f10464a).bSl_(i, contentValues);
        LogUtil.a("SmartMsgSkipActivity", "skipToTargetActivity, msgType=", Integer.valueOf(i2));
        switch (i2) {
            case 20000:
                c(20000, i2);
                Guidepost b = AppRouter.b("/HWUserProfileMgr/MyTargetActivity");
                a(b);
                b.c(this.f10464a);
                finish();
                break;
            case SmartMsgConstant.MSG_TYPE_SLEEP_USER /* 40007 */:
                rvz.c(this.d, this.f10464a, str);
                c(SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER, i2);
                break;
            case 50001:
                c(50000, i2);
                rvz.d(this.d, this.f10464a, str);
                finish();
                break;
            case 60000:
                c(60000, i2);
                Guidepost b2 = AppRouter.b("/HWUserProfileMgr/UserInfoActivity");
                a(b2);
                b2.c(this.f10464a);
                finish();
                break;
            case SmartMsgConstant.MSG_TYPE_TRACK_RECOVER_TIME_RECOMMEND /* 90001 */:
                c(SmartMsgConstant.MSG_TYPE_TRACK_RECOVER_TIME_RECOMMEND, i2);
                e(str);
                break;
            case 100000:
                rvz.a(this.f10464a, str);
                c(100000, i2);
                break;
            default:
                LogUtil.h("SmartMsgSkipActivity", "no match target activity");
                break;
        }
    }

    private void e(String str) {
        long j;
        long j2;
        try {
            String[] split = new JSONObject(str).getString(SmartMsgConstant.MSG_CONTENT_MESSAGE_CENTER_DETAIL_URL).split(" ");
            if (split == null || split.length < 2) {
                LogUtil.h("SmartMsgSkipActivity", "can't resolve trackUrl");
                return;
            }
            try {
                j = Long.parseLong(split[0]);
            } catch (NumberFormatException e) {
                e = e;
                j = 0;
            }
            try {
                j2 = Long.parseLong(split[1]);
            } catch (NumberFormatException e2) {
                e = e2;
                LogUtil.b("SmartMsgSkipActivity", e.getMessage());
                j2 = 0;
                if (j != 0) {
                    hps.a(j, j2, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.smartcenter.activity.SmartMsgSkipActivity.5
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (i == 0 && (obj instanceof knu)) {
                                knu knuVar = (knu) obj;
                                gso.e().e(knuVar.d(), knuVar.b());
                            } else {
                                LogUtil.h("SmartMsgSkipActivity", "MSG_TYPE_TRACK_RECOVER_TIME_RECOMMEND Not return valid data");
                            }
                        }
                    });
                }
                finish();
            }
            if (j != 0 && j2 != 0) {
                hps.a(j, j2, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.smartcenter.activity.SmartMsgSkipActivity.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0 && (obj instanceof knu)) {
                            knu knuVar = (knu) obj;
                            gso.e().e(knuVar.d(), knuVar.b());
                        } else {
                            LogUtil.h("SmartMsgSkipActivity", "MSG_TYPE_TRACK_RECOVER_TIME_RECOMMEND Not return valid data");
                        }
                    }
                });
            }
            finish();
        } catch (JSONException e3) {
            LogUtil.b("SmartMsgSkipActivity", "track content parse error: ", e3.getMessage());
        }
    }

    private void a(Guidepost guidepost) {
        if (this.d == 1) {
            guidepost.a(1879048192);
        }
    }

    private void c(int i, int i2) {
        int i3 = this.d;
        if (i3 == 2) {
            HashMap hashMap = new HashMap(4);
            String value = AnalyticsValue.HEALTH_HOME_OPERA_POSITION_CLICK_2010075.value();
            hashMap.put("click", "1");
            hashMap.put("type", Integer.valueOf(i2));
            if (!Utils.o()) {
                hashMap.put("title", this.b);
            }
            ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
            return;
        }
        if (i3 == 0) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("click", "1");
            hashMap2.put("module", Integer.valueOf(i));
            hashMap2.put("type", Integer.valueOf(i2));
            if (!Utils.o()) {
                hashMap2.put("title", this.b);
            }
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HOME_SMART_CARD_MSG_CLICK_2010051.value(), hashMap2, 0);
            return;
        }
        LogUtil.h("SmartMsgSkipActivity", "no match BI Event");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
