package com.project.quiz.app.service;

import com.project.quiz.app.Question;
import com.project.quiz.app.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Success";
    }

    public String updateQuestion(Integer id, Question question) {

        //Optional is a container that wraps a value (in this case, a Question object).
        //It can either contain a value of type Question or be empty (i.e., not contain a value).
        //Instead of returning null when a value is not found, methods like findById() in JpaRepository return an Optional to indicate that the value might be missing.
        //Prevents NullPointerExceptions
        Optional<Question> existingQuestion = questionDao.findById(id);
        if (existingQuestion.isPresent()) {
            //Optional<Question> is a container object and does not have methods like setQuestionTitle() directly.
            //call .get() to access the actual Question object inside the Optional.
            Question updatedQuestion = existingQuestion.get();

            // Update the fields of the existing question with the new values
            updatedQuestion.setQuestionTitle(question.getQuestionTitle());
            updatedQuestion.setOption1(question.getOption1());
            updatedQuestion.setOption2(question.getOption2());
            updatedQuestion.setOption3(question.getOption3());
            updatedQuestion.setOption4(question.getOption4());
            updatedQuestion.setRightAnswer(question.getRightAnswer());
            updatedQuestion.setDifficultyLevel(question.getDifficultyLevel());
            updatedQuestion.setCategory(question.getCategory());

            questionDao.save(updatedQuestion);
            return "Question updated successfully!";
        } else {
            // If the question doesn't exist, return a message
            return "Question not found!";
        }
    }

    public String deleteQuestion(Integer id) {

       Optional<Question> existingQuestion =  questionDao.findById(id);
        if (existingQuestion.isPresent()) { //checks whether the Optional contains a value.
            questionDao.deleteById(id);
            return "Question deleted successfully";
    }
        return "Question not found";
    }
}
