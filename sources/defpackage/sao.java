package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.Date;
import java.util.List;

/* loaded from: classes7.dex */
public class sao extends rzv {
    private MessageCenterApi g;

    public sao(Context context, PersonalCenterUiApi personalCenterUiApi) {
        super(context, personalCenterUiApi);
        if (this.g == null) {
            this.g = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        }
    }

    @Override // defpackage.rzv
    protected void c() {
        if (this.e != null) {
            return;
        }
        this.e = new rzs();
        this.e.b(5);
        this.e.a(R.mipmap._2131821259_res_0x7f1102cb);
        if (Utils.o() || CommonUtil.bu()) {
            this.e.d(R$string.IDS_plugin_achievement_weekly_monthly_report);
        } else {
            this.e.d(R$string.IDS_plugin_achievement_weekly_monthly_year_report);
        }
        this.e.c(this.d.getString(R$string.IDS_hwh_no_report));
        this.e.b(false);
        this.e.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: san
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                sao.this.dUH_(view);
            }
        }, (BaseActivity) this.d, true, AnalyticsValue.HEALTH_MINE_ACHIEVE_REPORT_2040062.value()));
    }

    /* synthetic */ void dUH_(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("MyReportCardData", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.e.l()) {
            LogUtil.a("MyReportCardData", "ReportRedPoint GONE.");
            d(false, 8);
            SharedPreferenceManager.e(String.valueOf(20003), "new_report", false);
        }
        sas.a(this.d, 5);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // defpackage.rzv
    protected void e() {
        super.e();
        if (this.f16979a) {
            this.e.c(this.d.getString(R$string.IDS_hwh_no_report));
            if (this.h != null) {
                this.h.d(this.e);
            }
            d(false, 8);
            return;
        }
        jdx.b(new Runnable() { // from class: sam
            @Override // java.lang.Runnable
            public final void run() {
                sao.this.b();
            }
        });
    }

    /* synthetic */ void b() {
        e(this.g.getMessages(11));
    }

    private void e(List<MessageObject> list) {
        String d = d();
        if (koq.b(list)) {
            LogUtil.h("MyReportCardData", "msgDbObject is null");
            c(d);
            return;
        }
        LogUtil.a("MyReportCardData", "msgDbObject size== ", Integer.valueOf(list.size()));
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            MessageObject messageObject = list.get(size);
            if (messageObject == null || !"16".equals(messageObject.getModule())) {
                size--;
            } else if ("monthReportMessage".equals(messageObject.getType())) {
                d = d();
            } else {
                d = this.d.getString(R$string.IDS_hwh_new_week_report);
            }
        }
        c(d);
    }

    public void c(final String str) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: saq
            @Override // java.lang.Runnable
            public final void run() {
                sao.this.e(str);
            }
        });
    }

    /* synthetic */ void e(String str) {
        this.e.c(str);
        if (this.h != null) {
            this.h.d(this.e);
        }
        d(SharedPreferenceManager.a(String.valueOf(20003), "new_report", false), 8);
    }

    private String d() {
        String a2 = UnitUtil.a(new Date(mkx.d(-1, System.currentTimeMillis(), 1)), 56);
        if (LanguageUtil.br(this.d) || LanguageUtil.u(this.d)) {
            a2 = a2.toLowerCase();
        }
        return this.d.getString(R$string.IDS_hwh_new_month_report, a2);
    }

    @Override // defpackage.rzv, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "MyReportCardData";
    }
}
