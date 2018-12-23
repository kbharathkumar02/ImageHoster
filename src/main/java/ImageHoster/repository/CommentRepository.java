package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment postComment(Comment comment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try
        {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }

    public List<Comment> getCommentsByImageId(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> query = em.createQuery("SELECT i from Comment i WHERE i.image.id =:imageId", Comment.class).setParameter("imageId", imageId);
        List<Comment> resultList = query.getResultList();

        return resultList;
    }
}
