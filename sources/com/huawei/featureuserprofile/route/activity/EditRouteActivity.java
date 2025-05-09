package com.huawei.featureuserprofile.route.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.huawei.featureuserprofile.route.activity.EditRouteActivity;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.bto;
import defpackage.btq;
import defpackage.btw;
import defpackage.ixx;
import defpackage.nrs;
import defpackage.nsf;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class EditRouteActivity extends BaseActivity {
    private boolean b;
    private HealthEditText c;
    private int d;
    private boolean e;
    private String f;
    private RelativeLayout h;
    private RouteData i;
    private CustomTitleBar j;
    private final Handler g = new c();

    /* renamed from: a, reason: collision with root package name */
    private final InputFilter f1997a = new InputFilter() { // from class: com.huawei.featureuserprofile.route.activity.EditRouteActivity.2
        final Pattern e = Pattern.compile("(?:[🌀-🗿]|[🤀-🧿]|[😀-🙏]|[🚀-\u1f6ff]|[☀-⛿]️?|[✀-➿]️?|Ⓜ️?|[🇦-🇿]{1,2}|[🅰🅱🅾🅿🆎🆑-🆚]️?|[#*0-9]️?⃣|[↔-↙↩-↪]️?|[⬅-⬇⬛⬜⭐⭕]️?|[⤴⤵]️?|[〰〽]️?|[㊗㊙]️?|[🈁🈂🈚🈯🈲-🈺🉐🉑]️?|[‼⁉]️?|[▪▫▶◀◻-◾]️?|[©®]️?|[™ℹ]️?|🀄️?|🃏️?|[⌚⌛⌨⏏⏩-⏳⏸-⏺]️?)", 66);

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (!this.e.matcher(charSequence).find()) {
                return charSequence;
            }
            LogUtil.c("Track_EditRouteActivity", "can not insert emoji");
            return "";
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hw_edit_route_activity);
        boolean booleanExtra = getIntent().getBooleanExtra("IS_NEED_CHANGE_POINT", false);
        this.d = getIntent().getIntExtra("from", 0);
        b(booleanExtra);
        a(booleanExtra);
    }

    private void b(final boolean z) {
        getWindow().setBackgroundDrawableResource(R.color._2131296690_res_0x7f0901b2);
        ((HealthSubHeader) findViewById(R.id.sub_header)).setSubHeaderBackgroundColor(ContextCompat.getColor(this, R.color._2131296690_res_0x7f0901b2));
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        this.j = customTitleBar;
        customTitleBar.setLeftButtonVisibility(0);
        this.j.setLeftButtonDrawable(getDrawable(R.drawable._2131429371_res_0x7f0b07fb), nsf.h(R.string._2130850617_res_0x7f023339));
        if (nrs.a(this)) {
            this.j.setTitleSize(getResources().getDimension(R.dimen._2131362673_res_0x7f0a0371));
        } else {
            this.j.setTitleSize(getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
        this.j.setTitleTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.j.setTitleText(getString(R.string._2130838796_res_0x7f02050c));
        this.j.setRightButtonVisibility(0);
        this.j.setRightButtonDrawable(getDrawable(R.drawable._2131429706_res_0x7f0b094a), nsf.h(R.string._2130841395_res_0x7f020f33));
        this.j.setRightButtonOnClickListener(new View.OnClickListener() { // from class: bsk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditRouteActivity.this.ub_(z, view);
            }
        });
        HealthEditText healthEditText = (HealthEditText) findViewById(R.id.et_name);
        this.c = healthEditText;
        ua_(healthEditText);
        this.c.requestFocus();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.c.setTextDirection(4);
        }
        getWindow().setSoftInputMode(5);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rel_route_loading);
        this.h = relativeLayout;
        relativeLayout.setVisibility(8);
    }

    public /* synthetic */ void ub_(boolean z, View view) {
        String trim = this.c.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            b(getString(R.string._2130838798_res_0x7f02050e));
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.e && z && !this.b) {
            LogUtil.a("Track_EditRouteActivity", "transfer point not finish,please try again");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (CommonUtil.aa(this)) {
            this.i.setRouteName(trim);
            this.j.setRightButtonClickable(false);
            this.h.setVisibility(0);
            if (this.e) {
                e();
            } else {
                a();
            }
        } else {
            c(R$string.IDS_hw_show_set_about_privacy_connectting_error);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ua_(EditText editText) {
        ArrayList arrayList = new ArrayList(Arrays.asList(editText.getFilters()));
        arrayList.add(this.f1997a);
        editText.setFilters((InputFilter[]) arrayList.toArray(new InputFilter[arrayList.size()]));
    }

    private void a(boolean z) {
        RouteData e = btq.e();
        this.i = e;
        if (e != null) {
            this.c.setText(e.getRouteName());
            this.f = this.i.getRouteName();
            HealthEditText healthEditText = this.c;
            healthEditText.setSelection(healthEditText.getText().toString().length());
            boolean booleanExtra = getIntent().getBooleanExtra("is_add", true);
            this.e = booleanExtra;
            if (booleanExtra && z) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: bsl
                    @Override // java.lang.Runnable
                    public final void run() {
                        EditRouteActivity.this.c();
                    }
                });
                return;
            }
            return;
        }
        finish();
    }

    public /* synthetic */ void c() {
        long currentTimeMillis = System.currentTimeMillis();
        RouteData routeData = this.i;
        routeData.setRoutePoints(btw.d(routeData.getRoutePoints()));
        this.b = true;
        LogUtil.a("Track_EditRouteActivity", "transferTrackPoint waste time:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.i);
        bto.d(arrayList, new RouteResultCallBack<CloudCommonReponse>() { // from class: com.huawei.featureuserprofile.route.activity.EditRouteActivity.4
            @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                EditRouteActivity.this.a(cloudCommonReponse, true);
            }

            @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
            public void onFailure(Throwable th) {
                LogUtil.b("Track_EditRouteActivity", "saveRoute onFailure:", th.getMessage());
                if (EditRouteActivity.this.i != null) {
                    EditRouteActivity.this.i.setRouteName(EditRouteActivity.this.f);
                }
                EditRouteActivity.this.b();
            }
        });
    }

    private void b(final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.route.activity.EditRouteActivity.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Track_EditRouteActivity", "from:", Integer.valueOf(i));
                HashMap hashMap = new HashMap();
                hashMap.put("click", 1);
                hashMap.put("from", Integer.valueOf(i));
                ixx.d().d(BaseApplication.getContext(), "1040120", hashMap, 0);
            }
        });
    }

    private void a() {
        bto.b(this.i, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.featureuserprofile.route.activity.EditRouteActivity.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                EditRouteActivity.this.a(cloudCommonReponse, false);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("Track_EditRouteActivity", "updateRoute onFailure:", th.getMessage());
                if (EditRouteActivity.this.i != null) {
                    EditRouteActivity.this.i.setRouteName(EditRouteActivity.this.f);
                }
                EditRouteActivity.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CloudCommonReponse cloudCommonReponse, final boolean z) {
        int i;
        LogUtil.a("Track_EditRouteActivity", "isSave=", Boolean.valueOf(z), " result = ", cloudCommonReponse.toString());
        if (cloudCommonReponse.getResultCode().intValue() == 0) {
            runOnUiThread(new Runnable() { // from class: bsm
                @Override // java.lang.Runnable
                public final void run() {
                    EditRouteActivity.this.d(z);
                }
            });
            if (z && (i = this.d) == 1) {
                b(i);
            }
            startActivity(new Intent(this, (Class<?>) MyRouteActivity.class));
            finish();
            return;
        }
        if (cloudCommonReponse.getResultCode().intValue() == 31013) {
            c(R.string._2130838813_res_0x7f02051d);
        } else if (cloudCommonReponse.getResultCode().intValue() == 31014) {
            c(R.string._2130838814_res_0x7f02051e);
        } else if (cloudCommonReponse.getResultCode().intValue() == 31015) {
            c(R.string._2130838815_res_0x7f02051f);
        } else if (cloudCommonReponse.getResultCode().intValue() == 1001) {
            c(R.string._2130838835_res_0x7f020533);
        } else {
            LogUtil.a("Track_EditRouteActivity", "save or update resp resultCode:", cloudCommonReponse.getResultCode(), ",resp resultDesc:", cloudCommonReponse.getResultDesc());
        }
        RouteData routeData = this.i;
        if (routeData != null) {
            routeData.setRouteName(this.f);
        }
        b();
    }

    public /* synthetic */ void d(boolean z) {
        if (z) {
            b(getString(R.string.IDS_device_wifi_my_qrcode_add_member_success_title));
        } else {
            b(getString(R.string._2130838836_res_0x7f020534));
        }
    }

    private void c(int i) {
        Message obtainMessage = this.g.obtainMessage();
        obtainMessage.obj = getString(i);
        obtainMessage.what = 1;
        this.g.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        Toast.makeText(this, str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Message obtainMessage = this.g.obtainMessage();
        obtainMessage.what = 2;
        this.g.sendMessage(obtainMessage);
    }

    /* loaded from: classes7.dex */
    static class c extends BaseHandler<EditRouteActivity> {
        private c(EditRouteActivity editRouteActivity) {
            super(editRouteActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: uc_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EditRouteActivity editRouteActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof String) {
                    editRouteActivity.b((String) message.obj);
                }
            } else {
                if (i != 2) {
                    return;
                }
                editRouteActivity.h.setVisibility(8);
                editRouteActivity.j.setRightButtonClickable(true);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.i = null;
        btq.a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
