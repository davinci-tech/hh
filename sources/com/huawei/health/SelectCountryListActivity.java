package com.huawei.health;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.SelectCountryListActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.scm;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SelectCountryListActivity extends BaseActivity {
    private List<String> b = new ArrayList();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.c("SelectCountryListActivity", "onCreate()");
        setContentView(R.layout.service_country_all_list_layout);
        cancelAdaptRingRegion();
        e();
    }

    private void e() {
        ((CustomTitleBar) findViewById(R.id.third_party_title_bar)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: byf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectCountryListActivity.this.BM_(view);
            }
        });
        ListView listView = (ListView) findViewById(R.id.service_country_list);
        setViewSafeRegion(false, listView);
        this.b = scm.b();
        listView.setAdapter((ListAdapter) new ArrayAdapter(this, R.layout.service_country_list_item_layout, this.b));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.SelectCountryListActivity$$ExternalSyntheticLambda1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                SelectCountryListActivity.this.BN_(adapterView, view, i, j);
            }
        });
    }

    public /* synthetic */ void BM_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void BN_(AdapterView adapterView, View view, int i, long j) {
        if (i < this.b.size() && i >= 0) {
            LogUtil.a("SelectCountryListActivity", "setOnItemClickListener: strCountry=", this.b.get(i));
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("service_area_country", this.b.get(i));
            intent.putExtras(bundle);
            setResult(0, intent);
            finish();
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("SelectCountryListActivity", "onBackPressed()");
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
