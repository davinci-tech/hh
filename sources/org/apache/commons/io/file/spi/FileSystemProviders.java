package org.apache.commons.io.file.spi;

import com.huawei.operation.ble.BleConstants;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: classes10.dex */
public class FileSystemProviders {
    private static final FileSystemProviders INSTALLED = new FileSystemProviders(FileSystemProvider.installedProviders());
    private final List<FileSystemProvider> providers;

    public static FileSystemProvider getFileSystemProvider(Path path) {
        return ((Path) Objects.requireNonNull(path, BleConstants.KEY_PATH)).getFileSystem().provider();
    }

    public static FileSystemProviders installed() {
        return INSTALLED;
    }

    private FileSystemProviders(List<FileSystemProvider> list) {
        this.providers = list == null ? Collections.emptyList() : list;
    }

    public FileSystemProvider getFileSystemProvider(final String str) {
        Objects.requireNonNull(str, "scheme");
        if (str.equalsIgnoreCase("file")) {
            return FileSystems.getDefault().provider();
        }
        return this.providers.stream().filter(new Predicate() { // from class: org.apache.commons.io.file.spi.FileSystemProviders$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equalsIgnoreCase;
                equalsIgnoreCase = ((FileSystemProvider) obj).getScheme().equalsIgnoreCase(str);
                return equalsIgnoreCase;
            }
        }).findFirst().orElse(null);
    }

    public FileSystemProvider getFileSystemProvider(URI uri) {
        return getFileSystemProvider(((URI) Objects.requireNonNull(uri, "uri")).getScheme());
    }

    public FileSystemProvider getFileSystemProvider(URL url) {
        return getFileSystemProvider(((URL) Objects.requireNonNull(url, "url")).getProtocol());
    }
}
