package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import defpackage.bzs;
import defpackage.edy;
import defpackage.efb;
import defpackage.gge;
import defpackage.koq;
import defpackage.mtp;
import defpackage.nkx;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SleepRecordEntryProvider extends BaseKnitDataProvider {
    private WeakReference<Context> c;
    private SectionBean f;
    private boolean i = efb.b(BaseApplication.getContext());
    private boolean d = efb.d();

    /* renamed from: a, reason: collision with root package name */
    private boolean f9829a = efb.b();
    private List<edy> b = new ArrayList();
    private View.OnClickListener e = new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepRecordEntryProvider.4
        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        private void drr_(View view) {
            if (view.getId() == R.id.section_button_view1) {
                SleepRecordEntryProvider.this.c();
            } else if (view.getId() == R.id.section_button_view2) {
                SleepRecordEntryProvider.this.b();
            } else if (view.getId() == R.id.section_button_view3) {
                SleepRecordEntryProvider.this.a();
            }
        }

        private void drs_(View view) {
            if (!SleepRecordEntryProvider.this.i || !SleepRecordEntryProvider.this.d) {
                if (!SleepRecordEntryProvider.this.i || !SleepRecordEntryProvider.this.f9829a) {
                    if (SleepRecordEntryProvider.this.f9829a && SleepRecordEntryProvider.this.d) {
                        if (view.getId() == R.id.section_button_view1) {
                            SleepRecordEntryProvider.this.b();
                            return;
                        } else {
                            if (view.getId() == R.id.section_button_view2) {
                                SleepRecordEntryProvider.this.a();
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                if (view.getId() == R.id.section_button_view1) {
                    SleepRecordEntryProvider.this.c();
                    return;
                } else {
                    if (view.getId() == R.id.section_button_view2) {
                        SleepRecordEntryProvider.this.a();
                        return;
                    }
                    return;
                }
            }
            if (view.getId() == R.id.section_button_view1) {
                SleepRecordEntryProvider.this.c();
            } else if (view.getId() == R.id.section_button_view2) {
                SleepRecordEntryProvider.this.b();
            }
        }

        private void drq_(View view) {
            if (!SleepRecordEntryProvider.this.i || view.getId() != R.id.section_button_view1) {
                if (!SleepRecordEntryProvider.this.f9829a || view.getId() != R.id.section_button_view1) {
                    if (SleepRecordEntryProvider.this.d && view.getId() == R.id.section_button_view1) {
                        SleepRecordEntryProvider.this.b();
                        return;
                    }
                    return;
                }
                SleepRecordEntryProvider.this.a();
                return;
            }
            SleepRecordEntryProvider.this.c();
        }

        private void drt_(View view, int i) {
            if (i == 3) {
                drr_(view);
                return;
            }
            if (i == 2) {
                drs_(view);
            } else if (i == 1) {
                drq_(view);
            } else {
                LogUtil.a("SleepRecordEntryProvider", "entry not exist");
            }
        }

        private void drv_(View view) {
            if (view.getId() == R.id.section_button_view1) {
                SleepRecordEntryProvider.this.c();
            } else if (view.getId() == R.id.section_button_view3) {
                SleepRecordEntryProvider.this.a();
            } else if (view.getId() == R.id.section_button_view4) {
                SleepRecordEntryProvider.this.b();
            }
        }

        private void drw_(View view) {
            if (!SleepRecordEntryProvider.this.i || !SleepRecordEntryProvider.this.d) {
                if (!SleepRecordEntryProvider.this.i || !SleepRecordEntryProvider.this.f9829a) {
                    if (SleepRecordEntryProvider.this.f9829a && SleepRecordEntryProvider.this.d) {
                        if (view.getId() == R.id.section_button_view1) {
                            SleepRecordEntryProvider.this.a();
                            return;
                        } else {
                            if (view.getId() == R.id.section_button_view2) {
                                SleepRecordEntryProvider.this.b();
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                if (view.getId() == R.id.section_button_view1) {
                    SleepRecordEntryProvider.this.c();
                    return;
                } else {
                    if (view.getId() == R.id.section_button_view2) {
                        SleepRecordEntryProvider.this.a();
                        return;
                    }
                    return;
                }
            }
            if (view.getId() == R.id.section_button_view1) {
                SleepRecordEntryProvider.this.c();
            } else if (view.getId() == R.id.section_button_view2) {
                SleepRecordEntryProvider.this.b();
            }
        }

        private void dru_(View view) {
            if (!SleepRecordEntryProvider.this.i || view.getId() != R.id.section_button_view1) {
                if (!SleepRecordEntryProvider.this.f9829a || view.getId() != R.id.section_button_view1) {
                    if (SleepRecordEntryProvider.this.d && view.getId() == R.id.section_button_view1) {
                        SleepRecordEntryProvider.this.b();
                        return;
                    }
                    return;
                }
                SleepRecordEntryProvider.this.a();
                return;
            }
            SleepRecordEntryProvider.this.c();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ?? r0 = SleepRecordEntryProvider.this.i;
            int i = r0;
            if (SleepRecordEntryProvider.this.d) {
                i = r0 + 1;
            }
            int i2 = i;
            if (SleepRecordEntryProvider.this.f9829a) {
                i2 = i + 1;
            }
            if (VersionControlUtil.isSupportSleepManagement()) {
                drt_(view, i2);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (i2 == 3) {
                drv_(view);
            } else if (i2 == 2) {
                drw_(view);
            } else if (i2 == 1) {
                dru_(view);
            } else {
                LogUtil.a("SleepRecordEntryProvider", "entry not exist");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private long g = System.currentTimeMillis();
    private boolean h = true;

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Context context = this.c.get();
        if (context == null) {
            LogUtil.h("SleepRecordEntryProvider", "jumpToPhone: context is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("hasdate", "true");
        gge.e(AnalyticsValue.MANUALLY_MONITOR_SLEEP_21300044.value(), hashMap);
        mtp.d().e(context, "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int i;
        H5ProClient.getServiceManager().registerService(SleepJsApi.class);
        Context context = this.c.get();
        if (context == null) {
            LogUtil.h("SleepRecordEntryProvider", "jumpToManualEntry: context is null");
            return;
        }
        if (context instanceof KnitSleepDetailActivity) {
            if (VersionControlUtil.isSupportSleepManagement()) {
                i = 9;
            } else {
                i = ((KnitSleepDetailActivity) context).mIsNoDataFragment ? 2 : 1;
            }
            bzs.e().loadH5ProApp(context, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addPath("#/sleepDataInput?pullFrom=" + i + "&whichDate=" + this.g).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("SleepDetection", mtp.d().getJsModule()).setForceDarkMode(1).setImmerse().showStatusBar().setStatusBarTextBlack(true));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Context context = this.c.get();
        if (context == null) {
            LogUtil.h("SleepRecordEntryProvider", "jumpToManualEntry: context is null");
            return;
        }
        LogUtil.a("SleepRecordEntryProvider", "jump to rest");
        Intent intent = new Intent();
        intent.setClassName(context, ComponentInfo.PluginSleepBriefs_A_1);
        intent.putExtra("moduleName", "SleepBriefs");
        intent.putExtra("from", 1);
        AppBundle.e().launchActivity(context, intent, null);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        if (EnvironmentInfo.k()) {
            return false;
        }
        return efb.d() || efb.b(context) || efb.b();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.f = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        if (this.h) {
            ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepRecordEntryProvider.2
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    if (!koq.e(objArr, 0)) {
                        LogUtil.a("SleepRecordEntryProvider", "null args!");
                    } else {
                        SleepRecordEntryProvider.this.g = ((Long) objArr[0]).longValue();
                    }
                }
            }, "SLEEP_CHART_NOW_DATE");
            this.h = false;
        }
    }

    private void c(HashMap hashMap) {
        if (this.c.get() instanceof BaseActivity) {
            View.OnClickListener cwZ_ = nkx.cwZ_(this.e, (BaseActivity) this.c.get(), true, "");
            if (this.i) {
                this.b.add(new edy.d().e(R.drawable.section_entry_card_background).d((!LanguageUtil.m(this.c.get()) || Utils.o()) ? R.drawable._2131430413_res_0x7f0b0c0d : R.drawable._2131431542_res_0x7f0b1076).a(R$string.IDS_intel_record_sleep).agQ_(cwZ_).b());
                hashMap.put("RIGHT_ICON_TEXT", Integer.valueOf(R$string.IDS_clear_sleep_data_cache));
            }
            if (this.d) {
                this.b.add(new edy.d().e(R.drawable.section_entry_card_background).d(R.drawable._2131430415_res_0x7f0b0c0f).a(R$string.IDS_rest_efficient).agQ_(cwZ_).b());
            }
            if (this.f9829a) {
                this.b.add(new edy.d().e(R.drawable.section_entry_card_background).d(R.drawable._2131430412_res_0x7f0b0c0c).a(R$string.IDS_sleep_input_text5).agQ_(cwZ_).b());
            }
            hashMap.put("SECTION_ENTRY_BUTTON_BEAN_LIST", this.b);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("SleepRecordEntryProvider", "parseParams");
        if (hashMap == null || context == null) {
            return;
        }
        this.c = new WeakReference<>(context);
        this.b.clear();
        c(hashMap);
    }
}
