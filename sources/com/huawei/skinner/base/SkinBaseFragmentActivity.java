package com.huawei.skinner.base;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.huawei.skinner.internal.IDynamicNewView;
import com.huawei.skinner.internal.ISkinUpdate2;
import com.huawei.skinner.internal.ISkinnableViewManager2;
import defpackage.ncg;
import defpackage.nci;
import defpackage.ncq;
import java.util.List;

/* loaded from: classes6.dex */
public class SkinBaseFragmentActivity extends FragmentActivity implements ISkinUpdate2, IDynamicNewView, ISkinnableViewManager2 {
    private ncq mSkinProcessor = new ncq(this);
    private boolean isInited = false;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initialize();
    }

    private void initialize() {
        if (this.isInited) {
            return;
        }
        this.isInited = true;
        this.mSkinProcessor.b();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mSkinProcessor.d();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mSkinProcessor.e();
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate
    public void onThemeUpdate() {
        onThemeUpdate(false);
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate
    public void onThemeServiceUpdate() {
        this.mSkinProcessor.onThemeServiceUpdate();
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate2
    public void onThemeUpdate(boolean z) {
        applySkin(z);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, String str, int i) {
        this.mSkinProcessor.dynamicAddSkinableView(view, str, i);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, List<ncg> list) {
        this.mSkinProcessor.dynamicAddSkinableView(view, list);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci addSkinnableView(nci nciVar) {
        return this.mSkinProcessor.addSkinnableView(nciVar);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci removeSkinnableView(nci nciVar) {
        return this.mSkinProcessor.removeSkinnableView(nciVar);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void applySkin() {
        applySkin(false);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager2
    public void applySkin(boolean z) {
        this.mSkinProcessor.applySkin(z);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void clean() {
        this.mSkinProcessor.clean();
    }

    public void setSkinEnable(boolean z) {
        this.mSkinProcessor.c(z);
    }

    public boolean isSkinEnable() {
        return this.mSkinProcessor.c();
    }

    public void setAnimateEnable(boolean z) {
        this.mSkinProcessor.b(z);
    }

    public boolean isAnimateEnable() {
        return this.mSkinProcessor.a();
    }
}
