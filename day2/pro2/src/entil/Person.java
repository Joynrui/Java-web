package entil;

public class Person {
    private String personId;
    private String name;
    private String age;
    private String sex;
    private String fax;

    public Person() {
    }

    public Person(String personId, String name, String age, String sex, String fax) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.fax = fax;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}
