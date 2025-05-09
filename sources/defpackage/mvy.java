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
public class mvy implements ShareDataHandlerInterface {
    private ConcurrentHashMap<Integer, fec> d = new ConcurrentHashMap<>();

    mvy() {
        a();
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void updateData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fec)) {
            return;
        }
        if (mvqVar.b() == null) {
            mvqVar.d(new ArrayList());
        }
        if (!mvqVar.b().contains(Integer.valueOf(shareDataInfo.getId()))) {
            mvqVar.b().add(Integer.valueOf(shareDataInfo.getId()));
        }
        this.d.put(Integer.valueOf(shareDataInfo.getId()), (fec) shareDataInfo);
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void deleteData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fec)) {
            return;
        }
        mvqVar.b().remove(Integer.valueOf(shareDataInfo.getId()));
        if (StringUtils.g(shareDataInfo.getSportTypes())) {
            this.d.remove(Integer.valueOf(shareDataInfo.getId()));
        } else {
            this.d.put(Integer.valueOf(shareDataInfo.getId()), (fec) shareDataInfo);
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public ShareDataInfo getShareDataById(int i) {
        if (this.d.isEmpty()) {
            LogUtil.b("BackgroundHandler", "background map do not load.");
            return null;
        }
        return this.d.get(Integer.valueOf(i));
    }

    private void a() {
        ConcurrentHashMap<Integer, fec> concurrentHashMap = this.d;
        if (concurrentHashMap == null || !concurrentHashMap.isEmpty()) {
            return;
        }
        String c = mwa.c(mus.e);
        if (StringUtils.g(c)) {
            LogUtil.b("BackgroundHandler", "background config is empty.");
            this.d = new ConcurrentHashMap<>();
        } else {
            this.d = (ConcurrentHashMap) new GsonBuilder().create().fromJson(CommonUtil.z(c), new TypeToken<ConcurrentHashMap<Integer, fec>>() { // from class: mvy.2
            }.getType());
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void writeJson() {
        ConcurrentHashMap<Integer, fec> concurrentHashMap = this.d;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        LogUtil.a("BackgroundHandler", "write background config json result:", Boolean.valueOf(mwa.b(mus.e, new GsonBuilder().create().toJson(this.d))));
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public List<ShareDataInfo> getShareDataByIdList(List<Integer> list) {
        fec fecVar;
        if (CollectionUtils.d(list) || CollectionUtils.e(this.d)) {
            LogUtil.h("BackgroundHandler", "getShareDataByIdList idList or mBackgroundInfoMap is empty.");
            return Collections.emptyList();
        }
        ArrayList<Integer> arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(list.size());
        for (Integer num : arrayList) {
            if (num != null && (fecVar = this.d.get(num)) != null) {
                arrayList2.add(fecVar);
            }
        }
        return arrayList2;
    }
}
