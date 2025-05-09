package defpackage;

import android.text.format.DateUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;

/* loaded from: classes6.dex */
public class qon extends BaseGroupDataAdapter.GroupData<qoi> {

    /* renamed from: a, reason: collision with root package name */
    private long f16523a;

    public long e() {
        return this.f16523a;
    }

    public void a(long j) {
        this.f16523a = j;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter.GroupData
    public String getTitle() {
        return DateUtils.formatDateTime(BaseApplication.getContext(), this.f16523a, 36);
    }
}
