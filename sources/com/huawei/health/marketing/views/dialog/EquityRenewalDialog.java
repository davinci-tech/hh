package com.huawei.health.marketing.views.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.PopUpMemberTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.dialog.EquityRenewalDialog;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.RepeatResourceBenefitInfo;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eil;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class EquityRenewalDialog extends BaseDialog implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ResourceBriefInfo f2901a;
    private HealthTextView b;
    private ImageView c;
    private Handler d;
    private Context e;
    private RepeatResourceBenefitInfo f;
    private long g;
    private int h;
    private PopUpMemberTemplate i;
    private HealthButton j;
    private HealthTextView l;
    private ImageView o;

    public EquityRenewalDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.g = 0L;
        this.h = 0;
        this.d = new Handler(Looper.getMainLooper());
        this.e = context;
    }

    public void b(int i, ResourceBriefInfo resourceBriefInfo, PopUpMemberTemplate popUpMemberTemplate) {
        LogUtil.a("EquityRenewalDialog", "payApi is null.");
        this.f2901a = resourceBriefInfo;
        this.h = i;
        this.i = popUpMemberTemplate;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.commonui_dialog_equity_renewal, (ViewGroup) null);
        setContentView(inflate);
        this.o = (ImageView) inflate.findViewById(R.id.renewal_top_img);
        this.l = (HealthTextView) inflate.findViewById(R.id.renewal_title);
        this.b = (HealthTextView) inflate.findViewById(R.id.renewal_description);
        this.j = (HealthButton) inflate.findViewById(R.id.renewal_submit);
        this.c = (ImageView) inflate.findViewById(R.id.renewal_close_dialog);
        nrf.cJB_(popUpMemberTemplate.getPicture(), this.o);
        this.j.setText(popUpMemberTemplate.getButtonName());
        this.j.setOnClickListener(this);
        this.c.setOnClickListener(this);
        e();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MarketRouterApi marketRouterApi;
        if (nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        LogUtil.a("EquityRenewalDialog", "viewId", Integer.valueOf(id));
        if (id == R.id.renewal_submit && (marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)) != null) {
            LogUtil.a("EquityRenewalDialog", "mTemplate.getLinkValue() :", this.i.getLinkValue());
            marketRouterApi.router(this.i.getLinkValue());
            MarketingBiUtils.b(this.h, this.f2901a, this.g);
        }
        dismiss();
        MarketingBiUtils.c(this.h, this.f2901a, this.g);
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        if (!a() || isShowing()) {
            return;
        }
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
        this.g = System.currentTimeMillis();
        MarketingBiUtils.d(this.h, this.f2901a);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    private boolean a() {
        Context context = this.e;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isDestroyed() && !activity.isFinishing()) {
                return true;
            }
        }
        return false;
    }

    private void e() {
        PayApi payApi = null;
        for (int i = 0; payApi == null && i < 2; i++) {
            payApi = (PayApi) Services.a("TradeService", PayApi.class);
        }
        if (payApi == null) {
            LogUtil.a("EquityRenewalDialog", "payApi is null.");
        } else {
            payApi.queryBenefitInfo(9, "", new IBaseResponseCallback() { // from class: elc
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    EquityRenewalDialog.this.e(i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (i == 0 && (obj instanceof RepeatResourceBenefitInfo)) {
            this.f = (RepeatResourceBenefitInfo) obj;
            LogUtil.a("EquityRenewalDialog", "hasEquity");
            f();
        }
    }

    private void f() {
        LogUtil.a("EquityRenewalDialog", "MarketingResourceSetting-Drawable:3");
        this.d.post(new Runnable() { // from class: elh
            @Override // java.lang.Runnable
            public final void run() {
                EquityRenewalDialog.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void d() {
        LogUtil.a("EquityRenewalDialog", " setPopUpVipTemplate showDialog .");
        RepeatResourceBenefitInfo repeatResourceBenefitInfo = this.f;
        boolean z = this.f2901a.getContentType() == 68;
        long longValue = repeatResourceBenefitInfo.getExpireTime().longValue();
        long nowTime = repeatResourceBenefitInfo.getNowTime();
        if (!z || longValue - nowTime > 3000) {
            long b = eil.b(repeatResourceBenefitInfo.getNowTime(), repeatResourceBenefitInfo.getExpireTime().longValue());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.i.getTheme());
            if (!TextUtils.isEmpty(this.i.getThemePost())) {
                stringBuffer.append(b);
                stringBuffer.append(this.i.getThemePost());
            }
            this.l.setText(stringBuffer.toString());
            stringBuffer.setLength(0);
            if (!TextUtils.isEmpty(this.i.getDescription()) || !TextUtils.isEmpty(this.i.getDescriptionPost())) {
                String description = this.i.getDescription();
                if (description == null) {
                    description = "";
                }
                stringBuffer.append(description);
                stringBuffer.append(DateFormatUtil.b(repeatResourceBenefitInfo.getExpireTime().longValue(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMMDD_POINT));
                String descriptionPost = this.i.getDescriptionPost();
                stringBuffer.append(descriptionPost != null ? descriptionPost : "");
                this.b.setText(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
            if (!a() || isShowing()) {
                return;
            }
            LogUtil.a("EquityRenewalDialog", " setPopUpVipTemplate show.");
            show();
        }
    }

    private void c() {
        this.l = null;
        this.b = null;
        this.j = null;
        this.o = null;
        this.c = null;
        this.f2901a = null;
        this.i = null;
    }
}
