package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sah extends rzv {
    public sah(Context context, PersonalCenterUiApi personalCenterUiApi) {
        super(context, personalCenterUiApi);
    }

    @Override // defpackage.rzv
    protected void c() {
        if (this.e != null) {
            return;
        }
        this.e = new rzs();
        this.e.b(5);
        this.e.a(R.mipmap._2131821258_res_0x7f1102ca);
        this.e.d(R$string.IDS_hwh_home_health_group);
        this.e.c(this.d.getString(R$string.IDS_hwh_group_create));
        this.e.b(false);
        this.e.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: saj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                sah.this.dUG_(view);
            }
        }, (BaseActivity) this.d, true, AnalyticsValue.Group_1070010.value()));
    }

    /* synthetic */ void dUG_(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("MyGroupCardData", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (CommonUtil.bu()) {
            AppRouter.b("/PluginChat/1.0/GroupStoreDemo/HealthGroupListActivity").c(this.d);
        } else {
            b();
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", "1");
            ixx.d().d(this.d, AnalyticsValue.Group_1070010.value(), hashMap, 0);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        bzs.e().loadH5ProApp(this.d, "com.huawei.health.h5.groups", null);
    }

    @Override // defpackage.rzv
    protected void e() {
        super.e();
        if (this.f16979a) {
            this.e.c(this.d.getString(R$string.IDS_hwh_group_create));
            if (this.h != null) {
                this.h.d(this.e);
                return;
            }
            return;
        }
        jdx.b(new Runnable() { // from class: sal
            @Override // java.lang.Runnable
            public final void run() {
                sah.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        FamilyHealthZoneApi familyHealthZoneApi = (FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class);
        if (familyHealthZoneApi == null) {
            LogUtil.h("MyGroupCardData", "healthZoneApi is null");
        } else {
            familyHealthZoneApi.getUserGrpList(new IBaseResponseCallback() { // from class: sak
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    sah.this.d(i, obj);
                }
            });
        }
    }

    /* synthetic */ void d(int i, Object obj) {
        if (i == 0) {
            LogUtil.a("MyGroupCardData", "getGroupDatas  success ");
            if (obj instanceof JSONObject) {
                b((sap) HiJsonUtil.e(((JSONObject) obj).toString(), sap.class));
                return;
            }
            return;
        }
        LogUtil.h("MyGroupCardData", "getGroupDatas failed, errorCode:", Integer.valueOf(i));
    }

    private void b(sap sapVar) {
        if (sapVar.getResultCode().intValue() == 0) {
            final int e = sapVar.e();
            LogUtil.a("MyGroupCardData", "getGroupDatas  count = ", Integer.valueOf(e));
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: sai
                @Override // java.lang.Runnable
                public final void run() {
                    sah.this.b(e);
                }
            });
        }
    }

    /* synthetic */ void b(int i) {
        String string = this.d.getString(R$string.IDS_hwh_group_create);
        if (i > 0) {
            string = nsf.a(R.plurals._2130903362_res_0x7f030142, i, UnitUtil.e(i, 1, 0));
        }
        this.e.c(string);
        if (this.h != null) {
            this.h.d(this.e);
        }
    }

    @Override // defpackage.rzv, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "MyGroupCardData";
    }
}
