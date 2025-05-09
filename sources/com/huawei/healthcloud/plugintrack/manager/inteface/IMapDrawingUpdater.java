package com.huawei.healthcloud.plugintrack.manager.inteface;

import defpackage.hiy;
import defpackage.hjd;
import java.util.List;

/* loaded from: classes4.dex */
public interface IMapDrawingUpdater {
    void addEndMarker(hjd hjdVar);

    void addStartMarker(hjd hjdVar);

    void drawFirstLocation(hjd hjdVar);

    void drawLineToMap(List<hiy> list);

    void drawPauseLine(hjd hjdVar, hjd hjdVar2);

    void drawRecoveryLine(hjd hjdVar, hjd hjdVar2);

    void forceDrawLineToMap();

    void pauseSportClear();

    void releaseMap();

    void updateCpMarker(int[] iArr);
}
