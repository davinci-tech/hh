package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.request.ColumnProvider;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.marketing.request.CustomConfigValue;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
class dlv implements ILayoutGenerator {
    dlv() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null) {
            LogUtil.c("GridLayoutGenerator", "context is null when getGridTemplate.");
            return null;
        }
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.h("GridLayoutGenerator", "getGridTemplate filterTemplate is null");
            return null;
        }
        if (c instanceof GridTemplate) {
            GridTemplate gridTemplate = (GridTemplate) c;
            List<SingleGridContent> gridContents = gridTemplate.getGridContents();
            if (koq.b(gridContents)) {
                LogUtil.c("GridLayoutGenerator", "singleGridContentList is empty.");
                return null;
            }
            if (resourceBriefInfo.getContentType() == 47) {
                List<CustomConfigValue> favoriteList = ColumnProvider.getInstance(BaseApplication.e()).getFavoriteList();
                List<SingleGridContent> functionMenuFinallyData = ColumnRequestUtils.getFunctionMenuFinallyData(gridContents, favoriteList);
                LogUtil.c("GridLayoutGenerator", "getGridTemplate jsonStr: ", lql.b(favoriteList));
                gridTemplate.setGridContents(functionMenuFinallyData);
            }
        }
        ColumnLinearLayout columnLinearLayout = new ColumnLinearLayout(BaseApplication.e());
        columnLinearLayout.e(i, resourceBriefInfo, c);
        return columnLinearLayout;
    }
}
