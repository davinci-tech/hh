package com.huawei.haf.common.os;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public final class FileVisitUtils {

    public interface VisitOperation {
        Path getRootPath();

        void visit(Path path, BasicFileAttributes basicFileAttributes, boolean z, int i, long j);

        void visitRootBegin();

        void visitRootEnd(long j);
    }

    private FileVisitUtils() {
    }

    public static long d(VisitOperation visitOperation) throws IOException {
        DirectoryInfoVisitor directoryInfoVisitor = new DirectoryInfoVisitor(visitOperation);
        Files.walkFileTree(visitOperation.getRootPath(), directoryInfoVisitor);
        return directoryInfoVisitor.d();
    }

    static long e(Path path) throws IOException {
        SizeDirectoryInfo sizeDirectoryInfo = new SizeDirectoryInfo();
        Files.walkFileTree(path, sizeDirectoryInfo);
        return sizeDirectoryInfo.b();
    }

    static class SizeDirectoryInfo extends SimpleFileVisitor<Path> {
        private long c;

        private SizeDirectoryInfo() {
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            super.visitFile(path, basicFileAttributes);
            if (!basicFileAttributes.isSymbolicLink()) {
                this.c += basicFileAttributes.size();
            }
            return FileVisitResult.CONTINUE;
        }

        public long b() {
            return this.c;
        }
    }

    static class DirectoryNode {
        long c;
        final BasicFileAttributes e;

        DirectoryNode(BasicFileAttributes basicFileAttributes) {
            this.e = basicFileAttributes;
        }
    }

    static class DirectoryInfoVisitor extends SimpleFileVisitor<Path> {

        /* renamed from: a, reason: collision with root package name */
        private final VisitOperation f2097a;
        private long d;
        private final ArrayDeque<DirectoryNode> e = new ArrayDeque<>();

        DirectoryInfoVisitor(VisitOperation visitOperation) {
            this.f2097a = visitOperation;
        }

        public long d() {
            return this.d;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            super.preVisitDirectory(path, basicFileAttributes);
            this.e.push(new DirectoryNode(basicFileAttributes));
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            super.visitFile(path, basicFileAttributes);
            if (!basicFileAttributes.isSymbolicLink()) {
                long size = basicFileAttributes.size();
                this.e.peek().c += size;
                this.f2097a.visit(path, basicFileAttributes, false, this.e.size(), size);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileVisitResult postVisitDirectory(Path path, IOException iOException) {
            DirectoryNode pop = this.e.pop();
            if (this.e.isEmpty()) {
                this.d = pop.c;
            } else {
                this.e.peek().c += pop.c;
                this.f2097a.visit(path, pop.e, true, this.e.size(), pop.c);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static class PrintVisitOperation implements VisitOperation {

        /* renamed from: a, reason: collision with root package name */
        private final boolean f2099a;
        private final Path b;
        private final PrintStream c;
        private final int d;
        private final int e;
        private final PrintWriter j;

        protected boolean e(String str, boolean z, int i, long j) {
            return true;
        }

        public PrintVisitOperation(File file, PrintStream printStream) {
            this(file.toPath(), Integer.MAX_VALUE, true, printStream, null);
        }

        private PrintVisitOperation(Path path, int i, boolean z, PrintStream printStream, PrintWriter printWriter) {
            this.b = path;
            this.e = path.getNameCount();
            this.d = i;
            this.f2099a = z;
            this.c = printStream;
            this.j = printWriter;
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public Path getRootPath() {
            return this.b;
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visitRootBegin() {
            e("Path, Depth, Type, Size");
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visit(Path path, BasicFileAttributes basicFileAttributes, boolean z, int i, long j) {
            if (!(this.c == null && this.j == null) && this.d >= i) {
                if (z) {
                    if (!this.f2099a && j <= 0) {
                        return;
                    }
                } else if (!this.f2099a) {
                    return;
                }
                String obj = path.subpath(this.e, path.getNameCount()).toString();
                if (e(obj, z, i, j)) {
                    a(obj);
                    a(", ");
                    a(String.valueOf(i));
                    a(z ? ", D, " : ", F, ");
                    a(String.valueOf(j));
                    a();
                }
            }
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visitRootEnd(long j) {
            e(this.b + ", 0, D, " + j);
        }

        private void a() {
            PrintStream printStream = this.c;
            if (printStream != null) {
                printStream.println();
            }
            PrintWriter printWriter = this.j;
            if (printWriter != null) {
                printWriter.println();
            }
        }

        private void e(String str) {
            PrintStream printStream = this.c;
            if (printStream != null) {
                printStream.println(str);
            }
            PrintWriter printWriter = this.j;
            if (printWriter != null) {
                printWriter.println(str);
            }
        }

        private void a(String str) {
            PrintStream printStream = this.c;
            if (printStream != null) {
                printStream.print(str);
            }
            PrintWriter printWriter = this.j;
            if (printWriter != null) {
                printWriter.print(str);
            }
        }
    }

    public static class MapVisitOperation implements VisitOperation {

        /* renamed from: a, reason: collision with root package name */
        private final Map<String, Long> f2098a;
        private final Path b;
        private final int c;
        private final int d;
        private int e;

        protected boolean c(String str, boolean z, int i, long j) {
            return true;
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visitRootBegin() {
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visitRootEnd(long j) {
        }

        public MapVisitOperation(File file, Map<String, Long> map) {
            this(file.toPath(), 0, map);
        }

        public MapVisitOperation(Path path, int i, Map<String, Long> map) {
            this.b = path;
            this.d = path.getNameCount();
            if (map == null) {
                this.c = 0;
                this.f2098a = Collections.EMPTY_MAP;
            } else {
                this.c = i;
                this.f2098a = map;
            }
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public Path getRootPath() {
            return this.b;
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visit(Path path, BasicFileAttributes basicFileAttributes, boolean z, int i, long j) {
            if (z) {
                if (this.c > 0 || this.f2098a.size() != this.e) {
                    String obj = path.subpath(this.d, path.getNameCount()).toString();
                    int i2 = this.c;
                    if ((i2 <= 0 || i > i2 || !c(obj, z, i, j)) && !this.f2098a.containsKey(obj)) {
                        return;
                    }
                    this.f2098a.put(obj, Long.valueOf(j));
                    this.e++;
                }
            }
        }
    }
}
