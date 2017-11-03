package net.lelyak.edu.model.dto;

import net.lelyak.edu.model.dao.XlsMapping;

//@Getter
public class AdminDTO {

    private String testType;
    private String login;
    private String pass;
    private String field;

    @XlsMapping(header = "testType")
    public void setTestType(String testType) {
        this.testType = testType;
    }

    @XlsMapping(header = "login")
    public void setLogin(String login) {
        this.login = login;
    }

    @XlsMapping(header = "pass")
    public void setPass(String pass) {
        this.pass = pass;
    }

    @XlsMapping(header = "field")
    public void setField(String field) {
        this.field = field;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
