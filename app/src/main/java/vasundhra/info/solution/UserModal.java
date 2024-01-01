package vasundhra.info.solution;

public class UserModal {

    String id, name, gen, email, pass, dob;

    public UserModal() {
    }

    public UserModal(String id, String name, String gen, String email, String pass, String dob) {
        this.id = id;
        this.name = name;
        this.gen = gen;
        this.email = email;
        this.pass = pass;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

