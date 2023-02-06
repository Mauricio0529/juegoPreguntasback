package mapperDTO;

import com.example.demo.dto.questionDTO;
import com.example.demo.models.question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


// https://www.youtube.com/watch?v=RapJOnLGvjY
//@Configuration
@Component("mapperEntityAndDTO")
public class mapperEntityAndDTO {

    /*
    * vamos a agregar informacion del entity al dto.
    * ej: al listar un dto no va a tener ningun valor, ya que esta vacio.
    * se mappea para pasar o agregar informacion que tenga una entity al dto.
    * se usara ModelMapper
    * */

    private final ModelMapper modelMapper = new ModelMapper();

    public questionDTO questionEntityConvertirDTO(question questionEntity) {
        questionDTO questionDTO = null;
        // VERIFICAMOS SI HAY INFORMACION EN LA ENTITY
        // FALTA VALIDAR O SI ARROJA UNA EXCEPCION SI LLEGA A SER NULO
        if (questionEntity != null) {
            // usamos el modelMapper para agregar la informacion de la entity al dto.
            questionDTO = modelMapper.map(questionEntity, questionDTO.class);
        }
        return questionDTO;
    }

  /*  public answerDTO answerEntityConvertirDTO(answer answerEntity) {
        answerDTO answerDTO = null;
        if(answerEntity != null) answerDTO = modelMapper.map(answerEntity, answerDTO.class);
        return answerDTO;
    }
*/

  /*  public questionAnswerDTO questionAnswerEntityConvertirDTO(question questionEntity) {
        questionAnswerDTO questionAnswerDTO = null;
        if(questionEntity != null) {
            questionAnswerDTO = modelMapper.map(questionEntity, questionAnswerDTO.class);
        }
        return questionAnswerDTO;
    }
*/

    /*
   ********************************************
   private final ModelMapper modelMapper = new ModelMapper();

    // se hace un singleton de esta clase
    private static mapperEntityAndDTO instance;

    private mapperEntityAndDTO() {
    }

    // un singleton
    public static mapperEntityAndDTO singleInstance() {
        // este se hace como un singleton, tengo que estudiar la metodologia singleton
        if(instance == null){
            instance = new mapperEntityAndDTO();
        }
        return instance;
    }

    public questionDTO entityToDTO(question questionEntity){
        // se coloca la entidad que tenemos y el dto que queremos mapear
        return modelMapper.map(questionEntity, questionDTO.class);
    }
**************************************************
*/

    /*
    public questionDTO entityConvertirDTO(question questionEntity) {
        questionDTO questionDTO = null;
        // VERIFICAMOS SI HAY INFORMACION EN LA ENTITY
        if (questionEntity != null) {
            // usamos el modelMapper para agregar la informacion de la entity al dto.
            questionDTO = modelMapper.map(questionEntity, questionDTO.class);
        }
        return questionDTO;
    }
    */


    /*
            * esta seria la forma clasica y no optima de agregar informacion de una entity al dto.
            * ya que no seria muy optimo llamar cada una de las variables que va a poseer un dto, ya que en -
            * - algunas ocaciones son muchas variables en un dto.
            public List<question> EntityAndDTO() {
                // video del codigo
                // https://www.youtube.com/watch?v=y0gBMMdTOfE&list=PLub7kY4x2lvFNeqbqzlLqH8AC1ON670Hf&index=8
                 List<question> entity = repository.findAll(); // obtener todos los datos de la entity
                 List<questionDTO> listaDTO = new ArrayList<>();
                 questionDTO questionDTO2 = new questionDTO();
                 RespuestaEntity re = new RespuestaEntity();
                 for(question q: entity) { // lo recorremos
                     questionDTO2.setQuestion(q.getQuestion());
                     questionDTO2.setOpcionesRespuestas(re.getRespuesta);
                     listaDTO.add(questionDTO2);
                 }
                 return listaDTO;
            }
        * */

}