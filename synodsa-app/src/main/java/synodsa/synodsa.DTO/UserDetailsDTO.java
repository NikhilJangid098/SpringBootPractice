package synodsa.synodsa.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class UserDetailsDTO {


    private String firstName;
    private String lastName;

    private Long zipCode;

    private Long aadharCard;

    private String panCard;

    private String email;

    private String typeOfEmp;

    private String state;

    private String city;

    private Long requiredLoan;

    private Long mobileNumber;

    private Long salary;

    @JsonProperty("jsonData")
    private JsonNode jsonData;

}