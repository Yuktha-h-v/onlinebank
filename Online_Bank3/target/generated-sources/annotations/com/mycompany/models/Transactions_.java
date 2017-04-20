package com.mycompany.models;

import com.mycompany.models.Accounts;
import com.mycompany.models.Customers;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T20:35:37")
@StaticMetamodel(Transactions.class)
public class Transactions_ { 

    public static volatile SingularAttribute<Transactions, String> transactionType;
    public static volatile SingularAttribute<Transactions, Accounts> accountId;
    public static volatile SingularAttribute<Transactions, Customers> customerIdTo;
    public static volatile SingularAttribute<Transactions, BigDecimal> amount;
    public static volatile SingularAttribute<Transactions, Customers> customerId;
    public static volatile SingularAttribute<Transactions, Accounts> accountIdTo;
    public static volatile SingularAttribute<Transactions, String> description;
    public static volatile SingularAttribute<Transactions, Integer> transactionId;

}