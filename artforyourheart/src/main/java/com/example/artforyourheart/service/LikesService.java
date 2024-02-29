package com.example.artforyourheart.service;

import com.example.artforyourheart.model.Like;
import com.example.artforyourheart.model.User;
import com.example.artforyourheart.repository.LikesRepository;
import com.example.artforyourheart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikesRepository likesRepository;

    //getAll
    public List<Like> allLikes(){
        System.out.println(likesRepository.findAll());
        return likesRepository.findAll();
    }
    public void recordLike(Like like) {
        if (!likesRepository.existsByLikerIdAndLikeeId(like.getLikerId(), like.getLikeeId())) {
            likesRepository.save(like);
        }
    }

    public List<User> findMatches(String userId) {
        List<Like> likesGiven = likesRepository.findByLikerId(userId);
        return likesGiven.stream()
                .filter(like -> likesRepository.existsByLikerIdAndLikeeId(like.getLikeeId(), userId))
                .map(like -> userRepository.findById(like.getLikeeId()).orElse(null))
                .collect(Collectors.toList());
    }

}
//MVP