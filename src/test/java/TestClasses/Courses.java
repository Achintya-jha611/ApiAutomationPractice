package TestClasses;

import java.util.List;

public class Courses {
    private List<Web> webAutomation;//We are mentioning it as List as the json response will have array of WebAutomation objects insided courses
    private List<Mobile> mobile;
    private List<Api> api;

    public List<Api> getApi() {
        return api;
    }

    public void setApi(List<Api> api) {
        this.api = api;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }

    public List<Web> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<Web> webAutomation) {
        this.webAutomation = webAutomation;
    }
}
