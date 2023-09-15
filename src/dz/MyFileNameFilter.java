package dz;

import java.io.File;
import java.io.FilenameFilter;

/**
 * переопределение FilenameFilter
 */

public class MyFileNameFilter implements FilenameFilter {
    private String ext;

    public MyFileNameFilter(String ext){
        this.ext = ext.toLowerCase();
    }
    @Override
    public boolean accept(File sourceDirectory, String name) {
        return name.toLowerCase().endsWith(ext);
    }
}
