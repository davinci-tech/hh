package defpackage;

import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class dlw {
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0041, code lost:
    
        if (r8 != null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0228, code lost:
    
        if (r8 != null) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<android.view.View> e(android.content.Context r8, int r9, com.huawei.health.marketing.datatype.ResourceBriefInfo r10, androidx.fragment.app.FragmentManager r11) {
        /*
            Method dump skipped, instructions count: 784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dlw.e(android.content.Context, int, com.huawei.health.marketing.datatype.ResourceBriefInfo, androidx.fragment.app.FragmentManager):java.util.List");
    }

    static class b implements ViewTreeVisibilityListener.ViewTreeListenee {

        /* renamed from: a, reason: collision with root package name */
        private Set<String> f11710a = new HashSet();
        private View b;
        private ResourceBriefInfo c;
        private int e;

        public b(View view, int i, ResourceBriefInfo resourceBriefInfo) {
            this.e = i;
            this.c = resourceBriefInfo;
            this.b = view;
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void checkVisibilityAndSetBiEvent() {
            ResourceBriefInfo resourceBriefInfo;
            if (!ViewTreeVisibilityListener.Zx_(this.b) || hasSetBiEvent() || (resourceBriefInfo = this.c) == null) {
                return;
            }
            LogUtil.a("LayoutProvider", "visible to user, mResourceBriefInfo", resourceBriefInfo);
            biEvent();
            this.f11710a.add(this.c.getResourceId());
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public boolean hasSetBiEvent() {
            ResourceBriefInfo resourceBriefInfo = this.c;
            return resourceBriefInfo != null && this.f11710a.contains(resourceBriefInfo.getResourceId());
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void biEvent() {
            ResourceBriefInfo resourceBriefInfo = this.c;
            if (resourceBriefInfo == null) {
                return;
            }
            dlw.d(this.e, resourceBriefInfo);
        }
    }

    public static void d(int i, ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", 1);
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }
}
