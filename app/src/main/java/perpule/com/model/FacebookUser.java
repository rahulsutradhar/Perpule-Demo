package perpule.com.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by developers on 03/02/2017 AD.
 */

public class FacebookUser {

    /**
     * Facebook user id
     */
    private String id;

    /**
     * first name
     */
    @SerializedName("first_name")
    private String firstName;

    /**
     * Last Name
     */
    @SerializedName("last_name")
    private String lastName;

    /**
     * Email
     */
    private String email;

    public String getName() {
        try {
            if (getFirstName() != null && getLastName() != null) {
                return getFirstName() + getLastName();
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
