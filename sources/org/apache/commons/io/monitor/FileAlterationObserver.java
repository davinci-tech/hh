package org.apache.commons.io.monitor;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.TrueFileFilter;

/* loaded from: classes10.dex */
public class FileAlterationObserver implements Serializable {
    private static final long serialVersionUID = 1185122225658782848L;
    private final Comparator<File> comparator;
    private final transient FileFilter fileFilter;
    private final transient List<FileAlterationListener> listeners;
    private final FileEntry rootEntry;

    public void destroy() throws Exception {
    }

    /* renamed from: org.apache.commons.io.monitor.FileAlterationObserver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$io$IOCase;

        static {
            int[] iArr = new int[IOCase.values().length];
            $SwitchMap$org$apache$commons$io$IOCase = iArr;
            try {
                iArr[IOCase.SYSTEM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$io$IOCase[IOCase.INSENSITIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static Comparator<File> toComparator(IOCase iOCase) {
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$io$IOCase[IOCase.value(iOCase, IOCase.SYSTEM).ordinal()];
        if (i == 1) {
            return NameFileComparator.NAME_SYSTEM_COMPARATOR;
        }
        if (i == 2) {
            return NameFileComparator.NAME_INSENSITIVE_COMPARATOR;
        }
        return NameFileComparator.NAME_COMPARATOR;
    }

    public FileAlterationObserver(File file) {
        this(file, (FileFilter) null);
    }

    public FileAlterationObserver(File file, FileFilter fileFilter) {
        this(file, fileFilter, (IOCase) null);
    }

    public FileAlterationObserver(File file, FileFilter fileFilter, IOCase iOCase) {
        this(new FileEntry(file), fileFilter, iOCase);
    }

    private FileAlterationObserver(FileEntry fileEntry, FileFilter fileFilter, Comparator<File> comparator) {
        this.listeners = new CopyOnWriteArrayList();
        Objects.requireNonNull(fileEntry, "rootEntry");
        Objects.requireNonNull(fileEntry.getFile(), "rootEntry.getFile()");
        this.rootEntry = fileEntry;
        this.fileFilter = fileFilter == null ? TrueFileFilter.INSTANCE : fileFilter;
        this.comparator = (Comparator) Objects.requireNonNull(comparator, "comparator");
    }

    protected FileAlterationObserver(FileEntry fileEntry, FileFilter fileFilter, IOCase iOCase) {
        this(fileEntry, fileFilter, toComparator(iOCase));
    }

    public FileAlterationObserver(String str) {
        this(new File(str));
    }

    public FileAlterationObserver(String str, FileFilter fileFilter) {
        this(new File(str), fileFilter);
    }

    public FileAlterationObserver(String str, FileFilter fileFilter, IOCase iOCase) {
        this(new File(str), fileFilter, iOCase);
    }

    public void addListener(FileAlterationListener fileAlterationListener) {
        if (fileAlterationListener != null) {
            this.listeners.add(fileAlterationListener);
        }
    }

    private void checkAndFire(FileEntry fileEntry, FileEntry[] fileEntryArr, File[] fileArr) {
        FileEntry[] fileEntryArr2 = fileArr.length > 0 ? new FileEntry[fileArr.length] : FileEntry.EMPTY_FILE_ENTRY_ARRAY;
        int i = 0;
        for (FileEntry fileEntry2 : fileEntryArr) {
            while (i < fileArr.length && this.comparator.compare(fileEntry2.getFile(), fileArr[i]) > 0) {
                FileEntry m1215xd1c1b90a = m1215xd1c1b90a(fileEntry, fileArr[i]);
                fileEntryArr2[i] = m1215xd1c1b90a;
                fireOnCreate(m1215xd1c1b90a);
                i++;
            }
            if (i < fileArr.length && this.comparator.compare(fileEntry2.getFile(), fileArr[i]) == 0) {
                fireOnChange(fileEntry2, fileArr[i]);
                checkAndFire(fileEntry2, fileEntry2.getChildren(), listFiles(fileArr[i]));
                fileEntryArr2[i] = fileEntry2;
                i++;
            } else {
                checkAndFire(fileEntry2, fileEntry2.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
                fireOnDelete(fileEntry2);
            }
        }
        while (i < fileArr.length) {
            FileEntry m1215xd1c1b90a2 = m1215xd1c1b90a(fileEntry, fileArr[i]);
            fileEntryArr2[i] = m1215xd1c1b90a2;
            fireOnCreate(m1215xd1c1b90a2);
            i++;
        }
        fileEntry.setChildren(fileEntryArr2);
    }

    public void checkAndNotify() {
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.this.m1213x2d975733((FileAlterationListener) obj);
            }
        });
        File file = this.rootEntry.getFile();
        if (file.exists()) {
            FileEntry fileEntry = this.rootEntry;
            checkAndFire(fileEntry, fileEntry.getChildren(), listFiles(file));
        } else if (this.rootEntry.isExists()) {
            FileEntry fileEntry2 = this.rootEntry;
            checkAndFire(fileEntry2, fileEntry2.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
        }
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.this.m1214x47b2d5d2((FileAlterationListener) obj);
            }
        });
    }

    /* renamed from: lambda$checkAndNotify$0$org-apache-commons-io-monitor-FileAlterationObserver, reason: not valid java name */
    /* synthetic */ void m1213x2d975733(FileAlterationListener fileAlterationListener) {
        fileAlterationListener.onStart(this);
    }

