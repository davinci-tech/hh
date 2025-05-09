package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.device.wifi.interfaces.CommBaseCallback;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.ClaimDataFatRateFluctuationFragment;
import com.huawei.ui.main.stories.health.fragment.ClaimDataSimilarOrNotMatchFragment;
import com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.SelectUserInterface;
import defpackage.cfi;
import defpackage.csf;
import defpackage.koq;
import defpackage.nqx;
import defpackage.nrh;
import defpackage.nsf;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ClaimMeasureDataActivity extends BaseActivity implements SelectUserInterface, HealthToolBar.OnSingleTapListener {

    /* renamed from: a, reason: collision with root package name */
    private b f10035a;
    private ArrayList<ClaimMeasureDataFragment> b = new ArrayList<>();
    private int c;
    private boolean d;
    private ClaimMeasureDataFragment e;
    private boolean f;
    private NoTitleCustomAlertDialog g;
    private String h;
    private nqx i;
    private HealthToolBar j;
    private CustomTitleBar k;
    private HealthSubTabWidget m;
    private HealthViewPager n;
    private CustomTextAlertDialog o;

    /* loaded from: classes8.dex */
    public static class b extends StaticHandler<ClaimMeasureDataActivity> {
        b(ClaimMeasureDataActivity claimMeasureDataActivity) {
            super(claimMeasureDataActivity);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: dzu_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(ClaimMeasureDataActivity claimMeasureDataActivity, Message message) {
            if (claimMeasureDataActivity != null && !claimMeasureDataActivity.isFinishing() && !claimMeasureDataActivity.isDestroyed()) {
                ClaimMeasureDataActivity.dzt_(claimMeasureDataActivity, message);
            } else {
                LogUtil.b("PluginDevice_ClaimMeasureDataActivity", "ClaimMeasureDataActivity object is null or is Destroyed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dzt_(ClaimMeasureDataActivity claimMeasureDataActivity, Message message) {
        int i = message.what;
        if (i == 0) {
            Bundle data = message.getData();
            if (claimMeasureDataActivity.e == null || data == null) {
                LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "mCurrentClaimMeasureData is null or MsgData is null");
                return;
            }
            claimMeasureDataActivity.e.claimFinishAndChangeUi(data.getBoolean(ClaimMeasureDataFragment.CLAIM_DATA_FINISH_RESULT), data.getString(ClaimMeasureDataFragment.CLAIM_DATA_FINISH_USER_NAME));
            claimMeasureDataActivity.f = false;
            claimMeasureDataActivity.e(data.getString(ClaimMeasureDataFragment.CLAIM_DATA_FINISH_USER_ID));
        }
        switch (i) {
            case 2:
                ClaimWeightDataManager.INSTANCE.initShowTip();
                claimMeasureDataActivity.i();
                break;
            case 3:
                f(claimMeasureDataActivity);
                break;
            case 4:
                ClaimMeasureDataFragment claimMeasureDataFragment = claimMeasureDataActivity.e;
                if (claimMeasureDataFragment != null) {
                    claimMeasureDataActivity.f = true;
                    claimMeasureDataFragment.saveWeightData();
                    break;
                }
                break;
            case 5:
                ClaimMeasureDataFragment claimMeasureDataFragment2 = claimMeasureDataActivity.e;
                if (claimMeasureDataFragment2 != null) {
                    claimMeasureDataFragment2.showDialog();
                    break;
                }
                break;
            case 6:
                claimMeasureDataActivity.n();
                break;
            case 7:
                if (message.obj instanceof cfi) {
                    claimMeasureDataActivity.c((cfi) message.obj);
                    break;
                }
                break;
            case 8:
                ClaimMeasureDataFragment claimMeasureDataFragment3 = claimMeasureDataActivity.e;
                if (claimMeasureDataFragment3 != null) {
                    claimMeasureDataFragment3.sendClaimWeight();
                    break;
                }
                break;
            default:
                LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "other");
                break;
        }
    }

    private static void f(ClaimMeasureDataActivity claimMeasureDataActivity) {
        claimMeasureDataActivity.d();
        LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "delete data success and start sync");
        ClaimWeightDataManager.INSTANCE.startSync();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_claim_measure_data_layout);
        LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "onCreate...");
        ClaimWeightDataManager.INSTANCE.initShowTip();
        this.f10035a = new b(this);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("productId");
            this.h = stringExtra;
            LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "getIntent mProductId = ", stringExtra);
        }
        j();
        b();
        f();
        ClaimWeightDataManager.INSTANCE.registerCallBack(getClass().getSimpleName(), new CommBaseCallback<ClaimMeasureDataActivity>(this) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.2
            @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onResult(ClaimMeasureDataActivity claimMeasureDataActivity, int i, String str, Object obj) {
                if (ClaimWeightDataManager.INSTANCE.getNewClaimDataCatch().size() > 0) {
                    LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "begin sendMessage CLAIM_WEIGHT_DATA_CHANGE_LIST_UI");
                    ClaimMeasureDataActivity.this.f10035a.sendEmptyMessage(2);
                } else {
                    LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "ClaimWeightDataManager.INSTANCE.getNewClaimDataCatch() = null");
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f() {
        /*
            r17 = this;
            r0 = r17
            java.util.ArrayList<com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment> r1 = r0.b
            int r1 = r1.size()
            r2 = 3
            r3 = 0
            if (r1 != r2) goto Le
            r1 = 1
            goto Lf
        Le:
            r1 = r3
        Lf:
            r4 = 0
            if (r1 == 0) goto L4a
            com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager r2 = com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.INSTANCE
            java.util.Map r2 = r2.getFatRateFluctuationDataBean()
            int r6 = r2.size()
            if (r6 <= 0) goto L4a
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
            r6 = r4
        L28:
            boolean r8 = r2.hasNext()
            if (r8 == 0) goto L4b
            java.lang.Object r8 = r2.next()
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            boolean r9 = defpackage.koq.b(r8)
            if (r9 == 0) goto L3b
            goto L28
        L3b:
            java.lang.Object r8 = r8.get(r3)
            csh r8 = (defpackage.csh) r8
            long r8 = r8.e()
            long r6 = java.lang.Math.max(r6, r8)
            goto L28
        L4a:
            r6 = r4
        L4b:
            com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager r2 = com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.INSTANCE
            java.util.ArrayList r2 = r2.getSimilarWeightDataBean()
            com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager r8 = com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.INSTANCE
            java.util.ArrayList r8 = r8.getNotMatchDataBean()
            boolean r9 = defpackage.koq.c(r2)
            if (r9 == 0) goto L68
            java.lang.Object r2 = r2.get(r3)
            csh r2 = (defpackage.csh) r2
            long r9 = r2.e()
            goto L69
        L68:
            r9 = r4
        L69:
            boolean r2 = defpackage.koq.c(r8)
            if (r2 == 0) goto L79
            java.lang.Object r2 = r8.get(r3)
            csh r2 = (defpackage.csh) r2
            long r4 = r2.e()
        L79:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            if (r1 == 0) goto L87
            java.lang.Long r1 = java.lang.Long.valueOf(r6)
            r2.add(r1)
        L87:
            java.lang.Long r1 = java.lang.Long.valueOf(r9)
            r2.add(r1)
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            r2.add(r1)
            java.lang.Object r1 = java.util.Collections.max(r2)
            java.lang.Long r1 = (java.lang.Long) r1
            long r11 = r1.longValue()
            java.lang.Long r1 = java.lang.Long.valueOf(r11)
            int r1 = r2.indexOf(r1)
            java.lang.String r11 = "setCurrentItem fatRateTime: "
            java.lang.Long r12 = java.lang.Long.valueOf(r6)
            java.lang.String r13 = " similarTime: "
            java.lang.Long r14 = java.lang.Long.valueOf(r9)
            java.lang.String r15 = " notMatchTime: "
            java.lang.Long r16 = java.lang.Long.valueOf(r4)
            java.lang.Object[] r2 = new java.lang.Object[]{r11, r12, r13, r14, r15, r16}
            java.lang.String r3 = "PluginDevice_ClaimMeasureDataActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            com.huawei.ui.commonui.viewpager.HealthViewPager r2 = r0.n
            r2.setCurrentItem(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.f():void");
    }

    private void j() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.custom_title_bar_weight_measure);
        this.k = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
        this.k.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131430298_res_0x7f0b0b9a), nsf.h(R$string.accessibility_close));
        h();
        HealthToolBar healthToolBar = (HealthToolBar) findViewById(R.id.claim_weight_data_health_tool_bar);
        this.j = healthToolBar;
        healthToolBar.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        this.j.setIcon(1, R.drawable._2131430849_res_0x7f0b0dc1);
        this.j.setIcon(2, R.drawable._2131430850_res_0x7f0b0dc2);
        this.j.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
        this.j.setIconTitleColor(1, getString(R$string.IDS_hw_health_show_healthdata_heart_delete), R.color._2131299244_res_0x7f090bac);
        this.j.setIconTitleColor(2, getString(R$string.IDS_hw_weight_claim_data_claim), R.color._2131299244_res_0x7f090bac);
        this.j.setIconTitle(3, getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select));
        this.j.setIconEnabled(1, false);
        this.j.setIconEnabled(2, false);
        this.j.cHf_(this);
        this.j.setOnSingleTapListener(this);
    }

    private void h() {
        this.m = (HealthSubTabWidget) findViewById(R.id.fitness_detail_sub_tab_layout);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.hw_base_health_viewpager);
        this.n = healthViewPager;
        healthViewPager.setScrollHeightArea(200);
        this.n.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.3
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "onPageSelected position = ", Integer.valueOf(i));
                if (koq.d(ClaimMeasureDataActivity.this.b, i)) {
                    ClaimMeasureDataFragment claimMeasureDataFragment = (ClaimMeasureDataFragment) ClaimMeasureDataActivity.this.b.get(i);
                    if (ClaimMeasureDataActivity.this.e != claimMeasureDataFragment) {
                        ClaimMeasureDataActivity.this.d = false;
                        ClaimMeasureDataActivity.this.e.setAllChooseItem(false);
                        ClaimMeasureDataActivity.this.e = claimMeasureDataFragment;
                        return;
                    }
                    return;
                }
                LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "onPageSelected position is isOutOfBounds");
            }
        });
        this.n.setOffscreenPageLimit(3);
    }

    private void m() {
        if (this.c == 0) {
            if (this.j.b(2)) {
                this.k.setTitleText(getString(R$string.IDS_hw_show_main_health_page_healthdata_selectNone));
                this.j.setIcon(1, R.drawable._2131430849_res_0x7f0b0dc1);
                this.j.setIcon(2, R.drawable._2131430850_res_0x7f0b0dc2);
                this.j.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
                this.j.setIconEnabled(1, false);
                this.j.setIconEnabled(2, false);
                this.j.setIconTitleColor(1, getString(R$string.IDS_hw_health_show_healthdata_heart_delete), R.color._2131299244_res_0x7f090bac);
                this.j.setIconTitleColor(2, getString(R$string.IDS_hw_weight_claim_data_claim), R.color._2131299244_res_0x7f090bac);
                this.j.setIconTitle(3, getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select));
                return;
            }
            return;
        }
        if (!this.j.b(2)) {
            this.j.setIcon(1, R.drawable._2131430848_res_0x7f0b0dc0);
            this.j.setIcon(2, R.drawable._2131430847_res_0x7f0b0dbf);
            this.j.setIconEnabled(1, true);
            this.j.setIconEnabled(2, true);
            this.j.setIconTitleColor(1, getString(R$string.IDS_hw_health_show_healthdata_heart_delete), R.color._2131299236_res_0x7f090ba4);
            this.j.setIconTitleColor(2, getString(R$string.IDS_hw_weight_claim_data_claim), R.color._2131299236_res_0x7f090ba4);
        }
        Resources resources = getResources();
        int i = this.c;
        this.k.setTitleText(resources.getQuantityString(R.plurals._2130903044_res_0x7f030004, i, Integer.valueOf(i)));
        ClaimMeasureDataFragment claimMeasureDataFragment = this.e;
        if (this.c == (claimMeasureDataFragment != null ? claimMeasureDataFragment.getListSize() : 0)) {
            this.d = true;
            this.j.setIcon(3, R.drawable._2131430846_res_0x7f0b0dbe);
            this.j.setIconTitle(3, getString(R$string.IDS_contact_delete_uncheck_all));
        } else {
            this.d = false;
            this.j.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
            this.j.setIconTitle(3, getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select));
        }
    }

    private void b() {
        if (this.i == null) {
            this.i = new nqx(this, this.n, this.m);
            b(1, R$string.IDS_hw_weight_similar, !c());
            b(2, R$string.IDS_hw_claim_not_match, false);
            this.e = this.b.get(0);
            this.n.setOffscreenPageLimit(3);
        }
        ArrayList<HiHealthData> claimDataCatch = ClaimWeightDataManager.INSTANCE.getClaimDataCatch();
        if (koq.b(claimDataCatch)) {
            LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "initData claimDataCatch is empty, begin startSync");
            ClaimWeightDataManager.INSTANCE.startSync();
        } else {
            LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "initData claimDataCatch not Empty size = ", Integer.valueOf(claimDataCatch.size()));
        }
    }

    private void b(int i, int i2, boolean z) {
        ClaimDataSimilarOrNotMatchFragment claimDataSimilarOrNotMatchFragment = new ClaimDataSimilarOrNotMatchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pagetype", i);
        bundle.putString("productId", this.h);
        claimDataSimilarOrNotMatchFragment.setArguments(bundle);
        claimDataSimilarOrNotMatchFragment.setUserInterfaceAndHandler(this, this.f10035a);
        this.i.c(this.m.c(getResources().getString(i2)), claimDataSimilarOrNotMatchFragment, z);
        this.b.add(claimDataSimilarOrNotMatchFragment);
        this.i.notifyDataSetChanged();
    }

    private boolean c() {
        if (ClaimWeightDataManager.INSTANCE.getFatRateFluctuationDataBean().size() <= 0) {
            return false;
        }
        ClaimDataFatRateFluctuationFragment claimDataFatRateFluctuationFragment = new ClaimDataFatRateFluctuationFragment();
        claimDataFatRateFluctuationFragment.setUserInterfaceAndHandler(this, this.f10035a);
        this.i.a(this.m.c(getResources().getString(R$string.IDS_hw_fatrate_fluctuation)), 0, claimDataFatRateFluctuationFragment, true);
        this.b.add(0, claimDataFatRateFluctuationFragment);
        this.i.notifyDataSetChanged();
        return true;
    }

    private void g() {
        if (ClaimWeightDataManager.INSTANCE.getFatRateFluctuationDataBean().size() == 0) {
            this.i.a(0);
            this.b.remove(0);
            this.i.notifyDataSetChanged();
        }
    }

    private void i() {
        if (this.i == null) {
            LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "mSubTabFragmentPagerAdapter is null");
            return;
        }
        if (koq.b(this.b)) {
            LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "refreshClaimDataList mClaimMeasureDataList is empty");
            return;
        }
        ArrayList<HiHealthData> claimDataCatch = ClaimWeightDataManager.INSTANCE.getClaimDataCatch();
        if (koq.c(claimDataCatch)) {
            LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "refreshClaimDataList claimDataCatch not Empty size = ", Integer.valueOf(claimDataCatch.size()));
            if (this.b.size() == 3) {
                g();
            } else {
                c();
            }
            Iterator<ClaimMeasureDataFragment> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().setList();
            }
            return;
        }
        LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "refreshClaimDataList claimWeightDataBean is empty, begin startSync");
        ClaimWeightDataManager.INSTANCE.startSync();
    }

    private void c(cfi cfiVar) {
        LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "user.getName()", cfiVar.h());
        String string = getString(R$string.IDS_hw_weight_measure_data_claim_hint, new Object[]{cfiVar.h()});
        LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "content", string);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R$string.IDS_hw_weight_measure_data_claim).e(string).cyR_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClaimMeasureDataActivity.this.o.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R$string.IDS_hw_weight_claim_data_claim, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClaimMeasureDataActivity.this.o.dismiss();
                ClaimMeasureDataActivity.this.f10035a.sendEmptyMessage(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.o = a2;
        a2.show();
    }

    private void n() {
        LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "syncDataStart in");
        this.f10035a.removeMessages(6);
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(HiSyncType.HiSyncDataType.c);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(this).synCloud(hiSyncOption, new HiCommonListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.5
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "syncDataStart onSuccess");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "syncDataStart onFailure");
            }
        });
    }

    @Override // com.huawei.ui.main.stories.health.interactors.healthdata.SelectUserInterface
    public void selectItem(int i, int i2) {
        LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "selectItem dataSize:", Integer.valueOf(i), " listSize:", Integer.valueOf(i2));
        this.c = i2;
        m();
    }

    @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
    public void onSingleTap(int i) {
        if (i == 1) {
            if (this.c > 0) {
                a();
            }
        } else if (i == 2) {
            if (this.c > 0) {
                this.e.claimMeasureData();
            }
        } else {
            if (i == 3) {
                if (this.f) {
                    LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "onSingleTap is Claiming or Deleting");
                    return;
                }
                boolean z = !this.d;
                this.d = z;
                this.e.setAllChooseItem(z);
                return;
            }
            LogUtil.h("PluginDevice_ClaimMeasureDataActivity", "onSingleTap default");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f10035a.removeMessages(6);
        ClaimMeasureDataFragment claimMeasureDataFragment = this.e;
        if (claimMeasureDataFragment != null) {
            this.f = true;
            claimMeasureDataFragment.deleteWeightData();
        }
    }

    private void d() {
        this.f10035a.sendEmptyMessageDelayed(6, 15000L);
        this.e.removeItem();
        this.f = false;
        nrh.b(this, R$string.IDS_hw_weight_claim_data_delete_success_hint);
    }

    private void a() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(R$string.IDS_hw_weight_delete_data_hint).czz_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClaimMeasureDataActivity.this.g.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R$string.IDS_hw_health_show_healthdata_heart_delete, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClaimMeasureDataActivity.this.g.dismiss();
                ClaimMeasureDataActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.g = e;
        e.setCancelable(false);
        this.g.show();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 1) {
            if (intent == null && this.e == null) {
                LogUtil.a("PluginDevice_ClaimMeasureDataActivity", "onActivityResult mCurrentClaimMeasureData is null or data is null");
            } else {
                this.e.onActivityResult(i, i2, intent);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.f10035a.hasMessages(6)) {
            this.f10035a.removeMessages(6);
            n();
        }
        super.onDestroy();
        ClaimWeightDataManager.INSTANCE.startSync();
        ClaimWeightDataManager.INSTANCE.unRegisterCallBack(getClass().getSimpleName());
        csf.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(java.lang.String r5) {
        /*
            r4 = this;
            com.huawei.health.device.fatscale.multiusers.MultiUsersManager r0 = com.huawei.health.device.fatscale.multiusers.MultiUsersManager.INSTANCE
            cfi r0 = r0.getMainUser()
            r1 = 1
            if (r0 == 0) goto L1b
            java.lang.String r0 = r0.i()
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L1b
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L1b
            r0 = r1
            goto L1c
        L1b:
            r0 = 0
        L1c:
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>(r1)
            if (r0 == 0) goto L2a
            java.lang.String r5 = ""
        L2a:
            java.lang.String r0 = "uid"
            r3.put(r0, r5)
            java.lang.String r5 = com.huawei.hihealth.util.HiJsonUtil.e(r3)
            java.lang.String r0 = "result"
            r2.putExtra(r0, r5)
            java.lang.String r0 = "setResult json "
            java.lang.Object[] r5 = new java.lang.Object[]{r0, r5}
            java.lang.String r0 = "PluginDevice_ClaimMeasureDataActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
            r5 = -1
            r4.setResult(r5, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity.e(java.lang.String):void");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
