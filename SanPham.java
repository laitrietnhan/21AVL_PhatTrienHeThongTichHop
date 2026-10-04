public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private double donGia;
    private int soLuong;

    public SanPham(String maSanPham, String tenSanPham, double donGia, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public String getMaSanPham() { return maSanPham; }
    public String getTenSanPham() { return tenSanPham; }
    public double getDonGia() { return donGia; }
    public int getSoLuong() { return soLuong; }

    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    public void nhapHang(int soLuongNhap) {
        if (soLuongNhap <= 0) {
            System.out.println("Số lượng nhập phải lớn hơn 0!");
            return;
        }
        soLuong += soLuongNhap;
        System.out.println("Nhập thành công " + soLuongNhap + " sản phẩm.");
    }

    public boolean banHang(int soLuongBan) {
        if (soLuongBan <= 0) {
            System.out.println("Số lượng bán phải lớn hơn 0!");
            return false;
        }
        if (soLuongBan > soLuong) {
            System.out.println("Không đủ hàng! Tồn kho: " + soLuong + ", yêu cầu bán: " + soLuongBan);
            return false;
        }
        soLuong -= soLuongBan;
        System.out.println("Bán thành công " + soLuongBan + " sản phẩm.");
        return true;
    }

    public void hienThiThongTin() {
        System.out.println("Mã: " + maSanPham
                + " | Tên: " + tenSanPham
                + " | Đơn giá: " + String.format("%,.0f", donGia)
                + " | Số lượng: " + soLuong
                + " | Thành tiền: " + String.format("%,.0f", tinhThanhTien()));
    }
}
