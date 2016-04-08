package tree;

import java.io.File;

public class FileDirectory {

	String path;

	public FileDirectory(String p) {
		path = p;
	}
	
	public void printAll() {
		File f = new File(path);
		printAll(f, 0);
	}
	
	private float printAll(File path, int level) {
		
		float size = 0;
		
		if (path == null) {
			return 0;
		}
		
		if (path.isFile()) {
			size = (float) (path.length() / 1024.0 / 1024.0);
		}
		
		if (path.isDirectory()){
			File[] fs = path.listFiles();
			for (File f : fs) {
				size += printAll(f, level + 1);
			}
		}
		
		System.out.print(printTables(level) + path.getName());
		System.out.format("  size: %.1f  \r\n", size);
		return size;
		
	}
	
	private String printTables(int level) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < level; i ++) {
			s.append("\t");
		}
		return s.toString();
	}
	
}
