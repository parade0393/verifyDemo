package demo.stream;

public class Address {
    private String province;
    private String city;
    private String area;

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public Address() {
    }

    public Address(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = area;
    }
}
