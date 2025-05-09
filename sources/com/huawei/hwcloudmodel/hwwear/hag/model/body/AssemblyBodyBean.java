package com.huawei.hwcloudmodel.hwwear.hag.model.body;

/* loaded from: classes5.dex */
public class AssemblyBodyBean {
    private EndpointBean endpoint;
    private HeaderBean header;
    private InquireBean inquire;

    public void setEndpoint(EndpointBean endpointBean) {
        this.endpoint = endpointBean;
    }

    public void setHeader(HeaderBean headerBean) {
        this.header = headerBean;
    }

    public void setInquire(InquireBean inquireBean) {
        this.inquire = inquireBean;
    }

    public String toString() {
        return "AssemblyBodyBean{endpoint=" + this.endpoint + ", header=" + this.header + ", inquire=" + this.inquire + '}';
    }
}
