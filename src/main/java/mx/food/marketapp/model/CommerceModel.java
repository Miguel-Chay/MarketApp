package mx.food.marketapp.model;

import javax.persistence.*;

@Entity
@Table(name = "commerce")
public class CommerceModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "salesman_id")
  private SalesmanModel salesman;
  
  @Column(name = "commercial_name")
  private String commercialName;
  
  @Column(name = "rfc")
  private String rfc;

  @Column(name = "description")
  private String description;

  @Column
  @Enumerated(EnumType.STRING)
  private CityModel city;

  @Column(name = "address")
  private String address;

  @Column(name = "logo")
  private String logo;

  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SalesmanModel getSalesman() {
    return salesman;
  }

  public void setSalesman(SalesmanModel salesman) {
    this.salesman = salesman;
  }

  public String getCommercialName() {
    return commercialName;
  }

  public void setCommercialName(String commercialName) {
    this.commercialName = commercialName;
  }

  public String getRfc() {
    return rfc;
  }

  public void setRfc(String rfc) {
    this.rfc = rfc;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CityModel getCity() {
    return city;
  }

  public void setCity(CityModel city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  
}
