package ru.eltex.vkbot.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.vkbot.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
