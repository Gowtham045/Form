package Contexts;

import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.json.annotations.JSON;

public class UserContext {
    @Getter@Setter
    private int id=-1;


    private String  firstName;
    private String  lastName;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    @JSON(serialize = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JSON(name="fullName")
    public String getFullName(){
        return this.firstName+" "+lastName;
    }

    private String  email;
    private Long contact;

    private  transient  String  password;
}
