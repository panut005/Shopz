package model;

import java.time.LocalDate;

public class Voucher {

    private String codename;
    private String discount;
    private int limit;
    private LocalDate localDate;

    public Voucher(String codename, String discount, int limit, LocalDate localDate) {
        this.codename = codename;
        this.discount = discount;
        this.limit = limit;
        this.localDate = localDate;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
