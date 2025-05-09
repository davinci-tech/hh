package com.huawei.health.suggestion.ui.fitness.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.suggestion.config.MeasurementModeTab;
import com.huawei.health.suggestion.ui.fitness.activity.ActionTrainModeActivity;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionModeViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.AIActionBundle;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;
import defpackage.eme;
import defpackage.ffy;
import defpackage.fnj;
import defpackage.ggr;
import defpackage.koq;
import defpackage.kxe;
import defpackage.moj;
import defpackage.nkx;
import defpackage.nqt;
import defpackage.nsn;
import defpackage.smy;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ActionTrainModeActivity extends Activity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f3071a;
    private int b;
    private HealthViewPager c;
    private nqt d;
    private String e;
    private AtomicAction f;
    private HealthBottomSheet g;
    private String[] h;
    private Map<String, Object> i;
    private int j;
    private List<FitnessActionModeViewHolder> k = new ArrayList(3);
    private HealthSubTabWidget m;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BaseActivity.setDisplaySideMode(this);
        setContentView(R.layout.activity_action_train_mode);
        d();
        c();
    }

    public void d() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_ActionTrainModeActivity", "initData() getIntent is null");
            return;
        }
        try {
            Bundle bundleExtra = intent.getBundleExtra("intentData");
            if (bundleExtra == null) {
                LogUtil.b("Suggestion_ActionTrainModeActivity", "bundle null.");
                return;
            }
            this.f = (AtomicAction) moj.e(bundleExtra.getString("actionInfo"), AtomicAction.class);
            this.i = (Map) bundleExtra.getSerializable("biIntent");
            Object[] objArr = new Object[2];
            objArr[0] = "initData atomicAction";
            objArr[1] = Boolean.valueOf(this.f == null);
            LogUtil.a("Suggestion_ActionTrainModeActivity", objArr);
            if (this.f == null) {
                LogUtil.h("Suggestion_ActionTrainModeActivity", "mAtomicAction == null");
                finish();
                return;
            }
            Map<String, Object> map = this.i;
            if (map != null) {
                LogUtil.a("Suggestion_ActionTrainModeActivity", "mBiMap:", map.entrySet());
            }
            String string = bundleExtra.getString("describe");
            this.e = string;
            this.b = fnj.a(string);
            this.f3071a = kxe.b(this.f.getExtendPropertyInt("aiMeasurement"));
        } catch (BadParcelableException e) {
            LogUtil.b("Suggestion_ActionTrainModeActivity", "initData exception", LogAnonymous.b((Throwable) e));
        }
    }

    public void c() {
        this.m = (HealthSubTabWidget) findViewById(R.id.sug_action_mode_tab);
        this.c = (HealthViewPager) findViewById(R.id.action_mode_pager);
        ((HealthButton) findViewById(R.id.train_dialog_confirm)).setOnClickListener(nkx.cwY_(this, this, true, ""));
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() { // from class: fju
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActionTrainModeActivity.this.ayN_(view);
            }
        });
        this.h = MeasurementModeTab.getModeTabName(this.f3071a);
        j();
        this.d = new nqt(this.c, this.m);
        this.c.setOffscreenPageLimit(this.h.length);
        HealthBottomSheet healthBottomSheet = (HealthBottomSheet) findViewById(R.id.sliding_layout);
        this.g = healthBottomSheet;
        healthBottomSheet.setIndicateSafeInsetsEnabled(true);
        this.g.setForceShowIndicateEnabled(false);
        this.g.setSheetHeight(0);
        int a2 = a();
        this.g.setHeightGap(a2);
        this.g.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.ActionTrainModeActivity.3
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                if (sheetState2 == HwBottomSheet.SheetState.COLLAPSED || sheetState2 == HwBottomSheet.SheetState.HIDDEN) {
                    ActionTrainModeActivity.this.finish();
                }
            }
        });
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.drag_View);
        viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), a2);
        if (this.f == null) {
            return;
        }
        f();
    }

    public /* synthetic */ void ayN_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private int a() {
        return (int) (((nsn.j() - nsn.c(this, b())) - nsn.r(this)) - (nsn.ab(this) ? nsn.q(this) : 0));
    }

    private int b() {
        LogUtil.a("Suggestion_ActionTrainModeActivity", "getDragViewHeight scale:", Float.valueOf(nsn.c()));
        return nsn.p() ? 300 : 291;
    }

    private void j() {
        this.m.setOnSubTabChangeListener(new HwSubTabWidget.OnSubTabChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.ActionTrainModeActivity.2
            @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
            public void onSubTabReselected(smy smyVar) {
            }

            @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
            public void onSubTabUnselected(smy smyVar) {
            }

            @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
            public void onSubTabSelected(smy smyVar) {
                ActionTrainModeActivity.this.j = smyVar.e();
                LogUtil.a("Suggestion_ActionTrainModeActivity", "onSubTabSelected mCurrentPosition:", Integer.valueOf(ActionTrainModeActivity.this.j));
            }
        });
    }

    private void f() {
        this.d.b();
        this.k.clear();
        FitnessActionModeViewHolder.ActionMode c2 = FitnessActionModeViewHolder.c(this.f.getId());
        int targetType = c2 != null ? c2.getTargetType() : Integer.MAX_VALUE;
        boolean isMatch = MeasurementModeTab.isMatch(targetType, this.f3071a);
        ReleaseLogUtil.e("Suggestion_ActionTrainModeActivity", "updateViewPager lastSelectMode:", Integer.valueOf(targetType), "mAIMeasurement:", Integer.valueOf(this.f3071a), "isMatch:", Boolean.valueOf(isMatch));
        int[] tabTypeArrayByMeasurement = MeasurementModeTab.getTabTypeArrayByMeasurement(this.f3071a);
        for (int i = 0; i < tabTypeArrayByMeasurement.length; i++) {
            e(targetType, i, MeasurementModeTab.getTabTypeArrayByMeasurement(this.f3071a)[i], isMatch);
        }
    }

    private void e(int i, int i2, int i3, boolean z) {
        boolean z2 = (i == i3 && z) || i2 == 0;
        String[] strArr = this.h;
        if (strArr.length <= i2) {
            LogUtil.h("Suggestion_ActionTrainModeActivity", "addSubTab mTabContent.length <= index", Integer.valueOf(i2), "mTabContent.length:", Integer.valueOf(this.h.length));
            return;
        }
        smy c2 = this.m.c(strArr[i2]);
        FitnessActionModeViewHolder fitnessActionModeViewHolder = new FitnessActionModeViewHolder(this, i3, this.f);
        this.d.cGt_(c2, fitnessActionModeViewHolder.aFu_(), z2);
        this.k.add(fitnessActionModeViewHolder);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.a("Suggestion_ActionTrainModeActivity", "onClick isFastClick true");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.train_dialog_confirm) {
            if (this.f == null) {
                LogUtil.h("Suggestion_ActionTrainModeActivity", "onClick mAtomicAction == null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            PermissionUtil.b(this, PermissionUtil.PermissionType.CAMERA_IMAGE, new c());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ggr.e(2, this.f, this.i);
        AIActionBundle e = e();
        Bundle bundle = new Bundle();
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        sportLaunchParams.addExtra(AIActionBundle.AI_ACTION_BUNDLE, e);
        bundle.putParcelable("bundle_key_sport_launch_paras", sportLaunchParams);
        eme.b().startRunCourseSport(this, 700001, AIActionBundle.coverActionToTrackTarget(e.getTargetType()), e.getTargetValue(), bundle);
        finish();
    }

    private AIActionBundle e() {
        AIActionBundle aIActionBundle = new AIActionBundle();
        aIActionBundle.setActionId(this.f.getId());
        aIActionBundle.setActionName(this.f.getName());
        aIActionBundle.setAiMeasurement(this.f3071a);
        aIActionBundle.setDuration(FitnessActionModeViewHolder.d(this.f));
        ReleaseLogUtil.e("Suggestion_ActionTrainModeActivity", "onClick viewHolderList size:", Integer.valueOf(this.k.size()), "mCurrentPosition:", Integer.valueOf(this.j));
        if (koq.d(this.k, this.j) && this.k.get(this.j) != null) {
            FitnessActionModeViewHolder fitnessActionModeViewHolder = this.k.get(this.j);
            int c2 = fitnessActionModeViewHolder.c();
            int a2 = fitnessActionModeViewHolder.a();
            aIActionBundle.setActionName(c(this.f.getName(), fitnessActionModeViewHolder.d()));
            FitnessActionModeViewHolder.a(this.f.getId(), c2, a2);
            aIActionBundle.setTargetType(c2);
            aIActionBundle.setTargetValue(a2);
        }
        aIActionBundle.setCalorie(this.f.getExtendPropertyFloat("calorie"));
        aIActionBundle.setBodyName(ffy.e(this.f.getTrainingPoints()));
        aIActionBundle.setDescribe(this.e);
        aIActionBundle.setTowards(this.b);
        Map<String, Object> map = this.i;
        if (map != null) {
            aIActionBundle.setPullFrom((String) map.get(WebViewHelp.BI_KEY_PULL_FROM));
            aIActionBundle.setResourceId((String) this.i.get("resourceId"));
            aIActionBundle.setResourceName((String) this.i.get("resourceName"));
            aIActionBundle.setPullOrder((String) this.i.get("pullOrder"));
        }
        return aIActionBundle;
    }

    private String c(String str, String str2) {
        String[] strArr = this.h;
        if (strArr == null) {
            return str;
        }
        int length = strArr.length;
        int i = this.j;
        return length > i ? ffy.d(this, R.string._2130843638_res_0x7f0217f6, str, ffy.d(this, R.string._2130842426_res_0x7f02133a, strArr[i], str2)) : str;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        nkx.e(toString());
        super.onDestroy();
    }

    static class c extends CustomPermissionAction {
        private final WeakReference<ActionTrainModeActivity> b;

        private c(ActionTrainModeActivity actionTrainModeActivity) {
            super(actionTrainModeActivity);
            this.b = new WeakReference<>(actionTrainModeActivity);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            ActionTrainModeActivity actionTrainModeActivity = this.b.get();
            if (actionTrainModeActivity != null) {
                actionTrainModeActivity.i();
            } else {
                ReleaseLogUtil.d("Suggestion_ActionTrainModeActivity", "activity is null in WeakReferenceCustomPermissionAction");
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
