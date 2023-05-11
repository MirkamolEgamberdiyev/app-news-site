package uz.fido.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fido.appnewssite.entity.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
