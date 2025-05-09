package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class dmm {
    private final int c;

    public dmm(int i) {
        this.c = i;
    }

    public static Map<Integer, ResourceResultInfo> d(Map<Integer, ResourceResultInfo> map, Map<String, ?> map2, int i) {
        return new dmm(i).c(map, map2);
    }

    private Map<Integer, ResourceResultInfo> c(Map<Integer, ResourceResultInfo> map, Map<String, ?> map2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        HashMap hashMap = new HashMap();
        if (map == null) {
            LogUtil.c("MarketingResourceFilter", "filterResourceByEvent skipped, ResultInfoMap is null in filterResource.");
            return hashMap;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, ResourceResultInfo>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().getKey().intValue();
            arrayList.add(Integer.valueOf(intValue));
            hashMap.put(Integer.valueOf(intValue), c(intValue, map.get(Integer.valueOf(intValue)), map2));
        }
        LogUtil.c("MarketingResourceFilter", "filterResourceByEvent finished, positionIdList: " + arrayList + ", eventType: " + this.c + ", time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return hashMap;
    }

    public Map<Integer, ResourceResultInfo> e(Map<Integer, ResourceResultInfo> map) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        HashMap hashMap = new HashMap();
        if (map == null) {
            LogUtil.c("MarketingResourceFilter", "filterInvalidRsc skipped, ResultInfoMap is null in filterResource.");
            return hashMap;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, ResourceResultInfo>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().getKey().intValue();
            arrayList.add(Integer.valueOf(intValue));
            hashMap.put(Integer.valueOf(intValue), a(intValue, map.get(Integer.valueOf(intValue))));
        }
        LogUtil.c("MarketingResourceFilter", "filterInvalidRsc finished, positionIdList: " + arrayList + ", time cost:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return hashMap;
    }

    private ResourceResultInfo c(int i, ResourceResultInfo resourceResultInfo, Map<String, ?> map) {
        if (resourceResultInfo == null || resourceResultInfo.getTotalNum() == 0 || koq.b(resourceResultInfo.getResources())) {
            LogUtil.c("MarketingResourceFilter", "filter single position skipped, positionId: " + i + ", eventType:" + this.c + ", triggerEventParams: " + map + ", reason: resourceResultInfo is null or no resource");
            return null;
        }
        ArrayList<ResourceBriefInfo> arrayList = new ArrayList(resourceResultInfo.getResources());
        Collections.sort(arrayList, new Comparator<ResourceBriefInfo>() { // from class: dmm.2
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
                return resourceBriefInfo2.getPriority() - resourceBriefInfo.getPriority();
            }
        });
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(arrayList)) {
            LogUtil.a("MarketingResourceFilter", "filterByEventInner skip, resourceBriefInfoList is null or no resource. PositionId: ", Integer.valueOf(i));
            return null;
        }
        int i2 = 0;
        for (ResourceBriefInfo resourceBriefInfo : arrayList) {
            if (dnb.b(this.c, map, resourceBriefInfo.getMarketingRule().getTriggerEvents())) {
                LogUtil.c("MarketingResourceFilter", "matched resource found: " + resourceBriefInfo.getResourceName());
                arrayList2.add(resourceBriefInfo);
                i2++;
                if (resourceBriefInfo.getContentType() == 1 || resourceBriefInfo.getContentType() == 2 || resourceBriefInfo.getContentType() == 45 || resourceBriefInfo.getContentType() == 50 || resourceBriefInfo.getContentType() == 68 || resourceBriefInfo.getContentType() == 69 || resourceBriefInfo.getContentType() == 70) {
                    LogUtil.c("MarketingResourceFilter", "jump out of loop, due to special content type");
                    break;
                }
            }
        }
        LogUtil.c("MarketingResourceFilter", "filter single position finished, positionId: " + i + ", eventType:" + this.c + ", triggerEventParams: " + map + ", size: " + arrayList.size() + "->" + arrayList2.size());
        return new ResourceResultInfo.Builder().setTotalNum(i2).setResourcesLatestModifyTime(resourceResultInfo.getResourcesLatestModifyTime()).setResources(arrayList2).build();
    }

    private ResourceResultInfo a(int i, ResourceResultInfo resourceResultInfo) {
        if (resourceResultInfo == null || resourceResultInfo.getTotalNum() == 0 || koq.b(resourceResultInfo.getResources())) {
            LogUtil.c("MarketingResourceFilter", "filterInvalidInner skipped, resourceResultInfo is null or no resource. PositionId: ", Integer.valueOf(i));
            return null;
        }
        LogUtil.c("MarketingResourceFilter", "filterInvalidInner, positionId: " + i);
        ArrayList<ResourceBriefInfo> arrayList = new ArrayList(resourceResultInfo.getResources());
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(arrayList)) {
            LogUtil.a("MarketingResourceFilter", "resourceBriefInfoList is null or no resource.PositionId:", Integer.valueOf(i));
            return null;
        }
        ArrayList<ResourceBriefInfo> arrayList3 = new ArrayList();
        HashMap hashMap = new HashMap();
        for (ResourceBriefInfo resourceBriefInfo : arrayList) {
            if (resourceBriefInfo.getMarketingRule() == null || resourceBriefInfo.getMarketingRule().getBackupSwitch() == 0 || TextUtils.isEmpty(resourceBriefInfo.getMarketingRule().getBackupResourceId())) {
                hashMap.put(resourceBriefInfo.getResourceId(), resourceBriefInfo);
            } else {
                arrayList3.add(resourceBriefInfo);
            }
        }
        int i2 = 0;
        for (ResourceBriefInfo resourceBriefInfo2 : arrayList3) {
            if (c(i, resourceBriefInfo2)) {
                arrayList2.add(resourceBriefInfo2);
                String backupResourceId = resourceBriefInfo2.getMarketingRule().getBackupResourceId();
                if (hashMap.containsKey(backupResourceId)) {
                    LogUtil.c("MarketingResourceFilter", "backup resource found, kick it out:" + backupResourceId);
                    hashMap.remove(backupResourceId);
                }
                i2++;
            }
        }
        for (ResourceBriefInfo resourceBriefInfo3 : new ArrayList(hashMap.values())) {
            if (c(i, resourceBriefInfo3)) {
                arrayList2.add(resourceBriefInfo3);
                i2++;
            }
        }
        LogUtil.c("MarketingResourceFilter", "filterInvalidInner finished, positionId: " + i + ", before size: " + arrayList.size() + ", after size: " + arrayList2.size());
        return new ResourceResultInfo.Builder().setTotalNum(i2).setResourcesLatestModifyTime(resourceResultInfo.getResourcesLatestModifyTime()).setResources(arrayList2).build();
    }

    private boolean c(int i, ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            LogUtil.c("MarketingResourceFilter", "resource invalid, null");
            return false;
        }
        if (!new dna(i, resourceBriefInfo).a()) {
            LogUtil.c("MarketingResourceFilter", "resource invalid in time dimension, name:" + resourceBriefInfo.getResourceName());
            return false;
        }
        if (!new dnj().a(resourceBriefInfo)) {
            LogUtil.c("MarketingResourceFilter", "resource invalid in logic dimension, name:" + resourceBriefInfo.getResourceName());
            return false;
        }
        if (new dnf().c(resourceBriefInfo.getMarketingRule().getUserLabels())) {
            return true;
        }
        LogUtil.c("MarketingResourceFilter", "resource invalid in user-label dimension, name:" + resourceBriefInfo.getResourceName());
        return false;
    }
}
