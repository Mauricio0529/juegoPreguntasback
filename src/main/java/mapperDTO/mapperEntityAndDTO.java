package mapperDTO;

import com.example.demo.dto.questionDTO;
import com.example.demo.models.question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("mapperEntityAndDTO")
public class mapperEntityAndDTO {

    private final ModelMapper modelMapper = new ModelMapper();

    public questionDTO questionEntityConvertirDTO(question questionEntity) {
        questionDTO questionDTO = null;
        if (questionEntity != null) {
            questionDTO = modelMapper.map(questionEntity, questionDTO.class);
        }
        return questionDTO;
    }
}