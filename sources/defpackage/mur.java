package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class mur {

    /* renamed from: a, reason: collision with root package name */
    private fdu f15184a;
    private Context b;
    private mvg c;
    private mwc e;

    private mur(Context context) {
        this.b = context;
        e(context);
    }

    public static mur b(Context context) {
        return new mur(context);
    }

    public void d() {
        a();
    }

    public void a() {
        this.b = null;
        this.c = null;
        mwc mwcVar = this.e;
        if (mwcVar != null) {
            mwcVar.b();
            this.e = null;
        }
    }

    private void e(Context context) {
        LogUtil.a("Share_InteractorManager", "init sdk");
        this.b = context;
        this.f15184a = mto.d();
        if (!Utils.o()) {
            this.c = new mvg(context);
        }
        this.e = new mwc(this.b, this.f15184a);
    }

    private PermissionUtil.PermissionType a(int i, fdu fduVar) {
        int u = fduVar.u();
        if (i == 4) {
            return PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES;
        }
        if (i == 5 && ((u == 4 || u == 1) && !PermissionUtil.c())) {
            return PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES;
        }
        if (i == 7 || i == 8) {
            return PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE;
        }
        if (u == 5) {
            return PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES;
        }
        return PermissionUtil.PermissionType.NONE;
    }

    public void a(Context context, final int i, fdu fduVar) {
        if (fduVar == null) {
            LogUtil.h("Share_InteractorManager", "shareContent is null");
            return;
        }
        this.f15184a = fduVar;
        PermissionUtil.PermissionType a2 = a(i, fduVar);
        if (a2 == PermissionUtil.PermissionType.NONE) {
            a(i);
        } else {
            PermissionUtil.b(context, a2, new CustomPermissionAction(context) { // from class: mur.4
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    mur.this.a(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        try {
            d(i);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("Share_InteractorManager", "share fail: out of memory");
            nrh.b(BaseApplication.getContext(), R$string.IDS_plugin_social_error_code_1);
        }
    }

    private void d(int i) {
        switch (i) {
            case 1:
                LogUtil.a("Share_InteractorManager", "share_wechat_moments");
                c(2);
                e(2);
                g();
                break;
            case 2:
                LogUtil.a("Share_InteractorManager", "share_wechat_friends");
                c(1);
                e(1);
                f();
                break;
            case 3:
                LogUtil.a("Share_InteractorManager", "share_weibo");
                c(3);
                e(3);
                h();
                break;
            case 4:
                LogUtil.a("Share_InteractorManager", "share_save_to_local");
                c(4);
                e(4);
                new mvc().e(this.f15184a);
                break;
            case 5:
                LogUtil.a("Share_InteractorManager", "share_more_layout");
                c(5);
                e(5);
                m();
                break;
            case 6:
                LogUtil.a("Share_InteractorManager", "share_familygroup");
                c(6);
                e(6);
                i();
                break;
            case 7:
                LogUtil.a("Share_InteractorManager", "share_save_pdf_layout");
                c(7);
                e(7);
                c();
                break;
            case 8:
                LogUtil.a("Share_InteractorManager", "share_print_layout");
                c(8);
                e(8);
                e();
                break;
            default:
                LogUtil.a("Share_InteractorManager", "unknow type:", Integer.valueOf(i));
                break;
        }
    }

    private void e(int i) {
        Context context = this.b;
        if (context != null) {
            b(context, i);
        }
    }

    private void b(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_SPEED_UP), "SHARE_POP_UP_ACTIVITY_SHARE_TYPE", String.valueOf(i), new StorageParams());
    }

    private void c(int i) {
        LogUtil.c("Share_InteractorManager", "setBiEvent ENTER key=", Integer.valueOf(i));
        HashMap hashMap = new HashMap(2);
        hashMap.put(Constants.BI_MODULE_ID, this.f15184a.m());
        hashMap.put("type", Integer.valueOf(i));
        Map<String, Object> l = this.f15184a.l();
        if (l != null) {
            hashMap.putAll(l);
        }
        iyb iybVar = new iyb();
        iybVar.d(hashMap);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("pageId", "Share_0001");
        iybVar.e(hashMap2);
        ixx.d().a(BaseApplication.getContext(), AnalyticsValue.SHARE_1140001.value(), iybVar, 0);
        j();
    }

    private void j() {
        if (this.f15184a != null) {
            b();
            String y = this.f15184a.y();
            if (TextUtils.isEmpty(y)) {
                return;
            }
            HashMap hashMap = new HashMap(2);
            hashMap.put("url", y);
            bzw.e().finishKakaTask(this.b, 40003, hashMap);
        }
    }

    private void b() {
        if ("14".equals(this.f15184a.m())) {
            bzw.e().finishKakaTask(this.b, 40004, new HashMap(2));
        }
    }

    private void g() {
        if (this.c == null) {
            LogUtil.b("Share_InteractorManager", "shareWeChatMoments() mWeChatInteractors == null");
            if (Utils.o()) {
                return;
            } else {
                this.c = new mvg(this.b);
            }
        }
        if (!jdm.b(this.b, "com.tencent.mm")) {
            nrh.b(BaseApplication.getContext(), R.string._2130839536_res_0x7f0207f0);
        } else {
            Context context = this.b;
            nsn.cLO_("com.tencent.mm", context, context.getString(R.string._2130844962_res_0x7f021d22), new View.OnClickListener() { // from class: muv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    mur.this.cpL_(view);
                }
            }, null);
        }
    }

    /* synthetic */ void cpL_(View view) {
        int a2 = this.c.a(this.f15184a, 1);
        if (a2 == 1) {
            LogUtil.b("Share_InteractorManager", "shareWeChatMoments() ERR_NOT_INSTALLED");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b(this.f15184a.aa());
            LogUtil.c("Share_InteractorManager", "shareWeChatMoments shareResult = ", Integer.valueOf(a2));
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void f() {
        if (this.c == null) {
            LogUtil.b("Share_InteractorManager", "ShareWeChat() mWeChatInteractors == null");
            if (Utils.o()) {
                return;
            } else {
                this.c = new mvg(this.b);
            }
        }
        if (!jdm.b(this.b, "com.tencent.mm")) {
            nrh.b(BaseApplication.getContext(), R.string._2130839536_res_0x7f0207f0);
        } else {
            Context context = this.b;
            nsn.cLO_("com.tencent.mm", context, context.getString(R.string._2130844962_res_0x7f021d22), new View.OnClickListener() { // from class: muz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    mur.this.cpK_(view);
                }
            }, null);
        }
    }

    /* synthetic */ void cpK_(View view) {
        int a2 = this.c.a(this.f15184a, 0);
        if (a2 == 1) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        b(this.f15184a.aa());
        LogUtil.c("Share_InteractorManager", "shareWeChat shareResult = ", Integer.valueOf(a2));
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        if (!jdm.b(this.b, "com.sina.weibo")) {
            nrh.b(BaseApplication.getContext(), R.string._2130839537_res_0x7f0207f1);
        } else {
            Context context = this.b;
            nsn.cLO_("com.sina.weibo", context, context.getString(R.string._2130843624_res_0x7f0217e8), new View.OnClickListener() { // from class: mux
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    mur.this.cpM_(view);
                }
            }, null);
        }
    }

    /* synthetic */ void cpM_(View view) {
        new mvj(this.b, this.f15184a).a();
        b(this.f15184a.aa());
        LogUtil.c("Share_InteractorManager", "shareWeibo");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i() {
        new muq(this.b, this.f15184a).c();
        b(this.f15184a.aa());
        LogUtil.c("Share_InteractorManager", "shareFamilyGroup", Integer.valueOf(this.f15184a.aa()));
    }

    private void b(int i) {
        LogUtil.a("Share_InteractorManager", "sendShareBroadCast shareSource=", Integer.valueOf(i));
        HashMap hashMap = new HashMap(10);
        hashMap.put("share_key", Integer.valueOf(i));
        bzw.e().finishKakaTask(this.b, SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER, hashMap);
    }

    private void c() {
        muw muwVar = new muw(this.f15184a);
        Context context = this.b;
        if (context instanceof Activity) {
            muwVar.cpQ_((Activity) context);
        }
        LogUtil.c("Share_InteractorManager", "savePdf ", Integer.valueOf(this.f15184a.aa()));
    }

    private void e() {
        new muw(this.f15184a).e(this.b);
        LogUtil.c("Share_InteractorManager", "print ", Integer.valueOf(this.f15184a.aa()));
    }

    private void m() {
        mwc mwcVar = this.e;
        if (mwcVar != null) {
            mwcVar.b();
        }
        mwc mwcVar2 = new mwc(this.b, this.f15184a);
        this.e = mwcVar2;
        mwcVar2.e();
        b(this.f15184a.aa());
    }
}
