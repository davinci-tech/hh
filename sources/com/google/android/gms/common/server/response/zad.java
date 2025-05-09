package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

/* loaded from: classes8.dex */
final class zad implements FastParser.zaa<Double> {
    zad() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Double zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        double zah;
        zah = fastParser.zah(bufferedReader);
        return Double.valueOf(zah);
    }
}
