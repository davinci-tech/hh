package com.huawei.watchface;

import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hms.network.embedded.y5;
import com.huawei.watchface.mvp.model.datatype.FileCreateResponse;
import com.huawei.watchface.mvp.model.datatype.MaterialFileInfo;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class bj extends BaseHttpRequest<FileCreateResponse> {

    /* renamed from: a, reason: collision with root package name */
    private final List<MaterialFileInfo> f10926a;

    public bj(List<MaterialFileInfo> list) {
        this.f10926a = list;
    }

    public String c() {
        return a(WatchFaceHttpUtil.getFileCreateResourceUrl(), d(), e());
    }

    public String d() {
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(WatchFaceConstant.SCENARIO_ID, "ThemeContentFile");
            linkedHashMap.put("materialFileInfoList", this.f10926a);
            JsonElement jsonElement = (JsonElement) GsonHelper.fromJson(GsonHelper.toJson(linkedHashMap), JsonElement.class);
            return jsonElement != null ? jsonElement.getAsJsonObject().toString() : "";
        } catch (Exception e) {
            HwLog.i("FileCreateRequest", "Exception : " + HwLog.printException(e));
            return "";
        }
    }

    protected LinkedHashMap<String, String> e() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(super.getReqHeaders());
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        linkedHashMap.put(WatchFaceHttpUtil.HTTP_CONFIG_JSON, f());
        linkedHashMap.remove("Content-Type");
        linkedHashMap.remove(WatchFaceConstant.CIPHER_VERSION);
        return linkedHashMap;
    }

    private String f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(x2.CONNECT_TIMEOUT, y5.h);
            jSONObject.put("read_timeout", y5.h);
        } catch (JSONException e) {
            HwLog.e("FileCreateRequest", "build reqConfigJson:" + HwLog.printException((Exception) e));
        }
        return jSONObject.toString();
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public FileCreateResponse c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("FileCreateRequest", "dealReceive json is empty");
            return null;
        }
        try {
            return (FileCreateResponse) dx.a().a(str, FileCreateResponse.class);
        } catch (Exception e) {
            HwLog.e("FileCreateRequest", "dealReceive " + HwLog.printException(e));
            return null;
        }
    }
}
