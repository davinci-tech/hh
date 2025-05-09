package com.huawei.ui.main.stories.soical.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.marketing.request.CustomConfigValue;
import com.huawei.health.marketing.request.IFavoriteCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.soical.activity.FunctionMenuActivity;
import com.huawei.ui.main.stories.soical.views.FunctionMenuAdapter;
import defpackage.eil;
import defpackage.koq;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class FunctionMenuActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private ViewGroup f10475a;
    private int b;
    private FunctionMenuAdapter c;
    private final Handler d = new Handler() { // from class: com.huawei.ui.main.stories.soical.activity.FunctionMenuActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("FunctionMenuActivity", "message is null!");
                return;
            }
            super.handleMessage(message);
            if (message.what == 12289 && (message.obj instanceof List)) {
                List<CustomConfigValue> list = (List) message.obj;
                FunctionMenuActivity.this.f10475a.setVisibility(8);
                FunctionMenuActivity.this.f.setVisibility(0);
                FunctionMenuActivity.this.c.b(FunctionMenuActivity.this.e, list);
            }
        }
    };
    private List<SingleGridContent> e;
    private HealthRecycleView f;
    private ResourceBriefInfo j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("FunctionMenuActivity", "enter onCreate.");
        setContentView(R.layout.activity_function_menu);
        this.f10475a = (ViewGroup) findViewById(R.id.layout_loading);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.rv_function_menu);
        this.f = healthRecycleView;
        healthRecycleView.setVisibility(8);
        this.f.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.f.setLayoutManager(linearLayoutManager);
        this.f.setNestedScrollingEnabled(false);
        FunctionMenuAdapter functionMenuAdapter = new FunctionMenuAdapter(this);
        this.c = functionMenuAdapter;
        this.f.setAdapter(functionMenuAdapter);
        ((CustomTitleBar) findViewById(R.id.ctb_function_menu)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.activity.FunctionMenuActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FunctionMenuActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f10475a.setVisibility(0);
        b();
        c();
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("contentType", 0);
            this.b = intExtra;
            LogUtil.h("FunctionMenuActivity", "intent mContentType: ", Integer.valueOf(intExtra));
            return;
        }
        LogUtil.h("FunctionMenuActivity", "intent is null");
    }

    private void c() {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(4001);
            resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: rxd
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    FunctionMenuActivity.this.e(marketingApi, (Map) obj);
                }
            });
            resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: rxe
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    FunctionMenuActivity.this.a(exc);
                }
            });
        }
    }

    public /* synthetic */ void e(MarketingApi marketingApi, Map map) {
        ResourceResultInfo resourceResultInfo = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map).get(4001);
        if (resourceResultInfo == null || koq.b(resourceResultInfo.getResources())) {
            LogUtil.a("FunctionMenuActivity", "resultInfo or resourceList is null");
            return;
        }
        Iterator<ResourceBriefInfo> it = resourceResultInfo.getResources().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResourceBriefInfo next = it.next();
            if (next.getContentType() == this.b) {
                this.j = next;
                break;
            }
        }
        ResourceBriefInfo resourceBriefInfo = this.j;
        if (resourceBriefInfo == null || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(this.j.getContent().getContent())) {
            LogUtil.a("FunctionMenuActivity", "mResultBriefInfo or Content is null");
            return;
        }
        LogUtil.a("FunctionMenuActivity", "data:", this.j.getContent().getContent());
        try {
            GridTemplate gridTemplate = (GridTemplate) new Gson().fromJson(this.j.getContent().getContent(), GridTemplate.class);
            if (gridTemplate == null) {
                LogUtil.h("FunctionMenuActivity", "gridTemplate is null.");
                return;
            }
            GridTemplate e = e(gridTemplate);
            if (!e.isIntelligentFlag()) {
                Collections.sort(e.getGridContents(), new Comparator() { // from class: rxb
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return FunctionMenuActivity.b((SingleGridContent) obj, (SingleGridContent) obj2);
                    }
                });
            }
            List<SingleGridContent> gridContents = e.getGridContents();
            this.e = gridContents;
            gridContents.remove(gridContents.size() - 1);
            ColumnRequestUtils.getFavoriteData(new IFavoriteCallBack() { // from class: rxa
                @Override // com.huawei.health.marketing.request.IFavoriteCallBack
                public final void onResult(List list) {
                    FunctionMenuActivity.this.c(list);
                }
            });
        } catch (JsonSyntaxException unused) {
            LogUtil.h("FunctionMenuActivity", "gridTemplate exception");
        }
    }

    public static /* synthetic */ int b(SingleGridContent singleGridContent, SingleGridContent singleGridContent2) {
        return singleGridContent2.getPriority() - singleGridContent.getPriority();
    }

    public /* synthetic */ void c(List list) {
        this.d.obtainMessage(12289, list).sendToTarget();
    }

    public /* synthetic */ void a(Exception exc) {
        this.f10475a.setVisibility(8);
    }

    private GridTemplate e(GridTemplate gridTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleGridContent> gridContents = gridTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleGridContent singleGridContent : gridContents) {
                if (eil.d(singleGridContent.getItemEffectiveTime(), singleGridContent.getItemExpirationTime())) {
                    arrayList.add(singleGridContent);
                }
            }
            gridTemplate.setGridContents(arrayList);
        }
        return gridTemplate;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
