package defpackage;

import android.content.Intent;
import com.huawei.android.bundlecore.install.ModuleInstallTask;
import defpackage.yu;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
final class yv extends ModuleInstallTask {
    private final int c;
    private final yp e;

    yv(yu yuVar, List<yi> list, int i, yp ypVar) {
        super(yuVar, true, list);
        this.c = i;
        this.e = ypVar;
    }

    @Override // com.huawei.android.bundlecore.install.ModuleInstallTask
    public void onPreInstall() {
        super.onPreInstall();
        yt a2 = this.e.a(this.c);
        if (a2 != null) {
            this.e.a(this.c, 4);
            c(a2);
        }
    }

    @Override // com.huawei.android.bundlecore.install.ModuleInstallTask
    public void onInstallCompleted(List<yu.c> list) {
        super.onInstallCompleted(list);
        ArrayList arrayList = new ArrayList(list.size());
        for (yu.c cVar : list) {
            Intent intent = new Intent();
            if (cVar.b != null) {
                intent.putStringArrayListExtra("added-dex", (ArrayList) cVar.b);
            }
            intent.putExtra("apk", cVar.f17767a.getAbsolutePath());
            intent.putExtra("moduleName", cVar.e);
            arrayList.add(intent);
        }
        yt a2 = this.e.a(this.c);
        if (a2 != null) {
            a2.e(arrayList);
            this.e.a(this.c, 10);
            c(a2);
        }
    }

    @Override // com.huawei.android.bundlecore.install.ModuleInstallTask
    public void onInstallFailed(List<zj> list) {
        super.onInstallFailed(list);
        yt a2 = this.e.a(this.c);
        if (a2 != null) {
            if (!list.isEmpty()) {
                a2.b(list.get(0).b());
            }
            this.e.a(this.c, 6);
            c(a2);
        }
    }

    private void c(yt ytVar) {
        this.e.c(ytVar);
    }
}
