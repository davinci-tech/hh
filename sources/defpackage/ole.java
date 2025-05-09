package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class ole implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("deviceSource")
    private int f15761a;

    @SerializedName("pageNum")
    private int b;

    @SerializedName("lang")
    private String c;

    @SerializedName("iVersion")
    private String d;

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int e;

    @SerializedName("topicId")
    private String i;

    @SerializedName("ts")
    private long j;

    public void a(String str) {
        this.i = str;
    }

    public void a(int i) {
        this.f15761a = i;
    }

    public void e(int i) {
        this.b = i;
    }

    public void e(long j) {
        this.j = j;
    }

    public void e(String str) {
        this.d = str;
    }

    public void d(int i) {
        this.e = i;
    }

    public void d(String str) {
        this.c = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/getWorkoutsByTopicId";
    }
}
