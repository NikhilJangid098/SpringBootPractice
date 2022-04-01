package synodsa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

@Component
@Data
@Entity
@Table(name = "user_details", schema = "public")
@TypeDef(name = "json", typeClass = JsonType.class)
public class UserDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private Long zipCode;

    @Column(name = "pan_card")
    private String panCard;

    @Column(name = "aadhar_card")
    private Long AadharCard;

    @Column(name = "type_of_emp")
    private String typeOfEmp;

    @Column(name = "required_loan")
    private Long requiredLoan;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "json_data")
    @Type(type = "json")
    private String jsonData;
}