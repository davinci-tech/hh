package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class f extends AbstractMessageEntity {

    @Packed
    private String errMsg;

    @Packed
    private int returnCode;

    @Packed
    private List<e> skuList;

    public int getReturnCode() {
        return this.returnCode;
    }

    public List<ProductInfo> getProductInfoList() {
        ArrayList arrayList = new ArrayList();
        List<e> list = this.skuList;
        if (list == null) {
            return arrayList;
        }
        Iterator<e> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toProductInfo());
        }
        return arrayList;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
