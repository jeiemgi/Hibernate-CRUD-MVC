/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.UserDAO;
import com.model.UsersData;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author josemiguel
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {

    }

    public void addUser() {

        UsersData user = new UsersData(
                getFirstName(),
                getLastName(),
                getEmail(),
                getPhoneNumber(),
                getGender()
        );
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);

    }

    public void returnUserById() {
        UserDAO userDAO = new UserDAO();
        UsersData user = userDAO.getUserByID(getId());

        if (user != null) {
            
            setId           (user.getId()           );
            setFirstName    (user.getFirstName()    );
            setLastName     (user.getLastName()     );
            setEmail        (user.getEmail()        );
            setPhoneNumber  (user.getPhoneNumber()  );
            setGender       (user.getGender()       );
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario NO encontrado."));
        }
    }
    
    public void updateUser() {
        UsersData newUser = new UsersData(
                getFirstName(),
                getLastName(),
                getEmail(),
                getPhoneNumber(),
                getGender()
        );
        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(getId(), newUser);
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Usuario con ID: " + getId() + " ha sido actualizado."));
    }
    
    public void deleteUser() {
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUser(getId());
        setFirstName    ("");
        setLastName     ("");
        setEmail        ("");
        setPhoneNumber  ("");
        setGender       ("");
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Usuario con ID: " + getId() + " ha sido eliminado."));
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

}
