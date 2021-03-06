package models;

import models.base.BaseModel;
import play.data.validation.Constraints;
import play.db.jpa.JPA;
import util.ExposeField;

import javax.persistence.Entity;
import javax.persistence.NoResultException;
import java.net.URI;
import java.util.List;

/**
 * Created by richard on 01.07.15.
 */
@Entity
public class Client extends BaseModel {

    @ExposeField(name ="client_name")
    @Constraints.Required
    public String clientName;

    public String clientId;

    @ExposeField(name = "client_secret")
    public String clientSecret;

    @ExposeField(name = "callback_uri")
    @Constraints.Required
    public URI callback;

    @Override
    public void create() {
        JPA.em().persist(this);
    }

    @Override
    public void update() {
    }

    @Override
    public void delete() {

    }

    public static Client findById(Long id) {
        return JPA.em().find(Client.class, id);
    }

    public static Client findByClientId(String clientId) {
        try{
            return (Client) JPA.em()
                    .createQuery("from Client a where a.clientId = :clientId")
                    .setParameter("clientId", clientId).getSingleResult();
        } catch (NoResultException exp) {
            return null;
        }
    }

    public static List<Client> findAll(){
        return JPA.em().createQuery("SELECT a FROM Client a ORDER BY a.clientName").getResultList();
    }
}
