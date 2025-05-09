package com.huawei.ui.homewear21.home.legal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.gltexture.util.FileUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.webview.WebViewActivity;
import com.huawei.ui.homewear21.home.adapter.WearHomeLegalRecyclerAdapter;
import com.huawei.ui.homewear21.home.bean.WearHomeXmlParseBean;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeOpenSourceStatementActivity extends BaseActivity {
    private Context e;
    private List<WearHomeXmlParseBean> c = new ArrayList(16);
    private String b = BaseApplication.getContext().getFilesDir() + "/source/";

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.open_source_statement_activity);
        this.e = this;
        d();
        b();
    }

    private void b() {
        ((CustomTitleBar) nsy.cMc_(this, R.id.wear_home_open_source_statement_title)).setTitleText(BaseApplication.getContext().getString(R.string._2130843847_res_0x7f0218c7));
        HealthRecycleView healthRecycleView = (HealthRecycleView) nsy.cMc_(this, R.id.open_source_statement_recycleView);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        if (this.c != null) {
            WearHomeLegalRecyclerAdapter wearHomeLegalRecyclerAdapter = new WearHomeLegalRecyclerAdapter(this.e, this.c, "openStatementAdapter");
            wearHomeLegalRecyclerAdapter.c(new WearHomeListener() { // from class: com.huawei.ui.homewear21.home.legal.WearHomeOpenSourceStatementActivity.5
                @Override // com.huawei.ui.homewear21.home.listener.WearHomeListener
                public void onItemClick(int i) {
                    if (i < 0 || i >= WearHomeOpenSourceStatementActivity.this.c.size()) {
                        LogUtil.a("WearHomeOpenSourceStatementActivity", "position is wrong ");
                        return;
                    }
                    String str = "file:" + WearHomeOpenSourceStatementActivity.this.b + ((WearHomeXmlParseBean) WearHomeOpenSourceStatementActivity.this.c.get(i)).getValueName();
                    String xmlName = ((WearHomeXmlParseBean) WearHomeOpenSourceStatementActivity.this.c.get(i)).getXmlName();
                    try {
                        Intent intent = new Intent(WearHomeOpenSourceStatementActivity.this.e, (Class<?>) WebViewActivity.class);
                        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
                        intent.putExtra(Constants.JUMP_MODE_KEY, 7);
                        intent.putExtra("WebViewActivity.TITLE", xmlName);
                        WearHomeOpenSourceStatementActivity.this.e.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("WearHomeOpenSourceStatementActivity", "initView ActivityNotFoundException");
                    }
                }
            });
            healthRecycleView.setAdapter(wearHomeLegalRecyclerAdapter);
        }
    }

    private void d() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.c = intent.getParcelableArrayListExtra("openSourceStatementName");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("WearHomeOpenSourceStatementActivity", "ArrayIndexOutOfBoundsException");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        FileUtil.c(this.e).c(this.b);
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
