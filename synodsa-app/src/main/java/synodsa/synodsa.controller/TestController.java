package synodsa.synodsa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import synodsa.entity.UserDetailsEntity;
import synodsa.pdfexporter.UserPdfExporter;
import synodsa.repository.UserDetailsRepository;
import synodsa.service.UserDetailsService;
import synodsa.synodsa.DTO.UserDetailsDTO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserDetailsRepository repo;
    @Autowired
    UserDetailsService userDetailsService;

    //Insert data
    @PostMapping("/userdetails/insert")
    public String setDataInDb(@RequestBody UserDetailsDTO userDetailsDTO){
        log.info("dto=[{}]",userDetailsDTO);
     userDetailsService.setData(userDetailsDTO);
     return "Data Has Been Inserted";
    }

    //Update data by id
    @PutMapping("/userdetails/updatebyid/{id}")
    public String updateDataInDb(@PathVariable long id, @RequestBody UserDetailsDTO userDetailsDTO)
    {
        userDetailsService.updateData(id, userDetailsDTO);
        return "Updated";
    }

    //Get all data
    @GetMapping("/userdetails/getalldata")
    public List<UserDetailsEntity> getAllData(){
        return userDetailsService.listAll();
    }

    //Get by firstname
    @GetMapping("/userdetails/getbyfirstname/{firstName}")
    public List<UserDetailsEntity> getDataByFirstName(@PathVariable(value = "firstName") String firstName){
        return userDetailsService.listByFirstName(firstName);
    }

    //Get by id
    @GetMapping("/userdetails/getbyid/{id}")
    public Optional<UserDetailsEntity> getDataById(
            @PathVariable(value = "id") long id)
    {
        return userDetailsService.listById(id);
    }

    //Pdf generate
    @GetMapping("/userdetails/pdf/generate")
    public  void  generateToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = pdf" + currentDateTime + ".pdf";

        response.setHeader(headerKey,headerValue);

        List<UserDetailsEntity> listUsers = userDetailsService.listAll();
        UserPdfExporter exporter = new UserPdfExporter(listUsers);
        exporter.export(response);
    }

    //Delete by id
    @DeleteMapping("/userdetails/deletebyid/{id}")
    public String deleteDataById(
            @PathVariable(value = "id") long id)
    {
        userDetailsService.deleteUserDetailsById(id);
        return "Successfully Deleted id " + id;
    }

    //Delete all data
    @DeleteMapping("/userdetails/deleteall")
    public String deleteAllData()
    {
        userDetailsService.deleteAllDetails();
        return "Successfully Deleted";
    }
}