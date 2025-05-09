package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.huawei.skinner.attrentry.SkinAttr;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

/* loaded from: classes6.dex */
public class nck extends SkinAttr {

    /* renamed from: a, reason: collision with root package name */
    private ndk f15247a;
    private ConcurrentSkipListSet<SkinAttr> b = new ConcurrentSkipListSet<>(new ndi());

    @Override // com.huawei.skinner.attrentry.SkinAttr
    public void apply(View view, boolean z) {
        synchronized (this) {
            d();
            csU_(view.getContext(), view);
            if (!ndb.e(this.b)) {
                Iterator<SkinAttr> it = this.b.iterator();
                while (it.hasNext()) {
                    it.next().apply(view, z);
                }
            }
        }
    }

    private void d() {
        Iterator<SkinAttr> it = this.b.iterator();
        while (it.hasNext()) {
            SkinAttr next = it.next();
            if (next != null) {
                next.setCancled(true);
            }
        }
    }

    private void csU_(Context context, View view) {
        int i;
        Resources resources;
        ncs c = ncs.c(context);
        if (c.a()) {
            context = c.d().getPluginContext();
            resources = context.getResources();
            i = resources.getIdentifier(this.attrValueRefName, this.attrValueTypeName, c.d().getPackageName());
        } else {
            i = this.attrValueRefId;
            resources = context.getResources();
        }
        Context context2 = context;
        int i2 = i;
        Resources resources2 = resources;
        if (this.f15247a == null) {
            this.f15247a = new ndk(view, this.attrName);
        }
        this.f15247a.ctk_(context2, view, resources2, i2, this.b);
    }
}
