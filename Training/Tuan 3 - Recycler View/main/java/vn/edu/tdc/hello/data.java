package vn.edu.tdc.hello;

/**
 * Created by IT on 3/14/2018.
 */

public class data {
    private  int hinh;
    private String ten;

    public data(int hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
