import java.io.File;

public class DeleteFileIO {
    private void deleteFile(String source) {
        File file = new File(source);
        // nếu tồn tại thì xóa
        if (file.exists()) {
            System.out.println("file ton tai");
            file.delete();
            System.out.println("xoa file thanh cong");
        } else {
            System.out.println("file khong ton tai");
        }
    }

    public static void main(String[] args) {
        DeleteFileIO deleteFileIO = new DeleteFileIO();
        deleteFileIO.deleteFile("D:/HocJava/demo.txt");
    }
}
