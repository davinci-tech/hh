package com.huawei.skinner.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.huawei.skinner.internal.IDynamicNewView;
import com.huawei.skinner.internal.ISkinUpdate2;
import com.huawei.skinner.internal.ISkinnableViewManager2;
import defpackage.ncg;
import defpackage.nci;
import defpackage.ncq;
import java.util.List;

/* loaded from: classes9.dex */
public class SkinBaseActivity extends Activity implements ISkinUpdate2, IDynamicNewView, ISkinnableViewManager2 {
    private ncq c = new ncq(this);
    private boolean d = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
    }

    private void b() {
        if (this.d) {
            return;
        }
        this.d = true;
        this.c.b();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.c.d();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.c.e();
    }

    @Override // com.huawei.skinner.internal.ISkinUpdate
    public void onThemeServiceUpdate() {
        this.c.onThemeServiceUpdate();
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
        this.c.dynamicAddSkinableView(view, str, i);
    }

    @Override // com.huawei.skinner.internal.IDynamicNewView
    public void dynamicAddSkinableView(View view, List<ncg> list) {
        this.c.dynamicAddSkinableView(view, list);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci addSkinnableView(nci nciVar) {
        return this.c.addSkinnableView(nciVar);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public nci removeSkinnableView(nci nciVar) {
        return this.c.removeSkinnableView(nciVar);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void applySkin() {
        applySkin(false);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager2
    public void applySkin(boolean z) {
        this.c.applySkin(z);
    }

    @Override // com.huawei.skinner.internal.ISkinnableViewManager
    public void clean() {
        this.c.clean();
    }
}
