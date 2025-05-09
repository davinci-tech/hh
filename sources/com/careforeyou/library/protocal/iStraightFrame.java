package com.careforeyou.library.protocal;

import com.careforeyou.library.bean.CsFatScale;
import com.careforeyou.library.enums.EnumProcessResult;
import defpackage.nq;

/* loaded from: classes2.dex */
public interface iStraightFrame {
    CsFatScale getFatScale();

    EnumProcessResult process(byte[] bArr, String str) throws nq;
}
