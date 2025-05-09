package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class rzt extends rzv {
    private UserProfileMgrApi i;

    public rzt(Context context, PersonalCenterUiApi personalCenterUiApi) {
        super(context, personalCenterUiApi);
    }

    @Override // defpackage.rzv
    protected void c() {
        if (this.e != null) {
            return;
        }
        this.e = new rzs();
        this.e.b(5);
        this.e.a(R.mipmap._2131820978_res_0x7f1101b2);
        this.e.d(R$string.IDS_hwh_me_active_target);
        this.e.c(this.d.getString(R$string.IDS_hwh_create_annual_flag));
        if (this.i == null) {
            this.i = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        }
        this.e.b(this.i.isTargetRedNeedShow());
        this.e.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: rzy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                rzt.this.dUD_(view);
            }
        }, (BaseActivity) this.d, true, ""));
    }

    /* synthetic */ void dUD_(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("AnnualFlagCardData", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.e != null && this.e.l()) {
            this.i.hideTargetRed();
            d(false, 2304);
        }
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).gotoH5ActiveTarget(this.d);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // defpackage.rzv
    protected void e() {
        super.e();
        if (this.f16979a) {
            this.e.c(this.d.getString(R$string.IDS_hwh_create_annual_flag));
            if (this.h != null) {
                this.h.d(this.e);
            }
            d(false, 2304);
            return;
        }
        b();
        jdx.b(new Runnable() { // from class: rzx
            @Override // java.lang.Runnable
            public final void run() {
                rzt.this.a();
            }
        });
    }

    /* synthetic */ void a() {
        this.i.getActiveTargetStatus(new IBaseResponseCallback() { // from class: sab
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                rzt.this.b(i, obj);
            }
        });
    }

    /* synthetic */ void b(int i, Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            LogUtil.a("AnnualFlagCardData", "flag count = ", Integer.valueOf(intValue));
            d(intValue);
        }
    }

    private void d(final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: saa
            @Override // java.lang.Runnable
            public final void run() {
                rzt.this.a(i);
            }
        });
    }

    /* synthetic */ void a(int i) {
        String string;
        if (i > 0) {
            string = nsf.a(R.plurals._2130903363_res_0x7f030143, i, UnitUtil.e(i, 1, 0));
        } else if (i == 0) {
            string = this.d.getString(R$string.IDS_hwh_annual_flag_finished);
        } else {
            string = this.d.getString(R$string.IDS_hwh_create_annual_flag);
        }
        this.e.c(string);
        if (this.h != null) {
            this.h.d(this.e);
        }
    }

    private void b() {
        if (Utils.o() || LoginInit.getInstance(this.d).isKidAccount()) {
            return;
        }
        d(this.i.isTargetRedNeedShow(), 2304);
    }

    @Override // defpackage.rzv, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "AnnualFlagCardData";
    }
}
