package ch.sowatec.pg.notenapp;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import ch.sowatec.pg.notenapp.Database.Data.Grade;

public class Exporter {
    private static String filename = "/Grades-Export";
    private static String fileending = ".csv";
    private final String path = "/data/data/ch.sowatec.gatzka.notenapp/files/Exports";

    public void export(ArrayList<Grade> choosen) throws Exception {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        String fname = filename + "-" + dateFormat.format(d) + fileending;
        Path p = Paths.get(path + fname);
        Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-r--r--");
        FileAttribute<?> permissions = PosixFilePermissions.asFileAttribute(ownerWritable);
        File f = Files.createFile(p, permissions).toFile();
        BufferedWriter w = new BufferedWriter(new FileWriter(f));
        CSVWriter csvWriter = new CSVWriter(w, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        String[] headerRecord = {"Fach", "Note", "Lehrperson", "Datum"};
        csvWriter.writeNext(headerRecord);
        choosen.forEach(g -> {
            String[] grade = {g.subject.name, String.valueOf(g.grade), g.subject.teacher.toString(), g.getDate()};

        });


    }


}