    /* renamed from: lambda$checkAndNotify$1$org-apache-commons-io-monitor-FileAlterationObserver, reason: not valid java name */
    /* synthetic */ void m1214x47b2d5d2(FileAlterationListener fileAlterationListener) {
        fileAlterationListener.onStop(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createFileEntry, reason: merged with bridge method [inline-methods] */
    public FileEntry m1215xd1c1b90a(FileEntry fileEntry, File file) {
        FileEntry newChildInstance = fileEntry.newChildInstance(file);
        newChildInstance.refresh(file);
        newChildInstance.setChildren(listFileEntries(file, newChildInstance));
        return newChildInstance;
    }

    private void fireOnChange(final FileEntry fileEntry, final File file) {
        if (fileEntry.refresh(file)) {
            this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda7
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    FileAlterationObserver.lambda$fireOnChange$2(FileEntry.this, file, (FileAlterationListener) obj);
                }
            });
        }
    }

    static /* synthetic */ void lambda$fireOnChange$2(FileEntry fileEntry, File file, FileAlterationListener fileAlterationListener) {
        if (fileEntry.isDirectory()) {
            fileAlterationListener.onDirectoryChange(file);
        } else {
            fileAlterationListener.onFileChange(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireOnCreate(final FileEntry fileEntry) {
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.lambda$fireOnCreate$3(FileEntry.this, (FileAlterationListener) obj);
            }
        });
        Stream.of((Object[]) fileEntry.getChildren()).forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.this.fireOnCreate((FileEntry) obj);
            }
        });
    }

    static /* synthetic */ void lambda$fireOnCreate$3(FileEntry fileEntry, FileAlterationListener fileAlterationListener) {
        if (fileEntry.isDirectory()) {
            fileAlterationListener.onDirectoryCreate(fileEntry.getFile());
        } else {
            fileAlterationListener.onFileCreate(fileEntry.getFile());
        }
    }

    private void fireOnDelete(final FileEntry fileEntry) {
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.lambda$fireOnDelete$4(FileEntry.this, (FileAlterationListener) obj);
            }
        });
    }

    static /* synthetic */ void lambda$fireOnDelete$4(FileEntry fileEntry, FileAlterationListener fileAlterationListener) {
        if (fileEntry.isDirectory()) {
            fileAlterationListener.onDirectoryDelete(fileEntry.getFile());
        } else {
            fileAlterationListener.onFileDelete(fileEntry.getFile());
        }
    }

    public File getDirectory() {
        return this.rootEntry.getFile();
    }

    public FileFilter getFileFilter() {
        return this.fileFilter;
    }

    public Iterable<FileAlterationListener> getListeners() {
        return new ArrayList(this.listeners);
    }

    public void initialize() throws Exception {
        FileEntry fileEntry = this.rootEntry;
        fileEntry.refresh(fileEntry.getFile());
        FileEntry fileEntry2 = this.rootEntry;
        fileEntry2.setChildren(listFileEntries(fileEntry2.getFile(), this.rootEntry));
    }

    static /* synthetic */ FileEntry[] lambda$listFileEntries$6(int i) {
        return new FileEntry[i];
    }

    private FileEntry[] listFileEntries(File file, final FileEntry fileEntry) {
        return (FileEntry[]) Stream.of((Object[]) listFiles(file)).map(new Function() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FileAlterationObserver.this.m1215xd1c1b90a(fileEntry, (File) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return FileAlterationObserver.lambda$listFileEntries$6(i);
            }
        });
    }

    private File[] listFiles(File file) {
        return file.isDirectory() ? sort(file.listFiles(this.fileFilter)) : FileUtils.EMPTY_FILE_ARRAY;
    }

    public void removeListener(final FileAlterationListener fileAlterationListener) {
        if (fileAlterationListener != null) {
            List<FileAlterationListener> list = this.listeners;
            Objects.requireNonNull(fileAlterationListener);
            list.removeIf(new Predicate() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda8
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = FileAlterationListener.this.equals((FileAlterationListener) obj);
                    return equals;
                }
            });
        }
    }

    private File[] sort(File[] fileArr) {
        if (fileArr == null) {
            return FileUtils.EMPTY_FILE_ARRAY;
        }
        if (fileArr.length > 1) {
            Arrays.sort(fileArr, this.comparator);
        }
        return fileArr;
    }

    public String toString() {
        return getClass().getSimpleName() + "[file='" + getDirectory().getPath() + "', " + this.fileFilter.toString() + ", listeners=" + this.listeners.size() + "]";
    }
}
