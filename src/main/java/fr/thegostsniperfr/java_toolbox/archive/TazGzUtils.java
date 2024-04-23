package fr.thegostsniperfr.java_toolbox.archive;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class TazGzUtils {
    public static void decompressTarGz(Path source, Path targetDir) throws IOException {
        try (final InputStream fileInputStream = Files.newInputStream(source);
             final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             final GzipCompressorInputStream gzipInputStream = new GzipCompressorInputStream(bufferedInputStream);
             final TarArchiveInputStream tarInputStream = new TarArchiveInputStream(gzipInputStream))
        {
            ArchiveEntry entry;
            byte[] buffer = new byte[8192];
            while ((entry = tarInputStream.getNextEntry()) != null) {
                Path entryPath = targetDir.resolve(entry.getName());

                if (entry.isDirectory()) {
                    Files.createDirectories(entryPath);
                } else {
                    Files.createDirectories(entryPath.getParent());
                    try (final OutputStream outputStream = Files.newOutputStream(entryPath);
                         final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192))
                    {
                        int length;
                        while ((length = tarInputStream.read(buffer)) != -1) {
                            bufferedOutputStream.write(buffer, 0, length);
                        }
                    }
                }
            }
        }
    }
}
