package com.mycompany.models;

import com.mycompany.models.Accounts;
import com.mycompany.models.Transactions;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-21T21:30:07")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, String> firstname;
    public static volatile SingularAttribute<Customers, String> password;
    public static volatile SingularAttribute<Customers, String> address;
    public static volatile CollectionAttribute<Customers, Accounts> accountsCollection;
    public static volatile SingularAttribute<Customers, Integer> customerId;
    public static volatile SingularAttribute<Customers, Date> dateOfBirth;
    public static volatile CollectionAttribute<Customers, Transactions> transactionsCollection;
    public static volatile SingularAttribute<Customers, String> user;
    public static volatile SingularAttribute<Customers, String> email;
    public static volatile SingularAttribute<Customers, String> lastname;

}