package com.huawei.health.suggestion.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gcd;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public abstract class BaseStateActivity extends com.huawei.ui.commonui.base.BaseActivity {
    private static final String TAG = "Suggestion_BaseStateActivity";
    protected ExecutorService mCachedThreadPool;
    private View mErrorView;
    public View mLoadingView;
    public CustomTitleBar mTitleBar;

    protected int getErrorLayoutId() {
        return R.layout.sug_error_layout;
    }

    protected int getLoadingLayoutId() {
        return 0;
    }

    public abstract void initData();

    public abstract void initLayout();

    public abstract void initViewController();

    protected int setLoadingBackgroundColor() {
        return -1;
    }

    public void updateViewController() {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        gcd.d(new WeakReference(this));
        this.mCachedThreadPool = Executors.newCachedThreadPool();
        initLayout();
        if (getLoadingLayoutId() != 0) {
            this.mLoadingView = LayoutInflater.from(getApplicationContext()).inflate(getLoadingLayoutId(), (ViewGroup) null);
            ((FrameLayout) findViewById(android.R.id.content)).addView(this.mLoadingView, -1, -1);
            if (setLoadingBackgroundColor() != -1) {
                this.mLoadingView.setBackgroundColor(setLoadingBackgroundColor());
            }
            this.mLoadingView.setTag(false);
        }
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sug_fitness_title);
        this.mTitleBar = customTitleBar;
        if (customTitleBar != null) {
            customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.BaseStateActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseStateActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        initViewController();
        this.mCachedThreadPool.execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseStateActivity.2
            @Override // java.lang.Runnable
            public void run() {
                BaseStateActivity.this.initData();
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    public void showLoading() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void finishLoading() {
        View view = this.mLoadingView;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseStateActivity.4
            @Override // java.lang.Runnable
            public void run() {
                BaseStateActivity.this.mLoadingView.setVisibility(8);
            }
        });
    }

    public void showErrorLayout() {
        if (this.mLoadingView != null) {
            runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseStateActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    BaseStateActivity.this.visibleErrorLayout();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void visibleErrorLayout() {
        this.mLoadingView.setVisibility(4);
        if ((this.mLoadingView.getParent() instanceof ViewGroup) && ((ViewGroup) this.mLoadingView.getParent()).findViewById(getErrorLayoutId()) == null) {
            this.mErrorView = LayoutInflater.from(getApplicationContext()).inflate(getErrorLayoutId(), (ViewGroup) null);
            if (this.mLoadingView.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.mLoadingView.getParent()).addView(this.mErrorView, -1, -1);
                return;
            }
            return;
        }
        View view = this.mErrorView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void retry(View view) {
        LogUtil.c(TAG, "BaseStateActivity retry get the data!");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "BaseStateActivity onDestroy():");
        this.mCachedThreadPool.shutdown();
    }
}
