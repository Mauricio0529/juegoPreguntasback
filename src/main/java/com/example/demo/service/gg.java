package com.example.demo.service;

public class gg {

   // @Override
  //  public void run(String... args) throws Exception {

		/*question questionObject =  new question(5, "Que numero en romano es el 5");

		optionsAnswer optionsAnswerA = new optionsAnswer(17, "V", true);
		optionsAnswer optionsAnswerB = new optionsAnswer(18, "X", false);
		optionsAnswer optionsAnswerC = new optionsAnswer(19, "|||", false);
		optionsAnswer optionsAnswerD = new optionsAnswer(20, "XX", false);

		List<optionsAnswer> listQuestionAnswer = new ArrayList<>();

		optionsAnswerA.setQuestion(questionObject);
		optionsAnswerB.setQuestion(questionObject);
		optionsAnswerC.setQuestion(questionObject);
		optionsAnswerD.setQuestion(questionObject);

		listQuestionAnswer.add(optionsAnswerA);
		listQuestionAnswer.add(optionsAnswerB);
		listQuestionAnswer.add(optionsAnswerC);
		listQuestionAnswer.add(optionsAnswerD);

		questionObject.setOptionsAnswerList(listQuestionAnswer);

		System.out.println("Pregunta => " + questionObject.getQuestion() +
				" " + questionObject.getIdQuestion());

		listQuestionAnswer.stream().forEach(x -> {
			System.out.println(" Respuestas: " + x.getOptionAnswer() + " Numero pregunta: " + x.getQuestion().getIdQuestion());
		});
*/

/*
        // DESDE AQUI ES EL CODIGO BUENO
        List<question> questionList = questionRespository.findAll();
        Optional<question> optionalQuestion = questionRespository.findById(2);
        List<optionsAnswer> answers = answerRepository.findAll();


        questionList.stream()
                .filter(x -> x.getIdQuestion() == 2)
                .forEach(x -> {
                    System.out.println("Salida N°1: " + x.getQuestion());
                });

        // Obtener la pregunta
        System.out.println("Salida  N°2: " + optionalQuestion.get().getIdQuestion() + ". " + optionalQuestion.get().getQuestion() + "? ");
*/

        /*
         * NOTA: TOCA HACER CON EL ANSWER PARA VER SI ME TRAE LA PREGUNTA Y LAS RESPUESTAS CON UNA PERSISTENCIA.
         * YA QUE EN ESTE EJEMPLO SE HIZO DOS PERSISTENCIAS PARA OBTENER LA RPREGUNTA Y OTRA PARA LAS RESPUESTAS
         */

/*
        // Obtener la respuesta correcta
        answers.stream()
                .forEach(x -> {
                    if(optionalQuestion.get().getIdQuestion() == x.getQuestion().getIdQuestion()) {
                        System.out.println(x.getOptionAnswer() + " " +
                                x.isCorrectAnswer() + "\n");
                    }
                });
*/

		/* Obtener la pregunta con sus respuestas
		answers.stream().forEach(x -> {
			if(optionalQuestion.get().getIdQuestion() == x.getQuestion().getIdQuestion()) {
				System.out.println(x.getOptionAnswer() + "\n");
			}

		});
		*/
   // }

}
