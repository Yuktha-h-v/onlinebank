/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olga Minguett
 */
@Entity
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")
    , @NamedQuery(name = "Transactions.findByTransactionId", query = "SELECT t FROM Transactions t WHERE t.transactionId = :transactionId")
    , @NamedQuery(name = "Transactions.findByTransactionType", query = "SELECT t FROM Transactions t WHERE t.transactionType = :transactionType")
    , @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount")
    , @NamedQuery(name = "Transactions.findByDescription", query = "SELECT t FROM Transactions t WHERE t.description = :description")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Basic(optional = false)
    @Column(name = "transaction_type")
    private String transactionType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Customers customerId;
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @ManyToOne(optional = false)
    private Accounts accountId;
    @JoinColumn(name = "customer_id_to", referencedColumnName = "customer_id")
    @ManyToOne
    private Customers customerIdTo;
    @JoinColumn(name = "account_id_to", referencedColumnName = "account_id")
    @ManyToOne
    private Accounts accountIdTo;

    public Transactions() {
    }

    public Transactions(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Transactions(Integer transactionId, String transactionType, BigDecimal amount, String description) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
    }

    public Customers getCustomerIdTo() {
        return customerIdTo;
    }

    public void setCustomerIdTo(Customers customerIdTo) {
        this.customerIdTo = customerIdTo;
    }

    public Accounts getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(Accounts accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.models.Transactions[ transactionId=" + transactionId + " ]";
    }
    
}
