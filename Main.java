public class Main {
    public static void main(String[] args) {
        SanPham sp1 = new SanPham("SP01", "Bút bi", 5000, 100);
        SanPham sp2 = new SanPham("SP02", "Vở 200 trang", 15000, 50);

        System.out.println("=== Thông tin ban đầu ===");
        sp1.hienThiThongTin();
        sp2.hienThiThongTin();

        System.out.println("\n=== Nhập thêm 30 bút bi ===");
        sp1.nhapHang(30);
        sp1.hienThiThongTin();

        System.out.println("\n=== Thử nhập số lượng không hợp lệ (-5) ===");
        sp1.nhapHang(-5);
        sp1.hienThiThongTin();

        System.out.println("\n=== Bán 20 vở (thành công) ===");
        boolean kq1 = sp2.banHang(20);
        System.out.println("Kết quả: " + kq1);
        sp2.hienThiThongTin();

        System.out.println("\n=== Bán 100 vở (vượt tồn kho) ===");
        boolean kq2 = sp2.banHang(100);
        System.out.println("Kết quả: " + kq2);
        sp2.hienThiThongTin();
    }
}
