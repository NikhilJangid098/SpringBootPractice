package synodsa.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synodsa.entity.UserDetailsEntity;
import synodsa.repository.UserDetailsRepository;
import synodsa.synodsa.DTO.UserDetailsDTO;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class UserDetailsService {
    @Autowired
    private final UserDetailsRepository userDetailsRepository;

    public List<UserDetailsEntity> listByFirstName(String firstName){
        List<UserDetailsEntity> response = userDetailsRepository.findByFirstName(firstName);
        if(response.isEmpty()){
            System.out.println(response);
            return null;
        }
        else
            return response;
    }

    public List<UserDetailsEntity> listAll() {
        return userDetailsRepository.findAll();
    }

    public Optional<UserDetailsEntity> listById(Long id) {
        return userDetailsRepository.findById(id);
    }

    public void setData(UserDetailsDTO userDetailsDTO) {
        UserDetailsEntity userDetails = new UserDetailsEntity();
        String name = null;
        log.info("DTO values" + userDetailsDTO);
        userDetails.setFirstName(userDetailsDTO.getFirstName());
        userDetails.setLastName(userDetailsDTO.getLastName());
        userDetails.setAadharCard(userDetailsDTO.getAadharCard());
        userDetails.setEmail(userDetailsDTO.getEmail());
        userDetails.setPanCard(userDetailsDTO.getPanCard());
        userDetails.setZipCode(userDetailsDTO.getZipCode());
        userDetails.setState(userDetailsDTO.getState());
        userDetails.setCity(userDetailsDTO.getCity());
        userDetails.setTypeOfEmp(userDetailsDTO.getTypeOfEmp());
        userDetails.setRequiredLoan(userDetailsDTO.getRequiredLoan());
        userDetails.setMobileNumber(userDetailsDTO.getMobileNumber());
        userDetails.setSalary(userDetailsDTO.getSalary());

        JSONObject requestDataField = new JSONObject(userDetailsDTO.getJsonData().toString());
        System.out.println(requestDataField);
        if (requestDataField.has("name"))
            name = requestDataField.getString("name");
        String value = "{\"name\": \"" + name + "\"}";
        userDetails.setJsonData(value);
        userDetailsRepository.save(userDetails);
    }

    public void updateData(Long id, UserDetailsDTO userDetailsDTO) {
        Optional<UserDetailsEntity> updateDetails = userDetailsRepository.findById(id);

        if (updateDetails.isEmpty()) {
            throw new IllegalStateException("There is no such data");
        }
        UserDetailsEntity user = updateDetails.get();
        String name = null;
        log.info("DTO values" + userDetailsDTO);
        user.setFirstName(userDetailsDTO.getFirstName());
        user.setLastName(userDetailsDTO.getLastName());
        user.setAadharCard(userDetailsDTO.getAadharCard());
        user.setEmail(userDetailsDTO.getEmail());
        user.setPanCard(userDetailsDTO.getPanCard());
        user.setZipCode(userDetailsDTO.getZipCode());
        user.setState(userDetailsDTO.getState());
        user.setCity(userDetailsDTO.getCity());
        user.setTypeOfEmp(userDetailsDTO.getTypeOfEmp());
        user.setRequiredLoan(userDetailsDTO.getRequiredLoan());
        user.setMobileNumber(userDetailsDTO.getMobileNumber());
        user.setSalary(userDetailsDTO.getSalary());

        JSONObject requestJson = new JSONObject(userDetailsDTO.getJsonData().toString());
        System.out.println(requestJson);
        if (requestJson.has("name"))
            name = requestJson.getString("name");
        String value = "{\"name\": \"" + name + "\"}";
        user.setJsonData(value);
        userDetailsRepository.save(user);
    }

    public void deleteUserDetailsById(long id) {
        userDetailsRepository.deleteById(id);
    }

    public void deleteAllDetails() {
        userDetailsRepository.deleteAll();
    }
}