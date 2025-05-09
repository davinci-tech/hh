package com.huawei.health.h5pro.jsbridge.system.shareplus;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class SharePlusParam {

    @SerializedName(KnitFragment.KEY_EXTRA)
    public Map<String, Object> d;

    @SerializedName("shareBiMap")
    public Map<String, Object> h;

    @SerializedName("type")
    public String l = "";

    @SerializedName(Constant.TEXT)
    public String o = "";

    @SerializedName("base64")
    public String c = "";

    @SerializedName("uri")
    public String k = "";

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(ContentResource.FILE_NAME)
    public String f2428a = "";

    @SerializedName(Constants.BI_MODULE_ID)
    public String f = "";

    @SerializedName("filePath")
    public String e = "";

    @SerializedName("mineType")
    public String g = "";

    @SerializedName("mimeType")
    public String i = "";

    @SerializedName("distList")
    public List<String> b = null;

    @SerializedName("isReport")
    public boolean j = false;

    public void setUri(String str) {
        this.k = str;
    }

    public void setType(String str) {
        this.l = str;
    }

    public void setText(String str) {
        this.o = str;
    }

    public void setShareBiMap(Map<String, Object> map) {
        this.h = map;
    }

    public void setModuleId(String str) {
        this.f = str;
    }

    public void setMineType(String str) {
        this.g = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.i = str;
    }

    public void setMimeType(String str) {
        this.i = str;
    }

    public void setIsReport(boolean z) {
        this.j = z;
    }

    public void setFilePath(String str) {
        this.e = str;
    }

    public void setFileName(String str) {
        this.f2428a = str;
    }

    public void setExtra(Map<String, Object> map) {
        this.d = map;
    }

    public void setDistList(List<String> list) {
        this.b = list;
    }

    public void setBase64(String str) {
        this.c = str;
    }

    public boolean isReport() {
        return this.j;
    }

    public String getUri() {
        return this.k;
    }

    public String getType() {
        return this.l;
    }

    public String getText() {
        return this.o;
    }

    public Map<String, Object> getShareBiMap() {
        return this.h;
    }

    public String getModuleId() {
        return this.f;
    }

    public String getMimeType() {
        return TextUtils.isEmpty(this.i) ? this.g : this.i;
    }

    public String getFilePath() {
        return this.e;
    }

    public String getFileName() {
        return this.f2428a;
    }

    public Map<String, Object> getExtra() {
        return this.d;
    }

    public List<String> getDistList() {
        return this.b;
    }

    public String getBase64() {
        return this.c;
    }
}
