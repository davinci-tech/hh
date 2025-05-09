package com.huawei.healthcloud.plugintrack.golf.cloud;

import java.util.Observer;

/* loaded from: classes8.dex */
public class GolfDownloadObserver implements Observer {
    private static final String TAG = "GolfDownloadObserver";
    boolean isAnon;

    public GolfDownloadObserver(boolean z) {
        this.isAnon = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    @Override // java.util.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(java.util.Observable r4, java.lang.Object r5) {
        /*
            r3 = this;
            boolean r5 = r4 instanceof com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadObservable
            java.lang.String r0 = "GolfDownloadObserver"
            if (r5 == 0) goto L20
            com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadObservable r4 = (com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadObservable) r4
            com.huawei.healthcloud.plugintrack.golf.cloud.beans.HandshakeInfo r4 = r4.getData()
            if (r4 == 0) goto L17
            com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseMapInfo r5 = r4.getCourseMapInfo()
            int r4 = r4.getMessageId()
            goto L22
        L17:
            java.lang.String r4 = "handShakeInfo is null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r4)
        L20:
            r4 = -1
            r5 = 0
        L22:
            if (r5 != 0) goto L2e
            java.lang.String r4 = " mapInfo is null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r4)
            return
        L2e:
            if (r4 >= 0) goto L44
            int r4 = r5.getCourseId()
            com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy r0 = com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy.getInstance()
            long r1 = (long) r4
            boolean r4 = r3.isAnon
            int r4 = com.huawei.healthcloud.plugintrack.golf.device.CloudHelper.getVersion(r1, r4)
            r1 = 0
            r0.sendPushCourseMapHandShake(r5, r4, r1)
            goto L4b
        L44:
            com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy r0 = com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy.getInstance()
            r0.sendMap(r5, r4)
        L4b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadObserver.update(java.util.Observable, java.lang.Object):void");
    }
}
