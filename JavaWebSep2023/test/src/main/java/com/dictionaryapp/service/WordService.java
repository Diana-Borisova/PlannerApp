package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class WordService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;


    public WordService(UserRepository userRepository, LoggedUser loggedUser, LanguageRepository languageRepository, WordRepository wordRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
    }

    public boolean create(AddWordDto addWordDto) {
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Language language = this.languageRepository.findByName(addWordDto.getLanguageEnum());
        Word word = new Word();
        word.setTerm(addWordDto.getTerm());
        word.setExample(addWordDto.getExample());
        word.setAddedBy(user.get());
        word.setLanguage(language);
        word.setTranslation(addWordDto.getTranslation());
        word.setInputDate(addWordDto.getInputDate());
      //  user.get().addWordToLoggedUser(word);
        this.wordRepository.save(word);
        this.userRepository.save(user.get());
        return true;
    }

    public List<Word> getAllWords() {

        return this.wordRepository.findAll().stream().collect(Collectors.toList());
    }

    public Set<Word> getGermanWords() {
        return this.wordRepository.findByLanguageName(LanguageEnum.GERMAN);
    }

    public Set<Word> getSpanishWords() {
        return this.wordRepository.findByLanguageName(LanguageEnum.SPANISH);
    }
    public Set<Word> getFrenchWords() {
        return this.wordRepository.findByLanguageName(LanguageEnum.FRENCH);
    }
    public Set<Word> getItalianWords() {
        return this.wordRepository.findByLanguageName(LanguageEnum.ITALIAN);
    }

    public void removeWord(Long id) {
        Word word = this.wordRepository.findById(id).orElseThrow();

        Optional<User> user = this.userRepository.findByAddedWordsContains(word);

        if (user.isPresent()) {
            User userEntity = user.get();
            userEntity.getAddedWords().remove(word);
            this.userRepository.save(userEntity);
        }

        this.wordRepository.delete(word);
    }
    public void removeAllWords() {

        this.wordRepository.deleteAll();

    }
}
