package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.request.ActivityIdInfo;
import com.huawei.health.marketing.request.InformationInfo;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes8.dex */
public class gdx {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f12774a;

    @SerializedName("dynamicDataId")
    @Expose
    private String b;
    private FitWorkout c;
    private ActivityIdInfo d;

    @SerializedName("description")
    @Expose
    private String e;

    @SerializedName("itemCategory")
    @Expose
    private String f;

    @SerializedName("descriptionVisibility")
    @Expose
    private boolean g;

    @SerializedName("themeVisibility")
    @Expose
    private boolean h;

    @SerializedName("linkValue")
    @Expose
    private String i;
    private InformationInfo j;

    @SerializedName("priority")
    @Expose
    private int k;
    private int l;

    @SerializedName("picture")
    @Expose
    private String m;
    private View.OnClickListener n;

    @SerializedName("theme")
    @Expose
    private String o;

    public String g() {
        return this.m;
    }

    public String c() {
        return this.b;
    }

    public String b() {
        return this.f;
    }

    public void d(FitWorkout fitWorkout) {
        this.c = fitWorkout;
    }

    public void d(InformationInfo informationInfo) {
        this.j = informationInfo;
    }

    public void a(ActivityIdInfo activityIdInfo) {
        this.d = activityIdInfo;
    }

    public String a() {
        if (!this.g) {
            return null;
        }
        if (this.c != null) {
            float b = fis.d().b();
            Context e = BaseApplication.e();
            return ggm.d(this.c.acquireDifficulty()) + "  " + gic.d(e, R.string._2130837534_res_0x7f02001e, gic.e(this.c.acquireDuration())) + "  " + gic.d(e, R.string._2130837535_res_0x7f02001f, gic.a(gic.d(this.c.acquireCalorie() * b)));
        }
        return this.e;
    }

    public String i() {
        if (!this.h) {
            return null;
        }
        FitWorkout fitWorkout = this.c;
        if (fitWorkout != null) {
            return fitWorkout.acquireName();
        }
        return this.o;
    }

