package com.huawei.skinner.base;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.huawei.skinner.internal.IDynamicNewView;
import com.huawei.skinner.internal.ISkinUpdate2;
import com.huawei.skinner.internal.ISkinnableViewManager2;
import defpackage.ncg;
import defpackage.nci;
import defpackage.ncq;
import java.util.List;

/* loaded from: classes9.dex */
public class SkinBaseAppcompatActivity extends AppCompatActivity implements ISkinUpdate2, IDynamicNewView, ISkinnableViewManager2 {
    private ncq d = new ncq(this);

    /* renamed from: a, reason: collision with root package name */
    private boolean f8721a = false;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        a();
        super.onCreate(bundle);
    }

    private void a() {
        if (this.f8721a) {
            return;
        }
        this.f8721a = true;
        this.d.b();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.d.d();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.d.e();
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate
    public void onThemeServiceUpdate() {
        this.d.onThemeServiceUpdate();
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate
    public void onThemeUpdate() {
        onThemeUpdate(false);
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate2
    public void onThemeUpdate(boolean z) {
        applySkin(z);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, String str, int i) {
        this.d.dynamicAddSkinableView(view, str, i);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, List<ncg> list) {
        this.d.dynamicAddSkinableView(view, list);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci addSkinnableView(nci nciVar) {
        return this.d.addSkinnableView(nciVar);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci removeSkinnableView(nci nciVar) {
        return this.d.removeSkinnableView(nciVar);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void applySkin() {
        applySkin(false);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager2
    public void applySkin(boolean z) {
        this.d.applySkin(z);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void clean() {
        this.d.clean();
    }
}
