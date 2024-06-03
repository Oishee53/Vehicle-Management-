public class Driver {
    private String name;
    private String phoneNumber;
    private String id;
    private String gender;

    Driver(String name,String phoneNumber,String id,String gender){
        this.name=name;
        this.id=id;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
