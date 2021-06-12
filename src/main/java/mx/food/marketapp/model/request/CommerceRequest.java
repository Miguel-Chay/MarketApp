package mx.food.marketapp.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommerceRequest {

  @NotNull
  private Integer salesmanId;

  @NotNull
  @Size(min = 1, max = 255)
  @NotEmpty
  private String commercialName;

  @NotNull
  private String rfc;

  @Size(min = 0, max = 255)
  private String description;

  @NotNull
  @Size(min = 5, max = 255)
  private String phone;

  @NotNull
  private String city;

  @NotNull
  @Size(min = 0, max = 255)
  @NotEmpty
  private String address;

  @Size(min = 0, max = 255)
  private String logo;

  public CommerceRequest() {}

  public Integer getSalesmanId() {
    return salesmanId;
  }

  public void setSalesmanId(Integer salesmanId) {
    this.salesmanId = salesmanId;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
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
