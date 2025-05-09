package defpackage;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public final class ic implements FileFilter {
    public final /* synthetic */ ie b;

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }

    public ic(ie ieVar) {
        this.b = ieVar;
    }
}
