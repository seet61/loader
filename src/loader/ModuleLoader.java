package loader;

import java.io.*;

public class ModuleLoader extends ClassLoader {
    //путь для загрузки
    private String pathToBin;

    public ModuleLoader(ClassLoader parent, String pathToBin) {
        super(parent);
        this.pathToBin = pathToBin;
    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            /**
             * Получем байт-код из файла и загружаем класс в рантайм
             */
            File f = new File(pathToBin, className + ".class");
            System.out.println(f.toString());
            byte b[] = fetchClassFromFS(f.toString());
            return defineClass(className, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }

    //http://www.ilnurgi1.ru/docs/java/java/lang/ClassLoader.html
    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));

        // Get the size of the file
        long length = new File(path).length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+path);
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;

    }
}

