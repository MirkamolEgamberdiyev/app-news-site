package uz.fido.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fido.appnewssite.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
