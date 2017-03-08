package com.github.appreciated.quickstart.base.registration;

/**
 * Created by appreciated on 05.03.2017.
 */
public class ExampleUser {
    @Field(caption = "Username")
    public String username;
    @Field(caption = "Name")
    public String firstname;
    @Field(caption = "Last Name")
    public String lastname;
    @Field(caption = "E-Mail")
    public String email;

    @Override
    public String toString() {
        return username + " | " + firstname + " | " + lastname + " | " + email;
    }
}
