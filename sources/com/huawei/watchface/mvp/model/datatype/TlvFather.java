package com.huawei.watchface.mvp.model.datatype;

import com.huawei.watchface.dm;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class TlvFather {
    private static final int DEFAULT_TLV_ARRAY_LENGTH = 10;
    private List<TlvFather> tlvFatherList;
    private List<Tlv> tlvList;

    public TlvFather(List<Tlv> list, List<TlvFather> list2) {
        this.tlvList = (List) dm.a(list);
        this.tlvFatherList = (List) dm.a(list2);
    }

    public TlvFather() {
        this.tlvList = new ArrayList(10);
        this.tlvFatherList = new ArrayList(10);
    }

    public List<Tlv> getTlvList() {
        return (List) dm.a(this.tlvList);
    }

    public void setTlvList(List<Tlv> list) {
        this.tlvList = (List) dm.a(list);
    }

    public List<TlvFather> getTlvFatherList() {
        return (List) dm.a(this.tlvFatherList);
    }

    public void setTlvFatherList(List<TlvFather> list) {
        this.tlvFatherList = (List) dm.a(list);
    }
}
