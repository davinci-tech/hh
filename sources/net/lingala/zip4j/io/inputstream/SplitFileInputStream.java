package net.lingala.zip4j.io.inputstream;

import defpackage.usm;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes10.dex */
public abstract class SplitFileInputStream extends InputStream {
    public abstract void prepareExtractionForFileHeader(usm usmVar) throws IOException;
}
