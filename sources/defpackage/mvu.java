package defpackage;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class mvu implements ShareDataHandlerInterface {
    private ConcurrentHashMap<Integer, fef> e = new ConcurrentHashMap<>();

    mvu() {
        b();
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void updateData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fef)) {
            return;
        }
        if (mvqVar.c() == null) {
            mvqVar.c(new ArrayList());
        }
        if (!mvqVar.c().contains(Integer.valueOf(shareDataInfo.getId()))) {
            mvqVar.c().add(Integer.valueOf(shareDataInfo.getId()));
        }
        this.e.put(Integer.valueOf(shareDataInfo.getId()), (fef) shareDataInfo);
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void deleteData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fef)) {
            return;
        }
        mvqVar.c().remove(Integer.valueOf(shareDataInfo.getId()));
        if (StringUtils.g(shareDataInfo.getSportTypes())) {
            this.e.remove(Integer.valueOf(shareDataInfo.getId()));
        } else {
            this.e.put(Integer.valueOf(shareDataInfo.getId()), (fef) shareDataInfo);
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public ShareDataInfo getShareDataById(int i) {
        if (this.e.isEmpty()) {
            LogUtil.b("WatermarkHandler", "watermark map do not load.");
            return null;
        }
        return this.e.get(Integer.valueOf(i));
    }

    private void b() {
        if (this.e.isEmpty()) {
            String c = mwa.c(mus.m);
            if (StringUtils.g(c)) {
                LogUtil.b("WatermarkHandler", "watermark config is empty.");
                this.e = new ConcurrentHashMap<>();
            } else {
                this.e = (ConcurrentHashMap) new GsonBuilder().create().fromJson(CommonUtil.z(c), new TypeToken<ConcurrentHashMap<Integer, fef>>() { // from class: mvu.5
                }.getType());
            }
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void writeJson() {
        ConcurrentHashMap<Integer, fef> concurrentHashMap = this.e;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        LogUtil.a("WatermarkHandler", "write watermark config json result:", Boolean.valueOf(mwa.b(mus.m, new GsonBuilder().create().toJson(this.e))));
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public List<ShareDataInfo> getShareDataByIdList(List<Integer> list) {
        fef fefVar;
        if (CollectionUtils.d(list) || CollectionUtils.e(this.e)) {
            LogUtil.h("WatermarkHandler", "getShareDataByIdList idList or mWatermarkInfoMap is empty.");
            return Collections.emptyList();
        }
        ArrayList<Integer> arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(list.size());
        for (Integer num : arrayList) {
            if (num != null && (fefVar = this.e.get(num)) != null) {
                arrayList2.add(fefVar);
            }
        }
        return arrayList2;
    }
}
