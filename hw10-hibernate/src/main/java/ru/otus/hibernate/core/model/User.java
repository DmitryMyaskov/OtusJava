package ru.otus.hibernate.core.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String name;
   @OneToOne(cascade = CascadeType.ALL)
   private AddressDataSet address;
   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<PhoneDataSet> phones = new ArrayList<>();

   public User() {
   }

   public User(long id, String name) {
      this.id = id;
      this.name = name;
   }

   public User(String name) {
      this.name = name;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public AddressDataSet getAddress() {
      return address;
   }

   public void setAddress(AddressDataSet address) {
      this.address = address;
   }

   public List<PhoneDataSet> getPhones() {
      return phones;
   }

   public void setPhones(List<PhoneDataSet> phones) {
      this.phones = phones;
   }

   @Override
   public String toString() {
      return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address=" + address +
            ", phones=" + phones +
            '}';
   }
}
