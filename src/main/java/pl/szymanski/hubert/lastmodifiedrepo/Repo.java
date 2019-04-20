package pl.szymanski.hubert.lastmodifiedrepo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {

    //--- FIELDS ---//

    private String name;
    private String updated_at;


    //--- CONSTRUCTORS ---//

    public Repo() { }

    public Repo( String name, String updated_at) {
        this.name = name;
        this.updated_at = updated_at;
    }


    //--- GETTERS AND SETTERS ---//

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
