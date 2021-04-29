package com.github.skozlov.mp3shuffle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    private static final String FILE_NAME_SUFFIX = ".mp3";

    public static void main(String[] args) throws IOException {
        var inDir = new File(args[0]);
        var outDir = new File(args[1]);
        //noinspection ResultOfMethodCallIgnored
        outDir.mkdirs();
        //noinspection ConstantConditions
        var sourceFiles = Arrays.asList(inDir.listFiles((file, name) -> name.endsWith(FILE_NAME_SUFFIX)));
        Collections.shuffle(sourceFiles);
        var maxIndex = sourceFiles.size() - 1;
        var indexLength = Integer.toString(maxIndex).length();
        for (int i = 0; i <= maxIndex; i++) {
            var sourceFile = sourceFiles.get(i);
            var targetFileName = String.format("%0" + indexLength + "d - %s", i, sourceFile.getName());
            var targetFile = new File(outDir, targetFileName);
            System.out.printf("%s -> %s%n", sourceFile.getAbsolutePath(), targetFile.getAbsolutePath());
            Files.copy(sourceFile.toPath(), targetFile.toPath());
        }
    }
}
