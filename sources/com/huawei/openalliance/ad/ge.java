package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.metadata.v3.SlotTemplate;
import com.huawei.openalliance.ad.beans.metadata.v3.SlotTemplateRsp;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface ge {
    String a(String str, String str2);

    List<SlotTemplate> a();

    List<String> a(String str, Map<String, Integer> map);

    void a(List<SlotTemplateRsp> list);

    boolean a(String str, String str2, Map<String, Integer> map);

    List<SlotTemplate> b();

    List<String> b(String str, Map<String, Integer> map);

    void b(List<SlotTemplateRsp> list);

    Map<String, List<String>> d(List<String> list);
}
