public class Main {
    public static void main(String[] args) {
        SinhVien sv1 = new SinhVien("Nguyễn Văn An", 2004, "TP.HCM", "SV001", "Công nghệ thông tin", 8.7);
        SinhVien sv2 = new SinhVien("Trần Thị Bình", 2003, "Đồng Nai", "SV002", "Kế toán", 6.4);

        GiangVien gv1 = new GiangVien("Lê Minh Cường", 1980, "Hà Nội", "GV001", "Lập trình Java", 2000000, 3.5);
        GiangVien gv2 = new GiangVien("Phạm Thu Dung", 1985, "Đà Nẵng", "GV002", "Cơ sở dữ liệu", 2000000, 3.0);

        sv1.hienThiThongTin();
        System.out.println();
        sv2.hienThiThongTin();
        System.out.println();
        gv1.hienThiThongTin();
        System.out.println();
        gv2.hienThiThongTin();

        System.out.println("\n=== XẾP LOẠI SINH VIÊN ===");
        System.out.println(sv1.getHoTen() + ": " + sv1.xepLoai());
        System.out.println(sv2.getHoTen() + ": " + sv2.xepLoai());

        System.out.println("\n=== LƯƠNG GIẢNG VIÊN ===");
        System.out.println(gv1.getHoTen() + ": " + String.format("%,.0f", gv1.tinhLuong()));
        System.out.println(gv2.getHoTen() + ": " + String.format("%,.0f", gv2.tinhLuong()));
    }
}
