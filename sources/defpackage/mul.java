package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;

/* loaded from: classes6.dex */
public class mul {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(WatchFaceConstant.HASHCODE)
    private String f15180a;

    @SerializedName(RecommendConstants.DOWNLOAD_URL)
    private String b;

    @SerializedName("fileType")
    private String c;

    @SerializedName("algorithmName")
    private String d;

    @SerializedName(ContentResource.FILE_NAME)
    private String e;

    @SerializedName("size")
    private String g;

    public String a() {
        return this.b;
    }

    public String d() {
        return this.g;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(255);
        stringBuffer.append("BriefFileInfo{fileName=");
        stringBuffer.append(this.e);
        stringBuffer.append(", fileType=").append(this.c);
        stringBuffer.append(", size=").append(this.g);
        stringBuffer.append(", algorithmName=").append(this.d);
        stringBuffer.append(", hashCode=").append(this.f15180a);
        stringBuffer.append(", downloadUrl=").append(this.b);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
