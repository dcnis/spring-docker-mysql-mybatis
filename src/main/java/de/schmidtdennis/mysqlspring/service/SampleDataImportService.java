package de.schmidtdennis.mysqlspring.service;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import de.schmidtdennis.mysqlspring.model.response.ImportUserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleDataImportService {

    private final UserMapper userMapper;

    private final Faker faker = new Faker();

    public SampleDataImportService(UserMapper userMapper){
        this.userMapper = userMapper;
    }


    public ImportUserResponse importUser(Integer amountOfUsersToBeImported){

        int addedUsers = 0;
        List<User> importedUsers = new ArrayList<>();

        for(int i = 0; i < amountOfUsersToBeImported; i++){
            Name name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();
            // randonNumber to avoid duplicate emails (email ist unique in DB)
            int randonNumber = (int) (Math.random() * 1_000_000_000);
            String email = firstName + "." + lastName + "." + randonNumber +  "@gmail.com";
            User user = new User(null, firstName, lastName, email);
            addedUsers += userMapper.addUser(user);
            importedUsers.add(user);
        }

        return new ImportUserResponse(addedUsers, importedUsers);
    }


}
