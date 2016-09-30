package cl.telematica.android.certamen2.models;

/**
 * Created by Erlend on 30.09.2016.
 */

public class GithubRepo {
    private String name;
    private String description;
    private String updatedAt;

    public GithubRepo(String name, String description, String updatedAt){
        this.name = name;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedAt() {
        return "Última actualización " +updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
