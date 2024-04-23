package fr.thegostsniperfr.java_toolbox.archive;

import fr.thegostsniperfr.java_toolbox.hash.HashUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtils {
    public static void Unzip(Path source, Path targetDir) throws IOException {
        try (final ZipFile zipFile = new ZipFile(source.toFile())) {
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                final Path entryPath = targetDir.resolve(entry.getName());

                if (entry.isDirectory()) {
                    Files.createDirectories(entryPath);
                    continue;
                }

                if (Files.notExists(entryPath) || HashUtils.getCRC32(entryPath) != entry.getCrc()) {
                    Files.createDirectories(entryPath.getParent());
                    try (final BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(entry), 8192);
                         final BufferedOutputStream out = new BufferedOutputStream(Files.newOutputStream(entryPath), 8192)) {
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        }
    }
}
