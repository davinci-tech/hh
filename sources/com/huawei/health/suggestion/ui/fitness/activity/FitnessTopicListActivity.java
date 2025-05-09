package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.TopicFitnessView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.arx;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FitnessTopicListActivity extends BaseStateActivity {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<String> f3094a = new ArrayList<>(10);
    private CustomTitleBar b;
    private TopicFitnessView d;
    private String e;

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(16777216, 16777216);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_fit_topic_list);
        a();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        LogUtil.a("Suggestion_FitnessTopicListActivity", "initViewController()");
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        try {
            this.f3094a = intent.getStringArrayListExtra("intent_key_topic_num");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("Suggestion_FitnessTopicListActivity", "get topics fail");
        }
        ArrayList<String> arrayList = this.f3094a;
        if (arrayList != null) {
            this.d.b(arrayList);
        }
    }

    public void a() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.e = intent.getStringExtra("intent_key_fitness_title");
        this.b = (CustomTitleBar) findViewById(R.id.fitness_recommend_course_title);
        this.d = (TopicFitnessView) findViewById(R.id.weight_recommend_fitness_view);
        String str = this.e;
        if (str == null) {
            this.b.setTitleText(arx.b().getString(R$string.IDS_device_wifi_weight_course_for_you));
        } else {
            this.b.setTitleText(str);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
