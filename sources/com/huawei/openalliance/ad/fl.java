package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.v3.SlotTemplate;
import com.huawei.openalliance.ad.beans.metadata.v3.SlotTemplateRsp;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateConfig;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class fl extends ep implements ge {
    private static final byte[] c = new byte[0];

    @Override // com.huawei.openalliance.ad.ge
    public Map<String, List<String>> d(List<String> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return d();
        }
        HashMap hashMap = new HashMap();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                List<String> a2 = a("SELECT templateId FROM TemplateStyleRecord WHERE slotId = \"" + str + "\";", (String[]) null, "templateId");
                if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
                    hashMap.put(str, a2);
                }
            }
        }
        return hashMap;
    }

    public boolean c(String str, Map<String, Integer> map) {
        ho.a("TemplateConfigDao", "tag: %s, unsupportedTags： %s", str, map);
        Map map2 = (Map) com.huawei.openalliance.ad.utils.be.b(str, Map.class, Integer.class);
        if (com.huawei.openalliance.ad.utils.bl.a(map2)) {
            ho.a("TemplateConfigDao", "tag is null");
            return false;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ho.a("TemplateConfigDao", "unsupportedTag: %s, templateTags: %s", entry, map2);
            Integer value = entry.getValue();
            if (value != null && value.intValue() >= -1 && value.intValue() <= 9) {
                if (value.intValue() == -1) {
                    return true;
                }
                if (map2.containsKey(entry.getKey()) && value.equals(map2.get(entry.getKey()))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.ge
    public void b(List<SlotTemplateRsp> list) {
        a(list, true);
    }

    @Override // com.huawei.openalliance.ad.ge
    public List<String> b(String str, Map<String, Integer> map) {
        List<String> a2 = a(str, map, 1);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return null;
        }
        return a2;
    }

    public List<TemplateStyleRecord> b(String str) {
        return a(TemplateStyleRecord.class, null, fi.TEMPLATE_STYLE_H5_BY_SLOTID, new String[]{str}, null, null);
    }

    @Override // com.huawei.openalliance.ad.ge
    public List<SlotTemplate> b() {
        return a(fi.TEMPLATE_STYLE_H5, (String[]) null);
    }

    @Override // com.huawei.openalliance.ad.ge
    public boolean a(String str, String str2, Map<String, Integer> map) {
        if (com.huawei.openalliance.ad.utils.bl.a(map)) {
            return false;
        }
        TemplateStyleRecord b = b(str, str2);
        if (b != null && !c(b.e(), map)) {
            return false;
        }
        ho.b("TemplateConfigDao", "not support");
        return true;
    }

    @Override // com.huawei.openalliance.ad.ge
    public void a(List<SlotTemplateRsp> list) {
        a(list, false);
    }

    @Override // com.huawei.openalliance.ad.ge
    public List<String> a(String str, Map<String, Integer> map) {
        List<String> a2 = a(str, map, 0);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return null;
        }
        return a2;
    }

    public List<TemplateStyleRecord> a(String str) {
        return a(TemplateStyleRecord.class, null, fi.TEMPLATE_STYLE_NATIVE_BY_SLOTID, new String[]{str}, null, null);
    }

    @Override // com.huawei.openalliance.ad.ge
    public List<SlotTemplate> a() {
        return a((fi) null, (String[]) null);
    }

    @Override // com.huawei.openalliance.ad.ge
    public String a(String str, String str2) {
        TemplateStyleRecord b = b(str, str2);
        if (b != null) {
            return b.c();
        }
        return null;
    }

    private void e(List<String> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            c(it.next());
        }
    }

    private Map<String, List<String>> d() {
        ho.b("TemplateConfigDao", "queryAllSlotTemplateIds");
        List<TemplateStyleRecord> a2 = a(TemplateStyleRecord.class, null, null, null, null, null);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (TemplateStyleRecord templateStyleRecord : a2) {
            if (templateStyleRecord != null) {
                String a3 = templateStyleRecord.a();
                List list = (List) hashMap.get(a3);
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(templateStyleRecord.b());
                hashMap.put(a3, list);
            }
        }
        return hashMap;
    }

    private void c(String str, String str2) {
        a(TemplateStyleRecord.class, fi.TEMPLATE_STYLE_BY_SLOTID_AND_TEMPLATEID, new String[]{str, str2});
    }

    private void c(String str) {
        a(TemplateStyleRecord.class, fi.TEMPLATE_STYLE_BY_TEMPLATEID, new String[]{str});
    }

    private List<String> c() {
        try {
            if (e.b() != null) {
                return e.b().a((Bundle) null);
            }
        } catch (Throwable th) {
            ho.b("TemplateConfigDao", "get blackTptIdList err: %s", th.getClass().getSimpleName());
        }
        return null;
    }

    private TemplateStyleRecord b(String str, String str2) {
        List a2 = a(TemplateStyleRecord.class, null, fi.TEMPLATE_STYLE_BY_SLOTID_AND_TEMPLATEID, new String[]{str, str2}, null, null);
        if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return (TemplateStyleRecord) a2.get(0);
        }
        ho.a("TemplateConfigDao", "no style");
        return null;
    }

    private void a(List<SlotTemplateRsp> list, boolean z) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            ho.b("TemplateConfigDao", "slotTemplateRsps is empty");
            return;
        }
        synchronized (c) {
            List<String> c2 = c();
            ho.a("TemplateConfigDao", "blackIdList = %s", c2);
            for (SlotTemplateRsp slotTemplateRsp : list) {
                if (slotTemplateRsp != null && !com.huawei.openalliance.ad.utils.bg.a(slotTemplateRsp.c())) {
                    a(slotTemplateRsp.a(), slotTemplateRsp.c());
                }
                if (slotTemplateRsp != null && !com.huawei.openalliance.ad.utils.bg.a(slotTemplateRsp.b())) {
                    for (TemplateConfig templateConfig : slotTemplateRsp.b()) {
                        if (templateConfig != null && !TextUtils.isEmpty(templateConfig.a()) && (com.huawei.openalliance.ad.utils.bg.a(c2) || !c2.contains(templateConfig.a()))) {
                            a(z ? new TemplateStyleRecord(slotTemplateRsp.a(), templateConfig, 1) : new TemplateStyleRecord(slotTemplateRsp.a(), templateConfig));
                        }
                    }
                }
            }
            e(c2);
        }
    }

    private void a(String str, List<String> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            c(str, it.next());
        }
    }

    private void a(TemplateStyleRecord templateStyleRecord) {
        if (b(templateStyleRecord.a(), templateStyleRecord.b()) != null) {
            a(TemplateStyleRecord.class, templateStyleRecord.d(this.f6846a), fi.TEMPLATE_STYLE_BY_SLOTID_AND_TEMPLATEID, new String[]{templateStyleRecord.a(), templateStyleRecord.b()});
        } else {
            a(TemplateStyleRecord.class, templateStyleRecord.d(this.f6846a));
        }
    }

    private List<String> a(String str, Map<String, Integer> map, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (com.huawei.openalliance.ad.utils.bl.a(map)) {
            ho.a("TemplateConfigDao", "unsupportedTags is empty");
            return a("SELECT templateId FROM TemplateStyleRecord WHERE slotId = \"" + str + "\" and tmpType = " + i + ";", (String[]) null, "templateId");
        }
        List<TemplateStyleRecord> b = com.huawei.openalliance.ad.utils.bd.a(i) ? b(str) : a(str);
        if (com.huawei.openalliance.ad.utils.bg.a(b)) {
            return arrayList;
        }
        for (TemplateStyleRecord templateStyleRecord : b) {
            if (templateStyleRecord != null && !c(templateStyleRecord.e(), map) && !TextUtils.isEmpty(templateStyleRecord.b())) {
                arrayList.add(templateStyleRecord.b());
            }
        }
        return arrayList;
    }

    private List<SlotTemplate> a(fi fiVar, String[] strArr) {
        List<TemplateStyleRecord> a2 = a(TemplateStyleRecord.class, null, fiVar, strArr, null, null);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return new ArrayList();
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (TemplateStyleRecord templateStyleRecord : a2) {
            if (templateStyleRecord != null) {
                String a3 = templateStyleRecord.a();
                List list = (List) concurrentHashMap.get(a3);
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(new TemplateConfig(templateStyleRecord.b(), templateStyleRecord.d()));
                concurrentHashMap.put(a3, list);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : concurrentHashMap.entrySet()) {
            arrayList.add(new SlotTemplate((String) entry.getKey(), (List) entry.getValue()));
        }
        return arrayList;
    }

    public static ge a(Context context) {
        return new fl(context);
    }

    protected fl(Context context) {
        super(context);
    }
}
