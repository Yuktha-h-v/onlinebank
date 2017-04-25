package com.mycompany.models;

import com.mycompany.models.Customers;
import com.mycompany.models.Transactions;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-24T17:05:24")
@StaticMetamodel(Accounts.class)
public class Accounts_ { 

    public static volatile SingularAttribute<Accounts, Integer> accountId;
    public static volatile SingularAttribute<Accounts, String> accountType;
    public static volatile SingularAttribute<Accounts, Customers> customerId;
    public static volatile CollectionAttribute<Accounts, Transactions> transactionsCollection;
    public static volatile SingularAttribute<Accounts, BigDecimal> accountBalance;

}