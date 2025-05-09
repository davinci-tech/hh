package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.ExperienceHistoryBean;
import com.huawei.pluginachievement.manager.model.LevelLineRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.ui.adapter.LevelExpandableAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.listview.HealthExpandableListView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.koq;
import defpackage.meh;
import defpackage.mfm;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nsf;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes9.dex */
public class AchieveLevelRecordActivity extends BaseActivity implements AchieveObserver {

    /* renamed from: a, reason: collision with root package name */
    private Context f8389a;
    private LevelExpandableAdapter b;
    private HealthExpandableListView c;
    private CustomTitleBar d;
    private Handler e;
    private List<ExperienceHistoryBean> g = new ArrayList(8);
    private meh h;

    static class b extends BaseHandler<AchieveLevelRecordActivity> {
        b(AchieveLevelRecordActivity achieveLevelRecordActivity) {
            super(achieveLevelRecordActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: chd_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchieveLevelRecordActivity achieveLevelRecordActivity, Message message) {
            if (achieveLevelRecordActivity == null || achieveLevelRecordActivity.g == null) {
                LogUtil.h("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "handleMessageWhenReferenceNotNull is null.");
                return;
            }
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "handlerCheckInMsg message = ", Integer.valueOf(message.what));
            if (message.what == 25) {
                c(achieveLevelRecordActivity, message.obj);
            }
        }

        private void c(AchieveLevelRecordActivity achieveLevelRecordActivity, Object obj) {
            if (!(obj instanceof LevelLineRecord)) {
                LogUtil.h("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "handleHistoryRecord obj is not LevelLineRecord.");
                return;
            }
            List<ExperienceHistoryBean> records = ((LevelLineRecord) obj).getRecords();
            if (!koq.b(records)) {
                achieveLevelRecordActivity.g.addAll(records);
                if (records.size() >= 200) {
                    achieveLevelRecordActivity.c(records.get(records.size() - 1).getCompleteTime());
                    achieveLevelRecordActivity.g = achieveLevelRecordActivity.a((List<ExperienceHistoryBean>) achieveLevelRecordActivity.g);
                }
                LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "handleHistoryRecord records size = ", Integer.valueOf(achieveLevelRecordActivity.g.size()));
                achieveLevelRecordActivity.b(achieveLevelRecordActivity.g);
                return;
            }
            LogUtil.h("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "handleHistoryRecord records is isEmpty.");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.achieve_level_record);
        clearBackgroundDrawable();
        cancelAdaptRingRegion();
        this.f8389a = this;
        this.e = new b(this);
        b();
        c(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ExperienceHistoryBean> a(List<ExperienceHistoryBean> list) {
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList(8);
        for (ExperienceHistoryBean experienceHistoryBean : list) {
            if (hashSet.add(experienceHistoryBean)) {
                arrayList.add(experienceHistoryBean);
            }
        }
        return arrayList;
    }

    private void b() {
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "initView()");
        meh c = meh.c(getApplicationContext());
        this.h = c;
        c.b((AchieveObserver) this);
        this.c = (HealthExpandableListView) findViewById(R.id.expandable_list_view);
        LevelExpandableAdapter levelExpandableAdapter = new LevelExpandableAdapter(this, this.g);
        this.b = levelExpandableAdapter;
        this.c.setAdapter(levelExpandableAdapter);
        a();
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) mfm.cgL_(this, R.id.level_titlebar);
        this.d = customTitleBar;
        customTitleBar.setRightButtonVisibility(8);
        if (LanguageUtil.bc(this.f8389a)) {
            if (nrt.a(this)) {
                this.d.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131429734_res_0x7f0b0966), nsf.h(R.string._2130850617_res_0x7f023339));
            }
        } else if (nrt.a(this)) {
            this.d.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131429733_res_0x7f0b0965), nsf.h(R.string._2130850617_res_0x7f023339));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<ExperienceHistoryBean> list) {
        if (koq.b(list)) {
            return;
        }
        this.b.b(list);
        this.c.expandGroup(0);
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "onDataChanged error=", Integer.valueOf(i));
        if (i == -1) {
            if (CommonUtil.aa(BaseApplication.getContext())) {
                return;
            }
            nrh.b(BaseApplication.getContext(), R.string._2130841392_res_0x7f020f30);
        } else {
            if (userAchieveWrapper == null) {
                return;
            }
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "onDataChanged resultCode=", userAchieveWrapper.getResultCode());
            if ("0".equals(userAchieveWrapper.getResultCode()) && userAchieveWrapper.getContentType() == 25) {
                e(25, (int) userAchieveWrapper.getLevelLineRecord());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final long j) {
        if (this.h == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveLevelRecordActivity.1
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap(8);
                hashMap.put("pageNum", String.valueOf(200));
                hashMap.put("timestamp", String.valueOf(j));
                hashMap.put("timeZone", HiDateUtil.d((String) null));
                AchieveLevelRecordActivity.this.h.a(25, hashMap);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        meh mehVar = this.h;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
    }

    private <T> void e(int i, T t) {
        Handler handler = this.e;
        if (handler == null) {
            LogUtil.h("PLGACHIEVE_PLGACHIEVE_AchieveLevelRecordActivity", "sendHandlerMessage handler is null!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        handler.sendMessage(obtain);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
