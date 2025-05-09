package com.huawei.ui.main.stories.template;

import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ui.commonui.popupview.PopViewList;
import defpackage.koq;
import defpackage.nsn;
import defpackage.ryf;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public abstract class PopMenuManager implements IMenuItemManager {
    private static String TAG = "PopMenuManager";
    private PopViewList mPopViewList;

    protected abstract View getTitleBar();

    ArrayList<String> getItemNameList(List<ryf> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (ryf ryfVar : list) {
            if (ryfVar != null) {
                arrayList.add(ryfVar.b());
            }
        }
        return arrayList;
    }

    public void showPopMenu() {
        if (getTitleBar() == null || getTitleBar().getContext() == null) {
            LogUtil.e(TAG, "getPopViewList getTitleBar or getContext is null");
            return;
        }
        final List<ryf> menuItemList = getMenuItemList();
        ArrayList<String> itemNameList = getItemNameList(menuItemList);
        if (koq.b(itemNameList) || koq.b(menuItemList)) {
            LogUtil.e(TAG, "showPopMenu popMenuList or menuItemList is null");
            return;
        }
        PopViewList popViewList = new PopViewList(BaseApplication.e(), getTitleBar(), itemNameList);
        this.mPopViewList = popViewList;
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: com.huawei.ui.main.stories.template.PopMenuManager.1
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public void setOnClick(int i) {
                if (nsn.a(800)) {
                    return;
                }
                if (koq.b(menuItemList, i)) {
                    LogUtil.e(PopMenuManager.TAG, "showPopMenu isOutOfBounds");
                } else if (menuItemList.get(i) == null || ((ryf) menuItemList.get(i)).d() == null) {
                    LogUtil.e(PopMenuManager.TAG, "showPopMenu get(position) is null");
                } else {
                    ((ryf) menuItemList.get(i)).d().onItemClick();
                }
            }
        });
    }

    public void dismissPopMenu() {
        PopViewList popViewList = this.mPopViewList;
        if (popViewList != null) {
            popViewList.b();
        }
    }
}
