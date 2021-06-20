package Dto;

public class User {
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String password;


    public User(String userName, String email, String phone, String address, String password) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() { return email; }
    public void setEmail(String userName) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
