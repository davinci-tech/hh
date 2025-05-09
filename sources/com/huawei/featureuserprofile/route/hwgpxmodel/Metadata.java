package com.huawei.featureuserprofile.route.hwgpxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import java.util.HashMap;
import java.util.Map;

@Tag("metadata")
/* loaded from: classes3.dex */
public class Metadata {
    private Map<String, Object> mMetadatas;

    public <T> void addMetadata(String str, T t) {
        if (this.mMetadatas == null) {
            this.mMetadatas = new HashMap();
        }
        this.mMetadatas.put(str, t);
    }

    public Map<String, Object> getMetadatas() {
        return this.mMetadatas;
    }

    public void setMetadatas(Map<String, Object> map) {
        this.mMetadatas = map;
    }
}
