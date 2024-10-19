package TestClasses;

public class GetCoursePojo {

    private String expertise;
    private Courses courses;//For nested key,datatype would be className of its class
    private String linkedIn;
    private String instructor;
    private String url;
    private String services;

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }


    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }


    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }


}