import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {

    public boolean copyFile(String source, String dest) throws IOException {
        File sourceFile = new File(source);
        File destFile = new File(dest);

        if (sourceFile.exists()) {
            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] arr = new byte[1024];
            int n;
            // Sửa lỗi so với bản gốc: chỉ ghi đúng số byte đã đọc (n)
            while ((n = fis.read(arr)) != -1) {
                fos.write(arr, 0, n);
                fos.flush();
            }
            fis.close();
            fos.close();
            System.out.println("copy thành công");
            return true;
        } else {
            System.out.println("file nguồn không tồn tại");
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        new CopyFile().copyFile("a.txt", "b.txt");
    }
}
