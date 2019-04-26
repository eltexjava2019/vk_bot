package ru.eltex.vkbot.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.vkbot.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
}
