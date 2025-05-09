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
public class mvx implements ShareDataHandlerInterface {
    private ConcurrentHashMap<Integer, fdy> e = new ConcurrentHashMap<>();

    mvx() {
        c();
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void updateData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fdy)) {
            return;
        }
        if (mvqVar.d() == null) {
            mvqVar.e(new ArrayList());
        }
        if (!mvqVar.d().contains(Integer.valueOf(shareDataInfo.getId()))) {
            mvqVar.d().add(Integer.valueOf(shareDataInfo.getId()));
        }
        if (CollectionUtils.e(this.e)) {
            LogUtil.h("RecommendHandler", "updateData mRecommendInfoMap isEmpty");
        } else {
            this.e.put(Integer.valueOf(shareDataInfo.getId()), (fdy) shareDataInfo);
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void deleteData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fdy)) {
            return;
        }
        mvqVar.d().remove(Integer.valueOf(shareDataInfo.getId()));
        if (CollectionUtils.e(this.e)) {
            LogUtil.h("RecommendHandler", "deleteData mRecommendInfoMap isEmpty");
        } else if (StringUtils.g(shareDataInfo.getSportTypes())) {
            this.e.remove(Integer.valueOf(shareDataInfo.getId()));
        } else {
            this.e.put(Integer.valueOf(shareDataInfo.getId()), (fdy) shareDataInfo);
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public ShareDataInfo getShareDataById(int i) {
        if (CollectionUtils.e(this.e)) {
            LogUtil.b("RecommendHandler", "recommend map do not load.");
            return null;
        }
        return this.e.get(Integer.valueOf(i));
    }

    private void c() {
        if (CollectionUtils.e(this.e)) {
            String c = mwa.c(mus.i);
            if (StringUtils.g(c)) {
                LogUtil.b("RecommendHandler", "recommend config is empty.");
                this.e = new ConcurrentHashMap<>();
            } else {
                this.e = (ConcurrentHashMap) new GsonBuilder().create().fromJson(CommonUtil.z(c), new TypeToken<ConcurrentHashMap<Integer, fdy>>() { // from class: mvx.4
                }.getType());
            }
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void writeJson() {
        ConcurrentHashMap<Integer, fdy> concurrentHashMap = this.e;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        LogUtil.a("RecommendHandler", "write recommend config json result:", Boolean.valueOf(mwa.b(mus.i, new GsonBuilder().create().toJson(this.e))));
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public List<ShareDataInfo> getShareDataByIdList(List<Integer> list) {
        fdy fdyVar;
        if (CollectionUtils.d(list) || CollectionUtils.e(this.e)) {
            LogUtil.h("RecommendHandler", "getShareDataByIdList idList or mRecommendInfoMap is empty.");
            return Collections.emptyList();
        }
        ArrayList<Integer> arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(list.size());
        for (Integer num : arrayList) {
            if (num != null && (fdyVar = this.e.get(num)) != null) {
                arrayList2.add(fdyVar);
            }
        }
        return arrayList2;
    }
}
