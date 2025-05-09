package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.huawei.skinner.attrentry.SkinAttr;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

/* loaded from: classes6.dex */
public class ncj extends SkinAttr {
    private ConcurrentSkipListSet<SkinAttr> d = new ConcurrentSkipListSet<>(new ndi());
    private ndk e;

    @Override // com.huawei.skinner.attrentry.SkinAttr
    public void apply(View view, boolean z) {
        csV_(view.getContext(), view);
        if (ndb.e(this.d)) {
            return;
        }
        Iterator<SkinAttr> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().apply(view, z);
        }
    }

    private void csV_(Context context, View view) {
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
        if (this.e == null) {
            this.e = new ndk(view, this.attrName);
        }
        this.e.ctk_(context2, view, resources2, i2, this.d);
    }
}
