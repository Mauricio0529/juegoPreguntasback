package mapperDTO;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*LA ANOTACION @Configuration IMPLEMENTA O UTILIZA BEANS EN LA CLASE EN LA QUE SE VALLA A USAR*/
@Configuration
public class mapperDTO {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    /*
    https://stackoverflow.com/questions/74405271/consider-defining-a-bean-of-type-org-modelmapper-modelmapper-in-your-configura
    https://github.com/amdegregorio/ModelMapper-NoSpringBootStarter/tree/master/src

     */
}