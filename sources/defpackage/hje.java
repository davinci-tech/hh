package defpackage;

import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;

/* loaded from: classes4.dex */
public class hje extends BaseGroupDataAdapter.GroupData<HotPathCityInfo> {

    /* renamed from: a, reason: collision with root package name */
    private String f13184a;
    private String d;

    public hje(String str, String str2) {
        this.d = str;
        this.f13184a = str2;
    }

    public HotPathCityInfo d(int i) {
        if (koq.b(getChildList(), i)) {
            return null;
        }
        return getChildList().get(i);
    }

    public String e() {
        return this.f13184a;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter.GroupData
    public String getTitle() {
        return this.f13184a;
    }
}
