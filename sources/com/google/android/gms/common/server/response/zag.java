package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

/* loaded from: classes8.dex */
final class zag implements FastParser.zaa<BigInteger> {
    zag() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ BigInteger zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        BigInteger zaf;
        zaf = fastParser.zaf(bufferedReader);
        return zaf;
    }
}
