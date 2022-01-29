package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.service.SampleDataImportService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleDataController {

    private final SampleDataImportService sampleDataImportService;

    public SampleDataController(SampleDataImportService sampleDataImportService){
        this.sampleDataImportService = sampleDataImportService;
    }

    @PostMapping("sampledata/import/user/{amountOfUsersToBeImported}")
    public void insertUser(@PathVariable("amountOfUsersToBeImported") Integer amountOfUsersToBeImported){
            sampleDataImportService.importUser(amountOfUsersToBeImported);
    }


}
