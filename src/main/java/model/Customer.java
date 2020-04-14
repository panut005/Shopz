package model;

public class Customer {

    private String address;
    private String district;
    private String province;

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    private String zipcode;
    private String firstname;
    private String lastname;
    private String tel_number;

    public Customer(String address, String district, String province, String zipcode,
                    String firstname, String lastname, String tel_number) {
        this.address = address;
        this.district = district;
        this.province = province;
        this.zipcode = zipcode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel_number = tel_number;
    }




    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getProvince() {
        return province;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
