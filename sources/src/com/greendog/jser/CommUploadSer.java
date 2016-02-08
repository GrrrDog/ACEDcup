package com.greendog.jser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

public class CommUploadSer {
	private String fPathTarget;

	private String fContent;
	private FileItem item;

	public CommUploadSer(String fPathTarget, String fContent) throws IOException {
		this.fPathTarget = fPathTarget;
		this.fContent = fContent;
	}

	public void Create() throws IOException {

		FileItemFactory factory = new DiskFileItemFactory(fContent.length(), null);

		this.item = factory.createItem("field1", " text/html", true, "temp.txt");

		OutputStream os = item.getOutputStream();
		os.write(fContent.getBytes());
		os.close();

		Class<? extends FileItem> c = item.getClass();
		// Field [] ownFields= c.getDeclaredFields();
		// for (Field field: ownFields){
		// Class fieldType = field.getType();
		// System.out.println(field.getName());
		// System.out.println(fieldType.getName());
		// }

		try {
			File nr = new File(fPathTarget);

			Field field = c.getDeclaredField("repository");
			field.setAccessible(true);
			field.set(item, nr);

			File rep = (File) field.get(item);
			System.out.println("repository: " + rep);

			Field field1 = c.getDeclaredField("sizeThreshold");
			field1.setAccessible(true);
			field1.setInt(item, 1);

			//Integer size = (Integer) field1.get(item);
			//System.out.println("size " + size);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-gnerated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Serialize(String fPathOut) throws IOException {
		FileOutputStream fos;

		fos = new FileOutputStream(fPathOut);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(item);

		oos.close();
		fos.close();

	
	}

}
