package com.project.quiz.app.service;

import com.project.quiz.app.model.Question;
import com.project.quiz.app.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error adding question.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question question) {
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
            return new ResponseEntity<>("Question updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question not found!", HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        Optional<Question> existingQuestion = questionDao.findById(id);
        if (existingQuestion.isPresent()) {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Question not found!", HttpStatus.NOT_FOUND);
    }
}