    public eem d() {
        Drawable drawable;
        String str = null;
        if (this.d == null) {
            return null;
        }
        Context e = BaseApplication.e();
        String b = SharedPreferenceManager.b(e, Integer.toString(PrebakedEffectId.RT_ICE), "marketingServerCurrentTime" + this.l);
        OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            LogUtil.h("AchieveBannerBean", "operationInteractorsApi = null");
            return null;
        }
        int activityStatus = operationInteractorsApi.getActivityStatus(b, this.d.getBeginDate(), this.d.getEndDate());
        int i = 0;
        if (activityStatus == 0) {
            str = e.getResources().getString(R.string._2130841922_res_0x7f021142);
            drawable = ContextCompat.getDrawable(e, R.drawable._2131430820_res_0x7f0b0da4);
        } else if (activityStatus == 1) {
            str = e.getResources().getString(R.string._2130842666_res_0x7f02142a);
            drawable = ContextCompat.getDrawable(e, R.drawable._2131430820_res_0x7f0b0da4);
        } else if (activityStatus == -1) {
            str = e.getResources().getString(R.string._2130841924_res_0x7f021144);
            drawable = ContextCompat.getDrawable(e, R.drawable._2131430821_res_0x7f0b0da5);
        } else {
            LogUtil.a("AchieveBannerBean", "setActivityStatusView activity status is empty.");
            i = 8;
            drawable = null;
        }
        return new eem(str, drawable, i);
    }

    public String f() {
        int numberOfPeople;
        InformationInfo informationInfo = this.j;
        boolean z = informationInfo != null;
        ActivityIdInfo activityIdInfo = this.d;
        boolean z2 = activityIdInfo != null;
        if (z) {
            numberOfPeople = informationInfo.getReadCount();
        } else {
            numberOfPeople = z2 ? activityIdInfo.getNumberOfPeople() : -1;
        }
        if (numberOfPeople < 0) {
            return null;
        }
        Context e = BaseApplication.e();
        if (z) {
            return com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903294_res_0x7f0300fe, numberOfPeople, Integer.valueOf(numberOfPeople)).replace(String.valueOf(numberOfPeople), UnitUtil.e(numberOfPeople, 1, 0));
        }
        if (z2) {
            return e.getResources().getString(R.string._2130841921_res_0x7f021141, UnitUtil.e(numberOfPeople, 1, 0));
        }
        LogUtil.b("AchieveBannerBean", "getPeopleNumberText failed, cause unknown dynamicId = ", this.b);
        return null;
    }

    public void b(int i) {
        this.l = i;
    }

    public View.OnClickListener aLH_() {
        return this.n;
    }

    public void a(final Context context, final String str) {
        if (TextUtils.equals(this.f, SingleDailyMomentContent.COURSE_TYPE)) {
            this.n = new View.OnClickListener() { // from class: gdx.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (gdx.this.c == null) {
                        LogUtil.b("AchieveBannerBean", "fitworkout is null");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    HashMap hashMap = new HashMap(4);
                    hashMap.put("entrance", str);
                    hashMap.put("position", 0);
                    hashMap.put("type", Integer.valueOf(gdx.this.c.getCategory()));
                    hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, TextUtils.isEmpty(gdx.this.c.acquireId()) ? "" : gdx.this.c.acquireId());
                    gge.e("1130015", hashMap);
                    mmp mmpVar = new mmp(gdx.this.c.acquireId());
                    mmpVar.d(gdx.this.c.acquireName());
                    mod.b(context, gdx.this.c, mmpVar);
                    ViewClickInstrumentation.clickOnView(view);
                }
            };
            return;
        }
        Activity activity = com.huawei.hwcommonmodel.application.BaseApplication.getActivity();
        BaseActivity baseActivity = activity instanceof BaseActivity ? (BaseActivity) activity : null;
        if (baseActivity == null) {
            LogUtil.b("AchieveBannerBean", "prepareOnClickListener failed cause baseActivity is null!");
        } else {
            d(baseActivity);
        }
    }

    public Drawable aLG_() {
        return this.f12774a;
    }

    public void h() {
        this.f12774a = nrf.cIb_(BaseApplication.e(), this.m);
    }

    public String toString() {
        return "AchieveBannerBean{mIsVisible=" + this.g + ", mDynamicDataId='" + this.b + "', mItemCategory='" + this.f + "', mPriority=" + this.k + ", mFitWorkout=" + this.c + '}';
    }

    private void d(BaseActivity baseActivity) {
        OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
        if (operationUtilsApi == null) {
            LogUtil.b("AchieveBannerBean", "prepareOnClickListener failed cause operationUtilsApi is null!");
        } else {
            this.n = nkx.cwZ_(new View.OnClickListener() { // from class: gdx.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.a(500)) {
                        LogUtil.a("AchieveBannerBean", "onClick isFastClick");
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                        if (marketRouterApi != null) {
                            marketRouterApi.router(eil.c(gdx.this.i, (ResourceBriefInfo) null, gdx.this.l, 1));
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            }, baseActivity, operationUtilsApi.isNotSupportBrowseUrl(this.i), "");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gdx gdxVar = (gdx) obj;
        return this.g == gdxVar.g && this.h == gdxVar.h && this.k == gdxVar.k && Objects.equals(this.e, gdxVar.e) && Objects.equals(this.i, gdxVar.i) && Objects.equals(this.o, gdxVar.o) && Objects.equals(this.b, gdxVar.b) && Objects.equals(this.f, gdxVar.f) && Objects.equals(this.m, gdxVar.m);
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.g), this.e, Boolean.valueOf(this.h), this.i, this.o, this.b, this.f, Integer.valueOf(this.k), this.m);
    }
}
